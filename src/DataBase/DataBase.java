package DataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Order.Customer;
import Order.Order;
import Order.Product;
import Tool.Logger;

public class DataBase {
	
	private static DataSet DS;
	private String Location;
	private Logger logger = null;
	
	int count=0;
	
	public DataBase(String loc) {
		logger = new Logger(this);
		// TODO Auto-generated constructor stub
		this.Location=loc;
		if(!this.Load()) {
			throw new NullPointerException("Can't load file");
		}
		count=this.CreatOrderID();
		logger.Log("Succes load file");
		
	}
	
	public ArrayList<Order> PullOrder(){
		
		return (ArrayList<Order>) this.DS.getOrderList().clone();
		
	}
	
	
	public boolean Load() {
		
		try {
			FileInputStream fis = new FileInputStream(java.time.LocalDate.now()+" "+this.Location);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.DS = (DataSet) ois.readObject();
			ois.close();
			logger.Log("Find Today's save sorry for crush");
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.Log("Class can not ne created");
			return false;
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			try {
				FileInputStream fis = new FileInputStream(this.getYesterdayDate()+" "+this.Location);
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.DS = (DataSet) ois.readObject();
				ois.close();
				logger.Log("Find yestoday good day");
				return true;
			} catch (ClassNotFoundException ew) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.Log("Class can not ne created");
				return false;
			}catch (FileNotFoundException ew) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.Log("File doesn't exise Now Creat one.");
				DS = new DataSet();
				return true;
			}catch (IOException ew) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.Log("Faild to read file");
				return false;
			}

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.Log("Faild to read file");
			return false;
		}
	}
	
	
	public synchronized boolean Save() {

			try {
				FileOutputStream fos;
				fos = new FileOutputStream(java.time.LocalDate.now()+" "+this.Location);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.DS);
				oos.close();
				return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.Log("Faild to save file");
				return false;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.Log("Faild to save file");
				return false;
			}
		
	}
	
	public synchronized boolean ShutDown() {
		if(!this.Save()) {
			throw new NullPointerException("Can't save file");
		}
		logger.Log("DataBase has been saved");
		logger.LogStop();
		return true;
	}


	
	public Object Search(String type, String[] Keywords) {
		
		if(type.equals("Order")) {
			
			ArrayList<Order> res = new ArrayList<Order>();
			
			for (Order tmp : DS.getOrderList()) 
			{ 
			    if(tmp.Search(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else if(type.equals("Customer")) {
			
			ArrayList<Customer> res = new ArrayList<Customer>();
			
			for (Customer tmp : DS.getCustomerList()) 
			{ 
			    if(tmp.Search(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else if(type.equals("Stored")) {
			
			ArrayList<Product> res = new ArrayList<Product>();
			
			for (Product tmp : DS.getStoredProductList()) 
			{ 
			    if(tmp.Search(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else {
			return Keywords;
		}
	}
	
public Object SoftSearch(String type, String[] Keywords) {
		
		if(type.equals("Order")) {
			
			ArrayList<Order> res = new ArrayList<Order>();
			
			for (Order tmp : DS.getOrderList()) 
			{ 
			    if(tmp.SoftSearch(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else if(type.equals("Customer")) {
			
			ArrayList<Customer> res = new ArrayList<Customer>();
			
			for (Customer tmp : DS.getCustomerList()) 
			{ 
			    if(tmp.SoftSearch(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else if(type.equals("Stored")) {
			
			ArrayList<Product> res = new ArrayList<Product>();
			
			for (Product tmp : DS.getStoredProductList()) 
			{ 
			    if(tmp.SoftSearch(Keywords)) {
			    	res.add(tmp);
			    }
			}
			
			return res;
			
		} else {
			return Keywords;
		}
	}
	/*
	public Order LocatOrder(Order o) {
		for(Order tmp : this.DS.getOrderList()) {
			if(tmp.equals(o)) {
				return tmp;
			}
		} else {
			
		}
	}*/
	
	public synchronized boolean AddCustomer(Customer cus){
		
		String[] tmp = new String[1];
		tmp[0]=cus.getName();
		
		if(this.Search("Customer", tmp) instanceof Customer) {
			return false;
		}else {
			this.DS.addCus(cus);
			return true;
		}
		
	}
	
	public static String getYesterdayDate() {

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return dateFormat.format(cal.getTime());
	}
	
	public synchronized boolean AddOrder(Order ord){
		logger.Log("Saving: +count "+ord.toString());
		count=this.CreatOrderID();
		
		count++;
		Customer tmp = GetCus(ord.getCus().getName());
		if(tmp == null){
			ord.getCus().getOrderHistory().add(ord);
			this.DS.addCus(ord.getCus());
			
		} else {
			tmp.getOrderHistory().add(ord);
		}

		ord.setOrderID("+"+Integer.toString(count)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1));
		this.DS.addOrder(ord);
		logger.Log("Saved Order"+ord.toString());
		return true;
		
	}
	
	public synchronized int CreatOrderID()
	{
		int tmp =0;
				
		for(Order x:this.DS.getOrderList()) {
			
			//logger.Log(x.getOrderID().substring(x.getOrderID().indexOf('-')+1, x.getOrderID().length()).equals(Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1)));
			
			if(x.getOrderID().substring(x.getOrderID().indexOf('-')+1, x.getOrderID().length()).equals(Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1))){
				//logger.Log("Found this mouth' order");
				tmp++;
			}
		}
		logger.Log("Found this mouth' order numbber "+tmp);
		return tmp;
	}
	/*
	public synchronized boolean MotiOrder(Order org,Order tobe) {
		
		this.Search("Order", Keywords)
		
		if() {
			
		}
		
		return true;
	}*/

	public Customer GetCus(String s) {
		
		for(Customer x : this.DS.getCustomerList()) {
			if(x.getName().equals(s.trim())) {
				return x;
			}
		}
		return null;
	}
	
}