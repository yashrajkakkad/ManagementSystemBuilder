package GUIGeneration;

//import Picocog.*;
//import CodeGeneration.*;
//import java.io.FileWriter;
//import java.io.IOException;
//import Utility.*;
//public class AddEntityButton {
//
//    private final Entity entity;
//    private final PicoWriter w;

//    public AddEntityButton(Entity entity) throws IOException {
//        this.entity = entity;
//        w = new PicoWriter();
//        initialize();
//        generateConstructor();
//        finish();
//        generateCode();
//    }

//    private void initialize() {
//        w.writeln("import javax.swing.*;");
//        w.writeln("import java.awt.*;");
//        w.writeln("import java.awt.Event.*;");
//        w.writeln("");
//        w.writeln_r("public class Add" + entity.getEntityName() + "Button"
//                + "Panel extends JPanel {");
//        w.writeln("");
//    }
//    
//    private void generateConstructor() {
//        w.writeln_r("public Add" + entity.getEntityName() + "ButtonPanel {");
//        generateButton();
//        w.writeln_l("}");
//    }

//    private void generateButton() {
//    }

//    private void finish() throws IOException {
//        w.writeln_l("}");
//        FileManager.writeCode("Add" + entity.getEntityName() + "ButtonPanel.java", w);
//    }

//}
