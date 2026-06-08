package Pantallas_Admin;

public class CREAR_PROVEEDOR_ADMIN extends javax.swing.JFrame {
    
        private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CREAR_PROVEEDOR_ADMIN.class.getName());
        private PROVEEDORES_ADMIN tablaProveedores;
        
        public CREAR_PROVEEDOR_ADMIN(PROVEEDORES_ADMIN tablaProveedores) {
           
        initComponents();
        this.tablaProveedores = tablaProveedores;
        
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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

        jLabel2.setText("PORFAVOR COMPLETA LOS DATOS");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(603, 70));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("NUEVO PROVEEDOR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel4.setText("Completa los datos :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 710, 84));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombres completos :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 308, -1));

        jLabel5.setText("Teléfono :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 308, -1));

        jLabel6.setText("Email :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 199, -1));

        jLabel7.setText("Dirección :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 308, -1));

        txtNombre.setPreferredSize(new java.awt.Dimension(300, 26));
        txtNombre.addActionListener(this::txtNombreActionPerformed);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 308, -1));

        txtTelefono.addActionListener(this::txtTelefonoActionPerformed);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 308, -1));
        jPanel2.add(txtGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 310, -1));
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 308, -1));

        btncancelarP.setText("Cancelar");
        btncancelarP.addActionListener(this::btncancelarPActionPerformed);
        jPanel2.add(btncancelarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 120, -1));

        btncrearP.setText("Crear proveedor");
        btncrearP.addActionListener(this::btncrearPActionPerformed);
        jPanel2.add(btncrearP, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
char c = evt.getKeyChar();
    // Solo números
    if (!Character.isDigit(c)) {
        evt.consume();
    }
    // Máximo 9 dígitos
    if (txtTelefono.getText().length() >= 9) {
        evt.consume();
    }
    }//GEN-LAST:event_txtTelefonoKeyTyped
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
char c = evt.getKeyChar();

    // Permitir solo letras y espacio
    if (!Character.isLetter(c) && c != ' ') {
        evt.consume();
       }   
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btncrearPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearPActionPerformed
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String gmail = txtGmail.getText();

        // 🔥 enviar a la tabla
        tablaProveedores.agregarProveedor(nombre, telefono, direccion, gmail);

        // opcional: limpiar campos
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtGmail.setText("");

        this.dispose(); // cerrar ventana
    }//GEN-LAST:event_btncrearPActionPerformed

    private void btncancelarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarPActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarPActionPerformed

    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
       
java.awt.EventQueue.invokeLater(() -> 
    new CREAR_PROVEEDOR_ADMIN(null).setVisible(true)
);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelarP;
    private javax.swing.JButton btncrearP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
