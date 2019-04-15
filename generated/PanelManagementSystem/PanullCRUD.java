import java.util.ArrayList;
import java.sql.SQLException;

public class PanullCRUD {
   
   public static boolean addPanull(Panull panull) throws SQLException {
      String insertQuery = "INSERT INTO tbl_panull (Name ) VALUES ('" + panull.getName() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deletePanull(String value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_panull WHERE Name = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updatePanull(Panull panull, String Name) throws SQLException {
      String updateQuery = "UPDATE tbl_panull SET Name = '" + panull.getName() + "' WHERE Name ='" + Name + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Panull viewPanull(String value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_panull WHERE Name = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Panull( DatabaseUtil.rs.getString("Name")  );
      }
      return null;
   }
   
   
}
