package util;

import java.awt.Color;

public class Formateador {

    public static final Color AZUL_PRINCIPAL = new Color(31, 94, 157);

    private Formateador() {}

    public static String precio(double valor) {
        return "S/. " + String.format("%.2f", valor);
    }
}
