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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatosDAO {
    Conexion conn = new Conexion();
    Connection cnn = conn.conexionDB();
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario comprobarLogin(String nombre, String clave) {
        try {
            ps = cnn.prepareStatement("SELECT * FROM usuarios INNER JOIN clientes ON usuarios.DocCli = clientes.DocCli WHERE NomUsu = ? AND Clave = ?");
            ps.setString(1, nombre);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Integer documento = rs.getInt("DocCli");
                return new Usuario(documento.toString(), (rs.getString("NomCli") + " " + rs.getString("ApeCli")), nombre, clave, rs.getString("Rol"), rs.getString("Estado"));
            }
            
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta " + ex);
            return null;
        }
    }
    
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
    
    public Tabla consultarDatos(String nombreTabla, String[] campos, Usuario usuario) {
        ArrayList<String[]> lista = new ArrayList<>();
        String stm = "SELECT * FROM " + nombreTabla;
        
        try {
            if (usuario.getRol().equals("Cliente")) {
                if (campos[0].equals("CodLinea")) {
                    stm = "SELECT l.CodLinea, NomLinea, MontoMaxiCredito, PlazoMaxCred FROM lineas l "
                            + "INNER JOIN creditos c ON l.CodLinea = c.CodLinea";
                }
                stm += " WHERE DocCli = ?";
                
                ps = cnn.prepareStatement(stm);
                ps.setString(1, usuario.getDocumento());
            } else {
                ps = cnn.prepareStatement(stm);
            }
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
