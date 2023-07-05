
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

@WebServlet(name = "updateFragment", urlPatterns = {"/updateFragment"})
public class updateFragment extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            
             String get_name=request.getParameter("name");
            String get_mobile=request.getParameter("mobile"); 
            String get_addres=request.getParameter("addres");

            
            System.out.println("email"+email);
                        System.out.println("pass"+pass);
                                    System.out.println("getname"+get_name);
                                                System.out.println("getAddres"+get_addres);
                                                            System.out.println("getmobile"+get_mobile);




             
            JsonArray js=new JsonArray();
            JsonObject jo=null;
            
        
            DBMS_Connection.DBMS.i("UPDATE `customer` SET `customer_name`='"+get_name+"',`customer_mobile`='"+get_mobile+"',`customer_addres`='"+get_addres+"' WHERE `customer_email`='"+email+"' AND `customer_password`='"+pass+"'");

             try {
  
               ResultSet rs = DBMS_Connection.DBMS.SE("SELECT * FROM `customer` WHERE `customer_email`='"+email+"' AND `customer_password`='"+pass+"'");

                if (rs.next()) {
                    
                 String email1 = rs.getString("customer_email");
                 String password = rs.getString("customer_password");
                 String mobile =rs.getString("customer_mobile");
                 String id =rs.getString("id");
                                
                 String cuname =rs.getString("customer_name");
                 String addres =rs.getString("customer_addres");


             
                     jo=new JsonObject();
                    
                     jo.addProperty("email", email1);
                     jo.addProperty("pass", password);
                     jo.addProperty("mobile", mobile);
                     jo.addProperty("id", id);
                     jo.addProperty("name", cuname);
                     jo.addProperty("addres", addres);
                     js.add(jo);
                }else{
                
                    jo=new JsonObject();
                    
                     jo.addProperty("email", "null");
                     jo.addProperty("pass", "null");
                     jo.addProperty("mobile", "null");
                     jo.addProperty("id", "null");
                     jo.addProperty("name", "null");
                     jo.addProperty("addres", "null");
                     js.add(jo);
                
                }
               
            } catch (Exception e) {
                System.out.println(e);
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
