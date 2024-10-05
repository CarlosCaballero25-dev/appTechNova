package Capa_Datos;

import Capa_Entidad.DetalleVenta;
import Conexion.ConexionMySQL;
import Capa_Entidad.Venta;
import Capa_Entidad.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    private final Connection connection;

    public DetalleVentaDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion(); // Establece la conexión usando tu método
    }

    public boolean guardarVenta(DetalleVenta detalle) {
        String sql = ("{CALL guardar_detalle_venta(?, ?, ?, ?, ?, ?, ?, ?, ?)}"); // Llama al procedimiento almacenado

        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, detalle.getCantidad());
            cstmt.setDouble(2, detalle.getSubtotal());
            cstmt.setDouble(3, detalle.getIgv());
            cstmt.setDouble(4, detalle.getTotalPagar());
            cstmt.setString(5, detalle.getNombreProducto());
            cstmt.setDouble(6, detalle.getPrecioUnitario()); // Asegúrate de que esto esté correcto
            cstmt.setString(7, detalle.getNombreUsuario());
            cstmt.setString(8, detalle.getNombreCliente());
            cstmt.registerOutParameter(9, java.sql.Types.INTEGER);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
            return false; // Retorna false en caso de error
        }
    }

    public List<DetalleVenta> listarVentasConDetalles() {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "{CALL listar_ventas_con_detalles()}"; // Llama al procedimiento almacenado

        try (CallableStatement cstmt = connection.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalleVenta(rs.getInt("idDetalleVenta")); // ID del detalle de la venta
                detalle.setCantidad(rs.getInt("cantidad")); // Cantidad
                detalle.setPrecioUnitario(rs.getDouble("precioUnitario")); // Precio unitario recuperado
                detalle.setSubtotal(detalle.getPrecioUnitario() * detalle.getCantidad()); // Calcular subtotal aquí
                detalle.setIgv(rs.getDouble("igv")); // IGV
                detalle.setTotalPagar(rs.getDouble("totalPagar")); // Total a pagar
                detalle.setNombreProducto(rs.getString("nombreProducto")); // Nombre del producto
                detalle.setNombreUsuario(rs.getString("nombreUsuario")); // Nombre del usuario que realizó la venta
                detalle.setNombreCliente(rs.getString("nombreCliente")); // Nombre del cliente
                detalle.setFechaVenta(rs.getTimestamp("fechaVenta")); // Fecha de la venta

                detalles.add(detalle); // Agrega el detalle a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
        return detalles; // Retorna la lista de detalles
    }

    public void listarProductosPorCategoria(int idCategoria) {
        String query = "{CALL ListarProductosPorCategoria(?)}";

        try (Connection con = new ConexionMySQL().conexion(); CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String codigoProducto = rs.getString("codigoProducto");
                int categoriaId = rs.getInt("idCategoria");
                String nombreCategoria = rs.getString("nombreCategoria");
                String prefijo = rs.getString("prefijo");

                // Aquí puedes procesar los resultados según lo necesites
                System.out.printf("ID: %d, Código: %s, ID Categoría: %d, Nombre: %s, Prefijo: %s%n",
                        idProducto, codigoProducto, categoriaId, nombreCategoria, prefijo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos por categoría: " + e.getMessage());
        }
    }
}
