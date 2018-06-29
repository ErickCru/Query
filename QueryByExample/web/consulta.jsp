<%-- 
    Document   : consulta
    Created on : 25/05/2018, 04:28:35 PM
    Author     : Argentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Hello World!</h1>
        <%
            String BD = request.getParameter("bd");
            String tabla = request.getParameter("tb");
            String select = request.getParameter("select");
            String cond = request.getParameter("where");
            String columna[] = request.getParameterValues("columna");  
        
             for(int i=0; i<columna.length;i++){
                        out.print( "<td>"+columna[i] +"</td>" ); }   

        %>
         
         <h3> BD <%=BD%></h3>
         <h3>tabla <%=tabla%></h3>
         <h3> select <%=select%></h3>
         <h3> cond <%=cond%></h3>
    </body>
</html>
