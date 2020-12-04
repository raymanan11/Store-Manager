package JavaSwingGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddEditProductsScreen extends JFrame {
    private JPanel panelProducts;
    private JTextField textProduct;
    private JTextField textCostPrice;
    private JTextField textRetailPrice;
    private JTextField textNumberOnHand;
    private JTextField textNumberSold;
    private JTextField textWarehouseNumber;
    private JButton addButton;
    private JButton updateButton;
    private JList productsList;
    private DefaultListModel listProductsModel;


    private Gson gson;
    private ArrayList<String> jsonResults;
    private FileReaderWriter fileReaderWriter;
    private Message messageWindow;

    public AddEditProductsScreen() {

        super("Add / Edit Products");
        setPreferredSize(new Dimension(900, 650));
        this.setContentPane(this.panelProducts);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        listProductsModel = new DefaultListModel();
        productsList.setModel(listProductsModel);

        updateButton.setEnabled(false);

        gson = new Gson();
        jsonResults = new ArrayList<>();
        fileReaderWriter = new FileReaderWriter();
        messageWindow = new Message();

        refreshList();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProducts(e);
                refreshList();
                resetTextFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct(e);
                refreshList();
                resetTextFields();
            }
        });
        productsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateButton.setEnabled(true);
                populateTextFields(e);
            }
        });
    }

    public void addProducts(ActionEvent e) {
        try {
            ArrayList<String> numberOfWarehouses = fileReaderWriter.readFile("NumberOfWarehouses.txt");
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

                fileReaderWriter.writeFile(json, "Products.txt");

                messageWindow.showWindow("Added Product!");
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

    public void updateProduct(ActionEvent e) {
        int customerNumber = productsList.getSelectedIndex();
        try {
            Products products = getUpdatedProducts();
            String jsonUpdatedResult = gson.toJson(products);
            jsonResults.remove(customerNumber);
            jsonResults.add(customerNumber, jsonUpdatedResult);
            fileReaderWriter.writeFile(jsonResults, "Products.txt");

            messageWindow.showWindow("Updated Product!");
        }
        catch (NumberFormatException excpt) {
            messageWindow.showWindow("Invalid input number!");
        }

    }

    public Products getUpdatedProducts() {
        return new Products(
                textProduct.getText(),
                Double.parseDouble(textCostPrice.getText()),
                Double.parseDouble(textRetailPrice.getText()),
                Integer.parseInt(textNumberOnHand.getText()),
                Integer.parseInt(textNumberSold.getText()),
                Integer.parseInt(textWarehouseNumber.getText())
        );
    }

    public void resetTextFields() {
        textProduct.setText("");
        textCostPrice.setText("");
        textRetailPrice.setText("");
        textNumberOnHand.setText("");
        textNumberSold.setText("");
        textWarehouseNumber.setText("");
    }

    public void populateTextFields(ListSelectionEvent e) {
        int productNumber = productsList.getSelectedIndex();
        if (productNumber >= 0 && jsonResults.size() > 0) {
            String selectedProductsJSON = jsonResults.get(productNumber);
            Products products = gson.fromJson(selectedProductsJSON, Products.class);
            textProduct.setText(products.getProductName());
            textCostPrice.setText(String.valueOf(products.getCostPrice()));
            textRetailPrice.setText(String.valueOf(products.getRetailPrice()));
            textNumberOnHand.setText(String.valueOf(products.getQuantityOnHand()));
            textNumberSold.setText(String.valueOf(products.getQuantitySold()));
            textWarehouseNumber.setText(String.valueOf(products.getWarehouseNumber()));
        }
    }

    public void refreshList() {
        jsonResults = fileReaderWriter.readFile("Products.txt");
        listProductsModel.removeAllElements();
        for (String productsJSON : jsonResults) {
            Products product = gson.fromJson(productsJSON, Products.class);
            listProductsModel.addElement(product.getProductName());
        }
    }
}
