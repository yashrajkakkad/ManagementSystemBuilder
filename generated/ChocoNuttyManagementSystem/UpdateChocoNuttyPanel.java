import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateChocoNuttyPanel extends JPanel {
   
   private JLabel TasteLabel = new JLabel("Taste");
   private JLabel MoodLabel = new JLabel("Mood");
   private JTextField TasteTextField = new JTextField(20);
   private JTextField MoodTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search ChocoNutty");
   JButton UpdateButton = new JButton("Update ChocoNutty");
   JPanel subPanel = new JPanel();
   public UpdateChocoNuttyPanel() throws SQLException{
      setLayout(new BorderLayout());
      TasteTextField.setMaximumSize(TasteTextField.getPreferredSize());
      MoodTextField.setMaximumSize(MoodTextField.getPreferredSize());
      add(new JLabel("Update ChocoNutty"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(TasteLabel);
      subPanel.add(TasteTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      topPanel.add(MoodLabel);
      topPanel.add(MoodTextField);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         ChocoNutty retrieveChocoNutty = null;
         try {
            retrieveChocoNutty = ChocoNuttyCRUD.viewChocoNutty(TasteTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveChocoNutty==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            TasteTextField.setText("" + retrieveChocoNutty.getTaste());
            MoodTextField.setText("" + retrieveChocoNutty.getMood());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = ChocoNuttyCRUD.updateChocoNutty(new ChocoNutty(TasteTextField.getText(), MoodTextField.getText()), TasteTextField.getText());
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"ChocoNutty Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
