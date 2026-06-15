package Metodos_de_pago;

public abstract class EstrategiaPago {
    public abstract void procesarPago(double total, String datoExtra);
    public abstract boolean validar(String entrada, double total);  
}