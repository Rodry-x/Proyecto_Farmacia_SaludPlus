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

        jLabel2.setText("PORFAVOR COMPLETA LOS DATOS");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(603, 70));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("NUEVO PROVEEDOR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombres:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, -1));

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
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 308, -1));

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

        btncrearP.setText("Crear proveedor");
        btncrearP.addActionListener(this::btncrearPActionPerformed);
        jPanel2.add(btncrearP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jLabel8.setText("RUC:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        txtRuc.addActionListener(this::txtRucActionPerformed);
        jPanel2.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 170, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
   
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btncrearPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearPActionPerformed
    String ruc = txtRuc.getText().trim();
    String nombre = txtNombre.getText().trim();
    String telefono = txtTelefono.getText().trim();
    String direccion = txtDireccion.getText().trim();
    String gmail = txtGmail.getText().trim();

    // 1. Validaciones indispensables para Azure SQL
    if (ruc.length() != 11) {
        javax.swing.JOptionPane.showMessageDialog(this, "El RUC debe tener exactamente 11 dígitos numéricos.");
        return;
    }
    if (nombre.isEmpty() || direccion.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, completa el Nombre y la Dirección.");
        return;
    }

    // 2. Empaquetar los datos en el objeto entidad
    model.entidad_proveedor_inventario nuevoProv = new model.entidad_proveedor_inventario();
    nuevoProv.setRuc(ruc);
    nuevoProv.setNombre_proveedor(nombre);
    nuevoProv.setDireccion(direccion);

    // 3. Convertir el texto de teléfonos en una lista (soporta comas por si ponen varios)
    if (!telefono.isEmpty()) {
        String[] listaTelfs = telefono.split(",");
        for (String t : listaTelfs) {
            if (!t.trim().isEmpty()) {
                nuevoProv.getTelefonos().add(t.trim());
            }
        }
    }

    // 4. Convertir el texto de emails en una lista
    if (!gmail.isEmpty()) {
        String[] listaCorreos = gmail.split(",");
        for (String c : listaCorreos) {
            if (!c.trim().isEmpty()) {
                nuevoProv.getCorreos().add(c.trim());
            }
        }
    }

    // 5. Enviar de manera segura a la base de datos mediante el DAO
    controller.ProveedorDAO dao = new controller.ProveedorDAO();
    if (dao.insertar(nuevoProv)) {
        javax.swing.JOptionPane.showMessageDialog(this, "¡Proveedor registrado con éxito en Azure SQL!");
        
        // 6. Sincronizar y actualizar inmediatamente el JTable de la pantalla anterior
        if (tablaProveedores != null) {
            tablaProveedores.listarProveedores();
        }
        
        this.dispose(); // Cerrar esta ventana
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "❌ Error: No se pudo registrar. Verifica si el RUC ya existe.");
    }

    }//GEN-LAST:event_btncrearPActionPerformed

    private void btncancelarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarPActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarPActionPerformed

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
