package JavaSwingGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateInvoiceScreen extends JFrame{
    private JPanel createInvoicePanel;
    private JPanel panelTop;
    private JLabel customersTitle;
    private JPanel panelLeft;
    private JScrollPane productsScroll;
    private JList listOfProducts;
    private JPanel panelRight;
    private JTextField textCustomerName;
    private JTextField textEmployeeName;
    private JTextField textTotalCost;
    private JTextField textInitialDate;
    private JTextField textAmountPaid;
    private JTextField textSalesTaxPercentage;
    private JCheckBox active;
    private JCheckBox delivery;
    private JTextField quantityOfProduct;
    private JPanel addProductPanel;
    private JPanel buttonPanel;
    // save the Arraylist productsOnInvoice
    private JButton createInvoiceButton;
    private JButton addProductsButton;
    private JTextField productsList;
    private DefaultListModel listProductsModel;

    private Gson gson;
    private ArrayList<String> jsonResults;
    private ArrayList<String> productsOnInvoice;
    private FileReaderWriter fileReaderWriter;
    private Message messageWindow;

    private Products currentSelectedProduct;
    private String productToBeAddedToInvoice;
    private double totalCost;

    public CreateInvoiceScreen(){

        super("Create Invoice");
        setPreferredSize(new Dimension(900, 650));
        this.setContentPane(this.createInvoicePanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        listProductsModel = new DefaultListModel();
        listOfProducts.setModel(listProductsModel);

        gson = new Gson();
        jsonResults = new ArrayList<>();
        fileReaderWriter = new FileReaderWriter();
        messageWindow = new Message();

        currentSelectedProduct = null;
        productToBeAddedToInvoice = "";
        productsOnInvoice = new ArrayList<>();

        refreshList();

        listOfProducts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String productsJSON = jsonResults.get(listOfProducts.getSelectedIndex());
                    currentSelectedProduct = gson.fromJson(productsJSON, Products.class);
                    productToBeAddedToInvoice = currentSelectedProduct.getProductName();
                    System.out.println(productToBeAddedToInvoice);
                }
            }
        });

        addProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (productToBeAddedToInvoice.equals("")) {
                        messageWindow.showWindow("Please select a product before proceeding.");
                        return;
                    }
                    if (quantityOfProduct.getText().equals("")) {
                        messageWindow.showWindow("Please enter a number for Quantity of Product before proceeding.");
                        return;
                    }
                    int quantity = Integer.parseInt(quantityOfProduct.getText());
                    productsOnInvoice.add(productToBeAddedToInvoice + "  " + quantity + "x");
                    productsList.setText(productsOnInvoice.toString().substring(1, productsOnInvoice.toString().length() - 1));
                    totalCost = totalCost + (currentSelectedProduct.getRetailPrice() * quantity);
                    textTotalCost.setText(String.valueOf(totalCost));
                    quantityOfProduct.setText("");
                }
                catch (NumberFormatException excpt) {
                    messageWindow.showWindow("Please enter a valid number for Quantity of Product.");
                }
            }
        });
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
