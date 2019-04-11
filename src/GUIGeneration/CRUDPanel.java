package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import Utility.*;
import java.io.IOException;

abstract class CRUDPanel {
    protected final Entity entity;
    protected final PicoWriter w;
    
    public CRUDPanel(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();        
    }
    
    protected void initialize(String className) {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("import java.sql.SQLException;");
        w.writeln("");
        w.writeln_r("public class " + className + " extends JPanel {");
        w.writeln("");        
    }

    protected void generateLabels() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JLabel " + entityMember.getValue()
                    + "Label = new JLabel(\"" 
                    + Character.toUpperCase(entityMember.getValue().charAt(0)) 
                    + entityMember.getValue().substring(1) + "\");");
        });
    }

    protected void generateLabels2() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JLabel " + entityMember.getValue()
                    + "Label2 = new JLabel(\"" 
                    + Character.toUpperCase(entityMember.getValue().charAt(0)) 
                    + entityMember.getValue().substring(1) + "\");");
        });
        
    }
    
    protected void generateTextFields() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JTextField " + entityMember.getValue()
                    + "TextField = new JTextField(20);");
        });
    }

    protected void generateButton(String CRUD) {
        w.writeln("JButton " + CRUD
                + "Button = new JButton(\""+CRUD+" " + entity.getEntityName() 
                + "\");");
    }

    abstract protected void generateComponents();
    
    abstract protected void generateAddComponents();

    abstract protected void generateConstructor();
    
    protected void finish(String className) throws IOException {
        w.writeln_l("}");
        FileManager.writeCode(className, w);
    }
}
