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


@WebServlet(name = "drivers", urlPatterns = {"/drivers"})
public class drivers extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
             JsonArray js=new JsonArray();
            JsonObject jo=null;
            
            ResultSet rs= DBMS_Connection.DBMS.SE("SELECT * FROM `rider_informations`");
     
            try {
                while (rs.next()) {
                    
                   
                        
                         jo=new JsonObject();
                    
                 jo.addProperty("dnic", rs.getString("rider_nic"));
                 jo.addProperty("dname", rs.getString("rider_name"));
                 jo.addProperty("demail", rs.getString("rider_email"));
                 jo.addProperty("dmobile", rs.getString("rider_mobile"));
                
   
                     js.add(jo);
                    
                    
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
