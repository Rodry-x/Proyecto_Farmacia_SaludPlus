package clases;

import clases.Cliente;
import database.ConectarBaseDatos;
import java.sql.*;

public class ClienteDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    // 🔍 Método para buscar un cliente por su DNI en Azure
    public Cliente buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT nombre_completo, telefono, correo FROM Clientes WHERE LOWER(TRIM(dni_ruc)) = LOWER(TRIM(?))";
        
        try (Connection con = db.conectar()) {
            if (con == null) return null;
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Si lo encuentra, empaqueta todo en un objeto Cliente y lo devuelve
                        return new Cliente(
                            dni,
                            rs.getString("nombre_completo"),
                            rs.getString("telefono"),
                            rs.getString("correo")
                        );
                    }
                }
            }
        }
        return null; // Si no existe en Azure, devuelve null
    }

    // ➕ ¡NUEVO MÉTODO! Guarda un objeto Cliente mapeado directamente en la base de datos de Azure
    public boolean insertarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (dni_ruc, nombre_completo, telefono, correo) VALUES (?, ?, ?, ?)";
        
        // Estructura auto-cierre try-with-resources para proteger los sockets de Azure
        try (Connection con = db.conectar()) {
            if (con == null) {
                throw new SQLException("La conexión con el servidor de Azure es nula o se encuentra cerrada.");
            }
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Mapeamos los atributos encapsulados del objeto Cliente a los comodines (?) de la consulta SQL
                ps.setString(1, cliente.getDniRuc());
                ps.setString(2, cliente.getNombreCompleto());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getCorreo());
                
                // executeUpdate devuelve el número de filas afectadas. Si es mayor a 0, la inserción fue un éxito.
                int filasAfectadas = ps.executeUpdate();
                return filasAfectadas > 0;
            }
        }
    }
}