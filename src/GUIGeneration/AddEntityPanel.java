package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddEntityPanel {

    private Entity entity;
    private PicoWriter w;

    public AddEntityPanel(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();
        initialize();
        generateConstructor();
        finish();
        generateCode();
    }

    public void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class Add" + entity.getEntityName() 
                + "Panel extends JPanel {");                
        w.writeln("");
    }

    public void generateConstructor() {
        w.writeln_r("public Add" + entity.getEntityName() + "Panel() {");
        w.writeln("setLayout(new GridLayout(" + entity.getEntityMembers().size() 
                + "," + entity.getEntityMembers().size() + ",10,10));");
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("JLabel " + entityMember.getValue() 
                    + "Label = new JLabel(\""+ entityMember.getValue() +"\");");
        });
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("JTextField " + entityMember.getValue() 
                    + "TextField = new JTextField();");
        });
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("add(" + entityMember.getValue() + "Label);");
            w.writeln("add(" + entityMember.getValue() + "TextField);");
        });        
        w.writeln_l("}");
    }
    
    public void finish() {
        w.writeln_l("}");
    }

    public void generateCode() throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName() 
                + "\\Add" + entity.getEntityName() + "Panel.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();        
    }
}
