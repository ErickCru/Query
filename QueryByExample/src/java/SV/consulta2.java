/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SV;

import CRUD.CRUD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Argentina
 */
public class consulta2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       
             //Recepcion de datos
            String tablas[] =  request.getParameterValues("tablas"); 
            String columna[] = request.getParameterValues("columna");
            String tabla = request.getParameter("tablas");
            String input[] = new String[columna.length];
            String tipo[] = new String[columna.length];
            for(int i = 0; i<columna.length; i++){
                input[i] = request.getParameter(columna[i]);
                tipo[i] = request.getParameter("tipo"+columna[i]);
            }
           
            for(int i = 0; i<tablas.length; i++)
               System.out.println("tablas: " + tablas[i]);
            
           for(int i = 0; i<columna.length; i++)
               System.out.println("tipo: " + tipo[i]);
           
           
           String BD =  request.getParameter("bd");
           String operacion =  request.getParameter("operacion");
            System.out.println("Tabla: " + tablas[0] +"\nBase" + BD + "\noperacion" + operacion);
              String var ="";
         
            CRUD ob = new CRUD(); 
            ResultSet s = ob.use(BD);
            
            //String condicion ="";         
            String cade ="";
      
             ResultSet rs = null;

         
        if(operacion.equals("consultar")){
            
            //Generar Select
            cade= ObtenerSelect(columna);            

            //Obtiene condicion         
           ArrayList<String> datos = new ArrayList<>();
           datos = ObtenerCondicion(columna, input, tipo);
           
           //Consulta
            if(datos.size()==3){
            int j = ob.change(datos.get(0), datos.get(1), tabla, datos.get(2));
                if(j != -1)
                 out.print("<br> <h2> Se actualizo correctamente </h2> <br>"); 
                else
                 out.print("<br> <h2> No se pudo actualizar, verifique sus condiciones </h2> <br>"); 
            // response.sendRedirect("columnas?tb="+tabla+"&bd="+BD+"");
            }
           else{
               String llaves = "";
                if(tablas.length>1){
                   for( int i=0; i<tablas.length;i++)
                    {
                        try {
                             ResultSet h = ob.columnas(tablas[i]);
                                //para obtener llaves primarias
                             while(h.next()){
                                if (h.getString("Key").equals("PRI") || h.getString("Key").equals("MUL") ){
                                    var = h.getString("Field") ;
                                    System.out.println("key: "+h.getString("Field") +h.getString("Key"));
                                }
                                }   
                              llaves= llaves + tablas[i]+"."+var + "=";
                            } catch (SQLException ex) {
                             Logger.getLogger(consulta2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                   char [] e = llaves.toCharArray();
                if (e[e.length - 1] == '=')
                    llaves = llaves.substring(0, llaves.length() -1);
                System.out.print("key "+llaves);
                
                }
                String t = "";
                                  
                    for(int i = 0; i<tablas.length; i++ ){
                        t += tablas[i] + ",";
                    }
                
                char [] e = t.toCharArray();
                if (e[e.length - 1] == ',')
                    t = t.substring(0, t.length() -1);
                
               if(llaves.equals("")){
                rs = ob.select(cade, t, datos.get(0));
               } else{
                   String condicion="";
                        if(datos.get(0).equals("")){
                        condicion=llaves;
                        } else{
                        condicion=llaves +" and "+ datos.get(0);
                        }                                     
                rs = ob.select(cade, t,condicion);
               }
               //out.print(this.estructuraJson(rs,columna));
               String V="";
                out.print( " <table class=\"table table-hover\">");            
                        out.print("<tr>" );            
                            for(int i=0; i<columna.length;i++){
                                 StringTokenizer st = new StringTokenizer(columna[i],".");
                            while (st.hasMoreTokens())  
                                V = st.nextToken();
                        System.out.println("V: "+V);                                
                                out.print( "<td> <b>"+ V +"</b> </td>" ); 
                            }             
                        out.print( "</tr>" );

                       try
                       {
                            while(rs.next()){
                                out.print( "<tr>" );
                                for(int i=0; i<columna.length;i++){
                                out.print( "<td>"+rs.getString(columna[i]) +"</td>" ); }
                                out.print( "</tr>" );
                            }
                        } catch (SQLException ex) {
                            out.print( ex );
                        }
                        out.print( "</table>  " );
   
           }
        }
        else if(operacion.equals("insertar")){
           String result="", col=""; //Declaracion de variables
           //Llamada para obtener columnas y valore
            col = ListColumn(columna);
            result = ListInput(input,tipo);
            int p  = columna.length;
            
            //Consulta
            int k =ob.insert(tabla, col, result);
         
             if(k==1){
            String V="";
                out.print( "<br> <table class=\"table table-hover\">");   
                    out.print("<tr> <td COLSPAN=\""+p+"\"> <b> Nuevos Datos  </b></td>  </tr>" );  
                     out.print("<tr>" );            
                    for(int i = 0; i<columna.length;i++){
                         StringTokenizer st = new StringTokenizer(columna[i],".");
                            while (st.hasMoreTokens())  
                                V = st.nextToken();
                        out.print( "<td><b>"+ V +"</b></td>" ); 
                    }             
                    out.print( "</tr>" );
                    out.print( "<tr>" );
                         for(int i=0; i<input.length;i++){
                           out.print( "<td>"+input[i] +"</td>" ); 
                         }
                        out.print( "</tr>" );
                out.print( "</table>" );         
            }
            else{
                out.print( "<h2> No se pudo insertar, Cheque sus campos </h2>");  
            }
        }
        else if(operacion.equals("eliminar")){                
           
               //Generar select de campos para eliminar
               String con ="";
               for(int i=0;i<columna.length;i++){  
                   con+=columna[i]+"="+"'"+input[i]+"'";
                   if(i<(columna.length-1)){
                       con+="  AND  ";
                   }
                } 
                int i = ob.delete(tabla,con);
                System.out.print(i);
                if(i == 1)
                   out.println("<h2>  Eliminado </h2>");
                else                      
                out.println("<h2> No se pudo Eliminar </h2>");
                  //  response.sendRedirect("columnas?tb="+tabla+"&bd="+BD+""); 
                    
            //Fin del if  ----------------------------------------------------------------------------------------------         
                 
            } 
              
        
        }
    } 
        
    
    // METODOS--------------------------------------------------------------------------------------------------------
    /*private String estructuraJson(ResultSet datos, String titulos[]){
        String  json= "[";  
        json+= "\""+titulos.length+"\",";
        for(int i = 0; i<titulos.length;i++){
            json += "\""+titulos[i]+"\",";
        }
         System.out.print("El valor del json:" + json);
        try{
            for (String titulo : titulos) {
                while (datos.next()) {
                    json += "\"" + datos.getString(titulo) + "\"";
                    if(datos.isLast())
                        json += "";
                    else
                        json += ",";
                }
            }
        }catch(SQLException e){System.out.print(e);}
        System.out.print(json+"]");
        System.out.print("El valor del json");
        return json+"]";
    }*/
    
    public String ListColumn(String columna[]){
         String columnas="",result="";
         for (int i = 0; i<columna.length; i++) {
             columnas = columnas + columna[i] + ","; 
        }
         
         char [] e = columnas.toCharArray();
            if (e[e.length - 1] == ',')
             result = columnas.substring(0, columnas.length() -1);           
         
         return result;
    }
    
    public String ListInput(String input[], String tipo[]){
         String in="",result="";
         for (int i = 0; i<input.length; i++) {
             if (!tipo[i].contains( "int") && !tipo[i].contains( "float")){
                in = in + "'"+ input[i]+"' ,";  
             }else{
             in = in + input[i] + ","; 
             }
        }         
         
         char [] e = in.toCharArray();
            if (e[e.length - 1] == ',')
             result = in.substring(0, in.length() -1);           
         
         return result;
    }
     
    public String ObtenerSelect(String columna[]){
        String cade="", cadena="";
           //For para crear mi select que se va a la consulta
            for (int i = 0; i<columna.length; i++) {
                cade = cade + columna[i] + ","; 
            }   
           // Para quitar la ultima , del select
        char [] e = cade.toCharArray();
            if (e[e.length - 1] == ',')
             cadena = cade.substring(0, cade.length() -1);
    System.out.println(cadena+"SLCT");
    return cadena;
   }
  
    public ArrayList<String> ObtenerCondicion(String columna[], String input[],String tipo[]){
        ArrayList<String> datos = new ArrayList<>();
        String condicion="", u="",v="", c="",  d=""; 
             
        
        
        //Saber el campo seleccionado          
            for( int i=0; i<input.length;i++)
            {
                 if(!input[i].equals("")) //campo que no este vacio
                 {   
              
                    if (input[i].contains("changeto")) {
                        u= input[i].substring(0,input[i].length());
                        System.out.println("oración: "+ u+ ". ");     
                        
                       for(int p=0;p<input[i].length();p++)
                        {  
                            if (input[i].charAt(p) == ',')
                            {   break;  }
                             else{
                              c=c+input[i].charAt(p);                          
                            }  
                        }
                       String viejo=columna[i]+" = '"+c+"'";
                                                              
                        StringTokenizer st = new StringTokenizer(u,",");
                            while (st.hasMoreTokens())  
                                v = st.nextToken();
                      
                        StringTokenizer s = new StringTokenizer(v," ");
                            while (s.hasMoreTokens())  
                                d = s.nextToken();
                            
                        String nuevo=columna[i]+" = '"+d+"'";
                                               
                       datos.add(nuevo);
                       datos.add(viejo); 
                    }                      
                    else  if(input[i].startsWith("<")){
                       u= input[i].substring(0,input[i].length());
                       condicion = columna[i]+u + " AND ";                   
                    }
                    else if( input[i].startsWith("<=")){                             
                       u= input[i].substring(0,input[i].length());
                       condicion = columna[i]+u;
                    }
                    else if(input[i].startsWith(">")){
                       u= input[i].substring(0,input[i].length());
                       condicion = columna[i]+u + " AND ";
                    }
                    else if( input[i].startsWith(">=") ){
                       u= input[i].substring(0,input[i].length());
                       condicion = columna[i]+u + " AND ";
                    }
                    else if(input[i].startsWith("..") && input[i].endsWith("..")){
                        u = input[i].substring(2,input[i].length() -2);//Obtiene la cadena intermedia
                        condicion = columna[i]+" LIKE '_%"+u+"%_'" + " AND ";
                    }
                    else if (input[i].startsWith("..") ){
                        u = input[i].substring(2,input[i].length()); //Obtiene la cadena despues de los puntos
                        condicion = columna[i]+" LIKE '%"+u+"'" + " AND ";
                    }
                    else if( input[i].endsWith("..")){
                        u =input[i].substring(0,input[i].length() -2); //Obtiene la primera cadena antes de los puntos
                        condicion = columna[i]+" LIKE '"+u+"%'"+ " AND ";
                    }                                                                
                    else  if  (tipo[i].contains("int") ){  //if en caso de que NO comience con .. y sea de tipo int                       
                        condicion = condicion + columna[i]+ " = "+ input[i]+" AND ";
                    }
                    else if( tipo[i].contains( "float") ){
                         condicion = condicion + "cast(" + columna[i]  + " as decimal) = cast(" + input[i] +" as decimal) AND ";
                    }
                    else  if  (tipo[i].contains("double") ){  //if en caso de que NO comience con .. y sea de tipo int                       
                        condicion = condicion + columna[i]+ " = "+ input[i]+" AND ";
                    }
                    else{
                        condicion = condicion + columna[i]+ " = '"+ input[i]+"' AND "; // en caso de que sea tipo varchar
                    }               
                }
                else{
                    condicion = condicion+""; //Si el campo de txt esta vacio
                }      
                 
            }   
        //Eliminar el ultimo AND
           if(!condicion.equals("")){            
                char [] b = condicion.toCharArray();
                if (b[b.length - 4] == 'A'){
                    condicion = condicion.substring(0, condicion.length() -4);
                }
            }
           
           datos.add(condicion);
                   
       return datos;        
 }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
