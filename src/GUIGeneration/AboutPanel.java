package GUIGeneration;

import Picocog.*;
import Utility.FileManager;
import java.io.IOException;

public class AboutPanel {
    private final PicoWriter w;
    private String aboutMessage;

    public AboutPanel(String aboutMessage) throws IOException {
        this.aboutMessage = "<html>" + aboutMessage;
        this.aboutMessage.replaceAll("\n", "<br>");
        this.aboutMessage += "</html>";
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
        w.writeln_r("public class AboutPanel extends JPanel {");
        w.writeln("");        
    }
    
    private void generateConstructor() {
        w.writeln_r("public AboutPanel() {");
        w.writeln("setLayout(new BorderLayout());");
        w.writeln("add(new JLabel(\"About Us\"), BorderLayout.NORTH);");
        w.writeln("add(new JLabel(\"" + aboutMessage + "\", BorderLayout.CENTER)");
        w.writeln_l("}");
    }
    
    private void finish() throws IOException {
        w.writeln_l("}");
        FileManager.writeCode("AboutPanel", w);
    }
}
