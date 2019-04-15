import java.util.ArrayList;
import java.sql.SQLException;

public class ChocoNuttyCRUD {
   
   public static boolean addChocoNutty(ChocoNutty choconutty) throws SQLException {
      String insertQuery = "INSERT INTO tbl_choconutty (Taste, Mood ) VALUES ('" + choconutty.getTaste() + "', '" + choconutty.getMood() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteChocoNutty(String value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_choconutty WHERE Taste = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateChocoNutty(ChocoNutty choconutty, String Taste) throws SQLException {
      String updateQuery = "UPDATE tbl_choconutty SET Taste = '" + choconutty.getTaste() + "', Mood = '" + choconutty.getMood() + "' WHERE Taste ='" + Taste + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static ChocoNutty viewChocoNutty(String value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_choconutty WHERE Taste = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new ChocoNutty( DatabaseUtil.rs.getString("Taste"), DatabaseUtil.rs.getString("Mood")  );
      }
      return null;
   }
   
   
}
