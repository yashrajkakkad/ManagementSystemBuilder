import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AddStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("studentID");
   private JLabel nameLabel = new JLabel("name");
   private JTextField studentIDTextField = new JTextField();
   private JTextField nameTextField = new JTextField();
   JButton addButton = new JButton("Add Student");
   public AddStudentPanel() {
      setLayout(new BorderLayout());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add Student"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(studentIDLabel);
      topPanel.add(studentIDTextField);
      topPanel.add(nameLabel);
      topPanel.add(nameTextField);
      bottomPanel.add(addButton);
      addButton.setMaximumSize(addButton.getPreferredSize());
      addButton.setOnAction((e) -> {
         String insertQuery = "INSERT INTO tbl_student VALUES (studentID, 'name' );";
         int i = DatabaseUtil.stmt.executeUpdate(insertQuery);
         if(i==1) {
            JOptionPane.showMessageDialog(this,"Student added successfully!");
         } else if {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
