package Metodos_de_pago;

public class PagoTarjeta extends EstrategiaPago {
    private String tipo; // "Crédito" o "Débito"

    public PagoTarjeta(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void procesarPago(double total, String nroOperacion) {
        System.out.println("💳 [STRATEGY] Pago con " + tipo + ". Operación: " + nroOperacion);
    }

    @Override
    public boolean validar(String entrada, double total) {
        // Valida que el número de operación de la transacción tenga al menos 6 dígitos
        return entrada != null && entrada.trim().length() >= 6;
    }
}
