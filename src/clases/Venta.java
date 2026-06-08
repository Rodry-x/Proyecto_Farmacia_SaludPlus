package clases;

import java.util.Date;

public class Venta {
    private int idVenta;
    private String numeroVenta; // Agregado porque es obligatorio en la BD
    private int idUsuario;
    private int idCliente;
    private int idMetodoPago;
    private Date fechaVenta;
    private double total;

    public Venta(int idVenta, String numeroVenta, int idUsuario, int idCliente, int idMetodoPago, Date fechaVenta, double total) {
        this.idVenta = idVenta;
        this.numeroVenta = numeroVenta;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idMetodoPago = idMetodoPago;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    // Getters
    public int getIdVenta() { return idVenta; }
    public String getNumeroVenta() { return numeroVenta; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdCliente() { return idCliente; }
    public int getIdMetodoPago() { return idMetodoPago; }
    public Date getFechaVenta() { return fechaVenta; }
    public double getTotal() { return total; }
}