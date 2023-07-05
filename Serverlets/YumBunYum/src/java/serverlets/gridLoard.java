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




@WebServlet(name = "gridLoard", urlPatterns = {"/gridLoard"})
public class gridLoard extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String chek=request.getParameter("gridload");
            
            JsonArray js=new JsonArray();
            JsonObject jo=null;
              
           
            if (chek.equals("gridload")) {
               
           
                
                  ResultSet rs = DBMS_Connection.DBMS.SE("select * from `catalog` where `isActive`='1'");
                
                try {
              
                    while (rs.next()) {
                        
                     
                        jo=new JsonObject();
                        
                        if (rs.getString("id").equals("1")) {
                            
                        }else{
                    
                     jo.addProperty("fid", rs.getString("id").toString());
                     jo.addProperty("furl", rs.getString("img_url").toString());
                     jo.addProperty("fname", rs.getString("food_name").toString());
                     jo.addProperty("fprice", rs.getString("food_price").toString());

                     js.add(jo);
                        }
                    }
                    
                  
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                
                
            } else {
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
