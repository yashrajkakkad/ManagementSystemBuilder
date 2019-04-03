import java.sql.*;

public class DatabaseUtil {

    static String dbURL = "jdbc:mysql://134.209.159.227:3306/db_mansys";
    static String rootURL = "jdbc:mysql://134.209.159.227:3306";
    static Connection con = null;
    static Statement stmt;
    static ResultSet rs;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, "mansys", "sysman$");
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("ex = " + ex);
        }

    }

    static Statement getStatement() throws SQLException {
        System.out.println("DataUtil stmt = " + stmt);
        return stmt;
    }
    
    static void resetConnection() throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, "mansys", "sysman$");
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in resetConnection = " + ex);
        }        
    }

    static void connectMain() throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(rootURL, "mansys", "sysman$");
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in connectMain = " + ex);
        }        
        
    }
    
    static void connectToProject(String dbName) throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            projectName = projectName.trim().toLowerCase().replaceAll(" ","");
            String append = "/" + dbName;
            con = DriverManager.getConnection(rootURL+append, "mansys", "sysman$");
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in connectToProject = " + ex);
        }                
    }
    protected void finalize() {
        try {
            stmt.close();
            con.close();
        } 
        catch (SQLException ex) {
            System.out.println("Exception in finalize = " + ex);
        }
    }
}