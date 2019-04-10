package GUIGeneration;

import Picocog.*;
import Utility.FileManager;
import java.io.IOException;

public class ContactPanel {
    private final PicoWriter w;
    private final String contactEmail;

    public ContactPanel(String contactMessage) throws IOException {
        this.contactEmail = contactMessage;
        w = new PicoWriter();
        initialize();
        generateComponents();
        generateConstructor();
        finish();
    }
        
    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class ContactPanel extends JPanel {");        
        w.writeln("");                
    }
    
    private void generateComponents() {
        w.writeln("private JLabel nameLabel = new JLabel(\"Name : \");");
        w.writeln("private JLabel emailLabel = new JLabel(\"E-mail : \");");
        w.writeln("private JLabel messageLabel = new JLabel(\"Message\");");
        w.writeln("private JTextField nameTextField = new JTextField(\"John Doe\");");
        w.writeln("private JTextField emailTextField = new JTextField(\"abc@xyz.com\");");
        w.writeln("private JTextArea messageTextArea = new JTextArea"
                + "(\"Your message comes here....\");");
        w.writeln("private JButton submitButton = new JButton(\"Submit\");");        
    }
    
    private void generateConstructor() {
        w.writeln_r("public ContactPanel() {");
        w.writeln("setLayout(new BorderLayout());");
        w.writeln("add(new JLabel(\"Contact Us\"), BorderLayout.NORTH);");        
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("bottomPanel.setLayout(new GridLayout(6,2,10,10));");
        w.writeln("bottomPanel.add(nameLabel);");
        w.writeln("bottomPanel.add(nameTextField);");
        w.writeln("bottomPanel.add(emailLabel);");
        w.writeln("bottomPanel.add(emailTextField);");
        w.writeln("bottomPanel.add(messageLabel);");
        w.writeln("bottomPanel.add(messageTextArea);");
        w.writeln("add(bottomPanel,BorderLayout.CENTER);");
        w.writeln("submitButton.setMaximumSize(submitButton.getPreferredSize());");
        w.writeln("add(submitButton,BorderLayout.SOUTH);");
        w.writeln_r("submitButton.addActionListener(e -> {");
        w.writeln("Email.sendEmail(\"" + contactEmail +"\","
                + "\"You have a new contact message!\",messageTextArea.getText());");
        w.writeln("JOptionPane.showMessageDialog(this,\"Thank you for contacting us!\");");
        w.writeln_l("});");
        w.writeln_l("}");
    }
    
    private void finish() throws IOException {
        w.writeln_l("}");
        FileManager.writeCode("ContactPanel", w);        
    }
}
