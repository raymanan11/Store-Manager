package JavaSwingGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DisplayProductsScreen extends JFrame {
    private JPanel productsPanel;
    private JTable productsTable;

    FileWriter fileWriter;

    public DisplayProductsScreen() {
        super("Display Products");
        fileWriter = new FileWriter();
        setPreferredSize(new Dimension(900, 650));
        this.setContentPane(this.productsPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        createTable();
    }

    public void createTable() {

        ArrayList<ArrayList<String>> productInfo = fileWriter.readFromFile("Products.txt");

        String[][] productData = new String[productInfo.size()][];
        String[] columnNames = {"Product Name", "Retail Price", "Cost Price", "Quantity On Hand", "Quantity Sold", "Total Sales", "Total Cost", "Total Profit", "Total Profit %"};

        for (int i = 0; i < productInfo.size(); i++) {
            ArrayList<String> row = productInfo.get(i);
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
