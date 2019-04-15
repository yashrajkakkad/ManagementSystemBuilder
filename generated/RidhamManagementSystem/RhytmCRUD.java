import java.util.ArrayList;
import java.sql.SQLException;

public class RhytmCRUD {
   
   public static boolean addRhytm(Rhytm rhytm) throws SQLException {
      String insertQuery = "INSERT INTO tbl_rhytm (Girlfriend ) VALUES ('" + rhytm.getGirlfriend() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteRhytm(String value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_rhytm WHERE Girlfriend = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateRhytm(Rhytm rhytm, String Girlfriend) throws SQLException {
      String updateQuery = "UPDATE tbl_rhytm SET Girlfriend = '" + rhytm.getGirlfriend() + "' WHERE Girlfriend ='" + Girlfriend + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Rhytm viewRhytm(String value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_rhytm WHERE Girlfriend = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Rhytm( DatabaseUtil.rs.getString("Girlfriend")  );
      }
      return null;
   }
   
   
}
