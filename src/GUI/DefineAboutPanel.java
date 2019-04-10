/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import GUIGeneration.AboutPanel;

public class DefineAboutPanel extends JPanel
{

    public DefineAboutPanel()
    {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("Define the About Us panel here");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);

        JPanel defineAboutUsContentPanel = new JPanel();
        add(defineAboutUsContentPanel, BorderLayout.CENTER);
        defineAboutUsContentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        defineAboutUsContentPanel.setBackground(Color.WHITE);
        c.insets = new Insets(10,10,10,10);

        c.gridy = 1;
        JLabel aboutUsTextAreaLabel = new JLabel("Give some info about your Organization");
        aboutUsTextAreaLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineAboutUsContentPanel.add(aboutUsTextAreaLabel,c);

        c.gridy++;
        JTextArea aboutUsContent = new JTextArea(5,25);
        aboutUsContent.setLineWrap(true);
        aboutUsContent.setWrapStyleWord(true);
        aboutUsContent.setFont(new Font("Century Gothic",Font.PLAIN,24));
        defineAboutUsContentPanel.add(aboutUsContent,c);

        c.gridy++;
        JLabel contactUsCheck = new JLabel("Do you want a Contact Us Page too?");
        contactUsCheck.setFont(new Font("Century Gothic",Font.PLAIN,30));
        defineAboutUsContentPanel.add(contactUsCheck,c);

        c.gridy++;
        JLabel openContactUsPage = new JLabel("If yes, click here to define one!");
        openContactUsPage.setFont(new Font("Century Gothic",Font.PLAIN,30));
        defineAboutUsContentPanel.add(openContactUsPage,c);

        c.gridy++;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineAboutUsContentPanel.add(submitButton,c);

        openContactUsPage.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                SystemCreationRootPanel.changeSystemCreationProcessPanel(5);
            }
            @Override public void mousePressed(MouseEvent e){}
            @Override public void mouseReleased(MouseEvent e){}
            @Override public void mouseEntered(MouseEvent e){
                openContactUsPage.setForeground(Color.BLUE);
            }
            @Override public void mouseExited(MouseEvent e){
                openContactUsPage.setForeground(Color.BLACK);
            }
        });

        submitButton.addActionListener(e ->
        {
            try
            {
                System.out.println(aboutUsContent.getText());
                AboutPanel aboutPanel = new AboutPanel(aboutUsContent.getText());
                SystemCreationRootPanel.changeSystemCreationProcessPanel(6);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });
    }
}

/* PROGRAM OUTPUT
 */
