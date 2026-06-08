package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class VentaDAO {
    
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    public boolean insertarVenta(Venta v) {
        // Incluimos numero_venta, que es requerido por tu tabla
        String sql = "INSERT INTO Ventas (numero_venta, id_usuario, id_cliente, id_metodo_pago, fecha_venta, total) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = db.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, v.getNumeroVenta());
            ps.setInt(2, v.getIdUsuario());
            ps.setInt(3, v.getIdCliente());
            ps.setInt(4, v.getIdMetodoPago());
            // Usamos Timestamp para manejar correctamente el DATETIME de SQL Server
            ps.setTimestamp(5, new java.sql.Timestamp(v.getFechaVenta().getTime()));
            ps.setDouble(6, v.getTotal());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    public String obtenerSiguienteNumeroVenta() {
        // Buscamos el último registro para generar un correlativo
        String sql = "SELECT TOP 1 numero_venta FROM Ventas ORDER BY id_venta DESC";
        
        try (Connection con = db.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                // Asumiendo formato V00001, extraemos el número y sumamos 1
                String ultimo = rs.getString("numero_venta");
                int num = Integer.parseInt(ultimo.substring(1)) + 1;
                return String.format("V%05d", num);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al generar número de venta: " + e.getMessage());
        }
        return "V00001"; // Valor por defecto inicial
    }
}