package dao;
import model.MetodoPago;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoDAO {

    public List<MetodoPago> listarTodos() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT id_metodopago, nombre FROM METODO_PAGO";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new MetodoPago(rs.getInt("id_metodopago"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar metodos de pago: " + e.getMessage());
        }
        return lista;
    }

    public MetodoPago buscarPorNombre(String nombre) {
        String sql = "SELECT id_metodopago, nombre FROM METODO_PAGO WHERE nombre = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MetodoPago(rs.getInt("id_metodopago"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar metodo de pago por nombre: " + e.getMessage());
        }
        return null;
    }

    public MetodoPago buscarPorId(int id) {
        String sql = "SELECT id_metodopago, nombre FROM METODO_PAGO WHERE id_metodopago = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MetodoPago(rs.getInt("id_metodopago"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar metodo de pago por id: " + e.getMessage());
        }
        return null;
    }
}
