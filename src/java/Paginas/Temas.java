/*
Terminado
Muestra los temas, que son publicaciones creadas por profesores (posteriormente por alumnos) en las cuales los alumnos
(posteriormente los profesores) pueden comentar y ser puntuados
Esta página no sufre cambios con la inclusión de alumnos creadores de temas pues no muestra el creador
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
import java.util.Random;

/**
 *
 * @author Erwin
 */
// <editor-fold defaultstate="collapsed" desc="Página Temas. Click on the sign on the left to edit the code.">
public class Temas extends HttpServlet {

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
        String identificadorCategoria = request.getParameter("identificador");
        try (PrintWriter out = response.getWriter()) {
            out.println("<HTML>");
            out.println("\t<HEAD>");
            out.println("\t\t<TITLE>Temas</TITLE>");
            out.println("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("\t</HEAD>");
            out.println("\t<BODY BGCOLOR=\"#6699CC\" link=\"#000000\" alink=\"#ffffff\" vlink=\"#000000\">");
            out.println("\t\t<center>");
            SimpleQuery consultaTemas = new SimpleQuery(request.getRealPath("/") + "tema.xml");
            SimpleQuery consultaPublicaciones = new SimpleQuery(request.getRealPath("/") + "publicacion.xml");
            HashMap<String, String>[] temas = consultaTemas.select("idCategoria", identificadorCategoria);
            for (HashMap<String, String> tema : temas)
            {
                HashMap<String, String>[] publicaciones = consultaPublicaciones.select("idTema", tema.get("idTema"));
                out.println("\t\t\t<div class=\"Caja_Tema\">");
                out.println("\t\t\t\t<A HREF=\"Discusion?identificador=" + tema.get("idTema") + "\">" + tema.get("titulo") + "</A>");
                out.println("\t\t\t\t<div class=\"Caja_TemaDes\">" + publicaciones[new Random().nextInt(publicaciones.length)].get("contenido") + "</div>");
                out.println("\t\t\t</div><BR>");
            }
            out.println("\t\t</center>");
            out.println("\t</BODY>");
            out.println("</HTML>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the sign on the left to edit the code.">
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