package Main;

import java.util.Scanner;

public class ServerStart {

	
	public static void main(String args[]) {
		ServerMain lol = new ServerMain(20114);
		//lol.start();
		Scanner sc=new Scanner(System.in);
		String tmp = "";
		
		
		System.out.println("============Server is on============");

		while(!lol.hasStopped()) {
			tmp=sc.nextLine();
			if(tmp.equals("/ls")||tmp.equals("/list")) {
				
				int i=0;
				
				for(ServerThread x:lol.getThreads()) {
					if(x!=null) {
						i++;
					}
				}
				
				System.out.println("Find "+i+" Client: ");
				
				for(ServerThread x:lol.getThreads()) {
					if(x!=null) {
						System.out.println("   "+x.toString());
					}
				}
				
				//System.out.println();
				//System.out.println("=========================================");

			}else if(tmp.equals("/quit")||tmp.equals("/stop")) {
				lol.shutdown();
				System.out.println("Finalized");
			}
		}
	}
}