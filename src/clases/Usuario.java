package clases;

public class Usuario {
    private int id;
    private int idRol;
    private String nombres;
    private String apellidos;
    private String username;
    private String passwordHash;

    public Usuario(int id, int idRol, String nombres, String apellidos, String username, String passwordHash) {
        this.id = id;
        this.idRol = idRol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters
    public int getId() { return id; }
    public int getIdRol() { return idRol; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    
    // Método auxiliar útil para interfaces
    public String getNombreCompleto() { 
        return nombres + " " + apellidos; 
    }
}