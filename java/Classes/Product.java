package Classes;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int stockQuantity;
    
    public Product() {}
    
    public Product(String productName, double price, int stockQuantity) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    public int getProductId() {
        return productId;
    }
    public void setProductID(int PID) {
		this.productId = PID;
	}
    
    public String getProductName() {
        return productName;
    }
    public void setProductName(String PN) {
        this.productName = PN;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double PN) {
        this.price = PN;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int SQ) {
        this.stockQuantity = SQ;
    }
}
