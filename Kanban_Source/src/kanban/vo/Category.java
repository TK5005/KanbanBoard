package kanban.vo;

import java.io.Serializable;

/**
 * This class holds all information about a Kanban Category.
 * 
 */
public class Category  implements Serializable {
	private String name;
	private String description;
	
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
	
	public String toString(){
		return "Category: " + name + ", Description: " + description;
	}
}