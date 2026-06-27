package service;
import dao.ProductoDAO;
import model.MetodoPago;
import dao.MetodoPagoDAO;

public class CajeroService {

    private CajeroService() {}

    public static int obtenerIdMetodo(String nombre) {
        MetodoPagoDAO dao = new MetodoPagoDAO();
        MetodoPago mp = dao.buscarPorNombre(nombre);
        return mp != null ? mp.getId_metodopago() : 1;
    }

    public static int validarStock(int idProducto) {
        return new ProductoDAO().obtenerStock(idProducto);
    }
}
