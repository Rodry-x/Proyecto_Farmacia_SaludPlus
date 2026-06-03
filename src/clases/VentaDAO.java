package clases;

import database.ConectarBaseDatos;
import java.sql.*;

public class VentaDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    // 🎫 Método para obtener el siguiente número correlativo desde Azure
    public String obtenerSiguienteNumeroVenta() throws SQLException {
        String sql = "SELECT MAX(id_venta) AS ultimo_id FROM Ventas";
        int siguienteId = 1;

        try (Connection con = db.conectar()) {
            if (con == null) return "000001";
            
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    int ultimoId = rs.getInt("ultimo_id");
                    siguienteId = ultimoId + 1;
                }
            }
        }
        // Formatea el número con ceros a la izquierda (Ej: 000005)
        return String.format("%06d", siguienteId);
    }
}
