package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    String url = "jdbc:mysql://localhost:3306/ bdtechnova";
    String usuario = "root";
    String pass = "";
    Connection conex;

    public Connection conexion() {
        try {
            //cargar el controlador
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer la Conexion
            conex = DriverManager.getConnection(url, usuario, pass);

            if (conex != null) {
                System.out.println("Conexion Establecida");
            }

        } catch (ClassNotFoundException ce) {
            System.out.println("Controlador no encontrado: "
                    + ce.getMessage());
        } catch (SQLException se) {
            System.out.println("Error al Conectar la Base de Datos");
        }
        return conex;
    }

    public static void main(String[] args) {
        ConexionMySQL cn = new ConexionMySQL();
        cn.conexion();
    }
}

