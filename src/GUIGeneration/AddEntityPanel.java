package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddEntityPanel {

    private final Entity entity;
    private final PicoWriter w;

    public AddEntityPanel(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();
        initialize();
        generateLabels();
        generateTextFields();
        generateConstructor();
        finish();
        generateCode();
    }

    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class Add" + entity.getEntityName()
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

    private void generateAddComponents() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("add(" + entityMember.getValue() + "Label);");
            w.writeln("add(" + entityMember.getValue() + "TextField);");
        });        
    }
    
    private void generateConstructor() {
        w.writeln_r("public Add" + entity.getEntityName() + "Panel() {");
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
                + "\\Add" + entity.getEntityName() + "Panel.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }
}
