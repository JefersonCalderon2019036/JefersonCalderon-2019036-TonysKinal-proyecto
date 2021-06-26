package org.jefersoncalderon.beans;

public class TipoEmpleado {
  private int CodigoTipoEmpleado;
  private String DescripcionTipoEmpleado;

    public TipoEmpleado() {
    }

    public TipoEmpleado(int CodigoTipoEmpleado, String DescripcionTipoEmpleado) {
        this.CodigoTipoEmpleado = CodigoTipoEmpleado;
        this.DescripcionTipoEmpleado = DescripcionTipoEmpleado;
    }

    public int getCodigoTipoEmpleado() {
        return CodigoTipoEmpleado;
    }

    public void setCodigoTipoEmpleado(int CodigoTipoEmpleado) {
        this.CodigoTipoEmpleado = CodigoTipoEmpleado;
    }

    public String getDescripcionTipoEmpleado() {
        return DescripcionTipoEmpleado;
    }

    public void setDescripcionTipoEmpleado(String DescripcionTipoEmpleado) {
        this.DescripcionTipoEmpleado = DescripcionTipoEmpleado;
    }

    public String toString() {
        return CodigoTipoEmpleado + "|" + DescripcionTipoEmpleado ;
    }
  
  
}
