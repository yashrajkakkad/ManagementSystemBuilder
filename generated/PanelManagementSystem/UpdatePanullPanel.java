import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdatePanullPanel extends JPanel {
   
   private JLabel NameLabel = new JLabel("Name");
   private JTextField NameTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Panull");
   JButton UpdateButton = new JButton("Update Panull");
   JPanel subPanel = new JPanel();
   public UpdatePanullPanel() throws SQLException{
      setLayout(new BorderLayout());
      NameTextField.setMaximumSize(NameTextField.getPreferredSize());
      add(new JLabel("Update Panull"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(NameLabel);
      subPanel.add(NameTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Panull retrievePanull = null;
         try {
            retrievePanull = PanullCRUD.viewPanull(NameTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrievePanull==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            NameTextField.setText("" + retrievePanull.getName());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = PanullCRUD.updatePanull(new Panull(NameTextField.getText()), NameTextField.getText());
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Panull Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
