package GUI;

import CodeGeneration.EntityManager;
import CodeGeneration.GenerateDatabaseUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        systemUseCase.setLineWrap(true);
        systemUseCase.setWrapStyleWord(true);
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
                    try
                    {
                        EntityManager.setProjectName(systemName.getText());
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    EntityManager.generateDBName();
                    try {
                        EntityManager.createDB();
                    } catch (SQLException ex) {
                        Logger.getLogger(SystemMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(EntityManager.getDBName());
                    try {
                        GenerateDatabaseUtil.generateCode(EntityManager.getDBName());
                    } catch (IOException | SQLException ex) {
                        Logger.getLogger(SystemMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Main.changeRootPanel(3);
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
