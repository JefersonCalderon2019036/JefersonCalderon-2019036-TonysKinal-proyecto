package org.jefersoncalderon.beans;

public class TipoPlato {
    private int CodigoTipoPlato;
    private String DescripcionTipoPlato;

    public TipoPlato() {
    }

    public TipoPlato(int CodigoTipoPlato, String DescripcionTipoPlato) {
        this.CodigoTipoPlato = CodigoTipoPlato;
        this.DescripcionTipoPlato = DescripcionTipoPlato;
    }

    public int getCodigoTipoPlato() {
        return CodigoTipoPlato;
    }

    public void setCodigoTipoPlato(int CodigoTipoPlato) {
        this.CodigoTipoPlato = CodigoTipoPlato;
    }

    public String getDescripcionTipoPlato() {
        return DescripcionTipoPlato;
    }

    public void setDescripcionTipoPlato(String DescripcionTipoPlato) {
        this.DescripcionTipoPlato = DescripcionTipoPlato;
    }

    @Override
    public String toString() {
        return CodigoTipoPlato + "|" + DescripcionTipoPlato;
    }
    
    
}
