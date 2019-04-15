import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class AddChocoNuttyPanel extends JPanel {
   
   private JLabel TasteLabel = new JLabel("Taste");
   private JLabel MoodLabel = new JLabel("Mood");
   private JTextField TasteTextField = new JTextField(20);
   private JTextField MoodTextField = new JTextField(20);
   JButton AddButton = new JButton("Add ChocoNutty");
   public AddChocoNuttyPanel() throws SQLException{
      setLayout(new BorderLayout());
      TasteTextField.setMaximumSize(TasteTextField.getPreferredSize());
      MoodTextField.setMaximumSize(MoodTextField.getPreferredSize());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add ChocoNutty"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(2,2,10,10));
      topPanel.add(TasteLabel);
      topPanel.add(TasteTextField);
      topPanel.add(MoodLabel);
      topPanel.add(MoodTextField);
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = false;
         try {
            isAdded = ChocoNuttyCRUD.addChocoNutty(new ChocoNutty(TasteTextField.getText(), MoodTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"ChocoNutty added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
