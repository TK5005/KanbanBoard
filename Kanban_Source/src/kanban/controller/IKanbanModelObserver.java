 package kanban.controller;

import kanban.model.KanbanModel;

/**
 * This interface requires the implementer
 * to have the modelUpdated method present. This method
 * is used to update the class when the model changes.
 * 
 */
public interface IKanbanModelObserver {
	void modelUpdated();
}