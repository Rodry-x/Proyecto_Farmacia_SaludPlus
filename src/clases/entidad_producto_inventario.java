/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Date;

/**
 *
 * @author manue
 */
public class entidad_producto_inventario {
    String codigo_producto;
    String nombre;
    String descripcion;
    int id_categoria;
    int id_nombre_proveedor;
    double precio_compra;
    double precio_venta;
    Date fecha_vencimiento;
    int stock_actual;
    int stock_minimo;

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public int getId_nombre_proveedor() {
        return id_nombre_proveedor;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setId_nombre_proveedor(int id_nombre_proveedor) {
        this.id_nombre_proveedor = id_nombre_proveedor;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }
    
    
    
}
