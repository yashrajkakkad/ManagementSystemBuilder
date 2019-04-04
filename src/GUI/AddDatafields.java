package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDatafields extends JPanel
{
    private int datafieldCount = 1;
    private int rowCount = -1;
    private JLabel[] labels = new JLabel[10];
    private JTextField[] textFields = new JTextField[10];
    private JComboBox<String>[] comboBoxes = new JComboBox[10];
    private JButton[] addButtons = new JButton[10];
    private JButton[] removeButtons = new JButton[10];

    public AddDatafields()
    {
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(gridBagLayout);
        add(titlePanel,BorderLayout.PAGE_START);
        GridBagConstraints c = new GridBagConstraints();
        setBackground(new Color(255,255,255));
        c.gridx = 0;
        c.gridy = 2;
        JLabel titleLabel = new JLabel("Add Datafields for the given entity");
        titleLabel.setFont(new Font("Century Gothic",Font.PLAIN,60));
        titlePanel.setBackground(new Color(103, 228, 255));
        titlePanel.add(titleLabel,c);

        JPanel addDatafieldPanel = new JPanel(gridBagLayout);
        addDatafieldPanel.setBackground(Color.WHITE);
        add(addDatafieldPanel,BorderLayout.CENTER);
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.NORTH;

        c.gridy = 0;
        c.gridx = 1;
        JLabel datafieldNameLabel = new JLabel("Datafield Name");
        datafieldNameLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(datafieldNameLabel,c);

        c.gridx++;
        JLabel datatypeLabel = new JLabel("Datatype");
        datatypeLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(datatypeLabel,c);

        c.gridy++;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;

        JLabel datafieldCountLabel = new JLabel("Datafield "+datafieldCount+":");
        datafieldCountLabel.setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(datafieldCountLabel,c);

        c.gridx++;
        JTextField datafieldName = new JTextField(15);
        datafieldName.setFont(new Font("Century Gothic",Font.PLAIN,18));
        addDatafieldPanel.add(datafieldName,c);

        c.gridx++;
        JComboBox<String> datatypeComboBox = new JComboBox<>();
        datatypeComboBox.addItem("Choose a suitable datatype");
        datatypeComboBox.addItem("Integer");
        datatypeComboBox.addItem("Floating Point Decimal");
        datatypeComboBox.addItem("String of characters");
        datatypeComboBox.addItem("Single character");
        datatypeComboBox.setFont(new Font("Century Gothic",Font.PLAIN,18));
        addDatafieldPanel.add(datatypeComboBox,c);

        c.gridx++;
        JButton addButton = new JButton("Add Datafield");
        addButton.setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(addButton,c);
        c.gridy++;
        ++datafieldCount;

        ActionListener removeRowEvent = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (rowCount > -1)
                {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure to delete this datafield?", "Warning",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (response == JOptionPane.YES_OPTION)
                    {
                        addDatafieldPanel.remove(labels[rowCount]);
                        addDatafieldPanel.remove(textFields[rowCount]);
                        addDatafieldPanel.remove(comboBoxes[rowCount]);
                        addDatafieldPanel.remove(addButtons[rowCount]);
                        addDatafieldPanel.remove(removeButtons[rowCount]);
                        --rowCount;
                        --datafieldCount;
                        addDatafieldPanel.revalidate();
                        addDatafieldPanel.repaint();
                    }
                }
            }
        };

        ActionListener addRowEvent = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean flag = true;
                if (datafieldName.getText().equals("") || datatypeComboBox.getSelectedItem().equals("Choose a suitable datatype"))
                {
                    JOptionPane.showMessageDialog(null,"Please fill out the required fields");
                    flag = false;
                }
                else if (rowCount > -1)
                {
                    for (int i=0;i<=rowCount;++i)
                    {
                        if (textFields[i].getText().equals("") || comboBoxes[i].getSelectedItem().equals("Choose a suitable datatype"))
                        {
                            JOptionPane.showMessageDialog(null,"Please fill out the required fields");
                            flag = false;
                        }
                    }
                }
                if (flag)
                {
                    ++rowCount;
                    c.gridy++;
                    c.fill = GridBagConstraints.BOTH;

                    c.gridx = 0;
                    labels[rowCount] = new JLabel("Datafield "+datafieldCount+":");
                    labels[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addDatafieldPanel.add(labels[rowCount],c);

                    c.gridx++;
                    textFields[rowCount] = new JTextField(15);
                    textFields[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,18));
                    addDatafieldPanel.add(textFields[rowCount],c);

                    c.gridx++;
                    comboBoxes[rowCount] = new JComboBox<>();
                    comboBoxes[rowCount].addItem("Choose a suitable datatype");
                    comboBoxes[rowCount].addItem("Integer");
                    comboBoxes[rowCount].addItem("Floating Point Decimal");
                    comboBoxes[rowCount].addItem("String of characters");
                    comboBoxes[rowCount].addItem("Single character");
                    comboBoxes[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,18));
                    addDatafieldPanel.add(comboBoxes[rowCount],c);

                    c.gridx++;
                    addButtons[rowCount] = new JButton("Add Datafield");
                    addButtons[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addButtons[rowCount].addActionListener(this::actionPerformed);
                    addDatafieldPanel.add(addButtons[rowCount],c);

                    c.gridx++;
                    removeButtons[rowCount] = new JButton("Remove Datafield");
                    removeButtons[rowCount].setFont(new Font("Century Gothic",Font.PLAIN,24));
                    removeButtons[rowCount].addActionListener(removeRowEvent);
                    addDatafieldPanel.add(removeButtons[rowCount],c);

                    c.gridy++;
                    ++datafieldCount;
                    addDatafieldPanel.revalidate();
                    addDatafieldPanel.repaint();
                }
            }
        };
        addButton.addActionListener(addRowEvent);


        JPanel bottomPanel = new JPanel();
        add(bottomPanel,BorderLayout.PAGE_END);
        bottomPanel.setLayout(gridBagLayout);
        bottomPanel.setBackground(new Color(103, 228, 255));
        GridBagConstraints bc = new GridBagConstraints();

        bc.gridx = 0;
        bc.gridy = 0;
        bc.insets = new Insets(10,10,10,10);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        bottomPanel.add(submitButton,bc);

        bc.gridx++;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure to cancel? All the datafields will be reset.", "Warning",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION)
                {
                    SystemCreationRootPanel.changeSystemCreationProcessPanel(2);
                }
            }
        });
        bottomPanel.add(cancelButton,bc);

        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setBackground(new Color(113, 211, 238));
        add(westPanel,BorderLayout.LINE_START);
        GridBagConstraints wc = new GridBagConstraints();
        wc.anchor = GridBagConstraints.CENTER;
        wc.insets = new Insets(30,15,30,15);
        wc.gridy = 0;
        JLabel datafieldInfo = new JLabel("INFO");
        datafieldInfo.setFont(new Font("Century Gothic",Font.PLAIN,30));
        westPanel.add(datafieldInfo,wc);

        wc.insets = new Insets(30,15,3,15);
        wc.gridy++;
        JLabel datafieldDetails = new JLabel("<html>Datafield blah blah<br>Datafield blah blah<br>Datafield blah blah<br>Datafield blah blah<br>Datafield blah blah");
        datafieldDetails.setFont(new Font("Century Gothic",Font.PLAIN,24));
        westPanel.add(datafieldDetails,wc);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        eastPanel.setBackground(new Color(113, 211, 238));
        GridBagConstraints ec = new GridBagConstraints();
        add(eastPanel,BorderLayout.LINE_END);
        ec.anchor = GridBagConstraints.CENTER;
        ec.insets = new Insets(30,15,30,15);
        ec.gridy = 0;
        JLabel datatypeInfo = new JLabel("What is a datatype?");
        datatypeInfo.setFont(new Font("Century Gothic",Font.PLAIN,30));
        eastPanel.add(datatypeInfo,ec);

        ec.insets = new Insets(30,15,3,15);
        ec.gridy++;
        JLabel datatypeDetails = new JLabel("<html>Datatype blah blah<br>Integer blah blah<br>Floating point number blah blah<br>String of characters blah blah<br>Character blah blah");
        datatypeDetails.setFont(new Font("Century Gothic",Font.PLAIN,24));
        eastPanel.add(datatypeDetails,ec);
    }
}

/* PROGRAM OUTPUT
 */
