package GUI;/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SystemMenu extends JPanel
{
    public SystemMenu()
    {
        setLayout(new GridBagLayout());
        setBackground(new Color(103, 228, 255));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        JLabel systemMenuWelcome = new JLabel("Create your own system");
        systemMenuWelcome.setFont(new Font("Century Gothic",Font.PLAIN,48));
        add(systemMenuWelcome,c);

        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        JLabel systemNameLabel = new JLabel("System Name");
        systemNameLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(systemNameLabel,c);

        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        JTextField systemName = new JTextField(20);
        systemName.setFont(new Font("Century Gothic",Font.PLAIN,18));
        add(systemName,c);

        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        JLabel systemUseCaseLabel = new JLabel("System Use Case");
        systemUseCaseLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(systemUseCaseLabel,c);

        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        JTextArea systemUseCase = new JTextArea(4,20);
        systemUseCase.setFont(new Font("Century Gothic",Font.PLAIN,18));
        add(systemUseCase,c);

        c.gridy+=2;
        c.gridwidth = 0;
        c.anchor = GridBagConstraints.CENTER;
        JButton createSystembtn = new JButton("Proceed");
        createSystembtn.setEnabled(false);
        createSystembtn.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(createSystembtn,c);

        createSystembtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!systemName.getText().equals(""))
                {
                    //VALIDATION CODE GOES HERE
                    HomePageFrame.changeRootPanel(3);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"System Name cannot be empty");
                    systemName.requestFocus();
                }
            }
        });

        systemName.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                createSystembtn.setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e)
            {

            }
        });
    }
}

/* PROGRAM OUTPUT
 */
