package Administrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    public Connection get_connection(){
        Connection conexion = null;
        
        try {
            conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/carritoCompras","root","mickybrujo");
            
            if(conexion != null){
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return conexion;
    }
    
}
