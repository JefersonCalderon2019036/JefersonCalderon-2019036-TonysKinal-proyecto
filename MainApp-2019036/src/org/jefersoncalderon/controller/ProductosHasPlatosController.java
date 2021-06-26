package org.jefersoncalderon.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Platos;
import org.jefersoncalderon.beans.Producto;
import org.jefersoncalderon.beans.ProductosHasPlatos;
import org.jefersoncalderon.beans.Servicios;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class ProductosHasPlatosController implements Initializable {

    @FXML private TableView tblProductoshasPlatos;
    @FXML private TableColumn colProducto;
    @FXML private TableColumn colPlatos;
    @FXML private Button btnNuevo;
    @FXML private Button btnReporte;
    @FXML private ComboBox cmbProducto;
    @FXML private ComboBox cmbPlato;
          private MainApp escenarioPrincipal;
          private enum Operacion{GUARDAR,NINGUNO}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Platos>ListaPlato;
          private ObservableList<Producto>ListaProducto;
          private ObservableList<ProductosHasPlatos>ListaProductosHasPlatos;
          
    public MainApp getEscenarioPrincipal() {
        return escenarioPrincipal;
    }
    public void setEscenarioPrincipal(MainApp escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    private void MenuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }
    
    public void DesactivarControles(){
    cmbProducto.setEditable(false);
    cmbPlato.setEditable(false);
    }
    
    public void ActivarControles(){
    cmbProducto.setEditable(false);
    cmbPlato.setEditable(false);
    }
    
    public void LimpiarControles(){
    cmbProducto.getSelectionModel().clearSelection();
    cmbProducto.getSelectionModel().select(null);
    cmbPlato.getSelectionModel().clearSelection();
    cmbPlato.getSelectionModel().select(null);
    }
    
    public void BotonesDefecto(){
    btnReporte.setDisable(false);
    btnNuevo.setDisable(false);
    btnReporte.setText("Reporte");
    btnNuevo.setText("Nuevo");
    }
    
    @FXML
    public void CargarDatos(){
    tblProductoshasPlatos.setItems(getProductosHasPlatos());
    colProducto.setCellValueFactory(new PropertyValueFactory<ProductosHasPlatos, Integer>("Producto_CodigoProducto"));
    colPlatos.setCellValueFactory(new PropertyValueFactory<ProductosHasPlatos, Integer>("Platos_CodigoPlatos"));
    }
    
    @FXML
    public void SeleccionarElemento(){
    cmbProducto.getSelectionModel().select(BuscarProducto(((ProductosHasPlatos)tblProductoshasPlatos.getSelectionModel().getSelectedItem()).getProducto_CodigoProducto()));
    cmbPlato.getSelectionModel().select(BuscarPlato(((ProductosHasPlatos)tblProductoshasPlatos.getSelectionModel().getSelectedItem()).getPlatos_CodigoPlatos()));
    }
    
    @FXML
    private void Nuevo(){
        switch(TipoOperacion){
            case NINGUNO:
                    ActivarControles();
                    btnReporte.setText("Cancelar");
                    btnNuevo.setText("Guardar");
                    TipoOperacion = Operacion.GUARDAR;
                    break;
                    
            case GUARDAR:
                    Guardar();
                    BotonesDefecto();
                    LimpiarControles();
                    DesactivarControles();
                    TipoOperacion = Operacion.NINGUNO;
                    break;
                    
            default:
                    BotonesDefecto();
                    LimpiarControles();
                    DesactivarControles();
                    TipoOperacion = Operacion.NINGUNO;
                    break;
        } 
         CargarDatos();
    }

    @FXML
    private void Reporte() {
        switch(TipoOperacion){
            case NINGUNO:
                    break;
            default:
                    BotonesDefecto();
                    LimpiarControles();
                    DesactivarControles();
                    TipoOperacion = Operacion.NINGUNO;
                    break;
        } 
         CargarDatos();
    }
    
    public void Guardar(){
        ProductosHasPlatos Nuevo=new ProductosHasPlatos();
        Nuevo.setProducto_CodigoProducto(((Producto)cmbProducto.getSelectionModel().getSelectedItem()).getCodigoProducto());
        Nuevo.setPlatos_CodigoPlatos(((Platos)cmbPlato.getSelectionModel().getSelectedItem()).getCodigoPlato());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarProductos_Has_Platos(?,?)}");
            sp.setInt(1, Nuevo.getProducto_CodigoProducto());
            sp.setInt(2, Nuevo.getPlatos_CodigoPlatos());
            sp.execute();
           ListaProductosHasPlatos.add(Nuevo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
    }
    
    public ObservableList<ProductosHasPlatos> getProductosHasPlatos(){
    ArrayList<ProductosHasPlatos> lista = new ArrayList<ProductosHasPlatos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarProductos_has_Platos()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new ProductosHasPlatos(resultado.getInt("Producto_CodigoProducto"),
                                     resultado.getInt("Platos_CodigoPlatos")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaProductosHasPlatos = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Platos> getPlato(){
        ArrayList<Platos> lista = new ArrayList<Platos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarPlatos()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Platos(resultado.getInt("CodigoPlato"),
                                     resultado.getInt("Cantidad"),
                                     resultado.getString("NombreaPlato"),
                                     resultado.getString("DescripcionPlato"),
                                     resultado.getDouble("PrecioPlato"),
                                     resultado.getInt("CodigoCocinero"),
                                     resultado.getInt("CodigoTipoPlato")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaPlato = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Producto> getProducto(){
        ArrayList<Producto> lista = new ArrayList<Producto>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarProductos()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Producto(resultado.getInt("CodigoProducto"),
                                      resultado.getString("NombreProducto"),
                                      resultado.getInt("Cantidad")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaProducto = FXCollections.observableArrayList(lista);
    }
    
    public Producto BuscarProducto(int Codigo){
    Producto Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarProducto(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new Producto(registro.getInt("CodigoProducto"),
                                      registro.getString("NombreProducto"),
                                      registro.getInt("Cantidad")) ;
        }
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, "No se encontro los datos");
      System.out.println("No se a podido encontrar los datos");
      e.printStackTrace();  
    }
    return Resultado;
    }
    public Platos BuscarPlato(int Codigo){
    Platos Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarPlato(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new Platos(registro.getInt("CodigoPlato"),
                                     registro.getInt("Cantidad"),
                                     registro.getString("NombreaPlato"),
                                     registro.getString("DescripcionPlato"),
                                     registro.getDouble("PrecioPlato"),
                                     registro.getInt("CodigoCocinero"),
                                     registro.getInt("CodigoTipoPlato"));
        }
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, "No se encontro los datos");
      System.out.println("No se a podido encontrar los datos");
      e.printStackTrace();  
    }
    return Resultado;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbProducto.getItems().addAll(getProducto());
        cmbPlato.getItems().addAll(getPlato());
        CargarDatos();
        DesactivarControles();
    }    
    
}
