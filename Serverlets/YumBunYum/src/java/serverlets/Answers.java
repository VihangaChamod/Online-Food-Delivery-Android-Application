
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


@WebServlet(name = "Answers", urlPatterns = {"/Answers"})
public class Answers extends HttpServlet {

    private String guest_details_id;
    private String cart_id;
    private String customerId;

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        customerId=request.getParameter("id");
        System.out.println(customerId);
          
      
           
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
        try (PrintWriter out = response.getWriter()) {
           


            
            
       
            

   ResultSet cart = DBMS_Connection.DBMS.SE("SELECT `set_id` FROM `cart` WHERE `customer_id`='"+customerId+"'");   

           
          
            try {
                while (cart.next()) {
                    
                  
                    
                    cart_id=cart.getString("set_id");
                    
       
                         
           ResultSet customized_select = DBMS_Connection.DBMS.SE("SELECT * FROM `customized_select` WHERE `set_id`='"+cart.getString("set_id")+"'");
            

                    while (customized_select.next()) {                        
                        
                        
                             
                        jo=new JsonObject();
                         jo.addProperty("ans",customized_select.getString("answer"));
                         js.add(jo);  
  
                    
                         
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
