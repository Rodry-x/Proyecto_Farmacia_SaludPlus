
package Metodos_de_pago;


public class PagoBilletera extends EstrategiaPago {
    
    public void procesarPago(double total, String celular) {
        System.out.println("Pago Digital recibido de: " + celular);
    }
    public boolean validar(String entrada) { return entrada.length() == 9; }
}