package dao;

import database.ConectarBaseDatos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class CompraDao {

    // ================================================================
    //  Listar todas las compras → tablaCompras
    //  Columnas: N°COMPRA, FECHA, PROVEEDOR, TOTAL, ESTADO, USUARIO
    // ================================================================
    public void listarCompras(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        modelo.addColumn("N°COMPRA");
        modelo.addColumn("FECHA");
        modelo.addColumn("PROVEEDOR");
        modelo.addColumn("TOTAL");
        modelo.addColumn("ESTADO");
        modelo.addColumn("USUARIO");

        tabla.setModel(modelo);

        try {
            ConectarBaseDatos obj = new ConectarBaseDatos();
            Statement st = obj.conectar().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM REPORTE_COMPRAS ORDER BY [ID Compra] DESC");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("ID Compra"),
                    rs.getDate("Fecha"),
                    rs.getString("Proveedor"),
                    "S/ " + String.format("%.2f", rs.getDouble("Total")),
                    rs.getString("Estado"),
                    rs.getString("Usuario")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar compras: " + e.toString());
        }
    }

    // ================================================================
    //  Listar detalle de una compra → TablaDetalleCompra
    //  Columnas: CÓDIGO, PRODUCTO, CANTIDAD, PRECIO COMPRA, SUB TOTAL
    // ================================================================
    public void listarDetalle(int idCompra, JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO COMPRA");
        modelo.addColumn("SUB TOTAL");
        modelo.addColumn("FECHA VENCIMIENTO");
        modelo.addColumn("DÍAS PARA VENCER");

        tabla.setModel(modelo);

        try {
            ConectarBaseDatos obj = new ConectarBaseDatos();
            Statement st = obj.conectar().createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT * FROM REPORTE_DETALLE_COMPRA WHERE [ID Compra] = " + idCompra
            );

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("ID Producto"),
                    rs.getString("Producto"),
                    rs.getInt("Cantidad"),
                    "S/ " + String.format("%.2f", rs.getDouble("Precio Unitario")),
                    "S/ " + String.format("%.2f", rs.getDouble("Subtotal")),
                    rs.getDate("Fecha Vencimiento"),
                    rs.getObject("Dias para Vencer") != null ? rs.getInt("Dias para Vencer") + " días" : "Sin fecha"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar detalle: " + e.toString());
        }
    }

    // ================================================================
    //  Confirmar recepción → SP ConfirmarRecepcionCompra
    //  Cambia estado a RECIBIDO + crea lotes automáticamente
    // ================================================================
    public void confirmarRecepcion(int idCompra) {
        try {
            CallableStatement cs = ConectarBaseDatos.conectar()
                .prepareCall("{call ConfirmarRecepcionCompra(?)}");
            cs.setInt(1, idCompra);
            cs.execute();
            JOptionPane.showMessageDialog(null,
                "✔ Compra recibida.\nLos lotes fueron creados correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al confirmar recepción: " + e.getMessage());
        }
    }

    // ================================================================
    //  Buscar el nombre de un proveedor por su ID
    // ================================================================
    public String obtenerNombreProveedor(int idProveedor) {
        String sql = "SELECT nombre FROM PROVEEDOR WHERE id_proveedor = ?";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage());
        }
        return null;
    }

    // ================================================================
    //  Registrar la cabecera de la compra (SP registrar_compra)
    //  Devuelve el id_compra generado, o -1 si algo falló
    // ================================================================
    public int registrarCompra(int idUsuario, int idProveedor, double subtotal, double igvTotal, double totalCompra) {
        try (Connection con = ConectarBaseDatos.conectar();
             CallableStatement cs = con.prepareCall("{call registrar_compra(?,?,?,?,?)}")) {
            cs.setInt(1, idUsuario);
            cs.setInt(2, idProveedor);
            cs.setDouble(3, subtotal);
            cs.setDouble(4, igvTotal);
            cs.setDouble(5, totalCompra);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_compra_generado");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la compra: " + e.getMessage());
        }
        return -1;
    }

    // ================================================================
    //  Registrar una línea de detalle (SP registrar_detalle_compra)
    // ================================================================
    public boolean registrarDetalleCompra(int idCompra, int idProducto, int stockComprado,
            double precioUnitario, double subtotal, double igv, double totalDetalle,
            java.sql.Date fechaVencimiento) {
        try (Connection con = ConectarBaseDatos.conectar();
             CallableStatement cs = con.prepareCall("{call registrar_detalle_compra(?,?,?,?,?,?,?,?)}")) {
            cs.setInt(1, idCompra);
            cs.setInt(2, idProducto);
            cs.setInt(3, stockComprado);
            cs.setDouble(4, precioUnitario);
            cs.setDouble(5, subtotal);
            cs.setDouble(6, igv);
            cs.setDouble(7, totalDetalle);
            if (fechaVencimiento != null) {
                cs.setDate(8, fechaVencimiento);
            } else {
                cs.setNull(8, java.sql.Types.DATE);
            }
            cs.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el detalle de compra: " + e.getMessage());
            return false;
        }
    }

    // ================================================================
    //  Buscar producto por código
    //  Retorna: [0]=id, [1]=nombre, [2]=precio_venta
    // ================================================================
    public Object[] obtenerProductoPorCodigo(int idProducto) {
        String sql = "SELECT id_producto, nombre, precio_venta FROM PRODUCTOS WHERE id_producto = ?";
        try (Connection cn = ConectarBaseDatos.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_venta")
                    };
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    // ================================================================
    //  Obtener id_proveedor por nombre
    // ================================================================
    public int obtenerIdProveedorPorNombre(String nombre) {
        String sql = "SELECT id_proveedor FROM PROVEEDOR WHERE nombre = ?";
        try (Connection cn = ConectarBaseDatos.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_proveedor");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage());
        }
        return -1;
    }

}