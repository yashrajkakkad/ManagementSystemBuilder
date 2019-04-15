import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class ViewMoodPanel extends JPanel {
   
   private JLabel gungunLabel = new JLabel("Gungun");
   private JTextField gungunViewTextField = new JTextField(10);
   JButton ViewButton = new JButton("View Mood");
   public ViewMoodPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("View Mood"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(gungunLabel);
      topPanel.add(gungunViewTextField);
      bottomPanel.add(ViewButton);
      ViewButton.setMaximumSize(ViewButton.getPreferredSize());
      ViewButton.addActionListener((e) -> {
         Mood retrieveMood = null;
         try {
            retrieveMood = MoodCRUD.viewMood(Integer.parseInt(gungunViewTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveMood==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
