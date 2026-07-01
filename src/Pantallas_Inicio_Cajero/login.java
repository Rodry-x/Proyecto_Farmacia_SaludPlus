
package Pantallas_Inicio_Cajero;
import model.Usuario;
import dao.UsuarioDAO;
import javax.swing.JOptionPane;



public class login extends javax.swing.JPanel {

 
   private final String rolRecibido;
   private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public login(String rol) { // Constructor que recibe el rol
        initComponents();
        this.rolRecibido = rol;
        // Personalización dinámica
        if (rol.equalsIgnoreCase("ADMINISTRADOR")) {
            lblTitulo.setText("Inicio de Sesión - Administrador");
        } else {
            lblTitulo.setText("Inicio de Sesión - Cajero");
        }
    }
    
    // Asegúrate de que este método esté DENTRO de tu clase login
    private void abrirPantallaPrincipal(int idRol) {
        if (idRol == 1) {
        new Pantallas_Admin.Panel_Admin().setVisible(true);
        } else {
        Pantallas_Inicio_Cajero.PantallaCajero pc = new Pantallas_Inicio_Cajero.PantallaCajero();
        pc.setIdUsuario(model.SesionUsuario.getUsuario().getId_usuario());
        pc.setVisible(true);
        }
    
        // Cerramos la ventana que contiene al panel
        java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
        ventana.dispose();
    }
}
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        btnRegistro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelBoton = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Ingresa tus credenciales para continuar");

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtUsuario.setCaretColor(new java.awt.Color(0, 102, 153));
        txtUsuario.setOpaque(true);
        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Contraseña");

        btnRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistroMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Iniciar sesión");

        javax.swing.GroupLayout btnRegistroLayout = new javax.swing.GroupLayout(btnRegistro);
        btnRegistro.setLayout(btnRegistroLayout);
        btnRegistroLayout.setHorizontalGroup(
            btnRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRegistroLayout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(176, 176, 176))
        );
        btnRegistroLayout.setVerticalGroup(
            btnRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRegistroLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelBoton.setName("btnPanelInicio"); // NOI18N
        panelBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBotonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotonMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 35, 126));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CAMBIAR ROL");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario_2.png"))); // NOI18N

        javax.swing.GroupLayout panelBotonLayout = new javax.swing.GroupLayout(panelBoton);
        panelBoton.setLayout(panelBotonLayout);
        panelBotonLayout.setHorizontalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        panelBotonLayout.setVerticalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonLayout.createSequentialGroup()
                .addGroup(panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBotonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBotonLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Iniciar sesión - Cajero");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresar-removebg-preview (1) (1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(63, 63, 63)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(panelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed

    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseClicked
        // 1. Capturamos los datos de los campos de texto
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword());

        // 2. Validación de campos vacíos
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
            return;
        }

        // 3. Determinamos el rol (1 = Administrador, 2 = Cajero)
        int idRolBuscado = rolRecibido.equalsIgnoreCase("ADMINISTRADOR") ? 1 : 2;

        // 4. Llamada al DAO para validar en la base de datos
        Usuario usuarioLogueado = usuarioDAO.validarUsuario(user, pass, idRolBuscado);

        // 5. Verificación de acceso
        if (usuarioLogueado != null) {
            model.SesionUsuario.setUsuario(usuarioLogueado);
            JOptionPane.showMessageDialog(this, "Acceso exitoso. Bienvenido " + usuarioLogueado.getNombre());
            abrirPantallaPrincipal(idRolBuscado); // Llama al método que ya tienes definido
        } else {
            JOptionPane.showMessageDialog(this, "Acceso denegado: Usuario o contraseña incorrectos.");
            txtPassword.setText("");
            txtPassword.requestFocus();
        }

    }//GEN-LAST:event_btnRegistroMouseClicked

    private void btnRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseEntered
        btnRegistro.setBackground(new java.awt.Color(200, 200, 200));
    }//GEN-LAST:event_btnRegistroMouseEntered

    private void btnRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseExited
        btnRegistro.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnRegistroMouseExited

    private void panelBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseClicked
   java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);
    
    // 2. Si la ventana encontrada es nuestra clase 'inicio', la usamos
    if (ventana instanceof Pantallas_Inicio_Cajero.inicio) {
        Pantallas_Inicio_Cajero.inicio ventanaPrincipal = (Pantallas_Inicio_Cajero.inicio) ventana;
        
        // 3. Llamamos a un método de la ventana principal para restaurar el menú
        // Esto NO destruye la ventana, solo cambia su contenido interior
        ventanaPrincipal.volverAlMenu(); 
    }
    }//GEN-LAST:event_panelBotonMouseClicked

    private void panelBotonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseEntered
        panelBoton.setBackground(new java.awt.Color(200, 200, 200));
    }//GEN-LAST:event_panelBotonMouseEntered

    private void panelBotonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseExited
        panelBoton.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_panelBotonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
