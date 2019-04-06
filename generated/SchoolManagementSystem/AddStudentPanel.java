import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AddStudentPanel extends JPanel {
   
   public AddStudentPanel() {
      setLayout(new GridLayout(2,2,10,10));
      JLabel studentIDLabel = new JLabel("studentID");
      JLabel nameLabel = new JLabel("name");
      JTextField studentIDTextField = new JTextField();
      JTextField nameTextField = new JTextField();
      add(studentIDLabel);
      add(studentIDTextField);
      add(nameLabel);
      add(nameTextField);
   }
}
