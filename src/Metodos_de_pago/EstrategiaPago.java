package Metodos_de_pago;

public abstract class EstrategiaPago {
    // Definimos los métodos como abstractos para que las subclases los completen
    public abstract void procesarPago(double total, String datoExtra);
    public abstract boolean validar(String entrada);  
}