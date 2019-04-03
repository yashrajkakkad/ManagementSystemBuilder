package GUI;/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;

public class WelcomeMessagePanel extends JPanel
{
    public WelcomeMessagePanel()
    {
        setLayout(new GridBagLayout());
        setBackground(new Color(103, 228, 255));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        JLabel w1 = new JLabel("Welcome");
        w1.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(w1,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.CENTER;
        JLabel w2 = new JLabel("To");
        w2.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(w2,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.CENTER;
        JLabel w3 = new JLabel("Management");
        w3.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(w3,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.CENTER;
        JLabel w4 = new JLabel("System");
        w4.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(w4,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.CENTER;
        JLabel w5 = new JLabel("Builder");
        w5.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(w5,c);

    }
}

/* PROGRAM OUTPUT
 */
