
package clases;

public class ClienteObjeto {
   
    public int idCliente;
    public String dniRuc;
    public String nombreCompleto;
    public String telefono;
    public String correo;

    // Constructor por defecto (Cliente genérico para cuando no quieren dar datos)
    public ClienteObjeto() {
        this.idCliente = 0; 
        this.dniRuc = "11111111";
        this.nombreCompleto = "PÚBLICO GENERAL";
        this.telefono = "";
        this.correo = "";
    }
}
