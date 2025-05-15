package in.sp.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class OrderDAO {

    public boolean placeOrder(int productId, int quantity, double orderPrice) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement updateStmt = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            
            String orderSql = "INSERT INTO orders (ProductID, OrderDate, Quantity, Price) VALUES (?, ?, ?, ?)";
            orderStmt = conn.prepareStatement(orderSql);
            orderStmt.setInt(1, productId);
            orderStmt.setDate(2, new Date(System.currentTimeMillis()));
            orderStmt.setInt(3, quantity);
            orderStmt.setDouble(4, orderPrice);
            int rowsInserted = orderStmt.executeUpdate();
            
            String updateSql = "UPDATE products SET stockQuantity = stockQuantity + ? WHERE ProductID = ?";
            updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, productId);
            int rowsUpdated = updateStmt.executeUpdate();
            
            if (rowsInserted > 0 && rowsUpdated > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (conn != null) conn.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
