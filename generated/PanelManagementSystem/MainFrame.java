import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class MainFrame extends JFrame{
   
   public MainFrame() throws SQLException{
      setLayout(new BorderLayout());
      setTitle("PanelManagementSystem");
      JLabel titleLabel = new JLabel();
      titleLabel.setText("PanelManagementSystem");
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
      JLabel PanullLabel = new JLabel("Panull");
      homePanel.add(PanullLabel);
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      centralPanel.add(new AddPanullPanel(), "AddPanull");
      centralPanel.add(new UpdatePanullPanel(), "UpdatePanull");
      centralPanel.add(new UpdateNullpaPanel(), "UpdateNullpa");
      centralPanel.add(new DeleteNullpaPanel(), "DeleteNullpa");
      centralPanel.add(new ViewRandomChikibumPanel(), "ViewRandomChikibum");
      JButton addPanullButton = new JButton("Add");
      homePanel.add(addPanullButton);
      addPanullButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddPanull");
      });
      JButton updatePanullButton = new JButton("Update");
      homePanel.add(updatePanullButton);
      updatePanullButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdatePanull");
      });
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      JLabel NullpaLabel = new JLabel("Nullpa");
      homePanel.add(NullpaLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton updateNullpaButton = new JButton("Update");
      homePanel.add(updateNullpaButton);
      updateNullpaButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateNullpa");
      });
      JButton deleteNullpaButton = new JButton("Delete");
      homePanel.add(deleteNullpaButton);
      deleteNullpaButton.addActionListener((e) -> {
         cl.show(centralPanel,"DeleteNullpa");
      });
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      JLabel RandomChikibumLabel = new JLabel("RandomChikibum");
      homePanel.add(RandomChikibumLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton viewRandomChikibumButton = new JButton("View");
      homePanel.add(viewRandomChikibumButton);
      viewRandomChikibumButton.addActionListener((e) -> {
         cl.show(centralPanel,"ViewRandomChikibum");
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
