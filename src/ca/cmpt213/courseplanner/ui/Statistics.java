package ca.cmpt213.courseplanner.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.*;


public class Statistics extends ABCPanel {
	String[] semesters;
	String[] locations;
	
	public Statistics(FileReader fileReader, String title) {
		super(fileReader, title);
		semesters = new String[3];
		semesters[0] = "Spring";
		semesters[1] = "Summer";
		semesters[2] = "Fall";
		
		locations = new String[4];
		
		locations[0] = "Burnaby";
		locations[1] = "Surrey";
		locations[2] = "Van";
		locations[3] = "Other";
			
		super.getContentPanel().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		
		super.getContentPanel().add(makeLabel("Course: "));
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		
		super.getContentPanel().add(makeLabel("Semester Offerings:"), c);
		
		int[] nullarray = new int[1];
		String[] nullstringarray = new String[1];
		BarGraphModel semesterGraph = new BarGraphModel(nullarray, nullstringarray);
		BarGraphIcon icon1 = new BarGraphIcon(semesterGraph, 300, 300);
		JLabel label1 = new JLabel(icon1); 
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		super.getContentPanel().add(label1, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		
		super.getContentPanel().add(makeLabel("Campus Offerings: "), c);
		
		BarGraphModel campusGraph = new BarGraphModel(nullarray, nullstringarray);
		BarGraphIcon icon2 = new BarGraphIcon(campusGraph, 300, 300);
		JLabel label2 = new JLabel(icon2); 
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 4;
		super.getContentPanel().add(label2, c);
	}

	public void makeSemesterGraph(Course course) {
		super.getContentPanel().removeAll();
		super.getContentPanel().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		
		super.getContentPanel().add(makeLabel("Course: " + course.getCourseName()), c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		
		super.getContentPanel().add(makeLabel("Semester Offerings:"), c);
		
		int[] semesterData = course.getSemesterGraphData();
		BarGraphModel semesterGraph = new BarGraphModel(semesterData, semesters);
		BarGraphIcon icon1 = new BarGraphIcon(semesterGraph, 300, 300);
		JLabel label1 = new JLabel(icon1); 
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		super.getContentPanel().add(label1, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		
		super.getContentPanel().add(makeLabel("Campus Offerings: "), c);
		
		int[] campusData = course.getCampusGraphData();
		BarGraphModel campusGraph = new BarGraphModel(campusData, locations);
		BarGraphIcon icon2 = new BarGraphIcon(campusGraph, 300, 300);
		JLabel label2 = new JLabel(icon2); 
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 4;
		super.getContentPanel().add(label2, c);
		
		
		super.getContentPanel().revalidate();
	}
}
