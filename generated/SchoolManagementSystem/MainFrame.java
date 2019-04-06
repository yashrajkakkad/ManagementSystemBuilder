import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class MainFrame extends JFrame {
   
   public MainFrame() {
      setLayout(new BorderLayout());
      setTitle("School Management System");
      JLabel titleLabel = new JLabel();
      titleLabel.setText(" School Management System");
      titleLabel.setFont(new Font("Arial", Font.BOLD,24));
      add(titleLabel, BorderLayout.NORTH);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JPanel centralPanel = new JPanel();
      centralPanel.setLayout(new CardLayout());
      add(new JScrollPane(new AddStudentPanel()), BorderLayout.CENTER);
   }
   
   public static void main(String[] args) {
      MainFrame mainFrame = new MainFrame();
      mainFrame.setVisible(true);
   }
}
