package Capa_Negocio;

import Capa_Datos.UsuarioDAO;
import Capa_Entidad.Usuario;
import java.util.List;

public class Neg_Usuario {

    private final UsuarioDAO usuarioDAO;

    public Neg_Usuario() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    public void eliminarUsuario(int idUsuario) {
        usuarioDAO.eliminarUsuario(idUsuario);
    }

    public void modificarUsuario(Usuario usuario) {
        usuarioDAO.modificarUsuario(usuario);
    }
}
