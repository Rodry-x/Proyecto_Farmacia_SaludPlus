package database; // Asegúrate de que coincida con el nombre de tu carpeta de paquete

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBaseDatos {

    // 1. Datos de conexión a tu servidor Azure SQL
    private static final String SERVIDOR = "adminfarmacia.database.windows.net";
    private static final String BASE_DATOS = "FarmaciaSaludPlus2";
    private static final String USUARIO = "adminfarmacia";
    private static final String CLAVE = "Farmacia123";

    // 2. Cadena de conexión con los parámetros de seguridad de Azure
    private static final String URL = "jdbc:sqlserver://" + SERVIDOR + ":1433;"
            + "database=" + BASE_DATOS + ";"
            + "user=" + USUARIO + ";"
            + "password=" + CLAVE + ";"
            + "encrypt=true;"            // Obligatorio para Azure
            + "trustServerCertificate=true;" // Evita problemas de certificados locales
            + "loginTimeout=30;";

    /**
     * Método para conectar a la base de datos en la nube
     * @return Connection objeto de conexión listo para usar
     */
    public static Connection conectar() {
        Connection cn = null;
        try {
            // Cargar el driver moderno de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establecer la conexión
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
