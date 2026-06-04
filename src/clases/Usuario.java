package clases;

public class Usuario {
    private int id;
    private String nombres;
    private String username;
    private String password;
    private int idRol;

    public Usuario() {}

    public Usuario(int id, String nombres, String username, String password, int idRol) {
        this.id = id;
        this.nombres = nombres;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }

    // Getters
    public int getId() { return id; }
    public String getNombres() { return nombres; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getIdRol() { return idRol; }
}