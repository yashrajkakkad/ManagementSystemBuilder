import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class ViewAlgoPanel extends JPanel {
   
   private JLabel gogoLabel = new JLabel("Gogo");
   private JTextField gogoViewTextField = new JTextField(10);
   JButton ViewButton = new JButton("View Algo");
   public ViewAlgoPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("View Algo"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(gogoLabel);
      topPanel.add(gogoViewTextField);
      bottomPanel.add(ViewButton);
      ViewButton.setMaximumSize(ViewButton.getPreferredSize());
      ViewButton.addActionListener((e) -> {
         Algo retrieveAlgo = null;
         try {
            retrieveAlgo = AlgoCRUD.viewAlgo(gogoViewTextField.getText().charAt(0));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveAlgo==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
         }
      });
      add(topPanel,BorderLayout.CENTER);
      add(bottomPanel, BorderLayout.SOUTH);
   }
}
