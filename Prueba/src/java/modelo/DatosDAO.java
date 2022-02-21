/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatosDAO {
    Conexion conn = new Conexion();
    Connection cnn = conn.conexionDB();
    PreparedStatement ps;
    
    public boolean insertarDato(String nombreTabla, Object[] campos) {
        String stm = "INSERT INTO " + nombreTabla + " VALUES(";
        for (int i = 0; i < campos.length; i++) {
            if (i == (campos.length - 1)) {
                stm += "?)";
            } else {
                stm += "?, ";
            }
        }
        
        try {
            ps = cnn.prepareStatement(stm);        
            for (int i = 0; i < campos.length; i++) {
                ps.setObject((i + 1), campos[i]);
            }
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error insertando " + ex);
            JOptionPane.showMessageDialog(null, stm);
            return false;
        }
    }
}
