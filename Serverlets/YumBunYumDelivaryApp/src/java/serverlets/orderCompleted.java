package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "orderCompleted", urlPatterns = {"/orderCompleted"})
public class orderCompleted extends HttpServlet {

  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            String driverId=request.getParameter("dId");
            String tempId=request.getParameter("Tempid");
     
            
       
  
          
          
            
            
            ResultSet rs=DBMS_Connection.DBMS.SE("SELECT * FROM `orders` WHERE `temp_id`='"+tempId+"' AND `status` ='procesing'");

            try {
           
                DBMS_Connection.DBMS.i("UPDATE `orders` SET `status`='Complete' WHERE `temp_id`='"+tempId+"'");

                while (rs.next()) {                    
                    
               try {
                   
             ResultSet rs1= DBMS_Connection.DBMS.SE("SELECT * FROM `rider_confirms` WHERE `orders_id`='"+rs.getString("id")+"' ");

                while (rs1.next()) {
                    
                   
                   DBMS_Connection.DBMS.i("DELETE FROM `rider_confirms` WHERE `rider_confirms_id` = '"+ rs1.getString("rider_confirms_id")+"'");
                    
                      
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
         
  
               DBMS_Connection.DBMS.i("INSERT INTO `rider_confirms` (`orders_id`,`rider_informations_rider_id`)VALUES('"+rs.getString("id")+"','"+driverId+"')");

                                            
              
                    
                    
                }
                    
            out.print("ok"); 
            } catch (Exception e) {
                          
              
                out.print("fail"); 
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
