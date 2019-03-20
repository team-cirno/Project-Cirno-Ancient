package Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileTool {
	
	public static void ObjectLog() {
		
		try {
			
			Person p1 = new Person("John", 30, "Male");
			Person p2 = new Person("Rachel", 25, "Female");
			
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(p1);
			o.writeObject(p2);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Person pr1 = (Person) oi.readObject();
			Person pr2 = (Person) oi.readObject();

			System.out.println(pr1.toString());
			System.out.println(pr2.toString());

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean ObjectLogger(String file, Object obj) {
		
		FileOutputStream f;
		try {
			f = new FileOutputStream(new File(file));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(obj);
			o.close();
			f.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Faild to open file");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("Faild to creat object");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
				
	}
	
	public static Object ObjectReader() {
		
		
		FileInputStream fi;
		try {
			fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Person pr1 = (Person) oi.readObject();
			
			oi.close();
			fi.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
		
	}

	public static void main(String[] args) {
		
		Person p1 = new Person("John", 30, "Male");
		Person p2 = new Person("Rachel", 25, "Female");

		try {
			
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(p1);
			o.writeObject(p2);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Person pr1 = (Person) oi.readObject();
			Person pr2 = (Person) oi.readObject();

			System.out.println(pr1.toString());
			System.out.println(pr2.toString());

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
