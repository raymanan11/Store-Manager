package JavaSwingGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DisplayProductsScreen extends JFrame {
    private JPanel productsPanel;
    private JTable productsTable;
    private ArrayList<ArrayList<String>> products;

    FileReaderWriter fileReaderWriter;

    public DisplayProductsScreen(ArrayList<ArrayList<String>> products) {
        super("Display Products");
        fileReaderWriter = new FileReaderWriter();
        setPreferredSize(new Dimension(1300, 650));
        this.setContentPane(this.productsPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.products = products;
        createTable();
    }

    public void createTable() {

        String[][] productData = new String[products.size()][];
        String[] columnNames = {"Product Name", "Retail Price", "Cost Price", "Quantity On Hand", "Quantity Sold",
                                "Total Sales", "Total Cost", "Total Profit", "Total Profit %", "Warehouse Number"};

        for (int i = 0; i < products.size(); i++) {
            ArrayList<String> row = products.get(i);
            productData[i] = row.toArray(new String[row.size()]);
        }

        productsTable.setModel(new DefaultTableModel(
                productData,
                columnNames
        ));

        TableColumnModel columns = productsTable.getColumnModel();
        columns.getColumn(0).setMinWidth(150);
    }

}
