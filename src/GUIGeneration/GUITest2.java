/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIGeneration;

import CodeGeneration.CRUDLogicGenerator;
import CodeGeneration.Entity;
import CodeGeneration.EntityManager;
import CodeGeneration.GenerateDatabaseUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author super
 */
public class GUITest2 {

    public static void main(String[] args) throws SQLException, IOException {
        ArrayList<Pair<String, String>> entityMembers1 = new ArrayList<>();
        EntityManager.setProjectName("Rusk Manment System");        
        EntityManager.generateDBName();
        EntityManager.createDB();
        System.out.println(EntityManager.getDBName());
        GenerateDatabaseUtil.generateCode(EntityManager.getDBName());
        entityMembers1.add(new Pair("int", "ruskID"));
        entityMembers1.add(new Pair("String", "name"));
        entityMembers1.add(new Pair("char", "secretCode"));
        entityMembers1.add(new Pair("double", "randomDoubleValue"));
        Entity entity1 = new Entity("Student", entityMembers1);        
        CRUDLogicGenerator.writeClassName(entity1);
        CRUDLogicGenerator.generateAddEntity(entity1);
        CRUDLogicGenerator.generateDeleteEntity(entity1);
        CRUDLogicGenerator.generateUpdateEntity(entity1);
        CRUDLogicGenerator.generateViewEntity(entity1);
        CRUDLogicGenerator.endClass();
        CRUDLogicGenerator.generateCode(entity1);
        ArrayList<Pair<Entity,Integer>> temp = new ArrayList<>();
        temp.add(new Pair(entity1,0));
        temp.add(new Pair(entity1,1));
        temp.add(new Pair(entity1,2));
        temp.add(new Pair(entity1,3));
        PanelManager.setEntityPanels(temp);
    }
}
