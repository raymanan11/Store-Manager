package JavaSwingGUI;

import javax.swing.*;

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
    private JButton updateInvoiceButton;
    private JPanel buttonPanel;
    private JButton createInvoiceButton;
    private DefaultListModel listProductsModel;

    private Gson gson;
    private ArrayList<String> jsonResults;
    private FileReaderWriter fileReaderWriter;
    private Message messageWindow;

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

//        refreshList();

//        addProductButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addProducts(e);
//                refreshList();
//            }
//        });
//    }
//
//    public void refreshList() {
//        jsonResults = fileReaderWriter.readFile("Products.txt");
//        listProductsModel.removeAllElements();
//        for (String productsJSON : jsonResults) {
//            Products product = gson.fromJson(productsJSON, Products.class);
//            listProductsModel.addElement(product.getProductName());
//        }
    }
}
