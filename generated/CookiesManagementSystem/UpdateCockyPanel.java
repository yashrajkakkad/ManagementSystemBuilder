import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateCockyPanel extends JPanel {
   
   private JLabel TakitakiLabel = new JLabel("Takitaki");
   private JTextField TakitakiTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Cocky");
   JButton UpdateButton = new JButton("Update Cocky");
   JPanel subPanel = new JPanel();
   public UpdateCockyPanel() throws SQLException{
      setLayout(new BorderLayout());
      TakitakiTextField.setMaximumSize(TakitakiTextField.getPreferredSize());
      add(new JLabel("Update Cocky"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(TakitakiLabel);
      subPanel.add(TakitakiTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Cocky retrieveCocky = null;
         try {
            retrieveCocky = CockyCRUD.viewCocky(TakitakiTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveCocky==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            TakitakiTextField.setText("" + retrieveCocky.getTakitaki());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = CockyCRUD.updateCocky(new Cocky(TakitakiTextField.getText()), TakitakiTextField.getText());
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Cocky Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
