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
import org.jefersoncalderon.beans.Producto;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class ProductoController implements Initializable {

    @FXML private TableView tblProductos;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colCantidad;
    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnReporte;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCantidad;
          private MainApp escenarioPrincipal;
          private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
          private Operacion TipoOperacion = Operacion.NINGUNO;
          private ObservableList<Producto>ListaProducto;
          
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
    }
    
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtNombre.setEditable(true);
    txtCantidad.setEditable(true);
    }
    
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtNombre.setText("");
    txtCantidad.setText("");
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
    tblProductos.setItems(getProducto());
    colCodigo.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("CodigoProducto"));
    colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("NombreProducto"));
    colCantidad.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("Cantidad"));
    }
    
    @FXML
    private void SeleccionarElemento() {
    txtCodigo.setText(String.valueOf(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto()));
    txtNombre.setText(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getNombreProducto());
    txtCantidad.setText(String.valueOf(((Producto)tblProductos.getSelectionModel().getSelectedItem()).getCantidad()));
    }
    
     @FXML
    private void eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                if(tblProductos.getSelectionModel().getSelectedItem() != null){
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
                        if(tblProductos.getSelectionModel().getSelectedItem() != null){
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
    
    public void Eliminar(){
        Producto Eliminar=new Producto();
        Eliminar.setCodigoProducto(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarProducto(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoProducto());
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
        Producto Editar=new Producto();
        Editar.setCodigoProducto(Integer.valueOf(txtCodigo.getText()));
        Editar.setNombreProducto(txtNombre.getText());
        Editar.setCantidad(Integer.valueOf(txtCantidad.getText()));
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarProducto(?,?,?)}");
            sp.setInt(1, Editar.getCodigoProducto());
            sp.setString(2, Editar.getNombreProducto());
            sp.setInt(3, Editar.getCantidad());
            sp.execute();
           ListaProducto.add(Editar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
    
    public void Gurdar(){
        Producto Nuevo=new Producto();
        Nuevo.setNombreProducto(txtNombre.getText());
        Nuevo.setCantidad(Integer.valueOf(txtCantidad.getText()));
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarProductos(?,?)}");
            sp.setString(1, Nuevo.getNombreProducto());
            sp.setInt(2, Nuevo.getCantidad());
            sp.execute();
           ListaProducto.add(Nuevo);

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
