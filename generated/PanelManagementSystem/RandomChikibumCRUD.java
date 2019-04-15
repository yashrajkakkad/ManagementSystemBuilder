import java.util.ArrayList;
import java.sql.SQLException;

public class RandomChikibumCRUD {
   
   public static boolean addRandomChikibum(RandomChikibum randomchikibum) throws SQLException {
      String insertQuery = "INSERT INTO tbl_randomchikibum (Bumbumbole ) VALUES ('" + randomchikibum.getBumbumbole() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteRandomChikibum(char value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_randomchikibum WHERE Bumbumbole = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateRandomChikibum(RandomChikibum randomchikibum, char Bumbumbole) throws SQLException {
      String updateQuery = "UPDATE tbl_randomchikibum SET Bumbumbole = '" + randomchikibum.getBumbumbole() + "' WHERE Bumbumbole ='" + Bumbumbole + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static RandomChikibum viewRandomChikibum(char value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_randomchikibum WHERE Bumbumbole = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new RandomChikibum( DatabaseUtil.rs.getString("Bumbumbole").charAt(0)  );
      }
      return null;
   }
   
   
}
