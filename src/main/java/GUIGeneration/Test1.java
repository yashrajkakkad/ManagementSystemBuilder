package GUIGeneration;

import java.awt.*;
import javax.swing.*;

public class Test1 extends JFrame {
    public Test1() {
        setLayout(new BorderLayout());        
        JLabel titleLabel = new JLabel("School Management System");
        setTitle("School Management System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel,BorderLayout.NORTH);
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new CardLayout());
        add(new JScrollPane(new Test2()), BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        Test1 frame = new Test1();
//        frame.setTitle("School Management System");
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
