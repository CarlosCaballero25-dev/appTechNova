package Capa_Entidad;

/**
 *
 * @author Eduardo
 */
public class Categoria {

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private String prefijo;

    public Categoria() {
    }

    // Constructor
    public Categoria(int idCategoria, String nombre, String descripcion, String prefijo) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prefijo = prefijo;
    }

    // Getters y Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
}
