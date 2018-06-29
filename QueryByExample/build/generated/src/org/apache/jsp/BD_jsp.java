package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import CRUD.*;
import java.sql.ResultSet;

public final class BD_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Base de datos</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/design.css\">\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        ");
  ResultSet rs = (ResultSet) request.getAttribute("rs");     
               
        
      out.write("\n");
      out.write("        <center>\n");
      out.write("         <h3> Crear nueva base de datos!  </h3>\n");
      out.write("        <form action=\"NewBD\" method=\"get\">\n");
      out.write("            Nombre <input type=\"text\" name=\"BD\" value=\"\">   <br><br>          \n");
      out.write("            <input type=\"submit\" value=\"Crear\">\n");
      out.write("        </form>\n");
      out.write("        </center>\n");
      out.write("    \n");
      out.write("    <span id=\"shadowbox\" onclick=\"menuToggle()\"></span>\n");
      out.write("    <nav>\n");
      out.write("\t<button id=\"navBtn\" onclick=\"menuToggle()\">\n");
      out.write("\t\t<div></div>\n");
      out.write("\t\t<div></div>\n");
      out.write("\t\t<div></div>\n");
      out.write("\t\t<div></div>\n");
      out.write("\t</button>\n");
      out.write("\n");
      out.write("        <div id=\"nav\" class=\"navigation\">\n");
      out.write("        <div class=\"navigation__inner\">\n");
      out.write("\t<ul id=\"listMenu\">\n");
      out.write("            \t<h1> Lista de Base de Datos en MySQL </h1>    \n");
      out.write("      ");
 
        while(rs.next()){
        String BD = rs.getString("Database");
        String liga = "http://localhost:8080/QueryByExample/tablas?bd="+ rs.getString("Database")+"\"";
        if(!BD.equals("information_schema") && !BD.equals("performance_schema")&& !BD.equals("phpmyadmin") && !BD.equals("mysql") && !BD.equals("test") )
            out.print( "<li><a href=\""+liga+"\">"+BD+"</li>");     
        }
       
      out.write("\n");
      out.write("        </ul>\n");
      out.write("        </div>\n");
      out.write("        </div>\n");
      out.write("    </nav>\n");
      out.write("\n");
      out.write("<script src=\"assets/magic.js\"></script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
