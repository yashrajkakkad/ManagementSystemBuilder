/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


package GUI;

import GUIGeneration.ContactPanel;
import Utility.DatabaseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

public class DefineContactUsPanel extends JPanel
{
    private static String receiverEmailAddress;
    private static String currentUser;

    public static void setCurrentUser(String currentUser)
    {
        DefineContactUsPanel.currentUser = currentUser;
    }

    public DefineContactUsPanel()
    {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("Define the Contact Us panel here");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);

        JPanel defineContactUsContentPanel = new JPanel();
        add(defineContactUsContentPanel, BorderLayout.CENTER);
        defineContactUsContentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        defineContactUsContentPanel.setBackground(Color.WHITE);
        c.insets = new Insets(10,10,10,10);

        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 1;

        c.gridy = 1;
        JLabel provideEmailLabel = new JLabel("Recipient Email Address : ");
        provideEmailLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineContactUsContentPanel.add(provideEmailLabel,c);

        c.gridy++;
        JTextField provideEmailAddress = new JTextField(20);
        provideEmailAddress.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineContactUsContentPanel.add(provideEmailAddress,c);

        c.gridy++;
        JLabel useEmailAddress = new JLabel("Click here to use your already entered email address");
        useEmailAddress.setFont(new Font("Century Gothic",Font.PLAIN,28));
        defineContactUsContentPanel.add(useEmailAddress,c);

        useEmailAddress.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    String query = "select emailAddress from tbl_loginInfo where username = ?";
                    DatabaseUtil.ps = DatabaseUtil.con.prepareStatement(query);
                    DatabaseUtil.ps.setString(1,currentUser);
                    DatabaseUtil.rs = DatabaseUtil.ps.executeQuery();
                    DatabaseUtil.rs.next();
                    provideEmailAddress.setText(DatabaseUtil.rs.getString("emailAddress"));
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            @Override public void mousePressed(MouseEvent e){}
            @Override public void mouseReleased(MouseEvent e){}
            @Override public void mouseEntered(MouseEvent e){
                useEmailAddress.setForeground(Color.BLUE);
            }
            @Override public void mouseExited(MouseEvent e){
                useEmailAddress.setForeground(Color.BLACK);
            }
        });

        c.gridy++;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineContactUsContentPanel.add(submitButton,c);

        submitButton.addActionListener(e ->
        {
            receiverEmailAddress = provideEmailAddress.getText();
            try
            {
                ContactPanel contactPanel = new ContactPanel(receiverEmailAddress);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"DONE! Your Contact Us Panel has been generated");
            SystemCreationRootPanel.changeSystemCreationProcessPanel(6);
        });

    }
}

/* PROGRAM OUTPUT
 */
