package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CodeGeneration.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class AddDataFields extends JPanel
{
    private int datafieldCount = 1;
    private int rowCount = 0;
    private ArrayList<JLabel> labels = new ArrayList<>();
    private ArrayList<JTextField> dataFields = new ArrayList<>();
    private ArrayList<JComboBox<String>> dataTypes = new ArrayList<>();
    private ArrayList<JButton> addButtons = new ArrayList<>();
    private ArrayList<JButton> removeButtons = new ArrayList<>();

    public AddDataFields()
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
        JScrollPane scrollPane = new JScrollPane(addDatafieldPanel);
        add(scrollPane,BorderLayout.CENTER);
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

        labels.add(0,new JLabel("Datafield "+datafieldCount+":"));
        labels.get(0).setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(labels.get(0),c);

        c.gridx++;
        dataFields.add(0,new JTextField(15));
        dataFields.get(0).setFont(new Font("Century Gothic",Font.PLAIN,18));
        addDatafieldPanel.add(dataFields.get(0),c);

        c.gridx++;
        dataTypes.add(0,new JComboBox<>());
        dataTypes.get(0).addItem("Choose a suitable datatype");
        dataTypes.get(0).addItem("Integer");
        dataTypes.get(0).addItem("Floating Point Decimal");
        dataTypes.get(0).addItem("String of characters");
        dataTypes.get(0).addItem("Single character");
        dataTypes.get(0).setFont(new Font("Century Gothic",Font.PLAIN,18));
        addDatafieldPanel.add(dataTypes.get(0),c);

        c.gridx++;
        addButtons.add(0,new JButton("Add Datafield"));
        addButtons.get(0).setFont(new Font("Century Gothic",Font.PLAIN,24));
        addDatafieldPanel.add(addButtons.get(0),c);
        c.gridy++;
        ++datafieldCount;

        ActionListener removeRowEvent = e ->
        {
            if (rowCount > -1)
            {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure to delete this datafield?", "Warning",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION)
                {
                    addDatafieldPanel.remove(labels.get(rowCount));
                    addDatafieldPanel.remove(dataFields.get(rowCount));
                    addDatafieldPanel.remove(dataTypes.get(rowCount));
                    addDatafieldPanel.remove(addButtons.get(rowCount));
                    addDatafieldPanel.remove(removeButtons.get(rowCount-1));
                    --rowCount;
                    --datafieldCount;
                    addDatafieldPanel.revalidate();
                    addDatafieldPanel.repaint();
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
                    if (dataFields.get(i).getText().equals("") || dataTypes.get(i).getSelectedItem().equals("Choose a suitable datatype"))
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
                    labels.add(rowCount,new JLabel("Datafield "+datafieldCount+":"));
                    labels.get(rowCount).setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addDatafieldPanel.add(labels.get(rowCount),c);

                    c.gridx++;
                    dataFields.add(rowCount,new JTextField(15));
                    dataFields.get(rowCount).setFont(new Font("Century Gothic",Font.PLAIN,18));
                    addDatafieldPanel.add(dataFields.get(rowCount),c);

                    c.gridx++;
                    dataTypes.add(rowCount,new JComboBox<>());
                    dataTypes.get(rowCount).addItem("Choose a suitable datatype");
                    dataTypes.get(rowCount).addItem("Integer");
                    dataTypes.get(rowCount).addItem("Floating Point Decimal");
                    dataTypes.get(rowCount).addItem("String of characters");
                    dataTypes.get(rowCount).addItem("Single character");
                    dataTypes.get(rowCount).setFont(new Font("Century Gothic",Font.PLAIN,18));
                    addDatafieldPanel.add(dataTypes.get(rowCount),c);

                    c.gridx++;
                    addButtons.add(rowCount,new JButton("Add Datafield"));
                    addButtons.get(rowCount).setFont(new Font("Century Gothic",Font.PLAIN,24));
                    addButtons.get(rowCount).addActionListener(this::actionPerformed);
                    addDatafieldPanel.add(addButtons.get(rowCount),c);

                    c.gridx++;
                    removeButtons.add(rowCount-1,new JButton("Remove Datafield"));
                    removeButtons.get(rowCount-1).setFont(new Font("Century Gothic",Font.PLAIN,24));
                    removeButtons.get(rowCount-1).addActionListener(removeRowEvent);
                    addDatafieldPanel.add(removeButtons.get(rowCount-1),c);

                    c.gridy++;
                    ++datafieldCount;
                    addDatafieldPanel.revalidate();
                    addDatafieldPanel.repaint();
                }
            }
        };
        addButtons.get(0).addActionListener(addRowEvent);


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

        // TODO - Add some visual response for entities already defined.
        submitButton.addActionListener(e ->
        {
            boolean flag = true;
            for (int i=0;i<=rowCount;++i)
            {
                if (dataFields.get(i).getText().equals("") || dataTypes.get(i).getSelectedItem().equals("Choose a suitable datatype"))
                {
                    JOptionPane.showMessageDialog(null,"Please fill out all the required fields");
                    flag = false;
                }
            }
            if(flag)
            {
                ArrayList<Pair<String, String>> datafieldList = new ArrayList<>();
                for (int i = 0; i <= rowCount; i++) {
                    datafieldList.add(new Pair(comboboxToDatatype((String)dataTypes.get(i).getSelectedItem()),dataFields.get(i).getText()));
                }
                Entity entity1;
                try {
                    entity1 = new Entity(AddEntity.getCurrentEntityName(), datafieldList);
                    CRUDLogicGenerator.writeClassName(entity1);
                    CRUDLogicGenerator.generateAddEntity(entity1);
                    CRUDLogicGenerator.generateDeleteEntity(entity1);
                    CRUDLogicGenerator.generateUpdateEntity(entity1);
                    CRUDLogicGenerator.generateViewEntity(entity1);
                    CRUDLogicGenerator.endClass();
                    CRUDLogicGenerator.generateCode(entity1);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AddDataFields.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddEntity.changeButtonName();
                SystemCreationRootPanel.changeSystemCreationProcessPanel(2);
            }
        });
        
        bc.gridx++;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Century Gothic",Font.PLAIN,30));
        cancelButton.addActionListener(e ->
        {
            int response = JOptionPane.showConfirmDialog(null,
                    "Are you sure to cancel? All the datafields will be reset.", "Warning",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION)
            {
                SystemCreationRootPanel.changeSystemCreationProcessPanel(2);
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
    
    private String comboboxToDatatype(String item)
    {
        String datatype = null;
        switch(item)
        {
            case "Integer" : datatype = "int";
                             break;
            case "Floating Point Decimal" : datatype = "double";
                                            break;
            case "String of characters" : datatype = "String";
                                          break;
            case "Single character" : datatype = "char";
                                      break;                          
        }
        return datatype;
    }
}

/* PROGRAM OUTPUT
 */
