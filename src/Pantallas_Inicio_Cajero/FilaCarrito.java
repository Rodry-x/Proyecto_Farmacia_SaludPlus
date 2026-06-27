package Pantallas_Inicio_Cajero;

import service.CarritoService;
import model.ItemCarrito;
import javax.swing.JOptionPane;

public class FilaCarrito extends javax.swing.JPanel {

    @FunctionalInterface
    public interface AccionFila {
        void ejecutar(String codigo);
    }

    private final String codigoProducto;
    private final ItemCarrito item;
    private final CarritoService carritoService;
    private final Runnable onCambio;

    public String getCodigoProducto() { return codigoProducto; }
    public int getIdProducto() { return item.getIdProducto(); }
    public int getCantidad() { return item.getCantidad(); }
    public String getNombreProducto() { return item.getNombreProducto(); }
    public double getPrecioUnitario() { return item.getPrecioUnitario(); }
    public double getTotalFila() { return item.getTotalFila(); }

    public FilaCarrito() {
        this.item = null;
        this.codigoProducto = null;
        this.carritoService = null;
        this.onCambio = null;
        initComponents();
    }

    public FilaCarrito(ItemCarrito item, CarritoService carritoService, Runnable onCambio) {
        this.item = item;
        this.codigoProducto = item.getCodigoProducto();
        this.carritoService = carritoService;
        this.onCambio = onCambio;
        initComponents();
        configureFila(item);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        btnRestar = new javax.swing.JButton();
        lblCantidad = new javax.swing.JLabel();
        btnSumar = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        lblSuma = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNombre.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblNombre.setText("jLabel1");

        btnRestar.setText("-");
        btnRestar.addActionListener(this::btnRestarActionPerformed);

        lblCantidad.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblCantidad.setText("jLabel1");

        btnSumar.setText("+");
        btnSumar.addActionListener(this::btnSumarActionPerformed);

        lblPrecio.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        lblPrecio.setText("jLabel1");

        lblSuma.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lblSuma.setText("jLabel1");

        btnEliminar.setText("x");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSuma))
                    .addComponent(lblPrecio))
                .addGap(12, 12, 12)
                .addComponent(btnEliminar)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestar)
                    .addComponent(lblCantidad)
                    .addComponent(btnSumar)
                    .addComponent(lblSuma)
                    .addComponent(btnEliminar))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

public void configureFila(ItemCarrito item) {
        java.awt.Dimension dim = new java.awt.Dimension(596, 60);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        
        if (lblNombre != null && lblPrecio != null && lblCantidad != null && lblSuma != null) {
            lblNombre.setText(item.getNombreProducto());
            lblPrecio.setText(util.Formateador.precio(item.getPrecioUnitario()) + " c/u");
            lblCantidad.setText(String.valueOf(item.getCantidad()));
            lblSuma.setText(util.Formateador.precio(item.getPrecioUnitario()));
        }
        
        if (btnRestar != null) btnRestar.setContentAreaFilled(false);
        if (btnSumar != null) btnSumar.setContentAreaFilled(false);
        if (btnEliminar != null) btnEliminar.setContentAreaFilled(false);
    }
    
    private void actualizarValoresFila() {
        lblCantidad.setText(String.valueOf(item.getCantidad()));
        lblSuma.setText(util.Formateador.precio(item.getTotalFila()));
        if (onCambio != null) onCambio.run();
    }


    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.Container contenedor = this.getParent();
        if (contenedor != null) {
            contenedor.remove(this);
            if (carritoService != null) {
                carritoService.eliminar(this.codigoProducto);
            }
            if (onCambio != null) onCambio.run();
            contenedor.revalidate();
            contenedor.repaint();
        }
    }

    private void btnSumarActionPerformed(java.awt.event.ActionEvent evt) {
        CarritoService.StockInfo info = carritoService.verificarStock(codigoProducto);
        if (!info.isPuedeIncrementar()) {
            JOptionPane.showMessageDialog(this,
                "Stock m\u00E1ximo alcanzado para \"" + item.getNombreProducto()
                    + "\". Disponible: " + info.getStockDisponible(),
                "Sin Stock", JOptionPane.WARNING_MESSAGE);
            return;
        }
        carritoService.incrementar(codigoProducto);
        actualizarValoresFila();
    }

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {
        if (item.getCantidad() > 1) {
            carritoService.decrementar(codigoProducto);
            actualizarValoresFila();
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRestar;
    private javax.swing.JButton btnSumar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblSuma;
    // End of variables declaration//GEN-END:variables

    
    
}
