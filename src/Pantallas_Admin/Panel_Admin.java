package Pantallas_Admin;

import Pantallas_Admin.Reportes_y_Estadistica;
import Pantallas_Admin.PROVEEDORES_ADMIN;
import Pantallas_Admin.CONTROL_INVENTARIO_ADMIN;
import Pantallas_Admin.GESTIO_USUARIOS_ADMIN;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import model.SesionUsuario;
import model.Usuario;


public class Panel_Admin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Panel_Admin.class.getName());

 
    public Panel_Admin() {
        initComponents();
        imagenes.Usar_imagenes.pintarImagen(lbl_usuario, "perfil.png");
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        // === ESTILO ===
        java.awt.Font fuenteBtn = new java.awt.Font("Segoe UI", 1, 13);
        java.awt.Cursor cursorMano = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
        
        jPanel2.setBackground(util.Formateador.AZUL_PRINCIPAL);
        jPanel6.setBackground(util.Formateador.AZUL_PRINCIPAL);
        jPanel1.setBackground(util.Formateador.AZUL_PRINCIPAL);
        
        for (javax.swing.JButton btn : new javax.swing.JButton[]{jButton1, jButton2, jButton3, jButton4, jButton5, jButton6}) {
            btn.setFont(fuenteBtn);
            btn.setFocusPainted(false);
            btn.setCursor(cursorMano);
        }
        
        lbl_usuario.setForeground(java.awt.Color.WHITE);
        Usuario u = SesionUsuario.getUsuario();
        lbl_nombre_usuario.setText("BIENVENIDO " + u.getNombreCompleto());
    }
    private void MostrarPanel(JPanel p){
        p.setSize(823, 748);
        p.setLocation(0, 0);
        
        content_ADMIN.removeAll();
        content_ADMIN.add(p, BorderLayout.CENTER);
        content_ADMIN.revalidate();
        content_ADMIN.repaint();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lbl_usuario = new javax.swing.JLabel();
        lbl_nombre_usuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        content_ADMIN = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(320, 640));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 300));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton1.setBackground(new java.awt.Color(31, 94, 157));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ATRÁS");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton1.setPreferredSize(new java.awt.Dimension(96, 27));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jButton1, gridBagConstraints);

        lbl_usuario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 150;
        jPanel6.add(lbl_usuario, gridBagConstraints);

        lbl_nombre_usuario.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel6.add(lbl_nombre_usuario, gridBagConstraints);

        jPanel2.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(31, 94, 157));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("GESTION USUARIOS");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, 40));

        jButton3.setBackground(new java.awt.Color(31, 94, 157));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CONTROL DE INVENTARIO");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton3.setPreferredSize(new java.awt.Dimension(141, 27));
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 320, 40));

        jButton4.setBackground(new java.awt.Color(31, 94, 157));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("REPORTE y CIERRE");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 320, 40));

        jButton5.setBackground(new java.awt.Color(31, 94, 157));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("PROVEEDORES");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 320, 40));

        jButton6.setBackground(new java.awt.Color(51, 153, 255));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("COMPRAS");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton6.addActionListener(this::jButton6ActionPerformed);
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 320, 40));

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        content_ADMIN.setLayout(new java.awt.BorderLayout());
        getContentPane().add(content_ADMIN, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GESTIO_USUARIOS_ADMIN p_usuarios = new GESTIO_USUARIOS_ADMIN();
        MostrarPanel(p_usuarios);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MENU_INVENTARIO menu = new MENU_INVENTARIO();
        MostrarPanel(menu);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Reportes_y_Estadistica p_reportes = new Reportes_y_Estadistica();
        MostrarPanel(p_reportes);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        PROVEEDORES_ADMIN p_admin = new PROVEEDORES_ADMIN();
        MostrarPanel(p_admin);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
           PanelCompras p_compras = new PanelCompras();
    MostrarPanel(p_compras);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new Pantallas_Inicio_Cajero.inicio().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    public void limpiarPanelCentral() {
        content_ADMIN.removeAll();
        content_ADMIN.revalidate();
        content_ADMIN.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Panel_Admin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content_ADMIN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_nombre_usuario;
    private javax.swing.JLabel lbl_usuario;
    // End of variables declaration//GEN-END:variables
}
