package Server;

import java.net.Socket;

public class Main {
  public static void main(String[] args) throws Exception {
    Socket client = new Socket("google.com", 80);
    client.setKeepAlive(true);
    System.out.println(client.getKeepAlive());

    client.close();
  }
}