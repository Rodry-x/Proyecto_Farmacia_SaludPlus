package dao;
import model.Cliente;

import database.ConectarBaseDatos;
import java.sql.*;
import java.time.LocalDate;

public class ClienteDAO {

    private Cliente mapear(ResultSet rs) throws SQLException {
        
        Date sqlDate = rs.getDate("fecha_nacimiento");
        LocalDate fechaNac = sqlDate != null ? sqlDate.toLocalDate() : null;
        
        return new Cliente(
            rs.getInt("id_cliente"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("dni"),
            rs.getInt("id_genero"),
            fechaNac
        );
    }

    public int insertar(Cliente c) {
        String sql = "INSERT INTO CLIENTES (nombre, apellido, dni, id_genero, fecha_nacimiento) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getDni());
            ps.setInt(4, c.getId_genero());
            ps.setDate(5, c.getFecha_nacimiento() != null
                ? Date.valueOf(c.getFecha_nacimiento()) : null);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        }
        return -1;
    }
    public boolean insertarTelefono(int idCliente, String telefono) {
        String sql = "INSERT INTO TELEFONO_CLIENTES (id_cliente, telefono) VALUES (?, ?)";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, telefono);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar teléfono: " + e.getMessage());
        }
        return false;
    }

    public boolean insertarCorreo(int idCliente, String correo) {
        String sql = "INSERT INTO CORREO_CLIENTES (id_cliente, correo) VALUES (?, ?)";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, correo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar correo: " + e.getMessage());
        }
        return false;
    }

    public Cliente buscarPorDni(String dni) {
        String sql = "SELECT * FROM CLIENTES WHERE dni = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por dni: " + e.getMessage());
        }
        return null;
    }
}
