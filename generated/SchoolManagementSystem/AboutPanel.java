import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;

public class AboutPanel extends JPanel {
   
   public AboutPanel() {
      setLayout(new BorderLayout());
      add(new JLabel("About Us"), BorderLayout.NORTH);
      add(new JLabel("<html>This is an about panel sample text!</html>", BorderLayout.CENTER)
   }
}
