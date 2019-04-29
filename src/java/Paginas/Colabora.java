/*
Sin avance
Página prácticamente estática, únicamente conecta con otras
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
public class Colabora extends HttpServlet {

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
        HttpSession secion=request.getSession();
        String identificador=(String) secion.getAttribute("identificadorUsuario");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("");
            out.println("<HEAD>");
            out.println("<TITLE>FORO</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("");
            out.println("<center>");
            out.println("<font face=\"Arial\" size=\"+3\"><BR/>");
            out.println("Colabora!");
            out.println("</font>");
            out.println("<font face=\"Arial\" size=\"+1\"><BR/><BR/>");
            out.println("Esto es Foro ESCOM, ayuda a nuestros compañeros a reolver dudas <BR/><BR/>");
            out.println("");
            out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
            if(identificador!=null){
                out.println("<font face=\"Arial\" size=\"+1\"><br/>¡Ya puedes colaborar aqui! Asi que te invitamos a comenzar<br/></font>");
            }else{
                out.println("<font face=\"Arial\" size=\"+1\"><br/>Crea un usuario y comienza a ayudar, clickea a continuación<br/></font>");
                out.println("<a href=\"Registro\">Registrate</A><br/>");
            }
            out.println("</div><BR/>");
            out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
            out.println("<A HREF=\"Categorias\">Categorias</A><br/>");
            out.println("<font face=\"Arial\" size=\"+1\"><br/>Responde las dudas de nuestros companeros, explora por las categorias</font>");
            out.println("</div><BR/>");
            out.println("<div style=\"width:750px; height:100px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:center; text-valign:center\">");
            out.println("<A HREF=\"NuevoTema\">Nuevo Tema</A><br/>");
            out.println("<font face=\"Arial\" size=\"+1\"><br/>Tienes dudas sobre algun tema? Inicia una nueva discusion aqui para que la comunidad te ayude :)<br/></font>");
            out.println("</div>");
            out.println("");
            out.println("</font>");
            out.println("<center>");
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
