public class Invoice {

    private String invoiceID;
    private Customers invoiceCustomer;
    private String invoiceMethodOfPayment;
    private Products[] invoiceProducts;
    private boolean invoiceStatus;
    private double amountPaid;
    private double taxRate;

    public Invoice() {
        invoiceID = "";
        invoiceCustomer = null;
        invoiceMethodOfPayment = "";
        invoiceProducts = null;
        invoiceStatus = false;
        amountPaid = 0;
        taxRate = 0;
    }

    public Invoice(String invoiceID, Customers invoiceCustomer, String invoiceMethodOfPayment, Products[] invoiceProducts, boolean invoiceStatus, double amountPaid, double taxRate) {
        this.invoiceID = invoiceID;
        this.invoiceCustomer = invoiceCustomer;
        this.invoiceMethodOfPayment = invoiceMethodOfPayment;
        this.invoiceProducts = invoiceProducts;
        this.invoiceStatus = invoiceStatus;
        this.amountPaid = amountPaid;
        this.taxRate = taxRate;
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

}
