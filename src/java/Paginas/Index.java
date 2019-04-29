/*
Terminado, Falta probar
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
// <editor-fold defaultstate="collapsed" desc="Página Index. Click on the sign on the left to edit the code.">
public class Index extends HttpServlet {

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
            HttpSession session = request.getSession();
            String identificadorUsuario = (String) session.getAttribute("identificadorUsuario");
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>FORO</TITLE>	");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            if(identificadorUsuario == null || identificadorUsuario.equals(""))
            {
                out.println("<div style=\"position:absolute;top:175px;left:1075px;\">");
                out.println("<a href=\"Login.html\">Iniciar sesion</a> ");
                out.println(" / ");
                out.println("<a href=\"Registro\" target=\"frame\">Registrarse</a>");
                out.println("</div>");
            }
            else
            {
                out.println("<div style=\"position:absolute;top:175px;left:1075px;\">");
                out.println("<a href=\"CerrarSesion\">Cerrar sesion</a> ");
                out.println(" / ");
                out.println("<a href=\"Perfil?identificadorUsuario=" + identificadorUsuario + "&tipoUsuario=" + tipoUsuario + "\" target=\"frame\">Perfil</a>");
                out.println("</div>");
            }
            out.println("<div class=\"Caja_Menu\" style=\"top:250px;left:20px;\"><a href=\"Inicio\" target=\"frame\">Inicio</a></div>");
            out.println("<div class=\"Caja_Menu\" style=\"top:285px;left:20px;\"><a href=\"Categorias\" target=\"frame\">Categorias</a></div>");
            out.println("<div class=\"Caja_Menu\" style=\"top:320px;left:20px;\"><a href=\"Colabora\" target=\"frame\">Colabora</a></div>	");
            out.println("<center>");
            out.println("<img src=\"imagenes/logo.png\"/ height=\"100\">");
            out.println("<br><br>");
            out.println("<div class=\"Ruta\">Inicio</div>");
            out.println("<IFRAME SRC=\"Inicio\" NAME=\"frame\" NORESIZE></iframe>");
            out.println("</center>");
            out.println("</BODY>");
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