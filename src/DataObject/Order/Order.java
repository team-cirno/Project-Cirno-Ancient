package DataObject.Order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7935592698531829805L;
	private Customer cus;
	private String Address;
	
	private ArrayList<Comp> CompList	=null;
	private String Instrotion			=null;
	private String OrderID				=null;
	private String Status				=null;
	private String OrderedDate			=null;
	private String Auth					=null;
	private String DeadLine				=null;
	private String CreatTime			=null;
	private String CreaterID			=null;
	private String PayMathend			=null;
	private String PayDay				=null;
	
	private double PayAmout				=0.0;
	private double cost					=0.0;
	private double persentge			=0.0;
	private double profect				=0.0;
	private int BoxNum					=0;

	public Order(Customer cus, String address, ArrayList<Comp> compList, String instrotion, String date, String deadLime,String createrID) {
		super();
		this.cus = cus;
		Address = address;
		CompList = compList;
		Instrotion = instrotion;
		Auth=null;
		OrderedDate=date;
		Status="Pre";
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date time = new Date();  
		CreatTime=formatter.format(time); 
		CreaterID=createrID;
	}
	
	
	
	public Order(Customer cus, ArrayList<Comp> compList, String instrotion, String orderID, String orderedDate,
			String deadLine,String createrID, String payMathend) {
		super();
		this.cus = cus;
		CompList = compList;
		Instrotion = instrotion;
		//OrderID = orderID;
		OrderedDate = orderedDate;
		DeadLine = deadLine;
		CreaterID = createrID;
		PayMathend = payMathend;
		
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date time = new Date();  
		//CreatTime=formatter.format(time); 
		int boxcount= 0;
		PayAmout=0;
		double costcount=0;
		 for(Comp x: compList) {
			 PayAmout=PayAmout+x.getPayAmount();
			 boxcount=boxcount+x.getBoxNum();
			 costcount=costcount+x.getP().getBp()*x.getBagNum();
		 }
		this.setCost(costcount);
		this.setBoxNum(boxcount);
		 Status="未处理";
	}



	public Order() {
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date time = new Date();  
		CreatTime=formatter.format(time); 
	}
	
	public boolean Search(String[] key) {
		
		String all = this.toString();
		
		for(String tmp: key) {
			if(all.indexOf(tmp)==-1) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	
	public int getBoxNum() {
		return BoxNum;
	}



	public void setBoxNum(int boxNum) {
		BoxNum = boxNum;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public boolean SoftSearch(String[] key) {
		
		String all = this.toString();
		
		for(String tmp: key) {
			if(all.indexOf(tmp)!=-1) {
				return true;
			}
		}
		
		return false;
		
	}

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public ArrayList<Comp> getCompList() {
		return CompList;
	}

	public void setCompList(ArrayList<Comp> compList) {
		CompList = compList;
	}

	public String getInstrotion() {
		return Instrotion;
	}

	public void setInstrotion(String instrotion) {
		Instrotion = instrotion;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getOrderedDate() {
		return OrderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		OrderedDate = orderedDate;
	}

	public String getAuth() {
		return Auth;
	}

	public void setAuth(String auth) {
		Auth = auth;
	}

	public String getDeadLine() {
		return DeadLine;
	}

	public void setDeadLine(String deadLine) {
		DeadLine = deadLine;
	}

	public String getCreatTime() {
		return CreatTime;
	}

	public void setCreatTime(String creatTime) {
		CreatTime = creatTime;
	}

	public String getCreaterID() {
		return CreaterID;
	}

	public void setCreaterID(String createrID) {
		CreaterID = createrID;
	}

	public String getPayMathend() {
		return PayMathend;
	}

	public void setPayMathend(String payMathend) {
		PayMathend = payMathend;
	}

	public String getPayDay() {
		return PayDay;
	}

	public void setPayDay(String payDay) {
		PayDay = payDay;
	}

	public double getPayAmout() {
		return PayAmout;
	}

	public void setPayAmout(double payAmout) {
		PayAmout = payAmout;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPersentge() {
		return persentge;
	}

	public void setPersentge(double persentge) {
		this.persentge = persentge;
	}

	public double getProfect() {
		return profect;
	}

	public void setProfect(double profect) {
		this.profect = profect;
	}



	@Override
	public String toString() {
		return "DataObject.Order [cus=" + cus.toString() + ", Address=" + Address + ", CompList=" + CompList + ", Instrotion=" + Instrotion
				+ ", OrderID=" + OrderID + ", Status=" + Status + ", OrderedDate=" + OrderedDate + ", Auth=" + Auth
				+ ", DeadLine=" + DeadLine + ", CreatTime=" + CreatTime + ", CreaterID=" + CreaterID + ", PayMathend="
				+ PayMathend + ", PayDay=" + PayDay + ", PayAmout=" + PayAmout + ", cost=" + cost + ", persentge="
				+ persentge + ", profect=" + profect + ", BoxNum=" + BoxNum + "]";
	}



	
	
	

}