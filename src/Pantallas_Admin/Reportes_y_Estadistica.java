
package Pantallas_Admin;

import dao.ReportesDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import util.Graficador;

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
        btn_graficoListaVentas = new javax.swing.JButton();
        btn_graficoCalculoUtilidades = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
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

        btn_graficoListaVentas.setText("Mostrar Grafico");
        btn_graficoListaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_graficoListaVentasActionPerformed(evt);
            }
        });

        btn_graficoCalculoUtilidades.setText("Mostrar Grafico");
        btn_graficoCalculoUtilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_graficoCalculoUtilidadesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(86, 86, 86)
                                .addComponent(btn_graficoListaVentas))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(39, 39, 39)
                                .addComponent(cmb_fechas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(75, 75, 75)
                                .addComponent(btn_graficoCalculoUtilidades))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(105, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Ranking_Mejores, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Ranking_Peores)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmb_fechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Limpiar))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btn_graficoListaVentas)
                    .addComponent(btn_graficoCalculoUtilidades))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Ranking_Mejores, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Ranking_Peores, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btn_Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_graficoListaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_graficoListaVentasActionPerformed
        JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (modoRanking && !ultimoRanking.isEmpty()) {
            Graficador.mostrarRankingCajeros(ultimoRanking, "Ranking de Cajeros", padre);
        } else if (!modoRanking && !ultimasVentas.isEmpty()) {
            Graficador.mostrarVentasPorFecha(ultimasVentas, "Ventas del Periodo", padre);
        }
    }//GEN-LAST:event_btn_graficoListaVentasActionPerformed

    private void btn_graficoCalculoUtilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_graficoCalculoUtilidadesActionPerformed
        if (ultimoResumen != null) {
            JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);
            double ingresos = (Double) ultimoResumen[0];
            double egresos = (Double) ultimoResumen[1];
            Graficador.mostrarComparacionIngresosEgresos(ingresos, egresos, "Ingresos vs Egresos", padre);
        }
    }//GEN-LAST:event_btn_graficoCalculoUtilidadesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Atras;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Ranking_Mejores;
    private javax.swing.JButton btn_Ranking_Peores;
    private javax.swing.JButton btn_graficoCalculoUtilidades;
    private javax.swing.JButton btn_graficoListaVentas;
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
    private List<Object[]> ultimasVentas = new ArrayList<>();
    private Object[] ultimoResumen;
    private List<Object[]> ultimoRanking = new ArrayList<>();
    private boolean modoRanking = false;

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
                ultimasVentas = ventas;
                ultimoResumen = resumen;
                modoRanking = false;
                configurarTablas();
                llenarTablaVentas(ventas);
                llenarTablaUtilidades(resumen);
            });
        }).start();
    }

    private void cargarRankingCajeros(boolean mejores) {
        String tipo = mejores ? "Mejores" : "Peores";
        new Thread(() -> {
            List<Object[]> ranking = reportesDAO.obtenerRankingCajeros(mejores, 5);
            java.awt.EventQueue.invokeLater(() -> {
                ultimoRanking = ranking;
                modoRanking = true;
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
        ultimasVentas = new ArrayList<>();
        ultimoResumen = null;
        ultimoRanking = new ArrayList<>();
        modoRanking = false;
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
