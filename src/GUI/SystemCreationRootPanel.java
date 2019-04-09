package GUI;

import javax.swing.*;
import java.awt.*;

public class SystemCreationRootPanel extends JPanel
{
    private static CardLayout systemCreationCardLayout = new CardLayout();
    private static JPanel systemCreationCardPanel;

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
        systemCreationCardPanel.add("Define Add Entity Panels", new DefineEntityPanels());
        systemCreationCardPanel.add("Define About us Panel", new DefineAboutPanel());
        systemCreationCardPanel.add("Define Contact us Panel", new DefineContactUsPanel());
        systemCreationCardLayout.show(systemCreationCardPanel,"Add Entities");

        JPanel bottomPanel = new JPanel();
        add(bottomPanel,BorderLayout.PAGE_END);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(103, 228, 255));
        GridBagConstraints bc = new GridBagConstraints();

        bc.gridx = 0;
        bc.gridy = 0;
        bc.insets = new Insets(10,10,10,10);
        JLabel placeFiller = new JLabel("System being created.....");
        placeFiller.setFont(new Font("Century Gothic",Font.PLAIN,24));
        bottomPanel.add(placeFiller,bc);
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
            case 3: systemCreationCardLayout.show(systemCreationCardPanel,"Define Add Entity Panels");
                    break;
            case 4: systemCreationCardLayout.show(systemCreationCardPanel,"Define About us Panel");
                    break;
            case 5: systemCreationCardLayout.show(systemCreationCardPanel, "Define Contact us Panel");
                    break;
        }
    }
}

/* PROGRAM OUTPUT
 */
