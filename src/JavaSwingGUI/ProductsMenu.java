package JavaSwingGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsMenu extends JFrame {
    private JPanel panelProductsMenu;
    private JButton addProductsButton;
    private JButton displayProductsButton;

    public ProductsMenu() {

        super("Products Menu");
        this.setContentPane(this.panelProductsMenu);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        addProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductsScreen addProducts = new AddProductsScreen();
                addProducts.setVisible(true);
            }
        });
        displayProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Displaying Products");
            }
        });
    }
}
