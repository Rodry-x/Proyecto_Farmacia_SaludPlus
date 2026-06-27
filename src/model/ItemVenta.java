package model;

public class ItemVenta {
    private final int idProducto;
    private final String nombreProducto;
    private final double precioUnitario;
    private final int cantidad;

    public ItemVenta(int idProducto, String nombreProducto, double precioUnitario, int cantidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }
    public double getTotalFila() { return precioUnitario * cantidad; }
}
