package kanban.service;

import kanban.vo.KanbanBoard;

public interface IBoardService {
	
	KanbanBoard loadBoard(String path);
	void saveBoard(KanbanBoard board);
}
