import java.util.ArrayList;
import java.sql.SQLException;

public class NullpaCRUD {
   
   public static boolean addNullpa(Nullpa nullpa) throws SQLException {
      String insertQuery = "INSERT INTO tbl_nullpa (NullID ) VALUES ('" + nullpa.getNullID() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteNullpa(int value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_nullpa WHERE NullID = " + value;
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateNullpa(Nullpa nullpa, int NullID) throws SQLException {
      String updateQuery = "UPDATE tbl_nullpa SET NullID = " + nullpa.getNullID() + " WHERE NullID = " + NullID;
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Nullpa viewNullpa(int value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_nullpa WHERE NullID = " + value;
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Nullpa( DatabaseUtil.rs.getInt("NullID")  );
      }
      return null;
   }
   
   
}
