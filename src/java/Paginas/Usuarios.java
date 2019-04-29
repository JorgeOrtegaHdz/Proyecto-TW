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
public class Usuarios extends HttpServlet {

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
            out.println("<HEAD>");
            out.println("<TITLE>Usuarios</TITLE>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#6699CC\">");
            out.println("<center>");
            out.println("PROFESORES <BR/>");
            out.println("<table border=\"3\" style=\"width:70%\">");
            out.println("<tr>");
            out.println("<td><font color=\"#FFFFFF\">Usuario</font></td>");
            out.println("</tr>");
            {
                SimpleQuery consulta = new SimpleQuery(request.getRealPath("/")  + "profesores.xml");
                HashMap<String, String>[] profesores = consulta.select();
                for(HashMap<String, String> profesor : profesores)
                {
                    out.println("<tr>");
                    out.println("<td><font color=\"#FFFFFF\">" + profesor.get("idUsuario") + "</font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Modificar?tipo=profesor&identificador=" + profesor.get("identificador") + "\">Cambiar nombre</a></font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Borrar?tipo=profesor&identificador=" + profesor.get("identificador") + "\">Borrar</a></font></td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("<BR/><BR/><BR/>");
            out.println("ALUMNOS <BR/>");
            out.println("<table border=\"3\" style=\"width:70%\">");
            out.println("<tr>");
            out.println("<td><font color=\"#FFFFFF\">Usuario</font></td>");
            out.println("</tr>");
            {
                SimpleQuery consulta = new SimpleQuery(request.getRealPath("/")  + "alumnos.xml");
                HashMap<String, String>[] alumnos = consulta.select();
                for(HashMap<String, String> alumno : alumnos)
                {
                    out.println("<tr>");
                    out.println("<td><font color=\"#FFFFFF\">" + alumno.get("idUsuario") + "</font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Modificar?tipo=alumno&identificador=" + alumno.get("identificador") + "\">Cambiar nombre</a></font></td>");
                    out.println("<td><font color=\"#FFFFFF\"><a href=\"Borrar?tipo=alumno&identificador=" + alumno.get("identificador") + "\">Borrar</a></font></td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("</CENTER>");
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
