package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.entidad_proveedor_inventario;
// Importamos tu clase de conexión
import database.ConectarBaseDatos;

public class ProveedorDAO {

    // Método para INSERTAR un proveedor con múltiples teléfonos y correos en Azure SQL
    public boolean insertar(entidad_proveedor_inventario prov) {
        // Usamos tu método exacto de conexión
        Connection con = ConectarBaseDatos.conectar();
        PreparedStatement psProv = null;
        PreparedStatement psTel = null;
        PreparedStatement psCorr = null;
        ResultSet rs = null;

        if (con == null) {
            System.out.println("❌ No se pudo establecer conexión con Azure para insertar.");
            return false;
        }

        try {
            con.setAutoCommit(false); // Iniciamos transacción transaccional

            // 1. Insertar en la tabla PROVEEDOR y recuperar el ID autogenerado (IDENTITY)
            String sqlProv = "INSERT INTO PROVEEDOR (ruc, nombre, direccion) VALUES (?, ?, ?)";
            psProv = con.prepareStatement(sqlProv, Statement.RETURN_GENERATED_KEYS);
            psProv.setString(1, prov.getRuc());
            psProv.setString(2, prov.getNombre_proveedor());
            psProv.setString(3, prov.getDireccion());
            psProv.executeUpdate();

            rs = psProv.getGeneratedKeys();
            int idProveedor = 0;
            if (rs.next()) {
                idProveedor = rs.getInt(1);
            }

            // 2. Insertar los múltiples teléfonos usando procesamiento por lotes (Batch)
            String sqlTel = "INSERT INTO TELEFONO_PROVEEDOR (id_proveedor, telefono) VALUES (?, ?)";
            psTel = con.prepareStatement(sqlTel);
            for (String tel : prov.getTelefonos()) {
                psTel.setInt(1, idProveedor);
                psTel.setString(2, tel);
                psTel.addBatch(); 
            }
            psTel.executeBatch();

            // 3. Insertar los múltiples correos
            String sqlCorr = "INSERT INTO CORREO_PROVEEDOR (id_proveedor, correo) VALUES (?, ?)";
            psCorr = con.prepareStatement(sqlCorr);
            for (String corr : prov.getCorreos()) {
                psCorr.setInt(1, idProveedor);
                psCorr.setString(2, corr);
                psCorr.addBatch();
            }
            psCorr.executeBatch();

            con.commit(); // Todo fue exitoso, guardamos en la nube
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar proveedor en Azure: " + e.getMessage());
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (psProv != null) psProv.close();
                if (psTel != null) psTel.close();
                if (psCorr != null) psCorr.close();
                if (con != null) con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // Método para ELIMINAR un proveedor (Limpieza en cascada manual debido a las FK)
    public boolean eliminar(int idProveedor) {
        Connection con = database.ConectarBaseDatos.conectar();
    PreparedStatement psTel = null;
    PreparedStatement psCorr = null;
    PreparedStatement psProv = null;

    if (con == null) return false;

    try {
        con.setAutoCommit(false); // 🔥 Iniciamos una transacción

        // 1. Eliminar primero los teléfonos asociados al proveedor
        String sqlTel = "DELETE FROM TELEFONO_PROVEEDOR WHERE id_proveedor = ?";
        psTel = con.prepareStatement(sqlTel);
        psTel.setInt(1, idProveedor);
        psTel.executeUpdate();

        // 2. Eliminar los correos asociados al proveedor
        String sqlCorr = "DELETE FROM CORREO_PROVEEDOR WHERE id_proveedor = ?";
        psCorr = con.prepareStatement(sqlCorr);
        psCorr.setInt(1, idProveedor);
        psCorr.executeUpdate();

        // 3. Ahora que está limpio, eliminamos al proveedor de la tabla principal
        String sqlProv = "DELETE FROM PROVEEDOR WHERE id_proveedor = ?";
        psProv = con.prepareStatement(sqlProv);
        psProv.setInt(1, idProveedor);
        psProv.executeUpdate();

        con.commit(); // 🔥 Confirmamos que todo se borró en cadena con éxito
        return true;

    } catch (SQLException e) {
        System.out.println("❌ Error al eliminar proveedor en Azure: " + e.getMessage());
        try {
            if (con != null) con.rollback(); // Si algo falla, revierte todo
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    } finally {
        try {
            if (psTel != null) psTel.close();
            if (psCorr != null) psCorr.close();
            if (psProv != null) psProv.close();
            if (con != null) con.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    }
    
    public boolean modificar(entidad_proveedor_inventario prov) {
    Connection con = database.ConectarBaseDatos.conectar();
    PreparedStatement psProv = null;
    PreparedStatement psDelTel = null;
    PreparedStatement psInsTel = null;
    PreparedStatement psDelCorr = null;
    PreparedStatement psInsCorr = null;

    if (con == null) return false;

    try {
        con.setAutoCommit(false); // Transacción para asegurar los datos en Azure

        // 1. Actualizar Nombre y Dirección
        String sqlProv = "UPDATE PROVEEDOR SET nombre = ?, direccion = ? WHERE id_proveedor = ?";
        psProv = con.prepareStatement(sqlProv);
        psProv.setString(1, prov.getNombre_proveedor());
        psProv.setString(2, prov.getDireccion());
        psProv.setInt(3, prov.getId_proveedor());
        psProv.executeUpdate();

        // 2. Limpiar y refrescar teléfonos anteriores
        psDelTel = con.prepareStatement("DELETE FROM TELEFONO_PROVEEDOR WHERE id_proveedor = ?");
        psDelTel.setInt(1, prov.getId_proveedor());
        psDelTel.executeUpdate();

        String sqlInsTel = "INSERT INTO TELEFONO_PROVEEDOR (id_proveedor, telefono) VALUES (?, ?)";
        psInsTel = con.prepareStatement(sqlInsTel);
        for (String tel : prov.getTelefonos()) {
            psInsTel.setInt(1, prov.getId_proveedor());
            psInsTel.setString(2, tel);
            psInsTel.addBatch();
        }
        psInsTel.executeBatch();

        // 3. Limpiar y refrescar correos anteriores
        psDelCorr = con.prepareStatement("DELETE FROM CORREO_PROVEEDOR WHERE id_proveedor = ?");
        psDelCorr.setInt(1, prov.getId_proveedor());
        psDelCorr.executeUpdate();

        String sqlInsCorr = "INSERT INTO CORREO_PROVEEDOR (id_proveedor, correo) VALUES (?, ?)";
        psInsCorr = con.prepareStatement(sqlInsCorr);
        for (String corr : prov.getCorreos()) {
            psInsCorr.setInt(1, prov.getId_proveedor());
            psInsCorr.setString(2, corr);
            psInsCorr.addBatch();
        }
        psInsCorr.executeBatch();

        con.commit(); // Confirmación de cambios exitosa
        return true;

    } catch (SQLException e) {
        System.out.println("❌ Error al modificar proveedor en Azure: " + e.getMessage());
        try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    } finally {
        try {
            if (psProv != null) psProv.close();
            if (psDelTel != null) psDelTel.close();
            if (psInsTel != null) psInsTel.close();
            if (psDelCorr != null) psDelCorr.close();
            if (psInsCorr != null) psInsCorr.close();
            if (con != null) con.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    }
}