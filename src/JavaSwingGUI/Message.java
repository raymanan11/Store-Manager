package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;

public class Message {
    public void showWindow(String errorMessage) {
        JLabel error = new JLabel(errorMessage);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(error, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, panel, "Error Message", JOptionPane.PLAIN_MESSAGE);
    }
}
