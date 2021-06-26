package org.jefersoncalderon.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Empresa;
import org.jefersoncalderon.beans.Presupuesto;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class PresupuestoController implements Initializable {

    @FXML private GridPane grpFecha;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtCantidad;
    @FXML private ComboBox cmbEmpresa;
    @FXML private Label LabelCodigo;
    @FXML private Label LabelFecha;
    @FXML private Label LabeCantidad;
    @FXML private Label LabelEmpresa;
    @FXML private TableView tblPresupuesto;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colFecha;
    @FXML private TableColumn colCantidad;
    @FXML private TableColumn colEmpresa;
    @FXML private Button btnReporte;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
          private DatePicker Fecha;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Presupuesto>ListaPresupuesto;
          private ObservableList<Empresa>ListaEmpresa;
          
    public MainApp getEscenarioPrincipal() {
        return escenarioPrincipal;
    }
    public void setEscenarioPrincipal(MainApp escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void FormatoFecha(){
       Fecha = new DatePicker(Locale.ENGLISH);
       Fecha.setDateFormat(new SimpleDateFormat("yyy-MM-dd"));
       Fecha.getCalendarView().todayButtonTextProperty().set("Today");
       Fecha.getCalendarView().setShowWeeks(false);
       grpFecha.add(Fecha,1,3);
       Fecha.getStylesheets().add("/org/jefersoncalderon/resorce/DatePicker.css");
     }
    
    @FXML
    private void MenuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }
    
    public void DesactivarControles(){
    txtCodigo.setEditable(false);
    txtCantidad.setEditable(false);
    cmbEmpresa.setEditable(false);
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtCantidad.setEditable(true);
    cmbEmpresa.setEditable(true);
    }
    
    public void LimpiarControles(){
    cmbEmpresa.getSelectionModel().clearSelection();
    cmbEmpresa.getSelectionModel().select(null);
    txtCodigo.setText("");
    txtCantidad.setText("");
    Fecha.selectedDateProperty().set(null);
    }
    
    public void BotonesDefecto(){
    btnReporte.setDisable(false);
    btnEliminar.setDisable(false);
    btnEditar.setDisable(false);
    btnNuevo.setDisable(false);
    btnEliminar.setText("Eliminar");
    btnReporte.setText("Reportar");
    btnEditar.setText("Editar");
    btnNuevo.setText("Nuevo");
    }
    
    @FXML
    public void CargarDatos(){
    tblPresupuesto.setItems(getPresupuesto());
    colCodigo.setCellValueFactory(new PropertyValueFactory<Presupuesto, Integer>("CodigoPresupuesto"));
    colFecha.setCellValueFactory(new PropertyValueFactory<Presupuesto, Date>("FechaSolicitud"));
    colEmpresa.setCellValueFactory(new PropertyValueFactory<Presupuesto, Integer>("CodigoEmpresa")); 
    colCantidad.setCellValueFactory(new PropertyValueFactory<Presupuesto, Double>("CantidadPresupuesto"));
    }
    
    @FXML
    public void SeleccionarElemento(){
    txtCodigo.setText(String.valueOf(((Presupuesto)tblPresupuesto.getSelectionModel().getSelectedItem()).getCodigoPresupuesto()));
    txtCantidad.setText(String.valueOf(((Presupuesto)tblPresupuesto.getSelectionModel().getSelectedItem()).getCantidadPresupuesto()));
    cmbEmpresa.getSelectionModel().select(BuscarEmpresa(((Presupuesto)tblPresupuesto.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
    Fecha.selectedDateProperty().set(((Presupuesto)tblPresupuesto.getSelectionModel().getSelectedItem()).getFechaSolicitud());
    }
    
     @FXML
    private void eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblPresupuesto.getSelectionModel().getSelectedItem() != null){
                    ActivarControles();
                    btnNuevo.setText("Cancelar");
                    btnReporte.setDisable(true);
                    btnEditar.setDisable(true);
                    TipoOperacion = Operacion.ELIMINAR;
                }else{
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para Eliminarlo");  
                }
                break;
            case ELIMINAR:
                    Eliminar();
                    LimpiarControles();
                    BotonesDefecto();
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
    public void Nuevo(){
    switch(TipoOperacion){
            case NINGUNO:
                    ActivarControles();
                    txtCodigo.setEditable(false);
                    btnReporte.setDisable(true);
                    btnEditar.setDisable(true);
                    btnEliminar.setText("Cancelar");
                    btnNuevo.setText("Guardar");
                    TipoOperacion = Operacion.GUARDAR;
                    break;
                    
            case GUARDAR:
                    Gurdar();
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
    public void Editar(){
        switch(TipoOperacion){
                case NINGUNO:
                        if(tblPresupuesto.getSelectionModel().getSelectedItem() != null){
                             ActivarControles();
                             txtCodigo.setEditable(false);
                              btnReporte.setDisable(true);
                              btnNuevo.setDisable(true);
                              btnEliminar.setText("Cancelar");
                              btnEditar.setText("Actualizar");
                              TipoOperacion = Operacion.ACTUALIZAR;
                    break; 
                        }else{
                           JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para Actualizar"); 
                        }
                        break;
                case ACTUALIZAR:
                            Actualizar();
                            LimpiarControles();
                             BotonesDefecto();
                             DesactivarControles();
                            TipoOperacion = Operacion.NINGUNO;
                        break;
        }
         CargarDatos();
    }
    
    public void Gurdar(){
        Presupuesto Nuevo=new Presupuesto();
        Nuevo.setCantidadPresupuesto(Double.valueOf(txtCantidad.getText()));
        Nuevo.setFechaSolicitud(Fecha.getSelectedDate());
        Nuevo.setCodigoEmpresa(((Empresa)cmbEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarPresupuesto(?,?,?)}");
            sp.setDate(1, new  java.sql.Date(Nuevo.getFechaSolicitud().getTime()));
            sp.setDouble(2, Nuevo.getCantidadPresupuesto());
            sp.setInt(3, Nuevo.getCodigoEmpresa());
            sp.execute();
           ListaPresupuesto.add(Nuevo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    public void Eliminar(){
        Presupuesto Eliminar=new Presupuesto();
        Eliminar.setCodigoPresupuesto(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarPresupuesto(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoPresupuesto());
    procedimiento.execute();
    JOptionPane.showMessageDialog(null, "Data actualizada");
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            JOptionPane.showMessageDialog(null, "Puede que los datos que dese eliminar este conectado a otra clase");
            JOptionPane.showMessageDialog(null, "Eliminar datos conectados");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
    }
    }
    
     public void Actualizar(){
        Presupuesto Editar=new Presupuesto();
        Editar.setCodigoPresupuesto(Integer.valueOf(txtCodigo.getText()));
        Editar.setCantidadPresupuesto(Double.valueOf(txtCantidad.getText()));
        Editar.setFechaSolicitud(Fecha.getSelectedDate());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarPresupuesto(?,?,?)}");
            sp.setInt(1, Editar.getCodigoPresupuesto());
            sp.setDate(2, new  java.sql.Date(Editar.getFechaSolicitud().getTime()));
            sp.setDouble(3, Editar.getCantidadPresupuesto());
           ListaPresupuesto.add(Editar);
           JOptionPane.showMessageDialog(null, "data actualizada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
     
    public ObservableList<Empresa> getEmpresa(){
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarListaEmpresa()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Empresa(resultado.getInt("CodigoEmpresa"),
                                      resultado.getString("NombreEmpresa"),
                                      resultado.getString("DireccionEmpresa"),
                                      resultado.getString("telefono")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaEmpresa = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Presupuesto> getPresupuesto(){
        ArrayList<Presupuesto> lista = new ArrayList<Presupuesto>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarPresupuesto()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Presupuesto(resultado.getInt("CodigoPresupuesto"),
                                      resultado.getDate("FechaSolicitud"),
                                      resultado.getDouble("CantidadPresupuesto"),
                                      resultado.getInt("CodigoEmpresa")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaPresupuesto = FXCollections.observableArrayList(lista);
    }
    
    public Empresa BuscarEmpresa(int Codigo){
    Empresa Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarEmpresa(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new Empresa (registro.getInt("CodigoEmpresa"),
                                 registro.getString("NombreEmpresa"),
                                 registro.getString("DireccionEmpresa"),
                                 registro.getString("telefono"));
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
        FormatoFecha();
        cmbEmpresa.getItems().addAll(getEmpresa());
        CargarDatos();
        DesactivarControles();
    }    
    
}
