import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class DeleteCookyPanel extends JPanel {
   
   private JLabel CockyNameLabel = new JLabel("CockyName");
   private JTextField CockyNameViewTextField = new JTextField(10);
   JButton DeleteButton = new JButton("Delete Cooky");
   JButton SearchButton = new JButton("Search Cooky");
   JPanel subPanel = new JPanel();
   public DeleteCookyPanel() throws SQLException{
      setLayout(new BorderLayout());
      add(new JLabel("Delete Cooky"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(CockyNameLabel);
      subPanel.add(CockyNameViewTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(DeleteButton);
      DeleteButton.setMaximumSize(DeleteButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Cooky retrieveCooky = null;
         try {
            retrieveCooky = CookyCRUD.viewCooky(Double.parseDouble(CockyNameViewTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveCooky==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      DeleteButton.addActionListener((e) -> {
         boolean isDeleted = false;
         try {
            isDeleted = CookyCRUD.deleteCooky(Double.parseDouble(CockyNameViewTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isDeleted) {
            JOptionPane.showMessageDialog(this,"Cooky deleted successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      }
   }
