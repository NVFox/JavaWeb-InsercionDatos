/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conexion;
    
    public Connection conexionDB () {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "");
                JOptionPane.showMessageDialog(null, "Conexion OK");
            } catch (Exception ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return conexion;
    } 
}
