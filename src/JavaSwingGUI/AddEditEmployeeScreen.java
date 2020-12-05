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

public class AddEditEmployeeScreen extends JFrame{
    private JPanel employeesPanel;
    private JPanel panelTop;
    private JLabel employeesTitle;
    private JPanel panelLeft;
    private JScrollPane employeesScroll;
    private JList listOfEmployees;
    private JPanel panelRight;
    private JTextField textName;
    private JTextField textDoB;
    private JTextField textAddress;
    private JTextField textPhoneNumber;
    private JTextField textEmail;
    private JTextField textMethodOfPayment;
    private JTextField textCommisionPercentage;
    private JButton addButton;
    private JButton updateButton;
    private JTextField textTotalSales;
    private JTextField textSSID;
    private DefaultListModel listEmployeeModel;

    private Gson gson;
    private ArrayList<String> jsonResults;
    private FileReaderWriter fileReaderWriter;
    private Message messageWindow;

    public AddEditEmployeeScreen() {

        super("Add / Edit Employees");
        setPreferredSize(new Dimension(900, 650));
        this.setContentPane(this.employeesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        listEmployeeModel = new DefaultListModel();
        listOfEmployees.setModel(listEmployeeModel);

        updateButton.setEnabled(false);

        gson = new Gson();
        jsonResults = new ArrayList<>();
        fileReaderWriter = new FileReaderWriter();
        messageWindow = new Message();

        refreshList();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee(e);
                refreshList();
                resetTextFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee(e);
                refreshList();
                resetTextFields();
            }
        });

        listOfEmployees.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateButton.setEnabled(true);
                populateTextFields(e);
            }
        });
    }

    public void addEmployee(ActionEvent e){
        try{
            Employee employee = new Employee(
                    textName.getText(),
                    textDoB.getText(),
                    textAddress.getText(),
                    textPhoneNumber.getText(),
                    textEmail.getText(),
                    textMethodOfPayment.getText(),
                    textTotalSales.getText(),
                    textSSID.getText(),
                    Double.parseDouble(textCommisionPercentage.getText()));

            String json = gson.toJson(employee);

            fileReaderWriter.writeFile(json, "Employee.txt");

            updateButton.setEnabled(false);

            messageWindow.showWindow("Added Employee!");
        }
        catch(NumberFormatException excpt){
            messageWindow.showWindow("Invalid Commision % entry. Please enter a valid number.");
        }

        catch(DateTimeParseException excpt){
            messageWindow.showWindow("Invalid Date of Birth entry. " + '\n' + "Please format Date of Birth as dd/MM/yyyy (i.e. 08/24/1995), Month between 1-12 and Date corresponds to number of days in respective month.")
        }

    }

    public void updateEmployee(ActionEvent e){
        int employeeNumber = listOfEmployees.getSelectedIndex();
        try {
            Employee employee = getUpdatedEmployee();
            String jsonUpdatedResult = gson.toJson(employee);
            jsonResults.remove(employeeNumber);
            jsonResults.add(employeeNumber, jsonUpdatedResult);
            fileReaderWriter.writeFile(jsonResults, "Employees.txt");

            messageWindow.showWindow("Updated Employees!");
        }
        catch (NumberFormatException excpt) {
            messageWindow.showWindow("Invalid Commision % entry. Please enter a valid number.");
        }

        catch (DateTimeParseException excpt) {
            messageWindow.showWindow("Invalid Date of Birth entry. " + '\n' + "Please format Date of Birth as dd/MM/yyyy (i.e. 08/24/1995), Month between 1-12 and Date corresponds to number of days in respective month.");
        }
    }

    private Employee getUpdatedEmployee() {
        return new Employee(
                textName.getText(),
                textDoB.getText(),
                textAddress.getText(),
                textPhoneNumber.getText(),
                textEmail.getText(),
                textMethodOfPayment.getText(),
                textTotalSales.getText(),
                textSSID.getText(),
                Double.parseDouble(textCommisionPercentage.getText()));
    }

    private void resetTextFields() {
        textName.setText("");
        textDoB.setText("");
        textAddress.setText("");
        textPhoneNumber.setText("");
        textEmail.setText("");
        textMethodOfPayment.setText("");
        textTotalSales.setText("");
        textSSID.setText("");
        textCommisionPercentage.setText("");
    }

    public void populateTextFields(ListSelectionEvent e) {
        int employeeNumber = listOfEmployees.getSelectedIndex();
        if (employeeNumber >= 0 && jsonResults.size() > 0) {
            String selectedEmployeeJSON = jsonResults.get(employeeNumber);
            Employee employee = gson.fromJson(selectedEmployeeJSON, Employee.class);
            textName.setText(employee.getEmployeeName());
            textDoB.setText(employee.getEmployeeDoB().format((DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
            textAddress.setText(employee.getEmployeeAddress());
            textPhoneNumber.setText(employee.getEmployeePhoneNumber());
            textEmail.setText(employee.getEmployeeEmail());
            textCommisionPercentage.setText(String.valueOf(employee.getEmployeeCommissionPercentage()));
            textSSID.setText(employee.getEmployeeSSID());
            textMethodOfPayment.setText(employee.getEmployeeMethodOfPayment());
            textTotalSales.setText(employee.getEmployeeMethodOfPayment());

        }
    }

    public void refreshList() {
        jsonResults = fileReaderWriter.readFile("Employees.txt");
        listEmployeeModel.removeAllElements();
        for (String employeeJSON : jsonResults) {
            Employee employee = gson.fromJson(employeeJSON, Employee.class);
            listEmployeeModel.addElement(employee.getEmployeeName());
        }
    }


}
