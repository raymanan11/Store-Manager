public class Products {

    private String productName;
    private int productNumber;
    private int quantityOnHand;
    private double costPrice;
    private double retailPrice;
    private int quantitySold;

    public Products() {
        productName = "";
        productNumber = 0;
        quantityOnHand = 0;
        costPrice = 0;
        retailPrice = 0;
        quantitySold = 0;
    }

    public Products(String productName, int productNumber, int quantity, double price, double retailPrice, int quantitySold) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.quantityOnHand = quantity;
        this.costPrice = price;
        this.retailPrice = retailPrice;
        this.quantitySold = quantitySold;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalSales() {
        return quantitySold * retailPrice;
    }

    public double getTotalCost() {
        return quantityOnHand * costPrice;
    }

    public double getProfit() {
        return getTotalSales() - getTotalCost();
    }

    public double getProfitPercent() {
        // Net Income / Revenue * 100
        // Net Income = getProfit()
        // Revenue = ? (take into account expenses so employee commission as well?)
        return 0;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setProduct(String productName, int productNumber, int quantity, double price, double retailPrice, int numberSold) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.quantityOnHand = quantity;
        this.costPrice = price;
        this.retailPrice = retailPrice;
        this.quantitySold = numberSold;
    }

}
