package Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import DataBase.DataBase;
import DataObject.Order.Customer;
import DataObject.Order.Order;
import DataObject.Order.Pack;
import Tool.Logger;

public class ServerMain extends Thread{
	
	protected int          ServerPortVal;
    protected ServerSocket serverSocket = null;
    protected boolean      HasStopped    = false;
    protected Thread       MovingThread= null;
    protected DataBase	   DB=null;
    protected final String	   Location="DataBase.ser";
    public int count=0;
    
    // This chat server can accept up to maxClientsCount clients' connections.
    private static final int maxClientsCount = 20;
    private static final ServerThread[] threads = new ServerThread[maxClientsCount];
    private Logger logger = null;
    
    public ServerMain(int i)
    {
    	this.logger=new Logger(this);
    	HasStopped=false;
    	this.ServerPortVal=i;
    	DB = new DataBase(Location);
    	this.start();
    }
    
    public void run()
    {
    	try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
    		serverSocket=OpenServerSocket();
            Socket socket=null;
            //记录客户端的数量
            logger.Log("Server online secquins inabled");
            //循环监听等待客户端的连接
            while(!hasStopped()){
                //调用accept()方法开始监听，等待客户端的连接
            	try {
                    socket=serverSocket.accept();

            	} catch (SocketException e) {
            		logger.Log("Socket closed.");
            	}
                //创建一个新的线程加入线程列表
                try {
	                int i = 0;
	                for (i = 0; i < maxClientsCount; i++) {
	                  if (threads[i] == null) {
	                	 logger.Log("plaseing threads in spot "+Integer.toString(i));
	                    (threads[i] = new ServerThread(socket, threads,i,DB,this)).start();
	                    break;
	                  }
	                }
	                if (i == maxClientsCount) {
	                  PrintStream os = new PrintStream(socket.getOutputStream());
	                  os.println("服务器繁忙");
	                  os.close();
	                  socket.close();
	                }
	              } catch (IOException e) {
	                logger.Log(e.toString());
                }
              
                count++;//统计客户端的数量
                logger.Log("客户端的数量："+count);
                InetAddress address=socket.getInetAddress();
                logger.Log("当前客户端的IP："+address.getHostAddress());
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	this.stop();
    }
    
    public synchronized boolean hasStopped() 
    {
        return this.HasStopped;
    }
    
    public synchronized void shutdown()
    {
    	
        this.HasStopped = true;
        this.DB.ShutDown();
        
		for(ServerThread x:this.threads) {
			if(x!=null) {
				x.shutdown();
				x.stop();
				x=null;
			}
		}
		
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Server can not be closed - Please check error", e);
        }
        logger.Log("Server CLOSED");
        logger.LogStop();
    }
    
    public synchronized void Cast(String s,Object o) {
    	int count=0;
    	if(s=="Update") {
        	for (ServerThread point : threads) {
        		if(point!=null) {
        			count++;
        			point.cast(new Pack("Update",(Order) o));
        		}
        	}
        	logger.Log("Cast to "+Integer.toString(count)+" Client: "+o.toString());
    	}

    }
    
    public Customer GetCus(String S) {
    	return this.DB.GetCus(S);
    }
    
    private ServerSocket OpenServerSocket()
    {
        try {
        	return new ServerSocket(this.ServerPortVal);
        } catch (IOException e) {
            throw new RuntimeException("Not able to open the port "+Integer.toString(this.ServerPortVal), e);
        }
    }

	public static ServerThread[] getThreads() {
		return threads;
	}

}
