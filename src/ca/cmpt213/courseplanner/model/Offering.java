package ca.cmpt213.courseplanner.model;

public class Offering implements Comparable {
	private String courseName;
	private String instructors;
	private String location;
	private String componentCode;
	private String catalogNumber;
	private String semester;
	private String subject;
	
	private int enrollmentTotal;
	private int enrollmentCapacity;
	
	public Offering(String semester, String subject, String catalogNumber, String location, String enrollmentCapacity, String enrollmentTotal, String instructors, String componentCode) {
		this.semester = semester;
		this.courseName = subject + ' ' + catalogNumber;
		this.catalogNumber = catalogNumber;
		this.subject = subject;
		this.location = location;
		this.enrollmentCapacity = Integer.parseInt(enrollmentCapacity);
		this.enrollmentTotal = Integer.parseInt(enrollmentTotal);
		this.instructors = instructors;
		this.componentCode = componentCode;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseCode() {
		return catalogNumber;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getLocation() {
		return location;
	}
	public String getInstructors() {
		return instructors;
	}
	
	public void setInstructors(String instructors) {
		this.instructors = instructors;
	}
	
	public String getComponentCode() {
		return componentCode;
	}
	
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	public int getEnrollmentTotal() {
		return enrollmentTotal;
	}
	
	public void setEnrollmentTotal(int enrollmentTotal) {
		this.enrollmentTotal = enrollmentTotal;
	}
	
	public int getEnrollmentCapacity() {
		return enrollmentCapacity;
	}
	
	public void setEnrollmentCapacity(int enrollmentCapacity) {
		this.enrollmentCapacity = enrollmentCapacity;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getSemester() {
		return this.semester;
	}
	public int getSemesterYear() {
		return Integer.parseInt("2" + semester.charAt(0) + semester.substring(1, 3)) - 100;	
	}
	
	public String getSemesterSeason() {
		if (semester.charAt(3) == '1') {
			return "Spring";
		} else if (semester.charAt(3) == '4') {
			return "Summer";
		} else if (semester.charAt(3) == '7') {
			return "Fall";
		}
	return null;	
	}

	@Override
	public boolean equals(Object offering) {
		Offering other = (Offering) offering;
		if (this.semester.equals(other.getSemester()) && this.courseName.equals(other.getCourseName()) 
				&& this.location.equals(other.getLocation()) && this.instructors.equals(other.getInstructors()) 
				&& this.componentCode.equals(other.getComponentCode())) {
					return true;
		}
		return false;	
	}

	@Override
	public int compareTo(Object offering) {
		Offering other = (Offering) offering;
		if (this.courseName.compareTo(other.getCourseName()) > 0) {
			return 1;
		} else if (this.courseName.compareTo(other.getCourseName()) < 0) {
			return -1;
		}
		
		if (Integer.parseInt(this.semester) > Integer.parseInt(other.getSemester())) {
			return 1;
		} else if (Integer.parseInt(this.semester) < Integer.parseInt(other.getSemester())) {
			return -1;	
		}
		
		if (this.location.compareTo(other.getLocation()) > 0) {
			return 1;
		} else if (this.location.compareTo(other.getLocation()) < 0) {
			return -1;
		}
		
		if (this.componentCode.compareTo(other.getComponentCode()) > 0 && this.courseName.equals(other.getCourseName()) 
			&& this.instructors.equals(other.getInstructors())) {
			return 1;
		} else if (this.componentCode.compareTo(other.getComponentCode()) < 0 && this.courseName.equals(other.getCourseName()) 
				   && this.instructors.equals(other.getInstructors())) {
			return -1;
		}
		return 0;
	}
}
