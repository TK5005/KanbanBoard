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

public class NewTaskDialog extends JDialog {
	private JTextField taskTitle;
	private JTextArea taskDescription;
	private JCheckBox taskCompleteCheckBox;
	private JComboBox taskCategoryBox;
	
	public NewTaskDialog(JFrame frame, String title, Boolean modal){
		super(frame, title, modal);
		createUI();
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
		contentPanel.add(taskTitle);
		taskTitle.setColumns(10);
		
		taskCategoryBox = new JComboBox(KanbanModel.getInstance().getCategories());
		taskCategoryBox.setBounds(93, 183, 212, 20);
		
		contentPanel.add(taskCategoryBox);
		
		taskCompleteCheckBox = new JCheckBox("Complete");
		taskCompleteCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		taskCompleteCheckBox.setBounds(10, 218, 424, 23);
		contentPanel.add(taskCompleteCheckBox);
		
		taskDescription = new JTextArea();
		taskDescription.setBounds(93, 39, 341, 133);
		contentPanel.add(taskDescription);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue_1);
		
		JButton addTaskButton = new JButton("Add New Task");
		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewTask();
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
	
	private void createNewTask(){
		Task task = new Task();
		task.setName(taskTitle.getText());
		task.setDescription(taskDescription.getText());
		task.setComplete(taskCompleteCheckBox.isSelected());
		task.setCategory((Category)taskCategoryBox.getSelectedItem());
		KanbanModel.getInstance().addTask(task);
		this.dispose();
	}
	
	private void cancelNewTask(){
		this.dispose();
	}
	
	private void createUI(){
	}
}
