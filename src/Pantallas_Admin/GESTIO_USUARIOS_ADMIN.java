/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pantallas_Admin;

import service.UsuarioService;
import model.Usuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class GESTIO_USUARIOS_ADMIN extends javax.swing.JPanel {

    private final UsuarioService service = new UsuarioService();
    private DefaultTableModel modeloTabla;

    public GESTIO_USUARIOS_ADMIN() {
        initComponents();
        imagenes.Usar_imagenes.pintarImagen(lbl_logo, "medicamento.png");

        jTextField1.setText("");
        configurarTabla();
        cargarUsuarios();

        jButton1.addActionListener(e -> registrar());
        jButton2.addActionListener(e -> editar());
        jButton3.addActionListener(e -> eliminar());

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar();
            }
        });

        // === ESTILO ===
        java.awt.Cursor cursorMano = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
        for (javax.swing.JButton btn : new javax.swing.JButton[]{jButton1, jButton2, jButton3}) {
            btn.setCursor(cursorMano);
        }

        jTextField1.setBackground(new java.awt.Color(245, 247, 250));
        jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)),
            javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));

        jTable1.setRowHeight(32);
        jTable1.setGridColor(new java.awt.Color(230, 230, 230));
        jTable1.setSelectionBackground(new java.awt.Color(220, 235, 255));
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Apellido", "Usuario", "Rol"}, 0
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        jTable1.setModel(modeloTabla);
        jTable1.getTableHeader().setBackground(util.Formateador.AZUL_PRINCIPAL);
        jTable1.getTableHeader().setForeground(java.awt.Color.WHITE);
        jTable1.setRowHeight(30);
        jTable1.getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    private void cargarUsuarios() {
        List<Usuario> lista = service.listarTodos();
        cargarTabla(lista);
    }

    private void cargarTabla(List<Usuario> lista) {
        modeloTabla.setRowCount(0);
        for (Usuario u : lista) {
            String rol = u.getId_rol() == 1 ? "Administrador" : "Cajero";
            modeloTabla.addRow(new Object[]{
                u.getId_usuario(), u.getNombre(), u.getApellido(), u.getUsername(), rol
            });
        }
    }

    private void buscar() {
        String texto = jTextField1.getText().trim();
        if (texto.isEmpty()) {
            cargarUsuarios();
        } else {
            List<Usuario> lista = service.buscar(texto);
            cargarTabla(lista);
        }
    }

    private void registrar() {
        VentanaRegistrarUsuario dlg = new VentanaRegistrarUsuario(
            javax.swing.SwingUtilities.getWindowAncestor(this)
        );
        dlg.setVisible(true);
        if (dlg.isGuardado()) cargarUsuarios();
    }

    private void editar() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        Usuario u = service.buscarPorId(id);
        if (u == null) return;

        VentanaRegistrarUsuario dlg = new VentanaRegistrarUsuario(
            javax.swing.SwingUtilities.getWindowAncestor(this), u
        );
        dlg.setVisible(true);
        if (dlg.isGuardado()) cargarUsuarios();
    }

    private void eliminar() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombre = (String) modeloTabla.getValueAt(fila, 1);

        int conf = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Eliminar usuario " + nombre + "?", "Confirmar",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (conf == javax.swing.JOptionPane.YES_OPTION) {
            try {
                if (service.eliminar(id)) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario eliminado correctamente");
                    cargarUsuarios();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar el usuario", "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (RuntimeException ex) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(943, 140));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 200));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(lbl_logo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("GESTION USUARIOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabel1, gridBagConstraints);

        jTextField1.setText("jTextField1");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.setPreferredSize(new java.awt.Dimension(500, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(jTextField1, gridBagConstraints);

        jPanel4.setPreferredSize(new java.awt.Dimension(3, 3));
        jPanel4.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(943, 60));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton1.setBackground(new java.awt.Color(31, 94, 157));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("REGISTRAR USUARIO");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton1.setFocusPainted(false);
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 120, 23, 0);
        jPanel5.add(jButton1, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(31, 94, 157));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDITAR USUARIO");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton2.setFocusPainted(false);
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 45, 23, 0);
        jPanel5.add(jButton2, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(31, 94, 157));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ELIMINAR USUARIO");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton3.setFocusPainted(false);
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 275, 23, 94);
        jPanel5.add(jButton3, gridBagConstraints);

        add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 80, 40, 80));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jPanel6, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_logo;
    // End of variables declaration//GEN-END:variables
}
