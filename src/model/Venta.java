package model;

import java.time.LocalDateTime;

public class Venta {
    private int id_venta;
    private int id_cliente;
    private int id_usuario;
    private int id_metodopago;
    private LocalDateTime fecha;
    private double subtotal;
    private double igv_total;
    private double total_pagar;

    public Venta() {}

    public Venta(int id_venta, int id_cliente, int id_usuario, int id_metodopago,
                 LocalDateTime fecha, double subtotal, double igv_total, double total_pagar) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_usuario = id_usuario;
        this.id_metodopago = id_metodopago;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.igv_total = igv_total;
        this.total_pagar = total_pagar;
    }

    public int getId_venta() { return id_venta; }
    public void setId_venta(int id_venta) { this.id_venta = id_venta; }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public int getId_metodopago() { return id_metodopago; }
    public void setId_metodopago(int id_metodopago) { this.id_metodopago = id_metodopago; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getIgv_total() { return igv_total; }
    public void setIgv_total(double igv_total) { this.igv_total = igv_total; }

    public double getTotal_pagar() { return total_pagar; }
    public void setTotal_pagar(double total_pagar) { this.total_pagar = total_pagar; }
}