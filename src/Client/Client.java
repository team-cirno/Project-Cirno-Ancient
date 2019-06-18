package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import GUI.GUIMain;
import GUI.Subwindow;
import Object.Order.Customer;
import Object.Order.Order;
import Object.Order.Pack;
import Tool.Logger;  

public class Client extends Thread{
	
	Logger logger = null;
	
	Socket socket =null;
    ObjectInputStream	io=null;
    ObjectOutputStream 	oo=null;
    GUIMain g =null;
    boolean Alive = true;
    //Logger logger = null;
    Subwindow Focuse=null;
	
	public Client(GUIMain g) {
		logger=new Logger(this);
		this.g=g;
		Alive = true;
		
		try {
			socket = new Socket("localhost", 20114);
			
			oo = new ObjectOutputStream(socket.getOutputStream());
        	io = new ObjectInputStream(socket.getInputStream());
        	
        	
		} catch (UnknownHostException e) {
            logger.Log("Don't know about host");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Client() {
		
		//this.g=g;
		Alive = true;
		
		try {
			socket = new Socket("localhost", 20114);
			
			oo = new ObjectOutputStream(socket.getOutputStream());
        	io = new ObjectInputStream(socket.getInputStream());
        	
        	
		} catch (UnknownHostException e) {
            logger.Log("Don't know about host");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public void run(){
			
			while (this.Alive) {
				try {
					logger.Log("Waiting for server...");
					Pack tmp = (Pack)io.readObject();
					logger.Log("Mas abtioed "+tmp.getMes());
					if(tmp.getMes().equals("Update")) {
						logger.Log("Update");
						this.g.w.uplist((Order)tmp.getBag());
						
					} else if(tmp.getMes().equals("Cus")){
						if(tmp.getBag()!=null)
							this.Focuse.SetCus((Customer)tmp.getBag());
						else
							this.Focuse.SetCus(new Customer(Focuse.tl[0].getText()));

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.Log("Server Contion Lost...");
					logger.Log("Shut down Client...");
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}
	

	
	public String SendOrder(Order o) {
		
    	try {
    		Pack pac = new Pack("PlaceOrder",o);
			oo.writeObject(pac);
			Pack res = (Pack) io.readObject();
			return res.getMes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return "Faild";
    	
	}
	
	public ArrayList<Order> PullOrder(){
		
		Pack pac = new Pack("PullList");
		try {
			oo.writeObject(pac);
			
			
			Pack tmp = (Pack)io.readObject();
			ArrayList<Order> res = (ArrayList<Order>) tmp.getBag();

			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	public void ShutDown() {
		Pack pac = new Pack("Kill",null);
		try {
			oo.writeObject(pac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void sendPack(String s,Object o) {
		Pack pac = new Pack(s,o);
		try {
			oo.writeObject(pac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void ShutDownServer() {
		Pack pac = new Pack("KillAll");
		try {
			logger.Log("Try to sent");
			this.Alive=false;
			oo.writeObject(pac);
			logger.Log("Requse sent");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void GetCus(String s,Subwindow sw) {
		
		this.Focuse=sw;

		logger.Log("Creat Pack");
		Pack pac = new Pack("GetCus",new Customer(s));
		try {
			logger.Log("Sending Pac");
			oo.writeObject(pac);
			logger.Log("Pac Send Waiting Server to respond");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean PlaseOrder(Order o) {

		logger.Log("Plaseing Pack");
		Pack pac = new Pack("PlaceOrder",o);
		try {
			logger.Log("Sending Pac");
			oo.writeObject(pac);
			return true;
		} catch (IOException e) {
			return false;
		} 
	}
}

