package JavaSwingGUI;

public class Products {

    private String productName;
    private double costPrice;
    private double retailPrice;
    private int quantityOnHand;
    private int quantitySold;
    private int warehouseNumber;

    public Products() {
        productName = "";
        quantityOnHand = 0;
        costPrice = 0;
        retailPrice = 0;
        quantitySold = 0;
    }

    public Products(String productName, double costPrice, double retailPrice, int quantityOnHand, int quantitySold, int warehouseNumber) {
        this.productName = productName;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.quantityOnHand = quantityOnHand;
        this.quantitySold = quantitySold;
        this.warehouseNumber = warehouseNumber;
    }

    public String getProductName() {
        return productName;
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

    public int getWarehouseNumber() {
        return warehouseNumber;
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
        return (getTotalSales() / getTotalCost()) * 100;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public void setProducts(String productName, double costPrice, double retailPrice, int quantityOnHand, int quantitySold, int warehouseNumber) {
        this.productName = productName;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.quantityOnHand = quantityOnHand;
        this.quantitySold = quantitySold;
        this.warehouseNumber = warehouseNumber;
    }

}
