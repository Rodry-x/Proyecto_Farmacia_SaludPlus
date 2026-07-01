/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manue
 */
public class entidad_proveedor_inventario {
    int id_proveedor;
    String ruc;
    String nombre_proveedor;
    String direccion;

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre_proveedor;
    }
}
