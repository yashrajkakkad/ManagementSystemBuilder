import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateNullpaPanel extends JPanel {
   
   private JLabel NullIDLabel = new JLabel("NullID");
   private JTextField NullIDTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Nullpa");
   JButton UpdateButton = new JButton("Update Nullpa");
   JPanel subPanel = new JPanel();
   public UpdateNullpaPanel() throws SQLException{
      setLayout(new BorderLayout());
      NullIDTextField.setMaximumSize(NullIDTextField.getPreferredSize());
      add(new JLabel("Update Nullpa"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(NullIDLabel);
      subPanel.add(NullIDTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Nullpa retrieveNullpa = null;
         try {
            retrieveNullpa = NullpaCRUD.viewNullpa(Integer.parseInt(NullIDTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveNullpa==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            NullIDTextField.setText("" + retrieveNullpa.getNullID());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = NullpaCRUD.updateNullpa(new Nullpa(Integer.parseInt(NullIDTextField.getText())), Integer.parseInt(NullIDTextField.getText()));
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Nullpa Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
