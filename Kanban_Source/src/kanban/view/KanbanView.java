package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import kanban.controller.IKanbanModelObserver;
import kanban.controller.KanbanController;
import kanban.model.KanbanModel;

/**
 * This the the main KanabanView class. It can contain other custom views and windows.
 * 
 */
public class KanbanView extends JFrame implements IKanbanModelObserver {
	
	private int DEFAULT_WIDTH = 1024;
	private int DEFAULT_HEIGHT = 768;
	
	/**
	 * Controller for view. Will act as the ActionListener for interactive components.
	 * Will also be used by the view to register with the model for updates.
	 */
	private KanbanController controller;
	
	/**
	 * Constructor
	 * @param controller The controller acting as the views ActionListener
	 */
	public KanbanView(KanbanController controller){
		this.controller = controller;
		controller.registerModelObserver(this);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("Kanban Board");
		createUI();
	}
	
	/**
	 * Create the Kanban UI
	 */
	private void createUI(){
		JLabel label = new JLabel("Kanban View");
		this.add(label, BorderLayout.CENTER);
	}
	
	@Override
	public void modelUpdated(KanbanModel model) {
		// TODO Auto-generated method stub
	}
}
