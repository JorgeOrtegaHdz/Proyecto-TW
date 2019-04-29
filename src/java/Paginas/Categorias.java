/*
Terminado
 */
package Paginas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import XMLQuery.SimpleQuery;
import java.util.HashMap;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erwin
 */
// <editor-fold defaultstate="collapsed" desc="Página Categorías. Click on the sign on the left to edit the code.">
public class Categorias extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<HTML>");
            out.println("\t<HEAD>");
            out.println("\t\t<TITLE>Categorías</TITLE>");
            out.println("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("\t</HEAD>");
            out.println("\t<BODY BGCOLOR=\"#6699CC\" link=\"#000000\" alink=\"#ffffff\" vlink=\"#000000\">");
            HttpSession session = request.getSession();
            String tipoUsuario = (String)session.getAttribute("tipoUsuario");
            SimpleQuery consulta = new SimpleQuery(request.getRealPath("/")  + "categoria.xml");
            HashMap<String, String>[] categorias = consulta.select();
            if(tipoUsuario != null && tipoUsuario.equals("Administrador"))
            {
                out.println("<center>");
                out.println("<table border=\"3\" style=\"width:50%\">");
                out.println("<tr>");
                out.println("<td><font color=\"#FFFFFF\">Categoria</font></td>");
                out.println("</tr>");
                for(HashMap<String, String> categoria : categorias)
                {
                    out.println("<tr>");
                    out.println("<td><font color=\"#FFFFFF\">" + categoria.get("nombre") + "</font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Temas?identificador=" + categoria.get("idCategoria") + "\">Ir</a></font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Modificar?tipo=categoria&identificador=" + categoria.get("idCategoria") + "\">Cambiar nombre</a></font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Borrar?tipo=categoria&identificador=" + categoria.get("idCategoria") + "\">Borrar</a></font></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</CENTER>");
            }
            else
            {
                int top = 10, left = 50;
                for (HashMap<String, String> categoria : categorias) 
                {
                    out.println("\t\t<div class=\"Caja_Categoria\" style=\"top:" + top + "px;left:" + left + "px\">");
                    out.println("\t\t\t<A HREF=\"Temas?identificador=" + categoria.get("idCategoria") + "\">" + categoria.get("nombre") + "</A>");
                    out.println("\t\t</div>");
                    if(left == 550)
                    {
                        top += 100;
                        left = 50;
                    }
                    else
                        left += 250;
                }
            }
            out.println("\t</BODY>");
            out.println("</HTML>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the  sign on the left to edit the code.">
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
// </editor-fold>