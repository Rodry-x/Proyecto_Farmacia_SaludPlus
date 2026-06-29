package clases;

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
}