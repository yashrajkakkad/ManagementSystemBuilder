package GUIGeneration;

import GUI.DefineContactUsPanel;
import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

public class MainFrame {

    private final PicoWriter w;
    private final String TITLEFONT = "Arial";
    private final int TITLEFONTSIZE = 24;

    public MainFrame() throws IOException {
        w = new PicoWriter();
        initialize();
        generateConstructor();
//        generateTitle();
        generateMain();
        finish();
        generateCode();
    }

    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("import java.sql.SQLException;");
        w.writeln("");
        w.writeln_r("public class MainFrame extends JFrame{");
        w.writeln("");
    }

    private void generateConstructor() {
        w.writeln_r("public MainFrame() throws SQLException{");
        w.writeln("setLayout(new BorderLayout());");
        generateTitle();
        w.writeln("setLocationRelativeTo(null);");
        w.writeln("pack();");
        w.writeln("setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);");
        generateCentralLayout();
        w.writeln_l("}");
        w.writeln("");
    }

    private void generateTitle() {
        w.writeln("setTitle(\"" + EntityManager.getProjectName() + "\");");
        w.writeln("JLabel titleLabel = new JLabel();");
        w.writeln("titleLabel.setText(\"" + EntityManager.getProjectName()
                + "\");");
        w.writeln("titleLabel.setFont(new Font(\"" + TITLEFONT + "\", Font.BOLD,"
                + TITLEFONTSIZE + "));");
        w.writeln("add(titleLabel, BorderLayout.NORTH);");
    }

    private void generateCentralLayout() {
        w.writeln("JPanel centralPanel = new JPanel();");
        w.writeln("add(centralPanel,BorderLayout.CENTER);");
        w.writeln("CardLayout cl = new CardLayout();");
        w.writeln("JPanel homePanel = new JPanel();");
        w.writeln("centralPanel.setLayout(cl);");
        w.writeln("centralPanel.add(new JScrollPane(homePanel),\"homePanel\");");
        w.writeln("homePanel.setLayout(new GridLayout(0,4,10,10));");
        int count = 0;
        Entity previousEntity = PanelManager.getEntityPanels().get(0).getKey();
        w.writeln("JLabel " + PanelManager.getEntityPanels().get(0)
                .getKey().getEntityName() + "Label = new JLabel(\""
                + PanelManager.getEntityPanels().get(0).getKey().getEntityName()
                + "\");");
        w.writeln("homePanel.add(" + PanelManager.getEntityPanels().get(0)
                .getKey().getEntityName() + "Label);");
        w.writeln("homePanel.add(new JLabel());");
        w.writeln("homePanel.add(new JLabel());");
        w.writeln("homePanel.add(new JLabel());");
//        javax.swing.JButton button1 = new javax.swing.JButton();
        Iterator<Pair<Entity, Integer>> itr = PanelManager.getEntityPanels().iterator();
        while (itr.hasNext()) {
            Pair<Entity, Integer> tempPair = itr.next();
            w.writeln("centralPanel.add(new "
                    + codeToStringCaps(tempPair.getValue())
                    + tempPair.getKey().getEntityName() + "Panel(), \""
                    + codeToStringCaps(tempPair.getValue())
                    + tempPair.getKey().getEntityName() + "\");");
        }
        itr = PanelManager.getEntityPanels().iterator();
        while (itr.hasNext()) {
            Pair<Entity, Integer> tempPair = itr.next();
            if (previousEntity.getEntityName().equals(tempPair.getKey().getEntityName())) {
                count++;
            } else {
                while (count < 4) {
                    w.writeln("homePanel.add(new JLabel(\"\"));");
                    count++;
                }
                w.writeln("JLabel " + tempPair.getKey().getEntityName()
                        + "Label = new JLabel(\"" + tempPair.getKey().getEntityName() + "\");");
                w.writeln("homePanel.add(" + tempPair.getKey().getEntityName() + "Label);");
                w.writeln("homePanel.add(new JLabel(\"\"));");
                w.writeln("homePanel.add(new JLabel(\"\"));");
                w.writeln("homePanel.add(new JLabel(\"\"));");
                count = 1;
            }
            w.writeln("JButton " + codeToString(tempPair.getValue())
                    + tempPair.getKey().getEntityName()
                    + "Button = new JButton(\"" + codeToStringCaps(tempPair.getValue()) + "\");");
            w.writeln("homePanel.add(" + codeToString(tempPair.getValue()) + tempPair.getKey().getEntityName() + "Button);");
            w.writeln_r(codeToString(tempPair.getValue())
                    + tempPair.getKey().getEntityName()
                    + "Button.addActionListener((e) -> {");
            w.writeln("cl.show(centralPanel,\""
                    + codeToStringCaps(tempPair.getValue())
                    + tempPair.getKey().getEntityName() + "\");");
            w.writeln_l("});");
            previousEntity = tempPair.getKey();
        }
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("add(bottomPanel,BorderLayout.SOUTH);");
        w.writeln("bottomPanel.setLayout(new GridLayout(1,3,10,10));");
        w.writeln("JButton backToHomebtn = new JButton(\"Back to Home\");");
        w.writeln_r("backToHomebtn.addActionListener(e -> {");
        w.writeln("cl.show(centralPanel, \"homePanel\");");
        w.writeln_l("});");
        w.writeln("bottomPanel.add(backToHomebtn);");
        w.writeln("centralPanel.add(new AboutPanel(), \"aboutUsPanel\");");
        w.writeln("JButton aboutUsbtn = new JButton(\"About Us\");");
        w.writeln_r("aboutUsbtn.addActionListener(e -> {");
        w.writeln("cl.show(centralPanel, \"aboutUsPanel\");");
        w.writeln_l("});");
        w.writeln("bottomPanel.add(aboutUsbtn);");
        if (DefineContactUsPanel.isAccessed())
        {
            w.writeln("centralPanel.add(new ContactPanel(), \"contactUsPanel\");");
            w.writeln("JButton contactUsbtn = new JButton(\"Contact Us\");");
            w.writeln_r("contactUsbtn.addActionListener(e -> {");
            w.writeln("cl.show(centralPanel, \"contactUsPanel\");");
            w.writeln_l("});");
            w.writeln("bottomPanel.add(contactUsbtn);");
        }
    }

    private void generateMain() {
        w.writeln_r("public static void main(String[] args) throws SQLException{");
        w.writeln("MainFrame mainFrame = new MainFrame();");
        w.writeln("mainFrame.setVisible(true);");
        w.writeln_l("}");
    }

    private void generateCode() throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName()
                + "\\MainFrame.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }

    private void finish() {
        w.writeln_l("}");
    }

    private String codeToString(Integer code) {
        switch (code) {
            case 0:
                return "add";
            case 1:
                return "update";
            case 2:
                return "delete";
            case 3:
                return "view";
        }
        return "";
    }

    private String codeToStringCaps(Integer code) {
        switch (code) {
            case 0:
                return "Add";
            case 1:
                return "Update";
            case 2:
                return "Delete";
            case 3:
                return "View";
        }
        return "";
    }
}
