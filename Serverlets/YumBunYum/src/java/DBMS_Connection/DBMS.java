
package DBMS_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class DBMS {
    private static final String U="jdbc:mysql://localhost:3306/yumbunyum";
    private static final String N="root";
    private static final String P="123";
    private static final String D="com.mysql.jdbc.Driver";
    private static Connection c;
    private DBMS(){}
    public static synchronized Connection getConnection(){
    
        try {
            if (c==null) {
                Class.forName(D);
            c=DriverManager.getConnection(U,N,P);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    
        
        
    }
    
    public static void i(String q){
    
        try {
            getConnection().createStatement().executeUpdate(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
    public static ResultSet SE(String q){
    
        ResultSet rs=null;
        try {
            rs=getConnection().createStatement().executeQuery(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    return rs;
    }
    
}
