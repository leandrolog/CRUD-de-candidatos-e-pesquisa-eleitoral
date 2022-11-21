import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstitutoStorage {
    private static List<InstitutoDePesquisa> institutos = new ArrayList<>();
    private static int incremento = 1;

    public static boolean inserir(InstitutoDePesquisa instituto) {
        instituto.setIdInstituto(incremento++);
        institutos.add(instituto);



        String query = "INSERT INTO instituto_de_pesquisa (nome, empresa_contratante, idInstituto ) VALUES (?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, instituto.getNome());
            statement.setString(2, instituto.getEmpresaContratante());
            statement.setInt(3, instituto.getIdInstituto());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                instituto.setIdInstituto(resultSet.getInt(incremento));
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

    public static boolean atualizar(InstitutoDePesquisa instituto) {

        String query = "UPDATE instituto_de_pesquisa SET nome = ?, empresa_contratante = ? WHERE idInstituto = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, instituto.getNome());
            statement.setString(2, instituto.getEmpresaContratante());
            statement.setInt(3, instituto.getIdInstituto());
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

    public static boolean remover(InstitutoDePesquisa instituto) {

        String query = "DELETE FROM instituto_de_pesquisa WHERE idInstituto = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, instituto.getIdInstituto());
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

    public static List<InstitutoDePesquisa> listar() {

        List<InstitutoDePesquisa> institutos = new ArrayList<>();

        String query = "SELECT * FROM instituto_de_pesquisa ORDER BY idInstituto";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                InstitutoDePesquisa instituto = new InstitutoDePesquisa();
                instituto.setNome(resultSet.getString("nome"));
                instituto.setEmpresaContratante(resultSet.getString("empresa_contratante"));
                instituto.setIdInstituto(resultSet.getInt("idInstituto"));

                institutos.add(instituto);
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

        return institutos;
    }


} // fim da classe TarefaStorage
