package Order;

import java.io.Serializable;

public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -560273107844393937L;

	private String ID;
	
	private String Type;
	
	private String Color;
	
	private String Material;
	
	private String Brand;
	
	private String Size;
	
	private String Shape;
	
	private boolean hole;
	
	private double BP;
	
	private String PVale;
	
	public Product() {
		
	}
	/*
	public Product(Product tmp) {
		this.ID=tmp.getID();
		this.Type=tmp.getType();
		this.Material=tmp.getMaterial();
		this.Brand=tmp.getBrand();
		this.Size=tmp.getSize();
		this.Shape=tmp.getShape();
		this.hole=tmp.isHole();
	}*/
	
	
	
	public Product(String iD, String type, String material, String brand, String size, String shape, boolean hole) {
		super();
		ID = iD;
		Type = type;
		Material = material;
		Brand = brand;
		Size = size;
		Shape = shape;
		this.hole = hole;
	}
	
	public Product(String iD, String color) {
		super();
		ID = iD;
		Color = color;
	}
	public Product(String iD) {
		this.ID=iD;
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
	
	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getShape() {
		return Shape;
	}

	public void setShape(String shape) {
		Shape = shape;
	}

	public boolean isHole() {
		return hole;
	}

	public void setHole(boolean hole) {
		this.hole = hole;
	}

	public double getBP() {
		return BP;
	}

	public void setBP(double d) {
		BP = d;
	}

	public String getPVale() {
		return PVale;
	}

	public void setPVale(String pVale) {
		PVale = pVale;
	}

	@Override
	public String toString() {
		return "Product [ID=" + ID + ", Type=" + Type + ", Color=" + Color + ", Material=" + Material + ", Brand="
				+ Brand + ", Size=" + Size + ", Shape=" + Shape + ", hole=" + hole + ", SP=" + BP + ", PVale=" + PVale
				+ "]";
	}
	
	
	
	

}