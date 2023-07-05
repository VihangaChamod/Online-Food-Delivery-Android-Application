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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "cartViewSelect", urlPatterns = {"/cartViewSelect"})
public class cartViewSelect extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            
            
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
            
            
            
            
            
                JsonArray js=new JsonArray();
                 JsonObject jo=null;
            
            
            
            try {
                
                String n="1";
               
                   ResultSet rs1= DBMS_Connection.DBMS.SE("select * from `cart` where `guest_details_id`='"+id+"' and `customer_id`='"+cuid+"' ");
                   
                   
                   double q;
                   double p;
                   
                    while (rs1.next()) {
                        
                        String t=rs1.getString("qty");
                         q=Double.parseDouble(t);
                        
                        jo=new JsonObject();
                        jo.addProperty("qty", rs1.getString("qty"));
                        jo.addProperty("id", rs1.getString("id"));

                          
                       
                      ResultSet rs_catalog = DBMS_Connection.DBMS.SE("select * from `catalog` where `id`='"+rs1.getString("catalog_id")+"'");
                      
                        if (rs_catalog.next()) {
                            
                       jo.addProperty("img", rs_catalog.getString("img_url"));
                       jo.addProperty("fname", rs_catalog.getString("food_name"));
                       
                       
                       String pr=rs_catalog.getString("food_price");
                       p=Double.parseDouble(pr);
                       
                      double total= p*q;
                       
                       jo.addProperty("fprice",total+"");

                            js.add(jo);
                            

                            
                        }

                        
                        
                       
                    
                }
                
                 
      String jsData=js.toString();
      out.print(jsData);
                
                 
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
