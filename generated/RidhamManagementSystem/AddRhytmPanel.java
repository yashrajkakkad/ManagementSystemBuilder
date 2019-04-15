import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class AddRhytmPanel extends JPanel {
   
   private JLabel GirlfriendLabel = new JLabel("Girlfriend");
   private JTextField GirlfriendTextField = new JTextField(20);
   JButton AddButton = new JButton("Add Rhytm");
   public AddRhytmPanel() throws SQLException{
      setLayout(new BorderLayout());
      GirlfriendTextField.setMaximumSize(GirlfriendTextField.getPreferredSize());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add Rhytm"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(GirlfriendLabel);
      topPanel.add(GirlfriendTextField);
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = false;
         try {
            isAdded = RhytmCRUD.addRhytm(new Rhytm(GirlfriendTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"Rhytm added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
