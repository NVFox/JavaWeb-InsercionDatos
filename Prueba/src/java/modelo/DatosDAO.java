/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class DatosDAO {
    Conexion conn = new Conexion();
    Connection cnn = conn.conexionDB();
    PreparedStatement ps;
    ResultSet rs;
    
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
    
    public Tabla consultarDatos(String nombreTabla, String[] campos) {
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM " + nombreTabla);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String[] valoresFila = new String[campos.length];
                
                for (int i = 0; i < campos.length; i++) {
                    valoresFila[i] = (String) rs.getObject(campos[i]);
                }
                
                lista.add(valoresFila);
            }
            
            return new Tabla(nombreTabla, campos, lista);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error consultando " + ex);
            return null;
        }
    }
}
