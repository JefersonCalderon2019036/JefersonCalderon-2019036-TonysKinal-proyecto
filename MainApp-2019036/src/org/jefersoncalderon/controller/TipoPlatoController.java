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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.TipoPlato;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class TipoPlatoController implements Initializable {

    @FXML private TableView tblTipoPlato;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colDescripcion;
    @FXML private Button btnReporte;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtDescripcion;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO; 
          private ObservableList<TipoPlato>ListaTipoPlato;
          
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
    txtDescripcion.setEditable(false);
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtDescripcion.setEditable(true);
    }
    
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtDescripcion.setText("");
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
    tblTipoPlato.setItems(getTipoPlato());
    colCodigo.setCellValueFactory(new PropertyValueFactory<TipoPlato, Integer>("CodigoTipoPlato"));
    colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoPlato, String>("DescripcionTipoPlato"));
    }
    
    @FXML
    private void SeleccionarElemento() {
    txtCodigo.setText(String.valueOf(((TipoPlato)tblTipoPlato.getSelectionModel().getSelectedItem()).getCodigoTipoPlato()));
    txtDescripcion.setText(((TipoPlato)tblTipoPlato.getSelectionModel().getSelectedItem()).getDescripcionTipoPlato()); 
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
    private void eliminar() {
        switch(TipoOperacion){
            case NINGUNO:
                if(tblTipoPlato.getSelectionModel().getSelectedItem() != null){
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
    private void Editar() {
          switch(TipoOperacion){
                case NINGUNO:
                        if(tblTipoPlato.getSelectionModel().getSelectedItem() != null){
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
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            System.out.println("no se a podido carga la Data");
            e.printStackTrace();
        }
        return ListaTipoPlato = FXCollections.observableArrayList(lista);
    }
    
    public void Gurdar(){
        TipoPlato Nuevo=new TipoPlato();
        Nuevo.setDescripcionTipoPlato(txtDescripcion.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarTipoPlato(?)}");
            sp.setString(1, Nuevo.getDescripcionTipoPlato());
            sp.execute();
           ListaTipoPlato.add(Nuevo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    public void Eliminar(){
        TipoPlato Eliminar=new TipoPlato();
        Eliminar.setCodigoTipoPlato(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarTipoPlato(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoTipoPlato());
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
        TipoPlato Editar=new TipoPlato();
        Editar.setCodigoTipoPlato(Integer.valueOf(txtCodigo.getText()));
        Editar.setDescripcionTipoPlato(txtDescripcion.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarTipoPlato(?,?)}");
            sp.setInt(1, Editar.getCodigoTipoPlato());
            sp.setString(2, Editar.getDescripcionTipoPlato());
            sp.execute();
           ListaTipoPlato.add(Editar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarDatos();
        DesactivarControles();
    }    
    
}
