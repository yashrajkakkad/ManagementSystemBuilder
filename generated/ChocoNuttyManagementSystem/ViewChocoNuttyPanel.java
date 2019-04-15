import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class ViewChocoNuttyPanel extends JPanel {
   
   private JLabel TasteLabel = new JLabel("Taste");
   private JLabel MoodLabel = new JLabel("Mood");
   private JTextField TasteViewTextField = new JTextField(10);
   private JLabel MoodViewLabel = new JLabel();
   JButton ViewButton = new JButton("View ChocoNutty");
   public ViewChocoNuttyPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("View ChocoNutty"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(TasteLabel);
      topPanel.add(TasteViewTextField);
      topPanel.add(MoodLabel);
      topPanel.add(MoodViewLabel);
      bottomPanel.add(ViewButton);
      ViewButton.setMaximumSize(ViewButton.getPreferredSize());
      ViewButton.addActionListener((e) -> {
         ChocoNutty retrieveChocoNutty = null;
         try {
            retrieveChocoNutty = ChocoNuttyCRUD.viewChocoNutty(TasteViewTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveChocoNutty==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            MoodViewLabel.setText("" + retrieveChocoNutty.getMood());
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
