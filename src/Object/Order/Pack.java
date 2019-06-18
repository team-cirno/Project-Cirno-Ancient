package Object.Order;

import java.io.Serializable;

public class Pack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6443450225668483040L;

	private String Mes;
	
	private Object bag;

	public Pack(String mes, Object bag) {
		super();
		Mes = mes;
		this.bag = bag;
	}
	
	public Pack(String mes) {
		super();
		Mes = mes;
		
	}

	@Override
	public String toString() {
		return "Pack [Mes=" + Mes + ", bag=" + bag + "]";
	}

	public String getMes() {
		return Mes;
	}

	public void setMes(String mes) {
		Mes = mes;
	}

	public Object getBag() {
		return bag;
	}

	public void setBag(Object bag) {
		this.bag = bag;
	}
	
	

}