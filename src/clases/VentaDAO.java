package clases;

import Pantallas_Inicio_Cajero.FilaCarrito;
import database.ConectarBaseDatos;
import java.sql.*;
import java.util.List;

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
    
    public boolean guardarVentaCompleta(Venta v, List<FilaCarrito> listaProductos) {
    String sqlVenta = "INSERT INTO Ventas (numero_venta, id_usuario, id_cliente, id_metodo_pago, total) VALUES (?, ?, ?, ?, ?)";
    String sqlDetalle = "INSERT INTO Detalle_Venta (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

    try (Connection con = db.conectar()) {
        con.setAutoCommit(false); // Iniciamos transacción

        // 1. Guardar Venta
        int idVentaGenerado = 0;
        try (PreparedStatement ps = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getNumeroVenta());
            ps.setInt(2, v.getIdUsuario());
            ps.setInt(3, v.getIdCliente());
            ps.setInt(4, v.getIdMetodoPago());
            ps.setDouble(5, v.getTotal());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                }
            }
        }

        // 2. Guardar Detalles (¡IMPORTANTE: Usar el idVentaGenerado!)
    if (idVentaGenerado > 0) {
        try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
            for (FilaCarrito p : listaProductos) {
                
                // --- CÓDIGO DE DEPURACIÓN ---
                System.out.println("DEBUG: Guardando detalle para Producto ID: " + p.getIdProducto() 
                                   + " | Cantidad: " + p.getCantidad() 
                                   + " | Precio: " + p.getPrecioUnitario());
                
                if (p.getIdProducto() <= 0) {
                    System.err.println("❌ ERROR CRÍTICO: El producto '" + p.getNombreProducto() + "' tiene ID 0 o negativo.");
                }
                // ---------------------------

                psDetalle.setInt(1, idVentaGenerado); 
                psDetalle.setInt(2, p.getIdProducto());
                psDetalle.setInt(3, p.getCantidad());
                psDetalle.setDouble(4, p.getPrecioUnitario());
                psDetalle.executeUpdate();
            }
        }
        con.commit(); 
        return true;
        } else {
            con.rollback(); // Si no hay ID, cancelamos
            return false;
        }
    } catch (SQLException e) {
        System.err.println("❌ ERROR: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
  }
}