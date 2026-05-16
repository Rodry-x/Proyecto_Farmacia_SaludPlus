/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farma.imagenes;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author manue
 */
public class Usar_imagenes {
    public static void pintarImagen(JLabel lbl, String nombre) {
    ImageIcon imagen = new ImageIcon(
        Usar_imagenes.class.getResource("/farma/imagenes/" + nombre)
    );

    ImageIcon icono = new ImageIcon(
        imagen.getImage().getScaledInstance(
            lbl.getWidth(),
            lbl.getHeight(),
            Image.SCALE_SMOOTH
        )
    );

    lbl.setIcon(icono);
}
}

