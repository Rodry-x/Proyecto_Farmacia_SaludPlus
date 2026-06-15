package Metodos_de_pago;

import Pantallas_Inicio_Cajero.FilaCarrito;
import clases.Venta;
import clases.VentaDAO;
import javax.swing.JOptionPane;

public class VentanaPago extends javax.swing.JDialog {
    
    private double total;
    private String tipoPago;
    private int idUsuario;
    private int idCliente;
    private int idMetodo;
    private java.util.List<FilaCarrito> listaProductos;
    
    // Atributo del Patrón Strategy
    private EstrategiaPago estrategia;
    
    public VentanaPago(java.awt.Frame parent, boolean modal, double total, String tipoPago, int idUsuario, int idCliente, int idMetodo, java.util.List<FilaCarrito> productos) {
        super(parent, modal);
        initComponents();
        
        this.total = total;
        this.tipoPago = tipoPago;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idMetodo = idMetodo;
        this.listaProductos = productos;
        
        this.setTitle("Procesar Pago - " + tipoPago);
        
        // 1. Instanciamos la estrategia según el botón presionado
        inyectarEstrategia();
        
        // 2. Modificamos los textos e inputs visuales de la ventana
        configurarLayaoutSegunTipo();
    }

        private void inyectarEstrategia() {
        switch (this.tipoPago) {
            case "Efectivo":
                this.estrategia = new PagoEfectivo();
                break;
            case "Credito":
                this.estrategia = new PagoTarjeta("Tarjeta de Crédito");
                break;
            case "Debito":
                this.estrategia = new PagoTarjeta("Tarjeta de Débito");
                break;
            case "Billetera Digital":
                this.estrategia = new PagoBilletera();
                break;
        }
    }
        
        
    private void configurarLayaoutSegunTipo() {
        lblVuelto.setVisible(false);
        comboCuotas.setVisible(false);

        if (tipoPago.equals("Efectivo")) {
            lblInfo.setText("Monto recibido:");
            lblVuelto.setText("Vuelto: S/. 0.00");
            lblVuelto.setVisible(true);
        } else if (tipoPago.equals("Credito")) {
            lblInfo.setText("Nro de Operación:");
            comboCuotas.setVisible(true);
            comboCuotas.removeAllItems();
            comboCuotas.addItem("3 Cuotas");
            comboCuotas.addItem("6 Cuotas");
            comboCuotas.addItem("12 Cuotas");
        } else {
            lblInfo.setText("Nro de Operación / Celular:");
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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblInfo)
                        .addGap(18, 18, 18)
                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 67, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lblVuelto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblVuelto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmar)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // ============================================================
    // 🚀 1. SIMULACIÓN DEL INTEGRADO POS (PRIMERO GENERAMOS EL CÓDIGO)
    // Si el tipo de pago es tarjeta (Credito o Debito), se dispara el simulador
    if (tipoPago.equals("Credito") || tipoPago.equals("Debito")) {
        boolean posExitoso = simularConexionPOS();
        if (!posExitoso) return;
    }
    
    // Si es Billetera Digital -> Usa el nuevo simulador
    if (tipoPago.equals("Billetera Digital")) {
        boolean billeteraExitosa = simularConexionBilletera();
        if (!billeteraExitosa) return;
    }
    // ============================================================

    // 🎯 Leemos la caja de texto (que ya tendrá el celular o el nro de operación autocompletado)
    String entradaDato = txtEntrada.getText().trim();
    
    // Ejecución de la validación de la Estrategia activa
    if (estrategia != null && !estrategia.validar(entradaDato, this.total)) {
        String msg = tipoPago.equals("Efectivo") 
            ? "Monto recibido insuficiente para cubrir el total de S/. " + this.total
            : "Formato de operación o número telefónico no es válido.";
        JOptionPane.showMessageDialog(this, msg, "Validación Fallida", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Procesamiento en consola de la estrategia
    if (estrategia != null) {
        estrategia.procesarPago(this.total, entradaDato);
    }
    
    /// Generar identificador de boleta único e insertarlo a Azure
    String numeroVentaUnico = "V" + (System.currentTimeMillis() / 1000); 
    
    Venta nuevaVenta = new Venta(0, numeroVentaUnico, this.idUsuario, this.idCliente, this.idMetodo, new java.util.Date(), this.total);
    VentaDAO dao = new VentaDAO();
    
    boolean exito = dao.guardarVentaCompleta(nuevaVenta, this.listaProductos);
    
    if (exito) {
        JOptionPane.showMessageDialog(this, "¡Comprobante generado con éxito!\nCódigo de Venta: " + numeroVentaUnico, "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Error crítico: La base de datos denegó la inserción.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntradaActionPerformed
       if (!tipoPago.equals("Efectivo")) return;
        
        try {
            String texto = txtEntrada.getText().trim();
            if (texto.isEmpty()) {
                lblVuelto.setText("Vuelto: S/. 0.00");
                return;
            }
            double recibido = Double.parseDouble(texto);
            double vuelto = recibido - this.total;
            
            if (vuelto >= 0) {
                lblVuelto.setText("Vuelto: S/. " + String.format("%.2f", vuelto));
            } else {
                lblVuelto.setText("Falta: S/. " + String.format("%.2f", Math.abs(vuelto)));
            }
        } catch (NumberFormatException e) {
            lblVuelto.setText("Monto Inválido");
        }
    }//GEN-LAST:event_txtEntradaActionPerformed

        private boolean simularConexionPOS() {
            // ➡️ FORMATEAMOS EL TOTAL PARA QUE SIEMPRE MUESTRE 2 DECIMALES EN EL MENSAJE
    String totalFormateado = String.format("%.2f", this.total);
    // 1. Primer mensaje: Conectando y enviando monto
    JOptionPane.showMessageDialog(this, 
        "🔄 Conectando con el terminal POS...\nEnviando monto: S/. " + totalFormateado, 
        "POS Integrado - Farmacia", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // 2. Segundo mensaje: Lectura de tarjeta
    JOptionPane.showMessageDialog(this, 
        "💳 [POS] ¡Tarjeta detectada!\nProcesando transacción con el banco... Por favor, espere.", 
        "POS Integrado - Farmacia", 
        JOptionPane.WARNING_MESSAGE);
    
    // ➡️ GENERAMOS UN CÓDIGO DE OPERACIÓN ALEATORIO DE 6 DÍGITOS
    int codigoAleatorio = 100000 + (int)(Math.random() * 900000);
    String nroOperacionPOS = String.valueOf(codigoAleatorio);
    
    // 3. Tercer mensaje: Éxito mostrando el número generado
    JOptionPane.showMessageDialog(this, 
        "✅ ¡Transacción Aprobada!\nNúmero de Operación: " + nroOperacionPOS + "\nImprimiendo boucher de pago.", 
        "POS Integrado - Farmacia", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // 🚀 ¡MÁGIA! Ponemos el código generado automáticamente en la caja de texto de tu pantalla
    txtEntrada.setText(nroOperacionPOS);
    return true; 
}
        private boolean simularConexionBilletera() {
    // Formateamos el total a 2 decimales para el mensaje
    String totalFormateado = String.format("%.2f", this.total);

    // 1. Mensaje de espera de QR / Transferencia
    JOptionPane.showMessageDialog(this, 
        "📱 Esperando confirmación de pago...\nMonto a recibir: S/. " + totalFormateado + "\nSolicite al cliente escanear el QR.", 
        "Simulador Billetera Digital", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // 2. Mensaje de verificación en los servidores de Yape/Plin
    JOptionPane.showMessageDialog(this, 
        "🔄 Verificando transacción en la red bancaria...", 
        "Simulador Billetera Digital", 
        JOptionPane.WARNING_MESSAGE);
    
    // ➡️ SIMULACIÓN: Generamos un número de celular ficticio que empiece con 9 para cumplir la validación
    int restoCelular = 10000000 + (int)(Math.random() * 90000000);
    String celularSimulado = "9" + restoCelular;
    
    // 3. Mensaje de éxito
    JOptionPane.showMessageDialog(this, 
        "✅ ¡Pago Recibido con éxito!\nCelular origen: " + celularSimulado, 
        "Simulador Billetera Digital", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // 🚀 Autocompletamos la caja de texto con el celular simulado
    txtEntrada.setText(celularSimulado);
        
    return true; 
}
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> comboCuotas;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JTextField txtEntrada;
    // End of variables declaration//GEN-END:variables
}
