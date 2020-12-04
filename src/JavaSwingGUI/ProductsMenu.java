package JavaSwingGUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProductsMenu extends JFrame {
    private JPanel panelProductsMenu;
    private JButton addProductsButton;
    private JButton displayProductsButton;

    public ProductsMenu() {

        super("Products Menu");
        setPreferredSize(new Dimension(500, 350));
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
        displayProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<String>> productInfo = fileReaderWriter.getProductsProfitPercentDesc("Products.txt");
                DisplayProductsScreen displayProductsScreen = new DisplayProductsScreen(productInfo);
                displayProductsScreen.setVisible(true);
            }
        });
    }
}
