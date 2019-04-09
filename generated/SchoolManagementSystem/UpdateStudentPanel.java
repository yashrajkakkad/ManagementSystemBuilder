import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class UpdateStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("studentID");
   private JLabel nameLabel = new JLabel("name");
   private JTextField studentIDTextField = new JTextField();
   private JTextField nameTextField = new JTextField();
   private JButton btSearch = new JButton("btSearch");

   JPanel subPanel = new JPanel();

   public UpdateStudentPanel() {
      setLayout(new GridLayout(2,2,10,10));
      add(studentIDLabel);
      subPanel.add(studentIDTextField);
      subPanel.add(btSearch);
      add(subPanel);
      add(nameLabel);
      add(nameTextField);
      btSearch.setOnAction((e) -> {
         if(studentIDTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Please enter a valid value to search!");
         } else {
            StringBuilder selectQuery = "SELECT * FROM tbl_student WHERE studentID = " + studentIDTextField.getText();
            DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(selectQuery);
            studentIDTextField.setText(rs.getInt("studentID") + "");
            nameTextField.setText(rs.getString("name") + "");
         }
      });
   }
}
