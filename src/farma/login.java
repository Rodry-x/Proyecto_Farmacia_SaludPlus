package farma;


//import database.Conexion; // Esto le dice a login dónde buscar la clase
public class login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(login.class.getName());
    public String rolRecibido;
    
        public login(String rol) {
            
            initComponents();
            
            this.rolRecibido = rol;
            this.setLocationRelativeTo(null); // Centra la ventana en tu Mac
            lblAcceso.setText("LOGIN - " + rol.toUpperCase()); // Para que sepa qué rol eligió
    
           
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblAcceso = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(31, 94, 158));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true));

        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        txtUsuario.setCaretColor(new java.awt.Color(0, 102, 153));
        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 0, 13)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 13)); // NOI18N
        jLabel2.setText("Contraseña");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(211, 233, 254));
        jLabel3.setText("FARMA ");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(31, 94, 157));
        jLabel4.setText("SISTEMA INTEGRAL DE FARMACIA ");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("INGRESAR CREDENCIALES ");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medicamento.png"))); // NOI18N

        btnAtras.setBackground(new java.awt.Color(210, 232, 253));
        btnAtras.setFont(new java.awt.Font("Arial Narrow", 0, 13)); // NOI18N
        btnAtras.setText("CAMBIAR ROL");
        btnAtras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true));
        btnAtras.addActionListener(this::btnAtrasActionPerformed);

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(31, 94, 157));
        jLabel10.setText("CONTROL");

        jPanel4.setBackground(new java.awt.Color(210, 232, 253));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true));

        lblAcceso.setForeground(new java.awt.Color(31, 94, 157));
        lblAcceso.setText("jLabel1");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setText("ACCESO:");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(61, 61, 61)
                .addComponent(lblAcceso)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblAcceso))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(31, 94, 157));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton1.setText("ACEPTAR");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(194, 194, 194))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(69, 69, 69)
                        .addComponent(btnAtras))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 // Tomamos los nombres exactos del segundo código
    String user = txtUsuario.getText().trim(); 
    String pass = new String(txtPassword.getPassword()); 

    // 1. VALIDACIÓN DE CAMPOS VACÍOS
    if (user.isEmpty() || pass.isEmpty()) { 
        javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos de usuario y contraseña"); 
        return; 
    } 

    // 2. INTENTO DE CONEXIÓN
    database.ConectarBaseDatos objetoConexion = new database.ConectarBaseDatos(); 
    java.sql.Connection cn = objetoConexion.conectar(); 

    if (cn != null) { 
        try { 
            // 3. VALIDACIÓN DE ROL (Admin = 1, Cajero = 2)
            int idRolBuscado = (rolRecibido.equals("ADMINISTRADOR")) ? 1 : 2; 

            // La consulta busca que coincidan: Usuario + Clave + ROL
            String sql = "SELECT nombres FROM Usuarios WHERE username=? AND password_hash=? AND id_rol=?"; 
            java.sql.PreparedStatement pst = cn.prepareStatement(sql); 
            pst.setString(1, user); 
            pst.setString(2, pass); 
            pst.setInt(3, idRolBuscado); 

            java.sql.ResultSet rs = pst.executeQuery(); 

            // 4. RESULTADO DE LA BÚSQUEDA
            if (rs.next()) { 
                String nombre = rs.getString("nombres"); 
                javax.swing.JOptionPane.showMessageDialog(this, "Acceso exitoso. Bienvenido " + nombre); 
                
                // Redirección inteligente según el Rol
                if (idRolBuscado == 1) {
                    // Si es Administrador (Rol 1), abre su panel correspondiente
                    new Panel_Admin().setVisible(true);
                } else {
                    // 🚨 CORRECCIÓN AQUÍ: Usamos el Singleton inteligente para el Cajero
                    PantallaCajero PantallaCajero = new PantallaCajero();
                    PantallaCajero.pack(); 
                    PantallaCajero.repaint();
                    PantallaCajero.setVisible(true);
                }
                
                this.dispose(); // Cierra el Login
            } else { 
                javax.swing.JOptionPane.showMessageDialog(this, "Usuario o contraseña no encontrados para el rol de " + rolRecibido); 
            } 

            cn.close(); 

        } catch (Exception e) { 
            javax.swing.JOptionPane.showMessageDialog(this, "Error al consultar datos: " + e.getMessage()); 
        } 
    } else { 
        javax.swing.JOptionPane.showMessageDialog(this, "Error: No se pudo establecer conexión con el servidor de SaludPlus"); 
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        inicio volver = new inicio();
        volver.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        
    }//GEN-LAST:event_txtUsuarioActionPerformed

    public static void main(String args[]) {
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAcceso;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
