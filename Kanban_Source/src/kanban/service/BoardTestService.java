package kanban.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Random;

import kanban.vo.Category;
import kanban.vo.KanbanBoard;
import kanban.vo.Task;

/**
 * This class loads test data in to the application. The save method
 * is not relevant for this test class.
 *
 */
public class BoardTestService implements IBoardService {

	@Override
	public KanbanBoard loadBoard(File file) {
		KanbanBoard board = new KanbanBoard();
		addCategories(board, 10);
		addTasks(board, 10);
		return board;
	}

	@Override
	public void saveBoard(File file, KanbanBoard board) {
		try{
			FileOutputStream fout = new FileOutputStream(file);
	        ObjectOutputStream oos = new ObjectOutputStream(fout);
	        oos.writeObject(board);
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	private void addCategories(KanbanBoard board, int numCategories){
		for(int i = 0; i < numCategories; i++){
			Category category = new Category();
			category.setName("Category " + (i+1));
			category.setDescription("This is the description for category " + (i+1));
			board.addCategory(category);
		}
	}
	
	private void addTasks(KanbanBoard board, int numTasks){
		for(int i = 0; i < numTasks; i++){
			Task task = new Task();
			task.setName("Task" + (i+1));
			task.setDescription("This is the description for task " + (i+1));
			task.setCategory(getRandomCategory(board));
			board.addTask(task);
		}
	}
	
	private Category getRandomCategory(KanbanBoard board){
		Category[] categories = board.getCategoryArray();
		Random rand = new Random();
		int randomIndex = rand.nextInt(((categories.length-1)) + 1);
		return categories[randomIndex];
	}
}