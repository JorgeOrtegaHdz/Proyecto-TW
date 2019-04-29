package Paginas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import XMLQuery.SimpleQuery;
import java.util.HashMap;

public class Alta extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruta=request.getRealPath("/");
        SimpleQuery maneja=null;
        response.setContentType("text/html;charset=UTF-8");
        String tipoU=request.getParameter("tipoU");
        String nombre=request.getParameter("nombre");
        String apellidos=request.getParameter("apellidos");
        String alias=request.getParameter("alias");
        String contrasena=request.getParameter("contrasena");
        String contrasena2=request.getParameter("contrasena2");
        HttpSession sesion=request.getSession();
        String tipoUsuario=(String) sesion.getAttribute("tipoUsuario");
        HashMap<String, String> data = new HashMap<>();
        if(tipoU.equals("Administrador"))
            maneja=new SimpleQuery(ruta+"administrador.xml");
        else{
            if(tipoU.equals("Profesor"))
                maneja=new SimpleQuery(ruta+"profesores.xml");
            else{
                maneja=new SimpleQuery(ruta+"alumnos.xml");
            }
        }
        if(contrasena!=null && contrasena.equals(contrasena2) && maneja.select("idUsuario", alias)==null){
            data.put("identificador", ""+(maneja.getMax()+1));
            maneja.insert(data);
            data.put("idUsuario", alias);
            maneja.insert(data);
            data.put("contraseña", contrasena);
            maneja.insert(data);
            data.put("nombre", nombre+apellidos);
            maneja.insert(data);
            response.sendRedirect("Index");
        }else{
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Registro</title>");   
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../estilo.css\">");
                out.println("</head>");
                out.println("<body BGCOLOR=\\\"#6699CC\\>");
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
                out.println("Nombre <input type=\"text\" name=\"nombre\" id=\"nombre\"/><BR/><BR/>\n" +
    "			Apellidos <input type=\"text\" name=\"apellidos\" id=\"apellidos\"/><br/><br/>\n" +
    "			Nombre Usuario <input type=\"text\" name=\"alias\" id=\"alias\"/><br/><br/>\n" +
    "			Contrasena <input type=\"password\" name=\"contrasena\" id=\"contrasena\"><br/><br/>\n" +
    "			Repetir contrasena <input type=\"password\" name=\"contrasena2\" id=\"contrasena2\"><br/><br/>");
                if(maneja.select("idUsuario", alias)!=null)
                    out.println("<h5 style='color=red;'>Nombre de usuario ya existente/Introducir nuevamente</h5>");
                else
                    out.println("<h5 style='color=red;'>Error en la contraseña/Introducir nuevamente</h5>");
                out.println("<br/><br/><br/><br/>\n" +
    "			<input type=\"submit\" value=\"Registrar\"/>\n" +
    "			<a href=\"Index\">Cancelar</a>\n" +
    "		</font></form>\n" +
    "   		\n" +
    "		<div style=\"width:300px;height:10px;position:relative;left:350px;top:-300;font-family:Arial;text-align:center; font-size:20;\">Completa todos los campos para registrarte en el sistema y poder comenzar discusiones y ayudar a otros compa�eros :) <br/> <br/> Al registrarte estas dispuesto a seguir las reglas establecidas y aceptas la correspondiente sancion en caso de no cumplirlas</div>\n" +
    "		\n" +
    "		</div><BR/>\n" +
    "\n" +
    "	</center>");
                out.println("</body>");
                out.println("</html>");
            }
        }
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
