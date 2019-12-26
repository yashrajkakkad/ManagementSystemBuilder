/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import CodeGeneration.EntityManager;
import GUIGeneration.*;

public class FinishPanel extends JPanel {
    public FinishPanel() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel, BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255, 255, 255));
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("You are almost there!");
        titleLabel.setFont(new Font("Century Gothic", Font.PLAIN, 60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel, tc);

        JPanel finishPanel = new JPanel();
        finishPanel.setLayout(new GridBagLayout());
        finishPanel.setBackground(Color.WHITE);
        add(finishPanel, BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;

        JLabel chooseLocationLabel = new JLabel("Your system is ready!");
        chooseLocationLabel.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        finishPanel.add(chooseLocationLabel, c);

        c.gridy++;
        JLabel donationPromptLabel = new JLabel("Consider donating a small amount to support us");
        donationPromptLabel.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        finishPanel.add(donationPromptLabel, c);

        c.gridy++;
        JLabel donationLabel = new JLabel("Scan the below QR code to donate");
        donationLabel.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        finishPanel.add(donationLabel, c);

        c.gridy++;
        JLabel qrCodeLabel = new JLabel();
        if (System.getProperty("os.name").startsWith("Windows")) {
            qrCodeLabel.setIcon(new ImageIcon("resources\\QRCode.jpeg"));
        } else {
            qrCodeLabel.setIcon(new ImageIcon("resources/QRCode.jpeg"));
        }
        finishPanel.add(qrCodeLabel, c);
    }
}

/*
 * PROGRAM OUTPUT
 */
