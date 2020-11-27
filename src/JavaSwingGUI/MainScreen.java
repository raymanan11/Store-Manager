package JavaSwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    private JPanel mainScreenPanel;
    private JButton productsClicked;
    private JButton invoicesClicked;
    private JButton employeesClicked;
    private JButton customersClicked;

    public MainScreen() {
        super("Main Menu");
        this.setContentPane(this.mainScreenPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        productsClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Showing Products Menu");
            }
        });

        customersClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Showing Customers Menu");
            }
        });

        employeesClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Showing Employees Window");
            }
        });

        invoicesClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Showing Invoices Window");
            }
        });

    }
}
