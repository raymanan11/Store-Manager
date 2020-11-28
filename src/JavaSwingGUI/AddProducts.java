package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import com.google.gson.Gson;

public class AddProducts extends JFrame {
    private JPanel panelProducts;
    private JTextField textProduct;
    private JTextField textCostPrice;
    private JTextField textRetailPrice;
    private JTextField textNumberOnHand;
    private JTextField textNumberSold;
    private JTextField textWarehouseNumber;
    private JButton addButton;

    private Gson gson;
    public FileWriter fileWriter;

    public AddProducts() {

        super("Add Products");
        this.setContentPane(this.panelProducts);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProducts(e);
            }
        });

        gson = new Gson();
        fileWriter = new FileWriter();
    }

    public void addProducts(ActionEvent e) {
        try {
            Products products = new Products(
                    textProduct.getText(),
                    Double.parseDouble(textCostPrice.getText().trim()),
                    Double.parseDouble(textRetailPrice.getText().trim()),
                    Integer.parseInt(textNumberOnHand.getText().trim()),
                    Integer.parseInt(textNumberSold.getText().trim()),
                    Integer.parseInt(textWarehouseNumber.getText().trim())
            );

            String json = gson.toJson(products);

            fileWriter.writeFile(json, "Products.txt");

        }
        catch(NumberFormatException excpt) {
            System.out.println("Invalid input number!");
            JLabel error = new JLabel("Invalid input number");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(error, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(null, panel, "Error Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
