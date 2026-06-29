package dao;
import model.Usuario;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id_usuario"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getInt("id_rol")
        );
    }

    public int insertar(Usuario u) {
        String sql = "INSERT INTO USUARIOS (nombre, apellido, username, password, id_rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId_rol());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
        return -1;
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE USUARIOS SET nombre = ?, apellido = ?, username = ?, password = ?, id_rol = ? WHERE id_usuario = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId_rol());
            ps.setInt(6, u.getId_usuario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM USUARIOS WHERE id_usuario = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) throw new SQLException("Conexión a BD es null");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            throw new RuntimeException("Error al eliminar: " + e.getMessage(), e);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS ORDER BY id_usuario";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    public List<Usuario> buscarPorUsername(String username) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS WHERE username LIKE ? ORDER BY id_usuario";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + username + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios: " + e.getMessage());
        }
        return lista;
    }

    public Usuario validarUsuario(String username, String password, int idRol) {
        String sql = "SELECT * FROM USUARIOS WHERE username = ? AND password = ? AND id_rol = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, idRol);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIOS WHERE id_usuario = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por id: " + e.getMessage());
        }
        return null;
    }
}
