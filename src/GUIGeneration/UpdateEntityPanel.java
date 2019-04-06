package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

public class UpdateEntityPanel {

    private final Entity entity;
    private final PicoWriter w;

    public UpdateEntityPanel(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();
        initialize();
        generateLabels();
        generateTextFields();
        generateSearchButton();
        generateSubPanel();
        generateConstructor();
        finish();
        generateCode();
    }

    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class Update" + entity.getEntityName()
                + "Panel extends JPanel {");
        w.writeln("");
    }

    private void generateLabels() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JLabel " + entityMember.getValue()
                    + "Label = new JLabel(\"" + entityMember.getValue() + "\");");
        });
    }

    private void generateTextFields() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JTextField " + entityMember.getValue()
                    + "TextField = new JTextField();");
        });
    }

    private void generateSearchButton() {
        w.writeln("private JButton btSearch = new JButton(\"btSearch\");");
    }

    private void generateSubPanel() {
        w.writeln("JPanel subPanel = new JPanel();");
    }

    private void generateAddComponents() {
//        entity.getEntityMembers().forEach((entityMember) -> {
//            w.writeln("add(" + entityMember.getValue() + "Label);");
//            w.writeln("add(" + entityMember.getValue() + "TextField);");
//        });
        Iterator<Pair<String, String>> itr
                = entity.getEntityMembers().iterator();
        w.writeln("add(" + itr.next().getValue() + "Label);");
        w.writeln("subPanel.add(" + itr.next().getValue() + "TextField);");
        w.writeln("subPanel.add(btSearch);");
        w.writeln("add(subPanel);");
//        w.writeln("add(\"new JPanel( + "\");");
//        w.writeln("add(" + entityMember.getValue() + "TextField);");
        while (itr.hasNext()) {
            w.writeln("add(" + itr.next().getValue() + "Label);");
            w.writeln("add(" + itr.next().getValue() + "TextField);");
        }
    }

    private void generateBtSearchActionListener() {
        w.writeln_r("btSearch.setOnAction((e) -> {");
        w.writeln_r("if(" + entity.getEntityMembers().get(0).getValue()
                + "TextField.getText().equals(\"\")) {");
        w.writeln("JOptionPane.showMessageDialog(this,"
                + "\"Please enter a valid value to search!\")");
        w.writeln_lr("} else {");
        w.writeln("StringBuilder selectQuery = new StringBuilder(\"SELECT * FROM tbl_\")");
        w.writeln("selectQuery.append(\"" + entity.getEntityName().toLowerCase()
                + "\")");
        w.writeln("selectQuery.append(\"WHERE '"
                + entity.getEntityMembers().get(0).getValue() + "' = \")");
        if (entity.getEntityMembers().get(0).getKey().equals("int")
                || entity.getEntityMembers().get(0).getKey().equals("double")) {
            w.writeln("selectQuery.append("
                    + entity.getEntityMembers().get(0).getValue()
                    + "TextField.getText()');");
        } else {
            w.writeln("selectQuery.append('"
                    + entity.getEntityMembers().get(0).getValue()
                    + "TextField.getText()';);");            
        }
    //More code yet to come       
        w.writeln_l("}");
        w.writeln_l("});");
    }

    private void generateConstructor() {
        w.writeln_r("public Update" + entity.getEntityName() + "Panel() {");
        w.writeln("setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        w.writeln_l("}");
    }

    private void finish() {
        w.writeln_l("}");
    }

    private void generateCode() throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName()
                + "\\Update" + entity.getEntityName() + "Panel.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }
}
