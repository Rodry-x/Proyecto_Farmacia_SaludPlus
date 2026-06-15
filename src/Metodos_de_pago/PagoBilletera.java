package Metodos_de_pago;

public class PagoBilletera extends EstrategiaPago {

    @Override
    public void procesarPago(double total, String celular) {
        System.out.println("📱 [STRATEGY] Pago Digital con billetera recibido del celular: " + celular);
    }

    @Override
    public boolean validar(String entrada, double total) {
        // Valida que sea un número de celular peruano estándar (9 dígitos)
        return entrada != null && entrada.matches("9\\d{8}");
    }
}