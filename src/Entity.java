/* 
Generates entityName.java file with data fields, getters and setters
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import Picocog.*;
import Utility.*;

public class Entity {

    private String entityName;
    private String inheritsWhat = "";
    private String entityID;
    private ArrayList<Pair<String, String>> entityMembers;
    PicoWriter w;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ArrayList<Pair<String, String>> getEntityMembers() {
        return entityMembers;
    }

    public void setEntityMembers(ArrayList<Pair<String, String>> entityMembers) {
        this.entityMembers = entityMembers;
    }
    
    public Entity(String entityName, ArrayList<Pair<String, String>> entityMembers) throws IOException, SQLException {
        this.entityName = entityName.trim();
        this.entityMembers = entityMembers;
        w = new PicoWriter();
        String classInit = "class " + entityName;
        if (!inheritsWhat.equals("")) {
            classInit += " inherits " + inheritsWhat;
        }
        classInit += " {";
        w.writeln_r(classInit);
        addDataFields();
        generateConstructor();
        generateGetters();
        generateSetters();
        w.writeln_l("}");
        generateCode();
        createTable();
    }

    private void addDataFields() {
        entityMembers.forEach((entityMember) -> {
            w.writeln("private " + entityMember.getKey() + " " + entityMember.getValue() + ";");
        });
        w.writeln("");
    }
    
    private void generateConstructor() {
        StringBuilder arguments = new StringBuilder();
        StringBuilder values = new StringBuilder();
        entityMembers.forEach((entityMember) -> {
            arguments.append(entityMember.getKey()).append(" ")
                    .append(entityMember.getValue()).append(", ");
        });
        arguments.deleteCharAt(arguments.length()-2);
        w.writeln_r("public " + entityName + "(" + arguments.toString() + ") {");
        entityMembers.forEach((entityMember) -> {
            w.writeln("this." + entityMember.getValue() + " = " 
                    + entityMember.getValue() + ";");
        });
        w.writeln_l("}");
        w.writeln("");
    }

    private void generateGetters() {
        entityMembers.forEach((entityMember) -> {
            String memberCaps = Character.toUpperCase(entityMember.getValue()
                    .charAt(0)) + entityMember.getValue().substring(1);
            w.writeln_r(entityMember.getKey() + " get" + memberCaps + "() {");
            w.writeln("return " + entityMember.getValue() + ";");
            w.writeln_l("}");
        });
        w.writeln("");
    }

    private void generateSetters() {
        entityMembers.forEach((entityMember) -> {
            String memberCaps = Character.toUpperCase(entityMember.getValue().charAt(0)) + entityMember.getValue().substring(1);
            w.writeln_r("void set" + memberCaps + "(" + entityMember.getKey() + " " + entityMember.getValue() + ") {");
            w.writeln("this." + entityMember.getValue() + " = " + entityMember.getValue() + ";");
            w.writeln_l("}");
        });
        w.writeln("");
    }

    private void generateCode() throws IOException {
        File writeFile = new File("F:\\Java\\" + EntityManager.getProjectName().replaceAll(" ","") + "\\src\\" + entityName + ".java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }

    private void createTable() throws SQLException {
        StringBuilder createQuery;
        createQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS tbl_" + entityName.toLowerCase() + "(\n");
        //First member of the ArrayList is the primary key.
        DatabaseUtil.connectToProject(EntityManager.getDBName());
        entityMembers.forEach((entityMember) -> {
            createQuery.append(entityMember.getValue()).append(" ").append(javaToSQLDataType(entityMember.getKey())).append(",\n");
        });    
        createQuery.append("PRIMARY KEY (").append(entityMembers.get(0).getValue()).append(")");
        createQuery.append(") ENGINE = INNODB;");
//        System.out.println(createQuery);
        DatabaseUtil.stmt.executeUpdate(new String(createQuery));
        DatabaseUtil.resetConnection();
    }

    private static String javaToSQLDataType(String dataType) {
        switch (dataType) {
            case "int":
                return "INT";
            case "String":
                return "VARCHAR(255)";
            case "char":
                return "CHAR";
            case "double":
                return "DOUBLE(10,2)";
        }
        return "";
    }

}
