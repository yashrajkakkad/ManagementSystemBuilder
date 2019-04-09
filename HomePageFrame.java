package GUI;

// HELLO WORLD

import javax.swing.*;
import java.awt.*;

public class HomePageFrame extends JFrame {

    private static CardLayout rootCardLayout = new CardLayout();
    private static CardLayout loginCardLayout = new CardLayout();
    private static JPanel loginModulePanel = new JPanel();
    private static JPanel rootPanel = new JPanel();

    public HomePageFrame() {
        createView();
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }

    private void createView() {
        add(rootPanel);
        rootPanel.setLayout(rootCardLayout);

        HomePagePanel lPage = new HomePagePanel();
        rootPanel.add("Landing Page", lPage);

        JPanel loginModuleRootPanel = new JPanel();
        loginModuleRootPanel.setLayout(new GridLayout(0, 2));
        loginModuleRootPanel.add(new WelcomeMessagePanel());
        loginModulePanel.setLayout(loginCardLayout);
        loginModulePanel.add("Sign Up Page", new SignUpPage());
        loginModulePanel.add("Login Page", new LoginPage());
        loginModuleRootPanel.add(loginModulePanel);
        rootPanel.add("LoginModule", loginModuleRootPanel);

        SystemMenu systemPage = new SystemMenu();
        rootPanel.add("System Menu", systemPage);

        SystemCreationRootPanel systemCreationRootPanel = new SystemCreationRootPanel();
        rootPanel.add("Create System", systemCreationRootPanel);

        rootCardLayout.show(rootPanel, "Landing Page");
    }

    public static void changeLoginPanel(int code) {
        if (code == 1) {
            loginCardLayout.show(loginModulePanel, "Login Page");
        } else {
            loginCardLayout.show(loginModulePanel, "Sign Up Page");
        }
    }

    public static void changeRootPanel(int code) {
        switch (code) {
            case 1:
                rootCardLayout.show(rootPanel, "LoginModule");
                break;
            case 2:
                rootCardLayout.show(rootPanel, "System Menu");
                break;
            case 3:
                rootCardLayout.show(rootPanel, "Create System");
                break;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.getInstalledLookAndFeels();
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePageFrame();
            }
        });
    }
}

/* PROGRAM OUTPUT
 */
