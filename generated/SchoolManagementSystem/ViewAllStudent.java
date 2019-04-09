import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.util.ArrayList;

public class ViewAllStudent extends JPanel {
   
   
   ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
   
   public ViewAllStudentPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("View all Student"), BorderLayout.NORTH);
      String selectQuery = "SELECT * FROM tbl_student";
      DatabaseUtil.rs.executeQuery(selectQuery);
      while(rs.next()) {
         ArrayList<String> column = new ArrayList<>();
         column.add(String.valueOf(DatabaseUtil.rs.getInt("studentID")));
         column.add(DatabaseUtil.rs.getString("name"));
         table.add(column);
      }
      String[][] tableArray = table.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
      ArrayList<String> columnNames = new ArrayList<>();
      columnNames.add("studentID");
      columnNames.add("name");
      JTable viewTable = new JTable(tableArray,columnNames.toArray());
      add(new JScrollPane(viewTable), BorderLayout.CENTER);
   }
}
