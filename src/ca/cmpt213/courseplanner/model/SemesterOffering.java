package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

public class SemesterOffering {
	private ArrayList<Offering> semesterOfferings;
	
	public SemesterOffering() {
		semesterOfferings = new ArrayList<Offering>();
	}
	
	public String getSemester() {
		return semesterOfferings.get(0).getSemesterSeason();
	}
	
	public String getLocation() {
		return semesterOfferings.get(0).getLocation();
	}
	
	public String getYearAndSemester() {
		return semesterOfferings.get(0).getSemesterYear() + ", " + semesterOfferings.get(0).getSemesterSeason();
	}
	
	public String getSemesterCode() {
		return semesterOfferings.get(0).getSemester();
	}
	
	public ArrayList<Offering> getSemesterOffering() {
		return semesterOfferings;
	}
	
	public int getSemesterYear() {
		return semesterOfferings.get(0).getSemesterYear();
	}

	public void addOffering(Offering offering) {
		this.semesterOfferings.add(offering);
	}
	
	public String getOfferingTitle() {
		return semesterOfferings.get(0).getCourseName() + "-" + semesterOfferings.get(0).getLocation();
	}
	
	public String getCourseName() {
		return semesterOfferings.get(0).getCourseName();
	}
	
	public String getInstructors() {
		ArrayList<String> instructors = new ArrayList<String>();
		instructors.add(semesterOfferings.get(0).getInstructors());
		String stringOfInstructors = "";
		
		for (Offering offering : semesterOfferings) {
			if (instructors.contains(offering.getInstructors()) == false) {
				instructors.add(offering.getInstructors());
			}
		}
		
		for (String s : instructors) {
			if (instructors.size() > 1) {
				stringOfInstructors = stringOfInstructors + s + ", ";
			}
		}
		
		
		return stringOfInstructors;
	}
	
	
	public void compileOfferings() {
		//System.out.println("    " + semesterOfferings.get(0).getSemester() + " in " + semesterOfferings.get(0).getLocation() + " by " + getInstructors());
		
		int enrollmentCapacity = 0;
		int enrollmentTotal = 0;
		
		int tempEnrollCap = 0;
		int tempEnrollTotal = 0;
		
		for (int i = semesterOfferings.size() - 1; i > 0; --i) {
			if (semesterOfferings.get(i).getComponentCode().equals(semesterOfferings.get(i - 1).getComponentCode())) {
				tempEnrollCap = semesterOfferings.get(i).getEnrollmentCapacity();
				tempEnrollTotal = semesterOfferings.get(i).getEnrollmentTotal();
				enrollmentCapacity += tempEnrollCap;
				enrollmentTotal += tempEnrollTotal;
				tempEnrollCap = 0;
				tempEnrollTotal = 0;
				if (i == 1) {
					tempEnrollCap = semesterOfferings.get(i - 1).getEnrollmentCapacity();
					tempEnrollTotal = semesterOfferings.get(i - 1).getEnrollmentTotal();
					enrollmentCapacity += tempEnrollCap;
					enrollmentTotal += tempEnrollTotal;
					tempEnrollCap = 0;
					tempEnrollTotal = 0;
					semesterOfferings.get(i - 1).setEnrollmentCapacity(enrollmentCapacity);		
					semesterOfferings.get(i - 1).setEnrollmentTotal(enrollmentTotal);	
				}
				semesterOfferings.remove(i);
			} else {
				tempEnrollCap = semesterOfferings.get(i).getEnrollmentCapacity();
				tempEnrollTotal = semesterOfferings.get(i).getEnrollmentTotal();
				enrollmentCapacity += tempEnrollCap;
				enrollmentTotal += tempEnrollTotal;
				semesterOfferings.get(i).setEnrollmentCapacity(enrollmentCapacity);
				semesterOfferings.get(i).setEnrollmentTotal(enrollmentTotal);
				tempEnrollCap = 0;
				tempEnrollTotal = 0;
				enrollmentCapacity = 0;
				enrollmentTotal = 0;
			}
		}	
	}
	
	public void printSemesterOfferings() {
		System.out.println("    " + semesterOfferings.get(0).getSemester() + " in " + semesterOfferings.get(0).getLocation() + " by " + getInstructors());
		for (Offering offering : semesterOfferings) {
			System.out.println("         Type=" + offering.getComponentCode() + ", " + "Enrollment=" + offering.getEnrollmentTotal() + "/" + offering.getEnrollmentCapacity());
		}
	}
}
