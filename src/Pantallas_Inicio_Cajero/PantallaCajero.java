package Pantallas_Inicio_Cajero;

import Metodos_de_pago.VentanaPago;
import Metodos_de_pago.VentanaVoucher;
import service.CajeroService;
import service.CarritoService;
import model.Cliente;
import dao.ClienteDAO;
import model.ItemCarrito;
import model.Producto;
import dao.ProductoDAO;
import dao.VentaDAO;
import service.VentaService;
import java.awt.Color;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;


public class PantallaCajero extends javax.swing.JFrame {

    private final CarritoService carritoService = new CarritoService();
    private java.util.List<Producto> productosSugeridos;
    private int idClienteSeleccionado = 9;
    private int idUsuario = 1;
    private boolean pagoCompletado = false;
    private int ultimaVentaId = -1;

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void limpiarCarrito() {
        carritoService.limpiar();
        panelCarrito.removeAll();
        this.idClienteSeleccionado = 9;
        lblNombreCliente.setText("Cliente: Genérico");
        lblNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        actualizarTotalesGenerales();
        generarSiguienteNumeroVenta();
        panelCarrito.revalidate();
        panelCarrito.repaint();
    }
  

    // EL MÉTODO DEBE ESTAR AQUÍ, DENTRO DE LA CLASE
    private void buscarCliente() {
       String documento = txtDniCliente.getText().trim();
    dao.ClienteDAO clienteDao = new dao.ClienteDAO();
    
    try {
        // Consultamos a SQL Server usando el DAO
        model.Cliente cliente = clienteDao.buscarPorDni(documento);

        if (cliente != null) {
            this.idClienteSeleccionado = cliente.getId_cliente();

            String nombreMostrar = cliente.getNombreCompleto();

            actualizarInterfazClienteRegistrado(cliente.getId_cliente(), nombreMostrar);
            
            JOptionPane.showMessageDialog(this, 
                "Cliente seleccionado: " + nombreMostrar, 
                "Cliente EnconbtnRegistrarClienteActionPerformedtrado", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } else {
            // Caso 2: El cliente NO existe
            
            // Hacemos que el botón de registrar aparezca inmediatamente en la pantalla
            if (btnRegistrarCliente != null) {
                btnRegistrarCliente.setVisible(true); 
            }
            
            // Cambiamos el label para advertir visualmente al cajero
            lblNombreCliente.setText("Cliente: No registrado");
            lblNombreCliente.setForeground(new java.awt.Color(204, 0, 0)); // Texto en Rojo para alertar
            
            // Mostramos la ventana emergente preguntando si quiere registrarlo ahora
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "El cliente con documento " + documento + " no está registrado.\n¿Desea abrir la ventana de registro ahora?", 
                "Cliente No Encontrado", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                // Si presiona SÍ, ejecutamos automáticamente el clic del botón registrar
                btnRegistrarClienteActionPerformed(null);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error al consultar el cliente: " + e.getMessage(), 
            "Error de Base de Datos", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
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

        Producto p = null;
        try {
            p = new ProductoDAO().buscarPorId(Integer.parseInt(codigo));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código inválido.");
        }

        if (p != null) {
            agregarProductoAVenta(p.getId_producto(), p.getNombre(), p.getPrecio_venta());
        } else {
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
                List<Producto> res = new ProductoDAO().obtenerSugerencias(texto);
                
                java.awt.EventQueue.invokeLater(() -> {
                    if (!txtBuscarProducto.getText().trim().equals(texto)) return;
                    productosSugeridos = res;
                    modelo.clear();

                    if (res != null && !res.isEmpty()) {
                        int pos = txtBuscarProducto.getCaretPosition();
                        
                        // Asegúrate de que el método getFormatoBusqueda esté en tu clase Producto
                        res.forEach(p -> modelo.addElement(p.getNombre() + " - S/. " + p.getPrecio_venta())); 
                        
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
                agregarProductoAVenta(p.getId_producto(), p.getNombre(), p.getPrecio_venta());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(util.Formateador.AZUL_PRINCIPAL);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnEfectivo.setBackground(new java.awt.Color(46, 125, 50));
        btnEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        btnEfectivo.setFocusPainted(false);
        btnEfectivo.setBorderPainted(false);
        btnEfectivo.setOpaque(true);
        btnEfectivo.setContentAreaFilled(true);
        btnEfectivo.setText("Efectivo");
        btnEfectivo.addActionListener(this::btnEfectivoActionPerformed);

        btnCredito.setBackground(new java.awt.Color(21, 101, 192));
        btnCredito.setForeground(new java.awt.Color(255, 255, 255));
        btnCredito.setFocusPainted(false);
        btnCredito.setBorderPainted(false);
        btnCredito.setOpaque(true);
        btnCredito.setContentAreaFilled(true);
        btnCredito.setText("Credito");
        btnCredito.addActionListener(this::btnCreditoActionPerformed);

        btnDdebito.setBackground(new java.awt.Color(0, 137, 123));
        btnDdebito.setForeground(new java.awt.Color(255, 255, 255));
        btnDdebito.setFocusPainted(false);
        btnDdebito.setBorderPainted(false);
        btnDdebito.setOpaque(true);
        btnDdebito.setContentAreaFilled(true);
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

        btnBilleterDigital.setBackground(new java.awt.Color(156, 39, 176));
        btnBilleterDigital.setForeground(new java.awt.Color(255, 255, 255));
        btnBilleterDigital.setFocusPainted(false);
        btnBilleterDigital.setBorderPainted(false);
        btnBilleterDigital.setOpaque(true);
        btnBilleterDigital.setContentAreaFilled(true);
        btnBilleterDigital.setText("Billetera Digital");
        btnBilleterDigital.addActionListener(this::btnBilleterDigitalActionPerformed);

        btnCobrar.setBackground(util.Formateador.AZUL_PRINCIPAL);
        btnCobrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCobrar.setFocusPainted(false);
        btnCobrar.setBorderPainted(false);
        btnCobrar.setOpaque(true);
        btnCobrar.setContentAreaFilled(true);
        btnCobrar.setText("IMPRIMIR");
        btnCobrar.addActionListener(this::btnCobrarActionPerformed);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setText("DNI/RUC  Cliente:");

        txtDniCliente.addActionListener(this::txtDniClienteActionPerformed);

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(this::btnBuscarClienteActionPerformed);

        btnRegistrarCliente.setBackground(new java.awt.Color(0, 102, 204));
        btnRegistrarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarCliente.setFocusPainted(false);
        btnRegistrarCliente.setBorderPainted(false);
        btnRegistrarCliente.setOpaque(true);
        btnRegistrarCliente.setContentAreaFilled(true);
        btnRegistrarCliente.setText("Registrar ");
        btnRegistrarCliente.setName("btnRegistrar"); // NOI18N
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

        jScrollPane1.setBackground(util.Formateador.AZUL_PRINCIPAL);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                             

            
    private void btnVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarActionPerformed
        limpiarCarrito();
    }//GEN-LAST:event_btnVaciarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
       
        String dni = txtDniCliente.getText().trim();

    // Validamos que no esté vacío y tenga el tamaño correcto (DNI 8 o RUC 11)
    if (dni.isEmpty() || (dni.length() != 8 && dni.length() != 11)) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, ingrese un número de DNI (8 dígitos) o RUC (11 dígitos) válido.", 
            "Formato Incorrecto", 
            JOptionPane.WARNING_MESSAGE);
        txtDniCliente.requestFocus();
        return;
    }
        buscarCliente();
       
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtDniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniClienteActionPerformed
        buscarCliente();
    }//GEN-LAST:event_txtDniClienteActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
    String docDigitado = txtDniCliente.getText().trim();
        
        // 1. Abrimos el modal como bloqueante (true)
        VentanaRegistrarCliente modal = new VentanaRegistrarCliente(this, true, docDigitado);
        modal.setLocationRelativeTo(this);
        modal.setVisible(true);
        
        // 2. Al cerrarse el modal, volvemos a intentar la búsqueda 
        // para traer los datos del cliente recién registrado
        if (!docDigitado.isEmpty()) {
            buscarCliente();
        }
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
    VentanaStock modal = new VentanaStock(this, true);
    modal.setLocationRelativeTo(this);
    modal.setVisible(true);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnDdebitoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Tarjeta de Debito");
}

private void btnBilleterDigitalActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Billetera Digital");
}

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
       
    if (carritoService.isEmpty() && ultimaVentaId <= 0) { 
        javax.swing.JOptionPane.showMessageDialog(this, "No hay productos en el carrito para visualizar.", "Carrito Vac\u00EDo", javax.swing.JOptionPane.WARNING_MESSAGE); 
        return; 
    }
    if (ultimaVentaId <= 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Debe seleccionar un m\u00E9todo de pago primero.",
            "Pago requerido", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    Metodos_de_pago.VentanaVoucher voucher = new Metodos_de_pago.VentanaVoucher(
        this, true, ultimaVentaId
    );
    voucher.setVisible(true);
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false); 
        new inicio().setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Tarjeta de Credito");
}

    private void btnEfectivoActionPerformed(java.awt.event.ActionEvent evt) {
    abrirVentanaPago("Efectivo");
}
    
   
    public void agregarProductoAVenta(int idProducto, String nombre, double precio) {
        CarritoService.Resultado resultado = carritoService.agregarOIncrementar(
            idProducto, nombre, precio);

        switch (resultado) {
            case STOCK_INSUFICIENTE:
                int stock = CajeroService.validarStock(idProducto);
                JOptionPane.showMessageDialog(this,
                    "Stock insuficiente para \"" + nombre + "\". Disponible: " + stock,
                    "Sin Stock", JOptionPane.WARNING_MESSAGE);
                return;
            case SIN_STOCK:
                JOptionPane.showMessageDialog(this,
                    "\"" + nombre + "\" no tiene stock disponible.",
                    "Sin Stock", JOptionPane.WARNING_MESSAGE);
                return;
            case AGREGADO: {
                ItemCarrito item = carritoService.getItem(String.valueOf(idProducto));
                if (item != null) {
                    FilaCarrito filaUI = new FilaCarrito(item, carritoService,
                        this::actualizarTotalesGenerales);
                    panelCarrito.add(filaUI);
                }
                break;
            }
        }

        actualizarTotalesGenerales();
        panelCarrito.revalidate();
        panelCarrito.repaint();
    }


    
    
public void actualizarTotalesGenerales() {
    if (carritoService.isEmpty()) {
        carritoService.setMontoTotal(0.0);
        lblSubtotal.setText("S/. 0.00");
        lblIGV.setText("S/. 0.00");
        lblTotalVenta.setText("S/. 0.00");
        jLabel3.setText("0");
        return;
    }

    VentaService.TotalesVenta totales = VentaService.calcularTotales(
        carritoService.toItemVentaList());

    carritoService.setMontoTotal(totales.total);
    lblSubtotal.setText(util.Formateador.precio(totales.subtotal));
    lblIGV.setText(util.Formateador.precio(totales.igv));
    lblTotalVenta.setText(util.Formateador.precio(totales.total));
    jLabel3.setText(String.valueOf(carritoService.size()));
}

private void abrirVentanaPago(String tipoPago) {
    if (carritoService.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "El carrito está vacío.");
        return;
    }

    int idMetodo = CajeroService.obtenerIdMetodo(tipoPago);
    VentanaPago modal = new VentanaPago(
        this, true, carritoService.getMontoTotal(), tipoPago, this.idUsuario,
        this.idClienteSeleccionado, idMetodo,
        carritoService.toItemVentaList()
    );
    modal.setLocationRelativeTo(this);
    modal.setNumeroVenta(lblNumeroVenta.getText());
    modal.setVisible(true);
    if (modal.isExitosa()) {
        pagoCompletado = true;
        ultimaVentaId = modal.getIdVentaGenerada();
        limpiarCarrito();
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

public void actualizarInterfazClienteRegistrado(int id, String nombre) {
    this.idClienteSeleccionado = id;
    lblNombreCliente.setText("Cliente: " + nombre);
    lblNombreCliente.setForeground(new java.awt.Color(0, 102, 0)); // Color verde al confirmar
    btnRegistrarCliente.setVisible(false); // Ocultamos el botón si ya está registrado
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
