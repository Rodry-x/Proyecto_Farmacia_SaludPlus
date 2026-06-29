package Pantallas_Inicio_Cajero;

import model.Cliente;
import service.ClienteService;
import model.Genero;
import dao.GeneroDAO;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaRegistrarCliente extends javax.swing.JDialog {

    private PantallaCajero pantallaPadre;

    public VentanaRegistrarCliente(PantallaCajero parent, boolean modal, String dniInicial) {
        super(parent, modal);
        initComponents();
        this.pantallaPadre = parent;

        cargarGeneros();

        if (dniInicial != null && !dniInicial.isEmpty()) {
            txtDni.setText(dniInicial);
        }
    }

    private void cargarGeneros() {
        cmbGenero.removeAllItems();
        GeneroDAO dao = new GeneroDAO();
        List<Genero> lista = dao.listarTodos();
        for (Genero g : lista) {
            cmbGenero.addItem(g);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JTextField();
        cmbGenero = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        jLabel1.setText("Registrar Cliente");

        jLabel2.setText("DNI:");
        jLabel3.setText("Nombres:");
        jLabel4.setText("Telefono:");
        jLabel5.setText("Apellidos:");
        jLabel6.setText("Genero:");
        jLabel7.setText("Fecha Nacimiento:");
        jLabel8.setText("Correo:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        txtDni.setToolTipText("DNI: 8 dígitos");
        txtDni.addKeyListener(crearFiltroDigitos(8));

        txtTelefono.setToolTipText("9 dígitos");
        txtTelefono.addKeyListener(crearFiltroDigitos(9));

        txtNombre.addKeyListener(crearFiltroLetras());
        txtApellidos.addKeyListener(crearFiltroLetras());

        txtFechaNac.setText("YYYY-MM-DD");
        txtFechaNac.setForeground(java.awt.Color.GRAY);
        txtFechaNac.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if ("YYYY-MM-DD".equals(txtFechaNac.getText())) {
                    txtFechaNac.setText("");
                    txtFechaNac.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtFechaNac.getText().trim().isEmpty()) {
                    txtFechaNac.setText("YYYY-MM-DD");
                    txtFechaNac.setForeground(java.awt.Color.GRAY);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDni)
                            .addComponent(txtNombre)
                            .addComponent(txtApellidos)
                            .addComponent(txtTelefono)
                            .addComponent(cmbGenero, 0, 200, Short.MAX_VALUE)
                            .addComponent(txtCorreo)
                            .addComponent(txtFechaNac))
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(30, 30, 30)
                        .addComponent(btnGuardar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String dni = txtDni.getText().trim();
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String fechaNac = "YYYY-MM-DD".equals(txtFechaNac.getText()) ? "" : txtFechaNac.getText().trim();
        Genero genero = (Genero) cmbGenero.getSelectedItem();

        if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos obligatorios deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esDNIValido(dni)) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esNombreValido(nombres)) {
            JOptionPane.showMessageDialog(this, "Los nombres solo deben contener letras y espacios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esApellidoValido(apellidos)) {
            JOptionPane.showMessageDialog(this, "Los apellidos solo deben contener letras y espacios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ClienteService.esTelefonoValido(telefono)) {
            JOptionPane.showMessageDialog(this, "El teléfono debe tener exactamente 9 dígitos numéricos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!correo.isEmpty() && !ClienteService.esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ingresado no es válido.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!fechaNac.isEmpty() && !ClienteService.esFechaValida(fechaNac)) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (genero == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un género.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate fecha = ClienteService.parseFecha(fechaNac);
        Cliente nuevoCliente = new Cliente(0, nombres, apellidos, dni, genero.getId_genero(), fecha);

        btnGuardar.setEnabled(false);

        new Thread(() -> {
            try {
                int idGenerado = ClienteService.registrarCliente(nuevoCliente, telefono, correo);

                int finalId = idGenerado;
                SwingUtilities.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    if (finalId != -1) {
                        JOptionPane.showMessageDialog(this, "¡Cliente registrado con éxito!");
                        if (pantallaPadre != null) {
                            pantallaPadre.actualizarInterfazClienteRegistrado(finalId, nombres + " " + apellidos);
                        }
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar. Verifique si el DNI ya existe.");
                    }
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    btnGuardar.setEnabled(true);
                    JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage());
                });
            }
        }).start();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private java.awt.event.KeyAdapter crearFiltroDigitos(int maxLen) {
        return new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b' && c != 127) {
                    evt.consume();
                }
                JTextField src = (JTextField) evt.getSource();
                if (src.getText().length() >= maxLen && c != '\b' && c != 127) {
                    evt.consume();
                }
            }
        };
    }

    private java.awt.event.KeyAdapter crearFiltroLetras() {
        return new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b' && c != 127
                    && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
                    && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú'
                    && c != 'ñ' && c != 'Ñ') {
                    evt.consume();
                }
            }
        };
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Genero> cmbGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
}
