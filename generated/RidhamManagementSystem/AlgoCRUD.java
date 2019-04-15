import java.util.ArrayList;
import java.sql.SQLException;

public class AlgoCRUD {
   
   public static boolean addAlgo(Algo algo) throws SQLException {
      String insertQuery = "INSERT INTO tbl_algo (gogo ) VALUES ('" + algo.getGogo() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteAlgo(char value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_algo WHERE gogo = '" + value + "'";
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateAlgo(Algo algo, char gogo) throws SQLException {
      String updateQuery = "UPDATE tbl_algo SET gogo = '" + algo.getGogo() + "' WHERE gogo ='" + gogo + "'";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Algo viewAlgo(char value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_algo WHERE gogo = '" + value + "'";
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Algo( DatabaseUtil.rs.getString("gogo").charAt(0)  );
      }
      return null;
   }
   
   
}
