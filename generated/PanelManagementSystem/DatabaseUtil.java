import java.sql.*;

public class DatabaseUtil {
   
   static String dbURL = "jdbc:mysql://134.209.159.227:3306/db_panelmanagementsystem";
   static Connection con = null;
   static Statement stmt;
   static ResultSet rs;
   
   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(dbURL, "mansys", "sysman$");
         stmt = con.createStatement();
      }
      catch (Exception ex) {
         System.out.println("ex = " + ex);
      }
      
   }
   
   static Statement getStatement() throws SQLException {
      System.out.println("DataUtil stmt = " + stmt);
      return stmt;
   }
   
   protected void finalize() {
      try {
         stmt.close();
         con.close();
      }
      catch (SQLException ex) {
         System.out.println("Exception ex = " + ex);
      }
   }
}
