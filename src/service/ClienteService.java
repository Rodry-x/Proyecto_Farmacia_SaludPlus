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
