package GUIGeneration;

import CodeGeneration.Entity;
import CodeGeneration.EntityManager;
import CodeGeneration.GenerateDatabaseUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;

public class GUITest {
    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<Pair<String, String>> entityMembers1 = new ArrayList<>();
        EntityManager.setProjectName("School Management System");        
        EntityManager.generateDBName();
        EntityManager.createDB();
        System.out.println(EntityManager.getDBName());
        GenerateDatabaseUtil.generateCode(EntityManager.getDBName());
        entityMembers1.add(new Pair("int", "studentID"));
        entityMembers1.add(new Pair("String", "name"));
        Entity entity1 = new Entity("Student", entityMembers1);        
        AddEntityPanel studentPanel = new AddEntityPanel(entity1);
        MainFrame mainFrame = new MainFrame();
        AddEntityButton studentButton = new AddEntityButton(entity1);
        UpdateEntityPanel updateStudentPanel = new UpdateEntityPanel(entity1);
    }
}
