package kanban.model;

import java.util.ArrayList;

import javax.swing.ListModel;

import kanban.controller.IKanbanModelObserver;
import kanban.vo.Category;
import kanban.vo.KanbanBoard;
import kanban.vo.Task;

/**
 * The KananbanModel holds all of the data in the application as
 * well as exposes methods to add and remove items from the KanbanBoard.
 * 
 */
public class KanbanModel {
	
	private static KanbanModel instance = null;
	
	private KanbanBoard kanbanBoard;
	
	private ArrayList<IKanbanModelObserver> observers;
	
	public void setKanbanBoard(KanbanBoard board){
		kanbanBoard = board;
		notifyObservers();
	}
	
	/**
	 * Gets the KanbanModel's KanbanBoard object
	 * @return The KanbanModel's KanbanBoard object
	 */
	public KanbanBoard getKanbanBoard(){
		return kanbanBoard;
	}
	
	public String getKanbanBoardString(){
		if(kanbanBoard != null){
			return kanbanBoard.toString();
		}
		return "";
	}
	
	public static KanbanModel getInstance() {
		if(instance == null){
			instance = new KanbanModel();
		}
		return instance;
	}
	/**
	 * Constructor
	 */
	protected KanbanModel(){
		kanbanBoard = new KanbanBoard();
		observers = new ArrayList<IKanbanModelObserver>();
	}
	
	/**
	 * Registers an IKanbanModelObserver to receive updates.
	 * @param observer The IKanbanModel observer to register.
	 */
	public void registerModelObserver(IKanbanModelObserver observer){
		for(IKanbanModelObserver o : observers){
			if(o.equals(observer)){
				return;
			}
		}
		observers.add(observer);
	}
	
	/**
	 * Removes an IKanbanModelObserver to no longer receive updates.
	 * @param observer The IKanbanModelObserver to remove.
	 */
	public void removeModelObserver(IKanbanModelObserver observer){
		for(IKanbanModelObserver o : observers){
			if(o.equals(observer)){
				observers.remove(o);
			}
		}
	}
	
	/**
	 * Notifies all IKanbanModel observers that the model has changed.
	 */
	private void notifyObservers(){
		for(IKanbanModelObserver observer : observers){
			observer.modelUpdated();
		}
	}
	
	/**
	 * Gets all task from a specific category
	 * @param category the category to get tasks from
	 * @return An array of Task objects
	 */
	public Task[] getTasksForCategory(Category category) {
		ArrayList<Task> categoryTasks = new ArrayList<Task>();
		Task[] tasks = kanbanBoard.getTaskArray();
		for(Task task : tasks){
			if(task.getCategory().equals(category)){
				categoryTasks.add(task);
			}
		}
		Task[] taskArray = new Task[categoryTasks.size()];
		taskArray = categoryTasks.toArray(taskArray);
		return taskArray;
	}
	
	/**
	 * Gets the number of tasks in a category
	 * @param category The category to get the task number for.
	 * @return The number of tasks in the category.
	 */
	public int categoryTaskNumber(Category category){
		Task[] tasks = kanbanBoard.getTaskArray();
		int taskNum = 0;
		for(Task task : tasks){
			if(task.getCategory().equals(category)){
				taskNum++;
			}
		}
		return taskNum;
	}
}