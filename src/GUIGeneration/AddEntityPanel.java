package GUIGeneration;

import CodeGeneration.*;
import java.io.IOException;

public class AddEntityPanel extends CRUDPanel {

    public AddEntityPanel(Entity entity) throws IOException {
        super(entity);
        super.initialize("Add" + entity.getEntityName() + "Panel");
        generateComponents();
        generateConstructor();
        super.finish("Add" + entity.getEntityName() + "Panel");
    }

    @Override
    protected final void generateComponents() {
        generateLabels();
        generateTextFields();
        generateButton("Add");
    }

    @Override
    protected void generateAddComponents() {
        entity.getEntityMembers().forEach((entityMember) -> {
            w.writeln("topPanel.add(" + entityMember.getValue() + "Label);");
            w.writeln("topPanel.add(" + entityMember.getValue() + "TextField);");
        });
        w.writeln("bottomPanel.add(AddButton);");
        w.writeln("AddButton.setMaximumSize(AddButton.getPreferredSize());");
    }

    private void generateButtonActionListener() {
        w.writeln_r("AddButton.addActionListener((e) -> {");
        StringBuilder addFunctionCall = new StringBuilder("isAdded = "+entity.getEntityName()+"CRUD.add" +entity.getEntityName() + "(");
        addFunctionCall.append("new ").append(entity.getEntityName()).append("(");
        entity.getEntityMembers().forEach((entityMember) -> {
              switch(entityMember.getKey()) {
                  case "int":
                      addFunctionCall.append("Integer.parseInt(")
                              .append(entityMember.getValue())
                              .append("TextField.getText())").append(", ");
                      break;
                  case "char":
                      addFunctionCall.append(entityMember.getValue())
                              .append("TextField.getText().charAt(0)").append(", ");
                      break;
                  case "double":
                      addFunctionCall.append("Double.parseDouble(")
                              .append(entityMember.getValue())
                              .append("TextField.getText())").append(", ");                      
                      break;
                  case "String":
                      addFunctionCall.append(entityMember.getValue())
                              .append("TextField.getText()").append(", ");
                      break;
              }
        });        
        addFunctionCall.deleteCharAt(addFunctionCall.length()-1);
        addFunctionCall.deleteCharAt(addFunctionCall.length()-1);
        addFunctionCall.append("));");
        w.writeln("boolean isAdded = false;");
        w.writeln_r("try {");
        w.writeln(addFunctionCall.toString());
        w.writeln_lr("} catch(SQLException ex) {");
        w.writeln("ex.printStackTrace();");
        w.writeln_l("}");
        w.writeln_r("if(isAdded) {");
        w.writeln("JOptionPane.showMessageDialog(this,\"" 
                + entity.getEntityName() + " added successfully!\");");
        w.writeln_lr("} else {");
        w.writeln("JOptionPane.showMessageDialog(this,"
                + "\"Unexpected error occured. It might be due to "
                + "faulty internet or duplication in your primary key!\");");
        w.writeln_l("}");        
        w.writeln_l("});");        
    }
    
    @Override
    protected final void generateConstructor() {
        w.writeln_r("public Add" + entity.getEntityName() + "Panel() throws SQLException{");
        w.writeln("setLayout(new BorderLayout());");
        entity.getEntityMembers().forEach(entityMember -> {
            w.writeln(entityMember.getValue() + "TextField.setMaximumSize(" + entityMember.getValue() + "TextField.getPreferredSize());");
        });
        w.writeln("JPanel topPanel = new JPanel();");
        w.writeln("JPanel bottomPanel = new JPanel();");
        w.writeln("add(new JLabel(\"Add " + entity.getEntityName() + "\"), BorderLayout.NORTH);");
        w.writeln("topPanel.setLayout(new GridLayout(" + entity.getEntityMembers().size()
                + "," + entity.getEntityMembers().size() + ",10,10));");
        generateAddComponents();
        generateButtonActionListener();
        w.writeln("add(topPanel,BorderLayout.CENTER);");
        w.writeln("add(bottomPanel, BorderLayout.SOUTH);");
        w.writeln_l("}");
    }
}
