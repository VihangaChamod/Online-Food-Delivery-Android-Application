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



@WebServlet(name = "showPendingOrders", urlPatterns = {"/showPendingOrders"})
public class showPendingOrders extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String driverId=request.getParameter("myId");
            
         
            
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
            ResultSet rs = DBMS_Connection.DBMS.SE("SELECT * FROM `rider_confirms` WHERE `rider_informations_rider_id`='"+driverId+"'");
            
            try {
                while (rs.next()) {  
                    
          ResultSet rs1 = DBMS_Connection.DBMS.SE("SELECT * FROM `orders` WHERE `id`='"+rs.getString("orders_id")+"' AND `status`='rider_Accepted' GROUP BY `temp_id`");
          
                    while (rs1.next()) {                        
                    
                         jo=new JsonObject();
                     
                     jo.addProperty("temp_id",  rs1.getString("temp_id"));  
                     jo.addProperty("datetime",   rs1.getString("datetime"));
                     
                     if (rs1.getString("delivary_methord").equals("0")) {
                         
                      jo.addProperty("delivary_methord","Cash On Delivary!");
                        
                    }
                     
                     jo.addProperty("price", rs1.getString("price"));      
                     jo.addProperty("mobile", rs1.getString("mobile"));      
                     jo.addProperty("delivery_addres", rs1.getString("delivery_addres"));      
                 

                     js.add(jo); 
                        
                        

                    }
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
