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
public class Registro extends HttpServlet {

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
        HttpSession sesion=request.getSession();
        String tipoUsuario=(String) sesion.getAttribute("tipoUsuario");
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registro</title>");   
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</head>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>\n" +
"		<div style=\"width:700px; background:#CCCCCC;border:#000000 3px solid;text-align:left;margin:10px;padding-left:50px; padding-top:25px;\">\n" +
"		<form method=\"get\" action=\"Alta\"><font face=\"Arial\">	\n");
            out.println("<select name='tipoU'>");
            out.println("<option value='alumno'>Alumno</option>");
            if(tipoUsuario!=null && tipoUsuario.equals("Administrador")){
                out.println("<option value='profesor'>Profesor</option>");
                out.println("<option value='administrador'>Administrador</option>");
            }
            out.println("</select>");
            out.println("Nombre <input pattern=\"(\\D){3,15}\" type=\"text\" name=\"nombre\" id=\"nombre\" "+
                    "title='De 3 a 15 caracteres sin algun numero'/><BR/><BR/>");
            out.println("Apellidos <input pattern=\"(\\D){3,20}\" type=\"text\" name=\"apellidos\" id=\"apellidos\""+
                    "title='De 3 a 20 caracteres sin algun numero' /><br/><br/>");
            out.println("Nombre Usuario <input pattern=\"(\\S|\\d){8,30}\" type=\"text\" name=\"alias\" id=\"alias\""+
                    "title='De 8 a 30 caracteres' /><br/><br/>");
            out.println("Contrasena <input pattern='(\\d|\\D){6,10}' type=\"password\" name=\"contrasena\" id=\"contrasena\""+
                    "title='De 6 a 10 caracteres' /><br/><br/>");
            out.println("Repetir contrasena <input type=\"password\" name=\"contrasena2\" id=\"contrasena2\"><br/><br/>");
            out.println("<br/><br/><br/><br/>\n" +
"			<input type=\"submit\" value=\"Registrar\"/>\n" +
"			<a href=\"Inicio\">Cancelar</a>\n" +
"		</font></form>\n" +
"		\n" +
"		<div style=\"width:300px;height:10px;position:relative;left:350px;top:-300px;font-family:Arial;text-align:center; font-size:20;\">Completa todos los campos para registrarte en el sistema y poder comenzar discusiones y ayudar a otros compañeros :) <br/> <br/> Al registrarte estas dispuesto a seguir las reglas establecidas y aceptas la correspondiente sancion en caso de no cumplirlas</div>\n" +
"		\n" +
"		</div><BR/>\n" +
"\n" +
"	</center>");
            out.println("</body>");
            out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
