package database;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        // 1. Creamos el objeto de tu clase Conexion
        Conexion instanciaConexion = new Conexion();
        
        // 2. Intentamos conectar
        System.out.println("Intentando conectar a SQL Server...");
        Connection conexionLista = instanciaConexion.conectar();
        
        // 3. Verificamos el resultado
        if (conexionLista != null) {
            System.out.println("--- ✅ PRUEBA SUPERADA ---");
            System.out.println("Java logró entrar a la base de datos FarmaciaSaludPlus.");
            try {
                conexionLista.close(); // Cerramos la conexión de prueba
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("--- ❌ PRUEBA FALLIDA ---");
            System.out.println("Causas probables:");
            System.out.println("1. El puerto 1433 está cerrado (revisa el Configuration Manager).");
            System.out.println("2. El usuario 'sa' o la clave '123456' no coinciden.");
            System.out.println("3. El servicio 'SQL Server (MSSQLSERVER)' está detenido.");
        }
    }
}