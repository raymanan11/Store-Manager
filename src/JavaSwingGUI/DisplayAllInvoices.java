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

public class DisplayAllInvoices extends JFrame {

    private JTable invoicesTable;
    private JPanel invoicesPanel;
    private ArrayList<ArrayList<String>> invoices;

    FileReaderWriter fileReaderWriter;

    public DisplayAllInvoices(ArrayList<ArrayList<String>> invoices) {
        super("Display All Invoices");
        fileReaderWriter = new FileReaderWriter();
        setPreferredSize(new Dimension(1300, 650));
        this.setContentPane(this.invoicesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.invoices = invoices;
        createTable();

        invoicesTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                ArrayList<String> invoicesJSON = fileReaderWriter.readFile("Invoices.txt");
                int column = invoicesTable.getSelectedColumn();
                int row = invoicesTable.getSelectedRow();
                System.out.println(invoicesTable.getValueAt(row,column));
                System.out.println(invoicesTable.getColumnName(column));
                invoices.get(row).set(column,String.valueOf(invoicesTable.getValueAt(row,column)));
                ArrayList<String> currentInvoice = invoices.get(row);
                System.out.println(currentInvoice);
                Invoice invoice = new Invoice(currentInvoice.get(0), currentInvoice.get(1), currentInvoice.get(2), getInvoiceArrayList(currentInvoice.get(3)),
                    checkBoolean(currentInvoice.get(4)), Double.parseDouble(currentInvoice.get(5)), Double.parseDouble(currentInvoice.get(6)),
                Double.parseDouble(currentInvoice.get(8)), checkBoolean(currentInvoice.get(7)), Double.parseDouble(currentInvoice.get(9)));
                Gson gson = new Gson();
                String invoiceJSON = gson.toJson(invoice, Invoice.class);
                invoicesJSON.set(row, invoiceJSON);
                invoicesJSON.remove(row + 1);
                fileReaderWriter.writeFile(invoiceJSON, "Invoices.txt");
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

        invoicesTable.setModel(new DefaultTableModel(
                invoiceData,
                columnNames
        ));

        TableColumnModel columns = invoicesTable.getColumnModel();
        columns.getColumn(0).setMinWidth(125);
        columns.getColumn(1).setMinWidth(125);
        columns.getColumn(3).setMinWidth(400);
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


}

