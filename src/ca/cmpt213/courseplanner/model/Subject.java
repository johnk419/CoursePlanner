package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

public class Subject {
	private ArrayList<Course> subjectCourses;
	
	public Subject() {
		subjectCourses = new ArrayList<Course>();
	}
	
	public ArrayList<Course> getCourses() {
		return subjectCourses;
	}

	public void addCourse(Course course) {
		this.subjectCourses.add(course);
	}
	
	public String getSubjectName() {
		return subjectCourses.get(0).getSubjectName();
	}
}
