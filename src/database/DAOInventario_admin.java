/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import clases.entidad_producto_inventario;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manue
 */
public class DAOInventario_admin {
    database.ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
    public void ListarProductos(JTable TablaProductos, JTextField txt_busqueda){
      String busqueda = txt_busqueda.getText();
      objetoConexion.conectar();
      clases.entidad_producto_inventario producto = new entidad_producto_inventario();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Codigo producto");
      modelo.addColumn("Nombre");
      modelo.addColumn("Descripción");
      modelo.addColumn("Nombre Categoria");
      modelo.addColumn("Nombre proveedor");
      modelo.addColumn("Precio compra");
      modelo.addColumn("Precio venta");
      modelo.addColumn("Fecha de vencimiento");
      modelo.addColumn("Stock actual");
      modelo.addColumn("Stock minimo");
      
      TablaProductos.setModel(modelo);
      String consulta = "select Productos.codigo_producto, Productos.nombre, Productos.descripcion, Productos.id_categoria, Productos.id_proveedor, Categorias.nombre_categoria, Productos.precio_compra, Productos.precio_venta, Productos.fecha_vencimiento, Productos.stock_actual, Productos.stock_minimo,Proveedores.nombre_proveedor\n" +
        "From Productos\n" +
        "INNER JOIN Categorias on Productos.id_categoria = Categorias.id_categoria\n" +
        "INNER JOIN Proveedores on Productos.id_proveedor = Proveedores.id_proveedor\n" +
        "where Productos.codigo_producto like '%"+busqueda+"%'";
        try {
          Statement st = objetoConexion.conectar().createStatement();
          ResultSet rs = st.executeQuery(consulta);
          
          while(rs.next()){
              producto.setCodigo_producto(rs.getString("codigo_producto"));
              producto.setNombre(rs.getString("nombre"));
              producto.setDescripcion(rs.getString("descripcion"));
              producto.setId_categoria(rs.getInt("id_categoria"));
              producto.setId_nombre_proveedor(rs.getInt("id_proveedor"));
              producto.setPrecio_compra(rs.getDouble("precio_compra"));
              producto.setPrecio_venta(rs.getDouble("precio_venta"));
              producto.setFecha_vencimiento(rs.getDate("fecha_vencimiento"));
              producto.setStock_actual(rs.getInt("stock_actual"));
              producto.setStock_minimo(rs.getInt("stock_minimo"));
              modelo.addRow(new Object[]{producto.getCodigo_producto(),producto.getNombre(),producto.getDescripcion(),rs.getString("nombre_categoria"),rs.getString("nombre_proveedor"),producto.getPrecio_compra(),producto.getPrecio_venta(),producto.getFecha_vencimiento(),producto.getStock_actual(),producto.getStock_minimo()});          
          }
          TablaProductos.setModel(modelo);
        } 
        catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Error al guardar el producto, " + e.toString());
        }   
    }
    
    public void GuardarProductos(JTextField codigo_producto, JTextField nombre, JTextField descripcion, JTextField precio_compra, JTextField stock_ingresante, JTextField categoria_id, JTextField proveedor_id, JTextField precio_venta, JTextField stock_minimo, Date fecha_vencimiento){
      objetoConexion.conectar();
      clases.entidad_producto_inventario producto = new entidad_producto_inventario();
      String consulta = "insert into Productos (codigo_producto,nombre,descripcion,id_categoria,precio_compra,precio_venta,fecha_vencimiento,stock_actual,stock_minimo,id_proveedor) values (?,?,?,?,?,?,?,?,?,?);";
        try {
          producto.setCodigo_producto(codigo_producto.getText());
          producto.setNombre(nombre.getText());
          producto.setDescripcion(descripcion.getText());
          producto.setPrecio_compra(Double.parseDouble(precio_compra.getText()));
          producto.setStock_actual(Integer.parseInt(stock_ingresante.getText()));
          producto.setId_categoria(Integer.parseInt(categoria_id.getText()));
          producto.setId_nombre_proveedor(Integer.parseInt(proveedor_id.getText()));
          producto.setPrecio_venta(Double.parseDouble(precio_venta.getText()));
          producto.setStock_minimo(Integer.parseInt(stock_minimo.getText()));
          producto.setFecha_vencimiento(fecha_vencimiento);
          
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setString(1, producto.getCodigo_producto());
          cs.setString(2, producto.getNombre());
          cs.setString(3, producto.getDescripcion());
          cs.setInt(4, producto.getId_categoria());
          cs.setDouble(5, producto.getPrecio_compra());
          cs.setDouble(6, producto.getPrecio_venta());
          cs.setDate(7, producto.getFecha_vencimiento());
          cs.setInt(8, producto.getStock_actual());
          cs.setInt(9, producto.getStock_minimo());
          cs.setInt(10, producto.getId_nombre_proveedor());
          cs.execute();
          JOptionPane.showMessageDialog(null,"Producto guardado correctamente");
        } 
        catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Error al guardar el producto, " + e.toString());
        } 
    }
    public void ModificarProductos(JTextField codigo_producto, JTextField nombre, JTextField descripcion, JTextField precio_compra, JTextField stock_ingresante, JTextField categoria_id, JTextField proveedor_id, JTextField precio_venta, JTextField stock_minimo, Date fecha_vencimiento){
      objetoConexion.conectar();
      entidad_producto_inventario producto = new entidad_producto_inventario();
      String consulta = "UPDATE Productos SET Productos.nombre = ?, Productos.descripcion = ?, Productos.id_categoria = ?, Productos.precio_compra = ?, Productos.precio_venta = ?, Productos.fecha_vencimiento = ?, Productos.stock_actual = ?, Productos.stock_minimo = ?, Productos.id_proveedor = ? where Productos.codigo_producto = ?;";
        try {
          producto.setCodigo_producto(codigo_producto.getText());
          producto.setNombre(nombre.getText());
          producto.setDescripcion(descripcion.getText());
          producto.setPrecio_compra(Double.parseDouble(precio_compra.getText()));
          producto.setStock_actual(Integer.parseInt(stock_ingresante.getText()));
          producto.setId_categoria(Integer.parseInt(categoria_id.getText()));
          producto.setId_nombre_proveedor(Integer.parseInt(proveedor_id.getText()));
          producto.setPrecio_venta(Double.parseDouble(precio_venta.getText()));
          producto.setStock_minimo(Integer.parseInt(stock_minimo.getText()));
          producto.setFecha_vencimiento(fecha_vencimiento);
          
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          //cs.setString(1, producto.getCodigo_producto());
          cs.setString(1, producto.getNombre());
          cs.setString(2, producto.getDescripcion());
          cs.setInt(3, producto.getId_categoria());
          cs.setDouble(4, producto.getPrecio_compra());
          cs.setDouble(5, producto.getPrecio_venta());
          cs.setDate(6, producto.getFecha_vencimiento());
          cs.setInt(7, producto.getStock_actual());
          cs.setInt(8, producto.getStock_minimo());
          cs.setInt(9, producto.getId_nombre_proveedor());
          cs.setString(10, producto.getCodigo_producto());
          cs.execute();
          JOptionPane.showMessageDialog(null,"Producto modificado correctamente");
        } 
        catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Error al modificar el producto, " + e.toString());
        } 
    }
    public void EliminarProductos(String codigo_producto){
      objetoConexion.conectar();
      entidad_producto_inventario producto = new entidad_producto_inventario();
      String consulta = "DELETE FROM Productos where Productos.codigo_producto = ?";
        try {
          producto.setCodigo_producto(codigo_producto);
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setString(1, producto.getCodigo_producto());
          cs.execute();
          JOptionPane.showMessageDialog(null,"Producto ELIMINADO correctamente");
        } 
        catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Error al eliminar el producto, " + e.toString());
        } 
    }
    
    
    public void MostrarProveedoresCombo(JComboBox combo_proveedores){
       String sql = "";
       sql = "select * from Proveedores";
       Statement st;
       try{
           st = objetoConexion.conectar().createStatement();
           ResultSet rs = st.executeQuery(sql);
           combo_proveedores.removeAllItems();
           while(rs.next()){
               combo_proveedores.addItem(rs.getString("nombre_proveedor")); 
           }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, "Error al mostrar" + e.toString());
       }
        
    }
    public void MostrarIdPorProveedores(JComboBox combo_proveedores, JTextField id_proveedores){
        String consulta = "Select Proveedores.id_proveedor from Proveedores where Proveedores.nombre_proveedor =?";
        try{
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setString(1,combo_proveedores.getSelectedItem().toString()); 
            cs.execute();
            
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                id_proveedores.setText(rs.getString("id_proveedor"));
            }
        }
        catch(Exception e){
              JOptionPane.showMessageDialog(null, "Error al mostrar" + e.toString());
        }
    }
    public void MostrarCategoriasCombo (JComboBox combo_categorias){
       String sql = "";
       sql = "select * from Categorias";
       Statement st;
       try{
           st = objetoConexion.conectar().createStatement();
           ResultSet rs = st.executeQuery(sql);
           combo_categorias.removeAllItems();
           while(rs.next()){
               combo_categorias.addItem(rs.getString("nombre_categoria")); 
           }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, "Error al mostrar" + e.toString());
       }
    }
    public void MostrarIdPorCategorias(JComboBox combo_categorias, JTextField id_categorias){
        String consulta = "Select Categorias.id_categoria from Categorias where Categorias.nombre_categoria =?";
        try{
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setString(1,combo_categorias.getSelectedItem().toString()); 
            cs.execute();
            
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                id_categorias.setText(rs.getString("id_categoria"));
            }
        }
        catch(Exception e){
              JOptionPane.showMessageDialog(null, "Error al mostrar" + e.toString());
        }
    }
}
