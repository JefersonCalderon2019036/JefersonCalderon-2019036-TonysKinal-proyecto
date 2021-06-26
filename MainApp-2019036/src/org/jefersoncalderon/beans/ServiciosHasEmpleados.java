package org.jefersoncalderon.beans;

import java.util.Date;

public class ServiciosHasEmpleados {
  private int Servicios_CodigoServicio;
  private int Empleado_CodigoEmpleado;
  private Date FechaEvento;
  private String HoraEvento;
  private String LugarEvento;

    public ServiciosHasEmpleados() {
    }

    public ServiciosHasEmpleados(int Servicios_CodigoServicio, int Empleado_CodigoEmpleado, Date FechaEvento, String HoraEvento, String LugarEvento) {
        this.Servicios_CodigoServicio = Servicios_CodigoServicio;
        this.Empleado_CodigoEmpleado = Empleado_CodigoEmpleado;
        this.FechaEvento = FechaEvento;
        this.HoraEvento = HoraEvento;
        this.LugarEvento = LugarEvento;
    }

    public int getServicios_CodigoServicio() {
        return Servicios_CodigoServicio;
    }

    public void setServicios_CodigoServicio(int Servicios_CodigoServicio) {
        this.Servicios_CodigoServicio = Servicios_CodigoServicio;
    }

    public int getEmpleado_CodigoEmpleado() {
        return Empleado_CodigoEmpleado;
    }

    public void setEmpleado_CodigoEmpleado(int Empleado_CodigoEmpleado) {
        this.Empleado_CodigoEmpleado = Empleado_CodigoEmpleado;
    }

    public Date getFechaEvento() {
        return FechaEvento;
    }

    public void setFechaEvento(Date FechaEvento) {
        this.FechaEvento = FechaEvento;
    }

    public String getHoraEvento() {
        return HoraEvento;
    }

    public void setHoraEvento(String HoraEvento) {
        this.HoraEvento = HoraEvento;
    }

    public String getLugarEvento() {
        return LugarEvento;
    }

    public void setLugarEvento(String LugarEvento) {
        this.LugarEvento = LugarEvento;
    }
  
}
