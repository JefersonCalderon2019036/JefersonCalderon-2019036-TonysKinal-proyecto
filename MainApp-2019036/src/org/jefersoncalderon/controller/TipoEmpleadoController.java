package org.jefersoncalderon.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.TipoEmpleado;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.system.MainApp;

public class TipoEmpleadoController implements Initializable {

    @FXML private TableView tblTipoEmpleado;
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colDescripcion;
    @FXML private Button btnReporte;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtDescripicion;
    
    private MainApp escenarioPrincipal;

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
    
    @FXML
    private void Empleados() {
        escenarioPrincipal.Empleados();
    }
    
    // variable de acciones 
    private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
    private Operacion TipoOperacion = Operacion.NINGUNO; 
    
    //observarve list con el nombre lista empresa
    private ObservableList<TipoEmpleado>ListaTipoEmpleado;
    
    //Metodo desactivar controles
    public void DesactivarControles(){
    txtCodigo.setEditable(false);
    txtDescripicion.setEditable(false);
    }
    
    //Metodo activar controles
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtDescripicion.setEditable(true);
    }
    
    //Metodo limpiar controles
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtDescripicion.setText("");
    }
       
    //Metodo para volver los botones a su estado original
    public void BotonesDefecto(){
    //Se arena visibles los botones
    btnReporte.setDisable(false);
    btnEliminar.setDisable(false);
    btnEditar.setDisable(false);
    btnNuevo.setDisable(false);
    //Sus nombres regresaran su estado del inicio
    btnEliminar.setText("Eliminar");
    btnReporte.setText("Reportar");
    btnEditar.setText("Editar");
    btnNuevo.setText("Nuevo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarDatos();
        DesactivarControles();
    }    

    @FXML
    private void SeleccionarElemento() {
    txtCodigo.setText(String.valueOf(((TipoEmpleado)tblTipoEmpleado.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado()));
    txtDescripicion.setText(((TipoEmpleado)tblTipoEmpleado.getSelectionModel().getSelectedItem()).getDescripcionTipoEmpleado()); 
    }

    @FXML
    private void CargarDatos() {
        tblTipoEmpleado.setItems(getTipoEmpleado());
        colCodigo.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, Integer>("CodigoTipoEmpleado"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, String>("DescripcionTipoEmpleado"));
    }

    @FXML
    private void eliminar() {
        switch(TipoOperacion){
            case NINGUNO:
                //verificamos si tiene seleccionada una parte de la tabla
                if(tblTipoEmpleado.getSelectionModel().getSelectedItem() != null){
                   // activaremos controles
                    ActivarControles();
                   //botones de nuevo pasa a cancelar
                    btnNuevo.setText("Cancelar");
                   //botonoes de editar y reporte no se hacen visibles
                    btnReporte.setDisable(true);
                    btnEditar.setDisable(true);
                   //variable cambia a Eliminar
                    TipoOperacion = Operacion.ELIMINAR;
                }else{
                  //Mensaje hacia el usuario
                  //que tiene seleccionar un dato
                    JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para Eliminarlo");  
                }
                break;
            case ELIMINAR:
                   //Activamos el metodo de eliminar
                    Eliminar();
                   //Limpiamos los controles
                    LimpiarControles();
                   // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                   // Cambiamos el estado de la variable
                    DesactivarControles();
                    TipoOperacion = Operacion.NINGUNO;
                break;
            default:
                // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                // limpiamos controles
                    LimpiarControles();
                    DesactivarControles();
                // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.NINGUNO;
                    break;
        }
         // cargamos la infomacion nuevamente
         CargarDatos();
    }

    @FXML
    private void Editar() {
          switch(TipoOperacion){
                case NINGUNO:
                        //verificamos si tiene seleccionada una parte de la tabla
                        if(tblTipoEmpleado.getSelectionModel().getSelectedItem() != null){
                           // activaremos controles
                             ActivarControles();
                            // descativamos el control de codigo
                             txtCodigo.setEditable(false);
                            // descativamos los votones de nuevo y reporte
                              btnReporte.setDisable(true);
                              btnNuevo.setDisable(true);
                            // cambiaremos los nombre de los botones
                              btnEliminar.setText("Cancelar");
                              btnEditar.setText("Actualizar");
                            // Cambiamos el estado de la variable
                              TipoOperacion = Operacion.ACTUALIZAR;
                    break; 
                        }else{
                           //Mensaje hacia el usuario
                           //que tiene seleccionar un dato
                           JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para Actualizar"); 
                        }
                        break;
                case ACTUALIZAR:
                           //activamos el metodo de actualizar
                            Actualizar();
                            //Limpiamos los controles
                            LimpiarControles();
                            // volvemos a los botones a su estado por defecto
                             BotonesDefecto();
                             DesactivarControles();
                            // Cambiamos el estado de la variable
                            TipoOperacion = Operacion.NINGUNO;
                        break;
        }
        // cargamos la infomacion nuevamente
         CargarDatos();
    }

    @FXML
    private void Nuevo() {
        switch(TipoOperacion){
            case NINGUNO:
                // activaremos controles
                    ActivarControles();
                // descativamos el control de codigo
                    txtCodigo.setEditable(false);
                // descativamos los votones de editar y reporte
                    btnReporte.setDisable(true);
                    btnEditar.setDisable(true);
                // cambiaremos los nombre de los botones
                    btnEliminar.setText("Cancelar");
                    btnNuevo.setText("Guardar");
                // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.GUARDAR;
                    break;
                    
            case GUARDAR:
                // activamos el proceso para aguardar
                    Gurdar();
                // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                // limpiamos controles
                    LimpiarControles();
                // desctivamos controles
                    DesactivarControles();
                // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.NINGUNO;
                    break;
                    
            default:
                // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                // limpiamos controles
                    LimpiarControles();
                // desctivamos controles
                    DesactivarControles();
                // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.NINGUNO;
                    break;
        } 
        // cargamos la infomacion nuevamente
         CargarDatos();
    }
    
    //Método para ejecutar el procedimiento almacenado y llenar una lista del resulset
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
            //mensaje al usuario
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            //error espeficio
            e.printStackTrace();
        }
        return ListaTipoEmpleado = FXCollections.observableArrayList(lista);
    }
    
   
    //Metodo para Aguardar una nueva empresa en la vase de datos
    public void Actualizar(){
        //capturamos la infomacion de las cajas de texto y la asignamos a los metos setter 
        TipoEmpleado Nuevo=new TipoEmpleado();
        Nuevo.setCodigoTipoEmpleado(Integer.valueOf(txtCodigo.getText()));
        Nuevo.setDescripcionTipoEmpleado(txtDescripicion.getText());
        try {
            //activamos el sp de nuestra vace de datos
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarTipoEmpleado(?,?)}");
            //Asignamos los parametros al procesos almacenado
            sp.setInt(1, Nuevo.getCodigoTipoEmpleado());
            sp.setString(2, Nuevo.getDescripcionTipoEmpleado());
            sp.execute();
           ListaTipoEmpleado.add(Nuevo);
        } catch (Exception e) {
            //mensaje al usuario
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            //error especifico
            e.printStackTrace();
        }
    }
     //Metodo para Aguardar una nueva empresa en la vase de datos
    public void Gurdar(){
        //capturamos la infomacion de las cajas de texto y la asignamos a los metos setter 
        TipoEmpleado Nuevo=new TipoEmpleado();
        Nuevo.setDescripcionTipoEmpleado(txtDescripicion.getText());
        try {
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgreagarTipoEmpleado(?)}");
            sp.setString(1, Nuevo.getDescripcionTipoEmpleado());
            sp.execute();
           ListaTipoEmpleado.add(Nuevo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            System.out.println("no se a podido cargar la Data");
            e.printStackTrace();
        }
    }
     public void Eliminar(){
        TipoEmpleado Eliminar=new TipoEmpleado();
        Eliminar.setCodigoTipoEmpleado(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarTipoEmpleado(?)}");
    procedimiento.setInt(1, Eliminar.getCodigoTipoEmpleado());
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
    
}
