
package Pantallas_Inicio_Cajero;

import clases.ClienteDAO;
import clases.Cliente;

public class VentanaRegistrarCliente extends javax.swing.JDialog {
    
    private PantallaCajero pantallaPadre;

    public VentanaRegistrarCliente(PantallaCajero parent, boolean modal, String dniInicial) {
        super(parent, modal);
        initComponents();
        this.pantallaPadre = parent; // Guardamos la referencia
        
        // Solo ponemos el texto si realmente recibimos un número
        if (dniInicial != null && !dniInicial.isEmpty()) {
        txtDniRuc.setText(dniInicial);
        }
    
       // Opcional: Esto ayuda a detectar el tipo de documento automáticamente
       if (dniInicial != null && dniInicial.length() == 11) {
        cmbTipoDocumento.setSelectedItem("RUC");
       } else {
        cmbTipoDocumento.setSelectedItem("DNI");
       }
        
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtDniRuc = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cmbTipoDocumento = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("Registrar Cliente");

        jLabel3.setText("Nombres :");

        jLabel4.setText("Telefono :");

        jLabel5.setText("Apellidos");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        txtNombre.addActionListener(this::txtNombreActionPerformed);

        cmbTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "RUC", " " }));
        cmbTipoDocumento.addActionListener(this::cmbTipoDocumentoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnCancelar)
                                .addGap(55, 55, 55)
                                .addComponent(btnGuardar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDniRuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel1)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    String tipoDoc = cmbTipoDocumento.getSelectedItem().toString(); // "DNI" o "RUC"
    String valorDoc = txtDniRuc.getText().trim();
    String nombres = txtNombre.getText().trim();
    String apellidos = txtApellidos.getText().trim();
    String telefono = txtTelefono.getText().trim();

    // 2. Validación de campos obligatorios
    if (valorDoc.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Validación de Documento (DNI 8 / RUC 11)
    if (tipoDoc.equals("DNI") && valorDoc.length() != 8) {
        javax.swing.JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (tipoDoc.equals("RUC") && valorDoc.length() != 11) {
        javax.swing.JOptionPane.showMessageDialog(this, "El RUC debe tener exactamente 11 dígitos.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 4. Validación de Nombres y Apellidos (Solo letras)
    if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Los nombres solo deben contener letras.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Los apellidos solo deben contener letras.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 5. Validación de Teléfono (Exactamente 9 dígitos)
    if (telefono.length() != 9 || !telefono.matches("\\d+")) {
        javax.swing.JOptionPane.showMessageDialog(this, "El teléfono debe tener exactamente 9 dígitos numéricos.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 6. Preparar variables para el objeto Cliente
    String dni = tipoDoc.equals("DNI") ? valorDoc : "";
    String ruc = tipoDoc.equals("RUC") ? valorDoc : "";

    // 7. POO: Instanciamos cliente y DAO
    Cliente nuevoCliente = new Cliente(0, dni, ruc, nombres, apellidos, telefono);
    ClienteDAO controlador = new ClienteDAO();

    btnGuardar.setEnabled(false); // Evita doble envío

    // Hilo asíncrono
    new Thread(() -> {
        try {
            boolean exito = controlador.insertarCliente(nuevoCliente);
            
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnGuardar.setEnabled(true);
                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(this, "¡Cliente registrado con éxito!");
                    if (pantallaPadre != null) {
                        pantallaPadre.actualizarInterfazClienteRegistrado(nombres + " " + apellidos);
                    }
                    this.dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar en base de datos.");
                }
            });
        } catch (Exception e) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnGuardar.setEnabled(true);
                javax.swing.JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage());
            });
        }
    }).start();
 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose(); // Si presiona cancelar, la ventana simplemente se destruye de la memoria
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoActionPerformed
        txtDniRuc.setText(""); 
        txtDniRuc.requestFocus();
    }//GEN-LAST:event_cmbTipoDocumentoActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTipoDocumento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDniRuc;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
