package org.jefersoncalderon.beans;

public class Empleados {
    private int CodigoEmpleado;
    private int NumeroEmpleado;
    private String ApellidosEmpleado;
    private String NombresEmpleado;
    private String DireccionEmpleado;
    private String TelefonoContacto;
    private String GradoCocinero;
    private int CodigoTipoEmpleado;

    public Empleados(int CodigoEmpleado, int NumeroEmpleado, String ApellidosEmpleado, String NombresEmpleado, String DireccionEmpleado, String TelefonoContacto, String GradoCocinero, int CodigoTipoEmpleado) {
        this.CodigoEmpleado = CodigoEmpleado;
        this.NumeroEmpleado = NumeroEmpleado;
        this.ApellidosEmpleado = ApellidosEmpleado;
        this.NombresEmpleado = NombresEmpleado;
        this.DireccionEmpleado = DireccionEmpleado;
        this.TelefonoContacto = TelefonoContacto;
        this.GradoCocinero = GradoCocinero;
        this.CodigoTipoEmpleado = CodigoTipoEmpleado;
    }

    public int getCodigoEmpleado() {
        return CodigoEmpleado;
    }

    public void setCodigoEmpleado(int CodigoEmpleado) {
        this.CodigoEmpleado = CodigoEmpleado;
    }

    public int getNumeroEmpleado() {
        return NumeroEmpleado;
    }

    public void setNumeroEmpleado(int NumeroEmpleado) {
        this.NumeroEmpleado = NumeroEmpleado;
    }

    public String getApellidosEmpleado() {
        return ApellidosEmpleado;
    }

    public void setApellidosEmpleado(String ApellidosEmpleado) {
        this.ApellidosEmpleado = ApellidosEmpleado;
    }

    public String getNombresEmpleado() {
        return NombresEmpleado;
    }

    public void setNombresEmpleado(String NombresEmpleado) {
        this.NombresEmpleado = NombresEmpleado;
    }

    public String getDireccionEmpleado() {
        return DireccionEmpleado;
    }

    public void setDireccionEmpleado(String DireccionEmpleado) {
        this.DireccionEmpleado = DireccionEmpleado;
    }

    public String getTelefonoContacto() {
        return TelefonoContacto;
    }

    public void setTelefonoContacto(String TelefonoContacto) {
        this.TelefonoContacto = TelefonoContacto;
    }

    public String getGradoCocinero() {
        return GradoCocinero;
    }

    public void setGradoCocinero(String GradoCocinero) {
        this.GradoCocinero = GradoCocinero;
    }

    public int getCodigoTipoEmpleado() {
        return CodigoTipoEmpleado;
    }

    public void setCodigoTipoEmpleado(int CodigoTipoEmpleado) {
        this.CodigoTipoEmpleado = CodigoTipoEmpleado;
    }

    public Empleados() {
    }

    @Override
    public String toString() {
        return CodigoEmpleado +"|" + ApellidosEmpleado + "|" + NombresEmpleado;
    }
    
}
