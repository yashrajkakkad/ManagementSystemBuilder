import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class DeleteNullpaPanel extends JPanel {
   
   private JLabel NullIDLabel = new JLabel("NullID");
   private JTextField NullIDViewTextField = new JTextField(10);
   JButton DeleteButton = new JButton("Delete Nullpa");
   JButton SearchButton = new JButton("Search Nullpa");
   JPanel subPanel = new JPanel();
   public DeleteNullpaPanel() throws SQLException{
      setLayout(new BorderLayout());
      add(new JLabel("Delete Nullpa"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(NullIDLabel);
      subPanel.add(NullIDViewTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(DeleteButton);
      DeleteButton.setMaximumSize(DeleteButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Nullpa retrieveNullpa = null;
         try {
            retrieveNullpa = NullpaCRUD.viewNullpa(Integer.parseInt(NullIDViewTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveNullpa==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      DeleteButton.addActionListener((e) -> {
         boolean isDeleted = false;
         try {
            isDeleted = NullpaCRUD.deleteNullpa(Integer.parseInt(NullIDViewTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isDeleted) {
            JOptionPane.showMessageDialog(this,"Nullpa deleted successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
      }
   }
