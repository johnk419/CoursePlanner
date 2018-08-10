package ca.cmpt213.courseplanner.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import ca.cmpt213.courseplanner.model.FileReader;

public class CourseListFilter extends ABCPanel implements ActionListener {
	private JComboBox<String> listBox;
	private CourseList courseListPanel;
	private ArrayList<Subject> subjects;
	JButton button;
	
	private boolean undergrad;
	private boolean grad;
	
	private String subjectSelection;
	
	public CourseListFilter(FileReader fileReader, String title, CourseList courseListPanel) {
		super(fileReader, title);
		
		this.courseListPanel = courseListPanel;
		JPanel contentPanel = super.getContentPanel();
		button = new JButton("Update Course List");
		undergrad = true;
		grad = true;
		
		subjects = fileReader.getSubjects();
		Vector<String> options = new Vector<String>();
		for (Subject s : subjects) {
			options.add(s.getSubjectName());
		}

		listBox = new JComboBox<String>(options);
		
		contentPanel.setPreferredSize(new Dimension(300, 125));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		contentPanel.add(setupComboBox());
		
		contentPanel.add(setupCheckBoxes());
		contentPanel.add(setupUpdateListButton());
	}
	
	public Component setupComboBox() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		listBox.setPreferredSize(new Dimension(150, 30));
		listBox.addActionListener(this);
		panel.add(makeLabel("Department"));
		panel.add(listBox);
		
		return panel;
	}
	
	
	public Component setupCheckBoxes() {
		JCheckBox underGradCheck = new JCheckBox("Include undergrad courses");
		underGradCheck.setMnemonic(KeyEvent.VK_C); 
		underGradCheck.setActionCommand("undergradCheck");
		underGradCheck.setSelected(true);
		
		JCheckBox gradCheck = new JCheckBox("Include grad courses");
		gradCheck.setMnemonic(KeyEvent.VK_G); 
		gradCheck.setActionCommand("gradCheck");
		gradCheck.setSelected(true);
		
		underGradCheck.addActionListener(this);
		gradCheck.addActionListener(this);
		
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(underGradCheck);
        checkPanel.add(gradCheck);

		return checkPanel;
	}
	
	public Component setupUpdateListButton() {		
		button.setMnemonic(KeyEvent.VK_D);
		button.setActionCommand("update");
		button.addActionListener(this);
		
		return button;
	}
	
	public boolean includeUnderGrad() {
		return undergrad;
	}

	public boolean includeGrad() {
		return grad;
	}
	
	public String getSubjectSelection() {
		return subjectSelection;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		subjectSelection = (String) listBox.getSelectedItem();	
		if ("update".equals(e.getActionCommand())) {
			for (Subject s : subjects) {
				if (s.getSubjectName() == subjectSelection) {
					ArrayList<Course> courses = s.getCourses();
					courseListPanel.updateJList(courses, undergrad, grad);
				}
			}
		}
		
		if ("undergradCheck".equals(e.getActionCommand())) {
			if (undergrad == false) {
				undergrad = true;
			} else {
				undergrad = false;
			}
		}
		
		if ("gradCheck".equals(e.getActionCommand())) {
			if (grad == false) {
				grad = true;
			} else {
				grad = false;
			}
		}		
	}
}
