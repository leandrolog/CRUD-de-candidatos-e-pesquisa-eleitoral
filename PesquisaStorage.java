import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisaStorage {
    private static List<Pesquisa> pesquisas = new ArrayList<>();
    private static int incremento = 1;

    public static boolean inserir(Pesquisa pesquisa) {
        pesquisa.setIdPesquisa(incremento++);
        pesquisas.add(pesquisa);

        String query = "INSERT INTO pesquisa_eleitoral (Turno, Data, Tipo_de_pesquisa, idPesquisa ) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pesquisa.getTurno());
            statement.setString(2, pesquisa.getData());
            statement.setString(3, pesquisa.getTipoDepesquisa());
            statement.setInt(4, pesquisa.getIdPesquisa());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                pesquisa.setIdPesquisa(resultSet.getInt(incremento));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean atualizar(Pesquisa pesquisa) {

        String query = "UPDATE pesquisa_eleitoral SET Turno = ?, Data = ?, Tipo_de_pesquisa = ? WHERE idPesquisa = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);

            statement.setInt(1, pesquisa.getIdPesquisa());
            statement.setString(2, pesquisa.getTurno());
            statement.setString(3, pesquisa.getData());
            statement.setString(4, pesquisa.getTipoDepesquisa());

            statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean remover(Pesquisa pesquisa) {

        String query = "DELETE FROM pesquisa_eleitoral WHERE idPesquisa = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, pesquisa.getIdPesquisa());
            statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static List<Pesquisa> listar() {

        List<Pesquisa> pesquisas = new ArrayList<>();

        String query = "SELECT * FROM pesquisa_eleitoral ORDER BY idPesquisa";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pesquisa pesquisa = new Pesquisa();
                pesquisa.setIdPesquisa(resultSet.getInt("idPesquisa"));
                pesquisa.setTurno(resultSet.getString("Turno"));
                pesquisa.setData(resultSet.getString("Data"));
                pesquisa.setTipoDepesquisa(resultSet.getString("Tipo_de_pesquisa"));

                pesquisas.add(pesquisa);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pesquisas;
    }
} // fim da classe TarefaStorage
