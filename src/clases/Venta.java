package clases;

import java.util.Date;

public class Venta {
    private int idVenta;
    private int idUsuario;
    private int idCliente;
    private int idMetodoPago;
    private Date fechaVenta;
    private double total;

    // Constructor completo
    public Venta(int idVenta, int idUsuario, int idCliente, int idMetodoPago, Date fechaVenta, double total) {
        this.idVenta = idVenta;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idMetodoPago = idMetodoPago;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    // Getters para acceder a los datos de forma segura
    public int getIdVenta() { return idVenta; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdCliente() { return idCliente; }
    public int getIdMetodoPago() { return idMetodoPago; }
    public Date getFechaVenta() { return fechaVenta; }
    public double getTotal() { return total; }
}
