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

/**
 *
 * @author Erwin
 */
public class Modificar extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String identificador = request.getParameter("identificador");
        SimpleQuery consulta = null;
        switch (tipo) 
        {
            case "categoria":
                consulta = new SimpleQuery(request.getRealPath("/")  + "categoria.xml");
                break;
            case "profesor":
                consulta = new SimpleQuery(request.getRealPath("/")  + "profesores.xml");
                break;
            case "alumno":
                consulta = new SimpleQuery(request.getRealPath("/")  + "alumnos.xml");
                break;
        }
        HashMap<String, String> mapa = consulta.select(identificador);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Cambiar información</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("<div style=\"width:700px; background:#CCCCCC;border:#000000 3px solid;text-align:left;margin:10px;padding-left:50px; padding-top:25px;\">");
            out.println("<form method=\"post\" action=\"EjecutarModificacion\">");
            out.println("<input type=\"hidden\" name=\"tipo\" value=\"" + tipo + "\">");
            out.println("<input type=\"hidden\" name=\"identificador\" value=\"" + identificador + "\">");
            for (String atributo : consulta.atributos) 
            {
                if(atributo.equals("contraseña"))
                    out.println(atributo + ": <input type=\"password\" maxlength=\"10\" name=\"" + atributo + "\" id=\"" + atributo + "\" value=\"" + mapa.get(atributo) + "\"/><br/><br/>");
                out.println(atributo + ": <input type=\"text\" name=\"" + atributo + "\" id=\"" + atributo + "\" value=\"" + mapa.get(atributo) + "\"/><br/><br/>");
            }
            out.println("<input type=\"submit\" value=\"Guardar Cambios\"/>");
            out.println("</form>");
            out.println("<a href=\"perfil.html\">Cancelar</a><br/><br/>");
            out.println("</div><BR>");
            out.println("</center>");
            out.println("</BODY>");
            out.println("</HTML>");
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
