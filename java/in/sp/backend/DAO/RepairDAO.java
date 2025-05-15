package in.sp.backend.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Repair;

import java.sql.Date;
import java.util.Calendar;

public class RepairDAO {

    public List<Repair> getWeeklyRepairs() throws ClassNotFoundException {
        List<Repair> repairs = new ArrayList<>();
        String sql = "SELECT RepairID, CustomerID, RepairDetails, TotalCost, RepairDate FROM Repair WHERE RepairDate BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            Calendar cal = Calendar.getInstance();
            Date endDate = new Date(cal.getTimeInMillis());
            cal.add(Calendar.DAY_OF_MONTH, -7);
            Date startDate = new Date(cal.getTimeInMillis());
            
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Repair rep = new Repair();
                rep.setRepairID(rs.getInt("RepairID"));
                rep.setCustomerID(rs.getInt("CustomerID"));
                rep.setRepairDetails(rs.getString("RepairDetails"));
                rep.setTotalCost(rs.getDouble("TotalCost"));
                rep.setRepairDate(rs.getDate("RepairDate"));
                repairs.add(rep);
            }
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return repairs;
    }
    public boolean recordRepair(int customerID, String repairDetails, double totalCost, Date repairDate) {
        String sql = "INSERT INTO Repair (CustomerID, RepairDetails, TotalCost, RepairDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ps.setString(2, repairDetails);
            ps.setDouble(3, totalCost);
            ps.setDate(4, repairDate);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean customerExists(int customerID) {
        String sql = "SELECT id FROM customers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
