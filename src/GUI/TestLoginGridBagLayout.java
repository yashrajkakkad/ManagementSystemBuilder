package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TestLoginGridBagLayout {

    protected void initUI() throws MalformedURLException {
        JFrame frame = new JFrame("Admin Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel background = new JLabel(new ImageIcon(new URL(
                "http://media1.santabanta.com/full1/Football/Football%20Abstract/football-abstract-6a.jpg"))) {
            @Override
            public Dimension getPreferredSize() {
                Dimension preferredSize = super.getPreferredSize();
                Dimension layoutPreferredSize = super.preferredSize();
                preferredSize.width = Math.max(preferredSize.width, layoutPreferredSize.width);
                preferredSize.height = Math.max(preferredSize.height, layoutPreferredSize.height);
                return preferredSize;
            }
        };
        background.setLayout(new BorderLayout());
        frame.add(background);
        GridBagLayout gbl = new GridBagLayout();
        final JPanel loginPanel = new JPanel(gbl);
        loginPanel.setOpaque(false);
        background.add(loginPanel, BorderLayout.WEST);
        JLabel adminIDLabel = new JLabel("Admin ID", JLabel.LEFT);
        JLabel passwordLabel = new JLabel("Password", JLabel.LEFT);
        adminIDLabel.setFont(new Font("TimesRoman", Font.BOLD, 16));
        adminIDLabel.setForeground(Color.BLUE);
        adminIDLabel.setBackground(Color.WHITE);
        passwordLabel.setFont(new Font("TimesRoman", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLUE);
        passwordLabel.setBackground(Color.WHITE);
        final JTextField adminID = new JTextField(15);
        final JPasswordField password = new JPasswordField(15);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setOpaque(false);
        JButton back = new JButton("Back");
        JButton signIn = new JButton("Sign in");
        buttonPanel.add(back);
        buttonPanel.add(signIn);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(adminIDLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        loginPanel.add(adminID, gbc);
        gbc.gridwidth = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        loginPanel.add(password, gbc);
        loginPanel.add(buttonPanel, gbc);
        GridBagConstraints gbcFiller = new GridBagConstraints();
        gbcFiller.weightx = 1.0;
        gbcFiller.weighty = 1.0;
        gbcFiller.fill = GridBagConstraints.BOTH;
        loginPanel.add(Box.createGlue(), gbcFiller);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestLoginGridBagLayout().initUI();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}