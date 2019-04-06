import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AddStudentPanel extends JPanel {
   
   private JLabel studentIDLabel = new JLabel("studentID");
   private JLabel nameLabel = new JLabel("name");
   private JTextField studentIDTextField = new JTextField();
   private JTextField nameTextField = new JTextField();
   public AddStudentPanel() {
      setLayout(new GridLayout(2,2,10,10));
      add(studentIDLabel);
      add(studentIDTextField);
      add(nameLabel);
      add(nameTextField);
   }
}
