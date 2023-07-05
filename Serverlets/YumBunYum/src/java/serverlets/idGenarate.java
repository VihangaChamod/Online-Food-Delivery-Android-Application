/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "idGenarate", urlPatterns = {"/idGenarate"})
public class idGenarate extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
                       
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
            int id=1;
            
            ResultSet rs= DBMS_Connection.DBMS.SE("SELECT guest_id FROM `guest_details` WHERE id=(SELECT MAX(id) FROM `guest_details`)");
            
            try {
                while (rs.next()) {
          
                 id= rs.getInt("guest_id")+1;
                    
                    DBMS_Connection.DBMS.i("INSERT INTO `guest_details` (`guest_id`)VALUES('"+id+""+"')");
                    System.out.println(id);
                    
                    
                    
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            jo=new JsonObject();
            jo.addProperty("guid", id+"");
            js.add(jo);
            
      String jsData=js.toString();
      out.print(jsData); 
            
            
            
            
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
