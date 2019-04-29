/*
Terminado, Falta probar
Permite a un usuario crear una nueva discusión, obteniendo el tipo de usuario y el id de usuario de la sesión HTTP
 */
package Paginas;

import XMLQuery.SimpleQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erwin
 */
// <editor-fold defaultstate="collapsed" desc="Página Nuevo Tema. Click on the + sign on the left to edit the code.">
public class NuevoTema extends HttpServlet {

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
            out.println("<TITLE>Nuevo tema</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("<div style=\"width:710px; background:#66CCCC; border:#000000 5px solid; font-size:30; text-align:justify; padding-left:20px; padding-right:20px\">");
            out.println("<center><br/>Nueva Discusion<br/>");
            out.println("<font size=\"+1\">");
            HttpSession session = request.getSession();
            String identificadorUsuario = (String) session.getAttribute("identificadorUsuario");
            if(identificadorUsuario == null || identificadorUsuario.equals(""))
            {
                out.println("Inicia sesion o <a href=\"Registro\">registrate</a> para crear discusiones");
            }
            else
            {
                out.println("<form method=\"post\" action=\"CrearTema\">");
                out.println("<p align=\"left\">Categoria<select name=\"categoria\" id=\"categoria\">");
                {
                    SimpleQuery consulta = new SimpleQuery(request.getRealPath("/") + "categoria.xml");
                    HashMap<String, String>[] categorias = consulta.select();
                    for (HashMap<String, String> categoria : categorias) 
                    {
                        out.println("<option value=\"" + categoria.get("idCategoria") + "\">");
                        out.println(categoria.get("nombre"));
                        out.println("</option>");
                    }
                }
                out.println("</select></p><br/>");
                out.println("<div class=\"Caja_Res\" style=\"width:700px\">");
                out.println("Titulo");
                out.println("<input type=\"text\" name=\"titulo\" id=\"titulo\" size=\"105\" placeholder=\"Cual es la duda?\"/><br/><br/>");
                out.println("<center>");
                out.println("<textarea rows=\"7\" cols=\"85\" placeholder=\"Describe aqui tu duda\" name=\"contenido\" id=\"contenido\"></textarea>");
                out.println("</center>");
                out.println("<p align=\"right\"><input type=\"submit\" value=\"Enviar\"/></p> ");
                out.println("</div>");
                out.println("</form>");
            }
            out.println("</font>");
            out.println("</center><br/>");
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