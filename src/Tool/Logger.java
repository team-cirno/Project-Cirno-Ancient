package Tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
	
	private Object Obj=null;
	
	public static ArrayList<String> LogSet = new ArrayList<String>();
	
	public static int num=0;
	
	public void Log(String log) {
		
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
	    Date time = new Date();  
		String CreatTime=formatter.format(time); 
		System.out.println(CreatTime+" "+Obj.getClass().getSimpleName()+": "+log);
		LogSet.add(CreatTime+" "+Obj.getClass().getSimpleName()+": "+log);
	}
	
	public Logger(Object obj){
		this.Obj=obj;
		this.num++;
	}
	
	public void LogStop() {
		if(num==1) {
			FileWriter writer;
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
			Date time = new Date();  
			String CreatTime=formatter.format(time); 
			createFile(CreatTime+"output.txt",this.LogSet);
		}
		num--;
	}
	
	public void createFile(String s,ArrayList a) {
		
	}


}