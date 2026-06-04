package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBaseDatos {
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    private static final String URL = "jdbc:sqlserver://farmaciasaludplus2026.database.windows.net:1433;databaseName=FarmaciaSaludPlus;encrypt=true;trustServerCertificate=true;loginTimeout=30;";
    
    private static final String USER = "adminsql"; 
    private static final String PASS = "Farmacia2026"; 

    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName(DRIVER);
            
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

