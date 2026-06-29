
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pantallas_Admin;
 
import clases.CompraDao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
 
public class PanelCompras extends javax.swing.JPanel {
 
    // ── Atributos ────────────────────────────────────────────────
    private CompraDao dao = new CompraDao();
    private int paginaActual = 1;
    private final int FILAS_POR_PAGINA = 8;
    private DefaultTableModel modeloCompleto;
 
    // ── Constructor ──────────────────────────────────────────────
    public PanelCompras() {
    initComponents();
    tablaCompras.setRowHeight(35);
    TablaDetalleCompra.setRowHeight(30);
    configurarEstiloTablas();
    configurarBotones();

    new javax.swing.SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            javax.swing.JTable temp = new javax.swing.JTable();
            dao.listarCompras(temp);
            modeloCompleto = (DefaultTableModel) temp.getModel();
            return null;
        }
        @Override
        protected void done() {
            paginaActual = 1;
            mostrarPagina();
            ((DefaultTableModel) TablaDetalleCompra.getModel()).setRowCount(0);
            jLabel13.setText("-");
            jLabel14.setText("ESTADO ACTUAL:");
            jLabel14.setForeground(new Color(26, 35, 126));
            jLabel10.setText("Total Productos: 0");
        }
    }.execute();
}
    private void configurarEstiloTablas() {
    tablaCompras.getTableHeader().setBackground(new Color(26, 35, 126));
    tablaCompras.getTableHeader().setForeground(Color.WHITE);
    tablaCompras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
    tablaCompras.setSelectionBackground(new Color(232, 240, 254));
    tablaCompras.setSelectionForeground(Color.DARK_GRAY);
    tablaCompras.setGridColor(new Color(225, 225, 225));
    tablaCompras.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    TablaDetalleCompra.getTableHeader().setBackground(new Color(240, 242, 255));
    TablaDetalleCompra.getTableHeader().setForeground(new Color(26, 35, 126));
    TablaDetalleCompra.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    TablaDetalleCompra.setGridColor(new Color(225, 225, 225));
    TablaDetalleCompra.setFont(new Font("Segoe UI", Font.PLAIN, 12));
}

private void cargarTabla() {
    new javax.swing.SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            javax.swing.JTable temp = new javax.swing.JTable();
            dao.listarCompras(temp);
            modeloCompleto = (DefaultTableModel) temp.getModel();
            return null;
        }
        @Override
        protected void done() {
            paginaActual = 1;
            mostrarPagina();
            ((DefaultTableModel) TablaDetalleCompra.getModel()).setRowCount(0);
            jLabel13.setText("-");
            jLabel14.setText("ESTADO ACTUAL:");
            jLabel14.setForeground(new Color(26, 35, 126));
            jLabel10.setText("Total Productos: 0");
        }
    }.execute();
}
private void mostrarPagina() {
    DefaultTableModel modeloPagina = new DefaultTableModel(
        new String[]{"N°COMPRA", "FECHA", "PROVEEDOR", "TOTAL", "ESTADO", "USUARIO"}, 0
    ) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    int total = modeloCompleto.getRowCount();
    int totalPaginas = Math.max(1, (int) Math.ceil((double) total / FILAS_POR_PAGINA));
    int inicio = (paginaActual - 1) * FILAS_POR_PAGINA;
    int fin = Math.min(inicio + FILAS_POR_PAGINA, total);
    for (int i = inicio; i < fin; i++) {
        Object[] fila = new Object[modeloCompleto.getColumnCount()];
        for (int j = 0; j < fila.length; j++) fila[j] = modeloCompleto.getValueAt(i, j);
        modeloPagina.addRow(fila);
    }
    
    
    tablaCompras.setModel(modeloPagina);
    configurarEstiloTablas();
  tablaCompras.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setOpaque(true);
        String estado = value != null ? value.toString() : "";
        if (estado.equals("PENDIENTE")) {
            lbl.setBackground(isSelected ? new Color(255, 220, 100) : new Color(255, 243, 205));
            lbl.setForeground(new Color(180, 100, 0));
        } else if (estado.equals("RECIBIDO")) {
            lbl.setBackground(isSelected ? new Color(150, 210, 160) : new Color(212, 237, 218));
            lbl.setForeground(new Color(30, 100, 50));
        } else {
            lbl.setBackground(isSelected ? new Color(232, 240, 254) : Color.WHITE);
            lbl.setForeground(Color.DARK_GRAY);
        }
        return lbl;
    }
});
    lblTotalCompras.setText("Total compras: " + total);
    jLabel9.setText(paginaActual + "/" + totalPaginas);
    jButton3.setEnabled(paginaActual > 1);
    jButton4.setEnabled(paginaActual < totalPaginas);
}

private void configurarBotones() {
    jButton1.addActionListener(e -> {
        int fila = tablaCompras.getSelectedRow();
        if (fila < 0) { javax.swing.JOptionPane.showMessageDialog(null, "Seleccione una compra primero."); return; }
        int idCompra = (int) tablaCompras.getValueAt(fila, 0);
        String estado = tablaCompras.getValueAt(fila, 4).toString();
        dao.listarDetalle(idCompra, TablaDetalleCompra);
        jLabel13.setText("C-" + String.format("%05d", idCompra));
        jLabel14.setText("ESTADO ACTUAL:  " + estado);
        jLabel14.setForeground(estado.equals("PENDIENTE") ? new Color(180, 100, 0) : new Color(30, 100, 50));
        jLabel10.setText("Total Productos: " + TablaDetalleCompra.getRowCount());
    });
    jButton2.addActionListener(e -> {
        int fila = tablaCompras.getSelectedRow();
        if (fila < 0) { javax.swing.JOptionPane.showMessageDialog(null, "Seleccione una compra primero."); return; }
        String estado = tablaCompras.getValueAt(fila, 4).toString();
        if (!estado.equals("PENDIENTE")) { javax.swing.JOptionPane.showMessageDialog(null, "Esta compra ya fue recibida."); return; }
        int idCompra = (int) tablaCompras.getValueAt(fila, 0);
        String proveedor = tablaCompras.getValueAt(fila, 2).toString();
        int ok = javax.swing.JOptionPane.showConfirmDialog(null,
            "¿Confirmar recepción de C-" + String.format("%05d", idCompra) + "?\nProveedor: " + proveedor + "\n\nSe crearán los lotes automáticamente.",
            "Confirmar Recepción", javax.swing.JOptionPane.YES_NO_OPTION);
        if (ok == javax.swing.JOptionPane.YES_OPTION) { dao.confirmarRecepcion(idCompra); cargarTabla(); }
    });
    jButton3.addActionListener(e -> { paginaActual--; mostrarPagina(); });
    jButton4.addActionListener(e -> { paginaActual++; mostrarPagina(); });
}    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        lblTotalCompras = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDetalleCompra = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 35, 126));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Compras");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión de compras a proveedores");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrito-removebg-preview (1).png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 35, 126));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Buscar Compras");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 35, 126));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Proveedor");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 35, 126));
        jLabel6.setText("Estado");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaCompras.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N°COMPRAS", "FECHA", "PROVEEDOR", "TOTAL", "ESTADO", "USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCompras);

        lblTotalCompras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalCompras.setText("Total compras: 0");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 35, 126));
        jLabel7.setText("Fecha inicio");

        jLabel8.setText("jLabel8");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ver-removebg-preview (1).png"))); // NOI18N
        jButton1.setText("Ver");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Confirmar");

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setText("<");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton4.setText(">");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("XD");

        TablaDetalleCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "PRODUCTO", "CANTIDAD", "PRECIO COMPRA", "SUB TOTAL", "FECHA VENCIMIENTO", "DÍAS PARA VENCER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaDetalleCompra);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total Productos: 0");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("TOTAL: ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(26, 35, 126));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hoja-removebg-preview (1).png"))); // NOI18N
        jLabel12.setText("DETALLE COMPRA");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("D");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("ESTADO ACTUAL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(74, 74, 74)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(323, 323, 323))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(280, 280, 280))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 81, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDetalleCompra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTotalCompras;
    private javax.swing.JTable tablaCompras;
    // End of variables declaration//GEN-END:variables
}
