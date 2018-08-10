package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public abstract class ABCPanel extends JPanel {
	private FileReader fileReader;
	private JPanel contentPanel;
	private JPanel panel;
	
	public ABCPanel(FileReader fileReader, String title) {
		this.fileReader = fileReader;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel(title);
		
		panel.add(label, BorderLayout.NORTH);
		
		contentPanel = new JPanel();
		setResizing(contentPanel);
		setResizing(label);
		contentPanel.setBorder(BorderFactory.createBevelBorder(
				BevelBorder.LOWERED,
				Color.black, Color.gray));
	
		panel.add(contentPanel, BorderLayout.CENTER);
	}
	
	public FileReader getFileReader() {
		return this.fileReader;
	}
	
	public void setResizing(Component item) {
		Dimension prefSize = item.getPreferredSize();
		Dimension newSize = new Dimension(Integer.MAX_VALUE,(int) prefSize.getHeight());
		item.setMaximumSize(newSize);
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public Component makeLabel(String title) {
		JLabel label = new JLabel(title);
		return label;
	}
	
	public JPanel getPanel() {
		return panel;
	}
}

