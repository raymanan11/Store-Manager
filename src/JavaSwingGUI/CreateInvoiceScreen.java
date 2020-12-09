package JavaSwingGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
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
    private JButton createInvoiceButton;
    private JButton addProductsButton;
    private JTextField productsList;
    private JTextField textDeliveryCost;
    private JLabel deliveryCostLabel;
    private JButton clearButton;
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

        deliveryCostLabel.setVisible(false);
        textDeliveryCost.setVisible(false);

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


        delivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delivery.isSelected()) {
                    deliveryCostLabel.setVisible(true);
                    textDeliveryCost.setVisible(true);
                }
                else if (!delivery.isSelected()) {
                    deliveryCostLabel.setVisible(false);
                    textDeliveryCost.setVisible(false);
                }
            }
        });

        createInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addInvoice(e);
                }
                catch (NumberFormatException excpt) {
                    messageWindow.showWindow("Please enter a valid number.");
                }
                catch(DateTimeParseException excpt) {
                    messageWindow.showWindow("Please enter a valid invoice date. Please enter date as format MM/dd/yyy");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTextFields();
            }
        });
    }

    public boolean checkName(String customersInput) {
        ArrayList<String> customersGSON = fileReaderWriter.readFile("Customers.txt");
        for (String customerGSON : customersGSON) {
            Customers customer = gson.fromJson(customerGSON, Customers.class);
            if (customer.getCustomerName().equals(customersInput)) return true;
        }
        return false;
    }

    public boolean checkEmployee(String employeeInput) {
        ArrayList<String> employeesJSON = fileReaderWriter.readFile("Employees.txt");
        for (String employeeJSON : employeesJSON) {
            Employee employee = gson.fromJson(employeeJSON, Employee.class);
            if (employee.getEmployeeName().equals(employeeInput)) return true;
        }
        return false;
    }

    public void setActive(double amountPaid) {
        if(totalCost == amountPaid) active.setSelected(false);
    }

    public String getDeliveryCharge() {
        if (delivery.isSelected()) return textDeliveryCost.getText();
        return "0";
    }

    public void refreshList() {
        jsonResults = fileReaderWriter.readFile("Products.txt");
        listProductsModel.removeAllElements();
        for (String productsJSON : jsonResults) {
            Products product = gson.fromJson(productsJSON, Products.class);
            listProductsModel.addElement(product.getProductName());
        }
    }

    public void addInvoice(ActionEvent e) {
        if(!checkName(textCustomerName.getText()) || textCustomerName.getText().equals("")) {
            messageWindow.showWindow("Please enter an existing customer name.");
            return;
        }
        if(!checkEmployee(textEmployeeName.getText()) || textEmployeeName.getText().equals("")) {
            messageWindow.showWindow("Please enter an existing employee name.");
            return;
        }
        if(textTotalCost.getText().equals("") || productsList.getText().equals("")) {
            messageWindow.showWindow("Please select a Product and it's Quantity.");
            return;
        }
        double deliveryCharge = Double.valueOf(getDeliveryCharge());
        double salesTax = Double.parseDouble(textSalesTaxPercentage.getText());
        totalCost = totalCost + deliveryCharge;
        totalCost = totalCost + (totalCost * (salesTax / 100));
        textTotalCost.setText(String.valueOf(totalCost));
        Invoice invoice = new Invoice(textCustomerName.getText(), textEmployeeName.getText(), textInitialDate.getText(), productsOnInvoice, active.isSelected(), totalCost, Double.parseDouble(textAmountPaid.getText()), salesTax, delivery.isSelected(), deliveryCharge);
        String invoiceJSON = gson.toJson(invoice);
        fileReaderWriter.writeFile(invoiceJSON, "Invoices.txt");
        double amountPaid = Double.valueOf(textAmountPaid.getText());
        setActive(amountPaid);
        messageWindow.showWindow("Added Invoice!");

        resetTextFields();
    }

    public void resetTextFields() {
        totalCost = 0;
        productsOnInvoice = new ArrayList<>();
        productToBeAddedToInvoice = "";
        textCustomerName.setText("");
        textEmployeeName.setText("");
        textTotalCost.setText("");
        productsList.setText("");
        textInitialDate.setText("");
        textAmountPaid.setText("");
        textSalesTaxPercentage.setText("");
        active.setSelected(false);
        delivery.setSelected(false);
        deliveryCostLabel.setVisible(false);
        textDeliveryCost.setText("");
        textDeliveryCost.setVisible(false);
        listOfProducts.clearSelection();
    }
}
