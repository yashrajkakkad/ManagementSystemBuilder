import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AddStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("StudentID");
   private JLabel nameLabel = new JLabel("Name");
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
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = addStudent(new Student(Integer.parseInt(studentIDTextField.getText()), nameTextField.getText());
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"Student added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
