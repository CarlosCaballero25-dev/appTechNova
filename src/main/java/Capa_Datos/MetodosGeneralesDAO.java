/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;

import Conexion.ConexionMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Eduardo
 */
public class MetodosGeneralesDAO {

    private final Connection connection;

    public MetodosGeneralesDAO() {
        ConexionMySQL con = new ConexionMySQL();
        this.connection = con.conexion(); // Establece la conexión usando tu método
    }

    public int obtenerProximoIdUsuario() {
        int proximoId = 0;
        String sql = "SELECT MAX(IdUsuario) FROM Usuario";  // Asumiendo que la tabla es 'Usuario'
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                proximoId = rs.getInt(1) + 1;  // El próximo ID será el máximo actual + 1
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el próximo IdUsuario: " + e.getMessage());
        }
        return proximoId;
    }

    public int obtenerProximoIdCategoria() {
        int proximoId = 0;
        String sql = "SELECT MAX(IdCategoria) FROM Categoria";  // Asumiendo que la tabla es 'Categoria'
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                proximoId = rs.getInt(1) + 1;  // El próximo ID será el máximo actual + 1
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el próximo IdCategoria: " + e.getMessage());
        }
        return proximoId;
    }

    public int obtenerProximoIdProducto() {
        int proximoId = 0; // Cambiar a 1 para que empiece desde el primer ID
        String sql = "SELECT MAX(idProducto) FROM Producto";  // Asegúrate de que la tabla sea 'Producto'
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                // Si no hay productos, el resultado será null, por eso iniciamos proximoId en 1
                proximoId = rs.getInt(1) + 1;  // El próximo ID será el máximo actual + 1
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el próximo IdProducto: " + e.getMessage());
        }
        return proximoId;
    }
    
    public int obtenerProximoIdCliente() {
        int proximoId = 0; // Cambiar a 1 para que empiece desde el primer ID
        String sql = "SELECT MAX(idCliente) FROM cliente";  // Asegúrate de que la tabla sea 'Producto'
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                // Si no hay productos, el resultado será null, por eso iniciamos proximoId en 1
                proximoId = rs.getInt(1) + 1;  // El próximo ID será el máximo actual + 1
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el próximo IdProducto: " + e.getMessage());
        }
        return proximoId;
    }

    public String generarCodigoProducto(int idCategoria) {
        String codigoProducto = null;
        String prefijo = null;
        int siguienteNumero = 1;

        // Consulta para obtener el prefijo de la categoría
        String queryPrefijo = "{CALL ObtenerPrefijoPorCategoria(?)}";
        // Consulta para contar productos existentes en la categoría
        String queryContarProductos = "SELECT COUNT(*) AS cantidad FROM producto WHERE idCategoria = ?";

        try (Connection con = new ConexionMySQL().conexion(); CallableStatement stmtPrefijo = con.prepareCall(queryPrefijo); PreparedStatement stmtContar = con.prepareStatement(queryContarProductos)) {

            // Obtener el prefijo
            stmtPrefijo.setInt(1, idCategoria);
            ResultSet rsPrefijo = stmtPrefijo.executeQuery();

            if (rsPrefijo.next()) {
                prefijo = rsPrefijo.getString("prefijo");
            }

            if (prefijo == null || prefijo.isEmpty()) {
                System.err.println("No se encontró el prefijo para la categoría con ID: " + idCategoria);
                return null;
            }

            // Contar cuántos productos ya existen en esta categoría
            stmtContar.setInt(1, idCategoria);
            ResultSet rsContar = stmtContar.executeQuery();

            if (rsContar.next()) {
                // Si hay productos, el siguiente número es la cantidad + 1
                siguienteNumero = rsContar.getInt("cantidad") + 1;
            } else {
                // Si no hay productos, comenzamos desde 1
                siguienteNumero = 1;
            }

            // Generar el código de producto en el formato "PREFIX###"
            codigoProducto = String.format("%s%03d", prefijo, siguienteNumero);

        } catch (SQLException e) {
            System.err.println("Error al generar el código de producto: " + e.getMessage());
        }

        return codigoProducto;
    }

}
