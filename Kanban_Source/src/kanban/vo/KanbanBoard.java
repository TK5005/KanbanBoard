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
	
	public KanbanBoard(){
		categories = new ArrayList<Category>();
		tasks = new ArrayList<Task>();
	}
}