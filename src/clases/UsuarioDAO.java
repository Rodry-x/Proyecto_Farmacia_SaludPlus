package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class UsuarioDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"),
            rs.getInt("id_rol"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("username"),
            rs.getString("password")
        );
    }

    public Usuario validarUsuario(String user, String pass, int idRol) {
        // Ajustado a los nombres exactos de tu tabla USUARIOS
        String sql = "SELECT id_usuario, id_rol, nombre, apellido, username, password " +
                     "FROM USUARIOS WHERE username = ? AND password = ? AND id_rol = ?";
        
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
}