/*
Terminado, Falta probar
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
// <editor-fold defaultstate="collapsed" desc="Página discusión. Click on the sign on the left to edit the code.">
public class Discusion extends HttpServlet {

    String identificadorTema;
    
    private void colocarDiv(PrintWriter out, HashMap<String, String> objeto, HashMap<String, String> creador, String tipoObjeto, String tipoCreador)
    {
        out.println("<div class=\"Caja_Dis\">");
        out.println(objeto.get("titulo"));
        out.println("<div class=\"Caja_DisCont\">");
        out.println(objeto.get("contenido"));
        out.println("</div>");
        out.println("<font face=\"Arial\" size=\"-1\"><i>Usuario: " + creador.get("idUsuario") + " Fecha: " + objeto.get("fecha") + "</i></font>");
        if(tipoObjeto.equals("Publicacion"))
        {
            out.println("<form method=\"get\" action=\"Puntuar\">");
            out.println("<input type=\"hidden\" name=\"identificadorTema\" value=\"" + identificadorTema + "\">");
            out.println("<input type=\"hidden\" name=\"identificadorObjeto\" value=\"" + objeto.get("idPublicacion") + "\">");
            out.println("<input type=\"submit\" name=\"dislike\" id=\"dislike\" value=\"-1\" style=\"float:right;\"/>");
            out.println("<input type=\"submit\" name=\"like\" id=\"like\" value=\"+1\" style=\"float:right;\"/>");
            out.println("</form>");
        }
        out.println("</div>");
        out.println("<br/>");
    }
    
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
        this.identificadorTema = request.getParameter("identificador");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Discusion</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\" background=\"games/imagenes/page.png\" link=\"#000000\" alink=\"#ffffff\" vlink=\"#000000\">");
            out.println("<center>");
            SimpleQuery consultaAlumno = new SimpleQuery(request.getRealPath("/") + "alumnos.xml");
            SimpleQuery consultaProfesor = new SimpleQuery(request.getRealPath("/") + "profesores.xml");
            SimpleQuery consultaCreador;
            SimpleQuery consultaTemas = new SimpleQuery(request.getRealPath("/") + "tema.xml");
            HashMap<String, String> tema = consultaTemas.select(identificadorTema);
            {
                consultaCreador = tema.get("tipoCreador").equals("Alumno")?consultaAlumno:consultaProfesor;
                HashMap<String, String> creador = consultaCreador.select(tema.get("idCreador"));
                this.colocarDiv(out, tema, creador, "Tema", tema.get("tipoCreador"));
            }
            {
                SimpleQuery consultaPublicaciones = new SimpleQuery(request.getRealPath("/") + "publicacion.xml");
                HashMap<String, String>[] publicaciones = consultaPublicaciones.select("idTema", identificadorTema);
                if(publicaciones != null)
                {
                    for (HashMap<String, String> publicacion : publicaciones) 
                    {
                        consultaCreador = publicacion.get("tipoCreador").equals("Alumno")?consultaAlumno:consultaProfesor;
                        HashMap<String, String> creador = consultaCreador.select(publicacion.get("idCreador"));
                        this.colocarDiv(out, publicacion, creador, "Publicacion", publicacion.get("tipoCreador"));
                    }
                }
            }
            HttpSession session = request.getSession();
            String identificadorUsuario = (String) session.getAttribute("identificadorUsuario");
            if(identificadorUsuario == null || identificadorUsuario.equals(""))
            {
                out.println("<div class=\"Caja_Res\">");
                out.println("<br/><br/><br/>");
                out.println("Inicia sesion o <a href=\"Registro\">registrate</a> para responder a este y otros temas<center>");
                out.println("</div>");
            }
            else
            {
                out.println("<div class=\"Caja_Res\"><form method=\"get\" action=\"Publicar\">");
                out.println("Titulo: <input size=\"110\" type=\"text\" value=\"RE: " + tema.get("titulo") + "\" name=\"titulo\" id=\"titulo\"/> <br><br>");
                out.println("<input type=\"hidden\" name=\"identificador\" value=\"" + identificadorTema + "\">");
                out.println("<div class=\"Caja_ResCont\">");
                out.println("<center>");
                out.println("<textarea rows=\"7\" cols=\"90\" placeholder=\"Escribe tu respuesta\" name=\"contenido\" id=\"contenido\"></textarea> ");
                out.println("</center>");
                out.println("<p align=\"right\"> <input type=\"submit\" value=\"Enviar\"/></p> ");
                out.println("</div>");
                out.println("</form></div>");
            }
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