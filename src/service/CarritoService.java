package service;
import dao.ProductoDAO;
import model.ItemVenta;
import model.ItemCarrito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CarritoService {

    public enum Resultado {
        AGREGADO, INCREMENTADO, STOCK_INSUFICIENTE, SIN_STOCK
    }

    public static class StockInfo {
        private final boolean puedeIncrementar;
        private final int stockDisponible;

        public StockInfo(boolean puedeIncrementar, int stockDisponible) {
            this.puedeIncrementar = puedeIncrementar;
            this.stockDisponible = stockDisponible;
        }

        public boolean isPuedeIncrementar() { return puedeIncrementar; }
        public int getStockDisponible() { return stockDisponible; }
    }

    private final Map<String, ItemCarrito> items = new LinkedHashMap<>();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private double montoTotal = 0.0;

    public Resultado agregarOIncrementar(int idProducto, String nombre, double precio) {
        String key = String.valueOf(idProducto);
        int stock = productoDAO.obtenerStock(idProducto);

        if (items.containsKey(key)) {
            ItemCarrito item = items.get(key);
            if (item.getCantidad() >= stock) return Resultado.STOCK_INSUFICIENTE;
            item.setCantidad(item.getCantidad() + 1);
            return Resultado.INCREMENTADO;
        } else {
            if (stock <= 0) return Resultado.SIN_STOCK;
            items.put(key, new ItemCarrito(idProducto, key, nombre, precio, 1));
            return Resultado.AGREGADO;
        }
    }

    public StockInfo verificarStock(String codigo) {
        ItemCarrito item = items.get(codigo);
        if (item == null) return new StockInfo(false, 0);
        int stock = productoDAO.obtenerStock(item.getIdProducto());
        return new StockInfo(item.getCantidad() < stock, stock);
    }

    public StockInfo verificarStock(int idProducto, int cantidadActual) {
        int stock = productoDAO.obtenerStock(idProducto);
        return new StockInfo(cantidadActual < stock, stock);
    }

    public void incrementar(String codigo) {
        ItemCarrito item = items.get(codigo);
        if (item != null) {
            item.setCantidad(item.getCantidad() + 1);
        }
    }

    public void decrementar(String codigo) {
        ItemCarrito item = items.get(codigo);
        if (item != null && item.getCantidad() > 1) {
            item.setCantidad(item.getCantidad() - 1);
        }
    }

    public void eliminar(String codigo) {
        items.remove(codigo);
    }

    public void limpiar() {
        items.clear();
        montoTotal = 0.0;
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public int size() { return items.size(); }
    public Collection<ItemCarrito> getItems() { return items.values(); }
    public ItemCarrito getItem(String codigo) { return items.get(codigo); }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double monto) { this.montoTotal = monto; }

    public List<ItemVenta> toItemVentaList() {
        List<ItemVenta> lista = new ArrayList<>();
        for (ItemCarrito item : items.values()) {
            lista.add(new ItemVenta(
                item.getIdProducto(),
                item.getNombreProducto(),
                item.getPrecioUnitario(),
                item.getCantidad()
            ));
        }
        return lista;
    }

    public List<String[]> prepararDatosVoucher() {
        List<String[]> lista = new ArrayList<>();
        for (ItemCarrito prod : items.values()) {
            String cantidad = String.valueOf(prod.getCantidad());
            String nombre = prod.getNombreProducto() != null
                ? prod.getNombreProducto() : "Medicamento";
            String totalFila = String.format("%.2f", prod.getTotalFila());
            lista.add(new String[]{cantidad, nombre, totalFila});
        }
        return lista;
    }
}
