package clases;

public class Cliente {
    private String dniRuc;
    private String nombreCompleto;
    private String telefono;
    private String correo;

    // Constructor
    public Cliente(String dniRuc, String nombreCompleto, String telefono, String correo) {
        this.dniRuc = dniRuc;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
    }
    // Ejemplo para Producto.java
    public Cliente() {
    }
    // Métodos de encapsulamiento (Getters)
    public String getDniRuc() { return dniRuc; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
}
