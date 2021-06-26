package org.jefersoncalderon.beans;

import java.util.Date;

public class Servicios {
 private int CodigoServicios;
 private Date FechaServicios;
 private String TipoServicio;
 private String HoraServicio;
 private String LugarServicio;
 private String TelefonoContacto;
 private int CodigoEmpresa;

    public int getCodigoServicios() {
        return CodigoServicios;
    }

    public void setCodigoServicios(int CodigoServicios) {
        this.CodigoServicios = CodigoServicios;
    }

    public Date getFechaServicios() {
        return FechaServicios;
    }

    public void setFechaServicios(Date FechaServicios) {
        this.FechaServicios = FechaServicios;
    }

    public String getTipoServicio() {
        return TipoServicio;
    }

    public void setTipoServicio(String TipoServicio) {
        this.TipoServicio = TipoServicio;
    }

    public String getHoraServicio() {
        return HoraServicio;
    }

    public void setHoraServicio(String HoraServicio) {
        this.HoraServicio = HoraServicio;
    }

    public String getLugarServicio() {
        return LugarServicio;
    }

    public void setLugarServicio(String LugarServicio) {
        this.LugarServicio = LugarServicio;
    }

    public String getTelefonoContacto() {
        return TelefonoContacto;
    }

    public void setTelefonoContacto(String TelefonoContacto) {
        this.TelefonoContacto = TelefonoContacto;
    }

    public int getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(int CodigoEmpresa) {
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public Servicios() {
    }

    public Servicios(int CodigoServicios, Date FechaServicios, String TipoServicio, String HoraServicio, String LugarServicio, String TelefonoContacto, int CodigoEmpresa) {
        this.CodigoServicios = CodigoServicios;
        this.FechaServicios = FechaServicios;
        this.TipoServicio = TipoServicio;
        this.HoraServicio = HoraServicio;
        this.LugarServicio = LugarServicio;
        this.TelefonoContacto = TelefonoContacto;
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public String toString() {
        return CodigoServicios + "|"+ CodigoEmpresa;
    }
    
    
}
