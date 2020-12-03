package JavaSwingGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddEditCustomersScren extends JFrame {
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
    private JPanel panelRight;
    private JPanel panelLeft;
    private JButton addButton;
    private JPanel panelTop;
    private JCheckBox active;
    private JButton updateButton;
    private JScrollPane customersScroll;
    private DefaultListModel listCustomersModel;

    private Gson gson;
    private ArrayList<String> jsonResults;
    private FileWriter fileWriter;
    private Message messageWindow;

    public AddEditCustomersScren() {

        super("Add / Edit Customers");
        setPreferredSize(new Dimension(900, 650));
        this.setContentPane(this.customersPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        listCustomersModel = new DefaultListModel();
        listOfCustomers.setModel(listCustomersModel);

        updateButton.setEnabled(false);

        gson = new Gson();
        jsonResults = new ArrayList<>();
        fileWriter = new FileWriter();
        messageWindow = new Message();

        refreshList();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer(e);
                refreshList();
                resetTextFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer(e);
                refreshList();
                resetTextFields();
            }
        });

        listOfCustomers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateButton.setEnabled(true);
                populateTextFields(e);
            }
        });
    }

    public void addCustomer(ActionEvent e) {
        try {
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

            updateButton.setEnabled(false);

            messageWindow.showWindow("Added Customer!");
        }
        catch (NumberFormatException excpt) {
            messageWindow.showWindow("Invalid Sales Tax % entry. Please enter a valid number.");
        }

        catch (DateTimeParseException excpt) {
            messageWindow.showWindow("Invalid Date of Birth entry. " + '\n' + "Please format Date of Birth as dd/MM/yyyy (i.e. 08/24/1995), Month between 1-12 and Date corresponds to number of days in respective month.");
        }

    }

    public void updateCustomer(ActionEvent e) {
        int customerNumber = listOfCustomers.getSelectedIndex();
        try {
            Customers customer = getUpdatedCustomer();
            String jsonUpdatedResult = gson.toJson(customer);
            jsonResults.remove(customerNumber);
            jsonResults.add(customerNumber, jsonUpdatedResult);
            fileWriter.writeFile(jsonResults, "Customers.txt");

            messageWindow.showWindow("Updated Customer!");
        }
        catch (NumberFormatException excpt) {
            messageWindow.showWindow("Invalid Sales Tax % entry. Please enter a valid number.");
        }

        catch (DateTimeParseException excpt) {
            messageWindow.showWindow("Invalid Date of Birth entry. " + '\n' + "Please format Date of Birth as dd/MM/yyyy (i.e. 08/24/1995), Month between 1-12 and Date corresponds to number of days in respective month.");
        }
    }

    private Customers getUpdatedCustomer() {
        return new Customers(
                textName.getText(),
                textDoB.getText(),
                textAddress.getText(),
                textPhoneNumber.getText(),
                textEmail.getText(),
                textPaymentInfo.getText(),
                active.isSelected(),
                Double.parseDouble(textSalesTaxPercentage.getText()));
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
