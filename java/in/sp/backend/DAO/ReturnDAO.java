package in.sp.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnDAO {

    public boolean recordReturn(int salesID, double refAmount) throws ClassNotFoundException {
        String sql = "INSERT INTO Returns (SalesID, RefAmount) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, salesID);
            ps.setDouble(2, refAmount);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean check(int SalesID) {
		String sql = "SELECT SalesID FROM Sales WHERE SalesID = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, SalesID); 
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
}
