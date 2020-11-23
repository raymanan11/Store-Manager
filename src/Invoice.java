import java.util.Date;

public class Invoice {

    private String invoiceID;
    private Customers invoiceCustomer;
    private String invoiceMethodOfPayment;
    private Date invoiceDate;
    private Products[] invoiceProducts;
    private boolean invoiceStatus;
    private double amountPaid;
    private double taxRate;
    private boolean pickUp;
    private boolean delivery;
    private double deliveryCharge;

    public Invoice() {
        invoiceID = "";
        invoiceCustomer = null;
        invoiceMethodOfPayment = "";
        invoiceDate = null;
        invoiceProducts = null;
        invoiceStatus = false;
        amountPaid = 0;
        taxRate = 0;
        pickUp = false;
        delivery = false;
        deliveryCharge = 0;
    }


    public Invoice(String invoiceID, Customers invoiceCustomer, String invoiceMethodOfPayment, Date invoiceDate, Products[] invoiceProducts,
                   boolean invoiceStatus, double amountPaid, double taxRate, boolean pickUp, boolean delivery, double deliveryCharge) {
        this.invoiceID = invoiceID;
        this.invoiceCustomer = invoiceCustomer;
        this.invoiceMethodOfPayment = invoiceMethodOfPayment;
        this.invoiceDate = invoiceDate;
        this.invoiceProducts = invoiceProducts;
        this.invoiceStatus = invoiceStatus;
        this.amountPaid = amountPaid;
        this.taxRate = taxRate;
        this.pickUp = pickUp;
        this.delivery = delivery;
        this.deliveryCharge = deliveryCharge;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public Customers getInvoiceCustomer() {
        return invoiceCustomer;
    }

    public String getInvoiceMethodOfPayment() {
        return invoiceMethodOfPayment;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public Products[] getInvoiceProducts() {
        return invoiceProducts;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public boolean getInvoiceStatus() {
        return invoiceStatus;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public boolean isInvoicePaid() {
        return invoiceStatus;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public double getInvoiceTotal() {
        double totalProductsPrice = getTotalProductsPrice();
        return totalProductsPrice * taxRate;
    }

    public double getAmountDue() {
        double totalProductsPrice = getTotalProductsPrice();
        return totalProductsPrice - amountPaid;
    }

    private double getTotalProductsPrice() {
        double totalProductsPrice = 0;
        for (int i = 0; i < invoiceProducts.length; i++) {
            totalProductsPrice += invoiceProducts[i].getPrice();
        }
        return totalProductsPrice;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setInvoiceCustomer(Customers invoiceCustomer) {
        this.invoiceCustomer = invoiceCustomer;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceMethodOfPayment(String invoiceMethodOfPayment) {
        this.invoiceMethodOfPayment = invoiceMethodOfPayment;
    }

    public void setInvoiceProducts(Products[] invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

    public void setInvoiceStatus(boolean invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
        delivery = !pickUp;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
        pickUp = !delivery;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        if (delivery) this.deliveryCharge = deliveryCharge;
    }

}
