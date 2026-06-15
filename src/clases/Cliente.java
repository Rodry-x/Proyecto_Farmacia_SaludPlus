package clases;

public class Cliente {
    private int id; // Agregado para consistencia con la BD
    private String dni;
    private String ruc;
    private String nombres;
    private String apellidos;
    private String telefono;

    // Constructor completo
    public Cliente(int id, String dni, String ruc, String nombres, String apellidos, String telefono) {
        this.id = id;
        this.dni = dni;
        this.ruc = ruc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    // Getters
    public int getId() { return id; }
    public String getDni() { return dni; }
    public String getRuc() { return ruc; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    
    public String getNombreCompleto() { return nombres + " " + apellidos; }
    
    public void setId(int id) { this.id = id; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public Cliente() {}
}