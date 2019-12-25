/*
Manages all entities of the project
 */
package CodeGeneration;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import Utility.*;
import java.io.File;

public final class EntityManager {

    private static ArrayList<Entity> entities;
    private static String projectName;
    private static String DBName;
    private static String directoryName; 
    
    static {
        entities = new ArrayList<>();
    }

    public static String getProjectName() {
        return projectName;
    }

    public static String getDBName() {
        return DBName;
    }

    public static String getDirectoryName() {
        return directoryName;
    }

    public static ArrayList<Entity> getEntities() {
        return entities;
    }
        
    public static void setProjectName(String projectName) throws IOException {
        EntityManager.projectName = projectName.trim().toLowerCase().replaceAll(" ", "");
        createDirectory();
    }

    public static void setDBName(String DBName) {
        EntityManager.DBName = DBName;
    }
    
    public static void generateDBName() {
        DBName = "db_" + projectName;
    }

    public static void createDB() throws SQLException {
        DatabaseUtil.connectMain();
        String query = "CREATE DATABASE IF NOT EXISTS " + DBName;
        DatabaseUtil.stmt.executeUpdate(query);
        DatabaseUtil.resetConnection();
    }
    
    public static void createDirectory() throws IOException{
        File tempFile = new File("generated");
        tempFile.mkdir();
        directoryName = "generated\\" + projectName.replaceAll(" ","");
        File dir = new File(directoryName);
        dir.mkdir();
        File emailFile = new File("src\\Utility\\Email.java");
        File destEmailFile = new File(directoryName+"\\Email.java");
        Files.copy(emailFile.toPath(),destEmailFile.toPath());
    }

    public static void addEntity(Entity entity) throws IOException, SQLException {
        entities.add(entity);
    }
}
