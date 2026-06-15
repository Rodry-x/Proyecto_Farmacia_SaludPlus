package clases;

public class Producto {
    // Campos (mismos nombres que tu lógica de Base de Datos)
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String proveedor;
    private double precioCompra;
    private double precioVenta;
    private int stockActual;
    private int stockMinimo;
    private String vencimiento;

    // Constructor vacío
    public Producto() {}

    // Constructor completo (Asegúrate de usar este mismo orden en tu ProductoDAO)
    public Producto(String codigo, String nombre, String descripcion, String categoria, 
                    String proveedor, double precioVenta, int stockActual, int stockMinimo, 
                    String vencimiento, double precioCompra) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.vencimiento = vencimiento;
        this.precioCompra = precioCompra;
    }

    // GETTERS (Aquí se eliminan los errores rojos)
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public String getProveedor() { return proveedor; }
    public double getPrecioVenta() { return precioVenta; }
    public double getPrecioCompra() { return precioCompra; }
    public int getStock() { return stockActual; }
    public int getStockMinimo() { return stockMinimo; }
    public String getVencimiento() { return vencimiento; }
    public int getId() {return this.id;}

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public void setVencimiento(String vencimiento) { this.vencimiento = vencimiento; }
    // Método auxiliar para el buscador
    public String getFormatoBusqueda() {
        return this.codigo + " | " + this.nombre + " | S/. " + this.precioVenta;
    }
}