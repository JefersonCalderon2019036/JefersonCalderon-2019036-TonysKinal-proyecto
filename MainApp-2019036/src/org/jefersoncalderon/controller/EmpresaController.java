package org.jefersoncalderon.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.jefersoncalderon.beans.Empresa;
import org.jefersoncalderon.db.Conexion;
import org.jefersoncalderon.report.GenerarReport;
import org.jefersoncalderon.system.MainApp;

public class EmpresaController implements Initializable {

    @FXML private TableView tableEmpresa;
    @FXML private TableColumn ColCodigo;
    @FXML private TableColumn ColNombre;
    @FXML private TableColumn ColDireccion;
    @FXML private TableColumn ColTelefono;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private Button btnEliminar;
    @FXML private Button btnReporte;
    @FXML private Button btnEditar;
    @FXML private Button btnNuevo;
    @FXML private TextField txtCodigo;
    
    private MainApp escenarioPrincipal;

    //Metodos Getter y Setter
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
    private void Servicios() {
        escenarioPrincipal.Servicios();
    }
    
    // variable de acciones 
    private enum Operacion{NUEVO, GUARDAR, EDITAR, ACTUALIZAR,NINGUNO,ELIMINAR}
    private Operacion TipoOperacion = Operacion.NINGUNO; 
    
    //observarve list con el nombre lista empresa
    private ObservableList<Empresa>ListaEmpresa;
    
    //Metodo desactivar controles
    public void DesactivarControles(){
    txtCodigo.setEditable(false);
    txtNombre.setEditable(false);
    txtTelefono.setEditable(false);
    txtDireccion.setEditable(false);
    }
    
    //Metodo activar controles
    public void ActivarControles(){
    txtCodigo.setEditable(true);
    txtNombre.setEditable(true);
    txtTelefono.setEditable(true);
    txtDireccion.setEditable(true);
    }
    
    //Metodo limpiar controles
    public void LimpiarControles(){
    txtCodigo.setText("");
    txtNombre.setText("");
    txtTelefono.setText("");
    txtDireccion.setText("");
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
    
     @FXML
    public void CargarDatos(){
    tableEmpresa.setItems(getEmpresa());
    ColCodigo.setCellValueFactory(new PropertyValueFactory<Empresa, Integer>("CodigoEmpresa"));
    ColNombre.setCellValueFactory(new PropertyValueFactory<Empresa, String>("NombreEmpresa"));
    ColDireccion.setCellValueFactory(new PropertyValueFactory<Empresa, String>("DireccionEmpresa"));
    ColTelefono.setCellValueFactory(new PropertyValueFactory<Empresa, String>("telefono"));
    }
    
    @FXML
    public void SeleccionarElementos(){
    txtCodigo.setText(String.valueOf(((Empresa)tableEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
    txtNombre.setText(((Empresa)tableEmpresa.getSelectionModel().getSelectedItem()).getNombreEmpresa());
    txtTelefono.setText(((Empresa)tableEmpresa.getSelectionModel().getSelectedItem()).getTelefono());
    txtDireccion.setText(((Empresa)tableEmpresa.getSelectionModel().getSelectedItem()).getDireccionEmpresa());
    }
    
    @FXML
    private void Eliminar(){
        switch(TipoOperacion){
            case NINGUNO:
                //verificamos si tiene seleccionada una parte de la tabla
                if(tableEmpresa.getSelectionModel().getSelectedItem() != null){
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
                    eliminar();
                   //Limpiamos los controles
                    LimpiarControles();
                   // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                   // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.NINGUNO;
                break;
            default:
                // volvemos a los botones a su estado por defecto
                    BotonesDefecto();
                // limpiamos controles
                    LimpiarControles();
                // Cambiamos el estado de la variable
                    TipoOperacion = Operacion.NINGUNO;
                    break;
        }
         // cargamos la infomacion nuevamente
         CargarDatos();
         DesactivarControles();
    }
    @FXML
    public void Nuevo(){
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
    @FXML
    public void Editar(){
        switch(TipoOperacion){
                case NINGUNO:
                        //verificamos si tiene seleccionada una parte de la tabla
                        if(tableEmpresa.getSelectionModel().getSelectedItem() != null){
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
                            // Cambiamos el estado de la variable
                            TipoOperacion = Operacion.NINGUNO;
                        break;
        }
        // cargamos la infomacion nuevamente
         CargarDatos();
    }
    
    //Método para ejecutar el procedimiento almacenado y llenar una lista del resulset
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
            //mensaje al usuario
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            //error espeficio
            e.printStackTrace();
        }
        return ListaEmpresa = FXCollections.observableArrayList(lista);
    }
    
    public void eliminar(){
        Empresa Eliminar=new Empresa();
        Eliminar.setCodigoEmpresa(Integer.valueOf(txtCodigo.getText()));
    try{
    PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarEmpresa(?)}");
    // Enviando los datos a ejecutar al procedimiento almacenado asignado
    procedimiento.setInt(1, Eliminar.getCodigoEmpresa());
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
    //Metodo para Aguardar una nueva empresa en la vase de datos
    public void Gurdar(){
        //capturamos la infomacion de las cajas de texto y la asignamos a los metos setter 
        Empresa Nuevo=new Empresa();
        Nuevo.setNombreEmpresa(txtNombre.getText());
        Nuevo.setDireccionEmpresa(txtTelefono.getText());
        Nuevo.setTelefono(txtDireccion.getText());
        try {
            //activamos el sp de nuestra vace de datos
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarEmpresa(?,?,?)}");
            //Asignamos los parametros al procesos almacenado
            sp.setString(1, Nuevo.getNombreEmpresa());
            sp.setString(2, Nuevo.getDireccionEmpresa());
            sp.setString(3, Nuevo.getTelefono());
            sp.execute();
           ListaEmpresa.add(Nuevo);
        } catch (Exception e) {
            //mensaje al usuario
            JOptionPane.showMessageDialog(null, "no se a podido cargar la Data");
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            //error especifico
            e.printStackTrace();
        }
    }
    @FXML
    public void Reporte(){
    Map parametros = new HashMap();
    parametros.put("CodigoEmpresa", null);
    GenerarReport.mostrarReporte("ReportEmpresa.jasper", "Reporte Empresa", parametros);
    }
    
    
    //Metodo para Aguardar una nueva empresa en la vase de datos
    public void Actualizar(){
        //capturamos la infomacion de las cajas de texto y la asignamos a los metos setter 
        Empresa Nuevo=new Empresa();
        Nuevo.setCodigoEmpresa(Integer.valueOf(txtCodigo.getText()));
        Nuevo.setNombreEmpresa(txtNombre.getText());
        Nuevo.setDireccionEmpresa(txtDireccion.getText());
        Nuevo.setTelefono(txtTelefono.getText());
        try {
            //activamos el sp de nuestra vace de datos
            PreparedStatement sp = Conexion.getInstance().getConexion().prepareCall("{call sp_ActualizarEmpresa(?,?,?,?)}");
            //Asignamos los parametros al procesos almacenado
            sp.setInt(1, Nuevo.getCodigoEmpresa());
            sp.setString(2, Nuevo.getNombreEmpresa());
            sp.setString(3, Nuevo.getDireccionEmpresa());
            sp.setString(4, Nuevo.getTelefono());
            sp.execute();
           ListaEmpresa.add(Nuevo);

        } catch (Exception e) {
            //mensaje al usuario
            JOptionPane.showMessageDialog(null, "no se a podido carga la Data");
            //mensaje en la terminal
            System.out.println("no se a podido cargar la Data");
            //error especifico
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarDatos();
        DesactivarControles();
    }  
    
}
