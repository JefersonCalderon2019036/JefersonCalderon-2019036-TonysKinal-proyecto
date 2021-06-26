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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Platos;
import org.jefersoncalderon.beans.Servicios;
import org.jefersoncalderon.beans.ServiciosHasPlatos;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class ServiciosHasPlatosController implements Initializable {

    @FXML private TableView tblServiciosHasPlatos;
    @FXML private TableColumn colServicios;
    @FXML private TableColumn colPlatos;
    @FXML private Button btnNuevo;
    @FXML private Button btnReporte;
    @FXML private ComboBox cmbServicios;
    @FXML private ComboBox cmbPlatos;
          private MainApp escenarioPrincipal;
          private enum Operacion{GUARDAR,NINGUNO}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Platos>ListaPlato;
          private ObservableList<Servicios>ListaServicio;
          private ObservableList<ServiciosHasPlatos>ListaProductosHasPlatos;
          
    public MainApp getEscenarioPrincipal() {
        return escenarioPrincipal;
    }
    public void setEscenarioPrincipal(MainApp escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void DesactivarControles(){
    cmbServicios.setEditable(false);
    cmbPlatos.setEditable(false);
    }
    
    public void ActivarControles(){
    cmbServicios.setEditable(true);
    cmbPlatos.setEditable(true);
    }
    
    public void LimpiarControles(){
    cmbServicios.getSelectionModel().clearSelection();
    cmbServicios.getSelectionModel().select(null);
    cmbPlatos.getSelectionModel().clearSelection();
    cmbPlatos.getSelectionModel().select(null);
    }
    
    public void BotonesDefecto(){
    btnReporte.setDisable(false);
    btnNuevo.setDisable(false);
    btnReporte.setText("Reporte");
    btnNuevo.setText("Nuevo");
    }
    
    @FXML
    private void SeleccionarElemento() {
        cmbServicios.getSelectionModel().select(BuscarServicios(((ServiciosHasPlatos)tblServiciosHasPlatos.getSelectionModel().getSelectedItem()).getServicios_CodigoServicio()));
        cmbPlatos.getSelectionModel().select(BuscarPlato(((ServiciosHasPlatos)tblServiciosHasPlatos.getSelectionModel().getSelectedItem()).getPlatos_CodigoPlatos()));
    }

    @FXML
    private void CargarDatos() {
       tblServiciosHasPlatos.setItems(getServiciosHasPlatos()); 
       colServicios.setCellValueFactory(new PropertyValueFactory<ServiciosHasPlatos, Integer>("Servicios_CodigoServicio"));
       colPlatos.setCellValueFactory(new PropertyValueFactory<ServiciosHasPlatos, Integer>("Platos_CodigoPlatos"));
    }

    @FXML
    private void MenuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }

    @FXML
    private void Nuevo() {
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
        ServiciosHasPlatos Nuevo=new ServiciosHasPlatos();
        Nuevo.setServicios_CodigoServicio(((Servicios)cmbServicios.getSelectionModel().getSelectedItem()).getCodigoServicios());
        Nuevo.setPlatos_CodigoPlatos(((Platos)cmbPlatos.getSelectionModel().getSelectedItem()).getCodigoPlato());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarServicios_has_Platos(?,?)}");
            sp.setInt(1, Nuevo.getServicios_CodigoServicio());
            sp.setInt(2, Nuevo.getPlatos_CodigoPlatos());
            sp.execute();
           ListaProductosHasPlatos.add(Nuevo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
    }
    
    public ObservableList<ServiciosHasPlatos> getServiciosHasPlatos(){
        ArrayList<ServiciosHasPlatos> lista = new ArrayList<ServiciosHasPlatos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarServicios_Has_Platos()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new ServiciosHasPlatos(resultado.getInt("Servicios_CodigoServicio"),
                                        resultado.getInt("Platos_CodigoPlatos")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaProductosHasPlatos = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Servicios> getServicio(){
        ArrayList<Servicios> lista = new ArrayList<Servicios>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarListaServicio()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Servicios(resultado.getInt("CodigoServicios"),
                                        resultado.getDate("FechaServicios"),
                                        resultado.getString("TipoServicio"),
                                        resultado.getString("HoraServicio"),
                                        resultado.getString("LugarServicio"),
                                        resultado.getString("TelefonoContacto"),
                                        resultado.getInt("CodigoEmpresa")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaServicio = FXCollections.observableArrayList(lista);
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
    
    public Servicios BuscarServicios(int Codigo){
    Servicios Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarServicio(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new Servicios(registro.getInt("CodigoServicios"),
                                        registro.getDate("FechaServicios"),
                                        registro.getString("TipoServicio"),
                                        registro.getString("HoraServicio"),
                                        registro.getString("LugarServicio"),
                                        registro.getString("TelefonoContacto"),
                                        registro.getInt("CodigoEmpresa"));
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
        DesactivarControles();
        CargarDatos();
        cmbPlatos.getItems().addAll(getPlato());
        cmbServicios.getItems().addAll(getServicio());
    }    
    
}
