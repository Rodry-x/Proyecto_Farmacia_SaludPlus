package dao;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {

    public List<Object[]> obtenerVentasPorFecha(java.util.Date desde, java.util.Date hasta) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id_venta, cajero, fecha, total_pagar "
                   + "FROM V_VENTAS "
                   + "WHERE CAST(fecha AS DATE) BETWEEN ? AND ? "
                   + "ORDER BY fecha DESC";

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
                   + "COALESCE(SUM(c.costo_total), 0) AS egresos "
                   + "FROM V_VENTAS v "
                   + "LEFT JOIN V_DETALLE_VENTA_COSTO c ON v.id_venta = c.id_venta "
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
        String sql = "SELECT TOP " + limite + " producto, total_vendido "
                   + "FROM V_RANKING_PRODUCTOS "
                   + "ORDER BY total_vendido " + orden;

        try (Connection con = ConectarBaseDatos.conectar()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                        rs.getString("producto"),
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
                   + "cajero, cantidad_ventas, total_ventas "
                   + "FROM V_RANKING_CAJEROS "
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
