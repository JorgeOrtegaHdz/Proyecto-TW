/*
Terminado, Falta Probar y corregir enlaces
Página casi estática, únicamente cambia si el usuario ha iniciado sesión (administrador y usuario) y si no lo ha 
hecho, la información la obtiene de la sesión HTTP de donde obtiene el tipo y el identificador
 */
package Paginas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erwin
 */
public class Inicio extends HttpServlet {

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
            out.println("<<HEAD>");
            out.println("<TITLE>FORO</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("<font face=\"Arial\" size=\"+3\"><BR/>");
            HttpSession session = request.getSession();
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");
            out.println("Bienvenido!");
            out.println("</font>");
            out.println("<font face=\"Arial\" size=\"+1\"><BR/><BR/>");
            out.println("Esto es Foro ESCOM, un lugar donde puedes resolver dudas a traves de discusiones en distintos foros :) <BR/><BR/>");
            if(tipoUsuario != null && tipoUsuario.equals("Administrador"))
            {
                out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
                out.println("<a href=\"Usuarios\">Administrar Usuarios</a><br/>");
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Agrega, edita y borra usuarios registrados</font>");
                out.println("</div><BR/>");
                out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
                out.println("<a href=\"Categorias\" target=\"frame\">Administrar Categorias</a><br/>");
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Agrega, edita y borra categorias</font>");
                out.println("</div><BR/>");
                out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
                out.println("<a href=\"estadisticas.html\">Estadisticas del sitio</a><br/>");
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Observe en graficas la actividad del sitio</font>");
                out.println("</div><br/>");
            }
            else
            {
                out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
                out.println("<A HREF=\"Categorias\">Categorias</A><br/>");
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Explora a traves de nuestras distintas categorias la materia y el tema de tu interes.</font>");
                out.println("</div><BR/>");
                out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
                out.println("<A HREF=\"Reglas\">Reglas</A><br/>");
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Para iniciar una nueva discusion antes esto es lo que debes saber.</font>");
                out.println("</div><BR/>");
            }
            out.println("</font>");
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
