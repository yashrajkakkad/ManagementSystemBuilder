import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class UpdateStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("StudentID");
   private JLabel nameLabel = new JLabel("Name");
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
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      topPanel.add(nameLabel);
      topPanel.add(nameTextField);
      bottomPanel.add(updateButton);
      updateButton.setMaximumSize(updateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         JTextField studentIDTextField.setText(Student.get(StudentID));
         JTextField nameTextField.setText(Student.get(Name));
      });
      updateButton.addActionListener((e) -> {
         boolean isUpdateded = updateStudent("" + studentIDTextField.getText() + ", " + nameTextField.getText()+","+studentID);
         if(isUpdateded) {
            JOptionPane.showMessageDialog(this,"Student Updated successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
