package kanban.vo;

import java.util.ArrayList;

public class KanbanBoard {
	
	ArrayList<Category> categories;
	ArrayList<Task> tasks;
	String owner;
	
	public KanbanBoard(){
		categories = new ArrayList<Category>();
		tasks = new ArrayList<Task>();
	}
}