package kanban.service;

import kanban.vo.KanbanBoard;

/**
 * This interface is used to save and load data
 * to a variety of formats.
 *
 */
public interface IBoardService {
	KanbanBoard loadBoard(String path);
	void saveBoard(KanbanBoard board);
}
