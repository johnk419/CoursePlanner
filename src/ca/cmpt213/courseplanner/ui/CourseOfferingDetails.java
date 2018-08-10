package ca.cmpt213.courseplanner.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.cmpt213.courseplanner.model.FileReader;
import ca.cmpt213.courseplanner.model.Offering;
import ca.cmpt213.courseplanner.model.SemesterOffering;

public class CourseOfferingDetails extends ABCPanel {
	private JTextArea textArea;
	private GridBagConstraints c;
	private JScrollPane scrollPane;
	
	public CourseOfferingDetails(FileReader fileReader, String title) {
		super(fileReader, title);
		textArea = new JTextArea(5, 20);
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		super.getContentPanel().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		super.getContentPanel().add(makeLabel("Course Name: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		super.getContentPanel().add(makeLabel("Semester: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		super.getContentPanel().add(makeLabel("Location: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		super.getContentPanel().add(makeLabel("Instructors: "), c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 4;
		c.gridx = 1;
		c.gridy = 0;
		super.getContentPanel().add(scrollPane, c);
		
	}
	
	public void updateCourseDetails(SemesterOffering offering) {
		super.getContentPanel().removeAll();
		super.getContentPanel().setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		super.getContentPanel().add(makeLabel("Course Name: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		super.getContentPanel().add(makeLabel("Semester: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		super.getContentPanel().add(makeLabel("Location: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		super.getContentPanel().add(makeLabel("Instructors: "), c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 4;
		c.gridx = 1;
		c.gridy = 0;
		
		super.getContentPanel().add(scrollPane, c);

		textArea.setText(offering.getCourseName() + "\n" + offering.getSemesterCode() + "\n" + 
					     offering.getLocation() + "\n" + offering.getInstructors());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		super.getContentPanel().add(makeLabel("Section Type: "), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		super.getContentPanel().add(makeLabel("Enrollment (filled/cap)"), c);
		
		ArrayList<Offering> offerings = offering.getSemesterOffering();
		
		for (Offering o : offerings) {
			System.out.println(o.getComponentCode());
		}
		
		for (int i = 0; i < offerings.size(); ++i) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = (i * 5) + 10;
			super.getContentPanel().add(makeLabel(offerings.get(i).getComponentCode()), c);
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.weighty = 0;
			c.gridx = 1;
			c.gridy = (i * 5) + 10;
			
			super.getContentPanel().add(makeLabel(offerings.get(i).getEnrollmentTotal() + "/" + offerings.get(i).getEnrollmentCapacity()), c);
		}
		
		super.getContentPanel().repaint();
		super.getContentPanel().revalidate();
	}
}
