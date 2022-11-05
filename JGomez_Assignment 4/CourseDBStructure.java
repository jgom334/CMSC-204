import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Jonathan Gomez
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface {

	ArrayList<LinkedList<CourseDBElement>> hashTable;

	public CourseDBStructure(int n) {

		int str = (int) (n / 1.5) + 1;

		int num = str + (3 - (str % 4));

		boolean prime = false;
		while (!prime) {

			prime = true;
			for (int i = 2; i <= num / 2; i++) {
				if (num % i == 0) {
					prime = false;
					break;
				}
			}
			if (!prime)
				num += 4;
		}

		hashTable = new ArrayList<LinkedList<CourseDBElement>>();
		for (int i = 0; i < num; i++) {
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}

	public CourseDBStructure(String Testing, int size) {

		hashTable = new ArrayList<LinkedList<CourseDBElement>>();
		for (int i = 0; i < size; i++) {
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}
	/**
	 * Adds a CourseDBElement object to the CourseDBStructure using the hashcode of
	 * the CourseDatabaseElemen object's crn value. If the CourseDatabaseElement
	 * already exists, exit quietly
	 * 
	 * @param element the CourseDBElement to be added to CourseDBStructure
	 */
	@Override
	public void add(CourseDBElement element) {

		LinkedList<CourseDBElement> data;
		for (int i = 0; i < hashTable.size(); i++) {
			data = hashTable.get(i);
			for (int j = 0; j < data.size(); j++) {
				if (data.get(j).getCRN() == element.getCRN()) {
					data.set(j, element);
					return;
				}
			}
		}

		int index = element.hashCode() % hashTable.size();
		hashTable.get(index).add(element);
	}
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		LinkedList<CourseDBElement> data;
		for (int i = 0; i < hashTable.size(); i++) {
			data = hashTable.get(i);
			for (int j = 0; j < data.size(); j++) {
				if (data.get(j).getCRN() == crn)
					return data.get(j);
			}
		}
		throw new IOException();
	}
	/**
	 * @return an array list of string representation of each course in the data
	 *         structure separated by a new line. Refer to the following example:
	 *         Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular
	 *         Room:SC100 Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody
	 *         Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {

		ArrayList<String> struc = new ArrayList<String>();
		LinkedList<CourseDBElement> data;
		CourseDBElement e;

		for (int i = 0; i < hashTable.size(); i++) {
			data = hashTable.get(i);
			for (int j = 0; j < data.size(); j++) {
				e = data.get(j);
				if (e != null) {
					struc.add("\nCourse:" + e.getID() + " CRN:" + e.getCRN() + " Credits:" + e.getCredits()
							+ " Instructor:" + e.getInstructorName() + " Room:" + e.getRoomNum());
				}
			}
		}
		return struc;
	}
	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the
	 * array)
	 */
	@Override
	public int getTableSize() {
		return hashTable.size();
	}
}