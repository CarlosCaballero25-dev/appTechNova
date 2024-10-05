/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidad;

/**
 *
 * @author Eduardo
 */
public class Cliente {

    private int idCliente;
    private String nomape;
    private int dni;
    private String telefono;
    private String direccion;


    public Cliente() {
    }

    
    // Constructor
    public Cliente(int idCliente, String nomape, int dni, String telefono, String direccion) {
        this.idCliente = idCliente;
        this.nomape = nomape;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomape() {
        return nomape;
    }

    public void setNomape(String nomape) {
        this.nomape = nomape;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
