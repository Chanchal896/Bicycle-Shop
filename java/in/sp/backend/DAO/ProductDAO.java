package in.sp.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Product;

public class ProductDAO {
	
	public Product getProductById(int productId) throws ClassNotFoundException {
        String sql = "SELECT productId, productName, price, stockQuantity FROM products WHERE ProductID = ?";
        Product product = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductID(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
	
	public double getTotalInventoryValue() throws ClassNotFoundException {
        double totalValue = 0.0;
        String sql = "SELECT SUM(price * stockQuantity) AS totalValue FROM products";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if(rs.next()){
                totalValue = rs.getDouble("totalValue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalValue;
    }
	
    public boolean addProduct(String productName, double price, int stockQuantity) throws ClassNotFoundException {
        String sql = "INSERT INTO products (productName, price, stockQuantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setInt(3, stockQuantity);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeProduct(int productID) throws ClassNotFoundException {
        String sql = "DELETE FROM products WHERE productId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean adjustInventory(int productID, int newQuantity) throws ClassNotFoundException {
        String sql = "UPDATE products SET stockQuantity = ? WHERE productId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, productID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
