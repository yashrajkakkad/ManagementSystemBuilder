/*
    Generates the panels as asked by the user.
*/
package GUIGeneration;

import java.util.ArrayList;
import javafx.util.Pair;
import CodeGeneration.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PanelManager {

    private static ArrayList<Pair<Entity, Integer>> entityPanels;
    private static CRUDPanel crudPanel;
    private static final int ADD = 0;
    private static final int UPDATE = 1;
    private static final int DELETE = 2;
    private static final int VIEW = 3;

//    public PanelManager(ArrayList<Pair<Entity, Integer>> entityPanels) {
//        this.entityPanels = entityPanels;
//        generatePanels();
//    }

    public static ArrayList<Pair<Entity, Integer>> getEntityPanels() {
        return entityPanels;
    }

    public static void setEntityPanels(ArrayList<Pair<Entity, Integer>> entityPanels) {
        PanelManager.entityPanels = entityPanels;
        generatePanels();
    }

    private static void generatePanels() {
        entityPanels.forEach((entityPanel) -> {
            switch (entityPanel.getValue()) {
                case ADD: {
                    try {
                        crudPanel = new AddEntityPanel(entityPanel.getKey());
                    } catch (IOException ex) {
                        Logger.getLogger(PanelManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case UPDATE: {
                    try {
                        crudPanel = new UpdateEntityPanel(entityPanel.getKey());
                    } catch (IOException ex) {
                        Logger.getLogger(PanelManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case DELETE:
//Ridham                    
                    break;
                case VIEW:
//Ridham                    
                    break;
            }
        });
    }
}
