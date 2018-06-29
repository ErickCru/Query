/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Argentina
 */
public class CRUD {
    private Conexion conexion = new Conexion();
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public CRUD() {
              try{
            if((con = conexion.getConexionMYSQL())==null){
                JOptionPane.showMessageDialog(null,"Error con la conexion a la BD");
                return;
            }
            st = con.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     //Consulta Base de Datos
    public ResultSet DataBase(){
        try {
            String query = "show databases";
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Crear base de datos
     public ResultSet crearBD(String nomBD){
        try {
          String query = "create database " + nomBD;        
            st = con.prepareStatement(query);
            st.executeUpdate(query);
        } catch (Exception ex) {
        System.out.println(ex);
    }
        return null;
    }
     
    //Eliminar BD
      public ResultSet EliminarBD(String BD ){
        try {
          String query = "drop database " + BD;        
            st = con.prepareStatement(query);
            st.executeUpdate(query);
        } catch (Exception ex) {
        System.out.println(ex);
    }
        return null;
    }
         
    //Usar Base de Datos
     public ResultSet use(String BD){
        try {
            String query = "use " + BD;
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Ver tablas de la BD seleccionada
    public ResultSet tabla(String BD){
        try {
            String query = "show tables FROM "+BD;
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Eliminar Tabla seleccionada
     public ResultSet Eliminartabla(String BD){
        try {
            String query = "Drop table "+BD;
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    //Ver columnas de la tabla seleccionada
     public ResultSet columnas(String tabla){
        try {
            String query = "describe "+tabla;
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
     
     //Ver consultas
     public ResultSet select(String cade, String tabla,String condicion){
         try {
              String query="";
             if(condicion.equals("")){
                query = "Select "+cade+" from "+tabla;
                System.out.print(query);
             } 
             else{
                query = "Select "+cade+" from "+tabla+" WHERE "+ condicion;
                System.out.print(query);
             }
          
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    public int change(String nuevo,String viejo, String tabla,String condicion){
         try {
              String query="";    
                query = " UPDATE "+tabla+" Set "+ nuevo +" WHERE ( "+ condicion +" ) AND ( "+ viejo+ ")";
                 System.out.print(nuevo+" y "+viejo);
                System.out.print(query);                       
           
            return st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
     
      //Inserta un nuevo correo en la Base de Datos
    public int insert(String tabla,String columnas,String valores){
        try {
            String query = "INSERT INTO "+tabla+" ( "+columnas+" ) VALUES ( "+valores+" )";
           
                 System.out.print(query);        
            return st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int delete(String tabla,String condicion){
        try {
            String query="";    
            query = " DELETE  FROM "+ tabla +" WHERE "+condicion+";";               
            return st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
     
}
