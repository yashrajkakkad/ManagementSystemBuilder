package GUIGeneration;

import CodeGeneration.*;
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
        EntityManager.createDirectory();
        
        System.out.println(EntityManager.getDBName());
        GenerateDatabaseUtil.generateCode(EntityManager.getDBName());
        entityMembers1.add(new Pair("int", "studentID"));
        entityMembers1.add(new Pair("String", "name"));
        Entity entity1 = new Entity("Student", entityMembers1);        
        ArrayList<Pair<Entity,Integer>> temp = new ArrayList<>();
        temp.add(new Pair(entity1,0));
        temp.add(new Pair(entity1,1));
        temp.add(new Pair(entity1,2));
        temp.add(new Pair(entity1,3));
        PanelManager.setEntityPanels(temp);
        AddEntityPanel studentPanel = new AddEntityPanel(entity1);
        MainFrame mainFrame = new MainFrame();
        UpdateEntityPanel updateStudentPanel = new UpdateEntityPanel(entity1);
        ViewAllPanel viewStudentPanel = new ViewAllPanel(entity1);
        AboutPanel aboutPanel = new AboutPanel("This is an about panel sample text!");
        ContactPanel contactPanel = new ContactPanel("prayag.s@ahduni.edu.in");
//        MainFrame mainFrame = new MainFrame();
    }
}
