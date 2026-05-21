package farma;
import clases.ClienteObjeto;
public class ventas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ventas.class.getName());
    // 1. Instancia única del Singleton
    private static ventas instanciaUnica;
    
    public ClienteObjeto clienteActual = new ClienteObjeto();

    public static ventas getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ventas();
        }
        return instanciaUnica;
    }
    
    public ventas() {
     initComponents();
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        txtBuscarCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        btnLimpiarLista = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnPago = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        lblImpuestos = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));

        jPanel1.setBackground(new java.awt.Color(31, 94, 157));

        btnAtras.setText("< Atras");
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        txtDni.setText("DNI");
        txtDni.addActionListener(this::txtDniActionPerformed);

        txtBuscarCodigo.setText("Buscar por codigo");
        txtBuscarCodigo.addActionListener(this::txtBuscarCodigoActionPerformed);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Escanea los productos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnAtras)
                        .addGap(75, 75, 75)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setWheelScrollingEnabled(false);

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PRODUCTOS", "CÓDIGO", "CANTIDAD", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.addPropertyChangeListener(this::tablaVentasPropertyChange);
        jScrollPane2.setViewportView(tablaVentas);

        btnLimpiarLista.setBackground(new java.awt.Color(102, 0, 204));
        btnLimpiarLista.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarLista.setText("Limpiar Lista");
        btnLimpiarLista.addActionListener(this::btnLimpiarListaActionPerformed);

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reembolso");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Eliminar productos");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        btnStock.setBackground(new java.awt.Color(0, 153, 255));
        btnStock.setForeground(new java.awt.Color(255, 255, 255));
        btnStock.setText("consultar stock ");
        btnStock.addActionListener(this::btnStockActionPerformed);

        btnPago.setBackground(new java.awt.Color(0, 153, 0));
        btnPago.setForeground(new java.awt.Color(255, 255, 255));
        btnPago.setText("Elegir medio de pago ");
        btnPago.addActionListener(this::btnPagoActionPerformed);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sub-Total:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Impuestos:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total:");

        lblSubTotal.setForeground(new java.awt.Color(255, 255, 255));

        lblImpuestos.setForeground(new java.awt.Color(255, 255, 255));

        lblTotal.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSubTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal)
                            .addComponent(lblImpuestos))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblImpuestos))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTotal))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpiarLista)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStock)
                    .addComponent(btnPago))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
     String dniBuscar = txtDni.getText().trim();

    if (dniBuscar.isEmpty() || dniBuscar.equals("DNI")) {
        return;
    }

    database.Conexion objetoConexion = new database.Conexion();
    java.sql.Connection cn = objetoConexion.conectar();

    if (cn != null) {
        try {
            String sql = "SELECT nombre_completo, telefono, correo FROM Clientes WHERE dni_ruc = ?";
            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dniBuscar);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Si ya existe, guardamos sus datos en la mochila
                clienteActual.dniRuc = dniBuscar;
                clienteActual.nombreCompleto = rs.getString("nombre_completo");
                clienteActual.telefono = rs.getString("telefono");
                clienteActual.correo = rs.getString("correo");
                
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente encontrado: " + clienteActual.nombreCompleto);
            } else {
                // 🚀 SI NO EXISTE: Abrimos la ventana que diseñaste visualmente
                int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Cliente no registrado. ¿Desea registrarlo?", "SaludPlus", javax.swing.JOptionPane.YES_NO_OPTION);
                
                if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                    // Llamamos a tu formulario visual
                    registrarCliente ventanaReg = new registrarCliente(this, true);
                    
                    // Le pasamos el DNI que ya digitó el cajero para que no lo vuelva a escribir
                    ventanaReg.txtDniReg.setText(dniBuscar); 
                    //ventanaReg.txtDniReg.setEditable(false); // Bloqueamos el DNI
                    
                    ventanaReg.setLocationRelativeTo(this); // La centramos
                    ventanaReg.setVisible(true); // La mostramos
                }
            }
            cn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }      
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtBuscarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCodigoActionPerformed
  String codigoBuscar = txtBuscarCodigo.getText().trim();

        if (codigoBuscar.isEmpty()) {
            return;
        }

        database.Conexion objetoConexion = new database.Conexion();
        java.sql.Connection cn = objetoConexion.conectar();

        if (cn != null) {
            try {
                String sql = "SELECT nombre, precio_venta FROM Productos WHERE codigo_producto = ?";
                java.sql.PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, codigoBuscar);
                
                java.sql.ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble("precio_venta");
                    int cantidad = 1; 

                    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaVentas.getModel();
                    
                    Object[] fila = new Object[4];
                    fila[0] = nombre;
                    fila[1] = codigoBuscar;
                    fila[2] = cantidad;
                    fila[3] = precio;

                    modelo.addRow(fila); 
                    calcularTotales();   
                    tablaVentas.revalidate();
                    tablaVentas.repaint();

                    txtBuscarCodigo.setText("");
                    txtBuscarCodigo.requestFocus(); 

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Medicamento no registrado con el código: " + codigoBuscar);
                    txtBuscarCodigo.setText("");
                    txtBuscarCodigo.requestFocus();
                }

                cn.close(); 
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al procesar la tabla: " + e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: No se pudo conectar al servidor.");
        }
    
    }//GEN-LAST:event_txtBuscarCodigoActionPerformed

    private void btnLimpiarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarListaActionPerformed
       try {
            javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaVentas.getModel();
            modelo.setRowCount(0);
            
            lblSubTotal.setText("Sub-Total: S/. 0.00");
            lblImpuestos.setText("Impuestos: S/. 0.00");
            lblTotal.setText("Total: S/. 0.00");
            
            txtDni.setText("");
            txtBuscarCodigo.setText(""); 
            
            javax.swing.JOptionPane.showMessageDialog(this, "Lista de productos vaciada correctamente");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al limpiar la lista: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLimpiarListaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          Reembolso Reembolso = new Reembolso();
        Reembolso.setVisible(true);
        this.dispose();
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaVentas.getModel();
        int filaSeleccionada = tablaVentas.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla con un clic para eliminarlo.");
            return;
        }
        
        modelo.removeRow(filaSeleccionada);
        calcularTotales();                  
        txtBuscarCodigo.requestFocus();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
     // 🔒 Congelamos la ventana actual y abrimos Stock
        this.setVisible(false); 
        Stock pantallaStock = new Stock();
        pantallaStock.setVisible(true);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
      // 🔒 Congelamos la ventana actual y abrimos Métodos de Pago
        this.setVisible(false); 
        Metodo_pago pantallaPago = new Metodo_pago();
        pantallaPago.setVisible(true);
    }//GEN-LAST:event_btnPagoActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
     btnLimpiarLista.doClick(); 
        this.setVisible(false); 
        new inicio().setVisible(true);     
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tablaVentasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaVentasPropertyChange
          if ("tableCellEditor".equals(evt.getPropertyName())) {
            if (!tablaVentas.isEditing()) {
               calcularTotales();
            }
        }
    }//GEN-LAST:event_tablaVentasPropertyChange

      public void calcularTotales() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaVentas.getModel();
        double subTotalGeneral = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 2) == null || modelo.getValueAt(i, 3) == null) {
                continue; 
            }

            try {
                int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString().trim());
                double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString().trim());
                
                subTotalGeneral += (cantidad * precio);
            } catch (Exception e) {
                // Evita caídas por errores de tipeo temporales
            }
        }

        double impuestos = subTotalGeneral * 0.18;
        double totalGeneral = subTotalGeneral + impuestos;

        lblSubTotal.setText(String.format("S/. %.2f", subTotalGeneral));
        lblImpuestos.setText(String.format("S/. %.2f", impuestos));
        lblTotal.setText(String.format("S/. %.2f", totalGeneral));
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> farma.ventas.getInstancia().setVisible(true));
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnLimpiarLista;
    private javax.swing.JButton btnPago;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImpuestos;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtBuscarCodigo;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
