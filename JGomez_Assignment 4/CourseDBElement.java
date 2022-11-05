/**
 * @author Jonathan Gomez
 *
 */
public class CourseDBElement implements Comparable {

	private String id, roomNum, instructor;;
	private int crn, credits;

	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructorName) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructorName;
	}

	public CourseDBElement() {
		this.id = this.roomNum = this.instructor = "null";
		this.crn = this.credits = -1;
	}
// 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crn;
		return result;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public int getCRN() {
		return crn;
	}

	public void setCRN(int crn) {
		this.crn = crn;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInstructorName() {
		return instructor;
	}

	public void setInstructorName(String instructorName) {
		this.instructor = instructorName;
	}

	public int compareTo(CourseDBElement o) {
		if (this.crn < o.getCRN())
			return -1;
		else if (this.crn > o.getCRN())
			return 1;
		else
			return 0;
	}
}