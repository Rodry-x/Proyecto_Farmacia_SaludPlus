/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import clases.entidad_categoria_inventario;
import clases.entidad_impuesto_inventario;
import clases.entidad_producto_inventario;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
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
      
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Producto");
      modelo.addColumn("Nombre");
      modelo.addColumn("Descripción");
      modelo.addColumn("Nombre Categoria");
      modelo.addColumn("Stock minimo");
      modelo.addColumn("Stock general");
      modelo.addColumn("Nombre Impuesto");
      modelo.addColumn("Porcentaje");
      modelo.addColumn("Precio venta");
      TablaProductos.setModel(modelo);
      String consulta = "select Productos.id_producto,PRODUCTOS.nombre, Productos.descripcion,Productos.id_categoria,CATEGORIAS.nombre_categoria,PRODUCTOS.stock_minimo, PRODUCTOS.stock_general, Productos.id_impuesto,IMPUESTOS.nombre_impuesto, IMPUESTOS.porcentaje, PRODUCTOS.precio_venta\n" +
"From PRODUCTOS INNER JOIN CATEGORIAS on PRODUCTOS.id_categoria = CATEGORIAS.id_categoria INNER Join IMPUESTOS on PRODUCTOS.id_impuesto = IMPUESTOS.id_impuesto where Productos.id_producto like '%"+busqueda+"%'";
        try {
          Statement st = objetoConexion.conectar().createStatement();
          ResultSet rs = st.executeQuery(consulta);
          while(rs.next()){
              clases.entidad_producto_inventario producto = new entidad_producto_inventario();
              producto.setId_producto(rs.getInt("id_producto"));
              producto.setNombre(rs.getString("nombre"));
              producto.setDescripcion(rs.getString("descripcion"));
              producto.setId_categoria(rs.getInt("id_categoria"));
              producto.setNombre_categoria(rs.getString("nombre_categoria"));
              producto.setStock_minimo(rs.getInt("stock_minimo"));
              producto.setStock_general(rs.getInt("stock_general"));
              producto.setId_impuesto(rs.getInt("id_impuesto"));
              producto.setNombre_impuesto(rs.getString("nombre_impuesto"));
              producto.setPorcentaje_impuesto(rs.getDouble("porcentaje"));
              producto.setPrecio_venta(rs.getDouble("precio_venta"));
              modelo.addRow(new Object[]{producto.getId_producto(),producto.getNombre(),producto.getDescripcion(),producto.getNombre_categoria(),producto.getStock_minimo(),producto.getStock_general(),producto.getNombre_impuesto(),producto.getPorcentaje_impuesto(),producto.getPrecio_venta()});          
          }
          TablaProductos.setModel(modelo);
        } 
        catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Error al guardar el producto, " + e.toString());
        }
    }
    public void ListarCategorias(JComboBox <entidad_categoria_inventario> combo_categorias){
      objetoConexion.conectar();
      String sql = "";
      sql = "EXEC LLamar_categorias";
      Statement st;
        try {
          st = objetoConexion.conectar().createStatement();
          ResultSet rs = st.executeQuery(sql);
          combo_categorias.removeAllItems();
          while(rs.next()){
          clases.entidad_categoria_inventario categorias = new entidad_categoria_inventario();
          categorias.setId_categoria(rs.getInt("id_categoria"));
          categorias.setNombre_categoria(rs.getString("nombre_categoria"));
          combo_categorias.addItem(categorias);
          }
        } 
        catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Error al llamar las tablas involucradas, " + e.toString());
        }   
    }
    public void ListarImpuestos(JComboBox <entidad_impuesto_inventario> combo_impuesto){
      objetoConexion.conectar();
      String sql = "";
      sql = "EXEC llamar_impuesto";
      Statement st;
        try {
          st = objetoConexion.conectar().createStatement();
          ResultSet rs = st.executeQuery(sql);
          combo_impuesto.removeAllItems();
          while(rs.next()){
          clases.entidad_impuesto_inventario impuesto = new entidad_impuesto_inventario();
          impuesto.setId_impuesto(rs.getInt("id_impuesto"));
          impuesto.setNombre_impuesto(rs.getString("nombre_impuesto"));
          impuesto.setPorcentaje(rs.getDouble("porcentaje"));
          combo_impuesto.addItem(impuesto);
          }
        } 
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error al llamar las tablas involucradas, " + e.toString());
        }   
    }
    public void GuardarProductos(JTextField nombre, JTextField descripcion,JComboBox <entidad_categoria_inventario> combo_categorias,JComboBox <entidad_impuesto_inventario> combo_impuesto, JTextField precio_venta){
        clases.entidad_producto_inventario producto = new entidad_producto_inventario();
        String consulta = "{call guardar_producto(?, ?, ?, ?, ?)}";
        try{
            producto.setNombre(nombre.getText());
            producto.setDescripcion(descripcion.getText());
            entidad_categoria_inventario categoria = (entidad_categoria_inventario)combo_categorias.getSelectedItem();
            producto.setId_categoria(categoria.getId_categoria());
            entidad_impuesto_inventario impuesto = (entidad_impuesto_inventario)combo_impuesto.getSelectedItem();
            producto.setId_impuesto(impuesto.getId_impuesto());
            producto.setPrecio_venta(Double.parseDouble(precio_venta.getText()));
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setInt(3, producto.getId_categoria());
            cs.setInt(4, producto.getId_impuesto());
            cs.setDouble(5, producto.getPrecio_venta());
            cs.execute();
            JOptionPane.showMessageDialog(null,"Producto guardado correctamente");
        }
        catch (NumberFormatException e) {
        // Captura específicamente si el usuario escribió letras en el precio
        JOptionPane.showMessageDialog(null, "Error: El precio de venta debe ser un número válido.");

        } catch (SQLException e) {
        // Captura específicamente errores de la base de datos (conexión, duplicados, etc.)
        JOptionPane.showMessageDialog(null, "Error de base de datos al guardar: " + e.getMessage());

        } catch (Exception e) {
        // Captura cualquier otro error inesperado (como un NullPointerException)
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.toString());
        }    
    }
    public void Seleccionar(JTable totalproductos, JTextField nombre, JTextField descripcion, JTextField stock_minimo, JComboBox <entidad_categoria_inventario> combo_categorias,JComboBox <entidad_impuesto_inventario> combo_impuesto, JTextField precio_venta){
     int fila = totalproductos.getSelectedRow();
     try{
         if (fila>=0){
             nombre.setText(totalproductos.getValueAt(fila, 1).toString());
             descripcion.setText(totalproductos.getValueAt(fila, 2).toString());
             String nombreCategoria = totalproductos.getValueAt(fila, 3).toString();
             String nombreImpuesto = totalproductos.getValueAt(fila, 6).toString();
             
             for (int i = 0; i < combo_categorias.getItemCount(); i++) {
                 entidad_categoria_inventario categoria = combo_categorias.getItemAt(i);
                 if (categoria.getNombre_categoria().equals(nombreCategoria)) {
                     combo_categorias.setSelectedIndex(i);
                     break;
                  }
            }  
             for (int i = 0; i < combo_impuesto.getItemCount(); i++) {
                 entidad_impuesto_inventario impuesto = combo_impuesto.getItemAt(i);
                 if (impuesto.getNombre_impuesto().equals(nombreImpuesto)) {
                     combo_impuesto.setSelectedIndex(i);
                     break;
                   }
            }
            stock_minimo.setText(totalproductos.getValueAt(fila, 4).toString());
            stock_minimo.setEditable(true);
            precio_venta.setText(totalproductos.getValueAt(fila, 8).toString());
         }
     }
     catch(Exception e){ 
         JOptionPane.showMessageDialog(null,"Error al seleccionar el registro" + e.toString());
     }
    }
    public void ModificarProducto(String id, JTextField nombre, JTextField descripcion, JTextField stock_minimo, JComboBox <entidad_categoria_inventario> combo_categorias,JComboBox <entidad_impuesto_inventario> combo_impuesto, JTextField precio_venta){
        ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
        entidad_producto_inventario producto = new entidad_producto_inventario();
        String consulta = "{call modificar_producto(?,?,?,?,?,?,?)}";
        try{
           producto.setId_producto(Integer.parseInt(id));
           producto.setNombre(nombre.getText());
           producto.setDescripcion(descripcion.getText());
           producto.setStock_minimo(Integer.parseInt(stock_minimo.getText()));
           entidad_categoria_inventario categoria = (entidad_categoria_inventario)combo_categorias.getSelectedItem();
           producto.setId_categoria(categoria.getId_categoria());
           entidad_impuesto_inventario impuesto = (entidad_impuesto_inventario)combo_impuesto.getSelectedItem();
           producto.setId_impuesto(impuesto.getId_impuesto());      
           producto.setPrecio_venta(Double.parseDouble(precio_venta.getText()));
           //////////////////////////////////////////////////////////////////////////////////
           CallableStatement cs = objetoConexion.conectar().prepareCall(consulta); 
           cs.setInt(1, producto.getId_producto());
           cs.setString(2, producto.getNombre());
           cs.setString(3, producto.getDescripcion());
           cs.setInt(4, producto.getId_categoria());
           cs.setInt(5, producto.getStock_minimo());
           cs.setInt(6, producto.getId_impuesto());
           cs.setDouble(7, producto.getPrecio_venta());
           cs.execute();
           JOptionPane.showMessageDialog(null,"Producto guardado correctamente");
        }
        catch (NumberFormatException e) {
        // Captura específicamente si el usuario escribió letras en el precio
        JOptionPane.showMessageDialog(null, "Error: El precio de venta debe ser un número válido.");

        } catch (SQLException e) {
        // Captura específicamente errores de la base de datos (conexión, duplicados, etc.)
        JOptionPane.showMessageDialog(null, "Error de base de datos al modificar: " + e.getMessage());

        } catch (Exception e) {
        // Captura cualquier otro error inesperado (como un NullPointerException)
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.toString());
        } 
        
    }
    public void EliminarProducto(String id){
        ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
        entidad_producto_inventario producto = new entidad_producto_inventario();
        String consulta = "{call eliminar_producto(?)}";
        try{
          producto.setId_producto(Integer.parseInt(id));
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setInt(1, producto.getId_producto());
          cs.execute();
          JOptionPane.showMessageDialog(null,"Producto eliminado correctamente");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de base de datos al eliminar: " + e.getMessage());
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
