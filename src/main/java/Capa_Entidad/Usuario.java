package Capa_Entidad;

import java.util.Date;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String nomape;
    private Date fecna;
    private String password;
    private int telefono;
    private int DNI;
    private String rol;
    private String estado;
    private byte[] imagen;

    public Usuario() {
    }

    
    // Constructor
    public Usuario(int idUsuario, String usuario, String nomape, Date fecna,
            String password, int telefono, int DNI, String rol, String estado, byte[] imagen) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nomape = nomape;
        this.fecna = fecna;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.estado = estado;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomape() {
        return nomape;
    }

    public void setNomape(String nomape) {
        this.nomape = nomape;
    }

    public Date getFecna() {
        return fecna;
    }

    public void setFecna(Date fecna) {
        this.fecna = fecna;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
