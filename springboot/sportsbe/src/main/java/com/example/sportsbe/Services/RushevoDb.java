package com.example.sportsbe.Services;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.example.sportsbe.Models.*;

import com.github.cliftonlabs.json_simple.JsonObject;

public class RushevoDb {
    Connection _cn;

    public RushevoDb(){
        _cn = new Conexion().openDb();
    }
    //API DE ARTICULOS
    public List<Articulos> ObtenerArticulos(){
        try {
            Statement stmt = _cn.createStatement();
            String query = "SELECT * FROM articulos";

            List<Articulos> articulos = new ArrayList<>();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                Articulos articulo = new Articulos(
                    result.getInt("id_pag"),
                    result.getString("page_name"),
                    result.getString("description"),
                    result.getString("link")
                    );
                articulos.add(articulo);
            }
            result.close();
            stmt.close();
            return articulos;
        } catch (Exception e) {
            int x = 1;
            e.printStackTrace();
        }
        return null;
    }

    public int GuardarArticulos(Articulos articulo){
      int resultado = 0;
      try {
        Statement stm = _cn.createStatement();
        String query = "insert into articulos values ('" +
         articulo.getId_pag() + "','" + 
         articulo.getPage_name() + "','" + 
         articulo.getDescription() + "','" + 
         articulo.getLink() + "')";
        resultado = stm.executeUpdate(query);

          return resultado;     
      } catch (Exception e) {
          int x=1;
      }
      return resultado;
  }

    public int ActualizarArticulos(Articulos articulo) {
        int resultado = 0;
        try {
            Statement stm = _cn.createStatement();
            String query = "update articulos set page_name = '" +articulo.getPage_name() +
            "', description = '" + articulo.getDescription() +
            "', Link = '" + articulo.getLink()  +
            "' WHERE id_pag = '" + articulo.getId_pag() + "'";
            resultado = stm.executeUpdate(query);
    
          return resultado;
        } catch (Exception e) {
          int x = 1;
        }
        return resultado;
      }
      public int EliminarArticulo(int pid) {
        int resultado = 0;
        try {
          Statement stmt = _cn.createStatement();
          String query = "DELETE FROM articulo WHERE id_pag = "+pid;
    
          return stmt.executeUpdate(query);
    
        } catch (Exception e) {
          int x = 1;
        }
        return resultado;
      }
      //API de USUARIOS
    public List<Usuarios> ObtenerUsuarios(){
        try {
            Statement stmt = _cn.createStatement();
            String query = "SELECT * FROM usuarios";

            List<Usuarios> usuarios = new ArrayList<>();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                Usuarios usuario = new Usuarios(
                    result.getInt("id_user"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("nombre"),
                    result.getString("apellido"),
                    result.getDate("fecha_nac")
                    );
                usuarios.add(usuario);
            }
            result.close();
            stmt.close();
            return usuarios;
            
        } catch (Exception e) {
            int x = 1;
            e.printStackTrace();
        }
        return null;    
    }

    public int GuardarUsuarios(Usuarios usuario){
        int resultado = 0;
        try {
            Statement stm = _cn.createStatement();
            String query = "insert into usuarios values ('" +
             usuario.getId_user() + "','" + 
             usuario.getEmail() + "','" + 
             usuario.getPassword() + "','" + 
             usuario.getNombre() + "','" + 
             usuario.getApellido() + "','" + 
             usuario.getFecha_nac() + "')";
            resultado = stm.executeUpdate(query);

            return resultado;     
        } catch (Exception e) {
            int x=1;
        }
        return resultado;
    }

    public int ActualizarUsuario(Usuarios usuario) {
    int resultado = 0;
    try {
        Statement stm = _cn.createStatement();
        String query = "update usuarios set email = '" + usuario.getEmail() +
        "', password = '" + usuario.getPassword() +
        "', nombre = '" + usuario.getNombre() +
        "', apellido = '" + usuario.getApellido() +
        "', fecha_nac = '" + usuario.getFecha_nac() +
        "' WHERE id_user = '" + usuario.getId_user() + "'";
        resultado = stm.executeUpdate(query);

      return resultado;
    } catch (Exception e) {
      int x = 1;
    }
    return resultado;
  }

  public int EliminarUsuario(int pid) {
    int resultado = 0;
    try {
      Statement stmt = _cn.createStatement();
      String query = "DELETE FROM usuarios WHERE id_user = "+pid;

      return stmt.executeUpdate(query);

    } catch (Exception e) {
      int x = 1;
    }
    return resultado;
  }

  // Login y Registro

  public int ValidarUsuarios(Usuarios usuario){

    int resultado = 0;

    try {

        String email=usuario.getEmail();
        String pass=usuario.getPassword();

        PreparedStatement statement = _cn.prepareStatement("select * from usuarios where email = ? and password = ?");

        statement.setString(1, email); 
        statement.setString(2, pass); 
        ResultSet result = statement.executeQuery();


        /*Statement stm = _cn.createStatement();
        String query = "select * from usuarios where email = '"+ usuario.getEmail() + "' and password =     '"+ usuario.getPassword() + "'"; 
        ResultSet result = stm.executeQuery(query);*/

        if(result.next()){
            resultado = result.getInt("id_user");
        }
        result.close();
        statement.close();
        return resultado;
    } catch (Exception e) {
        int x=1;
    }
    return resultado;
}


        public List<Usuarios> ObtenerUsuariosPorId(int pid){
          try {
              Statement stmt = _cn.createStatement();
              String query = "SELECT * FROM usuarios WHERE id_user   = " + pid ;

              List<Usuarios> usuarios = new ArrayList<>();
              ResultSet result = stmt.executeQuery(query);
              while(result.next()){
                  Usuarios usuario = new Usuarios(
                      result.getInt("id_user"),
                      result.getString("email"),
                      result.getString("password"),
                      result.getString("nombre"),
                      result.getString("apellido"),
                      result.getDate("fecha_nac")
                      );
                  usuarios.add(usuario);
              }
              result.close();
              stmt.close();
              return usuarios;
              
          } catch (Exception e) {
              int x = 1;
              e.printStackTrace();
          }
          return null;    
        }


        public List<ImagenesArticulos> ObtenerImagenes(){
          try {
              Statement stmt = _cn.createStatement();
              String query = "SELECT * FROM imagenes_articulos";
  
              List<ImagenesArticulos> imagenes = new ArrayList<>();
              ResultSet result = stmt.executeQuery(query);
              while(result.next()){
                  ImagenesArticulos imagen = new ImagenesArticulos(result.getInt("id_img"), result.getInt("pag_id"), result.getString("img_name"), result.getString("img_url"));
                  imagenes.add(imagen);
              }
              result.close();
              stmt.close();
              return imagenes;
          } catch (Exception e) {
              int x = 1;
              e.printStackTrace();
          }
          return null;
      }
    



}
