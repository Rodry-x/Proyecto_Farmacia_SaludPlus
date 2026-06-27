package model;

public class Producto {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private int id_categoria;
    private int stock_minimo;
    private int stock_general;
    private int id_impuesto;
    private double precio_venta;
    private String fecha_vencimiento;

    public Producto() {}

    public Producto(int id_producto, String nombre, double precio_venta) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
    }

    public Producto(int id_producto, String nombre, int id_categoria, double precio_venta,
                    int stock_general, int stock_minimo, String fecha_vencimiento) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.precio_venta = precio_venta;
        this.stock_general = stock_general;
        this.stock_minimo = stock_minimo;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public int getId_producto() { return id_producto; }
    public void setId_producto(int id_producto) { this.id_producto = id_producto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getId_categoria() { return id_categoria; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }

    public int getStock_minimo() { return stock_minimo; }
    public void setStock_minimo(int stock_minimo) { this.stock_minimo = stock_minimo; }

    public int getStock_general() { return stock_general; }
    public void setStock_general(int stock_general) { this.stock_general = stock_general; }

    public int getId_impuesto() { return id_impuesto; }
    public void setId_impuesto(int id_impuesto) { this.id_impuesto = id_impuesto; }

    public double getPrecio_venta() { return precio_venta; }
    public void setPrecio_venta(double precio_venta) { this.precio_venta = precio_venta; }

    public String getFecha_vencimiento() { return fecha_vencimiento; }
    public void setFecha_vencimiento(String fecha_vencimiento) { this.fecha_vencimiento = fecha_vencimiento; }
}