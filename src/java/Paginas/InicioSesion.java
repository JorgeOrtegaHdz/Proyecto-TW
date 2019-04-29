/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class InicioSesion extends HttpServlet {

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
        boolean ban=false;
        HttpSession session=request.getSession();
        String ruta=request.getRealPath("/");
        response.setContentType("text/html;charset=UTF-8");
        SimpleQuery manejador=new SimpleQuery(ruta+"alumnos.xml");
        SimpleQuery manejadorA=new SimpleQuery(ruta+"administrador.xml");
        SimpleQuery manejadorP=new SimpleQuery(ruta+"profesores.xml");
        String nomInicio=request.getParameter("nomInicio");
        String pass=request.getParameter("pass");
        HashMap[] nomIni=manejador.select("idUsuario", nomInicio);
        HashMap[] pas=manejador.select("contraseña", pass);
        if(nomIni!=null && pas!=null){
            ban=true;
            session.setAttribute("identificadorUsuario", (String)nomIni[0].get("identificador"));
            session.setAttribute("tipoUsuario", "Alumno");
        }else{
            nomIni=manejadorA.select("idUsuario", nomInicio);
            pas=manejadorA.select("contraseña", pass);
            if(nomIni!=null && pas!=null){
                ban=true;
                session.setAttribute("identificadorUsuario", nomIni[0].get("identificador"));
                session.setAttribute("tipoUsuario", "Administrador");
            }else{
                nomIni=manejadorP.select("idUsuario", nomInicio);
                pas=manejadorP.select("contraseña", pass);
                if(nomIni!=null && pas!=null){
                    ban=true;
                    session.setAttribute("identificadorUsuario", nomIni[0].get("identificador"));
                    session.setAttribute("tipoUsuario", "Profesor");
                }
            }
        }
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel = \"stylesheet\" type =\"text/css\" href = \"estiloIni.css\">");
        out.println("</head>");
        out.println("<body>");
        if(ban==true){
            response.sendRedirect("Index");
        }else{
            out.println("<form action=\"ServletInicioSecion\" method=\"post\" class=\"ini\" id=\"col\">\n" +
"            <div class=\"bor\">\n" +
"                Nombre de usuario<input name=\"nomInicio\" type=\"text\"/>\n" +
"                <br/>\n" +
"                Contraseña<input name=\"pass\" type=\"password\"/>\n" +
"                <br/>\n" +
"                <input type=\"submit\" value=\"Iniciar\"/>\n" +
"                <a href='Registro'>Registrarse</a>\n" +
"               <h5 style=\"color:red;\">Nombre o contraseña incorrectos</h5>\n" +
"            </div>\n" +                  
"        </form>");
        }
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
