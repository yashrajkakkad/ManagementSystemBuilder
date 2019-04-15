import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateRhytmPanel extends JPanel {
   
   private JLabel GirlfriendLabel = new JLabel("Girlfriend");
   private JTextField GirlfriendTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Rhytm");
   JButton UpdateButton = new JButton("Update Rhytm");
   JPanel subPanel = new JPanel();
   public UpdateRhytmPanel() throws SQLException{
      setLayout(new BorderLayout());
      GirlfriendTextField.setMaximumSize(GirlfriendTextField.getPreferredSize());
      add(new JLabel("Update Rhytm"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(GirlfriendLabel);
      subPanel.add(GirlfriendTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Rhytm retrieveRhytm = null;
         try {
            retrieveRhytm = RhytmCRUD.viewRhytm(GirlfriendTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveRhytm==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            GirlfriendTextField.setText("" + retrieveRhytm.getGirlfriend());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = RhytmCRUD.updateRhytm(new Rhytm(GirlfriendTextField.getText()), GirlfriendTextField.getText());
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Rhytm Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
