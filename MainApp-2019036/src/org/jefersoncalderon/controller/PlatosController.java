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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Platos;
import org.jefersoncalderon.beans.TipoPlato;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class PlatosController implements Initializable {

    @FXML private TableView tblPlato;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colCantidad;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colDescripcion;
    @FXML private TableColumn colPrecio;
    @FXML private TableColumn colCocinero;
    @FXML private TableColumn colTipoPlato;
    @FXML private Button btnReporte;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCocinero;
    @FXML private ComboBox cmbTipoPlato;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<TipoPlato> ListaTipoPlato;
          private ObservableList<Platos>ListaPlato;
          
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
    txtNombre.setEditable(false);
    txtCantidad.setEditable(false);
    txtDescripcion.setEditable(false);
    txtPrecio.setEditable(false);      
    txtCocinero.setEditable(false);
    cmbTipoPlato.setEditable(false);
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtNombre.setEditable(true);
    txtCantidad.setEditable(true);
    txtDescripcion.setEditable(true);
    txtPrecio.setEditable(true);      
    txtCocinero.setEditable(true);
    cmbTipoPlato.setEditable(true);
    }
    
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtNombre.setText("");
    txtCantidad.setText("");
    txtDescripcion.setText("");
    txtPrecio.setText("");     
    txtCocinero.setText("");
    cmbTipoPlato.getSelectionModel().clearSelection();
    cmbTipoPlato.getSelectionModel().select(null);
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
    tblPlato.setItems(getPlato());
    colCodigo.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("CodigoPlato"));
    colCantidad.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("Cantidad"));
    colNombre.setCellValueFactory(new PropertyValueFactory<Platos, String>("NombreaPlato"));
    colDescripcion.setCellValueFactory(new PropertyValueFactory<Platos, String>("DescripcionPlato"));
    colPrecio.setCellValueFactory(new PropertyValueFactory<Platos, Double>("PrecioPlato"));
    colCocinero.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("CodigoCocinero"));
    colTipoPlato.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("CodigoTipoPlato"));
    }
    
    @FXML
    public void SeleccionElemento(){
    txtCodigo.setText(String.valueOf(((Platos)tblPlato.getSelectionModel().getSelectedItem()).getCodigoPlato()));
    txtNombre.setText((((Platos)tblPlato.getSelectionModel().getSelectedItem()).getNombreaPlato()));
    txtCantidad.setText(String.valueOf(((Platos)tblPlato.getSelectionModel().getSelectedItem()).getCantidad()));
    txtDescripcion.setText((((Platos)tblPlato.getSelectionModel().getSelectedItem()).getDescripcionPlato()));
    txtPrecio.setText(String.valueOf(((Platos)tblPlato.getSelectionModel().getSelectedItem()).getPrecioPlato()));     
    txtCocinero.setText(String.valueOf(((Platos)tblPlato.getSelectionModel().getSelectedItem()).getCodigoCocinero()));
    cmbTipoPlato.getSelectionModel().select(BuscarEmpresa(((Platos)tblPlato.getSelectionModel().getSelectedItem()).getCodigoTipoPlato()));
    }
    
    @FXML
    private void eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblPlato.getSelectionModel().getSelectedItem() != null){
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
                        if(tblPlato.getSelectionModel().getSelectedItem() != null){
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
        Platos Nuevo=new Platos();
        Nuevo.setCantidad(Integer.valueOf(txtCantidad.getText()));
        Nuevo.setNombreaPlato(txtNombre.getText());
        Nuevo.setDescripcionPlato(txtDescripcion.getText());
        Nuevo.setPrecioPlato(Double.valueOf(txtPrecio.getText()));
        Nuevo.setCodigoCocinero(Integer.valueOf(txtCocinero.getText()));
        Nuevo.setCodigoTipoPlato(((TipoPlato)cmbTipoPlato.getSelectionModel().getSelectedItem()).getCodigoTipoPlato());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarPlatos(?,?,?,?,?,?)}");
            sp.setInt(1, Nuevo.getCantidad());
            sp.setString(2, Nuevo.getNombreaPlato());
            sp.setString(3, Nuevo.getDescripcionPlato());
            sp.setDouble(4, Nuevo.getPrecioPlato());
            sp.setInt(5, Nuevo.getCodigoCocinero());
            sp.setInt(6, Nuevo.getCodigoTipoPlato());
            sp.execute();
           ListaPlato.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
    }
    
    public void Actualizar(){
        Platos Editar=new Platos();
        Editar.setCodigoPlato(Integer.valueOf(txtCodigo.getText()));
        Editar.setCantidad(Integer.valueOf(txtCantidad.getText()));
        Editar.setNombreaPlato(txtNombre.getText());
        Editar.setDescripcionPlato(txtDescripcion.getText());
        Editar.setPrecioPlato(Double.valueOf(txtPrecio.getText()));
        Editar.setCodigoCocinero(Integer.valueOf(txtCocinero.getText()));
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarPlatos(?,?,?,?,?,?)}");
            sp.setInt(1, Editar.getCodigoPlato());
            sp.setInt(2, Editar.getCantidad());
            sp.setString(3, Editar.getNombreaPlato());
            sp.setString(4, Editar.getDescripcionPlato());
            sp.setDouble(5, Editar.getPrecioPlato());
            sp.setInt(6, Editar.getCodigoCocinero());
            sp.execute();
           ListaPlato.add(Editar);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    public void Eliminar(){
        Platos Eliminar=new Platos();
        Eliminar.setCodigoPlato(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarPlato(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoPlato());
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
    
    public ObservableList<TipoPlato> getTipoPlato(){
        ArrayList<TipoPlato> lista = new ArrayList<TipoPlato>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_MostrarListaTipoPlato()}");
            ResultSet resultado = procedimiento.executeQuery();
                while(resultado.next()){
                lista.add(new TipoPlato(resultado.getInt("CodigoTipoPlato"),
                                      resultado.getString("DescripcionTipoPlato")));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
        return ListaTipoPlato = FXCollections.observableArrayList(lista);
    }
    
    public TipoPlato BuscarEmpresa(int Codigo){
    TipoPlato Resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_BuscarTipoPlato(?)}");
        procedimiento.setInt(1, Codigo);    
        ResultSet registro = procedimiento.executeQuery();
        while(registro.next()){
        Resultado = new TipoPlato (registro.getInt("CodigoTipoPlato"),
                                 registro.getString("DescripcionTipoPlato"));
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
        CargarDatos();
        DesactivarControles();
        cmbTipoPlato.getItems().addAll(getTipoPlato());
    }    
    
}
