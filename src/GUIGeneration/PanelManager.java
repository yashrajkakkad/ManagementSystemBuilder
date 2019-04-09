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

    private final ArrayList<Pair<Entity, Integer>> entityPanels;
    private CRUDPanel crudPanel;
    private final int ADD = 0;
    private final int UPDATE = 1;
    private final int DELETE = 2;
    private final int VIEW = 3;

    public PanelManager(ArrayList<Pair<Entity, Integer>> entityPanels) {
        this.entityPanels = entityPanels;
        generatePanels();
    }

    private void generatePanels() {
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
