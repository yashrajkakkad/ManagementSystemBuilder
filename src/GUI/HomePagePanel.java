package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomePagePanel extends JPanel
{
    public HomePagePanel()
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
                getStarted.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                getStarted.setForeground(Color.BLACK);
            }
        });
    }
}

/* PROGRAM OUTPUT
 */
