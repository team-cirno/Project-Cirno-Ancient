package GUI;

import Client.Client;

public class GUIMain {
	
	public Window w  =null;
	public Client c=null;

	public GUIMain() {
		// TODO Auto-generated constructor stub
		c=new Client(this);
		System.out.println("lol");
		w = new Window(this);
		System.out.println("lol");
		c.start();
		System.out.println("loll");

	}
	
	public void recount() {
		// TODO Auto-generated constructor stub
		c=new Client(this);
		c.start();
		System.out.println("lol");
		this.w.getList();
	}
	
	public void Auth(String s) {
		if(s.equals("Caiwu")) {
			this.LunchCai();
		} else {
			
		}
	}
	
	private void LunchCai() {
		
	}

}
