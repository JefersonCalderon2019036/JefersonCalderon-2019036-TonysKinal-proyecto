package org.jefersoncalderon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conexion;
    private static Conexion istancia;
    
    
    //creamos un metodo publico para la conexion
    public Conexion(){
    try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTonysKinal2019036IN5BM?useSSL=false","root","root");
    }catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
    e.printStackTrace();
    }
    }
    
    //creamos un metodo estatico para realizar el patron sigleton
    public static Conexion getInstance(){
    if(istancia == null){
    istancia = new Conexion();
    }
    return istancia;
    }
    
    //fabricamos los getters y setters de la variable conexion
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}