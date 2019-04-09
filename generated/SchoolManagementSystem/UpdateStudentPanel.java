import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class UpdateStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("studentID");
   private JLabel nameLabel = new JLabel("name");
   private JTextField studentIDTextField = new JTextField();
   private JTextField nameTextField = new JTextField();
   JButton searchButton = new JButton("Search Student");
   JButton updateButton = new JButton("Update Student");
   JPanel subPanel = new JPanel();
   public UpdateStudentPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("Update Student"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(studentIDLabel);
      subPanel.add(studentIDTextField);
      subPanel.add(searchButton);
      topPanel.add(subPanel);
      topPanel.add(nameLabel);
      topPanel.add(nameTextField);
      bottomPanel.add(updateButton);
      updateButton.setMaximumSize(updateButton.getPreferredSize());
      searchButton.setOnAction((e) -> {
         if(studentIDTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Please enter a valid value to search!");
         } else {
            StringBuilder selectQuery = "SELECT * FROM tbl_student WHERE studentID = " + studentIDTextField.getText();
            DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(selectQuery);
            studentIDTextField.setText(rs.getInt("studentID") + "");
            nameTextField.setText(rs.getString("name") + "");
         }
      });
      updateButton.setOnAction((e) -> {
         int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
         if(i==1) {
            JOptionPane.showMessageDialog(this,"Student updated successfully!");
         } else if {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
