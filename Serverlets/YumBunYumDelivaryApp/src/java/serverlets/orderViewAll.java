package serverlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "orderViewAll", urlPatterns = {"/orderViewAll"})
public class orderViewAll extends HttpServlet {

    private String status="procesing";

    
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            
            
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
            ResultSet rs=DBMS_Connection.DBMS.SE("SELECT * FROM `orders` WHERE `status`='"+status+"' GROUP BY`temp_id` ");
            
        

            try {
                while (rs.next()) {
                     
                   
                     jo=new JsonObject();
                     
                     jo.addProperty("temp_id",  rs.getString("temp_id"));  
                     jo.addProperty("datetime",   rs.getString("datetime"));
                     
                     if (rs.getString("delivary_methord").equals("0")) {
                         
                      jo.addProperty("delivary_methord","Cash On Delivary!");
                        
                    }
                     
                     jo.addProperty("price", rs.getString("price"));      
                     jo.addProperty("mobile", rs.getString("mobile"));      
                     jo.addProperty("delivery_addres", rs.getString("delivery_addres"));      
                 

                     js.add(jo);
                
                }
            } catch (SQLException ex) {
                System.out.println(ex);
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
