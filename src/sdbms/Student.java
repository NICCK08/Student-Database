package sdbms;

//Bean Class
public class Student {
	private String id;
	private int age;
	private String name;
	private int marks;
	private static int count=101;
	
	Student(int age,String name,int marks){
		this.id="JSP"+count;
		count++;
		this.name=name;
		this.age=age;
		this.marks=marks;
	}

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Id=" + id + ", Age=" + age + ", Name=" + name + ", Marks=" + marks;
	}
}