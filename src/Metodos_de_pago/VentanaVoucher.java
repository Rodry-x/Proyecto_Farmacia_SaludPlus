package Metodos_de_pago;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaVoucher extends javax.swing.JDialog {
    
    private String htmlContenido;
    private double total;

    // Recibe una lista de arreglos de strings con [cantidad, nombre, total]
    public VentanaVoucher(java.awt.Frame parent, boolean modal, java.util.List<String[]> listaProductos, double total) {
        super(parent, modal);
        this.total = total;
        this.setTitle("Vista Previa del Comprobante - SaludPlus");
        this.setSize(360, 580); 
        this.setLocationRelativeTo(parent);
        
        // 1. Construir el diseño del voucher
        construirHTML(listaProductos);
        
        // 2. Inicializar los componentes de la ventana
        initComponentesPersonalizados();
    }

    private void construirHTML(java.util.List<String[]> productos) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family:monospace; font-size:11px; margin:15px; background-color:#FFFFFF;'>");
        sb.append("<div style='text-align:center;'>");
        sb.append("<b style='font-size:14px;'>FARMACIA SALUDPLUS</b><br>");
        sb.append("RUC: 20123456789<br>");
        sb.append("Av. Larco 123 - Trujillo<br>");
        sb.append("------------------------------------------<br>");
        sb.append("<b>TICKET DE VENTA</b><br>");
        sb.append("------------------------------------------<br>");
        sb.append("</div>");
        
        sb.append("<b>Fecha:</b> ").append(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date())).append("<br>");
        sb.append("<b>Cajero:</b> Cajero de Turno<br>");
        sb.append("------------------------------------------<br>");
        
        // Encabezado de columnas
        sb.append("<table style='width:100%; font-family:monospace; font-size:11px;'>");
        sb.append("<tr>");
        sb.append("<th align='left' style='width:15%;'>CANT</th>");
        sb.append("<th align='left' style='width:55%;'>DESCRIPCIÓN</th>");
        sb.append("<th align='right' style='width:30%;'>TOTAL</th>");
        sb.append("</tr>");
        sb.append("<tr><td colspan='3'>------------------------------------------</td></tr>");
        
        // Recorremos la lista inyectando los datos reales
        if (productos != null && !productos.isEmpty()) {
            for (String[] prod : productos) {
                String cantidad = prod[0];
                String descripcion = prod[1];
                String precioTotal = prod[2];
                
                String nombreCorto = descripcion.length() > 16 ? descripcion.substring(0, 16) : descripcion;
                
                sb.append("<tr>");
                sb.append("<td align='left'>").append(cantidad).append("</td>");
                sb.append("<td align='left'>").append(nombreCorto).append("</td>");
                sb.append("<td align='right'>S/. ").append(precioTotal).append("</td>");
                sb.append("</tr>");
            }
        } else {
            sb.append("<tr><td colspan='3' align='center'>(No hay productos en la venta)</td></tr>");
        }
        
        sb.append("</table>");
        sb.append("------------------------------------------<br>");
        
        // Sección de Totales
        sb.append("<div style='text-align:right; font-size:12px;'>");
        sb.append("<b>TOTAL A PAGAR: S/. ").append(String.format("%.2f", total)).append("</b><br><br>");
        sb.append("</div>");
        
        sb.append("<div style='text-align:center; font-size:10px;'>");
        sb.append("¡Gracias por su preferencia!<br>");
        sb.append("Conserve su comprobante para cambios.<br>");
        sb.append("</div>");
        sb.append("</body></html>");
        
        this.htmlContenido = sb.toString();
    }

    private void initComponentesPersonalizados() {
        this.setLayout(new BorderLayout());

        JTextPane papelVoucher = new JTextPane();
        papelVoucher.setContentType("text/html");
        papelVoucher.setText(htmlContenido);
        papelVoucher.setEditable(false);
        papelVoucher.setBackground(Color.WHITE);
        papelVoucher.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scroll = new JScrollPane(papelVoucher);
        scroll.setBorder(BorderFactory.createEmptyBorder()); 
        this.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 12));
        panelBotones.setBackground(new Color(230, 235, 240)); 

        JButton btnPdf = new JButton("📥 Descargar PDF");
        JButton btnImprimir = new JButton("🖨️ Imprimir Ticket");
        
        btnPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnPdf.addActionListener(e -> exportarAPDF());
        btnImprimir.addActionListener(e -> mandarAImprimir(papelVoucher));

        panelBotones.add(btnPdf);
        panelBotones.add(btnImprimir);
        this.add(panelBotones, BorderLayout.SOUTH);
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
                    "Para generar el archivo digital (.pdf):\nSeleccione la opción 'Microsoft Print to PDF' en la siguiente lista.", 
                    "Exportación Nativa", JOptionPane.INFORMATION_MESSAGE);
                
                if (job.printDialog()) {
                    job.print();
                    JOptionPane.showMessageDialog(this, "¡Documento PDF exportado exitosamente!", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en el flujo de exportación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mandarAImprimir(JTextPane componente) {
        try {
            boolean completado = componente.print(null, null, true, null, null, true);
            if (completado) {
                JOptionPane.showMessageDialog(this, "El ticket fue enviado a la cola de impresión.", "Impresión Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de comunicación con el hardware de impresión: " + ex.getMessage(), "Error de Dispositivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}