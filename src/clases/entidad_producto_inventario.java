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
    int id_producto;
    String nombre;
    String descripcion;
    int id_categoria;
    String nombre_categoria;
    int stock_minimo;
    int stock_general;
    int id_impuesto;
    String nombre_impuesto;
    double porcentaje_impuesto;
    double precio_venta;

    public int getId_producto() {
        return id_producto;
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

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public int getStock_general() {
        return stock_general;
    }

    public int getId_impuesto() {
        return id_impuesto;
    }

    public String getNombre_impuesto() {
        return nombre_impuesto;
    }

    public double getPorcentaje_impuesto() {
        return porcentaje_impuesto;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public void setStock_general(int stock_general) {
        this.stock_general = stock_general;
    }

    public void setId_impuesto(int id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public void setNombre_impuesto(String nombre_impuesto) {
        this.nombre_impuesto = nombre_impuesto;
    }

    public void setPorcentaje_impuesto(double porcentaje_impuesto) {
        this.porcentaje_impuesto = porcentaje_impuesto;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
}
