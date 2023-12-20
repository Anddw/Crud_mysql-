package com.mycompany.java_crud_myql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane; // Añadir importación para JOptionPane

public class CConexion {
    
    Connection conectar = null; 
    
    String usuario = "root";
    String contrasenia = "and231999";
    String bd = "bdescuela"; // Corregido el nombre de la variable
    String ip = "localhost"; // Supongamos que la IP es "localhost" para una conexión local
    String puerto = "3306"; // Corregido el nombre de la variable
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Añadido el cargador del controlador JDBC
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
             JOptionPane.showMessageDialog(null, "la conexion se a ralizado con exito");
       
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: " + e.toString());
        }
        return conectar; // Devuelto el objeto de conexión
    }
}
