package org.jefersoncalderon.beans;

public class Empresa {
    private int CodigoEmpresa;
    private String NombreEmpresa;
    private String DireccionEmpresa;
    private String telefono;

    public int getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        CodigoEmpresa = codigoEmpresa;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        NombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return DireccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        DireccionEmpresa = direccionEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empresa() {
    }

    public Empresa(int codigoEmpresa, String nombreEmpresa, String direccionEmpresa, String telefono) {
        CodigoEmpresa = codigoEmpresa;
        NombreEmpresa = nombreEmpresa;
        DireccionEmpresa = direccionEmpresa;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return + CodigoEmpresa + "|" + NombreEmpresa ;
    }

    

}