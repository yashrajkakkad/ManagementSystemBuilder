/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import GUIGeneration.*;

public class FinishPanel extends JPanel
{
    public FinishPanel()
    {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("You are almost there!");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);

        JPanel finishPanel = new JPanel();
        finishPanel.setLayout(new GridBagLayout());
        finishPanel.setBackground(Color.WHITE);
        add(finishPanel,BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;

        JLabel chooseLocationLabel = new JLabel("Choose where to save the JAR file");
        chooseLocationLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        finishPanel.add(chooseLocationLabel,c);

        c.gridx++;
        JTextField filePath = new JTextField(30);
        filePath.setFont(new Font("Century Gothic",Font.PLAIN,18));
        finishPanel.add(filePath,c);

        c.gridy++;
        JButton chooseLocation = new JButton("Choose Location");
        chooseLocation.setFont(new Font("Century Gothic",Font.PLAIN,30));
        finishPanel.add(chooseLocation,c);

        chooseLocation.addActionListener(e ->
        {
            JFileChooser jFileChooser = new JFileChooser();
            int response = jFileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION)
            {
                filePath.setText(jFileChooser.getSelectedFile().getAbsolutePath());
            }
            try
            {
                MainFrame mainFrame = new MainFrame();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });
    }
}

/* PROGRAM OUTPUT
 */
