
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // Estos son los datos para entrar al "archivador"
    // Si tu amigo te da su IP, cámbiala donde dice 'localhost'
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FarmaciaSaludPlus;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa"; // Usuario de SQL Server
    private static final String PASS = "TuPasswordSeguro"; // La clave de SQL Server

    public Connection conectar() {
        Connection cn = null;
        try {
            // Paso 1: Cargar el Driver (el cable que descargaste)
            Class.forName(DRIVER);
            
            // Paso 2: Intentar abrir la conexión con la URL, usuario y clave
            cn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("¡Conexión exitosa a la base de datos!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el Driver (revisa las Libraries)");
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        return cn;
    }
}