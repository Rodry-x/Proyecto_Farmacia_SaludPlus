package clases;

import clases.Producto;
import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    public List<Producto> obtenerCatalogo(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String filtroLimpio = (filtro == null) ? "" : filtro.trim();
        
        // SQL ajustado a los campos que me indicaste
        String sql = "SELECT p.codigo_producto, p.nombre, p.descripcion, c.nombre_categoria, " +
                     "p.precio_venta, p.stock_actual, p.stock_minimo, p.fecha_vencimiento " +
                     "FROM Productos p " +
                     "INNER JOIN Categorias c ON p.id_categoria = c.id_categoria";
        
        boolean tieneFiltro = !filtroLimpio.isEmpty();
        if (tieneFiltro) {
            // Buscamos en Nombre, Código o Descripción
            sql += " WHERE p.nombre LIKE ? OR p.codigo_producto LIKE ? OR p.descripcion LIKE ?";
        }

        try (Connection con = db.conectar()) {
            if (con == null) return lista;
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                if (tieneFiltro) {
                    String busqueda = "%" + filtroLimpio + "%";
                    ps.setString(1, busqueda); // Nombre
                    ps.setString(2, busqueda); // Código
                    ps.setString(3, busqueda); // Descripción
                }
                
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Producto p = new Producto(
                            rs.getString("codigo_producto"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("nombre_categoria"),
                            rs.getDouble("precio_venta"),
                            rs.getInt("stock_actual"),
                            rs.getInt("stock_minimo"),
                            rs.getString("fecha_vencimiento")
                        );
                        lista.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error en ProductoDAO: " + e.getMessage());
        }
        return lista;
    }
}