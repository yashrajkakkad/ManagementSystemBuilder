import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class DeleteChocoNuttyPanel extends JPanel {
   
   private JLabel TasteLabel = new JLabel("Taste");
   private JLabel MoodLabel = new JLabel("Mood");
   private JTextField TasteViewTextField = new JTextField(10);
   private JLabel MoodViewLabel = new JLabel();
   JButton DeleteButton = new JButton("Delete ChocoNutty");
   JButton SearchButton = new JButton("Search ChocoNutty");
   JPanel subPanel = new JPanel();
   public DeleteChocoNuttyPanel() throws SQLException{
      setLayout(new BorderLayout());
      add(new JLabel("Delete ChocoNutty"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(TasteLabel);
      subPanel.add(TasteViewTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      topPanel.add(MoodLabel);
      topPanel.add(MoodViewLabel);
      bottomPanel.add(DeleteButton);
      DeleteButton.setMaximumSize(DeleteButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
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
      DeleteButton.addActionListener((e) -> {
         boolean isDeleted = false;
         try {
            isDeleted = ChocoNuttyCRUD.deleteChocoNutty(TasteViewTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isDeleted) {
            JOptionPane.showMessageDialog(this,"ChocoNutty deleted successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      }
   }
