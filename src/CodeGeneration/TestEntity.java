package CodeGeneration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;

public class TestEntity {
    
    public static void main(String[] args) throws IOException, SQLException {
        ArrayList<Pair<String, String>> entityMembers1 = new ArrayList<>();
        EntityManager.setProjectName("School Management System");        
        EntityManager.generateDBName();
        EntityManager.createDB();
        System.out.println(EntityManager.getDBName());
        GenerateDatabaseUtil.generateCode(EntityManager.getDBName());
        entityMembers1.add(new Pair("int", "studentID"));
        entityMembers1.add(new Pair("String", "name"));
        Entity entity1 = new Entity("Student", entityMembers1);
        CRUDLogicGenerator.writeClassName(entity1);
        CRUDLogicGenerator.generateAddEntity(entity1);
        CRUDLogicGenerator.generateDeleteEntity(entity1);
        CRUDLogicGenerator.generateUpdateEntity(entity1);
        CRUDLogicGenerator.generateViewEntity(entity1);
        CRUDLogicGenerator.endClass();
        CRUDLogicGenerator.generateCode(entity1);
    }        
}
