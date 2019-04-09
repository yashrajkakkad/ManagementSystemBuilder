package CodeGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Picocog.*;

public class CRUDLogicGenerator {

    private static boolean isEntityInitialized = false;
    private static PicoWriter w = new PicoWriter();

    public static void writeClassName(Entity entity) {
        w.writeln("import java.util.ArrayList;");
        w.writeln("import java.sql.SQLException;");
        w.writeln("");
        w.writeln_r("public class " + entity.getEntityName()
                + "CRUD {");
        w.writeln("");
        isEntityInitialized = true;
    }

    public static void generateAddEntity(Entity entity) {
        w.writeln_r("public boolean add" + entity.getEntityName()
                + "(" + entity.getEntityName() + " "
                + entity.getEntityName().toLowerCase() + ") throws SQLException {");
        StringBuilder fields = new StringBuilder("(");
        entity.getEntityMembers().forEach((entityMember) -> {
            fields.append(entityMember.getValue()).append(", ");
        });
        fields.deleteCharAt(fields.length() - 2);
        fields.append(")");
        StringBuilder values = new StringBuilder("(");
        entity.getEntityMembers().forEach((entityMember) -> {
            values.append("'\" + ").append(entity.getEntityName().toLowerCase()).append(".get")
                    .append(Character.toUpperCase(entityMember.getValue().charAt(0)))
                    .append(entityMember.getValue().substring(1)).append("() + \"', ");
        });
        values.deleteCharAt(values.length() - 2);
        values.append(");");
        w.writeln("String insertQuery = \"INSERT INTO tbl_"
                + entity.getEntityName().toLowerCase() + " " + fields.toString()
                + " VALUES " + values.toString() + "\"; ");
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(insertQuery);");
        w.writeln_r("if(i!=1) {");
        w.writeln("return false;");
        w.writeln_l("}");
        w.writeln("return true;");
        w.writeln_l("}");
        w.writeln("");
    }

    public static void generateDeleteEntity(Entity entity) {

        w.writeln_r("public void delete" + entity.getEntityName()
                + "(String dataField, String value) throws SQLException {");
        w.writeln("StringBuilder conditionString = new StringBuilder();");
        w.writeln("conditionString.append(dataField).append(\"=\");");
        w.writeln_r("if(dataField.equals(\"int\") || dataField.equals(\"double\")) {");
        w.writeln("conditionString.append(value);");
        w.writeln_l("}");
        w.writeln_r("else {");
        w.writeln("conditionString.append(\"'\" + value + \"'\");");
        w.writeln_l("}");
        w.writeln("String deleteQuery = \"DELETE FROM tbl_"
                + entity.getEntityName().toLowerCase()
                + " WHERE \" + conditionString.toString();");
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);");
        w.writeln_r("if(i!=1) {");
        w.writeln("System.out.println(\"Error occured in deleting " + entity.getEntityName() + "\");");
        w.writeln_l("}");
        w.writeln_l("}");
        w.writeln("");
    }

    public static void generateViewEntity(Entity entity) {
        w.writeln_r("public " + entity.getEntityName() + " view" + entity.getEntityName()
                + "(String dataField, String value) throws SQLException {");
        w.writeln("StringBuilder conditionString = new StringBuilder();");
        w.writeln("conditionString.append(dataField).append(\"=\");");
        w.writeln_r("if(dataField.equals(\"int\") || dataField.equals(\"double\")) {");
        w.writeln("conditionString.append(value);");
        w.writeln_l("}");
        w.writeln_r("else {");
        w.writeln("conditionString.append(\"'\" + value + \"'\");");
        w.writeln_l("}");
        w.writeln("String viewQuery = \"SELECT * FROM tbl_"
                + entity.getEntityName().toLowerCase()
                + " WHERE \" + conditionString.toString();");
        w.writeln("DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);");
        w.writeln("ArrayList<" + entity.getEntityName() + "> "
                + Character.toLowerCase(entity.getEntityName().charAt(0))
                + entity.getEntityName().substring(1) + " = new ArrayList<>();");
        w.writeln_r("while(DatabaseUtil.rs.next()) {");
        StringBuilder toRetrieve = new StringBuilder();
        toRetrieve
//                .append(Character.toLowerCase(entity.getEntityName().charAt(0)))
//                .append(entity.getEntityName().substring(1)).append(".add(")
                .append("new ").append(entity.getEntityName()).append("( ");
        entity.getEntityMembers().forEach((entityMember) -> {
            toRetrieve.append("DatabaseUtil.rs.").append("get")
                    .append(Character.toUpperCase(entityMember.getKey().charAt(0)))
                    .append(entityMember.getKey().substring(1)).append("(")
                    .append("\"").append(entityMember.getValue()).append("\"")
                    .append("), ");
        });
        toRetrieve.deleteCharAt(toRetrieve.length()-2);
//        toRetrieve.append(" ));");
        w.writeln("return " + toRetrieve.toString());
        w.writeln_l("}");
//        w.writeln("return " + Character.toLowerCase(entity.getEntityName().charAt(0))
//                + entity.getEntityName().substring(1) + ";");
        w.writeln_l("}");
        w.writeln("");
    }

    public static void generateUpdateEntity(Entity entity) {
        w.writeln_r("public void update" + entity.getEntityName() + "("
                + entity.getEntityName() + " " + entity.getEntityName().toLowerCase()
                + ", " + entity.getEntityMembers().get(0).getKey() + " "
                + entity.getEntityMembers().get(0).getValue() + ") throws SQLException {");
        w.writeln("StringBuilder updatedValues = new StringBuilder();");
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("updatedValues.append(\"" + entityMember.getValue() 
                    + " = \" + "
                    + entity.getEntityName().toLowerCase() + ".get"
                    + Character.toUpperCase(entityMember.getValue().charAt(0))
                    + entityMember.getValue().substring(1) + "()" + ");");
        });
        w.writeln("String updateQuery = \"UPDATE \" + updatedValues.toString() + \" WHERE "
                + entity.getEntityMembers().get(0).getValue() + " = \" + "
                + entity.getEntityMembers().get(0).getValue() + ";");
        w.writeln_l("}");
        w.writeln("");
    }

    public static void endClass() {
        w.writeln("");
        w.writeln_l("}");
    }

    public static void generateCode(Entity entity) throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName() + "\\" + entity.getEntityName() + "CRUD.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }
}
