
package Pantallas_Inicio_Cajero;

import clases.ClienteDAO;
import clases.Cliente;

public class VentanaRegistrarCliente extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaRegistrarCliente.class.getName());
    private PantallaCajero pantallaPadre;

    public VentanaRegistrarCliente(PantallaCajero parent, boolean modal, String dniInicial) {
        super(parent, modal);
        initComponents();
        this.pantallaPadre = parent; // Guardamos la referencia
        setTitle("Registrar Nuevo Cliente");
        
        // Coloca el DNI automáticamente en el campo de texto y lo bloquea
        txtDniRuc.setText(dniInicial);
        txtDniRuc.setEditable(false);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtDniRuc = new javax.swing.JTextField();
        txtNombreCompleto = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("Registrar Cliente");

        jLabel2.setText("DNI :");

        jLabel3.setText("Nombres :");

        jLabel4.setText("Telefono :");

        jLabel5.setText("Correo :");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        txtNombreCompleto.addActionListener(this::txtNombreCompletoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnCancelar)
                                .addGap(55, 55, 55)
                                .addComponent(btnGuardar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(61, 61, 61)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDniRuc)
                                        .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel1)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDniRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
     // 1. Captura de datos y limpieza de espacios con .trim()
        String dni = txtDniRuc.getText().trim();
        String nombre = txtNombreCompleto.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        // 2. VALIDACIONES DE INTEGRIDAD
        if (nombre.isEmpty() || telefono.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nombre y teléfono son obligatorios.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!telefono.matches("\\d{7,9}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El teléfono debe tener entre 7 y 9 números.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!correo.isEmpty()) {
            String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
            if (!correo.matches(regexCorreo)) {
                javax.swing.JOptionPane.showMessageDialog(this, "El formato del correo electrónico es inválido.\nEjemplo correcto: usuario@correo.com", "Formato de Correo Incorrecto", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // 3. POO EN ACCIÓN: Instanciamos el objeto y llamamos al controlador
        Cliente nuevoCliente = new Cliente(dni, nombre, telefono, correo);
        ClienteDAO controlador = new ClienteDAO();

        btnGuardar.setEnabled(false); // Evita doble envío

        // Hilo asíncrono para mantener la interfaz fluida hacia Azure
        new Thread(() -> {
            try {
                boolean exito = controlador.insertarCliente(nuevoCliente);
                
                java.awt.EventQueue.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    if (exito) {
                        javax.swing.JOptionPane.showMessageDialog(this, "¡Cliente registrado con éxito!", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        
                        // 3. SE PONE AQUÍ: Notificar e inyectar el nombre del cliente directamente a la interfaz principal
                        if (pantallaPadre != null) {
                            pantallaPadre.actualizarInterfazClienteRegistrado(nombre);
                        }
                        
                        this.dispose(); // Cierra el JDialog
                    }
                });
            } catch (java.sql.SQLException e) {
                System.out.println("❌ Error: " + e.getMessage());
                java.awt.EventQueue.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    javax.swing.JOptionPane.showMessageDialog(this, "Error en Base de Datos: " + e.getMessage(), "Error Crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose(); // Si presiona cancelar, la ventana simplemente se destruye de la memoria
    }//GEN-LAST:event_btnCancelarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDniRuc;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
