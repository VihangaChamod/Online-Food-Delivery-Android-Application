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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "moreQw", urlPatterns = {"/moreQw"})
public class moreQw extends HttpServlet {

    private String orderId;

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         orderId=request.getParameter("orderId");
       
        
          
          JsonArray js=new JsonArray();
          JsonObject jo=null;
           JsonObject jo1=null;
 
            ResultSet orders = DBMS_Connection.DBMS.SE("SELECT * FROM `orders` WHERE `temp_id`='"+orderId+"'");
            
            try {
                while (orders.next()) {
                    
                    
                    ResultSet cart = DBMS_Connection.DBMS.SE("SELECT * FROM `cart` WHERE `id`='"+orders.getString("cart_id")+"'");
                    
                    while (cart.next()) {
             
                   
        
                        ResultSet customized_select = DBMS_Connection.DBMS.SE("SELECT * FROM `customized_select` WHERE `set_id`='"+cart.getString("set_id")+"'");
                        
                        while (customized_select.next()) {

                                  jo=new JsonObject();
                 
                                jo.addProperty("answer",  customized_select.getString("answer"));     

                      
                            
                            

                   
                     ResultSet customized = DBMS_Connection.DBMS.SE("SELECT * FROM `customized` WHERE `id`='"+customized_select.getString("customized_id")+"'");
    
                            while (customized.next()) {                                
                      
                                   
                 
                                jo.addProperty("qw",  customized.getString("qw"));     
                                
                            
                            js.add(jo);

     
                            }
    
    
    
                            
                        }
                        
                         }
                  
                }
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    
         String jsData=js.toString();
            out.print(jsData);
            System.out.println(jsData);             
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
