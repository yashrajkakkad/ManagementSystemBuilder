import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class AddPanullPanel extends JPanel {
   
   private JLabel NameLabel = new JLabel("Name");
   private JTextField NameTextField = new JTextField(20);
   JButton AddButton = new JButton("Add Panull");
   public AddPanullPanel() throws SQLException{
      setLayout(new BorderLayout());
      NameTextField.setMaximumSize(NameTextField.getPreferredSize());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add Panull"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(NameLabel);
      topPanel.add(NameTextField);
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = false;
         try {
            isAdded = PanullCRUD.addPanull(new Panull(NameTextField.getText()));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"Panull added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
