<%-- 
    Document   : Mostrar
    Created on : 8/06/2018, 11:12:14 AM
    Author     : HP
--%>

<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" java.sql.ResultSet "%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/body.css">
    </head>
    <body>
        <!--button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">ver Resultados</button>
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <!--div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Resultados</h4>
                  </div>
                  <div class="modal-body">
                    <%--
                        ResultSet rs = (ResultSet) request.getAttribute("rs");
                        String [] columna = request.getParameterValues("columna");


                        out.print( "<div class='table-responsive'> <table class=\"table table-hover\">");            
                        out.print("<tr>" );            
                            for(int i=0; i<columna.length;i++){
                        out.print( "<th>"+ columna[i] +"</th>" ); }             
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
                        out.print( "</table>  </div> " );
                    --%> 
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                  </div>
                </div>
            </div>
        </div-->
                    <%
                        ResultSet rs = (ResultSet) request.getAttribute("rs");
                        String [] columna = request.getParameterValues("columna");


                        out.print( "<div class='table-responsive'> <table class=\"table table-hover\">");            
                        out.print("<tr>" );            
                            for(int i=0; i<columna.length;i++){
                        out.print( "<th>"+ columna[i] +"</th>" ); }             
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
                        out.print( "</table>  </div> " );
                    %>

             <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="assets/js/bootstrap.min.js"></script> 
    </body>
</html>
