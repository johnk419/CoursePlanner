package ca.cmpt213.courseplanner.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.FileReader;

public class CourseList extends ABCPanel implements ListSelectionListener {
	private JPanel contentPanel;
	private JList list;
	private JPanel listPanel;
	private JFrame frame;
	private CourseOfferings courseOfferingPanel;
	private Statistics statisticsPanel;
	private ArrayList<String> courseList;
	private ArrayList<Course> courses;
	
	
	public CourseList(FileReader fileReader, String title, JFrame frame, CourseOfferings courseOfferingPanel, Statistics statisticsPanel) {
		super(fileReader, title);
		contentPanel = super.getContentPanel();
		this.frame = frame;
		courseList = new ArrayList<String>();
		courses = new ArrayList<Course>();
		this.courseOfferingPanel = courseOfferingPanel;
		this.statisticsPanel = statisticsPanel;
	   
		contentPanel.setLayout(new BorderLayout());
	    contentPanel.add(setupJList(), BorderLayout.WEST);
		
		contentPanel.setPreferredSize(new Dimension(300, 500));
	
	}
	
	public Component setupJList() {
		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(this);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(300, 500));
	    listScroller.setAlignmentX(LEFT_ALIGNMENT);
	    
	    listPanel.add(listScroller, BorderLayout.EAST);
	    return listPanel;
	}
	
	public void updateJList(ArrayList<Course> courses, boolean undergrad, boolean grad) {
		this.courses = courses;
		courseList.clear();
		for (Course c : courses) {
			if (undergrad == true && grad == true) {
				courseList.add(c.getCourseName());
			} else if (undergrad == false && grad == true && c.isGrad()) {
				courseList.add(c.getCourseName());
			} else if (undergrad == true && grad == false && c.isGrad() == false) {
				courseList.add(c.getCourseName());
			}
		}
		
		String[] coursesString = new String[courseList.size()];
		for (int i = 0; i < courseList.size(); ++i) {
			coursesString[i] = courseList.get(i);
		}

		list.setListData(coursesString);
		
		listPanel.removeAll();
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(300, 500));
	    listScroller.setAlignmentX(LEFT_ALIGNMENT);
	   
	    listPanel.add(listScroller);

		frame.revalidate();	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			String courseSelected = courseList.get(list.getSelectedIndex());
			
			for (Course c : courses) {
				if (c.getCourseName().equals(courseSelected)) {
					courseOfferingPanel.makeSemesterGrid(c);
					statisticsPanel.makeSemesterGraph(c);
				}
			}
		}
		
	}

}
