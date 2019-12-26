package CodeGeneration;

import java.sql.*;

public class TestDB {
    
    public static void main(String args[]) {
        Connection con = null;
        try {
            //LOAD THE JDBC DRIVER FOR MYSQL
            Class.forName("com.mysql.jdbc.Driver");
            //OBTAIN DATABASE CONNECTION
            String dbURL = "jdbc:mysql://139.59.17.103:3306/db_test";
            //STEP-2 Obtain a connection with the Database
            con = DriverManager.getConnection(dbURL, "mansys", "sysman$");
            //Step-3 - Create a Statement to encapsulate SQL query 
            Statement stmt = con.createStatement();
            System.out.println("Done!");
            //int customerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter customer id"));
            //String customerName = JOptionPane.showInputDialog(null, "Enter customer name");
            //JOptionPane.showInputDialog(frame, "What's your name?");
//            int customerId = 22;
//            String customerName = "Kishan";
            //String insertString = "INSERT INTO tbl_customer VALUES (1, '" + "ram" + "')";
            //INSERT INTO tbl_customer VALUES (22,'Darshan','Surat');
//            String insertString = "INSERT INTO tbl_customer VALUES (" + customerId + ", '" + customerName + "','Ahmedabad')";
//            System.out.println(insertString);
            //Step-4 execute your SQL query
//            int i = stmt.executeUpdate(insertString);
//            System.out.println("Return value from insert query = " + i);
//            if (i == 1) {
//                System.out.println("Customer added successfully.");
//            } else {
//                System.out.println("Error occured in adding Customer.");
//            }
//
//            //Executing SQL Queries using Statement Object
//            String selectQuery
//                    = "SELECT customer_id, name, city FROM tbl_customer";
//            ResultSet rs = stmt.executeQuery(selectQuery);
//            System.out.println("customer_id " + "  " + " customer_name" + " city");
//            while (rs.next()) {
//                int id = rs.getInt("customer_id");
//                String name = rs.getString("name");
//                String city = rs.getString("city");
//                System.out.println(id + "  " + name + " " + city);
//            }
//
            //con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception caught --> " + e);
        }

    }

}
