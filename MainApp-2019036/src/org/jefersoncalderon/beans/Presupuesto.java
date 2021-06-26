package org.jefersoncalderon.beans;

import java.util.Date;

public class Presupuesto {
 private int  CodigoPresupuesto;
 private Date FechaSolicitud;
 private Double CantidadPresupuesto;
 private int CodigoEmpresa;

    public Presupuesto(int CodigoPresupuesto, Date FechaSolicitud, Double CantidadPresupuesto, int CodigoEmpresa) {
        this.CodigoPresupuesto = CodigoPresupuesto;
        this.FechaSolicitud = FechaSolicitud;
        this.CantidadPresupuesto = CantidadPresupuesto;
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public Presupuesto() {
    }

    public int getCodigoPresupuesto() {
        return CodigoPresupuesto;
    }

    public void setCodigoPresupuesto(int CodigoPresupuesto) {
        this.CodigoPresupuesto = CodigoPresupuesto;
    }

    public Date getFechaSolicitud() {
        return FechaSolicitud;
    }

    public void setFechaSolicitud(Date FechaSolicitud) {
        this.FechaSolicitud = FechaSolicitud;
    }

    public Double getCantidadPresupuesto() {
        return CantidadPresupuesto;
    }

    public void setCantidadPresupuesto(Double CantidadPresupuesto) {
        this.CantidadPresupuesto = CantidadPresupuesto;
    }

    public int getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(int CodigoEmpresa) {
        this.CodigoEmpresa = CodigoEmpresa;
    }

 


}
