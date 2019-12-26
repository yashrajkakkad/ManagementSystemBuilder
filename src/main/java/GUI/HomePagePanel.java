package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton setupDBbtn = new JButton("Setup Database");
        setupDBbtn.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(setupDBbtn,c);

        setupDBbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.getInstalledLookAndFeels();
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SetupDB setupDB = new SetupDB();
                        setupDB.spawn();
                    }
                });
            }
        });

        c.gridy++;
        c.anchor = GridBagConstraints.CENTER;
        JButton getStarted = new JButton("Sign Up Here.");
        getStarted.setFont(new Font("Century Gothic",Font.PLAIN,24));
        add(getStarted,c);

        getStarted.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Main.changeRootPanel(1);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}

/* PROGRAM OUTPUT
 */
