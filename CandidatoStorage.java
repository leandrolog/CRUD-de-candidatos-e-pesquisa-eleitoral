import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoStorage {
    private static List<Candidato> candidatos = new ArrayList<>();
    private static int incremento = 1;


    public static boolean inserir(Candidato candidato) {
        candidato.setId(incremento++);
        candidatos.add(candidato);

        String query = "INSERT INTO candidato (nome, partido, idCandidato ) VALUES (?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, candidato.getNome());
            statement.setString(2, candidato.getPartido());
            statement.setInt(3, candidato.getId());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                candidato.setId(resultSet.getInt(incremento));
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

    public static boolean atualizar(Candidato candidato) {

        String query = "UPDATE candidato SET nome = ?, partido = ? WHERE idCandidato = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, candidato.getNome());
            statement.setString(2, candidato.getPartido());
            statement.setInt(3, candidato.getId());
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

    public static boolean remover(Candidato candidato) {

        String query = "DELETE FROM candidato WHERE idCandidato = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, candidato.getId());
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

    public static List<Candidato> listar() {

        List<Candidato> candidatos = new ArrayList<>();

        String query = "SELECT * FROM candidato ORDER BY idCandidato";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(resultSet.getInt("idCandidato"));
                candidato.setNome(resultSet.getString("nome"));
                candidato.setPartido(resultSet.getString("Partido"));

                candidatos.add(candidato);
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

        return candidatos;
    }


}
