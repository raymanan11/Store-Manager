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
    private JButton editNumberOfWarehousesButton;
    private JButton displayProductsOnHandButton;

    public ProductsMenu() {

        super("Products Menu");
        setPreferredSize(new Dimension(1000, 600));
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
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProducts("Products.txt", true, false, false);
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });

        displayProducts5OrLessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProducts("Products.txt", false, true, false);
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });

        displayProductsOnHandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProducts("Products.txt", false, false, true);
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });

        editNumberOfWarehousesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditNumberOfWarehousesScreen editNumberOfWarehousesScreen = new EditNumberOfWarehousesScreen();
                editNumberOfWarehousesScreen.setVisible(true);
            }
        });

    }
}
