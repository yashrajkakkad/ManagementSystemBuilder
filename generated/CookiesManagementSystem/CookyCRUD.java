import java.util.ArrayList;
import java.sql.SQLException;

public class CookyCRUD {
   
   public static boolean addCooky(Cooky cooky) throws SQLException {
      String insertQuery = "INSERT INTO tbl_cooky (CockyName ) VALUES ('" + cooky.getCockyName() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteCooky(double value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_cooky WHERE CockyName = " + value;
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateCooky(Cooky cooky, double CockyName) throws SQLException {
      String updateQuery = "UPDATE tbl_cooky SET CockyName = " + cooky.getCockyName() + " WHERE CockyName = " + CockyName;
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Cooky viewCooky(double value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_cooky WHERE CockyName = " + value;
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Cooky( DatabaseUtil.rs.getDouble("CockyName")  );
      }
      return null;
   }
   
   
}
