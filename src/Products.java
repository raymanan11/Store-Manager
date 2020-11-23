public class Products {

    private String productName;
    private int productNumber;
    private int quantity;
    private double costPrice;
    private double retailPrice;
    private int numberSold;

    public Products() {
        productName = "";
        productNumber = 0;
        quantity = 0;
        costPrice = 0;
        retailPrice = 0;
        numberSold = 0;
    }

    public Products(String productName, int productNumber, int quantity, double price, double retailPrice, int numberSold) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.costPrice = price;
        this.retailPrice = retailPrice;
        this.numberSold = numberSold;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public double getProfit() {
        return retailPrice - costPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }

    public void setProduct(String productName, int productNumber, int quantity, double price, double retailPrice, int numberSold) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.costPrice = price;
        this.retailPrice = retailPrice;
        this.numberSold = numberSold;
    }

}
