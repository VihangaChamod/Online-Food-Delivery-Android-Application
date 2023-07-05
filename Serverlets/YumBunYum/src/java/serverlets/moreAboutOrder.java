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


@WebServlet(name = "moreAboutOrder", urlPatterns = {"/moreAboutOrder"})
public class moreAboutOrder extends HttpServlet {


    private String c_id_m;
    private String customerId;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
          customerId=request.getParameter("orderId");
       
          
          JsonArray js=new JsonArray();
          JsonObject jo=null;
         
 
               ResultSet cart = DBMS_Connection.DBMS.SE("SELECT * FROM `cart` WHERE `customer_id`='"+customerId+"'");

            try {
                while (cart.next()) {
                    
                    jo=new JsonObject();
       
                   
                    
                   ResultSet orders = DBMS_Connection.DBMS.SE("SELECT * FROM `orders` WHERE `cart_id`='"+ cart.getString("id")+"'");  
                   
                    while (orders.next()) {                        
                        
                        
                       
                        jo.addProperty("date",  orders.getString("datetime"));     
                        jo.addProperty("price",   orders.getString("price"));     

                    }
  
                    
       
                    ResultSet catalog = DBMS_Connection.DBMS.SE("SELECT * FROM `catalog` WHERE `id`='"+cart.getString("catalog_id")+"'");     
                    
                     while (catalog.next()) {                            
               
                        jo.addProperty("food_name",  catalog.getString("food_name"));     
                        jo.addProperty("img",  catalog.getString("img_url"));     

                        }
                     
                        jo.addProperty("qty",   cart.getString("qty"));     
                            
                      
                     
                
                    js.add(jo);      
                    
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
