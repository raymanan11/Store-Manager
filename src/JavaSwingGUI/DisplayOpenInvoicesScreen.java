package JavaSwingGUI;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PropertyPermission;

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
                ArrayList<String> invoicesJSON = fileReaderWriter.readFile("Invoices.txt");
                int column = openInvoicesTable.getSelectedColumn();
                int row = openInvoicesTable.getSelectedRow();
                System.out.println(openInvoicesTable.getValueAt(row,column));
                System.out.println(openInvoicesTable.getColumnName(column));
                invoices.get(row).set(column,String.valueOf(openInvoicesTable.getValueAt(row,column)));
                ArrayList<String> currentInvoice = invoices.get(row);
                Invoice invoice = new Invoice(currentInvoice.get(0), currentInvoice.get(1), currentInvoice.get(2), getInvoiceArrayList(currentInvoice.get(3)),
                        checkBoolean(currentInvoice.get(4)), Double.parseDouble(currentInvoice.get(5)), Double.parseDouble(currentInvoice.get(6)),
                        Double.parseDouble(currentInvoice.get(7)), checkBoolean(currentInvoice.get(8)), Double.parseDouble(currentInvoice.get(9)));
                Gson gson = new Gson();
                String invoiceJSON = gson.toJson(invoice, Invoice.class);
                invoicesJSON.set(row, invoiceJSON);
                fileReaderWriter.writeFile(invoiceJSON, "Invoices.txt");

            }
        });
    }

    public ArrayList<String> getInvoiceArrayList(String invoice) {
        String[] invoiceArr = invoice.split(" ");
        ArrayList<String> arrayList = new ArrayList<String>();
        Collections.addAll(arrayList, invoiceArr);
        return arrayList;
    }

    public boolean checkBoolean(String bool) {
        if (bool.equals("true")) return true;
        return false;
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
