/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;

/**
 *
 * @author manue
 */
public class entidad_lotes_inventario {
    int id_lote;
    String producto_lote;
    int id_producto_lote;
    int stock_entrante_lote;
    int stock_actual;
    Date fecha_ingreso;
    Date fecha_vencimiento;
    int dias_vencimiento;
    String nombre_proveedor_lote;
    int id_proveedor_lote;
    int id_compra_asociada;
    String usuario_responsable;

    public int getId_lote() {
        return id_lote;
    }

    public String getProducto_lote() {
        return producto_lote;
    }

    public int getId_producto_lote() {
        return id_producto_lote;
    }

    public int getStock_entrante_lote() {
        return stock_entrante_lote;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public int getDias_vencimiento() {
        return dias_vencimiento;
    }

    public String getNombre_proveedor_lote() {
        return nombre_proveedor_lote;
    }

    public int getId_proveedor_lote() {
        return id_proveedor_lote;
    }

    public int getId_compra_asociada() {
        return id_compra_asociada;
    }

    public String getUsuario_responsable() {
        return usuario_responsable;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public void setProducto_lote(String producto_lote) {
        this.producto_lote = producto_lote;
    }

    public void setId_producto_lote(int id_producto_lote) {
        this.id_producto_lote = id_producto_lote;
    }

    public void setStock_entrante_lote(int stock_entrante_lote) {
        this.stock_entrante_lote = stock_entrante_lote;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public void setDias_vencimiento(int dias_vencimiento) {
        this.dias_vencimiento = dias_vencimiento;
    }

    public void setNombre_proveedor_lote(String nombre_proveedor_lote) {
        this.nombre_proveedor_lote = nombre_proveedor_lote;
    }

    public void setId_proveedor_lote(int id_proveedor_lote) {
        this.id_proveedor_lote = id_proveedor_lote;
    }

    public void setId_compra_asociada(int id_compra_asociada) {
        this.id_compra_asociada = id_compra_asociada;
    }

    public void setUsuario_responsable(String usuario_responsable) {
        this.usuario_responsable = usuario_responsable;
    }
    
}
