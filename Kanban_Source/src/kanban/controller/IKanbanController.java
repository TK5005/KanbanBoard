package kanban.controller;

import java.io.File;

import kanban.vo.Task;

public interface IKanbanController {
	void exitApplication();
	void openKanbanBoard();
	void saveKanbanBoard();
	void createNewKanbanBoard();
	void createNewCategory();
	void createNewTask();
	void showAboutInformation();
	void editTask(Task task);
	void editCategories();
}