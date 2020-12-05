package JavaSwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductsMenu extends JFrame {
    private JPanel panelProductsMenu;
    private JButton addProductsButton;
    private JButton displayProductsProfitPercentButton;
    private JButton displayProducts5OrLessButton;

    public ProductsMenu() {

        super("Products Menu");
        setPreferredSize(new Dimension(1100, 500));
        this.setContentPane(this.panelProductsMenu);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        FileReaderWriter fileReaderWriter = new FileReaderWriter();

        addProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditProductsScreen addProducts = new AddEditProductsScreen();
                addProducts.setVisible(true);
            }
        });
        displayProductsProfitPercentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProductsProfitPercentDesc("Products.txt");
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });

        displayProducts5OrLessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProductsFiveOrLess("Products.txt");
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });
    }
}
