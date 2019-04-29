/*
Terminado, Falta probar
Obtiene el identificador de usuario y el tipo de usuario de la sesión HTTP y los emplea para crear 
una publicación pertenenciente al tema que redirigió a esta aplicación, posteriormente regresa a la página anterior
 */
package Paginas;

import XMLQuery.SimpleQuery;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
// <editor-fold defaultstate="collapsed" desc="Página Publicar. Click on the + sign on the left to edit the code.">
public class Publicar extends HttpServlet {

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
        HttpSession session = request.getSession();
        String identificadorUsuario = (String) session.getAttribute("identificadorUsuario");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        String identificadorTema = request.getParameter("identificador");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();             
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");          
        String inActiveDate = format1.format(date);            
        SimpleQuery consultaPublicacion = new SimpleQuery(request.getRealPath("/") + "publicacion.xml");
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("idPublicacion", "" + (consultaPublicacion.getMax() + 1));
        mapa.put("idCreador", identificadorUsuario);
        mapa.put("titulo", titulo);
        mapa.put("contenido", contenido);
        mapa.put("fecha", inActiveDate);
        mapa.put("tipoCreador", tipoUsuario);
        mapa.put("calificacion", "0");
        mapa.put("idTema", identificadorTema);
        Boolean insercionCorrecta = consultaPublicacion.insert(mapa);
        if(insercionCorrecta)
            response.sendRedirect("Discusion?identificador=" + identificadorTema);
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
//</editor-fold>