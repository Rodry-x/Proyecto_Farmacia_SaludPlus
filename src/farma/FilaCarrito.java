package farma;


public class FilaCarrito extends javax.swing.JPanel {
    
    private String codigoProducto; // 🔑 ¡NUEVO! Almacena la identidad real del producto
    private double precioUnitario;
    private int cantidadActual = 1;
    private PantallaCajero PantallaCajero; // 🔌 Para avisar si modificamos totales generales

    public int getCantidadActual() {
        return this.cantidadActual;
    }

    // Método público que llamará el catálogo si el producto ya existe en el carrito
    public void incrementarDesdeCatálogo() {
        this.cantidadActual++;
        actualizarValoresFila();
    }
    
    public String getName() {
        return lblNombre.getText();
    }
    
    // 🔑 ¡NUEVO! Getter para que la pantalla pueda verificar el código si lo necesita
    public String getCodigoProducto() {
        return this.codigoProducto;
    }
    
    public double getPrecioUnitario() {
        return this.precioUnitario;
    }
   
    public FilaCarrito() {
        initComponents();
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

        setBackground(new java.awt.Color(102, 102, 102));

        lblNombre.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        lblNombre.setText("jLabel1");

        btnRestar.setText("-");
        btnRestar.addActionListener(this::btnRestarActionPerformed);

        lblCantidad.setText("jLabel1");

        btnSumar.setText("+");
        btnSumar.addActionListener(this::btnSumarActionPerformed);

        lblPrecio.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        lblPrecio.setText("jLabel1");

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
                    .addComponent(lblNombre)
                    .addComponent(lblPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidad)
                .addGap(18, 18, 18)
                .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSuma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

public void configureFila(PantallaCajero pantalla, String codigo, String nombre, double precio) {
        this.PantallaCajero = pantalla;
        this.codigoProducto = codigo; // Enlazamos el código internamente
        this.precioUnitario = precio;
        this.cantidadActual = 1;

        java.awt.Dimension dim = new java.awt.Dimension(360, 60);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        
        // Pintamos los textos
        if (lblNombre != null && lblPrecio != null && lblCantidad != null && lblSuma != null) {
            lblNombre.setText(nombre);
            lblPrecio.setText("S/. " + String.format("%.2f", precio) + " c/u");
            lblCantidad.setText(String.valueOf(cantidadActual));
            lblSuma.setText("S/. " + String.format("%.2f", precio));
            System.out.println("🏷️ Labels de FilaCarrito rellenados exitosamente con texto.");
        } else {
            System.out.println("⚠️ ¡ALERTA! Algunos Labels internos de FilaCarrito son NULL.");
        }
        
        // Desactivamos los bordes de los botones
        if (btnRestar != null) btnRestar.setContentAreaFilled(false);
        if (btnSumar != null) btnSumar.setContentAreaFilled(false);
        if (btnEliminar != null) btnEliminar.setContentAreaFilled(false);
    }
    
private void actualizarValoresFila() {
        double subtotalFila = precioUnitario * cantidadActual;
        
        lblCantidad.setText(String.valueOf(cantidadActual));
        lblSuma.setText("S/. " + String.format("%.2f", subtotalFila));
        
        // Le avisamos a la pantalla del cajero que recalcule el subtotal, igv y total general
        if (PantallaCajero != null) {
            PantallaCajero.actualizarTotalesGenerales();
        }
    }


    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    java.awt.Container contenedor = this.getParent();
        if (contenedor != null) {
            // Primero quitamos el componente visual
            contenedor.remove(this);
            
            if (PantallaCajero != null) {
                // ✅ CORREGIDO: Le enviamos el CÓDIGO real a la memoria para que lo remueva con éxito
                PantallaCajero.eliminarProductoDeMemoria(this.codigoProducto); 
            }
            
            contenedor.revalidate();
            contenedor.repaint();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarActionPerformed
        cantidadActual++; // Sumamos 1 a la cantidad
        actualizarValoresFila();
    }//GEN-LAST:event_btnSumarActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
       if (cantidadActual > 1) { // Evitamos que baje de 1
            cantidadActual--; // Restamos 1
            actualizarValoresFila();
        }
    }//GEN-LAST:event_btnRestarActionPerformed



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
