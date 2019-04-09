//Pending, courtesy Ridham

package GUIGeneration;

import CodeGeneration.*;
import java.io.IOException;

public class ViewEntityPanel extends CRUDPanel {

    public ViewEntityPanel(Entity entity) throws IOException {
        super(entity);
        super.initialize("View" + entity.getEntityName() + "Panel");
        generateComponents();
        generateConstructor();
        super.finish("View" + entity.getEntityName() + "Panel");
    }

    @Override
    protected final void generateComponents() {
        generateLabels();
        generateTextFields();
        generateButton("View");
    }

    @Override
    protected void generateAddComponents() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("topPanel.add(" + entityMember.getValue() + "Label);");
            w.writeln("topPanel.add(" + entityMember.getValue() + "TextField);");
        });
        w.writeln("bottomPanel.add(viewButton);");
        w.writeln("addButton.setMaximumSize(addButton.getPreferredSize());");
    }

    private void generateViewActionListener() {
        
    }
    
    @Override
    protected final void generateConstructor() {
        w.writeln_r("public View" + entity.getEntityName() + "Panel() {");
        w.writeln("setLayout(new BorderLayout());");
        w.writeln("add(new JLabel(\"Update " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        w.writeln("JPanel topPanel = new JPanel();");
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("topPanel.setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        generateViewActionListener();
        w.writeln("add(topPanel,BorderLayout.CENTER);");
        w.writeln("add(bottomPanel, BorderLayout.SOUTH);");
        
    }
    
}
