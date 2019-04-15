import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class MainFrame extends JFrame{
   
   public MainFrame() throws SQLException{
      setLayout(new BorderLayout());
      setTitle("CookiesManagementSystem");
      JLabel titleLabel = new JLabel();
      titleLabel.setText("CookiesManagementSystem");
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
      JLabel CookyLabel = new JLabel("Cooky");
      homePanel.add(CookyLabel);
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      centralPanel.add(new AddCookyPanel(), "AddCooky");
      centralPanel.add(new UpdateCookyPanel(), "UpdateCooky");
      centralPanel.add(new DeleteCookyPanel(), "DeleteCooky");
      centralPanel.add(new UpdateCockyPanel(), "UpdateCocky");
      centralPanel.add(new DeleteCockyPanel(), "DeleteCocky");
      centralPanel.add(new AddDockyPanel(), "AddDocky");
      JButton addCookyButton = new JButton("Add");
      homePanel.add(addCookyButton);
      addCookyButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddCooky");
      });
      JButton updateCookyButton = new JButton("Update");
      homePanel.add(updateCookyButton);
      updateCookyButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateCooky");
      });
      JButton deleteCookyButton = new JButton("Delete");
      homePanel.add(deleteCookyButton);
      deleteCookyButton.addActionListener((e) -> {
         cl.show(centralPanel,"DeleteCooky");
      });
      homePanel.add(new JLabel(""));
      JLabel CockyLabel = new JLabel("Cocky");
      homePanel.add(CockyLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton updateCockyButton = new JButton("Update");
      homePanel.add(updateCockyButton);
      updateCockyButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateCocky");
      });
      JButton deleteCockyButton = new JButton("Delete");
      homePanel.add(deleteCockyButton);
      deleteCockyButton.addActionListener((e) -> {
         cl.show(centralPanel,"DeleteCocky");
      });
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JLabel DockyLabel = new JLabel("Docky");
      homePanel.add(DockyLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton addDockyButton = new JButton("Add");
      homePanel.add(addDockyButton);
      addDockyButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddDocky");
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
   }
   
   public static void main(String[] args) throws SQLException{
      MainFrame mainFrame = new MainFrame();
      mainFrame.setVisible(true);
   }
}
