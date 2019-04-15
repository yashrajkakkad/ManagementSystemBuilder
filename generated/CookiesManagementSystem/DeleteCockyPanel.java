import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class DeleteCockyPanel extends JPanel {
   
   private JLabel TakitakiLabel = new JLabel("Takitaki");
   private JTextField TakitakiViewTextField = new JTextField(10);
   JButton DeleteButton = new JButton("Delete Cocky");
   JButton SearchButton = new JButton("Search Cocky");
   JPanel subPanel = new JPanel();
   public DeleteCockyPanel() throws SQLException{
      setLayout(new BorderLayout());
      add(new JLabel("Delete Cocky"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(TakitakiLabel);
      subPanel.add(TakitakiViewTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(DeleteButton);
      DeleteButton.setMaximumSize(DeleteButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Cocky retrieveCocky = null;
         try {
            retrieveCocky = CockyCRUD.viewCocky(TakitakiViewTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveCocky==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      DeleteButton.addActionListener((e) -> {
         boolean isDeleted = false;
         try {
            isDeleted = CockyCRUD.deleteCocky(TakitakiViewTextField.getText());
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isDeleted) {
            JOptionPane.showMessageDialog(this,"Cocky deleted successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      }
   }
