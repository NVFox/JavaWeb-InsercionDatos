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

public class UsuarioDao {
    Conexion conn = new Conexion();
    Connection cnn = conn.conexionDB();
    PreparedStatement ps;
    
    public boolean insertarUsuario(Usuario usu) {
        try {
            ps = cnn.prepareStatement("INSERT INTO usuarios VALUES(?, ?, ?, ?, ?, ?) ");
            ps.setString(1,usu.getDocumento());
            ps.setString(2, usu.getUsuario());
            ps.setString(3, usu.getClave());
            ps.setString(4, usu.getRol());
            ps.setString(5, usu.getEstado());
            ps.setString(6, usu.getImagen());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error insertando " + ex);
            return false;
        }
    }
}
