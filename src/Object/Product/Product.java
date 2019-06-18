package Object.Product;

import java.io.Serializable;

public class Product implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -560273107844393937L;

  private String id;

  private String type;

  private String color;

  private String material;

  private String Brand;

  private String size;

  private String Shape;

  private boolean hole;

  private double bp;

  private String p_vale;

  public Product() {

  }


	/*
	public Product(Product tmp) {
		this.id=tmp.getId();
		this.type=tmp.getType();
		this.material=tmp.getMaterial();
		this.Brand=tmp.getBrand();
		this.size=tmp.getSize();
		this.Shape=tmp.getShape();
		this.hole=tmp.isHole();
	}*/


  public Product(String iD, String type, String material, String brand, String size, String shape, boolean hole) {
    super();
    id = iD;
    this.type = type;
    this.material = material;
    Brand = brand;
    this.size = size;
    Shape = shape;
    this.hole = hole;
  }

  public Product(String iD, String color) {
    super();
    id = iD;
    this.color = color;
  }

  public Product(String iD) {
    this.id = iD;
  }

  public boolean Search(String[] key) {

    String all = this.toString();

    for (String tmp : key) {
      if (all.indexOf(tmp) == -1) {
        return false;
      }
    }

    return true;

  }

  public boolean SoftSearch(String[] key) {

    String all = this.toString();

    for (String tmp : key) {
      if (all.indexOf(tmp) != -1) {
        return true;
      }
    }

    return false;

  }


  public String getId() {
    return id;
  }

  public void setId(String iD) {
    id = iD;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getBrand() {
    return Brand;
  }

  public void setBrand(String brand) {
    Brand = brand;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
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

  public double getBp() {
    return bp;
  }

  public void setBp(double d) {
    bp = d;
  }

  public String getP_vale() {
    return p_vale;
  }

  public void setP_vale(String pVale) {
    p_vale = pVale;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", type=" + type + ", color=" + color + ", material=" + material + ", Brand="
            + Brand + ", size=" + size + ", Shape=" + Shape + ", hole=" + hole + ", SP=" + bp + ", p_vale=" + p_vale
            + "]";
  }
}