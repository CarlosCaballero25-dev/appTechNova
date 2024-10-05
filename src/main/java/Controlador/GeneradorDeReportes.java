package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Conexion.ConexionMySQL;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GeneradorDeReportes {

    private static final String RUTA_LOGO = "C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\appSystemTechNova_CECA\\src\\main\\resources\\Imagenes\\TechNova2.png";

    // Método para generar reporte de Categorías
    public void generarReporteCategorias() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\Reporte_Categorias.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance(RUTA_LOGO);
            header.scaleToFit(150, 300);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte de Categorías \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(4); // Se asume que cada categoría tiene 4 campos
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Descripción");
            tabla.addCell("Prefijo");

            // Usar la conexión de ConexionMySQL
            Connection cn = new ConexionMySQL().conexion();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM categoria");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(2));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
            }
            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Categorías creado");

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }

    // Método para generar reporte de Clientes
    public void generarReporteClientes() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\Reporte_Clientes.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance(RUTA_LOGO);
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte de Clientes \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5); // Se asume que cada cliente tiene 5 campos
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("DNI");
            tabla.addCell("Teléfono");
            tabla.addCell("Dirección");

            // Usar la conexión de ConexionMySQL
            Connection cn = new ConexionMySQL().conexion();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(2));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
            }
            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Clientes creado");

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }

    // Método para generar reporte de Productos
    public void generarReporteProductos() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\Reporte_Productos.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance(RUTA_LOGO);
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte de Productos \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(7); // Se asume que cada producto tiene 7 campos
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            tabla.addCell("Descripción");
            tabla.addCell("Código");
            tabla.addCell("ID Categoría");

            // Usar la conexión de ConexionMySQL
            Connection cn = new ConexionMySQL().conexion();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM producto");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(2));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(6));
                tabla.addCell(rs.getString(7));
            }
            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Productos creado");

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }

    // Método para generar reporte de Detalle de Ventas
    public void generarReporteDetalleVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\Reporte_Detalle_Ventas.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance(RUTA_LOGO);
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte de Detalle de Ventas \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(6); // Se asume que cada detalle de venta tiene 6 campos
            tabla.addCell("ID Detalle");
            tabla.addCell("ID Venta");
            tabla.addCell("ID Producto");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            tabla.addCell("Subtotal");

            // Usar la conexión de ConexionMySQL
            Connection cn = new ConexionMySQL().conexion();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM detalle_venta");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(2));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
                tabla.addCell(rs.getString(6));
            }
            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Detalle de Ventas creado");

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }

    // Método para generar reporte de Ventas
    public void generarReporteVentas() {
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\Reporte_Ventas.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance(RUTA_LOGO);
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte de Ventas \n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5); // Se asume que cada venta tiene 5 campos
            tabla.addCell("ID Venta");
            tabla.addCell("Fecha");
            tabla.addCell("Total");
            tabla.addCell("ID Cliente");
            tabla.addCell("ID Empleado");

            // Usar la conexión de ConexionMySQL
            Connection cn = new ConexionMySQL().conexion();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM tb_venta");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tabla.addCell(rs.getString(1));
                tabla.addCell(rs.getString(2));
                tabla.addCell(rs.getString(3));
                tabla.addCell(rs.getString(4));
                tabla.addCell(rs.getString(5));
            }
            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte de Ventas creado");

        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SQLException e) {
            System.out.println("Error al generar el reporte: " + e);
        }
    }
}
