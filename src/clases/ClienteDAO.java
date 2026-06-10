package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class ClienteDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    // Mapea los datos respetando la tabla real de la BD
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_cliente"),
            rs.getString("dni"),
            rs.getString("ruc"),
            rs.getString("nombres"),
            rs.getString("apellidos"),
            "" // Dejamos teléfono vacío o por defecto ya que la tabla de tu amigo no lo incluye
        );
    }

    // Busca por DNI o RUC (Se queda tal cual lo tenías, funciona perfecto)
    public Cliente buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM Clientes WHERE dni = ? OR ruc = ?";
        try (Connection con = ConectarBaseDatos.conectar(); // Usamos tu método estático moderno
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.setString(2, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    /**
     * Guarda el cliente en Azure y RETORNA EL ID AUTOGENERADO.
     * Si devuelve -1 es porque hubo un error (como DNI duplicado).
     */
    public int insertarClienteYObtenerId(Cliente c) {
        // SQL limpio según la estructura de la base de datos de tu amigo
        String sql = "INSERT INTO Clientes (nombres, apellidos, dni, ruc) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConectarBaseDatos.conectar(); // Conexión estática directa
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            
            // Si el DNI o RUC están vacíos, los mandamos como null para evitar conflictos con el índice UNIQUE
            ps.setString(3, (c.getDni() == null || c.getDni().trim().isEmpty()) ? null : c.getDni().trim());
            ps.setString(4, (c.getRuc() == null || c.getRuc().trim().isEmpty()) ? null : c.getRuc().trim());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Azure nos devuelve el ID que le asignó a este cliente
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // ¡Retorna el ID real (ej: 7)!
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente en Azure: " + e.getMessage());
        }
        return -1; // Retorna -1 si falló
    }
}