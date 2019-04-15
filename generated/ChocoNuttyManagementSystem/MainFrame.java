import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class MainFrame extends JFrame{
   
   public MainFrame() throws SQLException{
      setLayout(new BorderLayout());
      setTitle("ChocoNuttyManagementSystem");
      JLabel titleLabel = new JLabel();
      titleLabel.setText("ChocoNuttyManagementSystem");
      titleLabel.setFont(new Font("Arial", Font.BOLD,24));
      add(titleLabel, BorderLayout.NORTH);
      setLocationRelativeTo(null);
      pack();
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JPanel centralPanel = new JPanel();
      add(centralPanel,BorderLayout.CENTER);
      CardLayout cl = new CardLayout();
      JPanel homePanel = new JPanel();
      centralPanel.setLayout(cl);
      centralPanel.add(new JScrollPane(homePanel),"homePanel");
      homePanel.setLayout(new GridLayout(0,4,10,10));
      JLabel ChocoNuttyLabel = new JLabel("ChocoNutty");
      homePanel.add(ChocoNuttyLabel);
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      centralPanel.add(new AddChocoNuttyPanel(), "AddChocoNutty");
      centralPanel.add(new UpdateChocoNuttyPanel(), "UpdateChocoNutty");
      centralPanel.add(new DeleteChocoNuttyPanel(), "DeleteChocoNutty");
      centralPanel.add(new ViewChocoNuttyPanel(), "ViewChocoNutty");
      JButton addChocoNuttyButton = new JButton("Add");
      homePanel.add(addChocoNuttyButton);
      addChocoNuttyButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddChocoNutty");
      });
      JButton updateChocoNuttyButton = new JButton("Update");
      homePanel.add(updateChocoNuttyButton);
      updateChocoNuttyButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateChocoNutty");
      });
      JButton deleteChocoNuttyButton = new JButton("Delete");
      homePanel.add(deleteChocoNuttyButton);
      deleteChocoNuttyButton.addActionListener((e) -> {
         cl.show(centralPanel,"DeleteChocoNutty");
      });
      JButton viewChocoNuttyButton = new JButton("View");
      homePanel.add(viewChocoNuttyButton);
      viewChocoNuttyButton.addActionListener((e) -> {
         cl.show(centralPanel,"ViewChocoNutty");
      });
      JPanel bottomPanel = new JPanel();
      add(bottomPanel,BorderLayout.SOUTH);
      bottomPanel.setLayout(new GridLayout(1,3,10,10));
      JButton backToHomebtn = new JButton("Back to Home");
      backToHomebtn.addActionListener(e -> {
         cl.show(centralPanel, "homePanel");
      });
      bottomPanel.add(backToHomebtn);
      centralPanel.add(new AboutPanel(), "aboutUsPanel");
      JButton aboutUsbtn = new JButton("About Us");
      aboutUsbtn.addActionListener(e -> {
         cl.show(centralPanel, "aboutUsPanel");
      });
      bottomPanel.add(aboutUsbtn);
      centralPanel.add(new ContactPanel(), "contactUsPanel");
      JButton contactUsbtn = new JButton("Contact Us");
      contactUsbtn.addActionListener(e -> {
         cl.show(centralPanel, "contactUsPanel");
      });
      bottomPanel.add(contactUsbtn);
   }
   
   public static void main(String[] args) throws SQLException{
      MainFrame mainFrame = new MainFrame();
      mainFrame.setVisible(true);
   }
}
