package service;
import dao.ProductoDAO;
import model.ItemVenta;
import model.Venta;

import java.time.LocalDateTime;
import java.util.List;

public class VentaService {

    public static class TotalesVenta {
        public final double subtotal;
        public final double igv;
        public final double total;

        public TotalesVenta(double subtotal, double igv, double total) {
            this.subtotal = subtotal;
            this.igv = igv;
            this.total = total;
        }
    }

    public static TotalesVenta calcularTotales(List<ItemVenta> productos) {
        ProductoDAO productoDAO = new ProductoDAO();
        double subtotal = 0.0;
        double igv = 0.0;

        for (ItemVenta prod : productos) {
            double precio = prod.getPrecioUnitario();
            int cantidad = prod.getCantidad();
            double base = precio * cantidad;
            double porcentaje = productoDAO.obtenerPorcentajeImpuesto(prod.getIdProducto());
            subtotal += base;
            igv += base * (porcentaje / 100.0);
        }

        double total = subtotal + igv;
        return new TotalesVenta(subtotal, igv, total);
    }

    public static Venta crearVenta(int idCliente, int idUsuario, int idMetodo,
                                    double subtotal, double igv, double total) {
        return new Venta(0, idCliente, idUsuario, idMetodo,
                LocalDateTime.now(), subtotal, igv, total);
    }
}
