package Pantallas_Admin;

import javax.swing.table.DefaultTableModel;

public class PROVEEDORES_ADMIN extends javax.swing.JPanel {
public void agregarProveedor(String nombre,String telefono, String direccion, String gmail) {
    modelo.addRow(new Object[]{nombre,telefono, direccion, gmail});
}
public DefaultTableModel modelo;
    public PROVEEDORES_ADMIN() {
        initComponents();
    modelo = new DefaultTableModel();
    modelo.addColumn("Nombre");
    modelo.addColumn("Telefono");
    modelo.addColumn("Dirección");
    modelo.addColumn("Gmail");

    tablaProveedores.setModel(modelo);
    }   
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 20));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 66, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("GESTION DE PROVEEDORES");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PROVEEDORES ");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, 40));

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(943, 500));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel10.setRequestFocusEnabled(false);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProveedores.setBackground(new java.awt.Color(255, 255, 255));
        tablaProveedores.setForeground(new java.awt.Color(0, 0, 0));
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Telefono", "Dirección", "Gmail"
            }
        ));
        tablaProveedores.setGridColor(new java.awt.Color(0, 0, 0));
        tablaProveedores.setPreferredSize(new java.awt.Dimension(600, 100));
        tablaProveedores.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tablaProveedores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tablaProveedores);

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 756, -1));

        jPanel2.add(jPanel10, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Lista de proveedores ");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        btneliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneliminar.setText("Eliminar proveedor ");
        btneliminar.addActionListener(this::btneliminarActionPerformed);
        jPanel8.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 150, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Nuevo proveedor");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 150, -1));

        jPanel3.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         CREAR_PROVEEDOR_ADMIN p_crear = new CREAR_PROVEEDOR_ADMIN(this);
    p_crear.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
         int filaSeleccionada = tablaProveedores.getSelectedRow();
    if (filaSeleccionada >= 0) {
        modelo.removeRow(filaSeleccionada);
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Selecciona una fila para eliminar");
    }
    }//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JTable tablaProveedores;
    // End of variables declaration//GEN-END:variables
}
