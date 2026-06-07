package clases;

public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precio;
    private int stock;
    private int stockMinimo;
    private String vencimiento;

    public Producto(String codigo, String nombre, String descripcion, String categoria, 
                    double precio, int stock, int stockMinimo, String vencimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.vencimiento = vencimiento;
    }
    public Producto() {
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }
    public String getVencimiento() { return vencimiento; }
    // Agrega este método para que el buscador tenga un formato elegante
    public String getFormatoBusqueda() {
    return String.format("[%s] %s - %s (S/. %.2f | Stock: %d)", 
                         codigo, nombre, descripcion, precio, stock);
    }
}
