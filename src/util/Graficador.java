package util;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Graficador {

    public static void mostrarVentasPorFecha(List<Object[]> datos, String titulo, JFrame padre) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] fila : datos) {
            String id = "Venta #" + fila[0];
            double total = ((Number) fila[3]).doubleValue();
            dataset.addValue(total, "Total", id);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                titulo, "Venta", "Total (S/)",
                dataset, PlotOrientation.VERTICAL,
                false, true, false
        );
        mostrarDialogo(chart, "Grafico - " + titulo, padre, 600, 400);
    }

    public static void mostrarRankingCajeros(List<Object[]> datos, String titulo, JFrame padre) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] fila : datos) {
            String cajero = (String) fila[0];
            double total = ((Number) fila[2]).doubleValue();
            dataset.addValue(total, "Total Vendido", cajero);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                titulo, "Cajero", "Total Vendido (S/)",
                dataset, PlotOrientation.VERTICAL,
                false, true, false
        );
        mostrarDialogo(chart, "Grafico - " + titulo, padre, 600, 400);
    }

    public static void mostrarComparacionIngresosEgresos(double ingresos, double egresos, String titulo, JFrame padre) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Ingresos (S/ " + String.format("%.2f", ingresos) + ")", ingresos);
        dataset.setValue("Egresos (S/ " + String.format("%.2f", egresos) + ")", egresos);
        JFreeChart chart = ChartFactory.createPieChart(titulo, dataset, true, true, false);
        mostrarDialogo(chart, "Grafico - " + titulo, padre, 500, 400);
    }

    private static void mostrarDialogo(JFreeChart chart, String titulo, JFrame padre, int ancho, int alto) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(ancho, alto));

        JDialog dialogo = new JDialog(padre, titulo, true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.add(chartPanel);
        dialogo.pack();
        dialogo.setLocationRelativeTo(padre);
        dialogo.setVisible(true);
    }
}
