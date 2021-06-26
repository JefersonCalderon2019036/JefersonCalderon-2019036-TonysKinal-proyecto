package org.jefersoncalderon.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jefersoncalderon.controller.EmpleadoController;
import org.jefersoncalderon.controller.EmpresaController;
import org.jefersoncalderon.controller.MenuPrincipalController;
import org.jefersoncalderon.controller.PlatosController;
import org.jefersoncalderon.controller.PresupuestoController;
import org.jefersoncalderon.controller.ProductoController;
import org.jefersoncalderon.controller.ProductosHasPlatosController;
import org.jefersoncalderon.controller.ServiciosController;
import org.jefersoncalderon.controller.ServiciosHasEmpleadosController;
import org.jefersoncalderon.controller.ServiciosHasPlatosController;
import org.jefersoncalderon.controller.TipoEmpleadoController;
import org.jefersoncalderon.controller.TipoPlatoController;

public class MainApp extends Application {
    private final String PAQUETE_VISTA = "/org/jefersoncalderon/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    
    
    //cambiar de escena
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML=new FXMLLoader();
        InputStream archivo=MainApp.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(MainApp.class.getResource(PAQUETE_VISTA+fxml));
        escena=new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }
    
    //Menu Principal
    public void menuPrincipal(){
        try {
            MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml",606,340);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
     
    public void Empresa(){
        try {
            EmpresaController menuPrincipal = (EmpresaController)cambiarEscena("EmpresaView.fxml",541,589);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
     
    //Tipo Empleado
    public void TipoEmpleado() {
        try {
            TipoEmpleadoController menuPrincipal = (TipoEmpleadoController)cambiarEscena("TipoEmpleadoView.fxml",600,337);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
 
    
     //Empleados
    public void Empleados(){
        try {
            EmpleadoController menuPrincipal = (EmpleadoController)cambiarEscena("EmpleadoView.fxml",746,400);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }

     //Servicios
    public void Servicios(){
        try {
            ServiciosController menuPrincipal = (ServiciosController)cambiarEscena("ServiciosView.fxml",747,508);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    //Tipo Plato
    public void TipoPlato() {
        try {
            TipoPlatoController menuPrincipal = (TipoPlatoController)cambiarEscena("TipoPlatoView.fxml",600,400);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    // producto
    public void Producto() {
     try {
            ProductoController menuPrincipal = (ProductoController)cambiarEscena("ProductoView.fxml",673,375);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // producto
    public void Presupuesto() {
     try {
            PresupuestoController menuPrincipal = (PresupuestoController)cambiarEscena("PresupuestoView.fxml",714,400);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // platos
    public void Platos() {
    try {
            PlatosController menuPrincipal = (PlatosController)cambiarEscena("PlatosView.fxml",748,505);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    // productos has platos
    public void Productos_Has_Platos() {
    try {
            ProductosHasPlatosController menuPrincipal = (ProductosHasPlatosController)cambiarEscena("ProductosHasPlatosview.fxml",660,386);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    // servicios has platos
    public void Servicios_Has_Platos() {
     try {
            ServiciosHasPlatosController menuPrincipal = (ServiciosHasPlatosController)cambiarEscena("ServiciosHasPlatosView.fxml",633,361);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // servicios has empleados
    public void Servicios_Has_Empleados() {
     try {
            ServiciosHasEmpleadosController menuPrincipal = (ServiciosHasEmpleadosController)cambiarEscena("ServiciosHasEmpleadosView.fxml",565,607);
            menuPrincipal.setEscenarioPrincipal(this);
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        escenarioPrincipal.setTitle("Tonys Kinal 2019036");
        menuPrincipal();
        escenarioPrincipal.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
