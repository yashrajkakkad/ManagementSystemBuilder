package GUIGeneration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test2 extends JPanel {
    public Test2() {
        setLayout(new GridLayout(2, 2, 10, 10));
        JLabel idLabel = new JLabel("Student ID");
        JLabel nameLabel = new JLabel("Student Name");
        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        add(idLabel);
        add(idTextField);
        add(nameLabel);
        add(nameTextField);
        JButton bt1 = new JButton("TestButton");
        bt1.addActionListener((e) -> {
            
        });
    }
}
