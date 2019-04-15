import java.util.ArrayList;
import java.sql.SQLException;

public class MoodCRUD {
   
   public static boolean addMood(Mood mood) throws SQLException {
      String insertQuery = "INSERT INTO tbl_mood (gungun ) VALUES ('" + mood.getGungun() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteMood(int value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_mood WHERE gungun = " + value;
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateMood(Mood mood, int gungun) throws SQLException {
      String updateQuery = "UPDATE tbl_mood SET gungun = " + mood.getGungun() + " WHERE gungun = " + gungun;
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Mood viewMood(int value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_mood WHERE gungun = " + value;
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Mood( DatabaseUtil.rs.getInt("gungun")  );
      }
      return null;
   }
   
   
}
