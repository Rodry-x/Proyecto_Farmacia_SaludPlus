package farma;

import clases.ProductoDAO;
import clases.Producto;
import farma.PantallaCajero;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class VentanaStock extends javax.swing.JDialog {
    private final PantallaCajero PantallaCajero;
    private final List<Producto> listaCompletaStock = new ArrayList<>();
  

    public VentanaStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.PantallaCajero = (PantallaCajero) parent;
        initComponents();
        cargarDatosDesdeAzure(); // Carga los datos apenas se abre       
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

        scrollTablaStock.setName("scrollTablaStock"); // NOI18N

        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto", "Categoría", "Precio", "Vencimiento", "Stock"
            }
        ));
        tablaStock.setName("tablaStock"); // NOI18N
        tablaStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaStockMouseClicked(evt);
            }
        });
        scrollTablaStock.setViewportView(tablaStock);

        txtBuscarStock.addActionListener(this::txtBuscarStockActionPerformed);

        btnFiltroTodos.setText("Productos");
        btnFiltroTodos.setName("btnFiltroTodos"); // NOI18N
        btnFiltroTodos.addActionListener(this::btnFiltroTodosActionPerformed);

        btnFiltroStockBajo.setText("⚠️ 0 Stock Bajo");
        btnFiltroStockBajo.setName("btnFiltroStockBajo"); // NOI18N
        btnFiltroStockBajo.addActionListener(this::btnFiltroStockBajoActionPerformed);

        btnFiltroPorVencer.setText("📅 0 Por Vencer");
        btnFiltroPorVencer.addActionListener(this::btnFiltroPorVencerActionPerformed);

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscarStock, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnFiltroTodos)
                .addGap(18, 18, 18)
                .addComponent(btnFiltroStockBajo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFiltroPorVencer)
                .addGap(12, 12, 12))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtBuscarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltroTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltroStockBajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltroPorVencer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollTablaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollTablaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarStockActionPerformed

 String texto = txtBuscarStock.getText().toLowerCase();
    List<Producto> filtrados = new ArrayList<>();
    for (Producto p : listaCompletaStock) {
        // Buscamos en nombre, codigo Y descripcion
        if (p.getNombre().toLowerCase().contains(texto) || 
            p.getCodigo().toLowerCase().contains(texto) ||
            (p.getDescripcion() != null && p.getDescripcion().toLowerCase().contains(texto))) {
            
            filtrados.add(p);
        }
    }
    llenarTabla(filtrados);
    }//GEN-LAST:event_txtBuscarStockActionPerformed

    private void btnFiltroStockBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroStockBajoActionPerformed
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : listaCompletaStock) {
            if (p.getStock() <= p.getStockMinimo()) {
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
        for (Producto p : listaCompletaStock) {
            if (p.getVencimiento() != null && p.getVencimiento().contains("2026")) {
                filtrados.add(p);
            }
        }
        llenarTabla(filtrados);
    }//GEN-LAST:event_btnFiltroPorVencerActionPerformed

    private void tablaStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaStockMouseClicked
        if (evt.getClickCount() == 2) {
            int fila = tablaStock.getSelectedRow();
            if (fila != -1) {
                String codigo = tablaStock.getValueAt(fila, 0).toString();
                String nombre = tablaStock.getValueAt(fila, 1).toString();
                double precio = Double.parseDouble(tablaStock.getValueAt(fila, 3).toString().replace("S/. ", ""));
                
                PantallaCajero.agregarProductoAVenta(codigo, nombre, precio);
                dispose(); 
            }
        }
    }//GEN-LAST:event_tablaStockMouseClicked
private void cargarDatosDesdeAzure() {
        new Thread(() -> {
            List<Producto> productos = new ProductoDAO().obtenerCatalogo("");
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
            model.addRow(new Object[]{
                p.getCodigo(), p.getNombre(), p.getCategoria(), 
                "S/. " + String.format("%.2f", p.getPrecio()), p.getVencimiento(), p.getStock()
            });
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnFiltroPorVencer;
    public javax.swing.JButton btnFiltroStockBajo;
    public javax.swing.JButton btnFiltroTodos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelSuperior;
    public javax.swing.JScrollPane scrollTablaStock;
    public javax.swing.JTable tablaStock;
    public javax.swing.JTextField txtBuscarStock;
    // End of variables declaration//GEN-END:variables
}
