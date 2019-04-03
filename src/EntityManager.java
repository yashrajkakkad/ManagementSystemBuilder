/*
Manages all entities of the project
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import Utility.*;

public final class EntityManager {

    private static ArrayList<Entity> entities;
    private static String projectName;
    private static String DBName;

    static {
        entities = new ArrayList<>();
    }

    public static String getProjectName() {
        return projectName;
    }

    public static String getDBName() {
        return DBName;
    }

    public static void setProjectName(String projectName) {
        EntityManager.projectName = projectName;
    }

    public static void setDBName(String DBName) {
        EntityManager.DBName = DBName;
    }

    public static void generateDBName() {
        DBName = "db_" + projectName.trim().toLowerCase().replaceAll(" ", "");
    }

    public static void createDB() throws SQLException {
        DatabaseUtil.connectMain();
        String query = "CREATE DATABASE IF NOT EXISTS " + DBName;
        DatabaseUtil.stmt.executeUpdate(query);
        DatabaseUtil.resetConnection();
    }

    public static void addEntity(String entityName, ArrayList<Pair<String, String>> entityMembers) throws IOException, SQLException {
        entities.add(new Entity(entityName, entityMembers));
    }
}
