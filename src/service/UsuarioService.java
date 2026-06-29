package service;

import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public List<Usuario> listarTodos() {
        return dao.listarTodos();
    }

    public List<Usuario> buscar(String username) {
        return dao.buscarPorUsername(username);
    }

    public int registrar(Usuario u) {
        if (u.getNombre() == null || u.getNombre().trim().isEmpty()) return -1;
        if (u.getApellido() == null || u.getApellido().trim().isEmpty()) return -1;
        if (u.getUsername() == null || u.getUsername().trim().isEmpty()) return -1;
        if (u.getPassword() == null || u.getPassword().trim().isEmpty()) return -1;
        return dao.insertar(u);
    }

    public boolean actualizar(Usuario u) {
        if (u.getId_usuario() <= 0) return false;
        return dao.actualizar(u);
    }

    public boolean eliminar(int id) {
        if (id <= 0) return false;
        return dao.eliminar(id);
    }

    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
