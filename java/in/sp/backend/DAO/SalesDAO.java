package in.sp.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Classes.Customer;
import Classes.Sales;

public class SalesDAO {
	
	public List<Sales> getSalesByDateRange(Date startDate, Date endDate) throws ClassNotFoundException {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT SalesID, CustomerID, SalesDate, TotalAmount FROM Sales WHERE SalesDate BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sales sale = new Sales();
                sale.setSalesID(rs.getInt("SalesID"));
                sale.setCustomerID(rs.getInt("CustomerID"));
                sale.setSalesDate(rs.getDate("SalesDate"));
                sale.setTotalAmount(rs.getDouble("TotalAmount"));
                salesList.add(sale);
            }
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return salesList;
    }
	public boolean check(int customerID) {
		String sql = "SELECT id FROM customers WHERE id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, customerID); 
	        try (ResultSet rs = ps.executeQuery();){
				return rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			}  
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		return false;
	}
    public boolean recordSale(int customerID, double totalAmount, int productID, double netAmount) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement salesStmt = null;
        PreparedStatement detailsStmt = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            
            String salesSql = "INSERT INTO Sales (CustomerID, SalesDate, TotalAmount) VALUES (?, ?, ?)";
            salesStmt = conn.prepareStatement(salesSql, Statement.RETURN_GENERATED_KEYS);
            salesStmt.setInt(1, customerID);
            salesStmt.setDate(2, new Date(System.currentTimeMillis()));
            salesStmt.setDouble(3, totalAmount);
            int rowsInserted = salesStmt.executeUpdate();
            if (rowsInserted == 0) {
                conn.rollback();
                return false;
            }
            
            ResultSet rs = salesStmt.getGeneratedKeys();
            int salesID = 0;
            if (rs.next()) {
                salesID = rs.getInt(1);
            } else {
                conn.rollback();
                return false;
            }
            rs.close();
            
            String detailsSql = "INSERT INTO SalesDetails (SalesID, ProductID, NetAmount) VALUES (?, ?, ?)";
            detailsStmt = conn.prepareStatement(detailsSql);
            detailsStmt.setInt(1, salesID);
            detailsStmt.setInt(2, productID);
            detailsStmt.setDouble(3, netAmount);
            int rowsDetails = detailsStmt.executeUpdate();
            if (rowsDetails == 0) {
                conn.rollback();
                return false;
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (salesStmt != null) salesStmt.close();
                if (detailsStmt != null) detailsStmt.close();
                if (conn != null) conn.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
}
