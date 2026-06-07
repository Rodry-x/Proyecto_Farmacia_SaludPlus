package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class VentaDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    public String obtenerSiguienteNumeroVenta() throws SQLException {
        String sql = "SELECT MAX(id_venta) AS ultimo_id FROM Ventas";
        int siguienteId = 1;

        try (Connection con = db.conectar()) {
            if (con == null) return "000001";
            
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    siguienteId = rs.getInt("ultimo_id") + 1;
                }
            }
        }
        return String.format("%06d", siguienteId);
    }

    public boolean insertarVenta(Venta v) throws SQLException {
        String sql = "INSERT INTO Ventas (id_usuario, id_cliente, id_metodo_pago, fecha_venta, total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = db.conectar()) {
            if (con == null) {
                throw new SQLException("No se pudo establecer conexión con la base de datos.");
            }
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, v.getIdUsuario());
                ps.setInt(2, v.getIdCliente());
                ps.setInt(3, v.getIdMetodoPago());
                // Conversión de fecha de Java a SQL
                ps.setDate(4, new java.sql.Date(v.getFechaVenta().getTime()));
                ps.setDouble(5, v.getTotal());
                
                return ps.executeUpdate() > 0;
            }
        }
    }
}