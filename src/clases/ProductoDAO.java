package clases;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final ConectarBaseDatos db = new ConectarBaseDatos();

    // 1. MÉTODO PARA EL ADMINISTRADOR (Carga todos los campos)
    public List<Producto> obtenerCatalogo(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.codigo_producto, p.nombre, p.descripcion, c.nombre_categoria, " +
                     "prov.nombre_proveedor, p.precio_venta, p.stock_actual, p.stock_minimo, " +
                     "p.fecha_vencimiento, p.precio_compra " + // Se añadió precio_compra
                     "FROM Productos p " +
                     "INNER JOIN Categorias c ON p.id_categoria = c.id_categoria " +
                     "INNER JOIN Proveedores prov ON p.id_proveedor = prov.id_proveedor";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            sql += " WHERE p.nombre LIKE ? OR p.codigo_producto LIKE ? OR p.descripcion LIKE ?";
        }

        try (Connection con = db.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (filtro != null && !filtro.trim().isEmpty()) {
                String busqueda = "%" + filtro.trim() + "%";
                ps.setString(1, busqueda);
                ps.setString(2, busqueda);
                ps.setString(3, busqueda);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener catálogo completo: " + e.getMessage());
        }
        return lista;
    }

    // 2. MÉTODO PARA EL ESCÁNER DEL CAJERO
    public Producto buscarPorCodigoExacto(String codigo) {
        String sql = "SELECT codigo_producto, nombre, precio_venta FROM Productos WHERE codigo_producto = ?";
        try (Connection con = db.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setCodigo(rs.getString("codigo_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecioVenta(rs.getDouble("precio_venta")); // Uso correcto del setter
                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error en búsqueda exacta: " + e.getMessage());
        }
        return null;
    }

  // 3. MÉTODO PARA SUGERENCIAS DEL CAJERO (Optimizado para SQL Server)
public List<Producto> obtenerSugerenciasParaCajero(String filtro) {
    List<Producto> lista = new ArrayList<>();
    // Cambiamos LIMIT 10 por SELECT TOP 10
    String sql = "SELECT TOP 10 codigo_producto, nombre, precio_venta FROM Productos WHERE nombre LIKE ?";
    
    try (Connection con = db.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + filtro.trim() + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("codigo_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                lista.add(p);
            }
        }
    } catch (SQLException e) {
        System.err.println("❌ Error en sugerencias: " + e.getMessage());
    }
    return lista;
}

    // Método auxiliar para mapear el objeto completo (Admin)
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getString("codigo_producto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("nombre_categoria"),
            rs.getString("nombre_proveedor"),
            rs.getDouble("precio_venta"),
            rs.getInt("stock_actual"),
            rs.getInt("stock_minimo"),
            rs.getString("fecha_vencimiento"),
            rs.getDouble("precio_compra") // 10º parámetro coincidiendo con la clase Producto
        );
    }
}
