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
        w.writeln_r("public static boolean add" + entity.getEntityName()
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
        w.writeln_r("public static boolean delete" + entity.getEntityName()
                + "(" + entity.getEntityMembers().get(0).getKey() + " value) throws SQLException {");
//        w.writeln("StringBuilder conditionString = new StringBuilder();");
//        w.writeln("conditionString.append(dataField).append(\"=\");");
//        w.writeln_r("if(dataField.equals(\"int\") || dataField.equals(\"double\")) {");
//        w.writeln("conditionString.append(value);");
//        w.writeln_l("}");
//        w.writeln_r("else {");
//        w.writeln("conditionString.append(\"'\" + value + \"'\");");
//        w.writeln_l("}");
        StringBuilder deleteQuery = new StringBuilder("\"DELETE FROM tbl_");
        deleteQuery.append(entity.getEntityName().toLowerCase()).append(" WHERE ")
                .append(entity.getEntityMembers().get(0).getValue()).append(" = ");
        if(!(entity.getEntityMembers().get(0).getKey().equals("int") ||
                entity.getEntityMembers().get(0).getKey().equals("double"))) {
            deleteQuery.append("'\" + ").append("value").append(" + \"'\"");
        }
        else {
            deleteQuery.append("\" + ").append("value");
        }
        deleteQuery.append(";");
        w.writeln("String deleteQuery = " + deleteQuery.toString());
//        w.writeln("String viewQuery = \"SELECT * FROM tbl_"
//                + entity.getEntityName().toLowerCase()
//                + " WHERE \" + conditionString.toString() +  \";\";");
//        StringBuilder viewQuery = new StringBuilder("SELECT * from tbl_");
//        viewQuery.append(entity.getEntityName().toLowerCase()).append(" WHERE ");
//        if()
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);");
        w.writeln_r("if(i==1) {");
//        w.writeln("JOptionPane.showMessageDialog(this,\"Deleted successfully!\");");
        w.writeln("return true;");
//        w.writeln_lr("} else {");
//        w.writeln("JOptionPane.showMessageDialog(this,\"Unexpected error occured!\");");
//        w.writeln_l("}");
        w.writeln_l("}");
        w.writeln("return false;");
//        w.writeln("ArrayList<" + entity.getEntityName() + "> "
//                + Character.toLowerCase(entity.getEntityName().charAt(0))
//                + entity.getEntityName().substring(1) + " = new ArrayList<>();");
//        w.writeln_r("while(DatabaseUtil.rs.next()) {");
//        StringBuilder toRetrieve = new StringBuilder();
//        toRetrieve
////                .append(Character.toLowerCase(entity.getEntityName().charAt(0)))
////                .append(entity.getEntityName().substring(1)).append(".add(")
//                .append("new ").append(entity.getEntityName()).append("( ");
//        entity.getEntityMembers().forEach((entityMember) -> {
//            toRetrieve.append("DatabaseUtil.rs.").append("get")
//                    .append(Character.toUpperCase(entityMember.getKey().charAt(0)))
//                    .append(entityMember.getKey().substring(1)).append("(")
//                    .append("\"").append(entityMember.getValue()).append("\"")
//                    .append("), ");
//        });
//        toRetrieve.deleteCharAt(toRetrieve.length()-2);
//        toRetrieve.append(" );");
//        w.writeln("return " + toRetrieve.toString());
//        w.writeln_l("}");
        w.writeln_l("}");
        w.writeln("");

//        w.writeln_r("public boolean delete" + entity.getEntityName()
//                + "(String dataField, String value) throws SQLException {");
//        w.writeln("StringBuilder conditionString = new StringBuilder();");
//        w.writeln("conditionString.append(dataField).append(\"=\");");
//        w.writeln_r("if(dataField.equals(\"int\") || dataField.equals(\"double\")) {");
//        w.writeln("conditionString.append(value);");
//        w.writeln_l("}");
//        w.writeln_r("else {");
//        w.writeln("conditionString.append(\"'\" + value + \"'\");");
//        w.writeln_l("}");
//        w.writeln("String deleteQuery = \"DELETE FROM tbl_"
//                + entity.getEntityName().toLowerCase()
//                + " WHERE \" + conditionString.toString();");
//        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(deleteQuery);");
//        w.writeln_r("if(i!=1) {");
////        w.writeln("System.out.println(\"Error occured in deleting " + entity.getEntityName() + "\");");
//        w.writeln("return false;");
//        w.writeln_l("}");
//        w.writeln("return true;");
//        w.writeln_l("}");
//        w.writeln("");
    }

    public static void generateViewEntity(Entity entity) {
        w.writeln_r("public static " + entity.getEntityName() + " view" + entity.getEntityName()
                + "(" + entity.getEntityMembers().get(0).getKey() + " value) throws SQLException {");
//        w.writeln("StringBuilder conditionString = new StringBuilder();");
//        w.writeln("conditionString.append(dataField).append(\"=\");");
//        w.writeln_r("if(dataField.equals(\"int\") || dataField.equals(\"double\")) {");
//        w.writeln("conditionString.append(value);");
//        w.writeln_l("}");
//        w.writeln_r("else {");
//        w.writeln("conditionString.append(\"'\" + value + \"'\");");
//        w.writeln_l("}");
        StringBuilder viewQuery = new StringBuilder("\"SELECT * FROM tbl_");
        viewQuery.append(entity.getEntityName().toLowerCase()).append(" WHERE ")
                .append(entity.getEntityMembers().get(0).getValue()).append(" = ");
        if(!(entity.getEntityMembers().get(0).getKey().equals("int") ||
                entity.getEntityMembers().get(0).getKey().equals("double"))) {
            viewQuery.append("'\" + ").append("value").append(" + \"'\"");
        }
        else {
            viewQuery.append("\" + ").append("value");
        }
        viewQuery.append(";");
        w.writeln("String viewQuery = " + viewQuery.toString());
//        w.writeln("String viewQuery = \"SELECT * FROM tbl_"
//                + entity.getEntityName().toLowerCase()
//                + " WHERE \" + conditionString.toString() +  \";\";");
//        StringBuilder viewQuery = new StringBuilder("SELECT * from tbl_");
//        viewQuery.append(entity.getEntityName().toLowerCase()).append(" WHERE ");
//        if()
        w.writeln("DatabaseUtil.rs = DatabaseUtil.stmt.executeQuery(viewQuery);");
//        w.writeln("ArrayList<" + entity.getEntityName() + "> "
//                + Character.toLowerCase(entity.getEntityName().charAt(0))
//                + entity.getEntityName().substring(1) + " = new ArrayList<>();");
        w.writeln_r("while(DatabaseUtil.rs.next()) {");
        StringBuilder toRetrieve = new StringBuilder();
        toRetrieve
//                .append(Character.toLowerCase(entity.getEntityName().charAt(0)))
//                .append(entity.getEntityName().substring(1)).append(".add(")
                .append("new ").append(entity.getEntityName()).append("( ");
        entity.getEntityMembers().forEach((entityMember) -> {
            toRetrieve.append("DatabaseUtil.rs.").append("get");
            if (entityMember.getKey().equals("char"))
            {
                toRetrieve.append("String").append("(")
                          .append("\"").append(entityMember.getValue()).append("\"")
                          .append(").charAt(0), ");
            }
            else
            {
                toRetrieve.append(Character.toUpperCase(entityMember.getKey().charAt(0)))
                          .append(entityMember.getKey().substring(1)).append("(")
                          .append("\"").append(entityMember.getValue()).append("\"")
                          .append("), ");
            }
        });
        toRetrieve.deleteCharAt(toRetrieve.length()-2);
        toRetrieve.append(" );");
        w.writeln("return " + toRetrieve.toString());
        w.writeln_l("}");
//        w.writeln("return " + Character.toLowerCase(entity.getEntityName().charAt(0))
//                + entity.getEntityName().substring(1) + ";");
        w.writeln("return null;");
        w.writeln_l("}");
        w.writeln("");
    }

    public static void generateUpdateEntity(Entity entity) {
        w.writeln_r("public static boolean update" + entity.getEntityName() + "("
                + entity.getEntityName() + " " + entity.getEntityName().toLowerCase()
                + ", " + entity.getEntityMembers().get(0).getKey() + " "
                + entity.getEntityMembers().get(0).getValue() + ") throws SQLException {");
//        w.writeln("StringBuilder updatedValues = new StringBuilder();");
//        entity.getEntityMembers().forEach((entityMember) -> {
//            w.writeln("updatedValues.append(\"" + entityMember.getValue() 
//                    + " = \" + "
//                    + entity.getEntityName().toLowerCase() + ".get"
//                    + Character.toUpperCase(entityMember.getValue().charAt(0))
//                    + entityMember.getValue().substring(1) + "()" + ");");
//        });
//        w.writeln("String updateQuery = \"UPDATE \" + updatedValues.toString() + \" WHERE "
//                + entity.getEntityMembers().get(0).getValue() + " = \" + "
//                + entity.getEntityMembers().get(0).getValue() + ";");
//        w.writeln_l("}");
//        w.writeln("");
        StringBuilder updateQuery = new StringBuilder("UPDATE tbl_");
        updateQuery.append(entity.getEntityName().toLowerCase()).append(" SET ");
        entity.getEntityMembers().forEach((entityMember) -> {
            updateQuery.append(entityMember.getValue()).append(" = ");
            if(entityMember.getKey().equals("String") || entityMember.getKey().equals("char")) {
                updateQuery.append("'\" + ")
                        .append(entity.getEntityName().toLowerCase())
                        .append(".get")
                        .append(Character.toUpperCase(entityMember.getValue().charAt(0)))
                        .append(entityMember.getValue().substring(1))
                        .append("()").append(" + \"', ");
            }
            else {
                updateQuery.append("\" + ")
                        .append(entity.getEntityName().toLowerCase())
                        .append(".get")
                        .append(Character.toUpperCase(entityMember.getValue().charAt(0)))
                        .append(entityMember.getValue().substring(1))
                        .append("()").append(" + \", ");                
            }
        });
        updateQuery.deleteCharAt(updateQuery.length()-1);
        updateQuery.deleteCharAt(updateQuery.length()-1);
        updateQuery.append(" WHERE ");
        if(entity.getEntityMembers().get(0).getKey().equals("String") 
                || entity.getEntityMembers().get(0).getKey().equals("char")) {
            updateQuery
                    .append(entity.getEntityMembers().get(0).getValue())
                    .append(" ='\" + ").append(entity.getEntityMembers().get(0).getValue()).append(" + \"'");
            w.writeln("String updateQuery = \"" + updateQuery.toString() + "\";");
        }
        else
        {
            updateQuery.append(entity.getEntityMembers().get(0).getValue())
                    .append(" = \" + ").append(entity.getEntityMembers().get(0).getValue());
//                    .append(" + \";\"");
            w.writeln("String updateQuery = \"" + updateQuery.toString() + ";");
//        }
        }
        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(updateQuery);");
        w.writeln_r("if(i==1) {");
        w.writeln("return true;");
        w.writeln_l("}");
        w.writeln("return false;");
        w.writeln_l("}");
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
        w = new PicoWriter();
    }
}
