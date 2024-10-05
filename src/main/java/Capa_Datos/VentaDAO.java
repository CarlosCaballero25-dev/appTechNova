package Capa_Datos;

import Capa_Entidad.Venta;
import Conexion.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private final Connection connection;

    public VentaDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion();
    }

    public List<Venta> listarVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "{CALL listarVentas()}";

        try (CallableStatement stmt = connection.prepareCall(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("idVenta"),
                        rs.getInt("idCliente"),
                        rs.getInt("idUsuario"),
                        rs.getDouble("valorPagar"),
                        rs.getDouble("igv"), // Recuperar el IGV
                        rs.getDouble("vuelto"), // Recuperar el vuelto
                        rs.getDate("fechaVenta")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
        return ventas;
    }

    public void guardarVenta(Venta venta, double efectivo) {
        String sql = "{CALL guardarVenta(?, ?, ?, ?, ?, ?, ?)}"; // Asegúrate de modificar el procedimiento almacenado en la BD para reflejar los nuevos parámetros
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, venta.getIdCliente());
            stmt.setInt(2, venta.getIdUsuario());
            stmt.setDouble(3, venta.getValorPagar());
            stmt.setDouble(4, venta.getIgv()); // Pasar el IGV
            stmt.setDouble(5, venta.getVuelto()); // Pasar el vuelto
            stmt.setDouble(6, efectivo); // Pasar el efectivo entregado
            stmt.setDate(7, venta.getFechaVenta());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
    }

    public void eliminarVenta(int idVenta) {
        String sql = "{CALL eliminarVenta(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idVenta);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
        }
    }

    public void modificarVenta(Venta venta) {
        String sql = "{CALL modificarVenta(?, ?, ?, ?, ?, ?)}"; // Asegúrate de modificar el procedimiento almacenado
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, venta.getIdVenta());
            stmt.setInt(2, venta.getIdCliente());
            stmt.setInt(3, venta.getIdUsuario());
            stmt.setDouble(4, venta.getValorPagar());
            stmt.setDouble(5, venta.getIgv()); // Actualizar el IGV
            stmt.setDouble(6, venta.getVuelto()); // Actualizar el vuelto
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al modificar venta: " + e.getMessage());
        }
    }
}
