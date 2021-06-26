package org.jefersoncalderon.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.jefersoncalderon.system.MainApp;

public class MenuPrincipalController implements Initializable {
    
    private MainApp EscenarioPrincipal;
    
    public MainApp getEscenarioPrincipal() {
        return EscenarioPrincipal;
    }

    public void setEscenarioPrincipal(MainApp EscenarioPrincipal) {
        this.EscenarioPrincipal = EscenarioPrincipal;
    }
    
    @FXML
    public void Empresa(){
        EscenarioPrincipal.Empresa();
    }
    
     @FXML
    private void TipoEmpleado() {
        EscenarioPrincipal.TipoEmpleado();
    }

    @FXML
    private void Servicios() {
       EscenarioPrincipal.Servicios();
    }

    @FXML
    private void Empleados() {
        EscenarioPrincipal.Empleados();
    }

    @FXML
    private void TipoPlato() {
        EscenarioPrincipal.TipoPlato();
    }

    @FXML
    private void Productos() {
        EscenarioPrincipal.Producto();
    }
    
    @FXML
    private void Presupuesto(){
        EscenarioPrincipal.Presupuesto();
    }
    
    @FXML
    private void Productos_Has_Platos() {
        EscenarioPrincipal.Productos_Has_Platos();
    }
    
    @FXML
    private void Platos() {
        EscenarioPrincipal.Platos();
    }
    
    @FXML
    private void Servicios_Has_Platos() {
        EscenarioPrincipal.Servicios_Has_Platos();
    }
    
    @FXML
    private void Servicios_Has_Empleados() {
        EscenarioPrincipal.Servicios_Has_Empleados();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
