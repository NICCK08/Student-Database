package customexception;

import java.util.Comparator;

import sdbms.Student;

public class SortStudentByID implements Comparator<Student> {

	@Override
	public int compare(Student x, Student y) {
		return x.getId().compareTo(y.getId());
	}
}//x->Object to be inserted & y->Already existing Object
//For string comparison,we need to call comapareTo() of String Class
