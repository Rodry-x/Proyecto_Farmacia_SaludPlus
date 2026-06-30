/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manue
 */
public class SesionUsuario {
    private static Usuario usuarioActual;

    public static void setUsuario(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void cerrar() {
        usuarioActual = null;
    }
}
