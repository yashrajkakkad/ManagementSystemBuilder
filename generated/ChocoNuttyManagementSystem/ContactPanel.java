import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import Utility.Email;

public class ContactPanel extends JPanel {
   
   private JLabel nameLabel = new JLabel("Name : ");
   private JLabel emailLabel = new JLabel("E-mail : ");
   private JLabel messageLabel = new JLabel("Message");
   private JTextField nameTextField = new JTextField("John Doe");
   private JTextField emailTextField = new JTextField("abc@xyz.com");
   private JTextArea messageTextArea = new JTextArea("Your message comes here....");
   private JButton submitButton = new JButton("Submit");
   public ContactPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("Contact Us"), BorderLayout.NORTH);
      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(6,2,10,10));
      bottomPanel.add(nameLabel);
      bottomPanel.add(nameTextField);
      bottomPanel.add(emailLabel);
      bottomPanel.add(emailTextField);
      bottomPanel.add(messageLabel);
      bottomPanel.add(messageTextArea);
      add(bottomPanel,BorderLayout.CENTER);
      submitButton.setMaximumSize(submitButton.getPreferredSize());
      add(submitButton,BorderLayout.SOUTH);
      submitButton.addActionListener(e -> {
         Email.sendEmail("yashrajkakkad@gmail.com","You have a new contact message!",messageTextArea.getText());
         JOptionPane.showMessageDialog(this,"Thank you for contacting us!");
      });
   }
}
