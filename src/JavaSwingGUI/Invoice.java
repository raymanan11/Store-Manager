package JavaSwingGUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {

    private String invoiceID;
    private String invoiceCustomer;
    private String invoiceEmployee;
    private LocalDate invoiceDate;
    private ArrayList<String> invoiceProducts;
    private boolean active;
    private double amountPaid;
    private double taxRate;
    private boolean delivery;
    private double deliveryCharge;
    private double totalCost;


    public Invoice() {
        invoiceID = "";
        invoiceCustomer = null;
        invoiceDate = null;
        invoiceProducts = null;
        active = false;
        totalCost = 0;
        amountPaid = 0;
        taxRate = 0;
        delivery = false;
        deliveryCharge = 0;
    }


    public Invoice(String invoiceCustomer, String invoiceEmployee, String invoiceDate, ArrayList<String> invoiceProducts,
                   boolean active, double totalCost, double amountPaid, double taxRate, boolean delivery, double deliveryCharge) {
        this.invoiceID = invoiceID;
        this.invoiceCustomer = invoiceCustomer;
        this.invoiceEmployee = invoiceEmployee;
        setInvoiceDate(invoiceDate);
        this.invoiceProducts = invoiceProducts;
        this.active = active;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.taxRate = taxRate;
        this.delivery = delivery;
        this.deliveryCharge = deliveryCharge;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public ArrayList<String> getInvoiceProducts() {
        return invoiceProducts;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public boolean getActive() {
        return active;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public boolean isInvoicePaid() {
        return active;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

//    public double getInvoiceTotal() {
//        double totalProductsPrice = getTotalProductsPrice();
//        return totalProductsPrice * taxRate;
//    }
//
//    public double getAmountDue() {
//        double totalProductsPrice = getTotalProductsPrice();
//        return totalProductsPrice - amountPaid;
//    }

//    private double getTotalProductsPrice() {
//        double totalProductsPrice = 0;
//        for (int i = 0; i < invoiceProducts.length; i++) {
//            totalProductsPrice += invoiceProducts[i].getCostPrice();
//        }
//        return totalProductsPrice;
//    }

//    public double getEmployeeComissionAmount() {
//        return getTotalProductsPrice() * invoiceEmployee.getEmployeeCommissionPercentage();
//    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = LocalDate.parse(invoiceDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

//    public void setActive(boolean active) {
//        this.active = active;
//        invoiceCustomer.setActive(active);
//    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

//    public void setPickUp(boolean pickUp) {
//        this.pickUp = pickUp;
//        delivery = !pickUp;
//    }

//    public void setDelivery(boolean delivery) {
//        this.delivery = delivery;
//        pickUp = !delivery;
//    }

    public void setDeliveryCharge(double deliveryCharge) {
        if (delivery) this.deliveryCharge = deliveryCharge;
    }

}
