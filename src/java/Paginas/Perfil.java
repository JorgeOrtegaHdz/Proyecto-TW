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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Erwin
 */
public class Perfil extends HttpServlet {

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
            String idUsuario = request.getParameter("identificadorUsuario");
            String tipoUsuario = request.getParameter("tipoUsuario");
            SimpleQuery consulta = new SimpleQuery(request.getRealPath("/")  + "alumnos.xml");
            HashMap<String, String> alumno = consulta.select(idUsuario);
            out.println("<!DOCTYPE html>");
            out.println("<HEAD>");
            out.println("<TITLE>Perfil</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../estilo.css\">");
            out.println("</HEAD>");
            out.println("");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("<div style=\"width:700px; background:#CCCCCC;border:#000000 3px solid;text-align:left;margin:10px;padding-left:50px; padding-top:25px;\">");
            // Get a set of the entries
            Set set = alumno.entrySet();
            // Get an iterator
            Iterator i = set.iterator();
            // Display elements
            while(i.hasNext()) 
            {
                Map.Entry me = (Map.Entry)i.next();
                if(me.getKey().equals("contraseña") || me.getKey().equals("identificador"))
                    continue;
                out.println(me.getKey() + " \"" + me.getValue() + "\"<br/><br/>");
             }
            HttpSession session = request.getSession();
            String identificador = (String) session.getAttribute("identificadorUsuario");
            String tipo = (String) session.getAttribute("tipoUsuario");
            if(identificador.equals(idUsuario) && tipo.equals(tipoUsuario));
                out.println("<a href=\"Modificar?tipo=" + tipo + "&identificador=" + identificador + "\">Editar</a>");
            out.println("<a href=\"Inicio\">Regresar</a><br/><br/>");
            out.println("</center>");
            out.println("</BODY>");
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
