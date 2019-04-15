import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class MainFrame extends JFrame{
   
   public MainFrame() throws SQLException{
      setLayout(new BorderLayout());
      setTitle("RidhamManagementSystem");
      JLabel titleLabel = new JLabel();
      titleLabel.setText("RidhamManagementSystem");
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
      JLabel RhytmLabel = new JLabel("Rhytm");
      homePanel.add(RhytmLabel);
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      homePanel.add(new JLabel());
      centralPanel.add(new AddRhytmPanel(), "AddRhytm");
      centralPanel.add(new UpdateRhytmPanel(), "UpdateRhytm");
      centralPanel.add(new UpdateAlgoPanel(), "UpdateAlgo");
      centralPanel.add(new ViewAlgoPanel(), "ViewAlgo");
      centralPanel.add(new ViewMoodPanel(), "ViewMood");
      JButton addRhytmButton = new JButton("Add");
      homePanel.add(addRhytmButton);
      addRhytmButton.addActionListener((e) -> {
         cl.show(centralPanel,"AddRhytm");
      });
      JButton updateRhytmButton = new JButton("Update");
      homePanel.add(updateRhytmButton);
      updateRhytmButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateRhytm");
      });
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JLabel AlgoLabel = new JLabel("Algo");
      homePanel.add(AlgoLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton updateAlgoButton = new JButton("Update");
      homePanel.add(updateAlgoButton);
      updateAlgoButton.addActionListener((e) -> {
         cl.show(centralPanel,"UpdateAlgo");
      });
      JButton viewAlgoButton = new JButton("View");
      homePanel.add(viewAlgoButton);
      viewAlgoButton.addActionListener((e) -> {
         cl.show(centralPanel,"ViewAlgo");
      });
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JLabel MoodLabel = new JLabel("Mood");
      homePanel.add(MoodLabel);
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      homePanel.add(new JLabel(""));
      JButton viewMoodButton = new JButton("View");
      homePanel.add(viewMoodButton);
      viewMoodButton.addActionListener((e) -> {
         cl.show(centralPanel,"ViewMood");
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
