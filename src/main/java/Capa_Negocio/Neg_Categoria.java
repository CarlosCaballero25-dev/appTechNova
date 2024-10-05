package Capa_Negocio;

import Capa_Datos.CategoriaDAO;
import Capa_Entidad.Categoria;
import java.util.List;

public class Neg_Categoria {

    private final CategoriaDAO categoriaDAO;

    public Neg_Categoria() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public String guardarCategoria(Categoria categoria) {
        return categoriaDAO.guardarCategoria(categoria);
    }

    public void eliminarCategoria(int idCategoria) {
        categoriaDAO.eliminarCategoria(idCategoria);
    }

    public String actualizarCategoria(Categoria categoria) {
        return categoriaDAO.actualizarCategoria(categoria);
    }

    public boolean existeCategoria(int idCategoria) {
        return categoriaDAO.existeCategoria(idCategoria);
    }
    public List<String> listarNombresCategorias(){
        return categoriaDAO.listarNombresCategorias();
    }
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        return categoriaDAO.obtenerCategoriaPorNombre(nombreCategoria);
    }
}
