package kanban.vo;

import java.io.Serializable;

/**
 * This class holds all of the information
 * for a Kanban Task.
 *
 */
public class Task  implements Serializable {
	private String name;
	private String description;
	private Category category;
	private boolean isComplete;
	
	public void setComplete(boolean complete){
		isComplete = complete;
	}
	
	public boolean getComplete(){
		return isComplete;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String toString(){
		return "Task: " + name + ", Description: " + description + ", Category: " + category.getName();
	}
}