package Metodos_de_pago;

public class PagoEfectivo extends EstrategiaPago {
    
    @Override
    public void procesarPago(double total, String recibido) {
        double monto = Double.parseDouble(recibido);
        double vuelto = monto - total;
        System.out.println("💰 [STRATEGY] Pago en efectivo procesado. Vuelto: S/. " + vuelto);
    }

    @Override
    public boolean validar(String entrada, double total) {
        try {
            double monto = Double.parseDouble(entrada);
            return monto >= total; // El dinero entregado debe cubrir el total
        } catch (Exception e) {
            return false;
        }
    }
}