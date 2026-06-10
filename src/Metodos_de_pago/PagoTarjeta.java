
package Metodos_de_pago;


public class PagoTarjeta extends EstrategiaPago{
    
    private String tipo;
    public PagoTarjeta(String tipo) {this.tipo = tipo;}
    
public void procesarPago(double total, String nroOperacion) {
        System.out.println("Pago con " + tipo + ". Operación: " + nroOperacion);
    }
    public boolean validar(String entrada) { return entrada.length() > 5; }
}
