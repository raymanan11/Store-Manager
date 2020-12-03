package JavaSwingGUI;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class FileWriter {

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

    public ArrayList<ArrayList<String>> readFromFile(String fileName) {
        try {
            ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
            ArrayList<String> productInfo;
            Scanner file = new Scanner(new File(fileName));
            Gson gson = new Gson();
            ArrayList<Double> profitPercent = new ArrayList<>();
            Map<Double, ArrayList<String>> orderedProfit = new HashMap<>();
            while (file.hasNextLine()) {
                productInfo = new ArrayList<>();
                Products product = gson.fromJson(file.nextLine(), Products.class);
                productInfo.add(product.getProductName());
                productInfo.add(String.valueOf(product.getRetailPrice()));
                productInfo.add(String.valueOf(product.getCostPrice()));
                productInfo.add(String.valueOf(product.getQuantityOnHand()));
                productInfo.add(String.valueOf(product.getQuantitySold()));
                productInfo.add(String.valueOf(product.getTotalSales()));
                productInfo.add(String.valueOf(product.getTotalCost()));
                productInfo.add(String.valueOf(product.getProfit()));
                productInfo.add(String.format("%.2f", product.getProfitPercent()));
                profitPercent.add(product.getProfitPercent());
                orderedProfit.put(product.getProfitPercent(), productInfo);
//                resultSet.add(productInfo);
            }
            Collections.sort(profitPercent);
            for (int i = 0; i < profitPercent.size(); i++) {
                resultSet.add(orderedProfit.get(profitPercent.get(profitPercent.size() - 1 - i)));
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

}
