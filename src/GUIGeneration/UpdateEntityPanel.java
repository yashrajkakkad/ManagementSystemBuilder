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

//    private void generateLabels() {
//        entity.getEntityMembers().forEach((entityMember) -> {
//            w.writeln("private JLabel " + entityMember.getValue()
//                    + "Label = new JLabel(\"" + entityMember.getValue() + "\");");
//        });
//    }
//    private void generateTextFields() {
//        entity.getEntityMembers().forEach((entityMember) -> {
//            w.writeln("private JTextField " + entityMember.getValue()
//                    + "TextField = new JTextField();");
//        });
//    }
//    private void generateSearchButton() {
//        w.writeln("private JButton searchButton = new JButton(\"searchButton\");");
//    }
//    private void generateUpdateButton() {
//        w.writeln("JButton UpdateButton = new JButton(\"Update " + entity.getEntityName() + "\");");
//    }
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
//                .append();
//        entity.getEntityMembers().forEach((entityMember) -> {
//            viewFunctionCall.append("\" + ").append(entityMember.getKey()).append(",").append(entityMember.getValue()).append(" + \", ");
//           w.writeln("JTextField "+entityMember.getValue()+"TextField.setText("+entity.getEntityName()+".get("+entityMember.getValue().substring(0, 1).toUpperCase() + entityMember.getValue().substring(1)+"));");
//        });
//        viewFunctionCall.delete(viewFunctionCall.length()-6,viewFunctionCall.length());                
        w.writeln_l("}});");
//        w.writeln_r("searchButton.setOnAction((e) -> {");
//        w.writeln_r("if(" + entity.getEntityMembers().get(0).getValue()
//                + "TextField.getText().equals(\"\")) {");
//        w.writeln("JOptionPane.showMessageDialog(this,"
//                + "\"Please enter a valid value to search!\");");
//        w.writeln_lr("} else {");
//        StringBuilder selectQuery = new StringBuilder("SELECT * FROM tbl_");
//        selectQuery.append(entity.getEntityName().toLowerCase())
//                .append(" WHERE ")
//                .append(entity.getEntityMembers().get(0).getValue())
//                .append(" = ");
//        if (entity.getEntityMembers().get(0).getKey().equals("int")
//                || entity.getEntityMembers().get(0).getKey().equals("double")) {
//            w.writeln("StringBuilder selectQuery = \"" + selectQuery + "\" + "
//                    + entity.getEntityMembers().get(0).getValue()
//                    + "TextField.getText();");
//        } else {
//            w.writeln("StringBuilder selectQuery = \"" + selectQuery + "'\" + "
//                    + entity.getEntityMembers().get(0).getValue()
//                    + "TextField.getText() + \"'\";");
//        }
//        w.writeln("DatabaseUtil.rs "
//                + "= DatabaseUtil.stmt.executeQuery(selectQuery);");
//        entity.getEntityMembers().forEach((entityMember) -> {
//            StringBuilder getFromResultSet = new StringBuilder();
//            getFromResultSet.append("rs.get")
//                    .append(Character.toUpperCase(entityMember.getKey().charAt(0)))
//                    .append(entityMember.getKey().substring(1))
//                    .append("(\"").append(entityMember.getValue()).append("\")");
//            StringBuilder setTextField = new StringBuilder();
//            setTextField.append(entityMember.getValue()).append("TextField.setText");
//            w.writeln(setTextField.toString() + "("
//                    + getFromResultSet.toString() + " + \"\");");
//        });
//        w.writeln_l("}");
//        w.writeln_l("});");
    }
//

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
//                addFunctionCall.append()
//            addFunctionCall.append("\" + ").append(entityMember.getValue()).append("TextField.getText()").append(" + \", ");
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
//            updateFunctionCall.append(", ").append(entity.getEntityMembers().get(0).getValue()).append("TextField.getText()");
            updateFunctionCall.append(");");
        w.writeln("boolean isUpdated = false;");
        w.writeln_r("try {");
        w.writeln(updateFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
//            addFunctionCall.delete(addFunctionCall.length()-6, addFunctionCall.length());
            //addFunctionCall.deleteCharAt(addFunctionCall.length()-1);
//            addFunctionCall.append("+\",\"+").append(entity.getEntityMembers().get(0).getValue());
//            addFunctionCall.append(");");
//            w.writeln(addFunctionCall.toString());
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
//        w.writeln_r("UpdateButton.setOnAction((e) -> {");
//        StringBuilder updateQuery = new StringBuilder("UPDATE tbl_");
//        updateQuery.append(entity.getEntityName().toLowerCase()).append(" SET ");
//        entity.getEntityMembers().forEach((entityMember) -> {
//            updateQuery.append(entityMember.getValue()).append(" = ")
//                    .append("\" + ");
//            if (entityMember.getKey().equals("String")
//                    || entityMember.getKey().equals("char")) {
//                //More code to come
//                //Here we go!
//                updateQuery.append("'\" + ").append(entityMember.getValue())
//                        .append("TextField.getText()").append(" + \"', ");
//            } else {
//                updateQuery.append("\" + ").append(entityMember.getValue())
//                        .append("TextField.getText()").append(" + \", ");
//            }
//        });
//        w.writeln("int i = DatabaseUtil.stmt.executeUpdate(insertQuery);");
//        w.writeln_r("if(i==1) {");
//        w.writeln("JOptionPane.showMessageDialog(this,\""
//                + entity.getEntityName() + " updated successfully!\");");
//        w.writeln_lr("} else if {");
//        w.writeln("JOptionPane.showMessageDialog(this,"
//                + "\"Unexpected error occured. It might be due to "
//                + "faulty internet or duplication in your primary key!\");");
//        w.writeln_l("}");
//        w.writeln_l("});");
    

    @Override

    protected final void generateConstructor() {
        w.writeln_r("public Update" + entity.getEntityName() + "Panel() throws SQLException{");
        w.writeln("setLayout(new BorderLayout());");
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
