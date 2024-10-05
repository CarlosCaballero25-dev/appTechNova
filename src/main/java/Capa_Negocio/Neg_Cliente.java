package Capa_Negocio;

import Capa_Datos.ClienteDAO;
import Capa_Entidad.Cliente;
import java.util.List;

public class Neg_Cliente {

    private final ClienteDAO clienteDAO;

    public Neg_Cliente() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public String agregarCliente(Cliente cliente) {
        return clienteDAO.agregarCliente(cliente);
    }

    public void eliminarCliente(int idCliente) {
        clienteDAO.eliminarCliente(idCliente);
    }

    public void modificarCliente(Cliente cliente) {
        clienteDAO.modificarCliente(cliente);
    }

    public boolean existeCliente(int idCliente) {
        return clienteDAO.existeCliente(idCliente);
    }
}
