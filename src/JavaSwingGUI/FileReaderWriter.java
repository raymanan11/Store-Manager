package JavaSwingGUI;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class FileReaderWriter {

    public ArrayList<String> readFile(String fileName) {
        try {
            ArrayList<String> resultSet = new ArrayList<>();
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                resultSet.add(file.nextLine());
            }
            file.close();
            return resultSet;
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
        return null;
    }

    public void writeFile(String gson, String fileName) {
        try {
            java.io.FileWriter file = new java.io.FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(gson);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
    }

    public void writeFile(ArrayList<String> updatedList, String fileName) {
        try {
            java.io.FileWriter file = new java.io.FileWriter(fileName, false);
            BufferedWriter out = new BufferedWriter(file);
            for (String objectJSON : updatedList) {
                out.write(objectJSON);
                out.newLine();
            }
            out.close();
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
    }

    public ArrayList<ArrayList<String>> getProducts(String fileName, boolean desc, boolean fiveOrLess, boolean warehouse) {
        try {
            ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
            ArrayList<String> productInfo;
            Scanner file = new Scanner(new File(fileName));
            Gson gson = new Gson();
            ArrayList<Double> key = new ArrayList<>();
            Map<Double, ArrayList<ArrayList<String>>> productsMap = new HashMap<>();
            while (file.hasNextLine()) {
                productInfo = new ArrayList<>();
                Products product = gson.fromJson(file.nextLine(), Products.class);
                // add values from products class into products arraylist
                getProducts(productInfo, product);
                if (desc) addProductsDesc(productInfo, key, productsMap, product);
                else if (fiveOrLess) addProductsFiveOrLess(productInfo, key, productsMap, product);
                else if (warehouse) addProductsWarehouse(productInfo, key, productsMap, product);
            }
            Collections.sort(key);
            if (desc) for (int i = 0; i < key.size(); i++) resultSet.addAll(productsMap.get(key.get(key.size() - 1 - i)));
            else if (fiveOrLess || warehouse) for (Double numberInInventory : key) resultSet.addAll(productsMap.get(numberInInventory));
            file.close();
            return resultSet;
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
        return null;
    }

    // public ArrayList<ArrayList<String>> getEmployeeInfo(String fileName) {}

    private void addProductsDesc(ArrayList<String> productInfo, ArrayList<Double> profitPercent, Map<Double, ArrayList<ArrayList<String>>> orderedProfit, Products product) {
        // if profit percent isn't in arraylist,
        // add the productInfo json string into Arraylist, put that Arraylist into map with key profit percent
        if (!profitPercent.contains(product.getProfitPercent())) {
            ArrayList<ArrayList<String >> productsInformation = new ArrayList<>();
            productsInformation.add(productInfo);
            profitPercent.add(product.getProfitPercent());
            orderedProfit.put(product.getProfitPercent(), productsInformation);
        }
        else {
            orderedProfit.get(product.getProfitPercent()).add(productInfo);
        }
    }

    private void addProductsFiveOrLess(ArrayList<String> productInfo, ArrayList<Double> quantityOnHand, Map<Double, ArrayList<ArrayList<String>>> productsMap, Products product) {
        if (!quantityOnHand.contains((double) product.getQuantityOnHand())) {
            ArrayList<ArrayList<String >> productsInformation = new ArrayList<>();
            if (product.getQuantityOnHand() <= 5) {
                productsInformation.add(productInfo);
                quantityOnHand.add((double) product.getQuantityOnHand());
                productsMap.put((double) product.getQuantityOnHand(), productsInformation);
            }
        }
        else {
            productsMap.get((double) product.getQuantityOnHand()).add(productInfo);
        }
    }

    private void addProductsWarehouse(ArrayList<String> productInfo, ArrayList<Double> warehouseNumber, Map<Double, ArrayList<ArrayList<String>>> productsMap, Products product) {
        if (!warehouseNumber.contains((double) product.getWarehouseNumber())) {
            ArrayList<ArrayList<String>> productsInformation = new ArrayList<>();
            productsInformation.add(productInfo);
            warehouseNumber.add((double) product.getWarehouseNumber());
            productsMap.put((double) product.getWarehouseNumber(), productsInformation);
        }
        else {
            productsMap.get((double) product.getWarehouseNumber()).add(productInfo);
        }
    }

    private void getProducts(ArrayList<String> productInfo, Products product) {
        productInfo.add(product.getProductName());
        productInfo.add(String.valueOf(product.getRetailPrice()));
        productInfo.add(String.valueOf(product.getCostPrice()));
        productInfo.add(String.valueOf(product.getQuantityOnHand()));
        productInfo.add(String.valueOf(product.getQuantitySold()));
        productInfo.add(String.valueOf(product.getTotalSales()));
        productInfo.add(String.valueOf(product.getTotalCost()));
        productInfo.add(String.valueOf(product.getProfit()));
        productInfo.add(String.format("%.2f", product.getProfitPercent()));
        productInfo.add(String.valueOf(product.getWarehouseNumber()));
    }

}
