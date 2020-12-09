package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceMenu extends JFrame{
    private JPanel invoiceMenuPanel;
    private JPanel panelBottom;
    private JButton createInvoiceClicked;
    private JButton openInvoicesClicked;
    private JButton closedInvoicesClicked;
    private JPanel panelTop;

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

//        openInvoicesClicked.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DisplayOpenInvoicesScreen displayOpenInvoicesScreen = new DisplayOpenInvoicesScreen();
//                displayOpenInvoicesScreen.setVisible(true);
//            }
//        });
//
//        closedInvoicesClicked.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DisplayClosedInvoicesScreen displayClosedInvoicesScreen = new DisplayClosedInvoicesScreen();
//                displayClosedInvoicesScreen.setVisible(true);
//            }
//        });

    }
}

