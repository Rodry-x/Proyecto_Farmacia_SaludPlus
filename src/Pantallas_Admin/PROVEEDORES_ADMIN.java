package Pantallas_Admin;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class PROVEEDORES_ADMIN extends javax.swing.JPanel {
public void agregarProveedor(String nombre,String telefono, String direccion, String gmail) {
    modelo.addRow(new Object[]{nombre,telefono, direccion, gmail});
}
public DefaultTableModel modelo;
    public PROVEEDORES_ADMIN() {
    initComponents();
    modelo = new DefaultTableModel();
    modelo.addColumn("ID"); // Columna de control oculta
    modelo.addColumn("RUC");
    modelo.addColumn("Nombre");
    modelo.addColumn("Dirección");
    modelo.addColumn("Teléfonos");
    modelo.addColumn("Gmail");

    tablaProveedores.setModel(modelo);
    
    // Ocultar la columna ID (índice 0) para mantener la interfaz limpia
    tablaProveedores.getColumnModel().getColumn(0).setMinWidth(0);
    tablaProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
    tablaProveedores.getColumnModel().getColumn(0).setWidth(0);

    // 🔥 AQUÍ EJECUTAS LA CARGA DIRECTA DE LA BD:
    listarProveedores();
}
    public void listarProveedores() {
    // 1. Limpiamos el modelo para no duplicar filas visuales si se recarga
    modelo.setRowCount(0); 

    // 2. Consulta SQL usando STRING_AGG para agrupar múltiples teléfonos y correos en una sola celda separados por coma
    String sql = "SELECT p.id_proveedor, p.ruc, p.nombre, p.direccion, " +
                 "STRING_AGG(t.telefono, ', ') AS telefonos, " +
                 "STRING_AGG(c.correo, ', ') AS correos " +
                 "FROM PROVEEDOR p " +
                 "LEFT JOIN TELEFONO_PROVEEDOR t ON p.id_proveedor = t.id_proveedor " +
                 "LEFT JOIN CORREO_PROVEEDOR c ON p.id_proveedor = c.id_proveedor " +
                 "GROUP BY p.id_proveedor, p.ruc, p.nombre, p.direccion";

    // 3. Conectamos directamente a tu clase en Azure usando los paréntesis del try
    try (Connection con = database.ConectarBaseDatos.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        if (con == null) {
            System.out.println("❌ No se pudo conectar a Azure para listar.");
            return;
        }

        // 4. Recorremos los registros devueltos por la base de datos en la nube
        while (rs.next()) {
            int id = rs.getInt("id_proveedor");
            String ruc = rs.getString("ruc");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            // Si no tienen teléfonos o correos, validamos para que no muestre la palabra "null"
            String telefonos = rs.getString("telefonos") != null ? rs.getString("telefonos") : "Sin teléfono";
            String correos = rs.getString("correos") != null ? rs.getString("correos") : "Sin correo";

            // 5. Agregamos la fila al JTable en el orden de tus columnas creadas
            modelo.addRow(new Object[]{id, ruc, nombre, direccion, telefonos, correos});
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al listar proveedores desde Azure SQL: " + e.getMessage());
        e.printStackTrace();
    }
}
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(943, 120));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 160));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(lbl_logo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(843, 100));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 39)); // NOI18N
        jLabel2.setText("GESTION DE PROVEEDORES");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setText("PROVEEDORES ");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 40));

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel10.setRequestFocusEnabled(false);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Nuevo proveedor");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel10.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 580, 150, 30));

        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneliminar.setText("Eliminar proveedor ");
        btneliminar.addActionListener(this::btneliminarActionPerformed);
        jPanel10.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 620, 160, 30));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setText("Editar Proveedor");
        btnEditar.addActionListener(this::btnEditarActionPerformed);
        jPanel10.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 580, -1, 30));

        tablaProveedores.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "RUC", "Nombre", "Telefono (s)", "Direccion", "Correo"
            }
        ));
        tablaProveedores.setGridColor(new java.awt.Color(0, 0, 0));
        tablaProveedores.setPreferredSize(new java.awt.Dimension(600, 100));
        tablaProveedores.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tablaProveedores);

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 1080, 540));

        add(jPanel10, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tablaProveedores.getSelectedRow();

        if (filaSeleccionada >= 0) {
            int id = (int) tablaProveedores.getValueAt(filaSeleccionada, 0);          // Columna 0 (ID es entero)
            String ruc = (String) tablaProveedores.getValueAt(filaSeleccionada, 1);       // Columna 1 (RUC)
            String nombre = (String) tablaProveedores.getValueAt(filaSeleccionada, 2);    // Columna 2 (Nombre)
            String direccion = (String) tablaProveedores.getValueAt(filaSeleccionada, 3); // Columna 3 (Dirección)
            String telefonos = (String) tablaProveedores.getValueAt(filaSeleccionada, 4); // Columna 4 (Teléfonos)
            String correos = (String) tablaProveedores.getValueAt(filaSeleccionada, 5);   // Columna 5 (Correos)

            EDITAR_PROVEEDOR_ADMIN ventanaEditar = new EDITAR_PROVEEDOR_ADMIN(this);

            // Ahora sí compilará porque este método existe en la nueva clase
            ventanaEditar.cargarDatosFormulario(id, ruc, nombre, direccion, telefonos, correos);

            ventanaEditar.setVisible(true);

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un proveedor de la tabla para editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // 1. Obtener la fila seleccionada de tu JTable
    int filaSeleccionada = tablaProveedores.getSelectedRow();
    
    if (filaSeleccionada >= 0) {
        // El ID está en la columna 0
        int id = (int) tablaProveedores.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tablaProveedores.getValueAt(filaSeleccionada, 2); // Nombre en col 2

        // 2. Preguntar al usuario si realmente desea eliminarlo
        int confirmar = javax.swing.JOptionPane.showConfirmDialog(
            this, 
            "¿Estás seguro de que deseas eliminar al proveedor '" + nombre + "'?\nEsta acción borrará también sus teléfonos y correos.", 
            "Confirmar Eliminación", 
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE
        );

        if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
            // 3. Ejecutar la eliminación mediante el DAO
            controller.ProveedorDAO dao = new controller.ProveedorDAO();
            if (dao.eliminar(id)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
                
                // 4. Refrescar tu JTable automáticamente para que desaparezca visualmente
                listarProveedores(); 
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "❌ Error: No se pudo eliminar el proveedor desde Azure SQL.");
            }
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un proveedor de la tabla para eliminar.");
    }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CREAR_PROVEEDOR_ADMIN p_crear = new CREAR_PROVEEDOR_ADMIN(this);
        p_crear.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JTable tablaProveedores;
    // End of variables declaration//GEN-END:variables
}
