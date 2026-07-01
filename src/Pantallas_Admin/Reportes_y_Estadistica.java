
package Pantallas_Admin;

import dao.ReportesDAO;
import java.util.Calendar;
import java.util.List;
import javax.swing.SwingUtilities;

public class Reportes_y_Estadistica extends javax.swing.JPanel {

    public Reportes_y_Estadistica() {
        initComponents();
        configurarTablas();
        cmb_fechas.addActionListener(this::cmbFechasActionPerformed);
        btn_Ranking_Mejores.addActionListener(this::btnRankingMejoresActionPerformed);
        btn_Ranking_Peores.addActionListener(this::btnRankingPeoresActionPerformed);
        btn_Limpiar.addActionListener(this::btnLimpiarActionPerformed);
        btn_Atras.addActionListener(this::btnAtrasActionPerformed);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_Ranking_Mejores = new javax.swing.JButton();
        btn_Ranking_Peores = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Ventas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Calculo_Utilidades = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmb_fechas = new javax.swing.JComboBox<>();
        btn_Atras = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();

        jLabel1.setText("Reportes y Estadistica");

        btn_Ranking_Mejores.setText("Ranking 5 Mejores");

        btn_Ranking_Peores.setText("Ranking 5 Peores");

        tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ventas", "Fecha", "Total"
            }
        ));
        jScrollPane1.setViewportView(tabla_Ventas);

        jLabel2.setText("Lista Ventas");

        tabla_Calculo_Utilidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ingresos", "Egresos", "Ganancia Neta", "Margen de Utilidad"
            }
        ));
        jScrollPane2.setViewportView(tabla_Calculo_Utilidades);

        jLabel3.setText("Calculo de Utilidades");

        jLabel4.setText("Buscar por mes");

        cmb_fechas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        btn_Atras.setText("Atras");

        btn_Limpiar.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Ranking_Mejores)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Ranking_Peores)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(39, 39, 39)
                                        .addComponent(cmb_fechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(btn_Limpiar)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Atras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmb_fechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Limpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Ranking_Mejores)
                    .addComponent(btn_Ranking_Peores))
                .addGap(31, 31, 31)
                .addComponent(btn_Atras)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Atras;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Ranking_Mejores;
    private javax.swing.JButton btn_Ranking_Peores;
    private javax.swing.JComboBox<String> cmb_fechas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_Calculo_Utilidades;
    private javax.swing.JTable tabla_Ventas;
    // End of variables declaration//GEN-END:variables

    private final ReportesDAO reportesDAO = new ReportesDAO();

    private void configurarTablas() {
        tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID Venta", "Cajero", "Fecha", "Total"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tabla_Calculo_Utilidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"Ingresos", "Egresos", "Ganancia Neta", "Margen de Utilidad"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void cmbFechasActionPerformed(java.awt.event.ActionEvent evt) {
        int index = cmb_fechas.getSelectedIndex();
        if (index <= 0) return;
        cargarDatosPorMes(index);
    }

    private void btnRankingMejoresActionPerformed(java.awt.event.ActionEvent evt) {
        cargarRankingCajeros(true);
    }

    private void btnRankingPeoresActionPerformed(java.awt.event.ActionEvent evt) {
        cargarRankingCajeros(false);
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarTablas();
    }

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        Panel_Admin padre = (Panel_Admin) SwingUtilities.getAncestorOfClass(Panel_Admin.class, this);
        if (padre != null) {
            padre.limpiarPanelCentral();
        }
    }

    private void cargarDatosPorMes(int mes) {
        new Thread(() -> {
            Calendar cal = Calendar.getInstance();
            int año = cal.get(Calendar.YEAR);

            cal.set(año, mes - 1, 1, 0, 0, 0);
            java.util.Date desde = cal.getTime();

            cal.set(año, mes - 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
            java.util.Date hasta = cal.getTime();

            List<Object[]> ventas = reportesDAO.obtenerVentasPorFecha(desde, hasta);
            Object[] resumen = reportesDAO.obtenerResumenFinanciero(desde, hasta);

            java.awt.EventQueue.invokeLater(() -> {
                configurarTablas();
                llenarTablaVentas(ventas);
                llenarTablaUtilidades(resumen);
            });
        }).start();
    }

    private void cargarRankingCajeros(boolean mejores) {
        new Thread(() -> {
            List<Object[]> ranking = reportesDAO.obtenerRankingCajeros(mejores, 5);
            java.awt.EventQueue.invokeLater(() -> {
                tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][] {},
                    new String[] {"Cajero", "Ventas Realizadas", "Total Vendido"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla_Ventas.getModel();
                for (Object[] fila : ranking) {
                    model.addRow(fila);
                }
            });
        }).start();
    }

    private void limpiarTablas() {
        javax.swing.table.DefaultTableModel modelVentas = (javax.swing.table.DefaultTableModel) tabla_Ventas.getModel();
        modelVentas.setRowCount(0);
        javax.swing.table.DefaultTableModel modelUtilidades = (javax.swing.table.DefaultTableModel) tabla_Calculo_Utilidades.getModel();
        modelUtilidades.setRowCount(0);
        cmb_fechas.setSelectedIndex(0);
        configurarTablas();
    }

    private void llenarTablaVentas(List<Object[]> ventas) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla_Ventas.getModel();
        model.setRowCount(0);
        for (Object[] fila : ventas) {
            model.addRow(new Object[]{fila[0], fila[1], fila[2], fila[3]});
        }
    }

    private void llenarTablaUtilidades(Object[] resumen) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla_Calculo_Utilidades.getModel();
        model.setRowCount(0);
        if (resumen != null) {
            model.addRow(resumen);
        }
    }
}
