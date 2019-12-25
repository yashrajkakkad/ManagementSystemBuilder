package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SetupDB extends JFrame implements ItemListener {

    private static JPanel rootPanel = new JPanel();
    public static JRadioButton local_db;
    public static JRadioButton remote_db;
    public static JTextField server_address;
    public static JTextField username;

    public SetupDB() {
        createView();
        setTitle("Management System Builder");
        setIconImage(new ImageIcon("MSBtransparent.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 480);
        setBackground(new Color(103, 228, 255));
        setVisible(true);
    }

    private void createView() {
        add(rootPanel);
        rootPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 1;
//        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        JLabel db_name_label = new JLabel("Database name: ");
        db_name_label.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(db_name_label,c);
        c.gridx++;
        JTextField db_name = new JTextField(20);
        db_name.setFont(new Font("Century Gothic",Font.PLAIN,24));
        db_name.setText("db_name_goes_here");
        db_name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (db_name.getText().equals("db_name_goes_here")) {
                    db_name.setText("");
                    db_name.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (db_name.getText().isEmpty()) {
                    db_name.setForeground(Color.GRAY);
                    db_name.setText("db_name_goes_here");
                }
            }
        });
        rootPanel.add(db_name,c);

        c.gridy+=2;
        c.gridx = 0;
        JLabel db_type = new JLabel("Database type: ");
        db_type.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(db_type,c);

        c.gridx++;
        local_db = new JRadioButton("Local");
        local_db.setFont(new Font("Century Gothic",Font.PLAIN,24));
        local_db.setSelected(true);
        rootPanel.add(local_db, c);
        c.gridx++;
        remote_db = new JRadioButton("Remote");
        remote_db.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(remote_db, c);
        local_db.addItemListener(this::itemStateChanged);
        remote_db.addItemListener(this::itemStateChanged);

        ButtonGroup db_types = new ButtonGroup();
        db_types.add(local_db);
        db_types.add(remote_db);


        c.gridy+=2;
        c.gridx = 0;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
        JLabel server_address_label = new JLabel("Host Address: ");
        server_address_label.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(server_address_label,c);

//        c.gridy+=2;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
        c.gridx++;
        server_address = new JTextField(20);
        server_address.setFont(new Font("Century Gothic",Font.PLAIN,24));
        server_address.setText("localhost");
        rootPanel.add(server_address,c);

        c.gridy+=2;
        c.gridx = 0;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
        JLabel port_number_label = new JLabel("Port No.: ");
        port_number_label.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(port_number_label,c);

        c.gridx++;
        JTextField port_number = new JTextField(20);
        port_number.setFont(new Font("Century Gothic",Font.PLAIN,24));
        port_number.setText("3306");
        rootPanel.add(port_number,c);

        c.gridy+=2;
        c.gridx = 0;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
        JLabel username_label = new JLabel("Username: ");
        username_label.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(username_label,c);

        c.gridx++;
        username = new JTextField(20);
        username.setFont(new Font("Century Gothic",Font.PLAIN,24));
        username.setText("root");
        rootPanel.add(username,c);

        c.gridy+=2;
        c.gridx = 0;
//        c.gridwidth = 1;
//        c.anchor = GridBagConstraints.CENTER;
        JLabel password_label = new JLabel("Password: ");
        password_label.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(password_label,c);

        c.gridx++;
        JPasswordField password = new JPasswordField(20);
        password.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(password,c);

        c.gridy+=3;
//        c.gridx = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Century Gothic",Font.PLAIN,24));
        rootPanel.add(submitBtn, c);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (db_name.getText().equals("") || server_address.getText().equals("") || port_number.getText().equals("") || username.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out all the required fields");
                }
                File configFile = new File("config.properties");
                Properties default_props = new Properties();
                default_props.setProperty("db_name", "db_name_goes_here");
                default_props.setProperty("host", "localhost");
                default_props.setProperty("port", "3306");
                default_props.setProperty("username", "root");
                default_props.setProperty("password", "");

                Properties props = new Properties(default_props);
                props.setProperty("db_name", db_name.getText());
                props.setProperty("host", server_address.getText());
                props.setProperty("port", "3306");
                props.setProperty("username", username.getText());
                props.setProperty("password", password.getText());
                FileWriter writer = null;
                try {
                    writer = new FileWriter(configFile);
                    props.store(writer, "host config");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == local_db) {
            if (e.getStateChange() == 1) {
                server_address.setText("localhost");
                username.setText("root");
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.getInstalledLookAndFeels();
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SetupDB();
            }
        });
    }
}

/* PROGRAM OUTPUT
 */
