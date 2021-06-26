package org.jefersoncalderon.beans;

public class Producto {
 private int CodigoProducto;
 private String NombreProducto;
 private int Cantidad;

    public Producto() {
    }

    public Producto(int CodigoProducto, String NombreProducto, int Cantidad) {
        this.CodigoProducto = CodigoProducto;
        this.NombreProducto = NombreProducto;
        this.Cantidad = Cantidad;
    }

    public int getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(int CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    @Override
    public String toString() {
        return CodigoProducto + "|" + NombreProducto;
    }  
}
