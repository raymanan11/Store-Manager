package JavaSwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textActive;
    private JTextField textSalesTaxPercentage;
    private JScrollBar scrollBar1;
    private JPanel panelRight;
    private JPanel panelLeft;
    private JButton addButton;
    private JPanel panelTop;

    public AddCustomers() {
        super("JavaSwingGUI.Customers");
        this.setContentPane(this.customersPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add to JavaSwingGUI.Customers.txt file
                // refresh list so name is updated
                addCustomer(e);
            }
        });
    }

    public void addCustomer(ActionEvent e) {

    }
}
