package dao;
import model.ItemVenta;
import model.Venta;

import database.ConectarBaseDatos;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class VentaDAO {

    private Venta mapear(ResultSet rs) throws SQLException {
        return new Venta(
            rs.getInt("id_venta"),
            rs.getInt("id_cliente"),
            rs.getInt("id_usuario"),
            rs.getInt("id_metodopago"),
            rs.getTimestamp("fecha").toLocalDateTime(),
            rs.getDouble("subtotal"),
            rs.getDouble("igv_total"),
            rs.getDouble("total_pagar")
        );
    }

    public int insertar(Venta v) {
        String sql = "INSERT INTO VENTA (id_cliente, id_usuario, id_metodopago, fecha, subtotal, igv_total, total_pagar) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, v.getId_cliente());
            ps.setInt(2, v.getId_usuario());
            ps.setInt(3, v.getId_metodopago());
            ps.setTimestamp(4, Timestamp.valueOf(v.getFecha()));
            ps.setDouble(5, v.getSubtotal());
            ps.setDouble(6, v.getIgv_total());
            ps.setDouble(7, v.getTotal_pagar());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState() + " | Código: " + e.getErrorCode());
        }
        return -1;
    }

    public Venta buscarPorId(int id) {
        String sql = "SELECT * FROM VENTA WHERE id_venta = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar venta por id: " + e.getMessage());
        }
        return null;
    }

    public String obtenerSiguienteNumeroVenta() {
        String sql = "SELECT ISNULL(MAX(id_venta), 0) + 1 AS next FROM VENTA";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "V" + String.format("%05d", rs.getInt("next"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener siguiente numero: " + e.getMessage());
        }
        return "V00001";
    }

    public boolean guardarVentaCompleta(Venta v, List<ItemVenta> productos) {
        String sqlVenta = "INSERT INTO VENTA (id_cliente, id_usuario, id_metodopago, fecha, subtotal, igv_total, total_pagar) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DETALLE_VENTA (id_venta, id_producto, id_lote, precio_unitario, cantidad, subtotal, igv, total_producto) "
                          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlLote = "SELECT TOP 1 l.id_lote, i.porcentaje "
                       + "FROM LOTES l "
                       + "JOIN DETALLE_COMPRA dc ON l.id_detalle_compra = dc.id_detalle "
                       + "JOIN PRODUCTOS p ON dc.id_producto = p.id_producto "
                       + "JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto "
                       + "WHERE dc.id_producto = ? AND l.stock_actual > 0 "
                       + "ORDER BY l.fecha_vencimiento ASC";
        String sqlUpdateStock = "UPDATE LOTES SET stock_actual = stock_actual - ? WHERE id_lote = ?";
        String sqlUpdateStockGeneral = "UPDATE PRODUCTOS SET stock_general = ("
                                     + "SELECT ISNULL(SUM(l.stock_actual), 0) "
                                     + "FROM LOTES l "
                                     + "JOIN DETALLE_COMPRA dc ON l.id_detalle_compra = dc.id_detalle "
                                     + "WHERE dc.id_producto = ?"
                                     + ") WHERE id_producto = ?";

        try (Connection con = ConectarBaseDatos.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, v.getId_cliente());
                psVenta.setInt(2, v.getId_usuario());
                psVenta.setInt(3, v.getId_metodopago());
                psVenta.setTimestamp(4, Timestamp.valueOf(v.getFecha()));
                psVenta.setDouble(5, v.getSubtotal());
                psVenta.setDouble(6, v.getIgv_total());
                psVenta.setDouble(7, v.getTotal_pagar());
                psVenta.executeUpdate();

                try (ResultSet rs = psVenta.getGeneratedKeys()) {
                    if (!rs.next()) {
                        con.rollback();
                        System.err.println("ERROR: No se pudo obtener el ID de la venta generado.");
                        return false;
                    }
                    int idVenta = rs.getInt(1);

                    try (PreparedStatement psLote = con.prepareStatement(sqlLote);
                         PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
                         PreparedStatement psStock = con.prepareStatement(sqlUpdateStock);
                         PreparedStatement psStockGeneral = con.prepareStatement(sqlUpdateStockGeneral)) {

                        for (ItemVenta prod : productos) {
                            psLote.setInt(1, prod.getIdProducto());
                            try (ResultSet rsLote = psLote.executeQuery()) {
                                if (!rsLote.next()) {
                                    con.rollback();
                                    System.err.println("ERROR: No hay stock disponible para el producto ID=" + prod.getIdProducto());
                                    return false;
                                }
                                int idLote = rsLote.getInt("id_lote");
                                double porcentajeIGV = rsLote.getDouble("porcentaje");

                                int cantidad = prod.getCantidad();
                                double precio = prod.getPrecioUnitario();
                                double subtotal = cantidad * precio;
                                double igv = subtotal * (porcentajeIGV / 100.0);
                                double totalProducto = subtotal + igv;

                                psDetalle.setInt(1, idVenta);
                                psDetalle.setInt(2, prod.getIdProducto());
                                psDetalle.setInt(3, idLote);
                                psDetalle.setDouble(4, precio);
                                psDetalle.setInt(5, cantidad);
                                psDetalle.setDouble(6, subtotal);
                                psDetalle.setDouble(7, igv);
                                psDetalle.setDouble(8, totalProducto);
                                psDetalle.executeUpdate();

                                psStock.setInt(1, cantidad);
                                psStock.setInt(2, idLote);
                                psStock.executeUpdate();

                                psStockGeneral.setInt(1, prod.getIdProducto());
                                psStockGeneral.setInt(2, prod.getIdProducto());
                                psStockGeneral.executeUpdate();
                            }
                        }
                    }
                    con.commit();
                    return true;
                }
            } catch (SQLException e) {
                con.rollback();
                System.err.println("Error en guardarVentaCompleta: " + e.getMessage());
                System.err.println("SQL State: " + e.getSQLState() + " | Código: " + e.getErrorCode());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexion en guardarVentaCompleta: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState() + " | Código: " + e.getErrorCode());
            return false;
        }
    }
}
