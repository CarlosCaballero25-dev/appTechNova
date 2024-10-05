package Capa_Negocio;

import Capa_Datos.ProductoDAO;
import Capa_Entidad.Producto;
import java.util.List;

public class Neg_Producto {

    private final ProductoDAO productoDAO;

    public Neg_Producto() {
        this.productoDAO = new ProductoDAO();
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }

    public String agregarProducto(Producto producto) {
        return productoDAO.agregarProducto(producto);
    }

    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
    }

   public void modificarProducto(Producto producto) {
        productoDAO.modificarProducto(producto);
    }

    public boolean existeProducto(int idProducto) {
        return productoDAO.existeProducto(idProducto);
    }
}
