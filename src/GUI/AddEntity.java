package GUI;
/*
 * @author Prayag Savsani AU1841035
 * @version dd/03/2019
 * Description: write purpose/ description of the program here
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEntity extends JPanel
{
    private int entityCount = 1;
    private int rowCount = 0;
    private static String currentEntityName;
    private static String currentDefineButton;
    private JLabel[] labels = new JLabel[11];
    private static JTextField[] entities = new JTextField[11];
    private JButton[] addButtons = new JButton[11];
    private static JButton[] defineButtons = new JButton[11];
    private JButton[] removeButtons = new JButton[11];

    public static String getCurrentEntityName() {
        return currentEntityName;
    }

    public static void changeButtonName()
    {
        defineButtons[Integer.parseInt(currentDefineButton)].setName("Button Pressed");
    }

    public AddEntity()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0;
        tc.gridy = 2;
        JLabel titleLabel = new JLabel("Add Entities here");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,48));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,tc);
        tc.gridy++;
        tc.gridx = 0;
        JLabel primaryKeyNote = new JLabel("Note: The first entity that you add will be automatically set to be the primary key for your database");
        primaryKeyNote.setFont(new Font("Century Gothic",Font.PLAIN,24));
        titlePanel.add(primaryKeyNote,tc);

        JPanel addEntityPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        addEntityPanel.setBackground(Color.WHITE);
        add(addEntityPanel,BorderLayout.CENTER);
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.NORTH;

        c.gridy = 0;
        c.gridx = 1;
        JLabel entityNameLabel = new JLabel("Entity Name");
        entityNameLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        addEntityPanel.add(entityNameLabel,c);

        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        labels[0] = new JLabel("Entity no. "+entityCount+":");
        labels[0].setFont(new Font("Century Gothic",Font.PLAIN,24));
        addEntityPanel.add(labels[0],c);

        c.gridx++;
        entities[0] = new JTextField(15);
        entities[0].setFont(new Font("Century Gothic",Font.PLAIN,18));
        addEntityPanel.add(entities[0],c);

        c.gridx++;
        addButtons[0] = new JButton("Add Entity");
        addButtons[0].setFont(new Font("Century Gothic",Font.PLAIN,24));
        addEntityPanel.add(addButtons[0],c);
        ++entityCount;

        c.gridx++;
        defineButtons[0] = new JButton("Define Datafields");
        defineButtons[0].setFont(new Font("Century Gothic",Font.PLAIN,24));
        defineButtons[0].setName("0");
        addEntityPanel.add(defineButtons[0],c);

        c.gridy++;

        ActionListener defineDatafields = e ->
        {
            boolean flag = true;
            for (int i=0;i<=rowCount;++i)
            {
                if (entities[i].getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please fill out the required fields");
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                currentEntityName = entities[Integer.parseInt(((JButton)e.getSource()).getName())].getText();
                currentDefineButton = ((JButton)e.getSource()).getName();
                SystemCreationRootPanel.changeSystemCreationProcessPanel(1);
            }
        };
        defineButtons[0].addActionListener(defineDatafields);

        ActionListener removeRowEvent = e ->
        {
            if (rowCount > -1)
            {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure to delete this datafield?", "Warning",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION)
                {
                    addEntityPanel.remove(labels[rowCount]);
                    addEntityPanel.remove(entities[rowCount]);
                    addEntityPanel.remove(addButtons[rowCount]);
                    addEntityPanel.remove(defineButtons[rowCount]);
                    addEntityPanel.remove(removeButtons[rowCount]);
                    --rowCount;
                    --entityCount;
                    addEntityPanel.revalidate();
                    addEntityPanel.repaint();
                }
            }
        };

        ActionListener addRowEvent = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean flag = true;
                    for (int i=0;i<=rowCount;++i)
                    {
                        if (entities[i].getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(null,"Please fill out the required fields");
                            flag = false;
                        }
                    }
                if (flag)
                {
                    ++rowCount;
                    c.gridy++;
                    c.fill = GridBagConstraints.BOTH;

                    c.gridx = 0;
                    labels[rowCount] = new JLabel("Entity No. "+entityCount+":");
                    labels[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addEntityPanel.add(labels[rowCount],c);

                    c.gridx++;
                    entities[rowCount] = new JTextField(15);
                    entities[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,18));
                    addEntityPanel.add(entities[rowCount],c);

                    c.gridx++;
                    addButtons[rowCount] = new JButton("Add Entity");
                    addButtons[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addButtons[rowCount].addActionListener(this::actionPerformed);
                    addEntityPanel.add(addButtons[rowCount],c);

                    c.gridx++;
                    defineButtons[rowCount] = new JButton("Define Datafields");
                    defineButtons[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    defineButtons[rowCount].setName(String.valueOf(rowCount));
                    defineButtons[rowCount].addActionListener(defineDatafields);
                    addEntityPanel.add(defineButtons[rowCount],c);

                    c.gridx++;
                    removeButtons[rowCount] = new JButton("Remove Entity");
                    removeButtons[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    removeButtons[rowCount].addActionListener(removeRowEvent);
                    addEntityPanel.add(removeButtons[rowCount],c);

                    c.gridy++;
                    ++entityCount;
                    addEntityPanel.revalidate();
                    addEntityPanel.repaint();
                }
            }
        };
        addButtons[0].addActionListener(addRowEvent);

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
            boolean flag = true;
            for (int i=0; i<=rowCount; ++i)
            {
                if (!defineButtons[i].getName().equals("Button Pressed"))
                {
                    flag = false;
                    JOptionPane.showMessageDialog(null, "Please define datafields for all entities before submitting.");
                    break;
                }
            }
            if (flag)
            {
                JOptionPane.showMessageDialog(null, "DONE! Your system has been created!");
            }
        });

        bc.gridx++;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        cancelButton.addActionListener(e ->
        {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure to cancel? All the entities will be reset.", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION)
            {
                HomePageFrame.changeRootPanel(2);
            }
        });
        bottomPanel.add(cancelButton,bc);

        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setBackground(new Color(113, 211, 238));
        add(westPanel,BorderLayout.LINE_START);
        GridBagConstraints wc = new GridBagConstraints();
        wc.anchor = GridBagConstraints.CENTER;
        wc.insets = new Insets(30,30,30,30);
        wc.gridy = 0;
        JLabel entityInfo = new JLabel("INFO");
        entityInfo.setFont(new Font("Century Gothic",Font.PLAIN,30));
        westPanel.add(entityInfo,wc);

        wc.insets = new Insets(30,50,3,50);
        wc.gridy++;
        JLabel entityDetails = new JLabel("<html>Entity blah blah<br>Entity blah blah<br>Entity blah blah<br>Entity blah blah<br>Entity blah blah");
        entityDetails.setFont(new Font("Century Gothic",Font.PLAIN,30));
        westPanel.add(entityDetails,wc);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        eastPanel.setBackground(new Color(113, 211, 238));
        GridBagConstraints ec = new GridBagConstraints();
        add(eastPanel,BorderLayout.LINE_END);
        ec.anchor = GridBagConstraints.CENTER;
        ec.insets = new Insets(30,30,30,30);
        ec.gridy = 0;
        JLabel primaryKeyInfo = new JLabel("What is a primary key?");
        primaryKeyInfo.setFont(new Font("Century Gothic",Font.PLAIN,30));
        eastPanel.add(primaryKeyInfo,ec);

        ec.insets = new Insets(30,50,3,50);
        ec.gridy++;
        JLabel primaryKeyDetails = new JLabel("<html>Primary Key blah blah<br>Primary Key blah blah<br>Primary Key blah blah<br>Primary Key blah blah<br>Primary Key blah blah");
        primaryKeyDetails.setFont(new Font("Century Gothic",Font.PLAIN,30));
        eastPanel.add(primaryKeyDetails,ec);
    }
}

/* PROGRAM OUTPUT
 */
