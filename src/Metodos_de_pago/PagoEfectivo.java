
package Metodos_de_pago;

public class PagoEfectivo {
   
    public void procesarPago(double total, String recibido){
        double monto = Double.parseDouble(recibido);
        double vuelto = monto - total;
        System.out.println("Pago en efectivo. vuelvo: " + vuelto);
    }
    public boolean validar (String entrada){
        try{return Double.parseDouble(entrada) >= 0;} catch (Exception e ) {return false;}
    }

}
