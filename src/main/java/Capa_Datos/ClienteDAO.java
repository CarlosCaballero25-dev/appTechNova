package Capa_Datos;

import Conexion.ConexionMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import Capa_Entidad.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion(); // Establece la conexión usando tu método
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "{CALL listarClientes()}"; // Llamada al procedimiento almacenado

        try (CallableStatement cstmt = connection.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomape(rs.getString("nomape"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Clientes obtenidos: " + clientes.size()); // Verifica cuántos clientes se obtienen
        return clientes;
    }

    public Cliente obtenerClientePorNombre(String nombreCliente) {
        Cliente cliente = null;
        String sql = "SELECT idCliente, nomape, dni, telefono, direccion FROM cliente WHERE nomape = ?"; // Cambia la tabla y los nombres de columnas según tu base de datos

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombreCliente);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idcliente"));
                cliente.setNomape(resultSet.getString("nomape"));
                cliente.setDni(resultSet.getInt("dni"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores; puedes mejorar esto
        }
        return cliente;
    }

    public String agregarCliente(Cliente cliente) {
        String sql = "{CALL guardarCliente(?, ?, ?, ?)}"; // Llamada al procedimiento almacenado
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setString(1, cliente.getNomape());
            cstmt.setInt(2, cliente.getDni());
            cstmt.setString(3, cliente.getTelefono());
            cstmt.setString(4, cliente.getDireccion());
            cstmt.executeUpdate();
            return "Cliente agregado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al agregar el cliente.";
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "{CALL eliminarCliente(?)}"; // Llamada al procedimiento almacenado
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, idCliente);
            cstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarCliente(Cliente cliente) {
        String sql = "{CALL modificarCliente(?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setString(1, cliente.getNomape());
            cstmt.setInt(2, cliente.getDni());
            cstmt.setString(3, cliente.getTelefono());
            cstmt.setString(4, cliente.getDireccion());
            cstmt.setInt(5, cliente.getIdCliente());
            cstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeCliente(int idCliente) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE idCliente = ?"; // Asumiendo que este query sigue siendo necesario
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
