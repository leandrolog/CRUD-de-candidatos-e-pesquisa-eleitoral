import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisaStorage {
    private static List<Pesquisa> pesquisas = new ArrayList<>();


    public static boolean inserir(Pesquisa pesquisa) {

        String query = "INSERT INTO pesquisa_eleitoral (Turno, Data, Tipo_de_pesquisa, Instituto ) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pesquisa.getTurno());
            statement.setString(2, pesquisa.getData());
            statement.setString(3, pesquisa.getTipoDepesquisa());
            statement.setString(4, pesquisa.getInstituto());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
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

        String query = "UPDATE pesquisa_eleitoral SET turno = ?, data = ?,  tipo_de_pesquisa = ?, instituto = ?  WHERE idPesquisa = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);

            statement.setString(1, pesquisa.getTurno());
            statement.setString(2, pesquisa.getData());
            statement.setString(3, pesquisa.getTipoDepesquisa());
            statement.setString(4, pesquisa.getInstituto());
            statement.setInt(5, pesquisa.getIdPesquisa());

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
                pesquisa.setTurno(resultSet.getString("Turno"));
                pesquisa.setData(resultSet.getString("Data"));
                pesquisa.setTipoDepesquisa(resultSet.getString("Tipo_de_pesquisa"));
                pesquisa.setInstituto(resultSet.getString("instituto"));
                pesquisa.setTurno(resultSet.getString("idPesquisa"));


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
