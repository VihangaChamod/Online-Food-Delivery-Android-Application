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

/**
 *
 * @author DELL
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
       String name = request.getParameter("name");
       String pass = request.getParameter("pass");
       
       
            try {
  
               ResultSet rs = DBMS_Connection.DBMS.SE("select * from `customer` where customer_email='"+name+"' and customer_password='"+pass+"'");

                if (rs.next()) {
                    
                 String email = rs.getString("customer_email");
                 String password = rs.getString("customer_password");
                 String mobile =rs.getString("customer_mobile");
                 String id =rs.getString("id");
                                
                 String cuname =rs.getString("customer_name");
                 String addres =rs.getString("customer_addres");

                 

             
                     jo=new JsonObject();
                    
                     jo.addProperty("email", email);
                     jo.addProperty("pass", password);
                     jo.addProperty("mobile", mobile);
                     jo.addProperty("id", id);
                     jo.addProperty("name", cuname);
                     jo.addProperty("addres", addres);
                     js.add(jo);
                }else{
                
                    jo=new JsonObject();
                    
                     jo.addProperty("email", "null");
                     jo.addProperty("pass", "null");
                     jo.addProperty("mobile", "null");
                     jo.addProperty("id", "null");
                     jo.addProperty("name", "null");
                     jo.addProperty("addres", "null");
                        js.add(jo);
                
                }
               
            } catch (Exception e) {
                System.out.println(e);
            }
       
      
       
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
