package ca.cmpt213.courseplanner.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import ca.cmpt213.courseplanner.model.*;


public class CourseOfferings extends ABCPanel implements ActionListener {
	JPanel gridPanel;
	GridBagConstraints c;
	JFrame frame;
	ArrayList<SemesterOffering> offeringsPerSemester;
	private Statistics statisticsPanel;
	private CourseOfferingDetails courseOfferingDetails;

	public CourseOfferings(FileReader fileReader, String title, JFrame frame, CourseOfferingDetails courseOfferingDetails) {
		super(fileReader, title);
		offeringsPerSemester = new ArrayList<SemesterOffering>();
		gridPanel = super.getContentPanel();
		gridPanel.setLayout(new GridBagLayout());
		this.frame = frame;
		this.statisticsPanel = statisticsPanel;
		this.courseOfferingDetails = courseOfferingDetails;
		//gridPanel.setPreferredSize(new Dimension(600, 700));
		c = new GridBagConstraints();
		
	}
	
	public void makeSemesterGrid(Course course) {
		offeringsPerSemester = course.getOfferingsPerSemester();
		ArrayList<Integer> semesterYears = course.getSemesterYears();
		
		gridPanel.removeAll();
		gridPanel.revalidate();
		gridPanel.repaint();
		frame.revalidate();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		gridPanel.add(makeLabel(""), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		gridPanel.add(makeLabel("Spring"), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0;
		gridPanel.add(makeLabel("Summer"), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 0;
		gridPanel.add(makeLabel("Fall"), c);
		
		for (int i = 0; i < semesterYears.size(); ++i) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.gridx = 0;
			c.gridy = i + 1;
			gridPanel.add(makeLabel(Integer.toString(semesterYears.get(i))), c);
		}
		
		
		ArrayList<ArrayList<SemesterOffering>> semesterOfferings = course.getOfferingsBySemester();
		ArrayList<ArrayList<String>> semesters = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i < semesterYears.size(); ++i) {
			ArrayList<String> string = new ArrayList<String>();
			for (int j = 0; j < 3; ++j) {
				if (j == 0) {
					string.add(semesterYears.get(i) + ", " + "Spring");
				} else if (j == 1) {
					string.add(semesterYears.get(i) + ", " + "Summer");
				} else if (j == 2) {
					string.add(semesterYears.get(i) + ", " + "Fall");
				}
			}
			semesters.add(string);
		}
		
		ArrayList<ArrayList<JPanel>> gridPanels = new ArrayList<ArrayList<JPanel>>();
		for (int i = 0; i < semesterYears.size(); ++i) {
			ArrayList<JPanel> panels = new ArrayList<JPanel>();
			for (int j = 0; j < 3; ++j) {
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
				panel.setBorder(BorderFactory.createBevelBorder(
						BevelBorder.LOWERED,
						Color.black, Color.gray));
				
				c.fill = GridBagConstraints.BOTH;
				c.weighty = 1;
				c.weightx = 6;
				c.gridx = j + 1;
				c.gridy = i + 1;
				panels.add(panel);
				gridPanel.add(panel, c);
			}
			gridPanels.add(panels);
		}
		
		for (int i = 0; i < semesterYears.size(); ++i) {
			for (int j = 0; j < 3; ++j) {
				for (int z = 0; z < offeringsPerSemester.size(); ++z) {
					if (offeringsPerSemester.get(z).getYearAndSemester().equals(semesters.get(i).get(j))) {
						JPanel panel = gridPanels.get(i).get(j);
						panel.add(makeButton(offeringsPerSemester.get(z)));
					}
				}
			}
		}
		
		gridPanel.revalidate();
		gridPanel.repaint();
		frame.revalidate();
	}
	
	public Component makeButton(SemesterOffering semesterOffering) {
		JButton button = new JButton(semesterOffering.getOfferingTitle());
		button.setActionCommand(semesterOffering.getSemesterCode() + ", " + semesterOffering.getOfferingTitle());
		button.addActionListener(this);
		
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String courseSelected = e.getActionCommand();
		
		for (SemesterOffering s : offeringsPerSemester) {
			String course = s.getSemesterCode() + ", " + s.getOfferingTitle();
			if (course.equals(courseSelected)) {
				courseOfferingDetails.updateCourseDetails(s);
			}
		}
	}
}
