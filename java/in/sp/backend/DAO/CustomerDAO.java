package in.sp.backend.DAO;

import Classes.Customer; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public boolean addCustomer(String name, String email) throws ClassNotFoundException {
    	
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeCustomer(int customerID) throws ClassNotFoundException {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLIntegrityConstraintViolationException ec) {
            ec.printStackTrace();
            System.out.print("Refrential Integrity Constraint Violated. Clear other values first");
            return false;
        } 
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Customer> getAllCustomers() throws ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, name, email FROM customers";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer();
                c = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
