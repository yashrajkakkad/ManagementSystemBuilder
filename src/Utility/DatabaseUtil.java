package Utility;

import GUI.SetupDB;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseUtil {

    public static String dbURL = "";
    public static String rootURL = "";
    public static String db_username = "";
    public static String db_password = "";
    public static Connection con = null;
    public static Statement stmt;
    public static ResultSet rs;
    public static PreparedStatement ps;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbURL = "jdbc:mysql://"+getCredentials("host")+":"+getCredentials("port")+"/"+getCredentials("db_name");
            rootURL = "jdbc:mysql://"+getCredentials("host")+":"+getCredentials("port");
            db_username = getCredentials("username");
            db_password = getCredentials("password");
            con = DriverManager.getConnection(dbURL, db_username, db_password);
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("ex = " + ex);
            JOptionPane.showMessageDialog(null, "Your database is not configured correctly.");
            SetupDB setupDB = new SetupDB();
        }

    }

    private static String getCredentials(String cred_type) {
        File configFile = new File("config.properties");
        String cred = "";
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            cred = props.getProperty(cred_type);
            reader.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Your database is not configured correctly.");
            SetupDB setupDB = new SetupDB();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cred;
    }

    public static Statement getStatement() throws SQLException {
        System.out.println("DataUtil stmt = " + stmt);
        return stmt;
    }
    
    public static void resetConnection() throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            dbURL = "jdbc:mysql://"+getCredentials("host")+":"+getCredentials("port")+"/"+getCredentials("db_name");
            con = DriverManager.getConnection(dbURL, db_username, db_password);
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in resetConnection = " + ex);
            JOptionPane.showMessageDialog(null, "Your database is not configured correctly.");
            SetupDB setupDB = new SetupDB();
        }        
    }

    public static void connectMain() throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            dbURL = "jdbc:mysql://"+getCredentials("host")+":"+getCredentials("port");
            con = DriverManager.getConnection(rootURL, db_username, db_password);
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in connectMain = " + ex);
            JOptionPane.showMessageDialog(null, "Your database is not configured correctly.");
            SetupDB setupDB = new SetupDB();
        }
        
    }
    
    public static void connectToProject(String dbName) throws SQLException {
        stmt.close();
        con.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            projectName = projectName.trim().toLowerCase().replaceAll(" ","");
            String append = "/" + dbName;
            con = DriverManager.getConnection(rootURL+append, db_username, db_password);
            stmt = con.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("Exception in connectToProject = " + ex);
            JOptionPane.showMessageDialog(null, "Your database is not configured correctly.");
            SetupDB setupDB = new SetupDB();
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