package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import kanban.model.KanbanModel;
import kanban.vo.Category;
import kanban.vo.Task;

import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;
import javax.swing.Box;

import java.awt.Component;

import javax.swing.JScrollPane;
import java.awt.Dimension;

public class CategoryView extends JPanel {
	private JLabel categoryTitle;
	private Category category;
	private KanbanModel model;
	private JPanel taskPanel;
	
	public void setCategory(Category category){
		this.category = category;
		update();
	}
	
	public CategoryView(Category category){
		model = KanbanModel.getInstance();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		categoryTitle = new JLabel();
		add(categoryTitle);
		taskPanel = new JPanel();
		taskPanel.setMaximumSize(new Dimension(250, 200));
		JScrollPane scrollPane = new JScrollPane(taskPanel);
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		add(scrollPane);
		setCategory(category);
	}
	
	private void update(){
		categoryTitle.setText(category.getName());
		taskPanel.removeAll();
		Task[] tasks = model.getTasksForCategory(category);
		for(Task task : tasks){
			System.out.println("Creating task: " + task.toString());
			TaskView taskView = new TaskView(task);
			taskPanel.add(taskView);
		}
	}
}