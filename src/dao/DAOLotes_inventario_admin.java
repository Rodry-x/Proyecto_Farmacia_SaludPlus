/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.toedter.calendar.JDateChooser;
import model.entidad_lotes_inventario;
import database.ConectarBaseDatos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.entidad_producto_inventario;
import java.sql.Statement;
import model.Registro_movimiento_lotes;
import model.acción_registro_movimiento_lotesinventario;
import model.entidad_proveedor_inventario;

/**
 *
 * @author manue
 */
public class DAOLotes_inventario_admin {
    public void Listarlotes(JTable tabla_lotes){
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "select * from REPORTE_LOTES";
      try{
      Statement st = objetoConexion.conectar().createStatement();
      ResultSet rs = st.executeQuery(consulta);
      while(rs.next()){
          entidad_lotes_inventario lote = new entidad_lotes_inventario();
          lote.setId_lote(rs.getInt("ID Lote"));
          lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
          lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
          lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
          lote.setStock_actual(rs.getInt("Stock Actual"));
          lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
          lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
          lote.setDias_vencimiento(rs.getInt("Dias para Vencer"));
          lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
          lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
          lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
          lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
          modelo.addRow(new Object[]{lote.getId_lote(),lote.getProducto_lote(),lote.getId_producto_lote(),lote.getStock_entrante_lote(),lote.getStock_actual(),lote.getFecha_ingreso(),lote.getFecha_vencimiento(),lote.getDias_vencimiento(),lote.getNombre_proveedor_lote(),lote.getId_proveedor_lote(),lote.getId_compra_asociada(),lote.getUsuario_responsable()});
        }
      }
      catch(SQLException e){
         JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void Listarlotes_díasvencimiento(JTable tabla_lotes, JTextField días_vencimiento){
      int dias = Integer.parseInt(días_vencimiento.getText());
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "{call REPORTE_DE_LOTE_PROXIMOS_A_VENCER_SEGUN_DIA(?)}";
      try{
          CallableStatement cs = ConectarBaseDatos.conectar().prepareCall(consulta);
          cs.setInt(1, dias);
          
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
            entidad_lotes_inventario lote = new entidad_lotes_inventario();
            
            lote.setId_lote(rs.getInt("ID Lote"));
            lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
            lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
            lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
            lote.setStock_actual(rs.getInt("Stock Actual"));
            lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
            lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
            lote.setDias_vencimiento(rs.getInt("Dias para Vencer")); // Recuerda usar minúsculas si así quedó en tu vista final
            lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
            lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
            lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
            lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
            
            modelo.addRow(new Object[]{
                lote.getId_lote(),
                lote.getProducto_lote(),
                lote.getId_producto_lote(),
                lote.getStock_entrante_lote(),
                lote.getStock_actual(),
                lote.getFecha_ingreso(),
                lote.getFecha_vencimiento(),
                lote.getDias_vencimiento(),
                lote.getNombre_proveedor_lote(),
                lote.getId_proveedor_lote(),
                lote.getId_compra_asociada(),
                lote.getUsuario_responsable()
            });
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void llamar_productos(JComboBox <entidad_producto_inventario> combo_producto){
      database.ConectarBaseDatos conectar = new ConectarBaseDatos();
      String sql = "exec LLamar_producto";
      Statement st;
        try {
          st = conectar.conectar().createStatement();
          ResultSet rs = st.executeQuery(sql);
          combo_producto.removeAllItems();
          while(rs.next()){
          model.entidad_producto_inventario producto = new entidad_producto_inventario();
          producto.setId_producto(rs.getInt("id_producto"));
          producto.setNombre(rs.getString("nombre"));
          combo_producto.addItem(producto);
          }
        } 
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error al llamar las tablas involucradas, " + e.toString());
        }   
    }
    public void llamar_proveedores(JComboBox <entidad_proveedor_inventario> combo_proveedor){
      database.ConectarBaseDatos conectar = new ConectarBaseDatos();
      String sql = "exec LLamar_proveedor";
      Statement st;
        try {
          st = conectar.conectar().createStatement();
          ResultSet rs = st.executeQuery(sql);
          combo_proveedor.removeAllItems();
          while(rs.next()){
          entidad_proveedor_inventario proveedor = new entidad_proveedor_inventario();
          proveedor.setId_proveedor(rs.getInt("id_proveedor"));
          proveedor.setNombre_proveedor(rs.getString("nombre"));
          combo_proveedor.addItem(proveedor);
          }
        } 
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error al llamar las tablas involucradas, " + e.toString());
        } 
    }
    public void SeleccionarLote(JTable tabla_lotes, JTextField id_lote, JTextField stock_actual){
        int fila = tabla_lotes.getSelectedRow();
        try{
            if(fila>=0){
                id_lote.setText(tabla_lotes.getValueAt(fila, 0).toString());
                stock_actual.setText(tabla_lotes.getValueAt(fila, 4).toString());
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al seleccionar" + e.toString());
        }
    }
    public void Modificar_stock_lote(JTextField id_lote, JTextField stock_anterior, JTextField stock_nuevo, int id_usuario){
        ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
        entidad_lotes_inventario lotes = new entidad_lotes_inventario();
        String consulta = "{call Modificar_stock(?,?,?,?)}";
        try{
           lotes.setId_lote(Integer.parseInt(id_lote.getText().trim()));
           lotes.setStock_actual(Integer.parseInt(stock_nuevo.getText().trim()));
           //////////////////////////////////////////////////////////////////////////////////
           CallableStatement cs = objetoConexion.conectar().prepareCall(consulta); 
           cs.setInt(1, lotes.getId_lote());
           cs.setInt(2, Integer.parseInt(stock_anterior.getText()));
           cs.setInt(3, lotes.getStock_actual());
           cs.setInt(4, id_usuario);
           cs.execute();
           JOptionPane.showMessageDialog(null,"Lote modificado correctamente y stock actualizado");
        }
        catch (NumberFormatException e) {
        // Captura específicamente si el usuario escribió letras en el precio
        JOptionPane.showMessageDialog(null, "Error: El stock debe ser un número válido.");
        } catch (SQLException e) {
        // Captura específicamente errores de la base de datos (conexión, duplicados, etc.)
        JOptionPane.showMessageDialog(null, "Error de base de datos al modificar: " + e.getMessage());
        } catch (Exception e) {
        // Captura cualquier otro error inesperado (como un NullPointerException)
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.toString());
        } 
    }
    public void Eliminar_lote(String id, JTextField stock_actual, int id_usuario){
        ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
        entidad_lotes_inventario lote = new entidad_lotes_inventario();
        String consulta = "{call Eliminar_lote(?,?,?)}";
        try{
          lote.setId_lote(Integer.parseInt(id));
          lote.setStock_actual(Integer.parseInt(stock_actual.getText()));
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setInt(1, lote.getId_lote());
          cs.setInt(2, lote.getStock_actual());
          cs.setInt(3, id_usuario);
          cs.execute();
          JOptionPane.showMessageDialog(null,"Lote eliminado correctamente");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de base de datos al eliminar: " + e.getMessage());
        }
    }
    public void ListarLotes_x_producto(JTable tabla_lotes, int id_producto){
      int id = id_producto;
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "{call buscarlotes_x_producto(?)}";
      try{
          CallableStatement cs = ConectarBaseDatos.conectar().prepareCall(consulta);
          cs.setInt(1, id);
          
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
            entidad_lotes_inventario lote = new entidad_lotes_inventario();
            
            lote.setId_lote(rs.getInt("ID Lote"));
            lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
            lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
            lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
            lote.setStock_actual(rs.getInt("Stock Actual"));
            lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
            lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
            lote.setDias_vencimiento(rs.getInt("Dias para Vencer")); // Recuerda usar minúsculas si así quedó en tu vista final
            lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
            lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
            lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
            lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
            
            modelo.addRow(new Object[]{
                lote.getId_lote(),
                lote.getProducto_lote(),
                lote.getId_producto_lote(),
                lote.getStock_entrante_lote(),
                lote.getStock_actual(),
                lote.getFecha_ingreso(),
                lote.getFecha_vencimiento(),
                lote.getDias_vencimiento(),
                lote.getNombre_proveedor_lote(),
                lote.getId_proveedor_lote(),
                lote.getId_compra_asociada(),
                lote.getUsuario_responsable()
            });
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void ListarLotes_x_proveedor(JTable tabla_lotes, int id_proveedor){
      int id = id_proveedor;
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "{call buscarlotes_x_proveedor(?)}";
      try{
          CallableStatement cs = ConectarBaseDatos.conectar().prepareCall(consulta);
          cs.setInt(1, id);    
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
            entidad_lotes_inventario lote = new entidad_lotes_inventario();  
            lote.setId_lote(rs.getInt("ID Lote"));
            lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
            lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
            lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
            lote.setStock_actual(rs.getInt("Stock Actual"));
            lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
            lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
            lote.setDias_vencimiento(rs.getInt("Dias para Vencer")); // Recuerda usar minúsculas si así quedó en tu vista final
            lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
            lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
            lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
            lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
            
            modelo.addRow(new Object[]{
                lote.getId_lote(),
                lote.getProducto_lote(),
                lote.getId_producto_lote(),
                lote.getStock_entrante_lote(),
                lote.getStock_actual(),
                lote.getFecha_ingreso(),
                lote.getFecha_vencimiento(),
                lote.getDias_vencimiento(),
                lote.getNombre_proveedor_lote(),
                lote.getId_proveedor_lote(),
                lote.getId_compra_asociada(),
                lote.getUsuario_responsable()
            });
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void ListarLotes_x_producto_Y_proveedor(JTable tabla_lotes, int id_producto, int id_proveedor){
      int id_prod = id_producto;
      int id_prov = id_proveedor;
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "{call buscarlotes_x_producto_y_proveedor(?,?)}";
      try{
          CallableStatement cs = ConectarBaseDatos.conectar().prepareCall(consulta);
          cs.setInt(1, id_prod);
          cs.setInt(2, id_prov);
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
            entidad_lotes_inventario lote = new entidad_lotes_inventario();  
            lote.setId_lote(rs.getInt("ID Lote"));
            lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
            lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
            lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
            lote.setStock_actual(rs.getInt("Stock Actual"));
            lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
            lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
            lote.setDias_vencimiento(rs.getInt("Dias para Vencer")); // Recuerda usar minúsculas si así quedó en tu vista final
            lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
            lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
            lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
            lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
            
            modelo.addRow(new Object[]{
                lote.getId_lote(),
                lote.getProducto_lote(),
                lote.getId_producto_lote(),
                lote.getStock_entrante_lote(),
                lote.getStock_actual(),
                lote.getFecha_ingreso(),
                lote.getFecha_vencimiento(),
                lote.getDias_vencimiento(),
                lote.getNombre_proveedor_lote(),
                lote.getId_proveedor_lote(),
                lote.getId_compra_asociada(),
                lote.getUsuario_responsable()
            });
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void ListarLotes_Vencidos(JTable tabla_lotes){
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("Id_Lote");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("ID Producto");
      modelo.addColumn("Stock Entrante");
      modelo.addColumn("Stock Actual");
      modelo.addColumn("Fecha de Ingreso");
      modelo.addColumn("Fecha de Vencimiento");
      modelo.addColumn("Dias para vencer");
      modelo.addColumn("Proveedor Asociado");
      modelo.addColumn("ID Proveedor");
      modelo.addColumn("ID Compra Asociada");
      modelo.addColumn("Usuario Responsable");
      tabla_lotes.setModel(modelo);
      String consulta = "{call buscarlotes_vencidos}";
      try{
      Statement st = objetoConexion.conectar().createStatement();
      ResultSet rs = st.executeQuery(consulta);
      while(rs.next()){
          entidad_lotes_inventario lote = new entidad_lotes_inventario();
          lote.setId_lote(rs.getInt("ID Lote"));
          lote.setProducto_lote(rs.getString("Nombre de Producto Asociado"));
          lote.setId_producto_lote(rs.getInt("ID Producto Asociado"));
          lote.setStock_entrante_lote(rs.getInt("Stock Entrante"));
          lote.setStock_actual(rs.getInt("Stock Actual"));
          lote.setFecha_ingreso(rs.getDate("Fecha de Ingreso"));
          lote.setFecha_vencimiento(rs.getDate("Fecha de Vencimiento"));
          lote.setDias_vencimiento(rs.getInt("Dias para Vencer"));
          lote.setNombre_proveedor_lote(rs.getString("Nombre del Proveedor Asociado"));
          lote.setId_proveedor_lote(rs.getInt("ID Proveedor"));
          lote.setId_compra_asociada(rs.getInt("ID de Compra Asociada"));
          lote.setUsuario_responsable(rs.getString("Usuario Responsable"));
          modelo.addRow(new Object[]{lote.getId_lote(),lote.getProducto_lote(),lote.getId_producto_lote(),lote.getStock_entrante_lote(),lote.getStock_actual(),lote.getFecha_ingreso(),lote.getFecha_vencimiento(),lote.getDias_vencimiento(),lote.getNombre_proveedor_lote(),lote.getId_proveedor_lote(),lote.getId_compra_asociada(),lote.getUsuario_responsable()});
        }
      }
      catch(SQLException e){
         JOptionPane.showMessageDialog(null,"Error al listar los lotes, " + e.toString()); 
      }
    }
    public void ListarRegistros_movimientos(JTable tabla_registros){
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("ID Historial");
      modelo.addColumn("ID Lote Asociado");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("Nombre Acción");
      modelo.addColumn("Stock Anterior Registrado");
      modelo.addColumn("Stock Nuevo Registrado");
      modelo.addColumn("Usuario Responsable");
      modelo.addColumn("Username Reponsable");
      modelo.addColumn("Fecha del Movimiento");
      
      tabla_registros.setModel(modelo);
      String consulta = "{call BuscarHistorial_General}";
      try{
          Statement st = objetoConexion.conectar().createStatement();
          ResultSet rs = st.executeQuery(consulta);
          while(rs.next()){
              Registro_movimiento_lotes registro = new Registro_movimiento_lotes();
              registro.setId_historial(rs.getInt("ID Historial"));
              registro.setId_lote(rs.getInt("ID Lote Asociado"));
              registro.setNombre_producto(rs.getString("Nombre del Producto"));
              registro.setNombre_accion(rs.getString("Nombre Acción"));
              registro.setStock_anterior(rs.getInt("Stock Anterior Registrado"));
              registro.setStock_nuevo(rs.getInt("Stock Nuevo"));
              registro.setUsuario_responsable(rs.getString("Usuario Responsable"));
              registro.setUsername(rs.getString("username"));
              registro.setFecha_movimiento(rs.getTimestamp("Fecha del Movimiento"));
              modelo.addRow(new Object[]{registro.getId_historial(),registro.getId_lote(),registro.getNombre_producto(),
              registro.getNombre_accion(),registro.getStock_anterior(),registro.getStock_nuevo(),
              registro.getUsuario_responsable(), registro.getUsername(),registro.getFecha_movimiento()});  
          }
      }
      catch (Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los movimientos, " + e.toString());
      }
    }
    public void ListarRegistros_x_fechas(JDateChooser fecha_inicio, JDateChooser fecha_fin, JTable tabla_registros){
        if (fecha_inicio.getDate() == null || fecha_fin.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas para iniciar la búsqueda.");
            return;
        }
      ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
      DefaultTableModel modelo = new DefaultTableModel();
      modelo.addColumn("ID Historial");
      modelo.addColumn("ID Lote Asociado");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("Nombre Acción");
      modelo.addColumn("Stock Anterior Registrado");
      modelo.addColumn("Stock Nuevo Registrado");
      modelo.addColumn("Usuario Responsable");
      modelo.addColumn("Username Reponsable");
      modelo.addColumn("Fecha del Movimiento");
      tabla_registros.setModel(modelo);
      String consulta = "{call BuscarHistorial_x_dias(?,?)}";
      try{
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setDate(1, new java.sql.Date(fecha_inicio.getDate().getTime()));
          cs.setDate(2, new java.sql.Date(fecha_fin.getDate().getTime()));
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
              Registro_movimiento_lotes registro = new Registro_movimiento_lotes();
              registro.setId_historial(rs.getInt("ID Historial"));
              registro.setId_lote(rs.getInt("ID Lote Asociado"));
              registro.setNombre_producto(rs.getString("Nombre del Producto"));
              registro.setNombre_accion(rs.getString("Nombre Acción"));
              registro.setStock_anterior(rs.getInt("Stock Anterior Registrado"));
              registro.setStock_nuevo(rs.getInt("Stock Nuevo"));
              registro.setUsuario_responsable(rs.getString("Usuario Responsable"));
              registro.setUsername(rs.getString("username"));
              registro.setFecha_movimiento(rs.getTimestamp("Fecha del Movimiento"));
              modelo.addRow(new Object[]{registro.getId_historial(),registro.getId_lote(),registro.getNombre_producto(),
              registro.getNombre_accion(),registro.getStock_anterior(),registro.getStock_nuevo(),
              registro.getUsuario_responsable(), registro.getUsername(),registro.getFecha_movimiento()});
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al listar los movimientos, " + e.toString());
      }
    }
    public void ListarRegistros_x_productos(JTable tabla_registros,String nombre_producto){
        if (nombre_producto == null || nombre_producto.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto válido.");
        return;
    }
        ConectarBaseDatos objetoConexion = new ConectarBaseDatos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Historial");
      modelo.addColumn("ID Lote Asociado");
      modelo.addColumn("Nombre del Producto");
      modelo.addColumn("Nombre Acción");
      modelo.addColumn("Stock Anterior Registrado");
      modelo.addColumn("Stock Nuevo Registrado");
      modelo.addColumn("Usuario Responsable");
      modelo.addColumn("Username Reponsable");
      modelo.addColumn("Fecha del Movimiento");
      tabla_registros.setModel(modelo);
      String consulta = "{call BuscarHistorial_x_producto(?)}";
      try{
          CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
          cs.setString(1, nombre_producto);
          ResultSet rs = cs.executeQuery();
          while(rs.next()){
              Registro_movimiento_lotes registro = new Registro_movimiento_lotes();
              registro.setId_historial(rs.getInt("ID Historial"));
              registro.setId_lote(rs.getInt("ID Lote Asociado"));
              registro.setNombre_producto(rs.getString("Nombre del Producto"));
              registro.setNombre_accion(rs.getString("Nombre Acción"));
              registro.setStock_anterior(rs.getInt("Stock Anterior Registrado"));
              registro.setStock_nuevo(rs.getInt("Stock Nuevo"));
              registro.setUsuario_responsable(rs.getString("Usuario Responsable"));
              registro.setUsername(rs.getString("username"));
              registro.setFecha_movimiento(rs.getTimestamp("Fecha del Movimiento"));
              modelo.addRow(new Object[]{registro.getId_historial(),registro.getId_lote(),registro.getNombre_producto(),
              registro.getNombre_accion(),registro.getStock_anterior(),registro.getStock_nuevo(),
              registro.getUsuario_responsable(), registro.getUsername(),registro.getFecha_movimiento()});
          }
          if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron movimientos para: " + nombre_producto);
          }
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error al filtrar por producto: " + e.toString());
      }
    }
    public void llamar_accion(JComboBox <acción_registro_movimiento_lotesinventario> combo_accion){
      database.ConectarBaseDatos conectar = new ConectarBaseDatos();
      String sql = "exec LLamar_accion";
      Statement st;
        try {
          st = conectar.conectar().createStatement();
          ResultSet rs = st.executeQuery(sql);
          combo_accion.removeAllItems();
          while(rs.next()){
          acción_registro_movimiento_lotesinventario accion = new acción_registro_movimiento_lotesinventario();
          accion.setId_accion(rs.getInt("id_accion"));
          accion.setNombre_acción(rs.getString("nombre_accion"));
          combo_accion.addItem(accion);
          }
        } 
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error al llamar las tablas involucradas, " + e.toString());
        }   
    }
}
