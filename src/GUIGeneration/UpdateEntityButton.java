package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateEntityButton {

    private final Entity entity;
    private final PicoWriter w;

    public UpdateEntityButton(Entity entity, PicoWriter w) {
        this.entity = entity;
        this.w = w;
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
        w.writeln_r("public class Update" + entity.getEntityName() + "Button"
                + "Panel extends JPanel {");
        w.writeln("");
    }

    private void generateConstructor() {
        w.writeln_r("public Add" + entity.getEntityName() + "ButtonPanel {");
        generateButton();
        w.writeln_l("}");
    }

    private void generateButton() {
        w.writeln("JButton addButton = new JButton(\"Update " + entity.getEntityName() + "\");");
        w.writeln_r("addButton.setOnAction((e) -> {");
        StringBuilder updateQuery = new StringBuilder("UPDATE tbl_");
        updateQuery.append(entity.getEntityName().toLowerCase()).append(" SET ");
        entity.getEntityMembers().forEach((entityMember) -> {
            updateQuery.append(entityMember.getValue()).append(" = ");
            if (entityMember.getKey().equals("String")
                    || entityMember.getKey().equals("char")) {
                //More code to come
            }
            else {
                
            }
        });
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(insertQuery);");
        w.writeln_r("if(i==1) {");
        w.writeln("JOptionPane.showMessageDialog(this,\""
                + entity.getEntityName() + " updated successfully!\");");
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
                + "\\Update" + entity.getEntityName() + "ButtonPanel.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }
}
