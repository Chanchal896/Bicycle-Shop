package Classes;

import java.sql.Date;

public class Sales {
    private int salesID;
    private int customerID;
    private Date salesDate;
    private double totalAmount;
    
    public int getSalesID() { return salesID; }
    public void setSalesID(int SID) { this.salesID = SID; }
    
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int CID) { this.customerID = CID; }
    
    public Date getSalesDate() { return salesDate; }
    public void setSalesDate(Date sdate) { this.salesDate = sdate; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double Tam) { this.totalAmount = Tam; }
}
