package controlador;

import Capa_Entidad.DetalleVenta;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

public class DetalleVentaPDF {

    private JTable tblVenta; // Recibe la tabla de ventas
    private String nombreCliente;
    private String nombreUsuario;
    private String nomape;
    private int dni;
    private String telefono;
    private String direccion;
    private double totalFinal;
    private String fechaVenta; // Nueva variable para la fecha de venta

    public DetalleVentaPDF(JTable tblVenta, String nombreCliente, String nombreUsuario, String nomape, int dni, String telefono, String direccion, double totalFinal, String fechaVenta) {
        this.tblVenta = tblVenta;
        this.nombreCliente = nombreCliente;
        this.nombreUsuario = nombreUsuario;
        this.nomape = nomape;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.totalFinal = totalFinal;
        this.fechaVenta = fechaVenta; // Inicializa la fecha de venta
    }

    // Método para generar el PDF
    public void generarFacturaPDF() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                String fechaNueva = fechaActual.replace("/", "_");
                String nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";

                String rutaArchivo = "C:/Users/Eduardo/Documents/NetBeansProjects/appSystemTechNova_CECA/src/main/resources/pdf/" + nombreArchivoPDFVenta;

                try (FileOutputStream archivo = new FileOutputStream(new File(rutaArchivo))) {
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, archivo);
                    doc.open();

                    Image img = Image.getInstance("src/main/resources/Imagenes/TechNova2.png");
                    img.scaleToFit(250, 250);
                    doc.add(img);

                    crearEncabezado(doc, fechaActual);
                    crearDatosCliente(doc);
                    crearTablaProductos(doc);

                    Paragraph totalFinalParrafo = new Paragraph("TOTAL: S/ " + String.format("%.2f", totalFinal));
                    totalFinalParrafo.setAlignment(Element.ALIGN_RIGHT);
                    doc.add(totalFinalParrafo);

                    doc.close();
                    abrirPDF(rutaArchivo);

                } catch (DocumentException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al crear el documento PDF: " + e.getMessage(), "Error PDF", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();
    }

    private void crearEncabezado(Document doc, String fechaActual) throws DocumentException {
        Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);

        PdfPTable encabezado = new PdfPTable(2); // Usamos 2 columnas
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] columnasEncabezado = {30f, 70f}; // Ajustamos el tamaño de las columnas
        encabezado.setWidths(columnasEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Información de la empresa en la primera celda
        PdfPCell celdaEmpresa = new PdfPCell();
        celdaEmpresa.setBorder(0);
        celdaEmpresa.addElement(new Paragraph("RUC: 0987654321001", negrita));
        celdaEmpresa.addElement(new Paragraph("NOMBRE: TECHNOVA", negrita));
        celdaEmpresa.addElement(new Paragraph("TELÉFONO: 936129441", negrita));
        celdaEmpresa.addElement(new Paragraph("DIRECCIÓN: COMAS CITY", negrita));
        encabezado.addCell(celdaEmpresa);

        // Factura y fecha en la segunda celda
        PdfPCell celdaFactura = new PdfPCell();
        celdaFactura.setBorder(0);
        celdaFactura.addElement(new Paragraph("Factura: 001", negrita));
        celdaFactura.addElement(new Paragraph("Fecha: " + fechaVenta, negrita)); // Usa fechaVenta en lugar de fechaActual
        encabezado.addCell(celdaFactura);

        // Agregar tabla de encabezado al documento
        doc.add(encabezado);
    }

    private void crearDatosCliente(Document doc) throws DocumentException {
        // Imprimir en consola para verificar el valor
        System.out.println("Nombre Cliente: " + nombreCliente);

        Paragraph cliente = new Paragraph();
        cliente.add("\nDatos del Cliente:\n");
        cliente.add("Nombre: " + nomape + "\n"); // Esto es el nombre completo
        cliente.add("Nombre Cliente: " + nombreCliente + "\n"); // Agregar el nombre del cliente aquí
        doc.add(cliente);
    }

    private void crearTablaProductos(Document doc) throws DocumentException {
        if (tblVenta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en la venta.");
            return;
        }

        PdfPTable tabla = new PdfPTable(6); // Seis columnas
        tabla.setWidthPercentage(100);
        float[] anchosColumnas = {20f, 50f, 30f, 30f, 30f, 30f}; // Anchos de las columnas
        tabla.setWidths(anchosColumnas);

        // Encabezado de la tabla
        agregarCeldaEncabezado(tabla, "Cantidad");
        agregarCeldaEncabezado(tabla, "Descripción");
        agregarCeldaEncabezado(tabla, "Precio Unitario");
        agregarCeldaEncabezado(tabla, "Subtotal");
        agregarCeldaEncabezado(tabla, "IGV");
        agregarCeldaEncabezado(tabla, "Total");

        // Variables for totals
        double subtotalTotal = 0;
        double igvTotal = 0;
        double totalPagar = 0;

        // Obtener los datos de tblVenta
        for (int i = 0; i < tblVenta.getRowCount(); i++) {
            try {
                int cantidad = Integer.parseInt(tblVenta.getValueAt(i, 1).toString());
                String descripcion = tblVenta.getValueAt(i, 0).toString();
                String precioUnitarioStr = tblVenta.getValueAt(i, 2).toString().replace("S/ ", "").replace(",", "").trim();
                double precioUnitario = Double.parseDouble(precioUnitarioStr);

                // Calcular subtotal, IGV y total
                double subtotal = cantidad * precioUnitario;
                double igv = subtotal * 0.10; // 10% IGV
                double total = subtotal + igv;

                // Agregar detalles del producto a la tabla
                tabla.addCell(String.valueOf(cantidad)); // Cantidad
                tabla.addCell(descripcion); // Descripción
                tabla.addCell("S/ " + String.format("%.2f", precioUnitario)); // Precio Unitario
                tabla.addCell("S/ " + String.format("%.2f", subtotal)); // Subtotal
                tabla.addCell("S/ " + String.format("%.2f", igv)); // IGV
                tabla.addCell("S/ " + String.format("%.2f", total)); // Total

                // Acumular totales
                subtotalTotal += subtotal;
                igvTotal += igv;
                totalPagar += total;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al procesar la fila " + (i + 1) + ": " + e.getMessage(), "Error en el formato", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Agregar fila final para cálculos totales
        tabla.addCell(""); // Celda vacía para espaciar
        tabla.addCell("TOTAL:"); // Etiqueta de total
        tabla.addCell(""); // Celda vacía para espaciar
        tabla.addCell("S/ " + String.format("%.2f", subtotalTotal)); // Valor subtotal
        tabla.addCell("S/ " + String.format("%.2f", igvTotal)); // Valor IGV
        tabla.addCell("S/ " + String.format("%.2f", totalPagar)); // Valor total

        doc.add(tabla);
    }

    private void agregarCeldaEncabezado(PdfPTable tabla, String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }

    private void abrirPDF(String rutaArchivo) {
        try {
            File pdfFile = new File(rutaArchivo);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo PDF no se encontró: " + rutaArchivo, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
