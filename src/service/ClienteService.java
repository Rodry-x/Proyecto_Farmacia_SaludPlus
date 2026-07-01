package service;

import dao.ClienteDAO;
import model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClienteService {

    private static final ClienteDAO dao = new ClienteDAO();

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

    public static boolean esCorreoValido(String correo) {
        return correo == null || correo.isEmpty() || correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$");
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

    public static String validarDatos(String dni, String nombres, String apellidos,
                                        String fechaNac, Object genero,
                                        String telefono, String correo) {
        if (dni == null || dni.isEmpty()) return "El DNI es obligatorio.";
        if (nombres == null || nombres.isEmpty()) return "Los nombres son obligatorios.";
        if (apellidos == null || apellidos.isEmpty()) return "Los apellidos son obligatorios.";
        if (fechaNac == null || fechaNac.isEmpty()) return "La fecha de nacimiento es obligatoria.";
        if (genero == null) return "Seleccione un género.";

        if (!esDNIValido(dni)) return "El DNI debe tener exactamente 8 dígitos numéricos.";
        if (!esNombreValido(nombres)) return "Los nombres solo deben contener letras y espacios.";
        if (!esApellidoValido(apellidos)) return "Los apellidos solo deben contener letras y espacios.";
        if (!esFechaValida(fechaNac)) return "Formato de fecha inválido. Use YYYY-MM-DD.";

        if (telefono != null && !telefono.isEmpty() && !esTelefonoValido(telefono))
            return "El teléfono debe tener exactamente 9 dígitos numéricos.";
        if (correo != null && !correo.isEmpty() && !esCorreoValido(correo))
            return "El correo ingresado no es válido.";

        return null;
    }

    public static Cliente buscarPorDni(String dni) {
        return dao.buscarPorDni(dni);
    }

    public static int registrarCliente(Cliente cliente, String telefono, String correo) {
        int id = dao.insertar(cliente);
        if (id != -1) {
            if (telefono != null && !telefono.isEmpty()) {
                dao.insertarTelefono(id, telefono);
            }
            if (correo != null && !correo.isEmpty()) {
                dao.insertarCorreo(id, correo);
            }
        }
        return id;
    }
}
