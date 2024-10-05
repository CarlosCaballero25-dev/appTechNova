package Capa_Datos;

import Capa_Entidad.Usuario;
import Conexion.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion();
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "{CALL listarUsuarios()}";

        try (CallableStatement stmt = connection.prepareCall(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNomape(rs.getString("nomape"));
                usuario.setFecna(rs.getDate("fecna"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setDNI(rs.getInt("DNI"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setImagen(rs.getBytes("imagen"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public void guardarUsuario(Usuario usuario) {
        String sql = "{CALL guardarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, usuario.getUsuario());     // usuario
            stmt.setString(2, usuario.getNomape());      // nombre y apellido
            stmt.setDate(3, (Date) usuario.getFecna());         // fecha de nacimiento
            stmt.setString(4, usuario.getPassword());     // contraseña
            stmt.setInt(5, usuario.getTelefono());        // teléfono
            stmt.setInt(6, usuario.getDNI());             // DNI
            stmt.setString(7, usuario.getRol());          // rol
            stmt.setString(8, usuario.getEstado());       // estado
            stmt.setBytes(9, usuario.getImagen());        // imagen
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int idUsuario) {
        String sql = "{CALL eliminarUsuario(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void modificarUsuario(Usuario usuario) {
        String sql = "{CALL modificarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getNomape());
            stmt.setDate(4, (Date) usuario.getFecna());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getTelefono());
            stmt.setInt(7, usuario.getDNI());
            stmt.setString(8, usuario.getRol());
            stmt.setString(9, usuario.getEstado());
            stmt.setBytes(10, usuario.getImagen());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
        }
    }
}
