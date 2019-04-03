package GUI;/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JPanel
{
    public LoginPage()
    {
        setLayout(new GridBagLayout());
        setBackground(new Color(255,255,255));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,15,5);

        c.gridx = 0;
        c.gridy = 1;
        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Century Gothic",Font.BOLD,48));
        add(loginTitle,c);

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
        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(passwordLabel,c);
        c.gridy+=2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        JPasswordField password = new JPasswordField(20);
        password.setBackground(new Color(255,255,255));
        password.setToolTipText("Password cannot be empty");
        password.setFont(new Font("Century Gothic",Font.PLAIN,18));
        add(password,c);

        c.gridy+=2;
        c.anchor = GridBagConstraints.LINE_START;
        JButton loginbtn = new JButton("Login");
        loginbtn.setFont(new Font("Century Gothic",Font.PLAIN,24));
        loginbtn.setEnabled(false);
        add(loginbtn,c);
        
        loginbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!username.getText().equals(""))
                {
                    if (!password.getText().equals(""))
                    {
                        Connection con = null;
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            con = DriverManager.getConnection("jdbc:mysql://134.209.159.227:3306/db_mansys","mansys","sysman$");
                            Statement stat =(Statement) con.createStatement();
                            String query="select username, password from tbl_loginInfo where username=? and password =?";
                            PreparedStatement ps=con.prepareStatement(query);
                            ps.setString(1, username.getText());
                            ps.setString(2, password.getText());
                            ResultSet i = ps.executeQuery();
                            if (i.next())
                            {
                                JOptionPane.showMessageDialog(null, "Login Sucessfull");
                                HomePageFrame.changeRootPanel(2);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Username or password incorrect");
                                username.requestFocus();
                            }
                            stat.close();
                            con.close();
                        }catch (Exception ex)
                        {
                            System.out.println("Exception caught --> " + ex);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Password cannot be empty");
                        password.requestFocus();
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
                loginbtn.setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e){}
        });

        c.gridy+=2;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel openSignUpPage = new JLabel("Haven't registered yet? Login here.");
        openSignUpPage.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(openSignUpPage,c);

        openSignUpPage.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                HomePageFrame.changeLoginPanel(0);
            }

            @Override public void mousePressed(MouseEvent e){}@Override public void mouseReleased(MouseEvent e){}@Override public void mouseEntered(MouseEvent e){}@Override public void mouseExited(MouseEvent e){}
        });
    }
}

/* PROGRAM OUTPUT
 */
