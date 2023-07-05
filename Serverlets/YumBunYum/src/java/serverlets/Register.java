/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           String name= (String) request.getParameter("name");
              String email= (String) request.getParameter("email");
                 String password= (String) request.getParameter("password");
                    String mobile= (String) request.getParameter("mobile");
                       String addres= (String) request.getParameter("addres");
                       
                       
                    if (!name.equals("")||email.equals("")||password.equals("")||mobile.equals("")||addres.equals("")) {
       
   
                       
              
            try {
              
     DBMS_Connection.DBMS.i("insert into `customer` (`customer_name`,`customer_email`,`customer_password`,`customer_mobile`,`customer_addres`,`datetime`)values('"+name+"','"+email+"','"+password+"','"+mobile+"','"+addres+"','"+new Date()+"')");

     
                ResultSet rs= DBMS_Connection.DBMS.SE("SELECT `id` FROM `customer` where `customer_email`='"+email+"' and `customer_password`='"+password+"' ");
     
                if (rs.next()) {
                      out.print(rs.getString("id"));
                                      System.out.println(rs.getString("id"));

                }
   
                
           
            
            } catch (Exception e) {
               
              System.out.println(e);
            }
  
            
                     
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
