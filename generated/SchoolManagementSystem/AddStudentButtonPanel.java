import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AddStudentButtonPanel extends JPanel {
   
   public AddStudentButtonPanel {
      JButton addButton = new JButton("Add Student");
      addButton.setOnAction((e) -> {
         String insertQuery = "INSERT INTO tbl_student VALUES (studentID, name );";
         int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
         if(i==1) {
            JOptionPane.showMessageDialog(this,"Student added successfully!");
         } else if {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
   }
}
