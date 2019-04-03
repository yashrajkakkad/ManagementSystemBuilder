package GUI;/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectLandingPage extends JPanel
{
    public ProjectLandingPage()
    {
        setLayout(new GridBagLayout());
        setBackground(new Color(103,228,255));
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        JLabel welcomeMessage = new JLabel("WELCOME");
        welcomeMessage.setFont(new Font("Century Gothic",Font.PLAIN,48));
        add(welcomeMessage,c);

        c.gridy++;
        c.anchor = GridBagConstraints.CENTER;
        JLabel getStarted = new JLabel("Sign Up Here.");
        getStarted.setFont(new Font("Century Gothic",Font.PLAIN,36));
        add(getStarted,c);

        getStarted.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                HomePageFrame.changeRootPanel(1);
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }
}

/* PROGRAM OUTPUT
 */
