package dao;
import model.Producto;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public Producto buscarPorId(int id) {
        String sql = "SELECT id_producto, nombre, precio_venta FROM PRODUCTOS WHERE id_producto = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getDouble("precio_venta"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Producto> obtenerSugerencias(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT TOP 10 id_producto, nombre, precio_venta FROM PRODUCTOS WHERE nombre LIKE ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro.trim() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_venta")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerSugerencias: " + e.getMessage());
        }
        return lista;
    }

    public List<Producto> listarConStock() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.id_categoria, p.precio_venta, "
                   + "p.stock_general, p.stock_minimo, "
                   + "ISNULL(CONVERT(VARCHAR, l.fecha_vencimiento, 23), 'Sin lote') AS fecha_vencimiento "
                   + "FROM PRODUCTOS p "
                   + "LEFT JOIN LOTES l ON l.id_lote = ("
                   + "    SELECT TOP 1 id_lote FROM LOTES WHERE id_detalle_compra IN ("
                   + "        SELECT id_detalle FROM DETALLE_COMPRA WHERE id_producto = p.id_producto"
                   + "    ) ORDER BY fecha_vencimiento ASC"
                   + ") "
                   + "ORDER BY p.nombre";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getInt("id_categoria"),
                    rs.getDouble("precio_venta"),
                    rs.getInt("stock_general"),
                    rs.getInt("stock_minimo"),
                    rs.getString("fecha_vencimiento")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error en listarConStock: " + e.getMessage());
        }
        return lista;
    }

    public int obtenerStock(int idProducto) {
        String sql = "SELECT stock_general FROM PRODUCTOS WHERE id_producto = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock_general");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerStock: " + e.getMessage());
        }
        return 0;
    }

    public double obtenerPorcentajeImpuesto(int idProducto) {
        String sql = "SELECT i.porcentaje FROM PRODUCTOS p "
                   + "JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto "
                   + "WHERE p.id_producto = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("porcentaje");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerPorcentajeImpuesto: " + e.getMessage());
        }
        return 0.0;
    }
}
