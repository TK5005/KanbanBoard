package kanban.controller;

import java.io.File;

import javax.swing.JFileChooser;

import kanban.model.KanbanModel;
import kanban.service.BoardFileService;
import kanban.service.BoardTestService;
import kanban.service.IBoardService;
import kanban.vo.KanbanBoard;
import kanban.vo.Task;

/**
 * This is the controller of the application. It acts as the ActionListener for
 * all view components and modifies the model of the application.
 *
 */
public class KanbanController implements IKanbanController {
	
	private KanbanModel model;
	private IBoardService boardService;
	
	/**
	 * Constructor
	 * @param model The model for the controller to manipulate
	 */
	public KanbanController(KanbanModel model){
		this.model = model;
		boardService = new BoardFileService();
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
	public void exitApplication(){
		System.exit(0);
	}
	
	@Override
	public void saveKanbanBoard(){
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			boardService.saveBoard(file, model.getKanbanBoard());
		}
	}
	
	@Override
	public void createNewKanbanBoard() {
		KanbanBoard board = new KanbanBoard();
		model.setKanbanBoard(board);
	}
	
	@Override
	public void createNewCategory() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void createNewTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openKanbanBoard() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            KanbanBoard board = boardService.loadBoard(file);
    		model.setKanbanBoard(board);
        }
	}

	@Override
	public void showAboutInformation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCategories() {
		// TODO Auto-generated method stub
		
	}
}