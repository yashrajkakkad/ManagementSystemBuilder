package GUI;/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SignUpPage extends JPanel
{
    public SignUpPage()
    {
        setLayout(new GridBagLayout());
        setBackground(new Color(255,255,255));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,30,5);

        c.gridx = 0;
        c.gridy = 1;
        JLabel signUpTitle = new JLabel("Sign Up");
        signUpTitle.setFont(new Font("Century Gothic",Font.BOLD,48));
        add(signUpTitle,c);

        c.insets = new Insets(5,5,5,5);
        c.gridy+=4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel usernameLabel = new JLabel("Username : ");
        usernameLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(usernameLabel,c);
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JTextField username = new JTextField(20);
        username.setBackground(new Color(255,255,255));
        username.setFont(new Font("Century Gothic",Font.PLAIN,18));
        username.setToolTipText("Username cannot be empty");
        add(username,c);
        c.gridy++;

        c.gridx = 0;
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel emailLabel = new JLabel("E-mail Address : ");
        emailLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(emailLabel,c);
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JTextField emailAddress = new JTextField(20);
        emailAddress.setBackground(new Color(255,255,255));
        emailAddress.setFont(new Font("Century Gothic",Font.PLAIN,18));
        emailAddress.setToolTipText("EntityCRUDLogic.Email Address cannot be empty");
        add(emailAddress,c);

        c.gridx = 0;
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(passwordLabel,c);
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JPasswordField password = new JPasswordField(20);
        password.setBackground(new Color(255,255,255));
        password.setFont(new Font("Century Gothic",Font.PLAIN,18));
        password.setToolTipText("Password cannot be empty");
        add(password,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.LINE_START;
        JButton signUpbtn = new JButton("Sign Up");
        signUpbtn.setFont(new Font("Century Gothic",Font.PLAIN,24));
        signUpbtn.setEnabled(false);
        add(signUpbtn,c);

        signUpbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!username.getText().equals(""))
                {
                    if (!emailAddress.getText().equals(""))
                    {
                        if (!password.getText().equals(""))
                        {
                            //VALIDATION CODE GOES HERE
                            Connection con = null;
                            try
                            {
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://134.209.159.227:3306/db_mansys","mansys","sysman$");
                                Statement stat =(Statement) con.createStatement();
                                String insertString = "INSERT INTO `tbl_loginInfo`(`username`, `emailAddress`,`password`) VALUES ('"+username.getText()+"','"+emailAddress.getText()+"','"+password.getText()+"')";
                                System.out.println(insertString);
                                int i = stat.executeUpdate (insertString);
                                System.out.println("Return value from executeUpdate" + i);
                                stat.close();
                                con.close();
                            }catch (Exception ex)
                            {
                                System.out.println("Exception caught --> " + ex);
                            }
                            HomePageFrame.changeRootPanel(2);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Password cannot be empty");
                            password.requestFocus();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"EntityCRUDLogic.Email Address cannot be empty");
                        emailAddress.requestFocus();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Username cannot be empty");
                    username.requestFocus();
                }
            }
        });

        password.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                signUpbtn.setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e){}
        });

        c.gridy+=3;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel openLoginPage = new JLabel("Already Registered? Login Here.");
        openLoginPage.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(openLoginPage,c);

        openLoginPage.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                HomePageFrame.changeLoginPanel(1);
            }

            @Override public void mousePressed(MouseEvent e){}@Override public void mouseReleased(MouseEvent e){}@Override public void mouseEntered(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}
        });
    }
}

/* PROGRAM OUTPUT
 */
