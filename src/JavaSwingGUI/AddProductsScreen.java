package JavaSwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddProductsScreen extends JFrame {
    private JPanel panelProducts;
    private JTextField textProduct;
    private JTextField textCostPrice;
    private JTextField textRetailPrice;
    private JTextField textNumberOnHand;
    private JTextField textNumberSold;
    private JTextField textWarehouseNumber;
    private JButton addButton;

    private Gson gson;
    private FileWriter fileWriter;
    private Message messageWindow;

    public AddProductsScreen() {

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
        messageWindow = new Message();
    }

    public void addProducts(ActionEvent e) {
        try {
            ArrayList<String> numberOfWarehouses = fileWriter.readFile("NumberOfWarehouses.txt");
            int numWarehouses = Integer.parseInt(numberOfWarehouses.get(0));

            int inputWarehouse = Integer.parseInt(textWarehouseNumber.getText().trim());

            if (inputWarehouse > 0 && inputWarehouse <= numWarehouses) {
                Products products = new Products(
                        textProduct.getText(),
                        Double.parseDouble(textCostPrice.getText().trim()),
                        Double.parseDouble(textRetailPrice.getText().trim()),
                        Integer.parseInt(textNumberOnHand.getText().trim()),
                        Integer.parseInt(textNumberSold.getText().trim()),
                        inputWarehouse
                );

                String json = gson.toJson(products);

                fileWriter.writeFile(json, "Products.txt");

                messageWindow.showWindow("Added Product");
            }
            else {
                messageWindow.showWindow("Invalid warehouse number. Please edit number of warehouses if you want to proceed.");
            }


        }
        catch(NumberFormatException excpt) {
            System.out.println("Invalid input number!");
            messageWindow.showWindow("Invalid input number!");
        }
    }
}
