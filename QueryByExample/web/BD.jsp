<%-- 
    Document   : BD
    Created on : 21/05/2018, 02:58:25 PM
    Author     : Argentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" CRUD.* "%>
<%@page import=" java.sql.ResultSet "%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Base de datos</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/body.css">
    </head>
    
    <body>
        <%  ResultSet rs = (ResultSet) request.getAttribute("rs");     
               
        %>
        <center>
         <h3> Lista de base de datos  </h3>
         <br><br>
        <%
        while(rs.next()){
        String BD = rs.getString("Database");
        String liga = "http://localhost:8080/QueryByExample/tablas?bd="+ rs.getString("Database")+"\"";
        if(!BD.equals("information_schema") && !BD.equals("performance_schema")&& !BD.equals("phpmyadmin") && !BD.equals("mysql") && !BD.equals("test") )
            out.print( "<a class='btn btn-info btn-lg btn-block' href=\""+liga+"\" role='button'>"+BD+"</a><br>");     
        }
       %>
       </center>
 

<script src="assets/js/bootstrap.min.js"></script>

    </body>
</html>
