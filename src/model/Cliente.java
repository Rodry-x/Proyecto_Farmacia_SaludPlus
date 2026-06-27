package model;

import java.time.LocalDate;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String dni;
    private int id_genero;
    private LocalDate fecha_nacimiento;

    public Cliente() {}

    public Cliente(int id_cliente, String nombre, String apellido, String dni,
                   int id_genero, LocalDate fecha_nacimiento) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.id_genero = id_genero;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public int getId_genero() { return id_genero; }
    public void setId_genero(int id_genero) { this.id_genero = id_genero; }

    public LocalDate getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
