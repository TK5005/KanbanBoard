package kanban.model;

import java.util.ArrayList;

import kanban.controller.IKanbanModelObserver;
import kanban.vo.KanbanBoard;

/**
 * The KananbanModel holds all of the data in the application as
 * well as exposes methods to add and remove items from the KanbanBoard.
 * 
 */
public class KanbanModel {
	
	private KanbanBoard kanbanBoard;
	
	private ArrayList<IKanbanModelObserver> observers;
	
	public void setKanbanBoard(KanbanBoard board){
		kanbanBoard = board;
		notifyObservers();
	}
	
	public String getKanbanBoardString(){
		if(kanbanBoard != null){
			return kanbanBoard.toString();
		}
		return "";
	}
	
	/**
	 * Constructor
	 */
	public KanbanModel(){
		kanbanBoard = new KanbanBoard();
		observers = new ArrayList<IKanbanModelObserver>();
	}
	
	/**
	 * Registers an IKanbanModelObserver to receive updates.
	 * @param observer The IKanbanModel observer to register.
	 */
	public void registerModelObserver(IKanbanModelObserver observer){
		for(IKanbanModelObserver o : observers){
			if(o.equals(observer)){
				return;
			}
		}
		observers.add(observer);
	}
	
	/**
	 * Removes an IKanbanModelObserver to no longer receive updates.
	 * @param observer The IKanbanModelObserver to remove.
	 */
	public void removeModelObserver(IKanbanModelObserver observer){
		for(IKanbanModelObserver o : observers){
			if(o.equals(observer)){
				observers.remove(o);
			}
		}
	}
	
	/**
	 * Notifies all IKanbanModel observers that the model has changed.
	 */
	private void notifyObservers(){
		for(IKanbanModelObserver observer : observers){
			observer.modelUpdated(this);
		}
	}
}