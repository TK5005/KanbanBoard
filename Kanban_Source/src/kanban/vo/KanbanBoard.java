package kanban.vo;

import java.util.ArrayList;

/**
 * This class holds all the information about a Kanban Board.
 * This is the main class that gets serialized when the file saves.
 */
public class KanbanBoard {
	
	ArrayList<Category> categories;
	ArrayList<Task> tasks;
	String owner;
	
	public void addCategory(Category category){
		for(Category cat : categories){
			if(cat.equals(category)){
				return;
			}
		}
		categories.add(category);
	}
	
	public Category[] getCategoryArray(){
		return categories.toArray(new Category[categories.size()]);
	}
	
	public Task[] getTaskArray(){
		return tasks.toArray(new Task[tasks.size()]);
	}
	
	public void addTask(Task task){
		for(Task t : tasks){
			if(t.equals(task)){
				return;
			}
		}
		tasks.add(task);
	}
	
	public void removeCategory(Category category){
		//TODO: Remove category
	}
	
	public void removeTask(Task task){
		//TODO: Remove Task
	}
	
	public KanbanBoard(){
		categories = new ArrayList<Category>();
		tasks = new ArrayList<Task>();
	}
	
	public String toString(){
		StringBuilder strBuilder = new StringBuilder("");
		strBuilder.append("KanbanBoard:\n");
		strBuilder.append("\tCategories:\n");
		for(Category cat : categories){
			strBuilder.append("\t" + cat.toString() + "\n");
		}
		strBuilder.append("\tTasks:\n");
		for(Task task : tasks){
			strBuilder.append("\t" + task.toString() + "\n");
		}
		return strBuilder.toString();
	}
}