/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


package GUI;

import CodeGeneration.Entity;
import GUIGeneration.*;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefineEntityPanels extends JPanel
{
    private static ArrayList<JCheckBox[]> checkBoxes = new ArrayList<>();
    private static ArrayList<Entity> entityList = new ArrayList<>();
    private static int entityCount = 0;

    private static JPanel defineAddEntityPanel = new JPanel(new GridBagLayout());
    private static GridBagConstraints c = new GridBagConstraints();

    public DefineEntityPanels()
    {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("Define the GUI panels here");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);

        defineAddEntityPanel.setBackground(Color.WHITE);
        add(new JScrollPane(defineAddEntityPanel),BorderLayout.CENTER);
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10,500,30,1);

        c.gridx = 1;
        c.gridy = 0;
        JLabel entityListLabel = new JLabel("Entity List");
        entityListLabel.setFont(new Font("Century Gothic",Font.PLAIN,52));
        defineAddEntityPanel.add(entityListLabel,c);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel,BorderLayout.PAGE_END);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(103, 228, 255));
        GridBagConstraints bc = new GridBagConstraints();

        bc.gridx = 0;
        bc.gridy = 0;
        bc.insets = new Insets(10,10,10,10);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        bottomPanel.add(submitButton,bc);

        submitButton.addActionListener(e ->
        {
            ArrayList<Pair<Entity, Integer>> entityPanels = new ArrayList<>();

            for (int i=0; i<checkBoxes.size(); ++i)
            {
                JCheckBox[] arr = checkBoxes.get(i);
                for (int j=0; j<arr.length; ++j)
                {
                    if (arr[j].isSelected())
                    {
                        switch(arr[j].getName())
                        {
                            case "1": entityPanels.add(new Pair(entityList.get(i),0));
                                      break;
                            case "2": entityPanels.add(new Pair(entityList.get(i),1));
                                      break;
                            case "3": entityPanels.add(new Pair(entityList.get(i),2));
                                      break;
                            case "4": entityPanels.add(new Pair(entityList.get(i),3));
                                      break;
                        }
                    }
                }

            }
            PanelManager.setEntityPanels(entityPanels);
            JOptionPane.showMessageDialog(null, "DONE! Your GUI Panels are created!");
            SystemCreationRootPanel.changeSystemCreationProcessPanel(4);
        });

        bc.gridx++;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        cancelButton.addActionListener(e ->
        {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure to cancel? All the panels will be reset.", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION)
            {
                HomePageFrame.changeRootPanel(2);
            }
        });
        bottomPanel.add(cancelButton,bc);

    }

    public static void addLine(Entity entity)
    {
        entityList.add(entity);
        c.gridy++;
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;

        JLabel numberLabel = new JLabel((entityCount+1)+".");
        numberLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineAddEntityPanel.add(numberLabel,c);

        c.gridx++;
        JLabel entityNameLabel = new JLabel(entity.getEntityName());
        entityNameLabel.setFont(new Font("Century Gothic",Font.PLAIN,36));
        defineAddEntityPanel.add(entityNameLabel,c);

        c.gridx++;
        JCheckBox[] arr = new JCheckBox[4];
        arr[0] = new JCheckBox("Add");
        arr[1] = new JCheckBox("Update");
        arr[2] = new JCheckBox("Delete");
        arr[3] = new JCheckBox("View");
        for (int i=0; i<arr.length;++i)
        {
            arr[i].setFont(new Font("Century Gothic",Font.PLAIN,36));
            arr[i].setName((i+1)+"");
            defineAddEntityPanel.add(arr[i],c);
            c.gridx++;
        }
        checkBoxes.add(entityCount,arr);

        ++entityCount;
    }


}

/* PROGRAM OUTPUT
 */
