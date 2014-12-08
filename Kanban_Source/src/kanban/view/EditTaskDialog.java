package kanban.view;

import java.awt.BorderLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import kanban.model.KanbanModel;
import kanban.vo.Category;
import kanban.vo.Task;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditTaskDialog extends JDialog {
	private JTextField taskTitle;
	private JTextArea taskDescription;
	private JCheckBox taskCompleteCheckBox;
	private JComboBox taskCategoryBox;
	private Task task;
	
	public EditTaskDialog(Task task, JFrame frame, String title, Boolean modal){
		super(frame, title, modal);
		this.task = task;
		createUI();
	}
	
	private void saveTask(){
		KanbanModel.getInstance().updateTask(task, taskTitle.getText(), taskDescription.getText(), taskCompleteCheckBox.isSelected(), (Category)taskCategoryBox.getSelectedItem());
		this.dispose();
	}
	
	private void cancelNewTask(){
		this.dispose();
	}
	
	private void createUI(){
		this.setResizable(false);
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Description:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 39, 73, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(10, 14, 73, 14);
		contentPanel.add(lblTitle);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(10, 186, 73, 14);
		contentPanel.add(lblCategory);
		
		taskTitle = new JTextField();
		taskTitle.setBounds(93, 11, 341, 20);
		taskTitle.setText(task.getName());
		contentPanel.add(taskTitle);
		taskTitle.setColumns(10);
		
		taskCategoryBox = new JComboBox(KanbanModel.getInstance().getCategories());
		taskCategoryBox.setSelectedItem(task.getCategory());
		taskCategoryBox.setBounds(93, 183, 212, 20);
		
		contentPanel.add(taskCategoryBox);
		
		taskCompleteCheckBox = new JCheckBox("Complete");
		taskCompleteCheckBox.setSelected(task.getComplete());
		taskCompleteCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		taskCompleteCheckBox.setBounds(10, 218, 424, 23);
		contentPanel.add(taskCompleteCheckBox);
		
		taskDescription = new JTextArea();
		taskDescription.setText(task.getDescription());
		taskDescription.setBounds(93, 39, 341, 133);
		contentPanel.add(taskDescription);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue_1);
		
		JButton addTaskButton = new JButton("Save Task");
		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveTask();
			}
		});
		buttonPanel.add(addTaskButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelNewTask();
			}
		});
		buttonPanel.add(cancelButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue);
	}
}
