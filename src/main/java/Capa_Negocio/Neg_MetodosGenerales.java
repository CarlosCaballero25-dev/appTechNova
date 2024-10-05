package Capa_Negocio;

import Capa_Datos.MetodosGeneralesDAO;

/**
 *
 * @author Eduardo
 */
public class Neg_MetodosGenerales {

    private final MetodosGeneralesDAO metodosDAO;

    public Neg_MetodosGenerales() {
        this.metodosDAO = new MetodosGeneralesDAO();
    }

    public int obtenerProximoIdProducto() {
        return metodosDAO.obtenerProximoIdProducto(); // Llama al método del DAO
    }

    public String generarCodigoProducto(int idCategoria) {
        return metodosDAO.generarCodigoProducto(idCategoria); // Llama al método del DAO
    }
    
    public int obtenerProximoIdCliente(){
        return metodosDAO.obtenerProximoIdCliente();
    }

}
