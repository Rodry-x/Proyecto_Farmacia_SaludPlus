package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class UsuarioDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    // --- MÉTODOS DE VALIDACIÓN ---

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"),
            rs.getInt("id_rol"),
            rs.getString("nombres"),
            rs.getString("apellidos"),
            rs.getString("username"),
            rs.getString("password_hash")
        );
    }

    public Usuario validarUsuario(String user, String pass, int idRol) {
        String sql = "SELECT id_usuario, id_rol, nombres, apellidos, username, password_hash " +
                     "FROM Usuarios WHERE username = ? AND password_hash = ? AND id_rol = ? AND estado = 1";
        
        try (Connection con = db.conectar()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.setInt(3, idRol);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al validar usuario: " + e.getMessage());
        }
        return null;
    }

    // --- MÉTODOS DE ADMINISTRACIÓN ---

    // Este es el método que mencionaste, ahora integrado en la clase
    public boolean desactivarUsuario(int idUsuario) {
        String sql = "UPDATE Usuarios SET estado = 0 WHERE id_usuario = ?";
        
        try (Connection con = db.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al desactivar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // Tip: Podrías añadir también uno para reactivar usuarios
    public boolean activarUsuario(int idUsuario) {
        String sql = "UPDATE Usuarios SET estado = 1 WHERE id_usuario = ?";
        try (Connection con = db.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al activar usuario: " + e.getMessage());
            return false;
        }
    }
}