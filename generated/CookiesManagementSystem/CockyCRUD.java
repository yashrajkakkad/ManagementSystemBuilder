import java.util.ArrayList;
import java.sql.SQLException;

public class CockyCRUD {
   
   public static boolean addCocky(Cocky cocky) throws SQLException {
      String insertQuery = "INSERT INTO tbl_cocky (Takitaki ) VALUES ('" + cocky.getTakitaki() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteCocky(String value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_cocky WHERE Takitaki = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateCocky(Cocky cocky, String Takitaki) throws SQLException {
      String updateQuery = "UPDATE tbl_cocky SET Takitaki = '" + cocky.getTakitaki() + "' WHERE Takitaki ='" + Takitaki + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Cocky viewCocky(String value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_cocky WHERE Takitaki = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Cocky( DatabaseUtil.rs.getString("Takitaki")  );
      }
      return null;
   }
   
   
}
