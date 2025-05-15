package Classes;

import java.sql.Date;

public class Repair {
	 	private int RepairID;
	 	private int CustomerID;
	 	private String RepairDetails;
	 	private double TotalCost;
	 	private Date RepairDate;
	    
	    public Repair() {}
	    
	    public Repair(int RID,int CID,String RD,double TC,Date RDATE) {
	       this.RepairID=RID;
	       this.CustomerID=CID;
	       this.RepairDetails=RD;
	       this.TotalCost=TC;
	       this.RepairDate=RDATE;
	    }
	    
	    public int getRepairID() {return this.RepairID;}
	    public void setRepairID(int id) {this.RepairID=id;}
	    
	    public int getCustomerID() {return this.CustomerID;}
	    public void setCustomerID(int cid) {this.CustomerID=cid;}
	    
	    public String getRepairDetails() {return this.RepairDetails;}
	    public void setRepairDetails(String RD) {this.RepairDetails=RD;}
	    
	    public double getTotalCost() {return this.TotalCost;}
	    public void setTotalCost(double TC) {this.TotalCost=TC;}
	    
	    public Date getRepairDate() {return this.RepairDate;		}
		public void setRepairDate(Date date) {this.RepairDate=date;		}
	    
	    
	    
	    
	    
}
