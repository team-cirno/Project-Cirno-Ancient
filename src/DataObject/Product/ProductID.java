package DataObject.Product;

public class ProductID extends DataObject.ObjectID {

  private String cid;

  public ProductID(String cid) {
    this.cid = cid;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  @Override
  protected void log() {

  }
}
