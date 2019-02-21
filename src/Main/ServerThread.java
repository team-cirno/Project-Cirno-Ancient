package Main;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import DataBase.DataBase;
import Order.Customer;
import Order.Order;
import Order.Pack;
import Tool.Logger;

public class ServerThread extends Thread {
    // 和本线程相关的Socket
    Socket socket			=null;
    ServerThread[] threads	=null;
    public int ID			=0;
    int maxClientsCount		=0;
    private boolean Alive   =false;
    DataBase DB				=null;
    private Logger logger	=null;
    ServerMain sm 			=null;
    ObjectInputStream	io=null;
    ObjectOutputStream 	oo=null;
    
    
    InputStream 		is=null;
    InputStreamReader 	isr=null;
    BufferedReader 		br=null;
    OutputStream 		os=null;
    PrintWriter 		pw=null;

    public ServerThread(Socket socket, ServerThread[] threads, int ID,DataBase DB,ServerMain sm) {
        this.socket = socket;
        this.threads = threads;
        this.maxClientsCount = threads.length;
        this.ID=ID;
        Alive=true;
        this.DB=DB;
        this.logger=new Logger(this);
        this.sm=sm;
        
    	try {
			io = new ObjectInputStream(socket.getInputStream());
	    	oo = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//this.start();
    }

    //线程执行的操作，响应客户端的请求
    public void run(){


        
        
        try {

        	Pack pack = null;
        	
            while (Alive) {
            	try {
                	pack = (Pack) io.readObject();
                	
                	if(pack.getMes().equals("Kill")&&pack.getBag()==null) {
                		logger.Log("Shuting down Thread...");
                		Alive=false;
                		//sm.HasStopped=true;
                        break;               	
                    } else if ((pack.getMes().equals("KillAll")&&pack.getBag()==null)){
                		logger.Log("Shuting down Thread and server...");
                		Alive=false;
                		sm.stop();
                        break;
                    }
                	
                	if(!Alive) break;
                	
            	} catch (EOFException e){
            		//e.printStackTrace();
            		this.Alive=false;
            		logger.Log("Shuting down Thread...");
            		if(!Alive) break;
            	}
            	try {
            		if(Alive&&pack!=null) {
            			
                    	oo.writeObject(this.RequseHander(pack));
                    	pack = null;
            		}
            	} catch (SocketException e){
            		logger.Log("Client Contion lost...");
            		
            		if(!Alive) break;
            		Alive=false;
            	}
            }
        	
            socket.shutdownInput();//关闭输入流
            socket.shutdownOutput();//关闭输出流

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{

            //关闭资源
            try {
            	if(io!=null)
            		io.close();
            	if(oo!=null)
            		oo.close();
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
                //Find thread pointer

            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                synchronized (this) {
                	//logger.Log(threads[0].toString());

                    for (int i = 0; i < maxClientsCount; i++) {
                    	logger.Log("Closeing...");
                      if (threads[i] != null && threads[i] == this)
                      {
                    	  //Set pointer to null
                  		logger.Log("Thread CLOSED");
                  		logger.LogStop();
                  		//this.sm.stopthis(this);
                    	  threads[i]=null;
                    	  //close this thread
                    	  this.sm.count--;
                    	  this.stop();
                      	  logger.Log("TOOBAD");
                      }
                    }
                  }
            }
        }
    }
    
    private Pack RequseHander(Pack req) {
    	
    	if(req.getMes().equals("PlaceOrder")) {
    		logger.Log("Placeing Order...");

    		this.DB.AddOrder( (Order) req.getBag());
    		//this.DB.AddCustomer(((Order) req.getBag()).getCus());
    		logger.Log("Order Added.");
    		this.sm.Cast("Update",req.getBag());
    		return new Pack("Order Added.");
    	} else if(req.getMes().equals("PullList")){
    		logger.Log("PullList.");
    		return new Pack("Order List", this.DB.PullOrder());
    		
    	}else if(req.getMes().equals("GetCus")) {
    		logger.Log("GetCus.");
    		return new Pack("Cus", this.DB.GetCus( ((Customer) req.getBag()).getName()));

    	} else {
    		logger.Log("Will be incould in next DLC.");
    		return new Pack("N/A");
    	}
    }
    
    public void shutdown() {
    	this.Alive=false;
        //关闭资源
        try {
        	if(io!=null)
        		io.close();
        	if(oo!=null)
        		oo.close();
            if(pw!=null)
                pw.close();
            if(os!=null)
                os.close();
            if(br!=null)
                br.close();
            if(isr!=null)
                isr.close();
            if(is!=null)
                is.close();
            if(socket!=null)
                socket.close();
            //Find thread pointer

        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            synchronized (this) {
            	//logger.Log(threads[0].toString());

                for (int i = 0; i < maxClientsCount; i++) {
                	logger.Log("Closeing...");
                  if (threads[i] != null && threads[i] == this)
                  {
                	  //Set pointer to null
              		logger.Log("Thread CLOSED");
              		logger.LogStop();
              		//this.sm.stopthis(this);
                	  threads[i]=null;
                	  //close this thread
                	  this.sm.count--;
                	  this.stop();
                  	  logger.Log("TOOBAD");
                  }
                }
              }
        }
    }
    
    public void cast(Pack p) {
    	try {
			this.oo.writeObject(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public String toString() {
		return "ServerThread [socket=" + socket.toString();
	}
	
}
