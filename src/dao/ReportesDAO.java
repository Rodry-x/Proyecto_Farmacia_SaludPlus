package dao;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {

    public List<Object[]> obtenerVentasPorFecha(java.util.Date desde, java.util.Date hasta) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT v.id_venta, CONCAT(u.nombre, ' ', u.apellido) AS cajero, "
                   + "v.fecha, v.total_pagar "
                   + "FROM VENTA v "
                   + "INNER JOIN USUARIOS u ON v.id_usuario = u.id_usuario "
                   + "WHERE CAST(v.fecha AS DATE) BETWEEN ? AND ? "
                   + "ORDER BY v.fecha DESC";

        try (Connection con = ConectarBaseDatos.conectar()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDate(1, new java.sql.Date(desde.getTime()));
                ps.setDate(2, new java.sql.Date(hasta.getTime()));

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Object[]{
                            rs.getInt("id_venta"),
                            rs.getString("cajero"),
                            rs.getTimestamp("fecha"),
                            rs.getDouble("total_pagar")
                        });
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerVentasPorFecha: " + e.getMessage());
        }
        return lista;
    }

    public Object[] obtenerResumenFinanciero(java.util.Date desde, java.util.Date hasta) {
        String sql = "SELECT "
                   + "COALESCE(SUM(v.total_pagar), 0) AS ingresos, "
                   + "COALESCE(SUM(dv.cantidad * dc.precio_unitario), 0) AS egresos "
                   + "FROM VENTA v "
                   + "LEFT JOIN DETALLE_VENTA dv ON v.id_venta = dv.id_venta "
                   + "LEFT JOIN LOTES l ON dv.id_lote = l.id_lote "
                   + "LEFT JOIN DETALLE_COMPRA dc ON l.id_detalle_compra = dc.id_detalle "
                   + "WHERE CAST(v.fecha AS DATE) BETWEEN ? AND ?";

        try (Connection con = ConectarBaseDatos.conectar()) {
            if (con == null) return new Object[]{0.0, 0.0, 0.0, 0.0};

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDate(1, new java.sql.Date(desde.getTime()));
                ps.setDate(2, new java.sql.Date(hasta.getTime()));

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        double ingresos = rs.getDouble("ingresos");
                        double egresos = rs.getDouble("egresos");
                        double ganancia = ingresos - egresos;
                        double margen = ingresos > 0 ? (ganancia / ingresos) * 100 : 0;
                        return new Object[]{ingresos, egresos, ganancia, margen};
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerResumenFinanciero: " + e.getMessage());
        }
        return new Object[]{0.0, 0.0, 0.0, 0.0};
    }

    public List<Object[]> obtenerRankingProductos(boolean masVendidos, int limite) {
        List<Object[]> lista = new ArrayList<>();
        String orden = masVendidos ? "DESC" : "ASC";
        String sql = "SELECT TOP " + limite + " p.nombre, SUM(dv.cantidad) AS total_vendido "
                   + "FROM DETALLE_VENTA dv "
                   + "INNER JOIN PRODUCTOS p ON dv.id_producto = p.id_producto "
                   + "GROUP BY p.nombre "
                   + "ORDER BY total_vendido " + orden;

        try (Connection con = ConectarBaseDatos.conectar()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                        rs.getString("nombre"),
                        rs.getInt("total_vendido")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerRankingProductos: " + e.getMessage());
        }
        return lista;
    }

    public List<Object[]> obtenerRankingCajeros(boolean mejores, int limite) {
        List<Object[]> lista = new ArrayList<>();
        String orden = mejores ? "DESC" : "ASC";
        String sql = "SELECT TOP " + limite + " "
                   + "u.id_usuario, "
                   + "CONCAT(u.nombre, ' ', u.apellido) AS cajero, "
                   + "COUNT(v.id_venta) AS cantidad_ventas, "
                   + "COALESCE(SUM(v.total_pagar), 0) AS total_ventas "
                   + "FROM VENTA v "
                   + "INNER JOIN USUARIOS u ON v.id_usuario = u.id_usuario "
                   + "GROUP BY u.id_usuario, u.nombre, u.apellido "
                   + "ORDER BY total_ventas " + orden;

        try (Connection con = ConectarBaseDatos.conectar()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                        rs.getString("cajero"),
                        rs.getInt("cantidad_ventas"),
                        rs.getDouble("total_ventas")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en obtenerRankingCajeros: " + e.getMessage());
        }
        return lista;
    }
}
