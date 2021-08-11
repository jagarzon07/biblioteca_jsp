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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Libro;

/**
 *
 * @author jose
 */
public class LibroDao {
      public static boolean register(Libro l) {
        
          try {
            String SQL="INSERT INTO libros (isbn, titulo, nombre_autor, publicacion, codigo_categoria, fecha_registro, nit_editorial, descripcion)" + 
                    " VALUES(?, ?, ?, ?, ?, (select now()), ?, ?)";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
          
            ps = conn.prepareStatement(SQL);
        
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setString(3, l.getNombre_autor());
            ps.setString(4, l.getPublicacion());
            ps.setInt(5, l.getCodigo_categoria());
            ps.setString(6, l.getNit_editorial());
            ps.setString(7, l.getDescripcion());
                        
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
    
    public static ArrayList<Libro> listBooks() {
        
        ArrayList<Libro> list = new ArrayList<>();
        
        try {
            String SQL="SELECT * FROM libros";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            Libro libro;
            
            while (rs.next()) {
                libro = new Libro();
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setNombre_autor(rs.getString("nombre_autor"));
                libro.setPublicacion(rs.getString("publicacion"));
                libro.setCodigo_categoria(rs.getInt("codigo_categoria"));
                libro.setNit_editorial(rs.getString("nit_editorial"));
                
                list.add(libro);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return list;
    }
    
    public static boolean update(Libro l) {
        try {
            String SQL="UPDATE libros SET " +
                    " titulo = ?, " + 
                    " descripcion = ?, " + 
                    " nombre_autor = ?, " + 
                    " publicacion = ?, " + 
                    " codigo_categoria = ?, " + 
                    " nit_editorial = ? " + 
                    " WHERE isbn = ? ";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getDescripcion());
            ps.setString(3, l.getNombre_autor());
            ps.setString(4, l.getPublicacion() );
            ps.setInt(5, l.getCodigo_categoria());
            ps.setString(6, l.getNit_editorial());
            ps.setString(7, l.getIsbn());
            
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
    
    public static boolean delete(Libro l) {
        try {
            String SQL="DELETE FROM libros WHERE isbn = ?";
            Connection conn = Conexion.get_connection();

            PreparedStatement ps;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, l.getIsbn());
            
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
}
