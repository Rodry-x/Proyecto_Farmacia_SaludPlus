
package Metodos_de_pago;

import Pantallas_Inicio_Cajero.FilaCarrito;
import clases.Venta;
import clases.VentaDAO;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class VentanaPago extends javax.swing.JDialog {
    
    private String tipoPago;
    private double total;
    private int idUsuario;
    private int idCliente;
    private int idMetodo;
    private java.util.List<FilaCarrito> listaProductos;
    
    public VentanaPago(java.awt.Frame parent, boolean modal, double total, String tipoPago, int idUsuario, int idCliente, int idMetodo, java.util.List<FilaCarrito> productos) {
        super(parent, modal);
        initComponents();
        this.setTitle("Procesar pago: " + tipoPago);
        this.total = total;
        this.tipoPago = tipoPago;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idMetodo = idMetodo;
        this.listaProductos = productos;
        configurarSegunTipo();
    }

    private void configurarSegunTipo() {
    // Esto oculta lo que no necesitamos al abrir la ventana
    lblVuelto.setVisible(false);
    comboCuotas.setVisible(false);

    if (tipoPago.equals("Efectivo")) {
        lblInfo.setText("Monto recibido:");
        lblVuelto.setText("Vuelto: S/. 0.00");
        lblVuelto.setVisible(true);
    } else if (tipoPago.equals("Credito")) {
        lblInfo.setText("Nro de Operación:");
        comboCuotas.setVisible(true);
        // Cargar cuotas en el combo
        comboCuotas.removeAllItems();
        comboCuotas.addItem("3 Cuotas");
        comboCuotas.addItem("6 Cuotas");
        comboCuotas.addItem("12 Cuotas");
    } else {
        lblInfo.setText("Nro de Operación/Celular:");
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEntrada = new javax.swing.JTextField();
        lblInfo = new javax.swing.JLabel();
        lblVuelto = new javax.swing.JLabel();
        comboCuotas = new javax.swing.JComboBox<>();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtEntrada.addActionListener(this::txtEntradaActionPerformed);

        lblInfo.setText("INGRESA");

        lblVuelto.setText("jLabel2");

        comboCuotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblInfo)
                        .addGap(18, 18, 18)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(lblVuelto)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfo))
                .addGap(18, 18, 18)
                .addComponent(lblVuelto)
                .addGap(40, 40, 40)
                .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnConfirmar)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
System.out.println("DEBUG: VentanaPago recibiendo ID Cliente: " + this.idCliente);

    if (this.idCliente <= 0) { 
        JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente válido.");
        return; 
    }
    
    // --- MODIFICACIÓN AQUÍ ---
    // Generamos un número de venta único usando el tiempo actual
    String numeroVentaUnico = "V" + System.currentTimeMillis(); 
    
    // Si tu base de datos tiene un límite de caracteres para el código (ej. VARCHAR(20)), 
    // asegúrate de tomar solo una parte:
    if (numeroVentaUnico.length() > 20) {
        numeroVentaUnico = numeroVentaUnico.substring(0, 20);
    }
    // -------------------------
    
    // Usamos el número único en lugar de "V00001"
    Venta nuevaVenta = new Venta(0, numeroVentaUnico, this.idUsuario, this.idCliente, this.idMetodo, new java.util.Date(), this.total);
    VentaDAO dao = new VentaDAO();
    
    boolean guardado = dao.guardarVentaCompleta(nuevaVenta, this.listaProductos);
    
    if (guardado) {
        JOptionPane.showMessageDialog(this, "Venta exitosa. Código: " + numeroVentaUnico);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Error al guardar. Revisa la consola.");
    }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntradaActionPerformed
       try {
        String texto = txtEntrada.getText();
        if (texto.isEmpty()) {
            lblVuelto.setText("Vuelto: S/. 0.00");
            return;
        }
        
        double montoRecibido = Double.parseDouble(texto);
        double vuelto = montoRecibido - this.total;
        
        if (vuelto >= 0) {
            lblVuelto.setText("Vuelto: S/. " + String.format("%.2f", vuelto));
        } else {
            lblVuelto.setText("Falta: S/. " + String.format("%.2f", Math.abs(vuelto)));
        }
    } catch (NumberFormatException e) {
        lblVuelto.setText("Error en monto");
    }
    }//GEN-LAST:event_txtEntradaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> comboCuotas;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JTextField txtEntrada;
    // End of variables declaration//GEN-END:variables
}
