import java.util.ArrayList;
import java.sql.SQLException;

public class StudentCRUD {
   
   public void addStudent(Student student) throws SQLException {
      String insertQuery = "INSERT INTO tbl_student (studentID, name ) VALUES ('" + student.getStudentID() + "', '" + student.getName() + "' );"; 
      int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
      if(i!=1) {
         System.out.println("Error occured in adding Student");
      }
   }
   
   public void deleteStudent(String dataField, String value) throws SQLException {
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
         System.out.println("Error occured in deleting Student");
      }
   }
   
   public void updateStudent(Student student, int studentID) throws SQLException {
      StringBuilder updatedValues = new StringBuilder();
      updatedValues.append("studentID = " + student.getStudentID());
      updatedValues.append("name = " + student.getName());
      String updateQuery = "UPDATE " + updatedValues.toString() + " WHERE studentID = " + studentID;
   }
   
   public ArrayList<Student> viewStudent(String dataField, String value) throws SQLException {
      StringBuilder conditionString = new StringBuilder();
      conditionString.append(dataField).append("=");
      if(dataField.equals("int") || dataField.equals("double")) {
         conditionString.append(value);
      }
      else {
         conditionString.append("'" + value + "'");
      }
      String viewQuery = "SELECT * FROM tbl_student WHERE " + conditionString.toString();
      DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);
      ArrayList<Student> student = new ArrayList<>();
      while(DatabaseUtil.rs.next()) {
         student.add(new Student( DatabaseUtil.rs.getInt("studentID"), DatabaseUtil.rs.getString("name")  ));
      }
      return student;
   }
   
   
}
