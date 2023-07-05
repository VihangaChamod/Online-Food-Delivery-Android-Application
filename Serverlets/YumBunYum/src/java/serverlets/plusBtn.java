/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "plusBtn", urlPatterns = {"/plusBtn"})
public class plusBtn extends HttpServlet {

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           
            String qty=request.getParameter("qty");
            String fid=request.getParameter("fid");
            String guid=request.getParameter("guid");
            
              String cuid=request.getParameter("cuid");
            
            
            String id="";
           
            
            try {
                
                ResultSet rs= DBMS_Connection.DBMS.SE("select `id` from `guest_details` where `guest_id`='"+guid+"'");
                if (rs.next()) {
                    
                  id=rs.getString("id");
                    
                }
                
            } catch (Exception e) {
            }
            
            
            String n="1";
             
            try {
                
              ResultSet rs2=  DBMS_Connection.DBMS.SE("select * from `cart` where `guest_details_id`='"+id+"' and `catalog_id`='"+fid+"' and `set_id`='"+n+"' and `customer_id`='"+cuid+"'");
                
                if (rs2.next()) {
                    
                  
                  String cr_id=rs2.getString("id");
                    
                  String sqty=  rs2.getString("qty");
                  int i=Integer.parseInt(sqty);
                  i=i+1;
                  
                    
                    DBMS_Connection.DBMS.i("update `cart` set `qty`='"+i+""+"' where `id`='"+cr_id+"'");
                    
                }else{
                
                    try {
              
                
                
                    DBMS_Connection.DBMS.i("insert into `cart` (`catalog_id`,`qty`,`guest_details_id`,`set_id`,`customer_id`)values('"+fid+"','"+qty+"','"+id+"','"+n+"','"+cuid+"')");
                    out.print("yes");
            } catch (Exception e) {
                System.out.println(e);
                
            }
                
                
                
                
                
                }
                
                
                
                
            } catch (Exception e) {
                System.out.println(e);
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
