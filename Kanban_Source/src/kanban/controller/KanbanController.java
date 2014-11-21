package kanban.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kanban.model.KanbanModel;
import kanban.service.BoardTestService;
import kanban.vo.KanbanBoard;

/**
 * This is the controller of the application. It acts as the ActionListener for
 * all view components and modifies the model of the application.
 *
 */
public class KanbanController implements ActionListener{
	
	private KanbanModel model;
	
	/**
	 * Constructor
	 * @param model The model for the controller to manipulate
	 */
	public KanbanController(KanbanModel model){
		this.model = model;
	}
	
	/**
	 * Registers an IKanbanModelObserver with the model to receive updates.
	 * @param observer The IKanbanModel observer to register.
	 */
	public void registerModelObserver(IKanbanModelObserver observer){
		model.registerModelObserver(observer);
	}
	
	/**
	 * Removes an IKanbanModelObserver from the model to no longer receive updates.
	 * @param observer The IKanbanModelObserver to remove.
	 */
	public void removeModelObserver(IKanbanModelObserver observer){
		model.removeModelObserver(observer);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		BoardTestService testService = new BoardTestService();
		KanbanBoard loadedBoard = testService.loadBoard("");
		model.setKanbanBoard(loadedBoard);
	}
}