package JavaSwingGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DisplayClosedInvoicesScreen extends JFrame {
    private JPanel invoicesPanel;
    private JTable closedInvoicesTable;
    private ArrayList<ArrayList<String>> invoices;

    FileReaderWriter fileReaderWriter;

    public DisplayClosedInvoicesScreen(ArrayList<ArrayList<String>> invoices){
        super("Display Closed Invoices");
        fileReaderWriter = new FileReaderWriter();
        setPreferredSize(new Dimension(1300, 650));
        this.setContentPane(this.invoicesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.invoices = invoices;
        createTable();
    }

    public void createTable() {

        String[][] invoiceData = new String[invoices.size()][];
        String[] columnNames = {"Invoice ID", "Customer", "Employee", "Payment Type", "Invoice Date",
                "Products", "Status", "Amount Paid", "Tax Rate", "Pickup", "Delivery","Delivery Charge"};

        for (int i = 0; i < invoices.size(); i++) {
            ArrayList<String> row = invoices.get(i);
            invoiceData[i] = row.toArray(new String[row.size()]);
        }

        closedInvoicesTable.setModel(new DefaultTableModel(
                invoiceData,
                columnNames
        ));

        TableColumnModel columns = closedInvoicesTable.getColumnModel();
        columns.getColumn(0).setMinWidth(150);
    }

    //ADD METHOD OF SORTING BY ACTIVE
}


