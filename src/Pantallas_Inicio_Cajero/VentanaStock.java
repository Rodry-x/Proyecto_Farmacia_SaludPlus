package Pantallas_Inicio_Cajero;

import database.ConectarBaseDatos;
import model.Producto;
import dao.ProductoDAO;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaStock extends javax.swing.JDialog {
    private final PantallaCajero PantallaCajero;
    private final List<Producto> listaCompletaStock = new ArrayList<>();
    private final Map<Integer, String> categorias = new HashMap<>();
    Color miAzul = new Color(31,94,157);

    public VentanaStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.PantallaCajero = (PantallaCajero) parent;
        initComponents();
        cargarDatos();

        tablaStock.getTableHeader().setBackground(miAzul);
        tablaStock.getTableHeader().setForeground(Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollTablaStock = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();
        jPanelSuperior = new javax.swing.JPanel();
        txtBuscarStock = new javax.swing.JTextField();
        btnFiltroTodos = new javax.swing.JButton();
        btnFiltroStockBajo = new javax.swing.JButton();
        btnFiltroPorVencer = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        scrollTablaStock.setBackground(new java.awt.Color(0, 102, 153));
        scrollTablaStock.setViewportBorder(new javax.swing.border.LineBorder(util.Formateador.AZUL_PRINCIPAL, 1, true));
        scrollTablaStock.setFont(new java.awt.Font("Helvetica Neue", 1, 13));
        scrollTablaStock.setHorizontalScrollBar(null);
        scrollTablaStock.setName("scrollTablaStock");

        tablaStock.setBorder(new javax.swing.border.LineBorder(util.Formateador.AZUL_PRINCIPAL, 1, true));
        tablaStock.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "PRODUCTO", "CATEGORÍA", "PRECIO VENTA", "STOCK", "STOCK MÍN", "VENCIMIENTO"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tablaStock.setName("tablaStock");
        tablaStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaStockMouseClicked(evt);
            }
        });
        scrollTablaStock.setViewportView(tablaStock);

        jPanelSuperior.setBackground(util.Formateador.AZUL_PRINCIPAL);

        txtBuscarStock.addActionListener(this::txtBuscarStockActionPerformed);

        btnFiltroTodos.setText("Todos");
        btnFiltroTodos.setName("btnFiltroTodos");
        btnFiltroTodos.addActionListener(this::btnFiltroTodosActionPerformed);

        btnFiltroStockBajo.setText("⚠️ 0 Stock Bajo");
        btnFiltroStockBajo.setName("btnFiltroStockBajo");
        btnFiltroStockBajo.addActionListener(this::btnFiltroStockBajoActionPerformed);

        btnFiltroPorVencer.setText("📅 0 Por Vencer");
        btnFiltroPorVencer.addActionListener(this::btnFiltroPorVencerActionPerformed);

        jButton1.setText("Atras");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                        .addComponent(txtBuscarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltroTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltroStockBajo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltroPorVencer))
                    .addComponent(jButton1))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltroTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltroStockBajo)
                    .addComponent(btnFiltroPorVencer))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollTablaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTablaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarStockActionPerformed
        String texto = txtBuscarStock.getText().toLowerCase();
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : listaCompletaStock) {
            if (p.getNombre().toLowerCase().contains(texto) ||
                String.valueOf(p.getId_producto()).contains(texto)) {
                filtrados.add(p);
            }
        }
        llenarTabla(filtrados);
    }//GEN-LAST:event_txtBuscarStockActionPerformed

    private void btnFiltroStockBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroStockBajoActionPerformed
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : listaCompletaStock) {
            if (p.getStock_general() <= p.getStock_minimo()) {
                filtrados.add(p);
            }
        }
        llenarTabla(filtrados);
    }//GEN-LAST:event_btnFiltroStockBajoActionPerformed

    private void btnFiltroTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroTodosActionPerformed
        llenarTabla(listaCompletaStock);
    }//GEN-LAST:event_btnFiltroTodosActionPerformed

    private void btnFiltroPorVencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroPorVencerActionPerformed
        List<Producto> filtrados = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Producto p : listaCompletaStock) {
            String fechaStr = p.getFecha_vencimiento();
            if (fechaStr != null && !fechaStr.isEmpty() && !fechaStr.equals("Sin lote")) {
                try {
                    LocalDate fechaVencimiento = LocalDate.parse(fechaStr.trim(), formato);
                    long diasRestantes = ChronoUnit.DAYS.between(hoy, fechaVencimiento);
                    if (diasRestantes >= 0 && diasRestantes <= 30) {
                        filtrados.add(p);
                    }
                } catch (DateTimeParseException e) {
                    System.err.println("Error de formato en fecha: " + fechaStr);
                }
            }
        }
        llenarTabla(filtrados);
    }//GEN-LAST:event_btnFiltroPorVencerActionPerformed

    private void tablaStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaStockMouseClicked
        int fila = tablaStock.getSelectedRow();
        if (fila != -1) {
            int id = Integer.parseInt(tablaStock.getValueAt(fila, 0).toString());
            int stock = Integer.parseInt(tablaStock.getValueAt(fila, 4).toString());
            if (stock <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El producto seleccionado no tiene stock disponible.",
                    "Sin Stock", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String nombre = tablaStock.getValueAt(fila, 1).toString();
            double precio = Double.parseDouble(
                tablaStock.getValueAt(fila, 3).toString().replace("S/. ", "")
            );
            PantallaCajero.agregarProductoAVenta(id, nombre, precio);
            dispose();
        }
    }//GEN-LAST:event_tablaStockMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cargarDatos() {
        new Thread(() -> {
            List<Producto> productos = new ProductoDAO().listarConStock();
            try (Connection con = ConectarBaseDatos.conectar();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(
                     "SELECT id_categoria, nombre_categoria FROM CATEGORIAS")) {
                while (rs.next()) {
                    categorias.put(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                }
            } catch (Exception e) {
                System.err.println("Error al cargar categorías: " + e.getMessage());
            }
            java.awt.EventQueue.invokeLater(() -> {
                if (productos != null) {
                    listaCompletaStock.clear();
                    listaCompletaStock.addAll(productos);
                    llenarTabla(listaCompletaStock);
                }
            });
        }).start();
    }

    private void llenarTabla(List<Producto> lista) {
        DefaultTableModel model = (DefaultTableModel) tablaStock.getModel();
        model.setRowCount(0);
        for (Producto p : lista) {
            String nombreCat = categorias.getOrDefault(p.getId_categoria(), "Sin categoría");
            model.addRow(new Object[]{
                p.getId_producto(),
                p.getNombre(),
                nombreCat,
                util.Formateador.precio(p.getPrecio_venta()),
                p.getStock_general(),
                p.getStock_minimo(),
                p.getFecha_vencimiento()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnFiltroPorVencer;
    public javax.swing.JButton btnFiltroStockBajo;
    public javax.swing.JButton btnFiltroTodos;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelSuperior;
    public javax.swing.JScrollPane scrollTablaStock;
    public javax.swing.JTable tablaStock;
    public javax.swing.JTextField txtBuscarStock;
    // End of variables declaration//GEN-END:variables
}
