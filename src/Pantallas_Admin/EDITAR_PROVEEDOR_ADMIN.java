package Pantallas_Admin;

import controller.ProveedorDAO;
import model.entidad_proveedor_inventario;
import javax.swing.JOptionPane;

public class EDITAR_PROVEEDOR_ADMIN extends javax.swing.JFrame {
    
    private PROVEEDORES_ADMIN tablaProveedores;
    private int idProveedorActual;
    
    public EDITAR_PROVEEDOR_ADMIN(PROVEEDORES_ADMIN tablaProveedores) {
        initComponents(); // Esto ya no dará error porque NetBeans lo generó automáticamente
        this.tablaProveedores = tablaProveedores;
        this.setLocationRelativeTo(null);
        
        // Ajustamos los textos para el modo edición
        btncrearP.setText("Guardar Cambios");
        jLabel1.setText("EDITAR PROVEEDOR");
    }
    
    public void cargarDatosFormulario(int id, String ruc, String nombre, String direccion, String telefonos, String correos) {
        this.idProveedorActual = id;
        txtRuc.setText(ruc);
        txtRuc.setEditable(false); // El RUC se bloquea para que no sea editado
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        
        txtTelefono.setText(telefonos.equals("Sin teléfono") ? "" : telefonos);
        txtGmail.setText(correos.equals("Sin correo") ? "" : correos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtGmail = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btncancelarP = new javax.swing.JButton();
        btncrearP = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(603, 70));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("EDITAR PROVEEDOR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 50, -1));

        jLabel5.setText("Teléfono(s) :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 70, -1));

        jLabel6.setText("Correo(s):");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 60, 20));

        jLabel7.setText("Dirección :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 60, -1));

        txtNombre.setPreferredSize(new java.awt.Dimension(300, 26));
        txtNombre.addActionListener(this::txtNombreActionPerformed);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 320, -1));

        txtTelefono.addActionListener(this::txtTelefonoActionPerformed);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, -1));
        jPanel2.add(txtGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 310, -1));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 308, -1));

        btncancelarP.setText("Cancelar");
        btncancelarP.addActionListener(this::btncancelarPActionPerformed);
        jPanel2.add(btncancelarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 90, -1));

        btncrearP.setText("Guardar proveedor");
        btncrearP.addActionListener(this::btncrearPActionPerformed);
        jPanel2.add(btncrearP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jLabel8.setText("RUC:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        txtRuc.addActionListener(this::txtRucActionPerformed);
        jPanel2.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        
        // Permitir SOLO números y la coma (,) como separador
        if (!Character.isDigit(c) && c != ',') {
            evt.consume(); // Bloquea cualquier otra tecla presionada
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btncancelarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarPActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarPActionPerformed

    private void btncrearPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearPActionPerformed
        String nombre = txtNombre.getText().trim();
    String telefono = txtTelefono.getText().trim();
    String direccion = txtDireccion.getText().trim();
    String gmail = txtGmail.getText().trim();

    if (nombre.isEmpty() || direccion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, completa el Nombre y la Dirección.");
        return;
    }

    entidad_proveedor_inventario provEditado = new entidad_proveedor_inventario();
    provEditado.setId_proveedor(idProveedorActual);
    provEditado.setNombre_proveedor(nombre);
    provEditado.setDireccion(direccion);

    if (!telefono.isEmpty()) {
        String[] listaTelfs = telefono.split(",");
        for (String t : listaTelfs) {
            if (!t.trim().isEmpty()) provEditado.getTelefonos().add(t.trim());
        }
    }

    if (!gmail.isEmpty()) {
        String[] listaCorreos = gmail.split(",");
        for (String c : listaCorreos) {
            if (!c.trim().isEmpty()) provEditado.getCorreos().add(c.trim());
        }
    }

    ProveedorDAO dao = new ProveedorDAO();
    if (dao.modificar(provEditado)) {
        JOptionPane.showMessageDialog(this, "¡Proveedor modificado con éxito en Azure SQL!");
        if (tablaProveedores != null) {
            tablaProveedores.listarProveedores();
        }
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "❌ Error al intentar guardar las modificaciones.");
    }
    }//GEN-LAST:event_btncrearPActionPerformed

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucActionPerformed
    
    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {                                
        char c = evt.getKeyChar();
        
        // 1. Permitir SOLO números
        if (!Character.isDigit(c)) {
            evt.consume(); // Bloquea letras, espacios o signos
            return;
        }
        
        // 2. Limitar a un máximo de 11 dígitos
        if (txtRuc.getText().length() >= 11) {
            evt.consume(); // Bloquea el ingreso si ya llegó a 11
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Solución al error de logger: Usamos System.err en su lugar
            System.err.println("Error al iniciar el Look and Feel: " + ex.getMessage());
        }
       
        java.awt.EventQueue.invokeLater(() -> {
            // Solución al constructor: Le pasamos 'null' para que el main no falle al compilar
            new EDITAR_PROVEEDOR_ADMIN(null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelarP;
    private javax.swing.JButton btncrearP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
