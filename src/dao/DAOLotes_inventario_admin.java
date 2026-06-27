/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.entidad_lotes_inventario;
import database.ConectarBaseDatos;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/*import java.sql.Statement;*/

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
}
