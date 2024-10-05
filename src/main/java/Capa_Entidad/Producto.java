package Capa_Entidad;

import java.math.BigDecimal;

public class Producto {

    private int idProducto;
    private String nombre;
    private int cantidad;
    private BigDecimal precio;
    private String descripcion;
    private int idCategoria;
    private byte[] imagen; // O el tipo que estés utilizando para la imagen
    private String codigoProducto;

    public Producto() {
    }

    // Constructor
    public Producto(int idProducto, String nombre, int cantidad, BigDecimal precio,
            String descripcion, int idCategoria, byte[] imagen, String codigoProducto) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.codigoProducto = codigoProducto; // Inicializar el nuevo campo
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getCodigoProducto() { // Getter para codigoProducto
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) { // Setter para codigoProducto
        this.codigoProducto = codigoProducto;
    }
}
