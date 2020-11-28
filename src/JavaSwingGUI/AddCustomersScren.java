package JavaSwingGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddCustomersScren extends JFrame {
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

    public AddCustomersScren() {

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

        listOfCustomers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                populateTextFields(e);
            }
        });
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

        resetTextFields();

    }

    private void resetTextFields() {
        textName.setText("");
        textDoB.setText("");
        textAddress.setText("");
        textPhoneNumber.setText("");
        textEmail.setText("");
        textPaymentInfo.setText("");
        active.setSelected(false);
        textSalesTaxPercentage.setText("");
    }

    public void populateTextFields(ListSelectionEvent e) {
        int customerNumber = listOfCustomers.getSelectedIndex();
        if (customerNumber >= 0 && jsonResults.size() > 0) {
            String selectedCustomerJSON = jsonResults.get(customerNumber);
            Customers customer = gson.fromJson(selectedCustomerJSON, Customers.class);
            textName.setText(customer.getCustomerName());
            textDoB.setText(customer.getDoB().format((DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
            textAddress.setText(customer.getCustomerAddress());
            textPhoneNumber.setText(customer.getPhoneNum());
            textEmail.setText(customer.getCustomerEmail());
            textPaymentInfo.setText(customer.getCustomerPaymentInfo());
            active.setSelected(customer.isActive());
            textSalesTaxPercentage.setText(String.valueOf(customer.getSalesTaxPercentage()));
        }
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
