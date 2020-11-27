package JavaSwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddCustomers extends JFrame {
    private JPanel customersPanel;
    private JLabel customersTitle;
    private JList listOfCustomers;
    private JTextField textName;
    private JTextField textDoB;
    private JTextField textAddress;
    private JTextField textPhoneNumber;
    private JTextField textEmail;
    private JTextField textPaymentInfo;
    private JTextField textSalesTaxPercentage;
    private JScrollBar scrollBar1;
    private JPanel panelRight;
    private JPanel panelLeft;
    private JButton addButton;
    private JPanel panelTop;
    private JCheckBox active;
    private DefaultListModel listCustomersModel;

    private Gson gson;
    private ArrayList<String> jsonResults;

    public FileWriter fileWriter;

    public AddCustomers() {

        super("Customers");
        this.setContentPane(this.customersPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        listCustomersModel = new DefaultListModel();
        listOfCustomers.setModel(listCustomersModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add to JavaSwingGUI.Customers.txt file
                // refresh list so name is updated
                addCustomer(e);
            }
        });

        gson = new Gson();
        fileWriter = new FileWriter();

        refreshList();
    }

    public void addCustomer(ActionEvent e) {
        Customers customer = new Customers(
                textName.getText(),
                textDoB.getText(),
                textAddress.getText(),
                textPhoneNumber.getText(),
                textEmail.getText(),
                textPaymentInfo.getText(),
                active.isSelected(),
                Double.parseDouble(textSalesTaxPercentage.getText()));

        String json = gson.toJson(customer);

        fileWriter.writeFile(json, "Customers.txt");

        refreshList();

    }

    public void refreshList() {
        jsonResults = fileWriter.readFile("Customers.txt");
        listCustomersModel.removeAllElements();
        for (String customerJSON : jsonResults) {
            Customers customer = gson.fromJson(customerJSON, Customers.class);
            listCustomersModel.addElement(customer.getCustomerName());
        }
    }

}
