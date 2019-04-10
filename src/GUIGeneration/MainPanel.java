package GUIGeneration;

import CodeGeneration.*;
import Picocog.*;
import Utility.FileManager;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

public class MainPanel {

    private PicoWriter w;

    public MainPanel() throws IOException {
        w = new PicoWriter();
        initialize();
        generateConstructor();
        finish();
    }

    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class HomePanel extends JPanel {");
        w.writeln("");
    }
    
    private void generateConstructor() {
        w.writeln_r("public HomePanel() {");
        w.writeln("setLayout(new GridLayout(0,2,10,10);");
        int count = 0;
        Entity previousEntity = PanelManager.getEntityPanels().get(0).getKey();
        w.writeln("JLabel " + PanelManager.getEntityPanels().get(0)
                .getKey().getEntityName() + "Label = new JLabel(\"" 
                + PanelManager.getEntityPanels().get(0).getKey().getEntityName() 
                + "\");");
        w.writeln("add(" + PanelManager.getEntityPanels().get(0)
                .getKey().getEntityName() + "Label);");
        w.writeln("add(new JLabel());");
        Iterator<Pair<Entity,Integer>> itr = PanelManager.getEntityPanels().iterator();
//        itr.next();
        while(itr.hasNext()) {
            Pair<Entity, Integer> tempPair = itr.next();
            if(previousEntity.getEntityName().equals(tempPair.getKey().getEntityName())) {
                count++;
            }            
            else {
                while(count<=4) {
                    w.writeln("add(new JLabel());");
                }
                w.writeln("JLabel " + tempPair.getKey().getEntityName() 
                        + "Label = new JLabel(\"" + tempPair.getKey().getEntityName() + "\");");
                count = 0;
            }
            w.writeln("JButton " + codeToString(tempPair.getValue())
                    + "Button = new JButton(\"" + codeToStringCaps(tempPair.getValue()) + "\");");
            w.writeln("add(" + codeToString(tempPair.getValue()) + "Button);");
            previousEntity = tempPair.getKey();
        }
        w.writeln_l("}");  
    }
    
//    private String codeToString(Integer code) {
//        switch(code) {
//            case 0:
//                return "add";
//            case 1:
//                return "update";
//            case 2:
//                return "delete";
//            case 3:
//                return "view";
//        }
//        return "";
//    }
//    
//    private String codeToStringCaps(Integer code) {
//        switch(code) {
//            case 0:
//                return "Add";
//            case 1:
//                return "Update";
//            case 2:
//                return "Delete";
//            case 3:
//                return "View";
//        }
//        return "";
//    }

    private void finish() throws IOException {
        w.writeln_l("}");
        FileManager.writeCode("MainPanel", w);
    }

}
