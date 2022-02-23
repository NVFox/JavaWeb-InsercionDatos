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
                    valoresFila[i] = rs.getObject(campos[i]).toString();
                }
                
                lista.add(valoresFila);
            }
            
            return new Tabla(nombreTabla, campos, lista);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error consultando " + ex);
            return null;
        }
    }
    
    public boolean borrarDato(String nombreTabla, String campoPrincipal, String codigo) {
        try {
            ps = cnn.prepareStatement("DELETE FROM " + nombreTabla + " WHERE " + campoPrincipal + " = ?");
            ps.setString(1, codigo);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error borrando " + ex);
            return false;
        }
    }
    
    public boolean actualizarDato(String nombreTabla, Object[] campos, Object[] valores) {
        String stm = "UPDATE " + nombreTabla + " SET ";
        for (int i = 1; i < campos.length; i++) {
            if (i == (campos.length - 1)) {
                stm += campos[i] + " = ? ";
            } else {
                stm += campos[i] + " = ?, ";
            }
        }
        
        stm += "WHERE " + campos[0] + " = ?";
        
        JOptionPane.showMessageDialog(null, stm);
        
        try {  
            ps = cnn.prepareStatement(stm);        
            for (int i = 1; i < valores.length; i++) {
                ps.setObject(i, valores[i]);
            }
            ps.setObject(valores.length, valores[0]);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error actualizando " + ex);
            JOptionPane.showMessageDialog(null, stm);
            return false;
        }

    }
}
