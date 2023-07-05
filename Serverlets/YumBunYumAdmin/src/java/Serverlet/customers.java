
package Serverlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "customers", urlPatterns = {"/customers"})
public class customers extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
            ResultSet rs= DBMS_Connection.DBMS.SE("SELECT * FROM `customer`");
     
            try {
                while (rs.next()) {
                    
                    if (!rs.getString("customer_name").equals("system_default")) {
                        
                         jo=new JsonObject();
                    
                 jo.addProperty("cname", rs.getString("customer_name"));
                 jo.addProperty("cemail", rs.getString("customer_email"));
                 jo.addProperty("cmobile", rs.getString("customer_mobile"));
                 jo.addProperty("caddres", rs.getString("customer_addres"));
                 jo.addProperty("cdatetime", rs.getString("datetime"));
   
                     js.add(jo);
                    }
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            String data=js.toString();
            out.print(data);
            System.out.println(data);
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
