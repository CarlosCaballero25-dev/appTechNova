package Controlador;

import Conexion.ConexionMySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFrame;

public class Graficos {

    // Método para graficar las ventas
    public static void Graficar(JDateChooser dcInicio, JDateChooser dcFin) {
        Connection con;
        ConexionMySQL cn = new ConexionMySQL();
        con = cn.conexion(); // Usa la nueva clase de conexión
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Consulta SQL para contar la cantidad de ventas por fecha
            String sql = "SELECT COUNT(idDetalleVenta) AS cantidad_ventas, fechaVenta FROM detalle_venta WHERE fechaVenta BETWEEN ? AND ? GROUP BY fechaVenta";
            con = cn.conexion();
            ps = con.prepareStatement(sql);

            // Convertir las fechas seleccionadas a java.sql.Date
            java.sql.Date fechaInicio = new java.sql.Date(dcInicio.getDate().getTime());
            java.sql.Date fechaFin = new java.sql.Date(dcFin.getDate().getTime());

            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            rs = ps.executeQuery();

            DefaultPieDataset dataset = new DefaultPieDataset();

            // Recoger datos de la consulta y agregar al dataset
            while (rs.next()) {
                dataset.setValue(rs.getString("fechaVenta"), rs.getInt("cantidad_ventas"));
            }

            // Crear el gráfico
            JFreeChart chart = ChartFactory.createPieChart("Reporte de Ventas", dataset, true, true, false);
            ChartFrame frame = new ChartFrame("Total de Ventas por Día", chart);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.toString());
            }
        }
    }
}
