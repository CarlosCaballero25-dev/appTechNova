package Capa_Entidad;

import java.util.Date;

public class DetalleVenta {

    private int idDetalleVenta;         // ID del detalle de la venta
    private double PrecioUnitario;
    private String nombreProducto;      // Nombre del producto
    private int cantidad;               // Cantidad vendida
    private double subtotal;            // Subtotal de la venta
    private double igv;                 // IGV (Impuesto General a las Ventas)
    private double totalPagar;          // Total a pagar (subtotal + igv)
    private String nombreUsuario;       // Nombre del usuario que realiza la venta
    private String nombreCliente;       // Nombre del cliente
    private Date fechaVenta;            // Fecha de la venta

    // Constructor por defecto
    public DetalleVenta() {
    }

    // Constructor con parámetros
    public DetalleVenta(int idDetalleVenta, double PrecioUnitario,String nombreProducto, int cantidad,
            double subtotal, double igv, double totalPagar,
            String nombreUsuario, String nombreCliente, Date fechaVenta) {
        this.idDetalleVenta = idDetalleVenta;
        this.PrecioUnitario= PrecioUnitario;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.igv = igv;                       // Inicializa el IGV
        this.totalPagar = totalPagar;       // Inicializa el total a pagar
        this.nombreUsuario = nombreUsuario;  // Inicializa el nombre del usuario
        this.nombreCliente = nombreCliente;  // Inicializa el nombre del cliente
        this.fechaVenta = fechaVenta;        // Inicializa la fecha de la venta
    }

    // Getters y Setters
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }
    

    // Eliminar el getter y setter para idVenta
    // public int getIdVenta() { return idVenta; }
    // public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Eliminar el getter y setter para precioUnitario
    // public double getPrecioUnitario() { return precioUnitario; }
    // public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;                           // Getter para IGV
    }

    public void setIgv(double igv) {
        this.igv = igv;                       // Setter para IGV
    }

    public double getTotalPagar() {
        return totalPagar;                   // Getter para total a pagar
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;       // Setter para total a pagar
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
