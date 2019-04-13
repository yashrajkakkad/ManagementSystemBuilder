//Pending, courtesy Ridham

package GUIGeneration;

import CodeGeneration.*;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

public class ViewEntityPanel extends CRUDPanel {

    public void generateTextLabels(){
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JLabel view" + entityMember.getValue()
                    + "Label = new JLabel(\"" + entityMember.getValue() + "\");");
        });
    }
    
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
        generateViewLabels();
        generateButton("View");
    }

    @Override
    protected void generateAddComponents() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("topPanel.add(" + entityMember.getValue() + "Label);");
            w.writeln("topPanel.add(" + entityMember.getValue() + "ViewLabel);");
        });
        w.writeln("bottomPanel.add(ViewButton);");
        w.writeln("ViewButton.setMaximumSize(ViewButton.getPreferredSize());");
    }

    private void generateViewActionListener() {
        w.writeln_r("ViewButton.addActionListener((e) -> {");        
        StringBuilder viewFunctionCall = new StringBuilder("");
        viewFunctionCall.append("retrieve").append(entity.getEntityName())
                .append(" = ").append(entity.getEntityName()).append("CRUD.view").append(entity.getEntityName()).append("(");
        switch (entity.getEntityMembers().get(0).getKey()) {
            case "int":
                viewFunctionCall.append("Integer.parseInt(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "double":
                viewFunctionCall.append("Double.parseDouble(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "char":
                viewFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText().charAt(0)");
                break;
            case "String":
                viewFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText()");
                break;
        }

        viewFunctionCall.append(");");
        w.writeln(entity.getEntityName() + " retrieve" + entity.getEntityName() + " = null;");
        w.writeln_r("try {");
        w.writeln(viewFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
        w.writeln_r("if(retrieve" + entity.getEntityName() + "==null) {");
        w.writeln("JOptionPane.showMessageDialog(this,\"Unexpected error occured!\");");
        w.writeln_lr("} else {");
        w.writeln("JOptionPane.showMessageDialog(this,\"Data retrieved successfully!\");");
//        w.writeln("");
        Iterator<Pair<String,String>> iterator = entity.getEntityMembers().iterator();
        iterator.next();
        while(iterator.hasNext()) {
            Pair<String,String> tempPair = iterator.next();
            w.writeln(tempPair.getValue() + "ViewLabel.setText(retrieve" 
                    + entity.getEntityName() + ".get" 
                    + Character.toUpperCase(tempPair.getValue().charAt(0)) 
                    + tempPair.getValue().substring(1) + "());");
        }
//                .append();
//        entity.getEntityMembers().forEach((entityMember) -> {
//            viewFunctionCall.append("\" + ").append(entityMember.getKey()).append(",").append(entityMember.getValue()).append(" + \", ");
//           w.writeln("JLabel2 "+entityMember.getValue()+"Label2.setText("+entity.getEntityName()+".get("+entityMember.getValue().substring(0, 1).toUpperCase() + entityMember.getValue().substring(1)+"));");
//        });
//        viewFunctionCall.delete(viewFunctionCall.length()-6,viewFunctionCall.length());                
        w.writeln_l("}");
//        StringBuilder viewFunctionCall = new StringBuilder( entity.getEntityName() + " retrieve"+entity.getEntityName()+ " =  view"+entity.getEntityName()+"(\"");
//        
//        viewFunctionCall.append(entity.getEntityMembers().get(0).getKey()).append("\",\"").append(entity.getEntityMembers().get(0).getValue()).append("\"); ");
//        w.writeln(viewFunctionCall.toString());
//        entity.getEntityMembers().forEach((entityMember) -> {
//            //viewFunctionCall.append("\" + ").append(entityMember.getKey()).append(",").append(entityMember.getValue()).append(" + \", ");
//            w.writeln("JLabel "+ entityMember.getValue() + "Label.setText(\""+entityMember.getValue()+"\" );");
//            w.writeln("JLabel "+entityMember.getValue()+"Label2.setText(retrieved"+entity.getEntityName()+".get("+entityMember.getValue().substring(0, 1).toUpperCase() + entityMember.getValue().substring(1)+"));");
//        });
        //viewFunctionCall.delete(viewFunctionCall.length()-6,viewFunctionCall.length());
        
        
        w.writeln_l("});");
    }
    
    @Override
    protected final void generateConstructor() {
        w.writeln_r("public View" + entity.getEntityName() + "Panel() {");
        w.writeln("setLayout(new BorderLayout());");
        w.writeln("add(new JLabel(\"View " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        w.writeln("JPanel topPanel = new JPanel();");
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("topPanel.setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        generateViewActionListener();
        w.writeln("add(topPanel,BorderLayout.CENTER);");
        w.writeln("add(bottomPanel, BorderLayout.SOUTH);");
        w.writeln_l("}");
    }
    
}
