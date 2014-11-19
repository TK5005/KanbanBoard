import javax.swing.JFrame;

import kanban.controller.KanbanController;
import kanban.model.KanbanModel;
import kanban.view.KanbanView;

public class KanbanMain {
	/**
	 * Main method to start application
	 * @param args
	 */
	public static void main(String[] args) {
		
		KanbanModel model = new KanbanModel();
		
		KanbanController controller = new KanbanController(model);
		
		KanbanView view = new KanbanView(controller);
		view.setVisible(true);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}