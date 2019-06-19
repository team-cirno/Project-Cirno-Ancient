package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Stests {
private ServerSocket serverSocket = null;
private Socket socket = null;
private ObjectInputStream inStream = null;

public Stests() {

}

public void communicate() {
try {
serverSocket = new ServerSocket(4445);
socket = serverSocket.accept();
System.out.println("Connected");
inStream = new ObjectInputStream(socket.getInputStream());

Student student = (Student) inStream.readObject();
System.out.println("DataObject received = " + student);
socket.close();

} catch (SocketException se) {
System.exit(0);
} catch (IOException e) {
e.printStackTrace();
} catch (ClassNotFoundException cn) {
cn.printStackTrace();
}
}

public static void main(String[] args) {
Stests server = new Stests();
server.communicate();
}
}