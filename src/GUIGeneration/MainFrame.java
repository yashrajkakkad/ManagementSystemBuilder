package GUIGeneration;

import Picocog.*;
import CodeGeneration.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame {
    private final PicoWriter w;
    private final String TITLEFONT = "Arial";
    private final int TITLEFONTSIZE = 24;

    public MainFrame() throws IOException {
        w = new PicoWriter();
        initialize();
        generateConstructor();
        generateMain();
        finish();
        generateCode();
    }    
    
    private void initialize() {
        w.writeln("import javax.swing.*;");
        w.writeln("import java.awt.*;");
        w.writeln("import java.awt.Event.*;");
        w.writeln("");
        w.writeln_r("public class MainFrame extends JFrame {");
        w.writeln("");
    } 
    
    private void generateConstructor() {
        w.writeln_r("public MainFrame() {");
        w.writeln("setLayout(new BorderLayout());");
        generateTitle();
        w.writeln("setLocationRelativeTo(null);");
        w.writeln("setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);");
        generateCentralLayout();
        w.writeln_l("}");
        w.writeln("");
    }
    
    private void generateTitle() {
        w.writeln("setTitle(\"" + EntityManager.getProjectName() + "\");");
        w.writeln("JLabel titleLabel = new JLabel();");
        w.writeln("titleLabel.setText(\"" + EntityManager.getProjectName() 
                +"\");");
        w.writeln("titleLabel.setFont(new Font(\"" + TITLEFONT + "\", Font.BOLD," 
                + TITLEFONTSIZE + "));");
        w.writeln("add(titleLabel, BorderLayout.NORTH);");
    }
    
    private void generateCentralLayout() {
        w.writeln("JPanel centralPanel = new JPanel();");
        w.writeln("centralPanel.setLayout(new CardLayout());");        
        w.writeln("add(new JScrollPane(new AddStudentPanel()), BorderLayout.CENTER);");
    }
    
    private void generateMain() {
        w.writeln_r("public static void main(String[] args) {");        
        w.writeln("MainFrame mainFrame = new MainFrame();");
        w.writeln("mainFrame.setVisible(true);");
        w.writeln_l("}");
    }
    
    private void generateCode() throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName() 
                + "\\MainFrame.java");
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();        
    }
    
    private void finish() {
        w.writeln_l("}");
    }
}
