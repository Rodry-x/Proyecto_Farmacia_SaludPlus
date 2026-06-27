package model;

public class ItemCarrito {
    private final int idProducto;
    private final String codigoProducto;
    private final String nombreProducto;
    private final double precioUnitario;
    private int cantidad;

    public ItemCarrito(int idProducto, String codigoProducto, String nombreProducto,
                       double precioUnitario, int cantidad) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public int getIdProducto() { return idProducto; }
    public String getCodigoProducto() { return codigoProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getTotalFila() { return precioUnitario * cantidad; }
}
