package ca.cmpt213.courseplanner.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class FileReader {
	private ArrayList<Offering> offerings;
	private ArrayList<Course> courses;
	private ArrayList<Subject> subjects;
	
	public FileReader() {
		offerings = new ArrayList<Offering>();
		courses = new ArrayList<Course>();
		subjects = new ArrayList<Subject>();
		
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();
		
		File data = new File(currentPath + "\\data\\course_data_2015.csv");
		try {
			Scanner inputStream = new Scanner(data);
			inputStream.nextLine();
			while (inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				String[] fields = new String[8];
				fields = line.split(",");
				Offering offering = new Offering(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]);
				offerings.add(offering);	
			}
	    } catch (FileNotFoundException e){
	        e.printStackTrace();
	    }
		
		java.util.Collections.sort(offerings);
//		for (Offering c : offerings) {
//			System.out.println(c.getCourseName() + ", " + c.getInstructors() + ", " + c.getSemester() + ", " + c.getComponentCode() + ", " + c.getLocation());
//		}
//		System.out.println(offerings.size());
	}
	
	public void compileCourses() {
		Course course = new Course();
		for (int i = 1; i < offerings.size(); ++i) {
			if (offerings.get(i - 1).getCourseName().equals(offerings.get(i).getCourseName())) {
				course.addOffering(offerings.get(i - 1));
				if (i == offerings.size() - 1) {
					course.addOffering(offerings.get(i));
					courses.add(course);
				}
			} else {
				course.addOffering(offerings.get(i - 1));
				courses.add(course);
				course = new Course();
			}
		}
		
		Subject subject = new Subject();
		for (int i = 1; i < courses.size(); ++i) {
			if (courses.get(i - 1).getSubjectName().equals(courses.get(i).getSubjectName())) {
				subject.addCourse(courses.get(i - 1));
				if (i == courses.size() - 1) {
					subject.addCourse(courses.get(i));
					subjects.add(subject);
				}
			} else {
				subject.addCourse(courses.get(i - 1));
				subjects.add(subject);
				subject = new Subject();
			}
		}
		
		for (Course c : courses) {
			c.compileOfferingsPerSemester();
		}
	}
	
	public ArrayList<Offering> getOfferings() {
		return offerings;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
}
