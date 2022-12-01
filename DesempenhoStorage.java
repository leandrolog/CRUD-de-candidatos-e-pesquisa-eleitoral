import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesempenhoStorage {
    private static List<Desempenho> desempenhos = new ArrayList<>();

    public static boolean inserir(Desempenho desempenho) {



        String query = "INSERT INTO desempenho (idCandidato, nome, data, intenção_de_voto) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, desempenho.getIdCandidato());
            statement.setString(2, desempenho.getNome());
            statement.setString(3, desempenho.getData());
            statement.setString(4, desempenho.getIntencaoDeVoto());
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

    public static boolean atualizar(Desempenho desempenho) {

        String query = "UPDATE desempenho SET idCandidato = ?, nome = ?, data = ?, intenção_de_voto = ?WHERE idDesempenho = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, desempenho.getIdCandidato());
            statement.setString(2, desempenho.getNome());
            statement.setString(3, desempenho.getData());
            statement.setString(4, desempenho.getIntencaoDeVoto());
            statement.setInt(5, desempenho.getIdDesempenho());

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

    public static boolean remover(Desempenho desempenho) {

        String query = "DELETE FROM desempenho WHERE idDesempenho = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, desempenho.getIdDesempenho());
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

    public static List<Desempenho> listar() {

        List<Desempenho> desempenhos = new ArrayList<>();

        String query = "SELECT * FROM desempenho ORDER BY idDesempenho";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Desempenho desempenho = new Desempenho();
                desempenho.setIdDesempenho(resultSet.getInt("IdDesempenho"));
                desempenho.setNome(resultSet.getString("nome"));
                desempenho.setIntencaoDeVoto(resultSet.getString("Intenção_de_Voto"));
                desempenho.setData(resultSet.getString("Data"));

                desempenhos.add(desempenho);
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

        return desempenhos;
    }


}
