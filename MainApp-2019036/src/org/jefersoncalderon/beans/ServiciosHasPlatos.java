package org.jefersoncalderon.beans;


public class ServiciosHasPlatos {
  private int Servicios_CodigoServicio;
  private int Platos_CodigoPlatos;

    public ServiciosHasPlatos() {
    }

    public ServiciosHasPlatos(int Servicios_CodigoServicio, int Platos_CodigoPlatos) {
        this.Servicios_CodigoServicio = Servicios_CodigoServicio;
        this.Platos_CodigoPlatos = Platos_CodigoPlatos;
    }

    public int getServicios_CodigoServicio() {
        return Servicios_CodigoServicio;
    }

    public void setServicios_CodigoServicio(int Servicios_CodigoServicio) {
        this.Servicios_CodigoServicio = Servicios_CodigoServicio;
    }

    public int getPlatos_CodigoPlatos() {
        return Platos_CodigoPlatos;
    }

    public void setPlatos_CodigoPlatos(int Platos_CodigoPlatos) {
        this.Platos_CodigoPlatos = Platos_CodigoPlatos;
    }
  
}
