package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class ClienteDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_cliente"), // Capturamos el ID de la base de datos
            rs.getString("dni"),
            rs.getString("ruc"),
            rs.getString("nombres"),
            rs.getString("apellidos"),
            rs.getString("telefono")
        );
    }

public Cliente buscarPorDocumento(String documento) {
    // Usamos OR para buscar en ambas columnas con el mismo valor
    String sql = "SELECT id_cliente, dni, ruc, nombres, apellidos, telefono " +
                 "FROM Clientes WHERE dni = ? OR ruc = ?";
    
    try (Connection con = db.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Asignamos el valor al primer y segundo signo de interrogación
        ps.setString(1, documento);
        ps.setString(2, documento);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return mapearCliente(rs);
            }
        }
    } catch (SQLException e) {
        System.err.println("❌ Error al buscar cliente por DNI/RUC: " + e.getMessage());
    }
    return null;
}

    public boolean insertarCliente(Cliente c) {
        String sql = "INSERT INTO Clientes (dni, ruc, nombres, apellidos, telefono) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = db.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, c.getDni());
            ps.setString(2, c.getRuc());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getApellidos());
            ps.setString(5, c.getTelefono());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
}