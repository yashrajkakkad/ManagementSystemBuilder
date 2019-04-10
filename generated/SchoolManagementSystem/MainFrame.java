import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class MainFrame extends JFrame {
   
   public MainFrame() {
      setLayout(new BorderLayout());
      setTitle("School Management System");
      JLabel titleLabel = new JLabel();
      titleLabel.setText("School Management System");
      titleLabel.setFont(new Font("Arial", Font.BOLD,24));
      add(titleLabel, BorderLayout.NORTH);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JPanel centralPanel = new JPanel();
      CardLayout cl = new CardLayout();
      JPanel homePanel = new JPanel();
      centralPanel.setLayout(cl);
      centralPanel.add(new JScrollPane(homePanel),"homePanel");
      homePanel.setLayout(new GridLayout(0,2,10,10));
      JLabel StudentLabel = new JLabel("Student");
      homePanel.add(StudentLabel);
      homePanel.add(new JLabel());
      centralPanel.add(new AddStudentPanel(), "AddStudent");
      centralPanel.add(new UpdateStudentPanel(), "UpdateStudent");
      centralPanel.add(new DeleteStudentPanel(), "DeleteStudent");
      centralPanel.add(new ViewStudentPanel(), "ViewStudent");
      JButton addStudentButton = new JButton("Add");
      homePanel.add(addButton);
      addStudentButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddStudent");
      });
      JButton updateStudentButton = new JButton("Update");
      homePanel.add(updateButton);
      updateStudentButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateStudent");
      });
      JButton deleteStudentButton = new JButton("Delete");
      homePanel.add(deleteButton);
      deleteStudentButton.addActionListener((e) -> {
         cl.show(centralPanel,"DeleteStudent");
      });
      JButton viewStudentButton = new JButton("View");
      homePanel.add(viewButton);
      viewStudentButton.addActionListener((e) -> {
         cl.show(centralPanel,"ViewStudent");
      });
   }
   
   public static void main(String[] args) {
      MainFrame mainFrame = new MainFrame();
      mainFrame.setVisible(true);
   }
}
