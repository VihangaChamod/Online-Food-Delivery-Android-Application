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


@WebServlet(name = "cuzDataSave", urlPatterns = {"/cuzDataSave"})
public class cuzDataSave extends HttpServlet {

    private int set_id;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            
            String a_id=request.getParameter("cuid1");
            String a_awns=request.getParameter("anz1");
            
            String b_id=request.getParameter("cuid2");
            String b_awns=request.getParameter("anz2");
          
            String get_id=request.getParameter("guid");
            String id="";
            
                         ResultSet idselection =  DBMS_Connection.DBMS.SE("SELECT * FROM `guest_details` WHERE `guest_id`='"+get_id+"'");
                         
            try {
                if (idselection.next()) {
                   id=idselection.getString("id");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(cuzDataSave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            String customer_id=request.getParameter("cusId");
                    
            String catalogId=request.getParameter("catalogId");
            
            String qty=request.getParameter("qty");

            try {
                
                
                ResultSet rs= DBMS_Connection.DBMS.SE("SELECT `set_id` FROM `customized_select` WHERE id=(SELECT MAX(id) FROM `customized_select`)");
                
                   while (rs.next()) {
          
                     set_id= rs.getInt("set_id")+1;
   
                }
                
                
                
           
   
            DBMS_Connection.DBMS.i("INSERT INTO `customized_select`(`set_id`,`customized_id`,`date`,`answer`,`guest_details_id`)VALUES('"+set_id+""+"','"+a_id+"','"+new Date()+"','"+a_awns+"','"+id+"')");

            DBMS_Connection.DBMS.i("INSERT INTO `customized_select`(`set_id`,`customized_id`,`date`,`answer`,`guest_details_id`)VALUES('"+set_id+""+"','"+b_id+"','"+new Date()+"','"+b_awns+"','"+id+"')");
        
            
            DBMS_Connection.DBMS.i("insert into `cart` (`catalog_id`,`qty`,`guest_details_id`,`set_id`,`customer_id`)values('"+catalogId+"','"+qty+"','"+id+"','"+set_id+""+"','"+customer_id+"')");
            
            
            out.print("yes");
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
