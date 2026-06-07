package clases;

import java.sql.*;

public class UsuarioDAO {
    
    public Usuario validarUsuario(String user, String pass, int idRol) {
        Usuario usuarioEncontrado = null;
        // Consulta corregida con los nombres reales de tus columnas
        String sql = "SELECT id_usuario, nombres, username, password_hash, id_rol FROM Usuarios WHERE username = ? AND password_hash = ? AND id_rol = ?";
        
        database.ConectarBaseDatos db = new database.ConectarBaseDatos();
   
        try (Connection con = db.conectar()) {
            if (con == null) return null; // Protección contra error de conexión

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.setInt(3, idRol);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuarioEncontrado = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombres"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getInt("id_rol")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
        return usuarioEncontrado;
    }
}