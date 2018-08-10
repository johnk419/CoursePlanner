package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import ca.cmpt213.courseplanner.model.FileReader;

public class AppLayout {
	public static void main(String[] args) {
		new AppLayout();
	}
	
	public AppLayout() {
		JFrame frame = new JFrame("FAS Course Planner");
		frame.setPreferredSize(new Dimension(1600, 1000));
		frame.setLayout(new BorderLayout());
	
		
		FileReader fileReader = new FileReader();
		fileReader.compileCourses();
		
		CourseOfferingDetails courseOfferingDetails = new CourseOfferingDetails(fileReader, "Details of Course Offering");
		Statistics statisticsPanel = new Statistics(fileReader, "Statistics");	
		CourseOfferings courseOffering = new CourseOfferings(fileReader, "Course Offering by Semester", frame, courseOfferingDetails);
		CourseList courseList = new CourseList(fileReader, "Course List", frame, courseOffering, statisticsPanel);	
		CourseListFilter courseListFilter = new CourseListFilter(fileReader, "Course List Filter", courseList);

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		westPanel.add(courseListFilter.getPanel(), c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		westPanel.add(courseList.getPanel(), c);
		
		frame.add(courseOffering.getPanel(), BorderLayout.CENTER);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		
		d.fill = GridBagConstraints.VERTICAL;
		d.weightx = 1;
		d.weighty = 1;
		d.gridx = 0;
		d.gridy = 0;
		eastPanel.add(statisticsPanel.getPanel(), d);
		
		d.fill = GridBagConstraints.VERTICAL;
		d.weightx = 0;
		d.weighty = 0;
		d.gridx = 0;
		d.gridy = 1;
		eastPanel.add(courseOfferingDetails.getPanel(), d);
		
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	private Component makeComponent(String title, int width, int height) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(makeLabel(title), BorderLayout.NORTH);
		panel.add(makeBox(height, width), BorderLayout.CENTER);
		
		return panel;
	}
	
	private Component makeLabel(String title) {
		JLabel label = new JLabel(title);
		return label;
	}
	
	private Component makeBox(int width, int height) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setBorder(BorderFactory.createBevelBorder(
				BevelBorder.LOWERED,
				Color.black, Color.gray));
		
		return panel;
	}
	
	private Component makeTextBox(String title) {
		JTextField textBox = new JTextField(title);
		textBox.setPreferredSize(new Dimension(200, 200));
		textBox.setText("Hello world...");
		return textBox;
	}
}
