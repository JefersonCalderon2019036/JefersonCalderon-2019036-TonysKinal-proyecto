package org.jefersoncalderon.beans;

public class Platos {
 private int CodigoPlato;
 private int Cantidad;
 private String NombreaPlato;
 private String DescripcionPlato;
 private double PrecioPlato;
 private int CodigoCocinero;
 private int CodigoTipoPlato;

    public Platos() {
    }

    public Platos(int CodigoPlato, int Cantidad, String NombreaPlato, String DescripcionPlato, double PrecioPlato, int CodigoCocinero, int CodigoTipoPlato) {
        this.CodigoPlato = CodigoPlato;
        this.Cantidad = Cantidad;
        this.NombreaPlato = NombreaPlato;
        this.DescripcionPlato = DescripcionPlato;
        this.PrecioPlato = PrecioPlato;
        this.CodigoCocinero = CodigoCocinero;
        this.CodigoTipoPlato = CodigoTipoPlato;
    }

    public int getCodigoPlato() {
        return CodigoPlato;
    }

    public void setCodigoPlato(int CodigoPlato) {
        this.CodigoPlato = CodigoPlato;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getNombreaPlato() {
        return NombreaPlato;
    }

    public void setNombreaPlato(String NombreaPlato) {
        this.NombreaPlato = NombreaPlato;
    }

    public String getDescripcionPlato() {
        return DescripcionPlato;
    }

    public void setDescripcionPlato(String DescripcionPlato) {
        this.DescripcionPlato = DescripcionPlato;
    }

    public double getPrecioPlato() {
        return PrecioPlato;
    }

    public void setPrecioPlato(double PrecioPlato) {
        this.PrecioPlato = PrecioPlato;
    }

    public int getCodigoCocinero() {
        return CodigoCocinero;
    }

    public void setCodigoCocinero(int CodigoCocinero) {
        this.CodigoCocinero = CodigoCocinero;
    }

    public int getCodigoTipoPlato() {
        return CodigoTipoPlato;
    }

    public void setCodigoTipoPlato(int CodigoTipoPlato) {
        this.CodigoTipoPlato = CodigoTipoPlato;
    }

    @Override
    public String toString() {
        return  CodigoPlato + "|" + NombreaPlato ;
    }
   
}
