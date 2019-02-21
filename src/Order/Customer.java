package Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;


public class Customer implements Serializable ,Comparable<Customer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2315293834129798618L;

	private String Name;
	
	private String Address;
	
	private ArrayList<Order> OrderHistory;
	
	private String FAX					=null;
	private String TEL					=null;
	private String Compy				=null;
	private String Contect				=null;
	private String ID					=null;
	private String StorgeRoom			=null;
	private String uuid					=null;
	
	public Customer(String s) {
		this.Name=s;
		this.uuid= UUID.randomUUID().toString().replace("-", "");
		OrderHistory = new ArrayList<Order>();
	}
	
	

	
	
	public static Comparator<Customer> FruitNameComparator 
    = new Comparator<Customer>() {

		public int compare(Customer fruit1, Customer fruit2) {

			String fruitName1 = fruit1.getUUID().toUpperCase();
			String fruitName2 = fruit2.getUUID().toUpperCase();

			//ascending order
			return fruitName1.compareTo(fruitName2);

			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};
	
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public ArrayList<Order> getOrderHistory() {
		return OrderHistory;
	}

	public void setOrderHistory(ArrayList<Order> orderHistory) {
		OrderHistory = orderHistory;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public String getCompy() {
		return Compy;
	}

	public void setCompy(String compy) {
		Compy = compy;
	}

	public String getContect() {
		return Contect;
	}

	public void setContect(String contect) {
		Contect = contect;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getStorgeRoom() {
		return StorgeRoom;
	}

	public void setStorgeRoom(String storgeRoom) {
		StorgeRoom = storgeRoom;
	}

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uUID) {
		uuid = uUID;
	}

	public static Comparator<Customer> getFruitNameComparator() {
		return FruitNameComparator;
	}

	public static void setFruitNameComparator(Comparator<Customer> fruitNameComparator) {
		FruitNameComparator = fruitNameComparator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	

	@Override
	public String toString() {
		return "Customer [Name=" + Name + ", Address=" + Address + ", + FAX=" + FAX
				+ ", TEL=" + TEL + ", Compy=" + Compy + ", Contect=" + Contect + ", ID=" + ID + ", StorgeRoom="
				+ StorgeRoom + ", uuid=" + uuid + "]";
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
	
	public boolean SoftSearch(String[] key) {
		
		String all = this.toString();
		
		for(String tmp: key) {
			if(all.indexOf(tmp)!=-1) {
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public int compareTo(Customer o) {
		return  this.getUUID().compareTo(o.getUUID());
	}
}
