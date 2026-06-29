package clases;

import java.util.Date;

public class Compra {

    int id_compra;
    Date fecha;
    String proveedor;
    double subtotal;
    double igv;
    double total;
    String estado;
    String usuario;
    int id_estado;
    int id_proveedor;

    // --- GETTERS ---
    public int getId_compra() { return id_compra; }
    public Date getFecha() { return fecha; }
    public String getProveedor() { return proveedor; }
    public double getSubtotal() { return subtotal; }
    public double getIgv() { return igv; }
    public double getTotal() { return total; }
    public String getEstado() { return estado; }
    public String getUsuario() { return usuario; }
    public int getId_estado() { return id_estado; }
    public int getId_proveedor() { return id_proveedor; }

    // --- SETTERS ---
    public void setId_compra(int id_compra) { this.id_compra = id_compra; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public void setIgv(double igv) { this.igv = igv; }
    public void setTotal(double total) { this.total = total; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setId_estado(int id_estado) { this.id_estado = id_estado; }
    public void setId_proveedor(int id_proveedor) { this.id_proveedor = id_proveedor; }
}