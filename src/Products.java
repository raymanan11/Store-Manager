public class Products {
	
	private int productNum;
	private String productName;
	private int quantity;
	private double productPrice;
	private double productRetail;
	private int amtSold;
	
	public Product() {
		
		productNum = 0;
		productName = "";
		quantity = 0;
		productPrice = 0.0;
		productRetail = 0.0;
		amtSold = 0;
	}
	
	public Product(int num, String name, int stock, double price
					double retail, int sale) {
		
		productNum = num;
		productName = name;
		quantity = stock;
		productPrice = price;
		productRetail = retail;
		amtSold = sale;
	}
	
	
	
}
