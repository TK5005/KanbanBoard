package kanban.controller;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
		try{
			final JFileChooser fileChooser = new JFileChooser(new File("File to start in"));
		    fileChooser.setFileFilter(new FileFilter() {
		        @Override
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".kb");
		        }

		        @Override
		        public String getDescription() {
		            return "*.kb";
		        }
		    });
		int returnVal = fileChooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			String file_name = file.toString();
			if (!file_name.endsWith(".kb"))
			    file_name += ".kb";
			file = new File(file_name);
			boardService.saveBoard(file, model.getKanbanBoard());
		}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "There was an error saving your Kanban Board");
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
		try{
			final JFileChooser fileChooser = new JFileChooser(new File("File to start in"));
		    fileChooser.setFileFilter(new FileFilter() {
		        @Override
		        public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            }
		            final String name = f.getName();
		            return name.endsWith(".kb");
		        }

		        @Override
		        public String getDescription() {
		            return "*.kb";
		        }
		    });
			int returnVal = fileChooser.showOpenDialog(null);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            KanbanBoard board = boardService.loadBoard(file);
	    		model.setKanbanBoard(board);
	        }
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "There was an error opening your Kanban Board");
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