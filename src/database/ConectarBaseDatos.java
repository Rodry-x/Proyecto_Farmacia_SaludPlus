package database; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBaseDatos {

   
    private static final String SERVIDOR = "trabajo2026.database.windows.net";
    private static final String BASE_DATOS = "Trabajo_final";
    private static final String USUARIO = "Trabajofinal";
    private static final String CLAVE = "Trabajo2026";
    private static final String URL = "jdbc:sqlserver://" + SERVIDOR + ":1433;"
            + "database=" + BASE_DATOS + ";"
            + "user=" + USUARIO + ";"
            + "password=" + CLAVE + ";"
            + "encrypt=true;"            // Obligatorio para Azure
            + "trustServerCertificate=true;" // Evita problemas de certificados locales
            + "loginTimeout=30;";

    public static Connection conectar() {
        
        Connection cn = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            cn = DriverManager.getConnection(URL);
            System.out.println("🚀 ¡Conexión exitosa a la base de datos en la nube (Azure SQL)!");

        } catch (ClassNotFoundException e) {
            
            System.err.println("❌ Error: ¡No se encontró el Driver JDBC de SQL Server! Agrega el archivo .jar a las librerías.");
            e.printStackTrace();
            
        } catch (SQLException e) {
            
            System.err.println("❌ Error de SQL al intentar conectar a Azure: " + e.getMessage());
            e.printStackTrace();
            
        }
        return cn;
    }
}