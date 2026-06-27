package Metodos_de_pago;

import model.ItemVenta;
import model.Venta;
import dao.VentaDAO;
import service.VentaService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPago extends javax.swing.JDialog {

    private static final Color AZUL_OSCURO = new Color(20, 70, 120);
    private static final Color FONDO_CLARO = new Color(245, 247, 250);
    private static final Color TEXTO_BLANCO = Color.WHITE;

    private double total;
    private String tipoPago;
    private int idUsuario;
    private int idCliente;
    private int idMetodo;
    private java.util.List<ItemVenta> listaProductos;

    private EstrategiaPago estrategia;

    private JComboBox<String> cmbBilletera;
    private boolean exitosa = false;
    private String numeroVenta = "";

    private JPanel headerPanel;
    private JLabel lblIconoTitulo;
    private JLabel lblTotalAmount;
    private JTextField txtEntrada;
    private JLabel lblInfo;
    private JLabel lblVuelto;
    private JComboBox<String> comboCuotas;
    private javax.swing.JButton btnConfirmar;

    public boolean isExitosa() {
        return exitosa;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public VentanaPago(java.awt.Frame parent, boolean modal, double total, String tipoPago,
                        int idUsuario, int idCliente, int idMetodo,
                        java.util.List<ItemVenta> productos) {
        super(parent, modal);
        this.total = total;
        this.tipoPago = tipoPago;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idMetodo = idMetodo;
        this.listaProductos = productos;

        initUI();
        setTitle("Procesar Pago - " + tipoPago);
        setResizable(false);

        inyectarEstrategia();
        configurarLayaoutSegunTipo();
    }

    private void initUI() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FONDO_CLARO);

        headerPanel = new JPanel();
        headerPanel.setBackground(util.Formateador.AZUL_PRINCIPAL);
        headerPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(15, 20, 10, 20);
        gbc.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;

        String icono = "\uD83D\uDCB3";
        if (tipoPago != null) {
            if (tipoPago.equals("Efectivo")) icono = "\uD83D\uDCB5";
            else if (tipoPago.equals("Billetera Digital")) icono = "\uD83D\uDCF1";
        }

        lblIconoTitulo = new JLabel(icono + " " + tipoPago);
        lblIconoTitulo.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        lblIconoTitulo.setForeground(TEXTO_BLANCO);
        headerPanel.add(lblIconoTitulo, gbc);

        gbc.insets = new java.awt.Insets(0, 20, 15, 20);
        lblTotalAmount = new JLabel(util.Formateador.precio(total));
        lblTotalAmount.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        lblTotalAmount.setForeground(TEXTO_BLANCO);
        headerPanel.add(lblTotalAmount, gbc);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(FONDO_CLARO);
        formPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc2 = new java.awt.GridBagConstraints();
        gbc2.insets = new java.awt.Insets(8, 25, 8, 25);
        gbc2.fill = java.awt.GridBagConstraints.HORIZONTAL;

        lblInfo = new JLabel(" ");
        lblInfo.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        lblInfo.setForeground(new Color(80, 80, 80));
        gbc2.gridwidth = 1;
        gbc2.weightx = 0;
        formPanel.add(lblInfo, gbc2);

        txtEntrada = new JTextField(12);
        txtEntrada.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        txtEntrada.setHorizontalAlignment(JTextField.CENTER);
        txtEntrada.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtEntrada.addActionListener(this::txtEntradaActionPerformed);
        gbc2.weightx = 1;
        gbc2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        formPanel.add(txtEntrada, gbc2);

        lblVuelto = new JLabel(" ");
        lblVuelto.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        lblVuelto.setHorizontalAlignment(SwingConstants.CENTER);
        lblVuelto.setForeground(new Color(40, 150, 60));
        gbc2.insets = new java.awt.Insets(2, 25, 2, 25);
        formPanel.add(lblVuelto, gbc2);

        comboCuotas = new JComboBox<>();
        comboCuotas.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        comboCuotas.setBackground(Color.WHITE);
        gbc2.insets = new java.awt.Insets(8, 25, 2, 25);
        formPanel.add(comboCuotas, gbc2);

        cmbBilletera = new JComboBox<>();
        cmbBilletera.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        cmbBilletera.setBackground(Color.WHITE);
        gbc2.insets = new java.awt.Insets(8, 25, 2, 25);
        formPanel.add(cmbBilletera, gbc2);

        Color colorBoton = new Color(46, 125, 50);
        Color colorHover = new Color(38, 105, 42);

        btnConfirmar = new javax.swing.JButton("CONFIRMAR PAGO");
        btnConfirmar.setOpaque(true);
        btnConfirmar.setContentAreaFilled(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        btnConfirmar.setForeground(TEXTO_BLANCO);
        btnConfirmar.setBackground(colorBoton);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        final Color hoverColor = colorHover;
        final Color normalColor = colorBoton;
        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setBackground(normalColor);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConfirmar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(5, 5, 5)
            .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnConfirmar)
            .addContainerGap(20, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(getParent());
    }

    private void inyectarEstrategia() {
        switch (this.tipoPago) {
            case "Efectivo":
                this.estrategia = new PagoEfectivo();
                break;
            case "Tarjeta de Credito":
                this.estrategia = new PagoTarjeta("Tarjeta de Cr\u00E9dito");
                break;
            case "Tarjeta de Debito":
                this.estrategia = new PagoTarjeta("Tarjeta de D\u00E9bito");
                break;
            case "Billetera Digital":
                this.estrategia = new PagoBilletera();
                break;
        }
    }

    private void configurarLayaoutSegunTipo() {
        comboCuotas.setVisible(false);
        cmbBilletera.setVisible(false);
        lblVuelto.setVisible(false);
        lblVuelto.setText(" ");
        txtEntrada.setText("");

        if (tipoPago.equals("Efectivo")) {
            lblInfo.setText("Monto recibido:");
            lblVuelto.setVisible(true);
            lblVuelto.setText("Vuelto: S/. 0.00");
            txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    actualizarLabelVuelto();
                }
            });
        } else if (tipoPago.equals("Tarjeta de Credito") || tipoPago.equals("Tarjeta de Debito")) {
            lblInfo.setText("N\u00FAmero de operaci\u00F3n:");
            comboCuotas.setVisible(true);
            comboCuotas.removeAllItems();
            comboCuotas.addItem("3 Cuotas");
            comboCuotas.addItem("6 Cuotas");
            comboCuotas.addItem("12 Cuotas");
        } else if (tipoPago.equals("Billetera Digital")) {
            lblInfo.setText("Seleccione plataforma:");
            cmbBilletera.setVisible(true);
            cmbBilletera.removeAllItems();
            cmbBilletera.addItem("Yape");
            cmbBilletera.addItem("Plin");
        } else {
            lblInfo.setText("N\u00FAmero de operaci\u00F3n:");
        }
    }

    private void actualizarLabelVuelto() {
        if (!tipoPago.equals("Efectivo")) return;
        String texto = txtEntrada.getText().trim();
        if (texto.isEmpty()) {
            lblVuelto.setText("Vuelto: S/. 0.00");
            lblVuelto.setForeground(new Color(40, 150, 60));
            btnConfirmar.setEnabled(false);
            return;
        }
        try {
            double recibido = Double.parseDouble(texto);
            double cambio = recibido - this.total;
            if (cambio >= 0) {
                lblVuelto.setText("Vuelto: " + util.Formateador.precio(cambio));
                lblVuelto.setForeground(new Color(40, 150, 60));
                btnConfirmar.setEnabled(true);
            } else {
                lblVuelto.setText("Falta: " + util.Formateador.precio(Math.abs(cambio)));
                lblVuelto.setForeground(new Color(200, 50, 50));
                btnConfirmar.setEnabled(false);
            }
        } catch (NumberFormatException e) {
            lblVuelto.setText("Monto inv\u00E1lido");
            lblVuelto.setForeground(new Color(200, 50, 50));
            btnConfirmar.setEnabled(false);
        }
    }

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tipoPago.equals("Tarjeta de Credito") || tipoPago.equals("Tarjeta de Debito")) {
            boolean posExitoso = simularConexionPOS();
            if (!posExitoso) return;
        }

        if (tipoPago.equals("Billetera Digital")) {
            boolean billeteraExitosa = simularConexionBilletera();
            if (!billeteraExitosa) return;
        }

        String entradaDato = txtEntrada.getText().trim();

        if (estrategia != null && !estrategia.validar(entradaDato, this.total)) {
            String msg = tipoPago.equals("Efectivo")
                ? "Monto recibido insuficiente para cubrir el total de S/. " + this.total
                : "Formato de operaci\u00F3n o n\u00FAmero telef\u00F3nico no es v\u00E1lido.";
            JOptionPane.showMessageDialog(this, msg, "Validaci\u00F3n Fallida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (estrategia != null) {
            estrategia.procesarPago(this.total, entradaDato);
        }

        VentaService.TotalesVenta totales = VentaService.calcularTotales(this.listaProductos);
        Venta nuevaVenta = VentaService.crearVenta(
            this.idCliente, this.idUsuario, this.idMetodo,
            totales.subtotal, totales.igv, totales.total);
        VentaDAO dao = new VentaDAO();

        int idVenta = dao.guardarVentaCompleta(nuevaVenta, this.listaProductos);

        if (idVenta > 0) {
            System.out.println("Venta registrada exitosamente. Total: " + util.Formateador.precio(totales.total));
            this.exitosa = true;
            JOptionPane.showMessageDialog(this,
                "\u00A1Comprobante generado con \u00E9xito!",
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);

            VentanaVoucher voucher = new VentanaVoucher(
                (java.awt.Frame) this.getParent(), true, idVenta
            );
            if (tipoPago.equals("Efectivo")) {
                try {
                    double montoRecibido = Double.parseDouble(entradaDato);
                    double cambio = montoRecibido - this.total;
                    voucher.setMontoRecibido(montoRecibido);
                    if (cambio > 0) {
                        voucher.setVuelto(cambio);
                    }
                } catch (NumberFormatException e) {
                    // ignorar
                }
            }
            voucher.setVisible(true);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Error cr\u00EDtico: La base de datos deneg\u00F3 la inserci\u00F3n.\n"
                + "Revise la consola para m\u00E1s detalles o verifique que haya stock disponible.",
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarLabelVuelto();
    }

    private boolean simularConexionPOS() {
        String totalFormateado = String.format("%.2f", this.total);
        JOptionPane.showMessageDialog(this,
            "\uD83D\uDD04 Conectando con el terminal POS...\nEnviando monto: S/. " + totalFormateado,
            "POS Integrado - Farmacia",
            JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this,
            "\uD83D\uDCB3 [POS] \u00A1Tarjeta detectada!\nProcesando transacci\u00F3n con el banco... Por favor, espere.",
            "POS Integrado - Farmacia",
            JOptionPane.WARNING_MESSAGE);

        int codigoAleatorio = 100000 + (int)(Math.random() * 900000);
        String nroOperacionPOS = String.valueOf(codigoAleatorio);

        JOptionPane.showMessageDialog(this,
            "\u2705 \u00A1Transacci\u00F3n Aprobada!\nN\u00FAmero de Operaci\u00F3n: " + nroOperacionPOS + "\nImprimiendo boucher de pago.",
            "POS Integrado - Farmacia",
            JOptionPane.INFORMATION_MESSAGE);

        txtEntrada.setText(nroOperacionPOS);
        return true;
    }

    private boolean simularConexionBilletera() {
        String totalFormateado = String.format("%.2f", this.total);

        JOptionPane.showMessageDialog(this,
            "\uD83D\uDCF1 Esperando confirmaci\u00F3n de pago...\nMonto a recibir: S/. " + totalFormateado + "\nSolicite al cliente escanear el QR.",
            "Simulador Billetera Digital",
            JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this,
            "\uD83D\uDD04 Verificando transacci\u00F3n en la red bancaria...",
            "Simulador Billetera Digital",
            JOptionPane.WARNING_MESSAGE);

        int restoCelular = 10000000 + (int)(Math.random() * 90000000);
        String celularSimulado = "9" + restoCelular;

        JOptionPane.showMessageDialog(this,
            "\u2705 \u00A1Pago Recibido con \u00E9xito!\nCelular origen: " + celularSimulado,
            "Simulador Billetera Digital",
            JOptionPane.INFORMATION_MESSAGE);

        txtEntrada.setText(celularSimulado);
        return true;
    }
}
