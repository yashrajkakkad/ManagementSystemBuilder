package GUIGeneration;

import CodeGeneration.*;
import java.io.IOException;
import java.util.Iterator;
import javafx.util.Pair;

public class UpdateEntityPanel extends CRUDPanel {

    public UpdateEntityPanel(Entity entity) throws IOException {
        super(entity);
        super.initialize("Update" + entity.getEntityName() + "Panel");
        generateComponents();
        generateConstructor();
        super.finish("Update" + entity.getEntityName() + "Panel");
    }

    private void generateSubPanel() {
        w.writeln("JPanel subPanel = new JPanel();");
    }

    @Override
    protected final void generateComponents() {
        generateLabels();
        generateTextFields();
        generateButton("Search");
        generateButton("Update");
        generateSubPanel();
    }

    @Override
    protected void generateAddComponents() {
        Iterator<Pair<String, String>> itr
                = entity.getEntityMembers().iterator();
        String firstValue = itr.next().getValue();
        w.writeln("topPanel.add(" + firstValue + "Label);");
        w.writeln("subPanel.add(" + firstValue + "TextField);");
        w.writeln("subPanel.add(SearchButton);");
        w.writeln("topPanel.add(subPanel);");
        while (itr.hasNext()) {
            String nextValue = itr.next().getValue();
            w.writeln("topPanel.add(" + nextValue + "Label);");
            w.writeln("topPanel.add(" + nextValue + "TextField);");
        }
        w.writeln("bottomPanel.add(UpdateButton);");
        w.writeln("UpdateButton.setMaximumSize"
                + "(UpdateButton.getPreferredSize());");
    }

    private void generateSearchActionListener() {
        w.writeln_r("SearchButton.addActionListener((e) -> {");
        StringBuilder viewFunctionCall = new StringBuilder("");
        viewFunctionCall.append("retrieve").append(entity.getEntityName())
                .append(" = ").append(entity.getEntityName()).append("CRUD.view").append(entity.getEntityName()).append("(");
        switch (entity.getEntityMembers().get(0).getKey()) {
            case "int":
                viewFunctionCall.append("Integer.parseInt(").append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText())");
                break;
            case "double":
                viewFunctionCall.append("Double.parseDouble(").append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText())");
                break;
            case "char":
                viewFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText().charAt(0)");
                break;
            case "String":
                viewFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText()");
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
        Iterator<Pair<String,String>> iterator = entity.getEntityMembers().iterator();
//        iterator.next();
        while(iterator.hasNext()) {
            Pair<String,String> tempPair = iterator.next();
            w.writeln(tempPair.getValue() + "TextField.setText(\"\" + retrieve"
                    + entity.getEntityName() + ".get" 
                    + Character.toUpperCase(((String)tempPair.getValue()).charAt(0)) 
                    + ((String)tempPair.getValue()).substring(1) + "());");
        }
        w.writeln_l("}});");
    }

    private void generateUpdateActionListener() {
        w.writeln_r("UpdateButton.addActionListener((e) -> {");
        StringBuilder updateFunctionCall = new StringBuilder("isUpdated = " + entity.getEntityName() + "CRUD.update" + entity.getEntityName() + "(");
        updateFunctionCall.append("new ").append(entity.getEntityName()).append("(");
        entity.getEntityMembers().forEach((entityMember) -> {
            switch(entityMember.getKey()) {
            case "int":
                updateFunctionCall.append("Integer.parseInt(").append(entityMember.getValue()).append("TextField.getText())");
                break;
            case "double":
                updateFunctionCall.append("Double.parseDouble(").append(entityMember.getValue()).append("TextField.getText())");
                break;
            case "char":
                updateFunctionCall.append(entityMember.getValue()).append("TextField.getText().charAt(0)");
                break;
            case "String":
                updateFunctionCall.append(entityMember.getValue()).append("TextField.getText()");
                break;
            }
            updateFunctionCall.append(", ");
            });        
            updateFunctionCall.deleteCharAt(updateFunctionCall.length()-1);
            updateFunctionCall.deleteCharAt(updateFunctionCall.length()-1);
            updateFunctionCall.append("), ");
            switch(entity.getEntityMembers().get(0).getKey()) {
            case "int":
                updateFunctionCall.append("Integer.parseInt(").append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText())");
                break;
            case "double":
                updateFunctionCall.append("Double.parseDouble(").append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText())");
                break;
            case "char":
                updateFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText().charAt(0)");
                break;
            case "String":
                updateFunctionCall.append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText()");
                break;
        }
            updateFunctionCall.append(");");
        w.writeln("boolean isUpdated = false;");
        w.writeln_r("try {");
        w.writeln(updateFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
            w.writeln_r("if(isUpdated) {");
            w.writeln("JOptionPane.showMessageDialog(this,\"" 
                  + entity.getEntityName() + " Updated successfully!\");");
            w.writeln_lr("} else {");
            w.writeln("JOptionPane.showMessageDialog(this,"
                    + "\"Unexpected error occured. It might be due to "
                    + "faulty internet or duplication in your primary key!\");");
            w.writeln_l("}");        
            w.writeln_l("});");  
            }
    

    @Override
    protected final void generateConstructor() {
        w.writeln_r("public Update" + entity.getEntityName() + "Panel() throws SQLException{");
        w.writeln("setLayout(new BorderLayout());");
        entity.getEntityMembers().forEach(entityMember -> {
            w.writeln(entityMember.getValue() + "TextField.setMaximumSize(" + entityMember.getValue() + "TextField.getPreferredSize());");
        });
        w.writeln("add(new JLabel(\"Update " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        w.writeln("JPanel topPanel = new JPanel();");
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("topPanel.setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        generateSearchActionListener();
        generateUpdateActionListener();
        w.writeln("add(topPanel,BorderLayout.CENTER);");
        w.writeln("add(bottomPanel, BorderLayout.SOUTH);");
        w.writeln_l("}");
    }
}
