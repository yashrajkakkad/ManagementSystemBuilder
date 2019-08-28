package CodeGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import Picocog.*;

public class GenerateDatabaseUtil {

    public static void generateCode(String projectName) throws IOException, SQLException {
        PicoWriter w = new PicoWriter();
        w.writeln("import java.sql.*;");
        w.writeln("");
        w.writeln_r("public class DatabaseUtil {");
        w.writeln("");
        w.writeln("static String dbURL = \"jdbc:mysql://192.241.249.93:3306/" +EntityManager.getDBName()+ "\";");
        w.writeln("static Connection con = null;");
        w.writeln("static Statement stmt;");
        w.writeln("static ResultSet rs;");
        w.writeln("");
        w.writeln_r("static {");
        w.writeln_r("try {");
        w.writeln("Class.forName(\"com.mysql.jdbc.Driver\");");
        w.writeln("con = DriverManager.getConnection(dbURL, \"mansys\", \"sysman$\");");
        w.writeln("stmt = con.createStatement();");
        w.writeln_l("}");
        w.writeln_r("catch (Exception ex) {");
        w.writeln("System.out.println(\"ex = \" + ex);");
        w.writeln_l("}");
        w.writeln("");
        w.writeln_l("}");
        w.writeln("");
        w.writeln_r("static Statement getStatement() throws SQLException {");
        w.writeln("System.out.println(\"DataUtil stmt = \" + stmt);");
        w.writeln("return stmt;");
        w.writeln_l("}");
        w.writeln("");
        w.writeln_r("protected void finalize() {");
        w.writeln_r("try {");
        w.writeln("stmt.close();");
        w.writeln("con.close();");
        w.writeln_l("}");
        w.writeln_r("catch (SQLException ex) {");
        w.writeln("System.out.println(\"Exception ex = \" + ex);");
        w.writeln_l("}");
        w.writeln_l("}");
        w.writeln_l("}");
//        DatabaseUtil.stmt.close();
//        DatabaseUtil.con.close();        
//        DatabaseUtil.con = DriverManager.getConnection("jdbc:mysql://134.209.159.227:3306", "mansys", "sysman$");
//        DatabaseUtil.stmt = DatabaseUtil.con.createStatement();
        File writeFile = new File(EntityManager.getDirectoryName() + "/" + "DatabaseUtil.java");
        try (FileWriter out = new FileWriter(writeFile)) {
            out.write(w.toString());
        }
    }
}
