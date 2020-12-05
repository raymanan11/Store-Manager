package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditNumberOfWarehousesScreen extends JFrame {
    private JPanel panel1;
    private JTextField numberOfWarehouses;
    private JButton updateButton;
    private Message message;
    private FileReaderWriter fileReaderWriter;

    public EditNumberOfWarehousesScreen() {
        super("Edit Number of Warehouses");
        setPreferredSize(new Dimension(700, 500));
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        message = new Message();
        fileReaderWriter = new FileReaderWriter();

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNumberOfWarehouses(e);
            }
        });
    }

    public void updateNumberOfWarehouses(ActionEvent e) {
        try {
            int numberWarehouse = Integer.parseInt(numberOfWarehouses.getText());
            ArrayList<String> numWarehouse = new ArrayList<>();
            numWarehouse.add(String.valueOf(numberWarehouse));
            fileReaderWriter.writeFile(numWarehouse, "NumberOfWarehouses.txt");

            message.showWindow("Updated Number of Warehouses!");
        }
        catch (NumberFormatException excpt) {
            message.showWindow("Invalid number! Please add a valid integer. i.e. 8, 24");
        }
        ArrayList<String> numWarehouse = new ArrayList<>();
        numWarehouse.add(numberOfWarehouses.getText());


    }
}
