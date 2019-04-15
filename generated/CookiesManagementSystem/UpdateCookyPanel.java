import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateCookyPanel extends JPanel {
   
   private JLabel CockyNameLabel = new JLabel("CockyName");
   private JTextField CockyNameTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Cooky");
   JButton UpdateButton = new JButton("Update Cooky");
   JPanel subPanel = new JPanel();
   public UpdateCookyPanel() throws SQLException{
      setLayout(new BorderLayout());
      CockyNameTextField.setMaximumSize(CockyNameTextField.getPreferredSize());
      add(new JLabel("Update Cooky"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(CockyNameLabel);
      subPanel.add(CockyNameTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Cooky retrieveCooky = null;
         try {
            retrieveCooky = CookyCRUD.viewCooky(Double.parseDouble(CockyNameTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveCooky==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            CockyNameTextField.setText("" + retrieveCooky.getCockyName());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = CookyCRUD.updateCooky(new Cooky(Double.parseDouble(CockyNameTextField.getText())), Double.parseDouble(CockyNameTextField.getText()));
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Cooky Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
