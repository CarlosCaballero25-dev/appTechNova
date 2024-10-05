package Capa_Negocio;

import Capa_Datos.VentaDAO;
import Capa_Entidad.Venta;
import java.util.List;

public class Neg_Venta {

    private final VentaDAO ventaDAO;

    public Neg_Venta() {
        this.ventaDAO = new VentaDAO();
    }

    public List<Venta> listarVentas() {
        return ventaDAO.listarVentas();
    }

    public void guardarVenta(Venta venta) {
        ventaDAO.guardarVenta(venta, 0);
    }

    public void eliminarVenta(int idVenta) {
        ventaDAO.eliminarVenta(idVenta);
    }

    public void modificarVenta(Venta venta) {
        ventaDAO.modificarVenta(venta);
    }
}
