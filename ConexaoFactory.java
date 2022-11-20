import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/pi";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    private static Connection conexao;
    private ConexaoFactory() {}

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conexao;
    }
} // fim da classe ConexaoFactory