<%-- 
    Document   : tablas
    Created on : 22/05/2018, 01:01:55 PM
    Author     : Argentina
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tablas</title>
        <link rel="stylesheet" href="assets/design.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <% ResultSet rs = (ResultSet) request.getAttribute("rs");
           String BD = request.getParameter("bd");  
        %>
        <span id="shadowbox" onclick="menuToggle()"></span>
        <nav>
            <button id="navBtn" onclick="menuToggle()">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
            </button>
            <div id="nav" class="navigation">
                <div class="navigation__inner">
                    <ul id="listMenu">
                        <input hidden name="<%=BD%>" id="bd">
                        <h1> Tablas  </h1> 
                        <% 
                         String tabla = "";
                        while(rs.next()){                        
                            tabla = rs.getString("Tables_in_"+BD);
                            out.print("<li><button id=\"tabla\" name=\""+ tabla +"\" onclick=\"generarTabla(this.name)\">" + tabla + "</button></li>");
                           }
                        %>
                    </ul>
                </div>
            </div>
        </nav>
                    <br><br>
                    <button class= "btn btn-info" id="ejecutar" >Consultar</button>
                    <form id="form" >
                        <div id="tablaElegida" class="table-responsive">      </div>   
                    </form>
                    
                    <div id="tablaResultado"></div>
                    
            <script>
                $(document).ready(function(){
                    $('#ejecutar').click(function(){
                        var datos = $('#form').serialize();   
                        $.ajax({
                            type: "POST",
                            url:  "http://localhost:8080/QueryByExample/consulta2",
                            data: datos,
                            success: function (datos) { 
                                $("#tablaResultado").html(datos);
                            }    
                        });
                    });
                });
            </script>
        
            <script>
                function generarTabla(nombreTab){
                    var base = document.getElementById("bd").name;
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function() {
                      if (this.readyState === 4 && this.status === 200) {
                          addTable(JSON.parse(this.responseText),nombreTab,base );
                          
                        }
                    };
                    xhttp.open("POST", "Dibujarcolumnas", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
                    xhttp.send("tab="+nombreTab+"&base="+base);
                    console.log("tab="+nombreTab+"&base="+base);
                }
                
                function addTable(dat,nombreTab,base) {
                    var existencia = document.getElementById(nombreTab);
                     console.log(existencia);
                     console.log(nombreTab);
                    if(!document.body.contains(existencia)){
                        
                    var myTableDiv = document.getElementById("tablaElegida");  
                    console.log(dat);
                    var table = document.createElement('TABLE');

                    var tableBody = document.createElement('TBODY');
                    table.appendChild(tableBody);
                    var tr = document.createElement('TR');
                    tableBody.appendChild(tr);
                    var td2 = document.createElement('TD');
                    var td3 = document.createElement('TD');
                    var td4 = document.createElement('TD');
                    var td5 = document.createElement('TD');
                      
                    td2.width='75';
                    td3.width='75';
                    td2.innerHTML +=  "<select name=\"operacion\"> <option value=\"consultar\">consultar</option> <option value=\"insertar\">insertar</option> <option value=\"eliminar\">eliminar</option> </select>";
                    td3.innerHTML += nombreTab ;
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    for (var i = 0; i < dat.length ; i++){
                        if(i%2 === 0){
                            var td = document.createElement('TD');
                               td.width='75';
                               td.innerHTML +=  "<input type=\"checkbox\" name=\"columna\" value="+nombreTab+"."+dat[i]+"> &nbsp&nbsp"+ dat[i] +" <input type=\"text\" name=\""+nombreTab+"."+dat[i]+"\" value=\"\">";
                               tr.appendChild(td);
                        }
                        else{
                            var td = document.createElement('TD');
                               td.width='75';
                               td.innerHTML += "<input hidden name=\"tipo"+nombreTab+"."+dat[i-1]+"\" value="+dat[i]+">";
                               tr.appendChild(td);
                        }
                    }
                    td4.innerHTML += "<input hidden name='tablas' value=\""+nombreTab+"\">";
                    tr.appendChild(td4);
                    td5.innerHTML += "<input hidden name='bd' value=\""+base+"\">";
                    tr.appendChild(td5);
                    table.setAttribute("id",nombreTab);
                       table.classList.add("table");
                       table.classList.add("table-hover");
                       
                    myTableDiv.appendChild(table);
                    }
                    
                    
                    else{
                        alert("Tabla existente");
                    }
                }  
                
               /* function generarResultado(){
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function() {
                      if (this.readyState === 4 && this.status === 200) {
                          mostrar(JSON.parse(this.responseText) ); 
                          console.log(this.responseText);
                        }
                    };
                    xhttp.open("POST", "consulta2", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
                    xhttp.send();
                }
                
                function mostrar(datos){
                    
                    var myTableDiv = document.getElementById("tablaResultado");  
                    var table = document.createElement('TABLE');

                    var tableBody = document.createElement('TBODY');
                    table.appendChild(tableBody);
                    var tamanioTitulos = datos[0]; 
                    var tr = document.createElement('TR');
                    tableBody.appendChild(tr);
                    
                    for (var i = 1; i < tamanioTitulos.length ; i++){
                        var th = document.createElement('TH');
                           //td.width='75';
                           th.innerHTML +=  datos[i];
                           tr.appendChild(th);
                    }
                    
                    for (var i = tamanioTitulos+1; i < datos.length ; i++){
                        var td = document.createElement('TD');
                           td.width='75';
                           td.innerHTML += datos[i];
                           tr.appendChild(td);
                    }
                    table.classList.add("table");
                    table.classList.add("table-hover");
                       
                    myTableDiv.appendChild(table);
                }*/
                
            </script>
            
            <script src="assets/magic.js"></script>
            <script src="assets/js/bootstrap.min.js"></script>  
            
            
    </body>
</html>
