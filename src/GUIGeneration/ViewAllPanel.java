package GUIGeneration;

import CodeGeneration.Entity;
import java.io.IOException;
import Picocog.*;
import Utility.FileManager;

public class ViewAllPanel {

    private final Entity entity;
    private final PicoWriter w;

    public ViewAllPanel(Entity entity) throws IOException {
        this.entity = entity;
        w = new PicoWriter();
        initialize("ViewAll" + entity.getEntityName());
        w.writeln("");
        generateArrayList();
        w.writeln("");
        generateConstructor();
        finish("ViewAll" + entity.getEntityName());
    }

    private void initialize(String className) {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("import java.util.ArrayList;");
        w.writeln("");
        w.writeln_r("public class " + className + " extends JPanel {");
        w.writeln("");
    }

    private void generateArrayList() {
        w.writeln("ArrayList<ArrayList<String>> table = "
                + "new ArrayList<ArrayList<String>>();");
        //JTable to be added later since it would depend on the values here
    }

    private void getDetails() {
        w.writeln("String selectQuery = \"SELECT * FROM tbl_"
                + entity.getEntityName().toLowerCase() + "\";");
        w.writeln("DatabaseUtil.rs.executeQuery(selectQuery);");
        w.writeln_r("while(rs.next()) {");
        w.writeln("ArrayList<String> column = new ArrayList<>();");
        entity.getEntityMembers().forEach((entityMember) -> {
//            w.writeln("column.add(");
            StringBuilder addToColumn = new StringBuilder("column.add");
//                    + "(DatabaseUtil.rs.get");
            if (entityMember.getKey().equals("String")) {
                addToColumn.append("(DatabaseUtil.rs.get").append("String(\"").append(entityMember.getValue())
                        .append("\"));");
            } else {
                addToColumn.append("(String.valueOf(")
                        .append("DatabaseUtil.rs.get")
                        .append(Character.toUpperCase(entityMember.getKey().charAt(0)))
                        .append(entityMember.getKey().substring(1)).append("(\"")
                        .append(entityMember.getValue()).append("\")));");
            }
            w.writeln(addToColumn.toString());
            w.writeln("table.add(column);");
        });
        w.writeln_l("}");
        w.writeln("String[][] tableArray = table.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);");
    }
    
    private void getColumnNames() {
        w.writeln("ArrayList<String> columnNames = new ArrayList<>();");
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("columnNames.add(\"" + entityMember.getValue() + "\");");
        });
    }
    
    private void generateTable() {
        w.writeln("JTable viewTable = new JTable(tableArray,columnNames.toArray());");
    }

    private void generateConstructor() {
        w.writeln_r("public ViewAll" + entity.getEntityName() + "Panel() {");
        w.writeln("setLayout(new BorderLayout());");        
        w.writeln("add(new JLabel(\"View all " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        getDetails();
        getColumnNames();
        generateTable();
        w.writeln("add(new JScrollPane(viewTable), BorderLayout.CENTER);");
        w.writeln_l("}");
    }

    private void finish(String className) throws IOException {
        w.writeln_l("}");
        FileManager.writeCode(className, w);
    }

}
