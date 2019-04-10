import java.util.ArrayList;
import java.sql.SQLException;

public class StudentCRUD {
   
   public boolean addStudent(Student student) throws SQLException {
      String insertQuery = "INSERT INTO tbl_student (studentID, name ) VALUES ('" + student.getStudentID() + "', '" + student.getName() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public boolean deleteStudent(String dataField, String value) throws SQLException {
      StringBuilder conditionString = new StringBuilder();
      conditionString.append(dataField).append("=");
      if(dataField.equals("int") || dataField.equals("double")) {
         conditionString.append(value);
      }
      else {
         conditionString.append("'" + value + "'");
      }
      String deleteQuery = "DELETE FROM tbl_student WHERE " + conditionString.toString();
      int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);
      if(i!=1) {
         return false;
      }
      return true;
   }
   
   public boolean updateStudent(Student student, int studentID) throws SQLException {
      String updateQuery = "UPDATE tbl_student SET studentID = " + student.getStudentID() + ", name = ' " + student.getName() + " ' SET studentID = " + studentID + ";"";
      int i = DatabaseUtil.stmt.executeUpdate(updateQuery);
      if(i==1) {
         return true;
      }
      return false;
   }
   
   public Student viewStudent(int value) throws SQLException {
      String viewQuery = SELECT * FROM tbl_student WHERE studentID = " + value;
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      while(DatabaseUtil.rs.next()) {
         return new Student( DatabaseUtil.rs.getInt("studentID"), DatabaseUtil.rs.getString("name")  );
      }
   }
   
   
}
