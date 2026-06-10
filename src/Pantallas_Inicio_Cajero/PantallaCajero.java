package Pantallas_Inicio_Cajero;
import Metodos_de_pago.VentanaPago;
import clases.ClienteDAO;
import clases.Cliente;
import clases.Producto;
import clases.ProductoDAO;
import clases.VentaDAO;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;


public class PantallaCajero extends javax.swing.JFrame {
    
    private double montoTotalActual = 0.0;
    private final HashMap<String, FilaCarrito> productosEnCarrito = new HashMap<>();
    private java.util.List<Producto> productosSugeridos;
    private int idClienteSeleccionado = 9;
  

    // EL MÉTODO DEBE ESTAR AQUÍ, DENTRO DE LA CLASE
    private void buscarCliente() {
        String dni = txtDniCliente.getText().trim();
        Cliente c = new ClienteDAO().buscarPorDocumento(dni); // Usamos el nuevo método
        
        if (c != null) {
            lblNombreCliente.setText("Cliente: " + c.getNombres() + " " + c.getApellidos());
            this.idClienteSeleccionado = c.getId();
        } else {
            lblNombreCliente.setText("Cliente: Genérico");
            this.idClienteSeleccionado = 9;
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }
    }
    
    public PantallaCajero() {
        initComponents();
        configurarEstadoInicial();
        configurarEscaner();
    }
    
    
   
    private void configurarEscaner() {
      // Usamos el ActionListener que ya tenías en tu código original
      txtBuscarProducto.addActionListener(e -> {
        String codigo = txtBuscarProducto.getText().trim();
        if (codigo.isEmpty()) return;

        Producto p = new ProductoDAO().buscarPorCodigoExacto(codigo);

        if (p != null) {
            agregarProductoAVenta(p.getCodigo(), p.getNombre(), p.getPrecioVenta());
        } else {
            // Este es el mensaje que mencionabas
            JOptionPane.showMessageDialog(this, "El producto con código " + codigo + " no está registrado.");
        }

        txtBuscarProducto.setText("");
        txtBuscarProducto.requestFocus();
        });
    }

    private void configurarEstadoInicial() {
      btnRegistrarCliente.setVisible(false);
      generarSiguienteNumeroVenta();
    
      panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
      jScrollPane1.setViewportView(panelCarrito);

      javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
      javax.swing.JList<String> lista = new javax.swing.JList<>(modelo);
    
      menuSugerencias.setFocusable(false);
      javax.swing.JScrollPane scrollMenu = new javax.swing.JScrollPane(lista);
      scrollMenu.setBorder(null); 
      scrollMenu.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
      menuSugerencias.removeAll();
      menuSugerencias.add(scrollMenu);

      txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            String texto = txtBuscarProducto.getText().trim();
            if (texto.isEmpty()) { menuSugerencias.setVisible(false); return; }

            new Thread(() -> {
                // LLAMADA ACTUALIZADA: Usando tu nuevo método optimizado
                List<Producto> res = new ProductoDAO().obtenerSugerenciasParaCajero(texto);
                
                java.awt.EventQueue.invokeLater(() -> {
                    if (!txtBuscarProducto.getText().trim().equals(texto)) return;
                    productosSugeridos = res;
                    modelo.clear();

                    if (res != null && !res.isEmpty()) {
                        int pos = txtBuscarProducto.getCaretPosition();
                        
                        // Asegúrate de que el método getFormatoBusqueda esté en tu clase Producto
                        res.forEach(p -> modelo.addElement(p.getNombre() + " - S/. " + p.getPrecioVenta())); 
                        
                        menuSugerencias.setPopupSize(txtBuscarProducto.getWidth(), 140);
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

    lista.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int i = lista.getSelectedIndex();
            if (i != -1 && productosSugeridos != null && i < productosSugeridos.size()) {
                Producto p = productosSugeridos.get(i);
                // Ahora usamos el objeto Producto que ya trae los datos básicos
                agregarProductoAVenta(p.getCodigo(), p.getNombre(), p.getPrecioVenta());
                txtBuscarProducto.setText("");
                modelo.clear();
                menuSugerencias.setVisible(false);
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
        jLabel5 = new javax.swing.JLabel();
        btnEfectivo = new javax.swing.JButton();
        btnCredito = new javax.swing.JButton();
        btnDdebito = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        no = new javax.swing.JLabel();
        na = new javax.swing.JLabel();
        nada = new javax.swing.JLabel();
        lblTotalVenta = new javax.swing.JLabel();
        lblIGV = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblNumeroVenta = new javax.swing.JLabel();
        btnBilleterDigital = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnRegistrarCliente = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCarrito = new javax.swing.JPanel();
        txtBuscarProducto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVaciar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(31, 94, 157));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319)
                .addComponent(jLabel1)
                .addContainerGap(375, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnStock.setText("Stock");
        btnStock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        btnStock.addActionListener(this::btnStockActionPerformed);

        panelVenta.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Metodo de Pago ");

        btnEfectivo.setText("Efectivo");
        btnEfectivo.addActionListener(this::btnEfectivoActionPerformed);

        btnCredito.setText("Credito");
        btnCredito.addActionListener(this::btnCreditoActionPerformed);

        btnDdebito.setText("Debito");
        btnDdebito.addActionListener(this::btnDdebitoActionPerformed);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        jLabel6.setText("TOTAL A PAGAR:");

        no.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        no.setText("IGV (18%):");

        na.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        na.setText("Subtotal:");

        nada.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        nada.setText("N° Venta:");

        lblTotalVenta.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        lblTotalVenta.setText("S/. 0.00");

        lblIGV.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblIGV.setText("S/. 0.00");

        lblSubtotal.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblSubtotal.setText("S/. 0.00");

        lblNumeroVenta.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblNumeroVenta.setText("_______");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(lblTotalVenta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNumeroVenta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(no)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIGV))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(na)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSubtotal)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nada)
                    .addComponent(lblNumeroVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal)
                    .addComponent(na))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIGV)
                    .addComponent(no))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTotalVenta)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap())))
        );

        btnBilleterDigital.setText("Billetera Digital");
        btnBilleterDigital.addActionListener(this::btnBilleterDigitalActionPerformed);

        btnCobrar.setText("COBRAR");
        btnCobrar.addActionListener(this::btnCobrarActionPerformed);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("DNI/RUC  Cliente:");

        txtDniCliente.addActionListener(this::txtDniClienteActionPerformed);

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(this::btnBuscarClienteActionPerformed);

        btnRegistrarCliente.setBackground(new java.awt.Color(0, 102, 204));
        btnRegistrarCliente.setText("Registrar ");
        btnRegistrarCliente.addActionListener(this::btnRegistrarClienteActionPerformed);

        lblNombreCliente.setText("Cliente: Genérico");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNombreCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarCliente)
                    .addComponent(lblNombreCliente))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVentaLayout = new javax.swing.GroupLayout(panelVenta);
        panelVenta.setLayout(panelVentaLayout);
        panelVentaLayout.setHorizontalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                            .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDdebito, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBilleterDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel5))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelVentaLayout.setVerticalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelVentaLayout.createSequentialGroup()
                        .addGroup(panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDdebito, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVentaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBilleterDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(31, 94, 157));

        panelCarrito.setBackground(new java.awt.Color(255, 255, 255));
        panelCarrito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCarrito.setLayout(new javax.swing.BoxLayout(panelCarrito, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(panelCarrito);

        txtBuscarProducto.addActionListener(this::txtBuscarProductoActionPerformed);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("Carrito");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("0");

        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(this::btnVaciarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVaciar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btnVaciar))
                .addGap(2, 2, 2))
        );

        jButton2.setText("Ventas");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStock)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
       buscarCliente();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtDniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniClienteActionPerformed
        buscarCliente();
    }//GEN-LAST:event_txtDniClienteActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
    String docDigitado = txtDniCliente.getText().trim();
    // Pasamos el número a la pantalla de registro
    VentanaRegistrarCliente modal = new VentanaRegistrarCliente(this, true, docDigitado);
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
VentanaStock vs = new VentanaStock(this, true);

    vs.setSize(this.getWidth() - 0, this.getHeight() - 0);
    int x = this.getX() + 0;        // Un pequeño margen a la izquierda
    int y = this.getY() + 0;       // "120" es el espacio que bajará desde arriba
    vs.setLocation(x, y);
    
    vs.setVisible(true);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnDdebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDdebitoActionPerformed
   if (productosEnCarrito.isEmpty()) { JOptionPane.showMessageDialog(this, "Carrito vacío"); return; }
    
    VentanaPago modal = new VentanaPago(this, true, this.montoTotalActual, "Debito", 
                                        1, this.idClienteSeleccionado, 2, new java.util.ArrayList<>(productosEnCarrito.values()));
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnDdebitoActionPerformed

    private void btnBilleterDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBilleterDigitalActionPerformed
    if (productosEnCarrito.isEmpty()) { JOptionPane.showMessageDialog(this, "Carrito vacío"); return; }
    
    VentanaPago modal = new VentanaPago(this, true, this.montoTotalActual, "Billetera Digital", 
                                        1, this.idClienteSeleccionado, 4, new java.util.ArrayList<>(productosEnCarrito.values()));
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnBilleterDigitalActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false); 
        new inicio().setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditoActionPerformed
    if (productosEnCarrito.isEmpty()) { JOptionPane.showMessageDialog(this, "Carrito vacío"); return; }
    
    VentanaPago modal = new VentanaPago(this, true, this.montoTotalActual, "Credito", 
                                        1, this.idClienteSeleccionado, 3, new java.util.ArrayList<>(productosEnCarrito.values()));
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnCreditoActionPerformed

    private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfectivoActionPerformed
    if (productosEnCarrito.isEmpty()) { JOptionPane.showMessageDialog(this, "Carrito vacío"); return; }
    
    VentanaPago modal = new VentanaPago(this, true, this.montoTotalActual, "Efectivo", 
                                        1, this.idClienteSeleccionado, 1, new java.util.ArrayList<>(productosEnCarrito.values()));
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnEfectivoActionPerformed
    
   
public void agregarProductoAVenta(String codigo, String nombre, double precio) {
    try {
        // 1. Buscamos el producto para obtener su ID real
        Producto p = new ProductoDAO().buscarPorCodigoExacto(codigo);
        int idReal = (p != null) ? p.getId() : 0; 

        if (productosEnCarrito.containsKey(codigo)) {
            // Si ya existe, solo incrementamos
            productosEnCarrito.get(codigo).incrementarDesdeCatálogo();
        } else {
            // Si NO existe, creamos la fila y la configuramos
            FilaCarrito filaNueva = new FilaCarrito();
            filaNueva.configureFila(this, idReal, codigo, nombre, precio); 
            
            productosEnCarrito.put(codigo, filaNueva);
            panelCarrito.add(filaNueva);
        }
        
        // 2. Refrescamos la interfaz
        panelCarrito.revalidate();
        panelCarrito.repaint();
        actualizarTotalesGenerales();

    } catch (Exception e) {
        System.out.println("❌ Error en agregarProductoAVenta: " + e.getMessage());
        e.printStackTrace(); // Esto te ayudará a ver la línea exacta si vuelve a fallar
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

    // Actualizamos la variable global
    this.montoTotalActual = totalConIGV; 

    double subtotal = totalConIGV / 1.18;
    double igv = totalConIGV - subtotal;

    lblTotalVenta.setText(String.format("S/. %.2f", totalConIGV));
    lblSubtotal.setText(String.format("S/. %.2f", subtotal));
    lblIGV.setText(String.format("S/. %.2f", igv));
    jLabel3.setText(String.valueOf(cantidadTotalProductos));
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
    

    
    
private void generarSiguienteNumeroVenta() {
    VentaDAO ventaDao = new VentaDAO();
    new Thread(() -> {
        try {
            String numeroBoleta = ventaDao.obtenerSiguienteNumeroVenta();
            java.awt.EventQueue.invokeLater(() -> {
                if (lblNumeroVenta != null) {
                    lblNumeroVenta.setText(numeroBoleta);
                }
            });
        } catch (Exception e) { // <-- CAMBIA SQLException POR Exception AQUÍ
            System.err.println("❌ Error al obtener número de venta: " + e.getMessage());
            e.printStackTrace(); // Esto te dirá exactamente qué línea falla en la consola
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
    private javax.swing.JButton btnDdebito;
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JToggleButton btnStock;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
