/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manue
 */
public class entidad_impuesto_inventario {
    int id_impuesto;
    String nombre_impuesto;
    double porcentaje;

    public int getId_impuesto() {
        return id_impuesto;
    }

    public String getNombre_impuesto() {
        return nombre_impuesto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setId_impuesto(int id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public void setNombre_impuesto(String nombre_impuesto) {
        this.nombre_impuesto = nombre_impuesto;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    @Override
    public String toString() {
        return nombre_impuesto;
    }  
}
