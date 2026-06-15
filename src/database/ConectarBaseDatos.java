package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBaseDatos {
    
    // 1. Mantenemos tu Driver oficial de Microsoft SQL Server
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    // 2. Cambiamos la URL para apuntar a tu 'localhost' (tu máquina) en lugar de Azure, manteniendo tu BD
    //private static final String URL = "jdbc:sqlserver://farmaciasaludplus2026.database.windows.net:1433;databaseName=FarmaciaSaludPlus;encrypt=true;trustServerCertificate=true;loginTimeout=30;";
    private static final String URL = "jdbc:sqlserver://adminfarmacia.database.windows.net:1433;databaseName=FarmaciaSaludPlus2;encrypt=true;trustServerCertificate=true;loginTimeout=30;";
    
    // 3. Mantenemos tus credenciales reales exactas
    //private static final String USER = "adminsql"; 
    private static final String USER = "adminfarmacia";
    //private static final String PASS = "Farmacia2026"; 
    private static final String PASS = "Farmacia123";

    public Connection conectar() {
        Connection cn = null;
        try {
            // Cargar el driver de SQL Server
            Class.forName(DRIVER);
            
            // Establecer conexión con tu Docker local
            cn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("🟢 ¡Conexión exitosa a tu Docker local de SQL Server!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC de SQL Server. Verifica tus Libraries.");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión al Docker local: " + e.getMessage());
        }
        return cn;
    }
}
