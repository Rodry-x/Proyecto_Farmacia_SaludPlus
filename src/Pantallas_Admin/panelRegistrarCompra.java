package Pantallas_Admin;

import model.SesionUsuario;
import model.Usuario;
import dao.CompraDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class panelRegistrarCompra extends javax.swing.JPanel {

    private CompraDao compraDao = new CompraDao();
    private ArrayList<Integer> listaIdProductos = new ArrayList<>();
    private ArrayList<java.util.Date> listaFechasVencimiento = new ArrayList<>();
    private ArrayList<Integer> listaCantidades = new ArrayList<>();
    private ArrayList<Double> listaPrecios = new ArrayList<>();
    private ArrayList<String> listaProveedores = new ArrayList<>();
    
    private double subtotalTotal = 0;
    private double igvTotal = 0;
    private double totalCompraTotal = 0;
    private double precioActual = 0;

    public panelRegistrarCompra() {
        initComponents();
        inicializarPanel();
    }

    public void inicializarPanel() {
        configurarTabla();
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        modelo.addColumn("Código");
        modelo.addColumn("Medicamento");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Fecha de Vencimiento");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Costo Unit.");
        modelo.addColumn("SubTotal");
        modelo.addColumn("Igv");
        modelo.addColumn("Total");
        
        jTable1.setModel(modelo);
    }

    public void buscarProducto() {
        String codigoStr = txt_codigo.getText().trim();
        
        if (codigoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresa el código del producto");
            txt_codigo.requestFocus();
            return;
        }
        
        try {
            int idProducto = Integer.parseInt(codigoStr);
            Object[] datos = compraDao.obtenerProductoPorCodigo(idProducto);
            
            if (datos != null) {
                txt_medicamento.setText((String) datos[1]);      // Nombre
                precioActual = (Double) datos[2];                // Precio (guardado internamente)
                txt_cantidad.requestFocus();                     // Enfoca cantidad
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
                txt_codigo.setText("");
                txt_medicamento.setText("");
                precioActual = 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código debe ser un número");
            txt_codigo.requestFocus();
        }
    }

    public void agregarMedicamento() {
        try {
            String codigoStr = txt_codigo.getText().trim();
            String nombre = txt_medicamento.getText().trim();
            String cantidadStr = txt_cantidad.getText().trim();
            String fechaVencStr = txt_fechavencimiento.getText().trim();
            String proveedor = txt_proveedor.getText().trim();
            
            // Validaciones
            if (codigoStr.isEmpty() || nombre.isEmpty() || cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completa: Código, Medicamento y Cantidad");
                return;
            }
            
            if (proveedor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del proveedor");
                txt_proveedor.requestFocus();
                return;
            }
            
            if (fechaVencStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa fecha de vencimiento (dd/MM/yyyy)");
                txt_fechavencimiento.requestFocus();
                return;
            }
            
            if (precioActual <= 0) {
                JOptionPane.showMessageDialog(null, "Busca un producto válido primero");
                txt_codigo.requestFocus();
                return;
            }
            
            // Convertir valores
            int codigo = Integer.parseInt(codigoStr);
            int cantidad = Integer.parseInt(cantidadStr);
            double precio = precioActual;
            double subtotal = cantidad * precio;
            double igvLinea = subtotal * 0.18;
            double totalLinea = subtotal + igvLinea;
            
            // Parsear fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaVencimiento = sdf.parse(fechaVencStr);
            
            // Agregar a listas
            listaIdProductos.add(codigo);
            listaFechasVencimiento.add(fechaVencimiento);
            listaCantidades.add(cantidad);
            listaPrecios.add(precio);
            listaProveedores.add(proveedor);
            
            // Agregar fila a tabla
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.addRow(new Object[]{
                codigo,
                nombre,
                proveedor,
                fechaVencStr,
                cantidad,
                String.format("%.2f", precio),
                String.format("%.2f", subtotal),
                String.format("%.2f", igvLinea),
                String.format("%.2f", totalLinea)
            });
            
            // Actualizar totales
            subtotalTotal += subtotal;
            igvTotal = subtotalTotal * 0.18;
            totalCompraTotal = subtotalTotal + igvTotal;
            actualizarTotales();
            
            // Limpiar campos de entrada
            txt_codigo.setText("");
            txt_medicamento.setText("");
            txt_cantidad.setText("");
            txt_fechavencimiento.setText("");
            precioActual = 0;
            txt_codigo.requestFocus();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad debe ser un número");
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa: dd/MM/yyyy");
            txt_fechavencimiento.requestFocus();
        }
    }

    private void actualizarTotales() {
        jLabel15.setText("Sub Total: S/ " + String.format("%.2f", subtotalTotal));
        jLabel16.setText("IGV: (18%): S/ " + String.format("%.2f", igvTotal));
        jLabel17.setText("TOTAL: (S/) S/ " + String.format("%.2f", totalCompraTotal));
    }

    public void guardarCompraCompleta() {
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Agrega al menos un medicamento");
            return;
        }
        
        try {
            String nombreProveedor = txt_proveedor.getText().trim();
            
            if (nombreProveedor.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del proveedor");
                txt_proveedor.requestFocus();
                return;
            }
            
            // Obtener ID del proveedor
            int idProveedor = compraDao.obtenerIdProveedorPorNombre(nombreProveedor);
            if (idProveedor == -1) {
                JOptionPane.showMessageDialog(null, "Error: Proveedor no encontrado en BD");
                return;
            }
            
            // Obtener ID del usuario actual
            Usuario usuarioActual = SesionUsuario.getUsuario();
            if (usuarioActual == null) {
                JOptionPane.showMessageDialog(null, "Error: Sesión de usuario no válida");
                return;
            }
            int idUsuario = usuarioActual.getId_usuario();
            
            // REGISTRAR COMPRA
            int idCompraGenerado = compraDao.registrarCompra(
                idUsuario, idProveedor, subtotalTotal, igvTotal, totalCompraTotal
            );
            
            if (idCompraGenerado == -1) {
                JOptionPane.showMessageDialog(null, "Error al registrar la compra");
                return;
            }
            
            // REGISTRAR DETALLES
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int idProducto = listaIdProductos.get(i);
                int cantidad = listaCantidades.get(i);
                double precio = listaPrecios.get(i);
                java.util.Date fechaVencimiento = listaFechasVencimiento.get(i);
                
                // Calcular subtotal, igv y total para esta línea
                double subtotalLinea = cantidad * precio;
                double igvLinea = subtotalLinea * 0.18;
                double totalLinea = subtotalLinea + igvLinea;
                
                // Convertir fecha a java.sql.Date
                java.sql.Date fechaSQL = new java.sql.Date(fechaVencimiento.getTime());
                
                boolean exito = compraDao.registrarDetalleCompra(
                    idCompraGenerado, idProducto, cantidad, precio,
                    subtotalLinea, igvLinea, totalLinea, fechaSQL
                );
                
                if (!exito) {
                    JOptionPane.showMessageDialog(null, "Error en producto: " + idProducto);
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(null,
                "✅ Compra registrada correctamente\n\n" +
                "ID Compra: " + idCompraGenerado + "\n" +
                "Total: S/ " + String.format("%.2f", totalCompraTotal));
            
            limpiarTodo();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            e.printStackTrace();
        }
    }

    public void limpiarTodo() {
        txt_fechaingreso.setText("");
        txt_codigo.setText("");
        txt_medicamento.setText("");
        txt_cantidad.setText("");
        txt_fechavencimiento.setText("");
        txt_proveedor.setText("");
        
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        
        listaIdProductos.clear();
        listaFechasVencimiento.clear();
        listaCantidades.clear();
        listaPrecios.clear();
        listaProveedores.clear();
        
        subtotalTotal = 0;
        igvTotal = 0;
        totalCompraTotal = 0;
        precioActual = 0;
        
        actualizarTotales();
        txt_proveedor.requestFocus();
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxProveedor = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_fechaingreso = new javax.swing.JTextField();
        txt_proveedor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_medicamento = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_fechavencimiento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btn_limpiar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_guardarcompra = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/images-removebg-preview (1).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 35, 126));
        jLabel2.setText("REGISTRAR COMPRA");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Registre una nueva compra de medicamentos");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 35, 126));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hoja-removebg-preview (1).png"))); // NOI18N
        jLabel5.setText("DATOS DE LA COMPRA");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Proveedor");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Fecha ingreso");

        javax.swing.GroupLayout jComboBoxProveedorLayout = new javax.swing.GroupLayout(jComboBoxProveedor);
        jComboBoxProveedor.setLayout(jComboBoxProveedorLayout);
        jComboBoxProveedorLayout.setHorizontalGroup(
            jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jComboBoxProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jComboBoxProveedorLayout.createSequentialGroup()
                        .addGroup(jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(63, 63, 63)
                        .addGroup(jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_fechaingreso, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(txt_proveedor))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jComboBoxProveedorLayout.setVerticalGroup(
            jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jComboBoxProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jComboBoxProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_fechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Código:");

        btn_buscar.setText("Buscar");
        btn_buscar.setBorderPainted(false);
        btn_buscar.setContentAreaFilled(false);
        btn_buscar.addActionListener(this::btn_buscarActionPerformed);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Medicamento: ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Cantidad");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Fecha vencimiento");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(26, 35, 126));
        jLabel9.setText("AGREGAR MEDICAMENTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(btn_buscar)
                                .addGap(14, 14, 14))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(27, 27, 27)
                        .addComponent(txt_fechavencimiento)
                        .addGap(87, 87, 87))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Medicamento", "Proveedor", "Fecha de Vencimiento", "Cantidad", "Costo Unit.", "SubTotal", "Igv", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btn_limpiar.setText("Limpiar");
        btn_limpiar.setBorder(null);
        btn_limpiar.setBorderPainted(false);
        btn_limpiar.setContentAreaFilled(false);
        btn_limpiar.addActionListener(this::btn_limpiarActionPerformed);

        btn_cancelar.setText("Cancelar");
        btn_cancelar.setBorderPainted(false);
        btn_cancelar.setContentAreaFilled(false);
        btn_cancelar.addActionListener(this::btn_cancelarActionPerformed);

        btn_guardarcompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_guardarcompra.setText("Guardar Compra");
        btn_guardarcompra.setBorderPainted(false);
        btn_guardarcompra.setContentAreaFilled(false);
        btn_guardarcompra.addActionListener(this::btn_guardarcompraActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btn_limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelar)
                .addGap(101, 101, 101)
                .addComponent(btn_guardarcompra)
                .addGap(97, 97, 97))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_guardarcompra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Agregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Agregar.setText("Agregar");
        btn_Agregar.setBorderPainted(false);
        btn_Agregar.setContentAreaFilled(false);
        btn_Agregar.addActionListener(this::btn_AgregarActionPerformed);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Total Productos: ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Sub Total:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("IGV: (18%):");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("TOTAL: (S/)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btn_Agregar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 90, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_Agregar)
                        .addGap(82, 82, 82)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
      buscarProducto();
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiarTodo();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
      limpiarTodo();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_guardarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarcompraActionPerformed
        guardarCompraCompleta();
    }//GEN-LAST:event_btn_guardarcompraActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
      agregarMedicamento();
    }//GEN-LAST:event_btn_AgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardarcompra;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JPanel jComboBoxProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_fechaingreso;
    private javax.swing.JTextField txt_fechavencimiento;
    private javax.swing.JTextField txt_medicamento;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
