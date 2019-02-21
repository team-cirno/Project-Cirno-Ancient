package Client;

import java.io.Serializable;

public class Student implements Serializable {

private static final long serialVersionUID = 5950169519310163575L;
private int id;
private String name;

public Student(int id, String name) {
this.id = id;
this.name = name;
}

@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + "]";
}

}