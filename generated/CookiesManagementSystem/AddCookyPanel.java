import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class AddCookyPanel extends JPanel {
   
   private JLabel CockyNameLabel = new JLabel("CockyName");
   private JTextField CockyNameTextField = new JTextField(20);
   JButton AddButton = new JButton("Add Cooky");
   public AddCookyPanel() throws SQLException{
      setLayout(new BorderLayout());
      CockyNameTextField.setMaximumSize(CockyNameTextField.getPreferredSize());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add Cooky"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(CockyNameLabel);
      topPanel.add(CockyNameTextField);
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = false;
         try {
            isAdded = CookyCRUD.addCooky(new Cooky(Double.parseDouble(CockyNameTextField.getText())));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"Cooky added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
