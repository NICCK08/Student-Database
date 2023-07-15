package sdbms;
import java.util.Scanner;
import java.util.Set;

//import com.InvalidChoiceException;
//import customexception.InvalidChoiceException;
import customexception.*;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Implementation Class
public class StudentManagementSystemImpl implements StudentManagementSystem {

	Scanner scan=new Scanner(System.in);

	//Key->Student ID and Value->Student Object
	Map<String,Student> db=new LinkedHashMap<String,Student>();

	@Override
	public void displayStudent() {

		//Accept Student ID
		System.out.println("Enter the Student ID: ");
		String id=scan.next();//.toUppercase()

		//Convert toUpperCase()
		id=id.toUpperCase();

		//Check if ID is present or not-> containskey()->(id==key)
		if(db.containsKey(id)) {//If-> get the Student Object->get()->getAge(),getName()...............
			System.out.println("Student Record Found!!!");
			System.out.println("------------------------");
			Student std=db.get(id);//Getting Student Object Based on ID
			System.out.println("ID: "+std.getId());
			System.out.println("Age: "+std.getAge());
			System.out.println("Name: "+std.getName());
			System.out.println("Marks: "+std.getMarks());
			//printing reference variable as toString() is Overridden
			//System.out.println(std);
		}
		else {//else->StudentNotFoundException
			try{
				String message="Student with ID "+id+" is not found!!!";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudent() {
		if(!db.isEmpty()) {//Checking if DB is Not Empty
			System.out.println("Student Records are as follows");
			System.out.println("------------------------------");
			Set<String> keys=db.keySet();
			for(String key:keys) {
				Student std=db.get(key);//Getting Student Object
				System.out.println(std);//toString() is Overridden
				//System.out.println(db.get(key));
			}
		}
		else {
			//StudentNotFoundException
			try{
				String message="Student Not Found";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void addStudent() {
		//Accepting Age
		System.out.println("Enter Age:");
		int age=scan.nextInt();

		//Accepting Name
		System.out.println("Enter Name:");
		String name=scan.next();

		//Accepting Marks
		System.out.println("Enter Marks:");
		int marks=scan.nextInt();

		//Creating A Student Instance(Object)
		Student std=new Student(age,name,marks);

		//db->Map->put()->id,std
		db.put(std.getId(),std );

		//Print the Student ID
		System.out.println("Student Record Inserted Successfully!!!");
		System.out.println("Your Student Id is "+std.getId());

	}

	@Override
	public void removeStudent() {
		//Accept Student ID
		System.out.println("Enter the Student ID: ");
		String id=scan.next();//.toUppercase()

		//Convert toUpperCase()
		id=id.toUpperCase();

		//Check if ID is present or not-> containskey()->(id==key)
		if(db.containsKey(id)) {//If-> get the Student Object->get()->getAge(),getName()...............
			System.out.println("Student Record Found!!!");
			System.out.println("------------------------");
			System.out.println(db.get(id));//Printing Student Object
			db.remove(id);//Removing Student Object Based on ID(Key & Value)
			System.out.println("Student ID: "+id+" has been deleted successfully!!!");
		}
		else {//else->StudentNotFoundException
			try{
				String message="Student with ID: "+id+" is not found!!!";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudents() {
		if(!db.isEmpty()) {
			System.out.println("Number of Student Record"+db.size());
			db.clear();
			System.out.println("All Students Records are Deleted Successfully!!!");
		}
		else {
			try{
				String message="No Student Records to Delete!!!";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter Student ID: ");
		String id=scan.next().toUpperCase();

		if(db.containsKey(id)) {
			System.out.println("Student Record Found");
			Student std=db.get(id);//Getting Value(Student Object)

			System.out.println("1:Update Age\n2:Update Name\n3:Update Marks\nEnter Choice:");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter Age:");
				int age=scan.nextInt();
				std.setAge(age);//std.setAge(scan.nextInt());
				System.out.println("Age Updated Successfully");
				break;
			case 2:
				System.out.println("Enter Name:");
				String Name=scan.next();
				std.setName(Name);//std.setName(scan.next());
				System.out.println("Name Updated Successfully");
				break;
			case 3:
				System.out.println("Enter Marks:");
				int marks =scan.nextInt();
				std.setMarks(marks);//std.setMarks(scan.nextInt());
				System.out.println("Marks Updated Successfully");
				break;

			default:
				try {//InvalidChoice
					String message="Invalid Choice,Please Enter Valid Choice";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else {
			try{
				String message="Student with ID:"+id+" Record Found!!!";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}

	@Override
	public void countStudent() {
		System.out.println("Number of Student Records: "+db.size());
	}

	@Override
	public void sortStudent() {
		/**
		 * We cannot sort Map based on values,therefore we are getting
		 * the values from Map & storing it inside list so that we can sort
		 * list using-> Collections.sort(list,sorting logic Object);
		 */

		//Reference of list and Object of Arraylist storing Student Objects
		List<Student> list=new ArrayList<Student>();

		//Converting Map into Set,which stores keys(Id)
		Set<String> keys=db.keySet();

		//Traversing Keys(Id)
		for (String key:keys) {
			//Getting value(Student Object)& adding it into list
			list.add(db.get(key));
		}
		System.out.println("1:Sort Student Age\n2:Sort Student Name\n3:Sort Student Marks\n4:Sort Student IDd\nEnter Choice:");
		int choice=scan.nextInt();

		switch(choice) {
		case 1:
			Collections.sort(list,new SortStudentByAge());
			for(Student s:list) {
				System.out.println(s);
			}
			break;
		case 2:
			Collections.sort(list,new SortStudentByName());
			for(Student s:list) {
				System.out.println(s);
			}
			break;
		case 3:
			Collections.sort(list,new SortStudentByMarks());
			for(Student s:list) {
				System.out.println(s);
			}
			break;
		case 4:
			Collections.sort(list,new SortStudentByID());
			for(Student s:list) {
				System.out.println(s);
			}
			break;
		default:
			try {//InvalidChoice
				String message="Invalid Choice,Please Enter Valid Choice";
				throw new InvalidChoiceException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void findStudentWithHighestMarks() {
		List<Student> list=new ArrayList<Student>();

		//Converting Map into Set,which stores keys(Id)
		Set<String> keys=db.keySet();

		//Traversing Keys(Id)
		for (String key:keys) {
			//Getting value(Student Object)& adding it into list
			list.add(db.get(key));
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student with Highest Marks: ");
		System.out.println(list.get(list.size()-1));
	}

	@Override
	public void findStudentWithLowestMarks() {
		List<Student> list=new ArrayList<Student>();

		//Converting Map into Set,which stores keys(Id)
		Set<String> keys=db.keySet();

		//Traversing Keys(Id)
		for (String key:keys) {
			//Getting value(Student Object)& adding it into list
			list.add(db.get(key));
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student with Lowest Marks: ");
		System.out.println(list.get(0));
	}
}