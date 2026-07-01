/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author manue
 */
public class Registro_movimiento_lotes {
    int id_historial;
    int id_lote;
    String nombre_producto;
    String nombre_accion;
    int stock_anterior;
    int stock_nuevo;
    String usuario_responsable;
    String username;
    Date fecha_movimiento;

    public int getId_historial() {
        return id_historial;
    }

    public int getId_lote() {
        return id_lote;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getNombre_accion() {
        return nombre_accion;
    }

    public int getStock_anterior() {
        return stock_anterior;
    }

    public int getStock_nuevo() {
        return stock_nuevo;
    }

    public String getUsuario_responsable() {
        return usuario_responsable;
    }

    public String getUsername() {
        return username;
    }

    public Date getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public void setNombre_accion(String nombre_accion) {
        this.nombre_accion = nombre_accion;
    }

    public void setStock_anterior(int stock_anterior) {
        this.stock_anterior = stock_anterior;
    }

    public void setStock_nuevo(int stock_nuevo) {
        this.stock_nuevo = stock_nuevo;
    }

    public void setUsuario_responsable(String usuario_responsable) {
        this.usuario_responsable = usuario_responsable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFecha_movimiento(Date fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }
    
}
