/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manue
 */
public class acción_registro_movimiento_lotesinventario {
    int id_accion;
    String nombre_acción;

    public int getId_accion() {
        return id_accion;
    }

    public String getNombre_acción() {
        return nombre_acción;
    }

    public void setId_accion(int id_accion) {
        this.id_accion = id_accion;
    }

    public void setNombre_acción(String nombre_acción) {
        this.nombre_acción = nombre_acción;
    }

    @Override
    public String toString() {
        return nombre_acción;
    }
    
}
