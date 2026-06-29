package Pantallas_Admin;

import model.Usuario;
import service.UsuarioService;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistrarUsuario extends JDialog {

    private final UsuarioService service = new UsuarioService();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtUsername = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JComboBox<String> cbRol = new JComboBox<>(new String[]{"Administrador", "Cajero"});
    private Usuario usuarioEdit;
    private boolean guardado = false;

    public VentanaRegistrarUsuario(Window owner) {
        super(owner, "Registrar Usuario", ModalityType.APPLICATION_MODAL);
        initUI();
        setLocationRelativeTo(owner);
    }

    public VentanaRegistrarUsuario(Window owner, Usuario u) {
        super(owner, "Editar Usuario", ModalityType.APPLICATION_MODAL);
        this.usuarioEdit = u;
        initUI();
        txtNombre.setText(u.getNombre());
        txtApellido.setText(u.getApellido());
        txtUsername.setText(u.getUsername());
        txtPassword.setText(u.getPassword());
        cbRol.setSelectedIndex(u.getId_rol() == 1 ? 0 : 1);
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(250, 30);

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre.setFont(font); txtNombre.setPreferredSize(fieldSize);
        form.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        txtApellido.setFont(font); txtApellido.setPreferredSize(fieldSize);
        form.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        txtUsername.setFont(font); txtUsername.setPreferredSize(fieldSize);
        form.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtPassword.setFont(font); txtPassword.setPreferredSize(fieldSize);
        form.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        form.add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1;
        cbRol.setFont(font);
        form.add(cbRol, gbc);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(31, 94, 157));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> guardar());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dispose());

        buttons.add(btnGuardar);
        buttons.add(btnCancelar);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setResizable(false);
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        int idRol = cbRol.getSelectedIndex() == 0 ? 1 : 2;

        if (nombre.isEmpty() || apellido.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        if (usuarioEdit == null) {
            Usuario u = new Usuario(0, nombre, apellido, username, password, idRol);
            int id = service.registrar(u);
            if (id > 0) {
                guardado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario");
            }
        } else {
            usuarioEdit.setNombre(nombre);
            usuarioEdit.setApellido(apellido);
            usuarioEdit.setUsername(username);
            usuarioEdit.setPassword(password);
            usuarioEdit.setId_rol(idRol);
            if (service.actualizar(usuarioEdit)) {
                guardado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar usuario");
            }
        }
    }

    public boolean isGuardado() {
        return guardado;
    }
}
