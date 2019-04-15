import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class ViewRandomChikibumPanel extends JPanel {
   
   private JLabel BumbumboleLabel = new JLabel("Bumbumbole");
   private JTextField BumbumboleViewTextField = new JTextField(10);
   JButton ViewButton = new JButton("View RandomChikibum");
   public ViewRandomChikibumPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("View RandomChikibum"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(BumbumboleLabel);
      topPanel.add(BumbumboleViewTextField);
      bottomPanel.add(ViewButton);
      ViewButton.setMaximumSize(ViewButton.getPreferredSize());
      ViewButton.addActionListener((e) -> {
         RandomChikibum retrieveRandomChikibum = null;
         try {
            retrieveRandomChikibum = RandomChikibumCRUD.viewRandomChikibum(BumbumboleViewTextField.getText().charAt(0));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveRandomChikibum==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
