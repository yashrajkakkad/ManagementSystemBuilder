/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIGeneration;

import CodeGeneration.Entity;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

/**
 *
 * @author HP
 */
public class DeleteEntityPanel extends CRUDPanel {

    public void generateTextLabels() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("private JLabel delete" + entityMember.getValue()
                    + "Label = new JLabel(\"" + entityMember.getValue() + "\");");
        });
    }

    private void generateSubPanel() {
        w.writeln("JPanel subPanel = new JPanel();");
    }

    public DeleteEntityPanel(Entity entity) throws IOException {
        super(entity);
        super.initialize("Delete" + entity.getEntityName() + "Panel");
        generateComponents();
        generateConstructor();
        super.finish("Delete" + entity.getEntityName() + "Panel");
    }

    @Override
    protected final void generateComponents() {
        generateLabels();
        generateViewLabels();
        generateButton("Delete");
        generateButton("Search");
        generateSubPanel();
    }

    @Override
    protected void generateAddComponents() {
        Iterator<Pair<String, String>> itr
                = entity.getEntityMembers().iterator();
        String firstValue = itr.next().getValue();
        w.writeln("topPanel.add(" + firstValue + "Label);");
        w.writeln("subPanel.add(" + firstValue + "ViewLabel);");
        w.writeln("subPanel.add(SearchButton);");
        w.writeln("topPanel.add(subPanel);");
        while (itr.hasNext()) {
            String nextValue = itr.next().getValue();
            w.writeln("topPanel.add(" + nextValue + "Label);");
            w.writeln("topPanel.add(" + nextValue + "Label2);");
        }
        w.writeln("bottomPanel.add(DeleteButton);");
        w.writeln("DeleteButton.setMaximumSize"
                + "(DeleteButton.getPreferredSize());");
    }

    private void generateSearchActionListener() {
        w.writeln_r("SearchButton.addActionListener((e) -> {");
        StringBuilder deleteFunctionCall = new StringBuilder("");
        deleteFunctionCall.append("retrieve").append(entity.getEntityName())
                .append(" = ").append(entity.getEntityName()).append("CRUD.view").append(entity.getEntityName()).append("(");
        switch (entity.getEntityMembers().get(0).getKey()) {
            case "int":
                deleteFunctionCall.append("Integer.parseInt(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "double":
                deleteFunctionCall.append("Double.parseDouble(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "char":
                deleteFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText().charAt(0)");
                break;
            case "String":
                deleteFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText()");
                break;
        }

        deleteFunctionCall.append(");");
        w.writeln(entity.getEntityName() + " retrieve" + entity.getEntityName() + " = null;");
        w.writeln_r("try {");
        w.writeln(deleteFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
        w.writeln_r("if(retrieve" + entity.getEntityName() + "==null) {");
        w.writeln("JOptionPane.showMessageDialog(this,\"Unexpected error occured!\");");
        w.writeln_lr("} else {");
        w.writeln("JOptionPane.showMessageDialog(this,\"Data retrieved successfully!\");");
        w.writeln_l("}");
        w.writeln_l("});");

//        StringBuilder deleteFunctionCall = new StringBuilder( entity.getEntityName() + " retrieve"+entity.getEntityName()+ " =  view"+entity.getEntityName()+"(\"");
//        
//        deleteFunctionCall.append(entity.getEntityMembers().get(0).getKey()).append("\",\"").append(entity.getEntityMembers().get(0).getValue()).append("\"); ");
//        w.writeln(deleteFunctionCall.toString());
//        entity.getEntityMembers().forEach((entityMember) -> {
//            //deleteFunctionCall.append("\" + ").append(entityMember.getKey()).append(",").append(entityMember.getValue()).append(" + \", ");
//            w.writeln("JLabel "+ entityMember.getValue() + "Label.setText(\""+entityMember.getValue()+"\" );");
//            w.writeln("JLabel "+entityMember.getValue()+"Label2.setText(retrieved"+entity.getEntityName()+".get("+entityMember.getValue().substring(0, 1).toUpperCase() + entityMember.getValue().substring(1)+"));");
//        });
        //deleteFunctionCall.delete(deleteFunctionCall.length()-6,deleteFunctionCall.length());
    }

    private void generateDeleteActionListener() {
        w.writeln_r("DeleteButton.addActionListener((e) -> {");
        StringBuilder deleteFunctionCall = new StringBuilder("isDeleted = "+entity.getEntityName()+"CRUD.delete" + entity.getEntityName() + "(");
        switch (entity.getEntityMembers().get(0).getKey()) {
            case "int":
                deleteFunctionCall.append("Integer.parseInt(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "double":
                deleteFunctionCall.append("Double.parseDouble(").append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText())");
                break;
            case "char":
                deleteFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText().charAt(0)");
                break;
            case "String":
                deleteFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("Label2.getText()");
                break;
        }

//        entity.getEntityMembers().forEach((entityMember) -> {
//        deleteFunctionCall.append(entity.getEntityMembers().get(0).getKey()).append("\",\"").append(entity.getEntityMembers().get(0).getValue()).append("\" ");
        //w.writeln("JLabel "+ entityMember.getValue() + "Label.setText(\""+entityMember.getValue()+"\" );");
        //w.writeln("JLabel "+entityMember.getValue()+"Label2.setText("+entity.getEntityName()+".get("+entityMember.getValue().substring(0, 1).toUpperCase() + entityMember.getValue().substring(1)+"));");
//        });
        //deleteFunctionCall.delete(deleteFunctionCall.length()-6,deleteFunctionCall.length());
        deleteFunctionCall.append(");");
        w.writeln("boolean isDeleted" + " = false;");
        w.writeln_r("try {");
        w.writeln(deleteFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
        w.writeln_r("if(isDeleted) {");
        w.writeln("JOptionPane.showMessageDialog(this,\""
                + entity.getEntityName() + " deleted successfully!\");");
        w.writeln_lr("} else {");
        w.writeln("JOptionPane.showMessageDialog(this,"
                + "\"Unexpected error occured. It might be due to "
                + "faulty internet or duplication in your primary key!\");");
        w.writeln_l("}");
        w.writeln_l("});");
    }

    @Override
    protected final void generateConstructor() {
        w.writeln_r("public Delete" + entity.getEntityName() + "Panel() throws SQLException{");
        w.writeln("setLayout(new BorderLayout());");
        w.writeln("add(new JLabel(\"Delete " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        w.writeln("JPanel topPanel = new JPanel();");
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("topPanel.setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        generateSearchActionListener();
        generateDeleteActionListener();
        w.writeln("add(topPanel,BorderLayout.CENTER);");
        w.writeln("add(bottomPanel, BorderLayout.SOUTH);");
        w.writeln("}");
    }
}
