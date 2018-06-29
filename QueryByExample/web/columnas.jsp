<%-- 
    Document   : columnas
    Created on : 22/05/2018, 04:46:53 PM
    Author     : Argentina
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
                function seleccionar_todo(){ 
                for (i=0;i<document.f1.elements.length;i++) 
                    if(document.f1.elements[i].type === "checkbox")	
                     document.f1.elements[i].checked=1 
                }
        </script> 
    </head>
    <body>
         <% ResultSet rs = (ResultSet) request.getAttribute("rs"); 
            String tabla = request.getParameter("tb");
            String bd = request.getParameter("bd");
           
         %>
          <center>
        <table >
            <tr>
            <form method="GET" action="consulta2" name="f1">
                <%
                  out.print( "<td>" + tabla + "</td>" );               
                 while(rs.next()){  
                  String col = rs.getString("Field");
                  String tipo = rs.getString("Type");
                  out.print( "<input hidden name=\"type\" value="+tipo+">");
                  out.print( "<td> <input type=\"checkbox\" name=\"columna\" value="+col+">"+ col +" <input type=\"text\" name=\"txt\" value=\"\"> </td>" );               
                  }            
                %>
                        <input hidden name="tabla" value="<%=tabla%>">
                        <input hidden name="bd" value="<%=bd%>">
                  <input type="submit" value="Consultar">
                </form>
            </tr>
          </table>
        </center>                
                    
    </body>    
</html>
