package Metodos_de_pago;

import dao.VentaDAO;
import java.awt.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;

public class VentanaVoucher extends javax.swing.JDialog {

    private static final Color AZUL_OSCURO = new Color(20, 70, 120);

    private String htmlContenido;
    private double total;
    private double subtotal = 0.0;
    private double igv = 0.0;
    private double vuelto = 0.0;
    private double montoRecibido = 0.0;
    private String numeroVenta = "";
    private String metodoPago = "";
    private String nombreCliente = "";
    private String nombreCajero = "Cajero de Turno";
    private String fecha = "";
    private java.util.List<String[]> listaProductos;

    public void setVuelto(double vuelto) { this.vuelto = vuelto; }
    public void setMontoRecibido(double montoRecibido) { this.montoRecibido = montoRecibido; }
    public void setNumeroVenta(String numeroVenta) { this.numeroVenta = numeroVenta; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public void setIgv(double igv) { this.igv = igv; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setNombreCajero(String nombreCajero) { this.nombreCajero = nombreCajero; }

    public VentanaVoucher(java.awt.Frame parent, boolean modal, int idVenta) {
        super(parent, modal);
        setTitle("Comprobante - SaludPlus");
        setSize(380, 620);
        setLocationRelativeTo(parent);

        cargarDatosDesdeBD(idVenta);
        construirHTML();
        initComponentesPersonalizados();
    }

    public VentanaVoucher(java.awt.Frame parent, boolean modal, java.util.List<String[]> listaProductos, double total) {
        super(parent, modal);
        this.total = total;
        this.listaProductos = listaProductos;
        setTitle("Comprobante - SaludPlus");
        setSize(380, 620);
        setLocationRelativeTo(parent);

        construirHTML();
        initComponentesPersonalizados();
    }

    private void cargarDatosDesdeBD(int idVenta) {
        VentaDAO dao = new VentaDAO();
        Connection con = database.ConectarBaseDatos.conectar();
        if (con == null) return;

        String sqlVenta = "SELECT v.*, u.nombre + ' ' + u.apellido AS cajero, "
                        + "c.nombre + ' ' + c.apellido AS cliente, mp.nombre AS metodo "
                        + "FROM VENTA v "
                        + "JOIN USUARIOS u ON v.id_usuario = u.id_usuario "
                        + "JOIN CLIENTES c ON v.id_cliente = c.id_cliente "
                        + "JOIN METODO_PAGO mp ON v.id_metodopago = mp.id_metodopago "
                        + "WHERE v.id_venta = ?";
        try (PreparedStatement ps = con.prepareStatement(sqlVenta)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    this.numeroVenta = "V" + String.format("%05d", idVenta);
                    this.subtotal = rs.getDouble("subtotal");
                    this.igv = rs.getDouble("igv_total");
                    this.total = rs.getDouble("total_pagar");
                    this.nombreCliente = rs.getString("cliente");
                    this.nombreCajero = rs.getString("cajero");
                    this.metodoPago = rs.getString("metodo");
                    java.sql.Timestamp ts = rs.getTimestamp("fecha");
                    if (ts != null) {
                        this.fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(ts.getTime()));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar datos de venta: " + e.getMessage());
        }

        this.listaProductos = dao.obtenerDetalleVenta(idVenta);

        try { if (con != null) con.close(); } catch (SQLException e) { }
    }

    private void construirHTML() {
        double calcSubtotal = this.subtotal > 0 ? this.subtotal : total / 1.18;
        double calcIgv = this.igv > 0 ? this.igv : total - calcSubtotal;

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family:monospace; font-size:11px; margin:0; background-color:#F5F7FA;'>");

        sb.append("<div style='background-color:#1F5E9D; color:white; text-align:center; padding:14px 10px 10px 10px; margin-bottom:10px;'>");
        sb.append("<span style='font-size:18px; font-weight:bold;'>FARMACIA SALUDPLUS</span><br>");
        sb.append("<span style='font-size:10px; opacity:0.9;'>RUC: 20123456789 | Av. Larco 123 - Trujillo<br>Tel: (044) 123456</span>");
        sb.append("</div>");

        sb.append("<div style='text-align:center; margin:6px 0 8px 0;'>");
        sb.append("<span style='font-size:13px; font-weight:bold; color:#333; letter-spacing:1px;'>COMPROBANTE DE PAGO</span>");
        sb.append("</div>");

        sb.append("<div style='margin:0 14px 6px 14px; padding:8px 10px; background-color:white; border-radius:4px; border:1px solid #E0E0E0;'>");
        sb.append("<table style='width:100%; font-size:10px;'>");
        if (!numeroVenta.isEmpty()) {
            sb.append("<tr><td style='width:35%; color:#555;'><b>N\u00BA Venta:</b></td><td style='font-weight:bold;'>").append(numeroVenta).append("</td></tr>");
        }
        sb.append("<tr><td style='color:#555;'><b>Fecha:</b></td><td>").append(fecha.isEmpty() ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()) : fecha).append("</td></tr>");
        sb.append("<tr><td style='color:#555;'><b>Cajero:</b></td><td>").append(nombreCajero).append("</td></tr>");
        if (!nombreCliente.isEmpty()) {
            sb.append("<tr><td style='color:#555;'><b>Cliente:</b></td><td>").append(nombreCliente).append("</td></tr>");
        }
        if (!metodoPago.isEmpty()) {
            sb.append("<tr><td style='color:#555;'><b>Pago:</b></td><td>").append(metodoPago).append("</td></tr>");
        }
        sb.append("</table>");
        sb.append("</div>");

        sb.append("<div style='margin:0 14px 6px 14px; background-color:white; border-radius:4px; border:1px solid #E0E0E0; overflow:hidden;'>");
        sb.append("<table style='width:100%; font-size:10px; border-collapse:collapse;'>");
        sb.append("<tr style='background-color:#1F5E9D; color:white;'>");
        sb.append("<th align='center' style='width:12%; padding:4px 2px;'>CANT</th>");
        sb.append("<th align='left' style='width:48%; padding:4px 2px;'>DESCRIPCI\u00D3N</th>");
        sb.append("<th align='right' style='width:20%; padding:4px 2px;'>P.UNIT</th>");
        sb.append("<th align='right' style='width:20%; padding:4px 2px;'>TOTAL</th>");
        sb.append("</tr>");

        if (this.listaProductos != null && !this.listaProductos.isEmpty()) {
            boolean alt = false;
            for (String[] prod : this.listaProductos) {
                String cantidad = prod[0];
                String descripcion = prod[1];
                double precioUnit = Double.parseDouble(prod[2]);
                String precioTotal = prod[3];

                String nombreFila = descripcion.length() > 20 ? descripcion.substring(0, 20) : descripcion;
                String bgColor = alt ? "#F9F9F9" : "white";
                alt = !alt;

                sb.append("<tr style='background-color:").append(bgColor).append(";'>");
                sb.append("<td align='center' style='padding:3px 2px;'>").append(cantidad).append("</td>");
                sb.append("<td align='left' style='padding:3px 2px;'>").append(nombreFila).append("</td>");
                sb.append("<td align='right' style='padding:3px 2px;'>").append(util.Formateador.precio(precioUnit)).append("</td>");
                sb.append("<td align='right' style='padding:3px 2px;'>S/. ").append(precioTotal).append("</td>");
                sb.append("</tr>");
            }
        } else {
            sb.append("<tr><td colspan='4' align='center' style='padding:8px 0; color:#999;'>(Sin productos)</td></tr>");
        }
        sb.append("</table>");
        sb.append("</div>");

        sb.append("<div style='margin:0 14px 6px 14px; padding:8px 12px; background-color:white; border-radius:4px; border:1px solid #E0E0E0;'>");
        sb.append("<div style='text-align:right; font-size:11px;'>");
        sb.append("<span style='color:#555;'>Subtotal:</span> <b>").append(util.Formateador.precio(calcSubtotal)).append("</b><br>");
        sb.append("<span style='color:#555;'>IGV:</span> <b>").append(util.Formateador.precio(calcIgv)).append("</b><br>");
        sb.append("<div style='border-top:1px solid #333; margin:4px 0;'></div>");
        sb.append("<span style='font-size:14px; color:#1F5E9D;'>TOTAL: <b>").append(util.Formateador.precio(total)).append("</b></span>");
        sb.append("</div>");
        sb.append("</div>");

        if (montoRecibido > 0) {
            sb.append("<div style='margin:0 14px 6px 14px; padding:8px 12px; background-color:white; border-radius:4px; border:1px solid #E0E0E0;'>");
            sb.append("<div style='text-align:right; font-size:11px;'>");
            sb.append("<span style='color:#555;'>Monto recibido:</span> <b>").append(util.Formateador.precio(montoRecibido)).append("</b><br>");
            if (vuelto > 0) {
                sb.append("<span style='color:#555;'>Vuelto:</span> <b style='color:#2E7D32;'>").append(util.Formateador.precio(vuelto)).append("</b><br>");
            }
            sb.append("</div>");
            sb.append("</div>");
        }

        sb.append("<div style='text-align:center; font-size:10px; color:#888; margin:10px 14px;'>");
        sb.append("\u00A1Gracias por su preferencia!<br>");
        sb.append("Conserve su comprobante para cambios.<br>");
        sb.append("<span style='font-size:9px; color:#1F5E9D; font-weight:bold;'>SALUDPLUS - Cuidando tu bienestar</span>");
        sb.append("</div>");

        sb.append("</body></html>");

        this.htmlContenido = sb.toString();
    }

    private void initComponentesPersonalizados() {
        setLayout(new BorderLayout());

        JTextPane papelVoucher = new JTextPane();
        papelVoucher.setContentType("text/html");
        papelVoucher.setText(htmlContenido);
        papelVoucher.setEditable(false);
        papelVoucher.setBackground(new Color(245, 247, 250));
        papelVoucher.setMargin(new Insets(0, 0, 0, 0));

        JScrollPane scroll = new JScrollPane(papelVoucher);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(230, 235, 240));
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));

        JButton btnPdf = crearBotonStilizado("Descargar PDF", new Color(46, 125, 50));
        JButton btnImprimir = crearBotonStilizado("Imprimir Ticket", util.Formateador.AZUL_PRINCIPAL);

        btnPdf.addActionListener(e -> exportarAPDF());
        btnImprimir.addActionListener(e -> mandarAImprimir(papelVoucher));

        panelBotones.add(btnPdf);
        panelBotones.add(btnImprimir);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBotonStilizado(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.darker());
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });
        return btn;
    }

    private void exportarAPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Voucher como PDF");
        fileChooser.setSelectedFile(new File("Voucher_SaludPlus.pdf"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
                job.setJobName("Voucher_PDF_SaludPlus");

                JOptionPane.showMessageDialog(this,
                    "Para generar el archivo PDF:\nSeleccione 'Microsoft Print to PDF' como impresora.",
                    "Exportar PDF", JOptionPane.INFORMATION_MESSAGE);

                if (job.printDialog()) {
                    job.print();
                    JOptionPane.showMessageDialog(this,
                        "PDF exportado exitosamente.",
                        "Operaci\u00F3n Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al exportar PDF: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mandarAImprimir(JTextPane componente) {
        try {
            boolean completado = componente.print(null, null, true, null, null, true);
            if (completado) {
                JOptionPane.showMessageDialog(this,
                    "Ticket enviado a la cola de impresi\u00F3n.",
                    "Impresi\u00F3n Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error de impresi\u00F3n: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
