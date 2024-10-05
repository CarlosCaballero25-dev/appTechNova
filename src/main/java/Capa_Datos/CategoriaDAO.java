package Capa_Datos;

import Capa_Entidad.Categoria;
import Conexion.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Connection connection;

    public CategoriaDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion();
    }

    public String guardarCategoria(Categoria categoria) {
        String respuesta;
        int resultado;
        try (CallableStatement cs = connection.prepareCall("{CALL AgregarCategoria(?, ?, ?, ?)}")) {
            cs.setString(1, categoria.getNombre());
            cs.setString(2, categoria.getDescripcion());
            cs.setString(3, categoria.getPrefijo());
            cs.registerOutParameter(4, Types.INTEGER); // Registrar el OUT parameter
            cs.execute();

            resultado = cs.getInt(4); // Obtener el resultado del OUT parameter

            if (resultado > 0) {
                respuesta = "Categoría agregada correctamente. ID: " + resultado;
            } else {
                respuesta = "Error al agregar la categoría.";
            }
        } catch (SQLException e) {
            respuesta = "Error al agregar la categoría: " + e.getMessage();
        }
        return respuesta;
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "{CALL listarCategorias()}";

        try (CallableStatement stmt = connection.prepareCall(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setPrefijo(rs.getString("prefijo"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return categorias;
    }

    public void eliminarCategoria(int idCategoria) {
        String sql = "{CALL eliminarCategoria(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        }
    }

    public String actualizarCategoria(Categoria categoria) {
        String respuesta;
        int resultado;

        try (CallableStatement cs = connection.prepareCall("{CALL ActualizarCategoria(?, ?, ?, ?, ?)}")) {
            // Suponiendo que la clase Categoria tiene un método getId() para obtener el ID
            cs.setInt(1, categoria.getIdCategoria()); // ID de la categoría a actualizar
            cs.setString(2, categoria.getNombre()); // Nuevo nombre
            cs.setString(3, categoria.getDescripcion()); // Nueva descripción
            cs.setString(4, categoria.getPrefijo());
            cs.registerOutParameter(5, Types.INTEGER); // Registrar el OUT parameter

            cs.execute();

            resultado = cs.getInt(5); // Obtener el resultado del OUT parameter

            if (resultado > 0) {
                respuesta = "Categoría actualizada correctamente. ID: " + resultado;
            } else {
                respuesta = "No se encontró la categoría.";
            }
        } catch (SQLException e) {
            respuesta = "Error al actualizar la categoría: " + e.getMessage();
        }
        return respuesta;
    }

    public boolean existeCategoria(int idCategoria) {
        boolean existe = false;
        String sql = "{CALL existeCategoria(?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();
            existe = stmt.getInt(2) > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de categoría: " + e.getMessage());
        }
        return existe;
    }

    public List<String> listarNombresCategorias() {
        List<String> nombresCategorias = new ArrayList<>();
        String sql = "{CALL listarCategorias()}"; // Asegúrate de que este llamado sea correcto

        try (CallableStatement stmt = connection.prepareCall(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre"); // Asegúrate de que el nombre de la columna sea correcto
                nombresCategorias.add(nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar nombres de categorías: " + e.getMessage());
        }
        return nombresCategorias;
    }

    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        Categoria categoria = null;
        String sql = "SELECT * FROM Categoria WHERE nombre = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreCategoria);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setPrefijo(rs.getString("prefijo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la categoría por nombre: " + e.getMessage());
        }

        return categoria;
    }

}
