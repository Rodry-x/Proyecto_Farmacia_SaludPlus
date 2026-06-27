package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClienteService {

    public static boolean esDNIValido(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    public static boolean esNombreValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public static boolean esApellidoValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    public static boolean esFechaValida(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return true;
        try {
            LocalDate.parse(fecha.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parseFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return null;
        return LocalDate.parse(fecha.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static boolean esSoloNumeros(String texto) {
        return texto != null && texto.matches("\\d*");
    }
}
