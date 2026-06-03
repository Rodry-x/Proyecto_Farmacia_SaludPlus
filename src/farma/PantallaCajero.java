package farma;

import farma.FilaCarrito;
import clases.ClienteDAO;
import clases.Cliente;
import clases.Producto;
import clases.ProductoDAO;
import clases.VentaDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class PantallaCajero extends javax.swing.JFrame {
    
    private final HashMap<String, FilaCarrito> productosEnCarrito = new HashMap<>();
    private java.util.List<Producto> productosSugeridos;
    
    public PantallaCajero() {
        initComponents();
        configurarEstadoInicial();
    }

private void configurarEstadoInicial() {
    btnRegistrarCliente.setVisible(false);
    generarSiguienteNumeroVenta();
    
    panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
    jScrollPane1.setViewportView(panelCarrito);

    // --- CORRECCIÓN AQUÍ ---
    // En lugar de crear: javax.swing.JPopupMenu menu = new javax.swing.JPopupMenu();
    // Usaremos el que ya declaraste arriba como: menuSugerencias
    
    javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
    javax.swing.JList<String> lista = new javax.swing.JList<>(modelo);
    
    menuSugerencias.setFocusable(false); // Usamos la variable de clase
    javax.swing.JScrollPane scrollMenu = new javax.swing.JScrollPane(lista);
    scrollMenu.setBorder(null); 
    scrollMenu.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    menuSugerencias.removeAll(); // Limpiamos el que ya existe
    menuSugerencias.add(scrollMenu);

    txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            String texto = txtBuscarProducto.getText().trim();
            // Asegúrate de usar menuSugerencias aquí también
            if (texto.isEmpty()) { menuSugerencias.setVisible(false); return; }

            new Thread(() -> {
                List<Producto> res = new ProductoDAO().obtenerCatalogo(texto);
                java.awt.EventQueue.invokeLater(() -> {
                    if (!txtBuscarProducto.getText().trim().equals(texto)) return;
                    productosSugeridos = res;
                    modelo.clear();

                    if (res != null && !res.isEmpty()) {
                       int pos = txtBuscarProducto.getCaretPosition();
    
                       // Aquí está la corrección:
                       res.forEach(p -> modelo.addElement(p.getFormatoBusqueda())); 
    
                      menuSugerencias.setPopupSize(txtBuscarProducto.getWidth(), 140);
                      // ... el resto de tu código igual ...
                        if (!menuSugerencias.isShowing()) {
                            menuSugerencias.show(txtBuscarProducto, 0, txtBuscarProducto.getHeight());
                        }
                        txtBuscarProducto.setCaretPosition(pos);
                    } else {
                        menuSugerencias.setVisible(false);
                    }
                });
            }).start();
        }
    });

    // Asegúrate de cambiar 'menu' por 'menuSugerencias' también en el MouseListener
    lista.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int i = lista.getSelectedIndex();
            if (i != -1 && productosSugeridos != null && i < productosSugeridos.size()) {
                Producto p = productosSugeridos.get(i);
                agregarProductoAVenta(p.getCodigo(), p.getNombre(), p.getPrecio());
                txtBuscarProducto.setText("");
                modelo.clear();
                menuSugerencias.setVisible(false); // Aquí también
                txtBuscarProducto.requestFocus();
            }
        }
    });
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuSugerencias = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnStock = new javax.swing.JToggleButton();
        panelVenta = new javax.swing.JPanel();
        nada = new javax.swing.JLabel();
        na = new javax.swing.JLabel();
        no = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNumeroVenta = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblIGV = new javax.swing.JLabel();
        lblTotalVenta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        btnRegistrarCliente = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnEfectivo = new javax.swing.JButton();
        btnCredito = new javax.swing.JButton();
        btnDebito = new javax.swing.JButton();
        btnBilleterDigital = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVaciar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCarrito = new javax.swing.JPanel();
        txtBuscarProducto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PUNTO DE VENTA ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setText("atras ");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(319, 319, 319)
                .addComponent(jLabel1)
                .addGap(403, 403, 403))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnStock.setText("Stock");
        btnStock.addActionListener(this::btnStockActionPerformed);

        panelVenta.setBackground(new java.awt.Color(102, 102, 102));

        nada.setText("N° Venta:");

        na.setText("Subtotal:");

        no.setText("IGV (18%):");

        jLabel6.setText("TOTAL A PAGAR:");

        lblNumeroVenta.setText("_______");

        lblSubtotal.setText("S/. 0.00");

        lblIGV.setText("S/. 0.00");

        lblTotalVenta.setText("S/. 0.00");

        jLabel4.setText("DNI Cliente:");

        txtDniCliente.addActionListener(this::txtDniClienteActionPerformed);

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(this::btnBuscarClienteActionPerformed);

        lblNombreCliente.setText("Cliente: Genérico");

        btnRegistrarCliente.setBackground(new java.awt.Color(0, 102, 204));
        btnRegistrarCliente.setText("Registrar ");
        btnRegistrarCliente.addActionListener(this::btnRegistrarClienteActionPerformed);

        jLabel5.setText("Metodo de Pago ");

        btnEfectivo.setText("Efectivo");

        btnCredito.setText("Credito");

        btnDebito.setText("Debito");
        btnDebito.addActionListener(this::btnDebitoActionPerformed);

        btnBilleterDigital.setText("Billetera Digital");
        btnBilleterDigital.addActionListener(this::btnBilleterDigitalActionPerformed);

        btnCobrar.setText("COBRAR");
        btnCobrar.addActionListener(this::btnCobrarActionPerformed);

        javax.swing.GroupLayout panelVentaLayout = new javax.swing.GroupLayout(panelVenta);
        panelVenta.setLayout(panelVentaLayout);
        panelVentaLayout.setHorizontalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(panelVentaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(no)
                            .addComponent(jLabel6)
                            .addGroup(panelVentaLayout.createSequentialGroup()
                                .addComponent(lblNombreCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelVentaLayout.createSequentialGroup()
                                .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nada)
                                    .addComponent(na))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSubtotal)
                                    .addComponent(lblNumeroVenta)
                                    .addComponent(lblIGV)
                                    .addComponent(lblTotalVenta)))
                            .addComponent(jLabel5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelVentaLayout.createSequentialGroup()
                                        .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBilleterDigital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(4, 4, 4)))))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panelVentaLayout.setVerticalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCliente)
                    .addComponent(btnRegistrarCliente))
                .addGap(18, 18, 18)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nada)
                    .addComponent(lblNumeroVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(na)
                    .addComponent(lblSubtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no)
                    .addComponent(lblIGV))
                .addGap(18, 18, 18)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTotalVenta))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btnEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBilleterDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVentaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnDebito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jLabel2.setText("Carrito");

        jLabel3.setText("0");

        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(this::btnVaciarActionPerformed);

        panelCarrito.setBackground(new java.awt.Color(102, 102, 102));
        panelCarrito.setLayout(new javax.swing.BoxLayout(panelCarrito, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(panelCarrito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVaciar)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStock, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(txtBuscarProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(btnVaciar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                             

            
    private void btnVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarActionPerformed
        productosEnCarrito.clear();
        panelCarrito.removeAll();
        actualizarTotalesGenerales(); 
        generarSiguienteNumeroVenta();
        panelCarrito.revalidate();
        panelCarrito.repaint();
    }//GEN-LAST:event_btnVaciarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
       buscarClientePorDni();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtDniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniClienteActionPerformed
        buscarClientePorDni();
    }//GEN-LAST:event_txtDniClienteActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        String dniDigitado = txtDniCliente.getText().trim();
        VentanaRegistrarCliente modal = new VentanaRegistrarCliente(this, true, dniDigitado);
        modal.setLocationRelativeTo(this);
        modal.setVisible(true);
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        VentanaStock ventana = new VentanaStock(this, true);
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true); // Se detiene aquí hasta que cierren el stock
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDebitoActionPerformed

    private void btnBilleterDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBilleterDigitalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBilleterDigitalActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false); 
        new inicio().setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
// MÉTODO PÚBLICO CLAVE: VentanaStock llamará a este método al hacer doble clic en una fila
    public void agregarProductoAVenta(String codigo, String nombre, double precio) {
        try {
            if (productosEnCarrito.containsKey(codigo)) {
                FilaCarrito filaExistente = productosEnCarrito.get(codigo);
                filaExistente.incrementarDesdeCatálogo();
            } else {
                FilaCarrito filaNueva = new FilaCarrito();
                filaNueva.configureFila(this, codigo, nombre, precio); 
                
                productosEnCarrito.put(codigo, filaNueva);
                panelCarrito.add(filaNueva);
            }
            
            panelCarrito.revalidate();
            panelCarrito.repaint();
            actualizarTotalesGenerales();

        } catch (Exception e) {
            System.out.println("❌ Error al meter el producto: " + e.getMessage());
        }
    }
    
    
public void actualizarTotalesGenerales() {
        int cantidadTotalProductos = 0;
        double totalConIGV = 0.0;

        for (FilaCarrito fila : productosEnCarrito.values()) {
            int cantidadFila = fila.getCantidadActual();
            double precioFila = fila.getPrecioUnitario();
            
            cantidadTotalProductos += cantidadFila;
            totalConIGV += (precioFila * cantidadFila);
        }

        double subtotal = totalConIGV / 1.18;
        double igv = totalConIGV - subtotal;

        final int conteoFinal = cantidadTotalProductos;
        final double subtotalFinal = subtotal;
        final double igvFinal = igv;
        final double totalFinal = totalConIGV;

        java.awt.EventQueue.invokeLater(() -> {
            jLabel3.setText(String.valueOf(conteoFinal));
            lblSubtotal.setText("S/. " + String.format("%.2f", subtotalFinal));
            lblIGV.setText("S/. " + String.format("%.2f", igvFinal));
            lblTotalVenta.setText("S/. " + String.format("%.2f", totalFinal));
        });
    }

    
public void eliminarProductoDeMemoria(String codigoProducto) {
        if (productosEnCarrito.containsKey(codigoProducto)) {
            FilaCarrito filaAQuitar = productosEnCarrito.remove(codigoProducto);
            actualizarTotalesGenerales();
            
            java.awt.EventQueue.invokeLater(() -> {
                if (filaAQuitar != null) panelCarrito.remove(filaAQuitar); 
                panelCarrito.revalidate();
                panelCarrito.repaint();
            });
        }
    }
    
    
private void buscarClientePorDni() {
        String dni = txtDniCliente.getText().trim(); 
        if (dni.length() != 8 || !dni.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI válido de 8 dígitos.", "DNI Incorrecto", JOptionPane.WARNING_MESSAGE);
            return;
        }

        lblNombreCliente.setText("Buscando en Azure...");
        btnRegistrarCliente.setVisible(false); 

        ClienteDAO clienteDao = new ClienteDAO();
        new Thread(() -> {
            try {
                Cliente cliente = clienteDao.buscarPorDni(dni);
                java.awt.EventQueue.invokeLater(() -> {
                    if (cliente != null) {
                        lblNombreCliente.setForeground(new java.awt.Color(255, 255, 255)); 
                        lblNombreCliente.setText("Cliente: " + cliente.getNombreCompleto());
                    } else {
                        lblNombreCliente.setForeground(new java.awt.Color(255, 51, 51)); 
                        lblNombreCliente.setText("Cliente no registrado");
                        btnRegistrarCliente.setVisible(true); 
                    }
                });
            } catch (SQLException e) {
                java.awt.EventQueue.invokeLater(() -> {
                    lblNombreCliente.setText("Error de conexión");
                });
            }
        }).start();
    }
    
    
private void generarSiguienteNumeroVenta() {
        VentaDAO ventaDao = new VentaDAO();
        new Thread(() -> {
            try {
                String numeroBoleta = ventaDao.obtenerSiguienteNumeroVenta();
                java.awt.EventQueue.invokeLater(() -> lblNumeroVenta.setText(numeroBoleta));
            } catch (SQLException e) {
                System.out.println("❌ Error correlativo: " + e.getMessage());
            }
        }).start();
    }

    public void actualizarInterfazClienteRegistrado(String nombreCliente) {
        lblNombreCliente.setForeground(new java.awt.Color(255, 255, 255)); 
        lblNombreCliente.setText("Cliente: " + nombreCliente);
        btnRegistrarCliente.setVisible(false); 
    }
    




    
public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> new PantallaCajero().setVisible(true));
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBilleterDigital;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCredito;
    private javax.swing.JButton btnDebito;
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JToggleButton btnStock;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIGV;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNumeroVenta;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotalVenta;
    private javax.swing.JPopupMenu menuSugerencias;
    private javax.swing.JLabel na;
    private javax.swing.JLabel nada;
    private javax.swing.JLabel no;
    private javax.swing.JPanel panelCarrito;
    private javax.swing.JPanel panelVenta;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtDniCliente;
    // End of variables declaration//GEN-END:variables
}
