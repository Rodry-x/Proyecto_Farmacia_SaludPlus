package model;

import java.util.ArrayList;
import java.util.List;

public class entidad_proveedor_inventario {
    private int id_proveedor;
    private String ruc;
    private String nombre_proveedor;
    private String direccion;
    private List<String> telefonos;
    private List<String> correos;

    public entidad_proveedor_inventario() {
        this.telefonos = new ArrayList<>();
        this.correos = new ArrayList<>();
    }

    // Getters y Setters
    public int getId_proveedor() { return id_proveedor; }
    public void setId_proveedor(int id_proveedor) { this.id_proveedor = id_proveedor; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getNombre_proveedor() { return nombre_proveedor; }
    public void setNombre_proveedor(String nombre_proveedor) { this.nombre_proveedor = nombre_proveedor; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<String> getTelefonos() { return telefonos; }
    public void setTelefonos(List<String> telefonos) { this.telefonos = telefonos; }

    public List<String> getCorreos() { return correos; }
    public void setCorreos(List<String> correos) { this.correos = correos; }

    @Override
    public String toString() {
        return nombre_proveedor;
    }
}