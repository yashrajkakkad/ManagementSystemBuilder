import java.util.ArrayList;
import java.sql.SQLException;

public class DockyCRUD {
   
   public static boolean addDocky(Docky docky) throws SQLException {
      String insertQuery = "INSERT INTO tbl_docky (tiktok ) VALUES ('" + docky.getTiktok() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public static boolean deleteDocky(int value) throws SQLException {
      String deleteQuery = "DELETE FROM tbl_docky WHERE tiktok = " + value;
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public static boolean updateDocky(Docky docky, int tiktok) throws SQLException {
      String updateQuery = "UPDATE tbl_docky SET tiktok = " + docky.getTiktok() + " WHERE tiktok = " + tiktok;
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   public static Docky viewDocky(int value) throws SQLException {
      String viewQuery = "SELECT * FROM tbl_docky WHERE tiktok = " + value;
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Docky( DatabaseUtil.rs.getInt("tiktok")  );
      }
      return null;
   }
   
   
}
