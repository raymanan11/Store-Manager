package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeMenu extends JFrame {
    private JPanel employeesPanel;
    private JButton addEditEmployeeButton;
    private JButton showEmployeeSalesAndButton;

    public EmployeeMenu() {

        super("Employee Menu");
        setPreferredSize(new Dimension(500, 350));
        this.setContentPane(this.employeesPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        addEditEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditEmployeeScreen addEditEmployeeScreen = new AddEditEmployeeScreen();
                addEditEmployeeScreen.setVisible(true);
            }


        });

        showEmployeeSalesAndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReaderWriter fileReaderWriter = new FileReaderWriter();
                ArrayList<ArrayList<String>> employeesInfo = fileReaderWriter.getEmployeeInfo("Employees.txt");
                DisplayEmployeeSales displayEmployeeSales = new DisplayEmployeeSales(employeesInfo);
                displayEmployeeSales.setVisible(true);
            }
        });
    }
}
