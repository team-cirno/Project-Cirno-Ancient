package Object.Order;

import Object.Product.Product;

import java.io.Serializable;

public class Comp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2994972833734999587L;
	Product P;
	double Amount;
	double payAmount;
	int BoxNum;
	int BagNum;
	double Price;
	double totle;
	double KGPBB;
	
	
	public Comp(Product p, double KGPBB,int bagNum,int BoxNum, double price, double totle, double tmpa) {
		super();
		P = p;
		this.KGPBB=KGPBB;
		this.BagNum=bagNum;
		this.BoxNum=BoxNum;
		Amount = KGPBB*BagNum;
		Price = price;
		this.totle = totle;
		this.payAmount=tmpa;
	}
	
	
	

	public double getPayAmount() {
		return payAmount;
	}

	


	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}




	public int getBagNum() {
		return BagNum;
	}



	public void setBagNum(int bagNum) {
		BagNum = bagNum;
	}



	public double getKGPBB() {
		return KGPBB;
	}



	public void setKGPBB(double kGPBB) {
		KGPBB = kGPBB;
	}



	public Product getP() {
		return P;
	}

	public void setP(Product p) {
		P = p;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public int getBoxNum() {
		return BoxNum;
	}

	public void setBoxNum(int boxNum) {
		BoxNum = boxNum;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public double getTotle() {
		return totle;
	}

	public void setTotle(double totle) {
		this.totle = totle;
	}

	@Override
	public String toString() {
		return "Comp [P=" + P + ", Amount=" + Amount + ", BoxNum=" + BoxNum + ", Price=" + Price + ", totle=" + totle
				+ "]";
	}
	
	
	
	
}
