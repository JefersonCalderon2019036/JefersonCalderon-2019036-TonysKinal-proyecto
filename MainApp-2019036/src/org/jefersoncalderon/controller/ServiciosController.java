package org.jefersoncalderon.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Empleados;
import org.jefersoncalderon.beans.Empresa;
import org.jefersoncalderon.beans.Servicios;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;


public class ServiciosController implements Initializable {

    @FXML private TableView tblServicios;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colFecha;
    @FXML private TableColumn colTipoServicio;
    @FXML private TableColumn colHora;
    @FXML private TableColumn colLugarServicios;
    @FXML private TableColumn colTelefono;
    @FXML private TableColumn colEmpresa;
    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnReporte;
    @FXML private ColumnConstraints GpFecha;
    @FXML private GridPane GPFecha;
    @FXML private ComboBox cmbEmpresa;
    @FXML private Label LabelTipoEmpresa;
    @FXML private Label LabelCodigo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtTipoServicio;
    @FXML private Label LabelFecha;
    @FXML private Label LabelTipoServicio;
    @FXML private Label LabelHora;
    @FXML private TextField txtHoraServicio;
    @FXML private Label LabelLugarServicio;
    @FXML private TextField txtLugarServicio;
    @FXML private Label LabelTelefonoContacto;
    @FXML private TextField txtTelefonoContacto;
          private DatePicker Fecha;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Servicios>ListaServicio;
          private ObservableList<Empresa>ListaEmpresa;
          
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
    txtCodigo.setEditable(false);
    txtTipoServicio.setEditable(false);
    txtHoraServicio.setEditable(false);
    txtLugarServicio.setEditable(false);
    txtTelefonoContacto.setEditable(false);
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtTipoServicio.setEditable(true);
    txtHoraServicio.setEditable(true);
    txtLugarServicio.setEditable(true);
    txtTelefonoContacto.setEditable(true);
    }
    
    public void LimpiarControles(){
    cmbEmpresa.getSelectionModel().clearSelection();
    cmbEmpresa.getSelectionModel().select(null);
    txtCodigo.setText("");
    txtTipoServicio.setText("");
    txtHoraServicio.setText("");
    txtLugarServicio.setText("");
    txtTelefonoContacto.setText("");
    Fecha.selectedDateProperty().set(null);
    }
    
    public void BotonesDefecto(){
    btnReporte.setDisable(false);
    btnEliminar.setDisable(false);
    btnEditar.setDisable(false);
    btnNuevo.setDisable(false);
    btnEliminar.setText("Eliminar");
    btnReporte.setText("Reporte");
    btnEditar.setText("Editar");
    btnNuevo.setText("Nuevo");
    }
    
    @FXML
    public void CargarDatos(){
    tblServicios.setItems(getServicio());
    colCodigo.setCellValueFactory(new PropertyValueFactory<Servicios, Integer>("CodigoServicios"));
    colFecha.setCellValueFactory(new PropertyValueFactory<Servicios, Date>("FechaServicios"));
    colTipoServicio.setCellValueFactory(new PropertyValueFactory<Servicios, String>("TipoServicio"));
    colHora.setCellValueFactory(new PropertyValueFactory<Servicios, String>("HoraServicio"));
    colLugarServicios.setCellValueFactory(new PropertyValueFactory<Servicios, String>("LugarServicio"));
    colTelefono.setCellValueFactory(new PropertyValueFactory<Servicios, String>("TelefonoContacto"));
    colEmpresa.setCellValueFactory(new PropertyValueFactory<Servicios, Integer>("CodigoEmpresa")); 
    }
    
    @FXML
    public void SeleccionarElemento(){
    txtCodigo.setText(String.valueOf(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getCodigoServicios()));
    txtTipoServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getTipoServicio()); 
    txtHoraServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getHoraServicio()); 
    txtLugarServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getLugarServicio()); 
    txtTelefonoContacto.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getTelefonoContacto()); 
    cmbEmpresa.getSelectionModel().select(BuscarEmpresa(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
    Fecha.selectedDateProperty().set(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getFechaServicios());
    }
    
     @FXML
    private void eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblServicios.getSelectionModel().getSelectedItem() != null){
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
    public void Editar(){
        switch(TipoOperacion){
                case NINGUNO:
                        if(tblServicios.getSelectionModel().getSelectedItem() != null){
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
    
      public void Guardar(){
        Servicios Nuevo=new Servicios();
        Nuevo.setTipoServicio(txtTipoServicio.getText());
        Nuevo.setFechaServicios(Fecha.getSelectedDate());
        Nuevo.setLugarServicio(txtLugarServicio.getText());
        Nuevo.setHoraServicio(txtHoraServicio.getText());
        Nuevo.setTelefonoContacto(txtTelefonoContacto.getText());
        Nuevo.setCodigoEmpresa(((Empresa)cmbEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarServicio(?,?,?,?,?,?)}");
            sp.setDate(1, new  java.sql.Date(Nuevo.getFechaServicios().getTime()));
            sp.setString(2, Nuevo.getTipoServicio());
            sp.setString(3, Nuevo.getHoraServicio());
            sp.setString(4, Nuevo.getLugarServicio());
            sp.setString(5, Nuevo.getTelefonoContacto());
            sp.setInt(6, Nuevo.getCodigoEmpresa());
            sp.execute();
           ListaServicio.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido carga la Data");
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
    
     public void Eliminar(){
        Servicios Eliminar=new Servicios();
        Eliminar.setCodigoServicios(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarDatosServicios(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoServicios());
    procedimiento.execute();
    JOptionPane.showMessageDialog(null, "Data actualizada");
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            JOptionPane.showMessageDialog(null, "Puede que los datos que dese eliminar este conectado a otra clase");
            JOptionPane.showMessageDialog(null, "Eliminar datos conectados");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
    }
    }
     public void Actualizar(){
        Servicios Nuevo=new Servicios();
        Nuevo.setCodigoServicios(Integer.valueOf(txtCodigo.getText()));
        Nuevo.setTipoServicio(txtTipoServicio.getText());
        Nuevo.setFechaServicios(Fecha.getSelectedDate());
        Nuevo.setLugarServicio(txtLugarServicio.getText());
        Nuevo.setHoraServicio(txtHoraServicio.getText());
        Nuevo.setTelefonoContacto(txtTelefonoContacto.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarServicio(?,?,?,?,?,?)}");
            sp.setInt(1, Nuevo.getCodigoServicios());
            sp.setDate(2, new  java.sql.Date(Nuevo.getFechaServicios().getTime()));
            sp.setString(3, Nuevo.getTipoServicio());
            sp.setString(4, Nuevo.getHoraServicio());
            sp.setString(5, Nuevo.getLugarServicio());
            sp.setString(6, Nuevo.getTelefonoContacto());
           ListaServicio.add(Nuevo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
    }
     
     public void FormatoFecha(){
       Fecha = new DatePicker(Locale.ENGLISH);
       Fecha.setDateFormat(new SimpleDateFormat("yyy-MM-dd"));
       Fecha.getCalendarView().todayButtonTextProperty().set("Today");
       Fecha.getCalendarView().setShowWeeks(false);
       GPFecha.add(Fecha,1,1);
       Fecha.getStylesheets().add("/org/jefersoncalderon/resorce/DatePicker.css");
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CargarDatos();
       DesactivarControles();
       FormatoFecha();
       cmbEmpresa.getItems().addAll(getEmpresa());
    }  
    
}
