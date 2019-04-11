package GUI;

import CodeGeneration.EntityManager;
import GUIGeneration.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SystemCreationRootPanel extends JPanel
{
    private static CardLayout systemCreationCardLayout = new CardLayout();
    private static JPanel systemCreationCardPanel;
    private static JLabel placeFiller;
    private static JButton startYourSystembtn;

    public SystemCreationRootPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        tc.gridheight = 2;
        JLabel titleLabel = new JLabel("MANAGEMENT SYSTEM BUILDER");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);

        systemCreationCardPanel = new JPanel();
        add(systemCreationCardPanel,BorderLayout.CENTER);
        systemCreationCardPanel.setLayout(systemCreationCardLayout);
        systemCreationCardPanel.add("Add Entities",new AddEntity());
        systemCreationCardPanel.add("Add Datafields",new AddDataFields());
        systemCreationCardPanel.add("Define Entity Panels", new DefineEntityPanels());
        systemCreationCardPanel.add("Define About us Panel", new DefineAboutPanel());
        systemCreationCardPanel.add("Define Contact us Panel", new DefineContactUsPanel());
        systemCreationCardPanel.add("Finish Page",new FinishPanel());
        systemCreationCardLayout.show(systemCreationCardPanel,"Add Entities");
        //systemCreationCardLayout.show(systemCreationCardPanel,"Finish Page");

        JPanel bottomPanel = new JPanel();
        add(bottomPanel,BorderLayout.PAGE_END);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(103, 228, 255));
        GridBagConstraints bc = new GridBagConstraints();

        bc.gridx = 0;
        bc.gridy = 0;
        bc.insets = new Insets(10,10,10,10);
        placeFiller = new JLabel("Step 1 of 4");
        placeFiller.setFont(new Font("Century Gothic",Font.PLAIN,36));
        bottomPanel.add(placeFiller,bc);

        bc.gridx++;
        startYourSystembtn = new JButton("Start your System");
        startYourSystembtn.setFont(new Font("Century Gothic",Font.PLAIN,36));
        startYourSystembtn.setEnabled(false);
        bottomPanel.add(startYourSystembtn,bc);
        startYourSystembtn.addActionListener(e ->
        {
            StringBuilder command = new StringBuilder("");
            command.append("cd D:\\Study\\Semester_2_2018-19\\OOP_Lab\\Java_Projects\\MSBuilderGUI && ")
                    .append("xcopy production D:\\Study\\Semester_2_2018-19\\OOP_Lab\\Java_Projects\\MSBuilderGUI\\").append(EntityManager.getDirectoryName()).append(" /i /s && ")
                    .append("cd D:\\Study\\Semester_2_2018-19\\OOP_Lab\\Java_Projects\\MSBuilderGUI\\").append(EntityManager.getDirectoryName()).append(" && ")
                    .append("dir /s /B *.java > sources.txt && ")
                    .append("javac -cp \"mysql-connector-java-5.1.11-bin.jar;javax.mail.jar;activation.jar\" @sources.txt -d out && ")
                    .append("jar cfm ").append(EntityManager.getProjectName()).append(".jar").append(" Manifest.txt -C out/ . && ")
                    .append("java -jar ").append(EntityManager.getProjectName()).append(".jar && ")
                    .append("exit");
            System.out.println(command.toString());
            try
            {
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+command.toString()+"\"");
                try
                {
                    Thread.sleep(7500);
                } catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
            } catch (IOException ex)
            {
                ex.printStackTrace();
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

    public static void changeSystemCreationProcessPanel(int code)
    {
        switch (code)
        {
            case 1: systemCreationCardPanel.add("Add Datafields",new AddDataFields());
                    systemCreationCardLayout.show(systemCreationCardPanel,"Add Datafields");
                    break;
            case 2: systemCreationCardLayout.show(systemCreationCardPanel,"Add Entities");
                    break;
            case 3: systemCreationCardLayout.show(systemCreationCardPanel,"Define Entity Panels");
                    placeFiller.setText("Step 2 of 4");
                    break;
            case 4: systemCreationCardLayout.show(systemCreationCardPanel,"Define About us Panel");
                    placeFiller.setText("Step 3 of 4");
                    break;
            case 5: systemCreationCardLayout.show(systemCreationCardPanel, "Define Contact us Panel");
                    placeFiller.setText("Step 3 of 4");
                    break;
            case 6: systemCreationCardLayout.show(systemCreationCardPanel, "Finish Page");
                    placeFiller.setText("Step 4 of 4");
                    startYourSystembtn.setEnabled(true);
                    break;
        }
    }
}

/* PROGRAM OUTPUT
 */
