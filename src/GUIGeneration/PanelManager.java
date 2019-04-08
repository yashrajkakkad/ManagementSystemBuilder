package GUIGeneration;

import java.util.ArrayList;
import javafx.util.Pair;
import CodeGeneration.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PanelManager {

    private final ArrayList<Pair<Entity, String>> entityPanels;
    private CRUDPanel crudPanel;

    public PanelManager(ArrayList<Pair<Entity, String>> entityPanels) {
        this.entityPanels = entityPanels;
        generatePanels();
    }

    private void generatePanels() {
        entityPanels.forEach((entityPanel) -> {
            switch (entityPanel.getValue()) {
                case "Add": {
                    try {
                        crudPanel = new AddEntityPanel(entityPanel.getKey());
                    } catch (IOException ex) {
                        Logger.getLogger(PanelManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "Update": {
                    try {
                        crudPanel = new UpdateEntityPanel(entityPanel.getKey());
                    } catch (IOException ex) {
                        Logger.getLogger(PanelManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "Delete":
//Ridham                    
                    break;
                case "View":
//Ridham                    
                    break;
            }
        });
    }
}
