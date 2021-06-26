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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Empleados;
import org.jefersoncalderon.beans.Servicios;
import org.jefersoncalderon.beans.ServiciosHasEmpleados;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class ServiciosHasEmpleadosController implements Initializable {

    @FXML private TableView tblServiciosHasEmpleados;
    @FXML private TableColumn colServicios;
    @FXML private TableColumn colEmpleados;
    @FXML private TableColumn colFecha;
    @FXML private TableColumn colHora;
    @FXML private TableColumn colLugar;
    @FXML private GridPane grpFecha;
    @FXML private ComboBox cmbServicios;
    @FXML private ComboBox cmbEmpleados;
    @FXML private TextField txtHora;
    @FXML private TextField txtLugar;
    @FXML private Label LabelServicios;
    @FXML private Label LabelEmpleados;
    @FXML private Label LabelFecha;
    @FXML private Label LabelHora;
    @FXML private Label LabelLugar;
    @FXML private Button btnReporte;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
          private DatePicker Fecha;
          private MainApp EscenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Servicios>ListaServicio;
          private ObservableList<Empleados>ListaEmpleado;
          private ObservableList<ServiciosHasEmpleados>ListaServiciosHasEmpleados;
    
    public MainApp getEscenarioPrincipal() {
        return EscenarioPrincipal;
    }

    public void setEscenarioPrincipal(MainApp EscenarioPrincipal) {
        this.EscenarioPrincipal = EscenarioPrincipal;
    } 
    
    public void FormatoFecha(){
       Fecha = new DatePicker(Locale.ENGLISH);
       Fecha.setDateFormat(new SimpleDateFormat("yyy-MM-dd"));
       Fecha.getCalendarView().todayButtonTextProperty().set("Today");
       Fecha.getCalendarView().setShowWeeks(false);
       grpFecha.add(Fecha,1,2);
       Fecha.getStylesheets().add("/org/jefersoncalderon/resorce/DatePicker.css");
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
    
    public void DesactivarControles(){
    cmbServicios.setEditable(false);
    cmbEmpleados.setEditable(false);
    txtHora.setEditable(false);
    txtLugar.setEditable(false);
    }
    
    public void ActivarControles(){
    cmbServicios.setEditable(true);
    cmbEmpleados.setEditable(true);
    txtHora.setEditable(true);
    txtLugar.setEditable(true);
    }
    
    public void LimpiarControles(){
    cmbServicios.getSelectionModel().clearSelection();
    cmbServicios.getSelectionModel().select(null);
    cmbEmpleados.getSelectionModel().clearSelection();
    cmbEmpleados.getSelectionModel().select(null);
    txtHora.setText("");
    txtLugar.setText("");
    }
    
     @FXML
    private void eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblServiciosHasEmpleados.getSelectionModel().getSelectedItem() != null){
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
                        if(tblServiciosHasEmpleados.getSelectionModel().getSelectedItem() != null){
                             ActivarControles();
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
    
    @FXML
    private void MenuPrincipal() {
        EscenarioPrincipal.menuPrincipal();
    }
    
    private void Guardar() {
    ServiciosHasEmpleados Nuevo=new ServiciosHasEmpleados();
        Nuevo.setEmpleado_CodigoEmpleado(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getEmpleado_CodigoEmpleado());
        Nuevo.setServicios_CodigoServicio(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getServicios_CodigoServicio());
        Nuevo.setFechaEvento(Fecha.getSelectedDate());
        Nuevo.setHoraEvento(txtHora.getText());
        Nuevo.setLugarEvento(txtLugar.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarServiciosHasEmpleados(?,?,?,?,?)}");
            sp.setInt(1, Nuevo.getServicios_CodigoServicio());
            sp.setInt(2, Nuevo.getEmpleado_CodigoEmpleado());
            sp.setDate(3, new  java.sql.Date(Nuevo.getFechaEvento().getTime()));
            sp.setString(4, Nuevo.getHoraEvento());
            sp.setString(5, Nuevo.getLugarEvento());
            sp.execute();
           ListaServiciosHasEmpleados.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    private void Actualizar() {
    ServiciosHasEmpleados Editar=new ServiciosHasEmpleados();
        Editar.setEmpleado_CodigoEmpleado(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getEmpleado_CodigoEmpleado());
        Editar.setServicios_CodigoServicio(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getServicios_CodigoServicio());
        Editar.setFechaEvento(Fecha.getSelectedDate());
        Editar.setHoraEvento(txtHora.getText());
        Editar.setLugarEvento(txtLugar.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarServiciosHasEmpleados(?,?,?,?,?)}");
            sp.setInt(1, Editar.getServicios_CodigoServicio());
            sp.setInt(2, Editar.getEmpleado_CodigoEmpleado());
            sp.setDate(3, new  java.sql.Date(Editar.getFechaEvento().getTime()));
            sp.setString(4, Editar.getHoraEvento());
            sp.setString(5, Editar.getLugarEvento());
            sp.execute();
           ListaServiciosHasEmpleados.add(Editar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    private void Eliminar() {
    ServiciosHasEmpleados Eliminar=new ServiciosHasEmpleados();
        Eliminar.setEmpleado_CodigoEmpleado(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getEmpleado_CodigoEmpleado());
        Eliminar.setServicios_CodigoServicio(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getServicios_CodigoServicio());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarServiciosHasEmpleados(?,?)}");
            sp.setInt(1, Eliminar.getServicios_CodigoServicio());
            sp.setInt(2, Eliminar.getEmpleado_CodigoEmpleado());
            sp.execute();
           ListaServiciosHasEmpleados.add(Eliminar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void SeleccionarElementos() {
    cmbServicios.getSelectionModel().select(BuscarServicios(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getServicios_CodigoServicio()));
    cmbEmpleados.getSelectionModel().select(BuscarEmpleados(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getEmpleado_CodigoEmpleado()));
    Fecha.selectedDateProperty().set(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getFechaEvento());
    txtHora.setText(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getHoraEvento());
    txtLugar.setText(((ServiciosHasEmpleados)tblServiciosHasEmpleados.getSelectionModel().getSelectedItem()).getLugarEvento());
    }

    @FXML
    private void CargarDatos() {
    tblServiciosHasEmpleados.setItems(getServiciosHasEmpleados());
    colServicios.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Integer>("Servicios_CodigoServicio"));
    colEmpleados.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Integer>("Empleado_CodigoEmpleado"));
    colFecha.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Date>("FechaEvento"));
    colHora.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, String>("HoraEvento"));
    colLugar.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, String>("LugarEvento"));
    }
    
    public ObservableList<ServiciosHasEmpleados> getServiciosHasEmpleados(){
        ArrayList<ServiciosHasEmpleados> lista = new ArrayList<ServiciosHasEmpleados>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostraServiciosHasEmpleados()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new ServiciosHasEmpleados(resultado.getInt("Servicios_CodigoServicio"),
                                        resultado.getInt("Empleado_CodigoEmpleado"),
                                        resultado.getDate("FechaEvento"),
                                        resultado.getString("HoraEvento"),
                                        resultado.getString("LugarEvento")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaServiciosHasEmpleados = FXCollections.observableArrayList(lista);
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
    
    public ObservableList<Empleados> getEmpleado(){
        ArrayList<Empleados> lista = new ArrayList<Empleados>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarListaEmpleados()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new Empleados(resultado.getInt("CodigoEmpleado"),
                                        resultado.getInt("NumeroEmpleado"),
                                        resultado.getString("ApellidosEmpleado"),
                                        resultado.getString("NombresEmpleado"),
                                        resultado.getString("DireccionEmpleado"),
                                        resultado.getString("TelefonoContacto"),
                                        resultado.getString("GradoCocinero"),
                                        resultado.getInt("CodigoTipoEmpleado")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaEmpleado = FXCollections.observableArrayList(lista);
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
   
   public Empleados BuscarEmpleados(int Codigo){
    Empleados Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarEmpleados(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new Empleados(registro.getInt("CodigoEmpleado"),
                                        registro.getInt("NumeroEmpleado"),
                                        registro.getString("ApellidosEmpleado"),
                                        registro.getString("NombresEmpleado"),
                                        registro.getString("DireccionEmpleado"),
                                        registro.getString("TelefonoContacto"),
                                        registro.getString("GradoCocinero"),
                                        registro.getInt("CodigoTipoEmpleado"));
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
        cmbServicios.getItems().addAll(getServicio());
        cmbEmpleados.getItems().addAll(getEmpleado());
        CargarDatos();
    }    
    
}
