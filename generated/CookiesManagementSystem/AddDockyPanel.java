import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class AddDockyPanel extends JPanel {
   
   private JLabel tiktokLabel = new JLabel("Tiktok");
   private JTextField tiktokTextField = new JTextField(20);
   JButton AddButton = new JButton("Add Docky");
   public AddDockyPanel() throws SQLException{
      setLayout(new BorderLayout());
      tiktokTextField.setMaximumSize(tiktokTextField.getPreferredSize());
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      add(new JLabel("Add Docky"), BorderLayout.NORTH);
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(tiktokLabel);
      topPanel.add(tiktokTextField);
      bottomPanel.add(AddButton);
      AddButton.setMaximumSize(AddButton.getPreferredSize());
      AddButton.addActionListener((e) -> {
         boolean isAdded = false;
         try {
            isAdded = DockyCRUD.addDocky(new Docky(Integer.parseInt(tiktokTextField.getText())));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(isAdded) {
            JOptionPane.showMessageDialog(this,"Docky added successfully!");
         } else {
            JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
