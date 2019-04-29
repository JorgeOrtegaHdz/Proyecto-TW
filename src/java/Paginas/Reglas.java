/*
Terminado
Muestra las reglas, es una página completamente estática
 */
package Paginas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erwin
 */
// <editor-fold defaultstate="collapsed" desc="Página Reglas. Click on the sign on the left to edit the code.">
public class Reglas extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<HEAD>");
            out.println("<TITLE>FORO</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("<div style=\"width:710px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:justify; padding-left:20px; padding-right:20px\">");
            out.println("<center><br/>Reglas<br/><br/></center>");
            out.println("<font face=\"Arial\" size=\"+1\">Para mantener la paz y el mutuo respeto en este sitio, al crear y al responder una discusion deberas tener en cuenta los siguientes puntos:\n");
            out.println("1.- No hacer comentarios ofensivos ni discriminatorios hacia otros usuarios. La sancion sera BAN hacia al usuario agresor.<br/><br/>\n");
            out.println("2.- No hacer spam ni copiar y pegar cadenas. Se prohibira hacer publicaciones durante 15 dias al usuario que viole esta regla<br/><br/>\n");
            out.println("3.- No publicar contenido pornografico. Se prohibira hacer publicaciones durante 15 dias al usuario que viole esta regla<br/><br/>\n");
            out.println("No nos hacemos responsables por atentados que puedan ocurrirte en este sitio. <br/><br/>");
            out.println("</font>");
            out.println("</div>");
            out.println("</center>");
            out.println("</BODY>");
            out.println("</html>");
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