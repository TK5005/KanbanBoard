package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kanban.vo.Task;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskView extends JPanel {
	
	private Task task;
	private JTextPane taskDescription;
	private JLabel taskName;
	private JCheckBox completeCheckBox;
	
	public void setTask(Task task){
		this.task = task;
		update();
	}
	
	public TaskView(Task task){
		setPreferredSize(new Dimension(300, 200));
		setSize(new Dimension(400, 200));
		setLayout(new BorderLayout(0, 0));
		
		taskName = new JLabel();
		taskName.setHorizontalAlignment(SwingConstants.CENTER);
		add(taskName, BorderLayout.NORTH);
		
		completeCheckBox = new JCheckBox("Complete");
		completeCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handleCompleteCheckbox();
			}
		});
		completeCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		add(completeCheckBox, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		
		taskDescription = new JTextPane();
		
		taskDescription.setBackground(UIManager.getColor("Button.background"));
		panel.add(taskDescription);
		setTask(task);
	}
	
	/**
	 * Called when the user checks or unchecks the complete
	 * checkbox
	 */
	private void handleCompleteCheckbox(){
		task.setComplete(completeCheckBox.isSelected());
	}
	
	private void update(){
		taskName.setText(task.getName());
		taskDescription.setText(task.getDescription());
		completeCheckBox.setSelected(task.getComplete());
	}
}