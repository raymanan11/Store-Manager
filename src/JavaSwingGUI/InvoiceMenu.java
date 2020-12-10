package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class InvoiceMenu extends JFrame{
    private JPanel invoiceMenuPanel;
    private JPanel panelBottom;
    private JButton createInvoiceClicked;
    private JButton openInvoicesClicked;
    private JButton closedInvoicesClicked;
    private JPanel panelTop;
    private JButton allInvoicesClicked;

    public InvoiceMenu() {
        super("Invoice Menu");
        setPreferredSize(new Dimension(500, 350));
        this.setContentPane(this.invoiceMenuPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        createInvoiceClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreateInvoiceScreen createInvoiceScreen = new CreateInvoiceScreen();
                createInvoiceScreen.setVisible(true);
            }
        });

        openInvoicesClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReaderWriter fileReaderWriter = new FileReaderWriter();
                ArrayList<ArrayList<String>> openInvoices = fileReaderWriter.getOpenInvoicesDate("Invoices.txt");
                DisplayOpenInvoicesScreen displayOpenInvoicesScreen = new DisplayOpenInvoicesScreen(openInvoices);
                displayOpenInvoicesScreen.setVisible(true);
            }
        });

        closedInvoicesClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReaderWriter fileReaderWriter = new FileReaderWriter();
                ArrayList<ArrayList<String>> closedInvoices = fileReaderWriter.getClosedInvoicesAmount("Invoices.txt");
                DisplayClosedInvoicesScreen displayClosedInvoicesScreen = new DisplayClosedInvoicesScreen(closedInvoices);
                displayClosedInvoicesScreen.setVisible(true);
            }
        });

        allInvoicesClicked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReaderWriter fileReaderWriter = new FileReaderWriter();
                ArrayList<ArrayList<String>> allInvoices = fileReaderWriter.getAllInvoices("Invoices.txt");
                DisplayAllInvoices displayAllInvoices = new DisplayAllInvoices(allInvoices);
                displayAllInvoices.setVisible(true);
            }
        });
    }
}

