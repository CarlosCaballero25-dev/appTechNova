package Capa_Entidad;

import java.sql.Date;


public class Venta {

    private int idVenta;
    private int idCliente;
    private int idUsuario;
    private double valorPagar;
    private double igv; // Nuevo campo
    private double vuelto; // Nuevo campo
    private Date fechaVenta;

    // Constructor actualizado
    public Venta(int idVenta, int idCliente, int idUsuario, double valorPagar, double igv, double vuelto, Date fechaVenta) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.valorPagar = valorPagar;
        this.igv = igv; // Asignar IGV
        this.vuelto = vuelto; // Asignar vuelto
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    
}
