package Capa_Datos;

import Capa_Entidad.Producto;
import Conexion.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private final Connection connection;

    public ProductoDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion();
    }

    public String agregarProducto(Producto producto) {
        String respuesta;
        String sql = "{CALL agregar_producto(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, producto.getNombre());
            cs.setInt(2, producto.getCantidad());
            cs.setBigDecimal(3, producto.getPrecio());
            cs.setString(4, producto.getDescripcion());
            cs.setInt(5, producto.getIdCategoria());
            cs.setBytes(6, producto.getImagen());
            cs.setString(7, producto.getCodigoProducto());
            cs.registerOutParameter(8, Types.BIGINT);
            cs.executeUpdate();
            respuesta = "Producto agregado con ID: " + cs.getLong(8);
        } catch (SQLException e) {
            respuesta = "Error al agregar el producto: " + e.getMessage();
        }
        return respuesta;
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "{CALL listarProductos()}"; // Asegúrate de que esta llamada almacenada esté bien definida

        try (CallableStatement stmt = connection.prepareCall(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setCantidad(rs.getInt("cantidad")); // Cambiado de "stock" a "cantidad"
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setImagen(rs.getBytes("imagen"));
                producto.setCodigoProducto(rs.getString("codigoProducto"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            // Usar un logger en vez de System.out.println en producción
            System.err.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    public void eliminarProducto(int idProducto) {
        String sql = "{CALL eliminarProducto(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idProducto);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public void modificarProducto(Producto producto) {
        String sql = "{CALL modificarProducto(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, producto.getIdProducto());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getCantidad());
            stmt.setBigDecimal(4, producto.getPrecio());
            stmt.setString(5, producto.getDescripcion());
            stmt.setInt(6, producto.getIdCategoria());
            stmt.setBytes(7, producto.getImagen());
            stmt.setString(8, producto.getCodigoProducto());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al modificar producto: " + e.getMessage());
        }
    }

    public boolean existeProducto(int idProducto) {
        boolean existe = false;
        String sql = "{CALL existeProducto(?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idProducto);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();
            existe = stmt.getInt(2) > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de producto: " + e.getMessage());
        }
        return existe;
    }
}
