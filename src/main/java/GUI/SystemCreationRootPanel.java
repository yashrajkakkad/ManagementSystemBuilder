package GUI;

import CodeGeneration.EntityManager;
import GUIGeneration.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCreationRootPanel extends JPanel {
    private static CardLayout systemCreationCardLayout = new CardLayout();
    private static JPanel systemCreationCardPanel;
    private static JLabel placeFiller;
    private static JButton startYourSystembtn;
    private String currentDirectory = System.getProperty("user.dir");

    public SystemCreationRootPanel() throws IOException {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel, BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255, 255, 255));
        tc.gridheight = 2;
        JLabel titleLabel = new JLabel("MANAGEMENT SYSTEM BUILDER");
        titleLabel.setFont(new Font("Century Gothic", Font.PLAIN, 60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel, tc);

        systemCreationCardPanel = new JPanel();
        add(systemCreationCardPanel, BorderLayout.CENTER);
        systemCreationCardPanel.setLayout(systemCreationCardLayout);
        systemCreationCardPanel.add("Add Entities", new AddEntity());
        systemCreationCardPanel.add("Add Datafields", new AddDataFields());
        systemCreationCardPanel.add("Define Entity Panels", new DefineEntityPanels());
        systemCreationCardPanel.add("Define About us Panel", new DefineAboutPanel());
        systemCreationCardPanel.add("Define Contact us Panel", new DefineContactUsPanel());
        systemCreationCardPanel.add("Finish Page", new FinishPanel());
        systemCreationCardLayout.show(systemCreationCardPanel, "Add Entities");
        // systemCreationCardLayout.show(systemCreationCardPanel,"Finish Page");

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.PAGE_END);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(103, 228, 255));
        GridBagConstraints bc = new GridBagConstraints();

        bc.gridx = 0;
        bc.gridy = 0;
        bc.insets = new Insets(10, 10, 10, 10);
        placeFiller = new JLabel("Step 1 of 4");
        placeFiller.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        bottomPanel.add(placeFiller, bc);

        bc.gridx++;
        startYourSystembtn = new JButton("Start your System");
        startYourSystembtn.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        startYourSystembtn.setEnabled(false);
        bottomPanel.add(startYourSystembtn, bc);
        startYourSystembtn.addActionListener(e -> {
            if (System.getProperty("os.name").startsWith("Windows")) {
                try {
                    createJARWindows();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    createJARLinux();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public String getExecPath(String string) {
        String path = "";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c \"cd C:\\ && where /r . " + string + ".exe\"");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                path = line;
                break;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return path;
    }

    public void createJARWindows() throws IOException {

        String javac = getExecPath("javac");
        String jar = getExecPath("jar");
        System.out.println(javac);
        System.out.println(jar);

        StringBuilder command_string = new StringBuilder("");
        command_string.append("cd \"").append(currentDirectory).append("\" && ").append("xcopy deps \"")
                .append(currentDirectory).append("\\").append(EntityManager.getDirectoryName()).append("\" /i /s && ")
                .append("cd \"").append(currentDirectory).append("\\").append(EntityManager.getDirectoryName())
                .append("\" && ").append("dir /s /B *.java > sources.txt").append(" && ")
                .append("\"" + javac
                        + "\" -cp \"mysql-connector-java-5.1.11-bin.jar;javax.mail.jar;activation.jar\" @sources.txt -d out && ")
                .append("\"" + jar + "\" cfm ").append(EntityManager.getProjectName()).append(".jar")
                .append(" Manifest.txt -C out/ . && ").append("java -jar ").append(EntityManager.getProjectName())
                .append(".jar");
        System.out.println(command_string.toString());
        try {
            Runtime rt = Runtime.getRuntime();
            // Process proc = rt.exec("cmd /c \""+command_string.toString()+"\"");
            Process proc = rt.exec("cmd /c start cmd.exe /K \"" + command_string.toString() + "\"");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            MainFrame mainFrame = new MainFrame();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createJARLinux() throws InterruptedException, IOException {

        StringBuilder command = new StringBuilder("");
        command.append("cd deps && cp -r . \"" + currentDirectory + "/" + EntityManager.getDirectoryName() + "\" && ")
                .append("cd \"").append(currentDirectory).append("/").append(EntityManager.getDirectoryName())
                .append("\" && ").append("ls *.java >> sources.txt && ")
                .append("javac -cp \"mysql-connector-java-5.1.11-bin.jar:javax.mail.jar:activation.jar:\" @sources.txt -d out && ")
                .append("jar cfm ").append(EntityManager.getProjectName()).append(".jar")
                .append(" Manifest.txt -C out/ . && ").append("java -jar ").append(EntityManager.getProjectName())
                .append(".jar");

        System.out.println(command.toString());
        // Process process = null;
        // try {
        // process = Runtime.getRuntime().exec(command.toString());
        // process.waitFor();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // BufferedReader reader = new BufferedReader(new
        // InputStreamReader(process.getInputStream()));
        // String line;
        // while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        // }
        try {
            FileWriter writer = new FileWriter("createJAR.sh");
            writer.write("#!/bin/bash\n\n");
            writer.write(command.toString());
            writer.close();
            Process process = Runtime.getRuntime().exec("./createJAR.sh");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeSystemCreationProcessPanel(int code) {
        switch (code) {
        case 1:
            systemCreationCardPanel.add("Add Datafields", new AddDataFields());
            systemCreationCardLayout.show(systemCreationCardPanel, "Add Datafields");
            break;
        case 2:
            systemCreationCardLayout.show(systemCreationCardPanel, "Add Entities");
            break;
        case 3:
            systemCreationCardLayout.show(systemCreationCardPanel, "Define Entity Panels");
            placeFiller.setText("Step 2 of 4");
            break;
        case 4:
            systemCreationCardLayout.show(systemCreationCardPanel, "Define About us Panel");
            placeFiller.setText("Step 3 of 4");
            break;
        case 5:
            systemCreationCardLayout.show(systemCreationCardPanel, "Define Contact us Panel");
            placeFiller.setText("Step 3 of 4");
            break;
        case 6:
            systemCreationCardLayout.show(systemCreationCardPanel, "Finish Page");
            placeFiller.setText("Step 4 of 4");
            startYourSystembtn.setEnabled(true);
            break;
        }
    }
}

/*
 * PROGRAM OUTPUT
 */
