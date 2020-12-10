package JavaSwingGUI;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;import java.util.ArrayList;

public class DisplayOpenInvoicesScreen extends JFrame{
    private JPanel invoicesPanel;
    private JTable openInvoicesTable;
    private ArrayList<ArrayList<String>> invoices;

    FileReaderWriter fileReaderWriter;

    public DisplayOpenInvoicesScreen(ArrayList<ArrayList<String>> invoices){
        super("Display Open Invoices");
        fileReaderWriter = new FileReaderWriter();
        setPreferredSize(new Dimension(1300, 650));
        this.setContentPane(this.invoicesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.invoices = invoices;
        createTable();

        openInvoicesTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int column = openInvoicesTable.getSelectedColumn();
                int row = openInvoicesTable.getSelectedRow();
                System.out.println(openInvoicesTable.getValueAt(row,column));
                System.out.println(openInvoicesTable.getColumnName(column));
                invoices.get(row).set(column,String.valueOf(openInvoicesTable.getValueAt(row,column)));
                System.out.println(invoices);
//                ArrayList<String> invoicesJson;
//                invoicesJson = fileReaderWriter.readFile("Invoices.txt");
//                Gson gson = new Gson();
//                Invoice invoice = gson.fromJson(invoicesJson.get(row), Invoice.class);
                fileReaderWriter.writeFile(invoices.get(row), "Invoices.txt");

            }
        });
    }




    public void createTable() {

        String[][] invoiceData = new String[invoices.size()][];
        String[] columnNames = { "Customer Name", "Employee Name", "Invoice Date",
                "Products", "Status", "Amount Paid", "Tax Rate","Delivery","Delivery Charge", "Total Cost"};

        for (int i = 0; i < invoices.size(); i++) {
            ArrayList<String> row = invoices.get(i);
            invoiceData[i] = row.toArray(new String[row.size()]);
        }

        openInvoicesTable.setModel(new DefaultTableModel(
                invoiceData,
                columnNames
        ));

        TableColumnModel columns = openInvoicesTable.getColumnModel();
        columns.getColumn(0).setMinWidth(125);
        columns.getColumn(1).setMinWidth(125);
        columns.getColumn(3).setMinWidth(400);
    }

    //ADD METHOD OF SORTING BY ACTIVE
}
