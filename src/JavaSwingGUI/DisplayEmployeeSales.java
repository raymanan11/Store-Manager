package JavaSwingGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class DisplayEmployeeSales extends JFrame {
    private JTable employeesTable;
    private JPanel employeesPanel;

    public DisplayEmployeeSales(ArrayList<ArrayList<String>> employees) {
        super("Display Products");
        setPreferredSize(new Dimension(1300, 650));
        this.setContentPane(this.employeesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        createTable(employees);
    }

    private void createTable(ArrayList<ArrayList<String>> employees) {
        String[][] productData = new String[employees.size()][];
        String[] columnNames = {"Employee Name", "Total Sales", "Total Commission"};

        for (int i = 0; i < employees.size(); i++) {
            ArrayList<String> row = employees.get(i);
            productData[i] = row.toArray(new String[row.size()]);
        }

        employeesTable.setModel(new DefaultTableModel(
                productData,
                columnNames
        ));

        TableColumnModel columns = employeesTable.getColumnModel();
        columns.getColumn(0).setMinWidth(150);
    }
}
