package model;

public class MetodoPago {
    private int id_metodopago;
    private String nombre;

    public MetodoPago() {}

    public MetodoPago(int id_metodopago, String nombre) {
        this.id_metodopago = id_metodopago;
        this.nombre = nombre;
    }

    public int getId_metodopago() { return id_metodopago; }
    public void setId_metodopago(int id_metodopago) { this.id_metodopago = id_metodopago; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
