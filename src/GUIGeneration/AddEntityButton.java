package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddEntityButton {

    private final Entity entity;
    private final PicoWriter w;

    public AddEntityButton(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();
        initialize();
        generateConstructor();
        finish();
        generateCode();
    }

    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class Add" + entity.getEntityName() + "Button"
                + "Panel extends JPanel {");
        w.writeln("");
    }
    
    private void generateConstructor() {
        w.writeln_r("public Add" + entity.getEntityName() + "ButtonPanel {");
        generateButton();
        w.writeln_l("}");
    }

    private void generateButton() {
        w.writeln("JButton addButton = new JButton(\"Add " 
                + entity.getEntityName() + "\");");
        w.writeln_r("addButton.setOnAction((e) -> {");
        StringBuilder insertQuery = new StringBuilder("INSERT INTO tbl_");
        insertQuery.append(entity.getEntityName().toLowerCase())
                .append(" VALUES (");
        entity.getEntityMembers().forEach((entityMember) -> {
            if(entityMember.getKey().equals("String") 
                    || entityMember.getKey().equals("char"))
                insertQuery.append('\'').append(entityMember.getValue())
                        .append('\'').append(", ");
            else {
                insertQuery.append(entityMember.getValue()).append(", ");                
            }
        });
        
        insertQuery.deleteCharAt(insertQuery.length()-2);
        insertQuery.append(");");
        w.writeln("String insertQuery = \"" + insertQuery.toString() + "\";");
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(insertQuery);");
        w.writeln_r("if(i==1) {");
        w.writeln("JOptionPane.showMessageDialog(this,\"" 
                + entity.getEntityName() + " added successfully!\");");
        w.writeln_lr("} else if {");
        w.writeln("JOptionPane.showMessageDialog(this,"
                + "\"Unexpected error occured. It might be due to "
                + "faulty internet or duplication in your primary key!\");");
        w.writeln_l("}");
        w.writeln_l("});");
    }

    private void finish() {
        w.writeln_l("}");
    }

    private void generateCode() throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName()
                + "\\Add" + entity.getEntityName() + "ButtonPanel.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }

}
