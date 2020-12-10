package JavaSwingGUI;

import com.google.gson.Gson;
import com.sun.source.tree.ArrayAccessTree;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileReaderWriter {

    Gson gson = new Gson();

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

    public ArrayList<ArrayList<String>> getOpenInvoicesDate(String fileName) {
        try {
            ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
            ArrayList<String> invoiceInfo;
            Scanner file = new Scanner(new File(fileName));
            Gson gson = new Gson();
            ArrayList<LocalDate> key = new ArrayList<>();
            Map<LocalDate, ArrayList<ArrayList<String>>> invoicesMap = new HashMap<>();
            while (file.hasNextLine()) {
                invoiceInfo = new ArrayList<>();
                Invoice invoice = gson.fromJson(file.nextLine(), Invoice.class);
                if (invoice.getActive()) {
                    getInvoice(invoiceInfo, invoice);
                    addInvoiceDate(invoiceInfo, key, invoicesMap, invoice);
                }
                // add values from products class into products arraylist
            }
            Collections.sort(key);
            for(LocalDate date : key) resultSet.addAll(invoicesMap.get(date));
            file.close();
            return resultSet;
        }
        catch (IOException excpt) {
            System.out.println("File not found");
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getClosedInvoicesAmount(String fileName) {
        try {
            ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
            ArrayList<String> invoiceInfo;
            Scanner file = new Scanner(new File(fileName));
            Gson gson = new Gson();
            ArrayList<Double> key = new ArrayList<>();
            Map<Double, ArrayList<ArrayList<String>>> invoicesMap = new HashMap<>();
            while (file.hasNextLine()) {
                invoiceInfo = new ArrayList<>();
                Invoice invoice = gson.fromJson(file.nextLine(), Invoice.class);
                if (!invoice.getActive()) {
                    System.out.println("hi");
                    getInvoice(invoiceInfo, invoice);
                    System.out.println(invoiceInfo);
                    addInvoiceAmount(invoiceInfo, key, invoicesMap, invoice);
                }
                // add values from products class into products arraylist
            }
            Collections.sort(key);
            for (int i = 0; i < key.size(); i++) resultSet.addAll(invoicesMap.get(key.get(key.size() - 1 - i)));
            file.close();
            return resultSet;
        }
        catch (IOException excpt) {
            System.out.println("File not found");
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getEmployeeInfo(String fileName) {
        try {
            ArrayList<ArrayList<String>> resultSet = new ArrayList<>();
            ArrayList<String> employeeInfo;
            Scanner file = new Scanner(new File(fileName));
            Gson gson = new Gson();
            while (file.hasNextLine()) {
                employeeInfo = new ArrayList<>();
                Employee employee = gson.fromJson(file.nextLine(), Employee.class);
                // add values from products class into products ArrayList
                getEmployees(employeeInfo, employee);
                resultSet.add(employeeInfo);
            }
            file.close();
            return resultSet;
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
        return null;
    }

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

    private void addInvoiceDate(ArrayList<String> invoiceInfo, ArrayList<LocalDate> dates, Map<LocalDate, ArrayList<ArrayList<String>>> invoicesMap, Invoice invoice) {
        if (!dates.contains(invoice.getInvoiceDate())) {
            ArrayList<ArrayList<String>> invoiceInformation = new ArrayList<>();
            if (invoice.getActive()) {
                invoiceInformation.add(invoiceInfo);
                dates.add(invoice.getInvoiceDate());
                invoicesMap.put(invoice.getInvoiceDate(), invoiceInformation);
            }
        }
        else {
            invoicesMap.get(invoice.getInvoiceDate()).add(invoiceInfo);
        }
    }

    private void addInvoiceAmount(ArrayList<String> invoiceInfo, ArrayList<Double> invoiceAmount, Map<Double, ArrayList<ArrayList<String>>> invoicesMap, Invoice invoice) {
        if (!invoiceAmount.contains(invoice.getTotalCost())) {
            ArrayList<ArrayList<String>> invoiceInformation = new ArrayList<>();
            System.out.println("invoice is not active");
            invoiceInformation.add(invoiceInfo);
            invoiceAmount.add(invoice.getTotalCost());
            invoicesMap.put(invoice.getTotalCost(), invoiceInformation);
        }
        else {
            invoicesMap.get(invoice.getTotalCost()).add(invoiceInfo);
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

    private void getEmployees(ArrayList<String> employeeInfo, Employee employee) {
        ArrayList<String> invoiceInfo = readFile("Invoices.txt");
        double totalSales = 0;
        for (String invoiceJSON : invoiceInfo) {
            Invoice invoice = gson.fromJson(invoiceJSON, Invoice.class);
            if (employee.getEmployeeName().equals(invoice.getInvoiceEmployee())) {
                totalSales += invoice.getTotalCost();
            }
        }
        employeeInfo.add(employee.getEmployeeName());
        employeeInfo.add(String.format("%.2f", totalSales));
        employeeInfo.add(String.format("%.2f", totalSales * (employee.getEmployeeCommissionPercentage() / 100)));
    }

    private void getInvoice(ArrayList<String> invoiceInfo, Invoice invoice) {
        System.out.println("get invoice");
        invoiceInfo.add(invoice.getInvoiceCustomer());
        invoiceInfo.add(invoice.getInvoiceEmployee());
        invoiceInfo.add(invoice.getInvoiceDate().toString());
        invoiceInfo.add(invoice.getInvoiceProducts().toString());
        if (invoice.getActive()) invoiceInfo.add("true");
        else invoiceInfo.add("false");
        invoiceInfo.add(String.valueOf(invoice.getAmountPaid()));
        invoiceInfo.add(String.valueOf(invoice.getTaxRate()));
        if (invoice.isDelivery()) invoiceInfo.add("true");
        else invoiceInfo.add("false");
        invoiceInfo.add(String.valueOf(invoice.getDeliveryCharge()));
        invoiceInfo.add(String.valueOf(invoice.getTotalCost()));
    }

}
