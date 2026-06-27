package dao;
import model.Genero;

import database.ConectarBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public List<Genero> listarTodos() {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT id_genero, nombre_genero FROM GENEROS";
        try (Connection con = ConectarBaseDatos.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Genero(rs.getInt("id_genero"), rs.getString("nombre_genero")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar generos: " + e.getMessage());
        }
        return lista;
    }
}
