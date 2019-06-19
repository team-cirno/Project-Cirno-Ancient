package DataObject;

import java.util.UUID;

public abstract class ObjectID {


  private String sid;
  private Integer iid;
  private String bid;
  private String hid;
  private UUID uuid;


  public ObjectID() {
    uuid = UUID.randomUUID();
    iid = uuid.hashCode();
    sid = Integer.toString(iid);
    hid = Integer.toHexString(iid);
    bid = Integer.toBinaryString(iid);
  }

  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public Integer getIid() {
    return iid;
  }

  public void setIid(Integer iid) {
    this.iid = iid;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getHid() {
    return hid;
  }

  public void setHid(String hid) {
    this.hid = hid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  protected abstract void log();
}
