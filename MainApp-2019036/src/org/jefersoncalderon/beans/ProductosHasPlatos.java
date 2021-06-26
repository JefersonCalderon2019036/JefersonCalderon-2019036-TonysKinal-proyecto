package org.jefersoncalderon.beans;

public class ProductosHasPlatos {
 private int Producto_CodigoProducto;
 private int Platos_CodigoPlatos;

    public ProductosHasPlatos() {
    }

    public ProductosHasPlatos(int Producto_CodigoProducto, int Platos_CodigoPlatos) {
        this.Producto_CodigoProducto = Producto_CodigoProducto;
        this.Platos_CodigoPlatos = Platos_CodigoPlatos;
    }

    public int getProducto_CodigoProducto() {
        return Producto_CodigoProducto;
    }

    public void setProducto_CodigoProducto(int Producto_CodigoProducto) {
        this.Producto_CodigoProducto = Producto_CodigoProducto;
    }

    public int getPlatos_CodigoPlatos() {
        return Platos_CodigoPlatos;
    }

    public void setPlatos_CodigoPlatos(int Platos_CodigoPlatos) {
        this.Platos_CodigoPlatos = Platos_CodigoPlatos;
    }
    
}
