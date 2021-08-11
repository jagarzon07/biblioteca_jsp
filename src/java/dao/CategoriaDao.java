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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;

/**
 *
 * @author jose
 */
public class CategoriaDao {
    
    public static boolean register(Categoria c) {
        try {
            String SQL="INSERT INTO categorias (nombre) VALUES(?)";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, c.getNombre());
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
    
    public static ArrayList<Categoria> listCategories() {
        
        ArrayList<Categoria> list = new ArrayList<>();
        
        try {
             String SQL="SELECT * FROM categorias";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            Categoria cat;
            
            while (rs.next()) {
                cat = new Categoria();
                cat.setCodigo(rs.getInt("codigo"));
                cat.setNombre(rs.getString("nombre"));
                list.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return list;
    }
    
    public static String getCategoria(int codigo) {
        
        try {
            String SQL="SELECT nombre FROM categorias WHERE codigo = ? ";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, codigo);
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
