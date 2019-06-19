package DataBase;

import java.io.Serializable;
import java.util.ArrayList;

import DataObject.Order.Customer;
import DataObject.Order.Order;
import DataObject.Product.Product;

public class DataSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5839926720369335475L;
	
	private ArrayList<Order> OrderList=new ArrayList<Order>();

	private ArrayList<Customer> CustomerList=new ArrayList<Customer>();

	private ArrayList<Product> ProductList=new ArrayList<Product>();

	private ArrayList<String> ProductIDList=new ArrayList<String>();

	private ArrayList<String> ColorIDList=new ArrayList<String>();
	
	private ArrayList<Product> StoredProductList=new ArrayList<Product>();
	

	
	public void addCus(Customer c) {
		this.CustomerList.add(c);
	}
	
	public void addOrder(Order o) {
		this.OrderList.add(o);
	}

	public DataSet() {

		OrderList=new ArrayList<Order>();
		
		CustomerList=new ArrayList<Customer>();
		
		ProductList=new ArrayList<Product>();
		
		StoredProductList=new ArrayList<Product>();
		
	}

	public ArrayList<Order> getOrderList() {
		return OrderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		OrderList = orderList;
	}

	public ArrayList<Customer> getCustomerList() {
		return CustomerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		CustomerList = customerList;
	}

	public ArrayList<Product> getProductList() {
		return ProductList;
	}

	public void setProductList(ArrayList<Product> productList) {
		ProductList = productList;
	}

	public ArrayList<Product> getStoredProductList() {
		return StoredProductList;
	}

	public void setStoredProductList(ArrayList<Product> storedProductList) {
		StoredProductList = storedProductList;
	}

	@Override
	public String toString() {
		return "DataSet [OrderList=" + OrderList + ", CustomerList=" + CustomerList + ", ProductList=" + ProductList
				+ ", StoredProductList=" + StoredProductList + "]";
	}
}
