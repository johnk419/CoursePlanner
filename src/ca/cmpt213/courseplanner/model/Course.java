package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;

public class Course {
	private ArrayList<Offering> offerings;
	private ArrayList<SemesterOffering> offeringsPerSemester;

	public Course() {
		offerings = new ArrayList<Offering>();
		offeringsPerSemester = new ArrayList<SemesterOffering>();
	}
	
	public ArrayList<Offering> getOfferings() {
		return offerings;
	}
	
	public ArrayList<SemesterOffering> getOfferingsPerSemester() {
		return offeringsPerSemester;
	}

	public void addOffering(Offering offering) {
		this.offerings.add(offering);
	}

	public ArrayList<ArrayList<SemesterOffering>> getOfferingsBySemester() {
		ArrayList<ArrayList<SemesterOffering>> semesterOfferings = new ArrayList<ArrayList<SemesterOffering>>();
		
		ArrayList<SemesterOffering> semesterOffering = new ArrayList<SemesterOffering>();
		for (int i = 1; i < offeringsPerSemester.size(); ++i) {
			if (offeringsPerSemester.get(i).getSemesterCode().equals(offeringsPerSemester.get(i-1).getSemesterCode())) {
				semesterOffering.add(offeringsPerSemester.get(i - 1));
			} else {
				semesterOffering.add(offeringsPerSemester.get(i));
				semesterOfferings.add(semesterOffering);
				semesterOffering = new ArrayList<SemesterOffering>();
			}
		}
		
		return semesterOfferings;
	}
	public String getSubjectName() {
		return offerings.get(0).getSubject(); 
	}
	
	public String getCourseName() {
		return offerings.get(0).getCourseName();
	}
	
	public ArrayList<Integer> getSemesterYears() {
		ArrayList<Integer> semesterYears = new ArrayList<Integer>();
		
		for (Offering o : offerings) {
			semesterYears.add(o.getSemesterYear());
		}
		
		for (int i = semesterYears.size() - 1; i > 0; --i) {
			if (semesterYears.get(i - 1).equals(semesterYears.get(i))) {
				//System.out.println(semesterYears.get(i));
				semesterYears.remove(i);
			}
		}
		return semesterYears;
	}
	
	public void compileOfferingsPerSemester() {	
		SemesterOffering semesterOfferings = new SemesterOffering();
		for (int i = 1; i < offerings.size(); ++i) {
			if (offerings.get(i - 1).getSemester().equals(offerings.get(i).getSemester()) && 
				offerings.get(i - 1).getLocation().equals(offerings.get(i).getLocation())) {
				semesterOfferings.addOffering(offerings.get(i - 1));
				if (i == offerings.size() - 1) {
					semesterOfferings.addOffering(offerings.get(i));
					semesterOfferings.compileOfferings();
					offeringsPerSemester.add(semesterOfferings);
				}
			} else {
				semesterOfferings.addOffering(offerings.get(i - 1));
				semesterOfferings.compileOfferings();
				offeringsPerSemester.add(semesterOfferings);
				semesterOfferings = new SemesterOffering();
			}
		}
	}
	
	public int[] getSemesterGraphData() {
		int spring = 0;
		int summer = 0;
		int fall = 0;
		
		for (SemesterOffering s : offeringsPerSemester) {
			if (s.getSemester().equals("Spring")) {
				spring++;
			} else if (s.getSemester().equals("Summer")) {
				summer++;
			} else if (s.getSemester().equals("Fall")) {
				fall++;
			}
		}
		
		int[] semesterData = new int[3];
		
		semesterData[0] = spring;
		semesterData[1] = summer;
		semesterData[2] = fall;
		
		return semesterData;
	}
	
	public int[] getCampusGraphData() {
		int surrey = 0;
		int burnaby = 0;
		int other = 0;
		int van = 0;
		
		for (SemesterOffering s : offeringsPerSemester) {
			if (s.getLocation().equals("BURNABY")) {
				burnaby++;
			} else if (s.getLocation().equals("SURREY")) {
				surrey++;
			} else if (s.getLocation().equals("HRBRCNTR")) {
				van++;
			} else {
				other++;
			}
		}
		
		int[] campusData = new int[4];
		campusData[0] = burnaby;
		campusData[1] = surrey;
		campusData[2] = van;
		campusData[3] = other;
		
		return campusData;
	}
	
	public boolean isGrad() {
		if (getCourseValue(offerings.get(0).getCourseCode()) > 500) {
			return true;
		}
		return false;
	}
	
	int getCourseValue(String str) {
		 return Integer.valueOf("0" + str.replaceAll("(\\d*).*", "$1"));
	}

	
	public void printCourseOfferings() {
		System.out.println(offerings.get(0).getCourseName());
		
		for (SemesterOffering semesterOfferings : offeringsPerSemester) {
			semesterOfferings.printSemesterOfferings();
		}
		System.out.print("\n");
	}
}
