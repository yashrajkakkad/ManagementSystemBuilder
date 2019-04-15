import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.sql.SQLException;

public class UpdateAlgoPanel extends JPanel {
   
   private JLabel gogoLabel = new JLabel("Gogo");
   private JTextField gogoTextField = new JTextField(20);
   JButton SearchButton = new JButton("Search Algo");
   JButton UpdateButton = new JButton("Update Algo");
   JPanel subPanel = new JPanel();
   public UpdateAlgoPanel() throws SQLException{
      setLayout(new BorderLayout());
      gogoTextField.setMaximumSize(gogoTextField.getPreferredSize());
      add(new JLabel("Update Algo"), BorderLayout.NORTH);
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,10,10));
      topPanel.add(gogoLabel);
      subPanel.add(gogoTextField);
      subPanel.add(SearchButton);
      topPanel.add(subPanel);
      bottomPanel.add(UpdateButton);
      UpdateButton.setMaximumSize(UpdateButton.getPreferredSize());
      SearchButton.addActionListener((e) -> {
         Algo retrieveAlgo = null;
         try {
            retrieveAlgo = AlgoCRUD.viewAlgo(gogoTextField.getText().charAt(0));
         } catch(SQLException ex) {
            ex.printStackTrace();
         }
         if(retrieveAlgo==null) {
            JOptionPane.showMessageDialog(this,"Unexpected error occured!");
         } else {
            JOptionPane.showMessageDialog(this,"Data retrieved successfully!");
            gogoTextField.setText("" + retrieveAlgo.getGogo());
         }});
         UpdateButton.addActionListener((e) -> {
            boolean isUpdated = false;
            try {
               isUpdated = AlgoCRUD.updateAlgo(new Algo(gogoTextField.getText().charAt(0)), gogoTextField.getText().charAt(0));
            } catch(SQLException ex) {
               ex.printStackTrace();
            }
            if(isUpdated) {
               JOptionPane.showMessageDialog(this,"Algo Updated successfully!");
            } else {
               JOptionPane.showMessageDialog(this,"Unexpected error occured. It might be due to faulty internet or duplication in your primary key!");
            }
         });
         add(topPanel,BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH);
      }
   }
