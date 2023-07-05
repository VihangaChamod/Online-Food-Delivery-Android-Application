package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SaveOrders", urlPatterns = {"/SaveOrders"})
public class SaveOrders extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String methord=request.getParameter("methord");
            String tempid=request.getParameter("tempid");
            String total_price=request.getParameter("total_price");
            String addres=request.getParameter("addres");
            String mobile=request.getParameter("mobile");
            String status=request.getParameter("status");
            
            
            System.out.println(request.getParameter("methord"));
            System.out.println(request.getParameter("tempid"));
            System.out.println(request.getParameter("total_price"));
            System.out.println(request.getParameter("addres"));
            System.out.println(request.getParameter("mobile"));
            System.out.println(request.getParameter("status"));
          
             ResultSet idselection =  DBMS_Connection.DBMS.SE("SELECT * FROM `guest_details` WHERE `guest_id`='"+tempid+"'");
             
            try {
                if (idselection.next()) {
                    
                    ResultSet rs =  DBMS_Connection.DBMS.SE("SELECT * FROM `cart` WHERE `guest_details_id`='"+idselection.getString("id")+"'");
            
            try {
                while (rs.next()) {
                    
                    System.out.println(total_price);
                    DBMS_Connection.DBMS.i("INSERT INTO `orders` (`temp_id`,`datetime`,`cart_id`,`delivary_methord`,`price`,`mobile`,`delivery_addres`,`status`)VALUES('"+tempid+"','"+new Date()+"','"+rs.getString("id")+"','"+methord+"','"+total_price+"','"+mobile+"','"+addres+"','"+status+"')");
                    

                }
                out.print("yes");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex);
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
