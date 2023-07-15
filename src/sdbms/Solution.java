package sdbms;
import java.util.Scanner;
import customexception.*;

//Main Class
public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student Database System");
		System.out.println("----------------------------------");	

		/**
		 * 1.Scanner
		 * 2.SMS=SMSImpl()->Upcasting->Abstraction
		 * 3.Infinite Loop->While(true)
		 * 4.Menu Driven Program->Display->1:Add Student 2:.......11:Exit
		 * 5.Switch->case1:...................case11:....Default:........
		 */

		Scanner scan=new Scanner(System.in);

		//Upcasting For Achieving Abstraction
		StudentManagementSystem S=new StudentManagementSystemImpl();

		//Infinite Loop
		while(true) {

			//Menu Driven Program
			System.out.println("1:AddStudent\n"
					+ "2:DisplayStudent\n"
					+ "3:DisplayAllStudent\n"
					+ "4:RemoveStudent\n"
					+ "5:RemoveAllStudents\n"
					+ "6:UpdateStudent\n"
					+ "7:CountStudent\n"
					+ "8:SortStudent\n"
					+ "9:FindStudentWithHighestMarks\n"
					+ "10:FindStudentWithLowestMarks\n"+"11:Exit\nEnter your Choice\n");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				S.addStudent();
				break;

			case 2:
				S.displayStudent();
				break;

			case 3:
				S.displayAllStudent();
				break;

			case 4:
				S.removeStudent();
				break;

			case 5:
				S.removeAllStudents();
				break;

			case 6:
				S.updateStudent();
				break;

			case 7:
				S.countStudent();
				break;

			case 8:
				S.sortStudent();
				break;

			case 9:
				S.findStudentWithHighestMarks();
				break;

			case 10:
				S.findStudentWithLowestMarks();
				break;

			case 11:System.out.println("Thank You!!!");
			System.exit(0);

			default:try{//InvalidChoice
				String message="Invalid Choice,Please Enter Valid Choice";
				throw new InvalidChoiceException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

			}//End of Switch 

		  System.out.println("---------------------------------");

		}//End Of While Loop

	}//End of Main()

}//End of Class
