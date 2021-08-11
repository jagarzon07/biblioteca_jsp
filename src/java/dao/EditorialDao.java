/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Editorial;

/**
 *
 * @author jose
 */
public class EditorialDao {
     public static boolean register(Editorial e) {
        try {
            String SQL="INSERT INTO editoriales (nit, nombre, telefono, direccion, email, sitioweb) VALUES(?, ?, ?, ?, ?, ?)";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, e.getNit());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getTelefono());
            ps.setString(4, e.getDireccion());
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getSitioweb());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
            else {
                return false;
            }
            
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList<Editorial> listEditorial() {
        
        ArrayList<Editorial> list = new ArrayList<>();
        
        try {
            String SQL="SELECT * FROM editoriales";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            Editorial edit;
            
            while (rs.next()) {
                edit = new Editorial();
                edit.setNit(rs.getString("nit"));
                edit.setNombre(rs.getString("nombre"));
                edit.setTelefono(rs.getString("telefono"));
                edit.setDireccion(rs.getString("direccion"));
                edit.setEmail(rs.getString("email"));
                edit.setSitioweb(rs.getString("sitioweb"));
                
                list.add(edit);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return list;
    }
    
    public static String getEditorial(String nit) {
        
        try {
            String SQL="SELECT nombre FROM editoriales WHERE nit = ? ";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nombre");
            }
            return "--";
        } catch (SQLException ex) {
            System.out.println(ex);
            return "--";
        }
    }
}
