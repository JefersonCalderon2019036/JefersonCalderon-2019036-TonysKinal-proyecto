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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Empleados;
import org.jefersoncalderon.beans.Empresa;
import org.jefersoncalderon.beans.TipoEmpleado;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class EmpleadoController implements Initializable {

    @FXML private TableView tblEmpleados;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colNumeroEmpleado;
    @FXML private TableColumn colApellidosEmpleado;
    @FXML private TableColumn colNombreEmpleado;
    @FXML private TableColumn colDireccionEmpleado;
    @FXML private TableColumn colTelefonoContacto;
    @FXML private TableColumn GradoCocinero;
    @FXML private TableColumn CodigoTipoEmpleado;
    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnReporte;
    @FXML private TextField txtCodigo;
    @FXML private ComboBox cmbTipoEmpleado;
    @FXML private TextField txtNumeroEmpleado;
    @FXML private TextField txtApellidosEmpleados;
    @FXML private TextField txtNombreEmpleado;
    @FXML private TextField txtDireccionEmpleado;
    @FXML private TextField txtTelefonoContacto;
    @FXML private TextField txtGradoCocinero;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO; 
          private ObservableList<TipoEmpleado>ListaTipoEmpleado;
          private ObservableList<Empleados>ListaEmpleado;

    public MainApp getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(MainApp escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void DesactivarControles(){
    txtCodigo.setEditable(false);
    txtNumeroEmpleado.setEditable(false);
    txtApellidosEmpleados.setEditable(false);
    txtNombreEmpleado.setEditable(false);
    txtDireccionEmpleado.setEditable(false);
    txtTelefonoContacto.setEditable(false);
    txtGradoCocinero.setEditable(false);
    cmbTipoEmpleado.setEditable(false);
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtNumeroEmpleado.setEditable(true);
    txtApellidosEmpleados.setEditable(true);
    txtNombreEmpleado.setEditable(true);
    txtDireccionEmpleado.setEditable(true);
    txtTelefonoContacto.setEditable(true);
    txtGradoCocinero.setEditable(true); 
    cmbTipoEmpleado.setEditable(true);
    }
    
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtNumeroEmpleado.setText("");
    txtApellidosEmpleados.setText("");
    txtNombreEmpleado.setText("");
    txtDireccionEmpleado.setText("");
    txtTelefonoContacto.setText("");
    txtGradoCocinero.setText("");
    cmbTipoEmpleado.getSelectionModel().clearSelection();
    cmbTipoEmpleado.getSelectionModel().select(null);
    }
       
    public void BotonesDefecto(){
    //Se arena visibles los botones
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
    private void MenuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }
    
    @FXML
    private void SeleccionarElementos(){
    txtCodigo.setText(String.valueOf(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
    txtNumeroEmpleado.setText(String.valueOf(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getNumeroEmpleado()));
    txtApellidosEmpleados.setText(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getApellidosEmpleado()); 
    txtNombreEmpleado.setText(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getNombresEmpleado()); 
    txtDireccionEmpleado.setText(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getDireccionEmpleado()); 
    txtTelefonoContacto.setText(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getTelefonoContacto()); 
    txtGradoCocinero.setText(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getGradoCocinero()); 
    cmbTipoEmpleado.getSelectionModel().select(BuscarTipoEmpleado(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado()));
    }
    
    @FXML
    public void CargarDatos(){
    tblEmpleados.setItems(getEmpleado());
    colCodigo.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("CodigoEmpleado"));
    colNumeroEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("NumeroEmpleado"));
    colApellidosEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("ApellidosEmpleado"));
    colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("NombresEmpleado"));
    colDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("DireccionEmpleado"));
    colTelefonoContacto.setCellValueFactory(new PropertyValueFactory<Empleados, String>("TelefonoContacto"));
    GradoCocinero.setCellValueFactory(new PropertyValueFactory<Empleados, String>("GradoCocinero"));
    CodigoTipoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("CodigoTipoEmpleado"));
    }
    
    @FXML
    private void Eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblEmpleados.getSelectionModel().getSelectedItem() != null){
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
                    eliminar();
                    LimpiarControles();
                    BotonesDefecto();
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
                        if(tblEmpleados.getSelectionModel().getSelectedItem() != null){
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
    
    public ObservableList<TipoEmpleado> getTipoEmpleado(){
        ArrayList<TipoEmpleado> lista = new ArrayList<TipoEmpleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarTipoEmpleado()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new TipoEmpleado(resultado.getInt("CodigoTipoEmpleado"), 
                        resultado.getString("DescripcionTipoEmpleado")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaTipoEmpleado = FXCollections.observableArrayList(lista);
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
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaEmpleado = FXCollections.observableArrayList(lista);
    }
    
    public void eliminar(){
        TipoEmpleado Eliminar=new TipoEmpleado();
        Eliminar.setCodigoTipoEmpleado(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarDatosEmpleados(?)}");
    // Enviando los datos a ejecutar al procedimiento almacenado asignado
    procedimiento.setInt(1, Eliminar.getCodigoTipoEmpleado());
    procedimiento.execute();
    JOptionPane.showMessageDialog(null, "Data actualizada");
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            JOptionPane.showMessageDialog(null, "Puede que los datos que dese eliminar este conectado a otra clase");
            JOptionPane.showMessageDialog(null, "Eliminar datos conectados");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
    }
    }
    
    public void Gurdar(){
        Empleados Nuevo=new Empleados();
        Nuevo.setNumeroEmpleado(Integer.valueOf(txtNumeroEmpleado.getText()));
        Nuevo.setApellidosEmpleado(txtApellidosEmpleados.getText());
        Nuevo.setNombresEmpleado(txtNombreEmpleado.getText());
        Nuevo.setDireccionEmpleado(txtDireccionEmpleado.getText());
        Nuevo.setGradoCocinero(txtGradoCocinero.getText());
        Nuevo.setTelefonoContacto(txtTelefonoContacto.getText());
        Nuevo.setCodigoTipoEmpleado(((TipoEmpleado)cmbTipoEmpleado.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarDatosEmpleados(?,?,?,?,?,?,?)}");
            sp.setInt(1, Nuevo.getNumeroEmpleado());
            sp.setString(2, Nuevo.getApellidosEmpleado());
            sp.setString(3, Nuevo.getNombresEmpleado());
            sp.setString(4, Nuevo.getDireccionEmpleado());
            sp.setString(6, Nuevo.getGradoCocinero());
            sp.setString(5, Nuevo.getTelefonoContacto());
            sp.setInt(7, Nuevo.getCodigoTipoEmpleado());
            sp.execute();
           ListaEmpleado.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    public void Actualizar(){
        Empleados Nuevo=new Empleados();
        Nuevo.setCodigoEmpleado(Integer.valueOf(txtCodigo.getText()));
        Nuevo.setNumeroEmpleado(Integer.valueOf(txtNumeroEmpleado.getText()));
        Nuevo.setApellidosEmpleado(txtApellidosEmpleados.getText());
        Nuevo.setNombresEmpleado(txtNombreEmpleado.getText());
        Nuevo.setDireccionEmpleado(txtDireccionEmpleado.getText());
        Nuevo.setGradoCocinero(txtGradoCocinero.getText());
        Nuevo.setTelefonoContacto(txtTelefonoContacto.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarEmpleados(?,?,?,?,?,?,?)}");
            sp.setInt(1, Nuevo.getCodigoEmpleado());
            sp.setInt(2, Nuevo.getNumeroEmpleado());
            sp.setString(3, Nuevo.getApellidosEmpleado());
            sp.setString(4, Nuevo.getNombresEmpleado());
            sp.setString(5, Nuevo.getDireccionEmpleado());
            sp.setString(7, Nuevo.getGradoCocinero());
            sp.setString(6, Nuevo.getTelefonoContacto());
            sp.execute();
           ListaEmpleado.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
    }
    
    //Metodo Buscar
    public TipoEmpleado BuscarTipoEmpleado(int Codigo){
    TipoEmpleado Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarTipoEmpleado(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new TipoEmpleado (registro.getInt("CodigoTipoEmpleado"),
                                       registro.getString("DescripcionTipoEmpleado"));
        }
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, "No se encontro el dato de tipo empleado");
      System.out.println("No se a podido encontrar el dato en tipo empleado");
      e.printStackTrace();  
    }
    return Resultado;
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CargarDatos();
       DesactivarControles();
       cmbTipoEmpleado.setItems(getTipoEmpleado());
    }    
}
