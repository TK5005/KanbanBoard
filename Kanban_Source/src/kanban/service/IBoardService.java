package kanban.service;

import java.io.File;

import kanban.vo.KanbanBoard;

/**
 * This interface is used to save and load data
 * to a variety of formats.
 *
 */
public interface IBoardService {
	KanbanBoard loadBoard(File file);
	void saveBoard(File file, KanbanBoard board);
}
