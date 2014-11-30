package kanban.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kanban.vo.KanbanBoard;

/**
 * This class loads and saves KanbanBoard objects to and from .kb files.
 *
 */
public class BoardFileService implements IBoardService {

	@Override
	public KanbanBoard loadBoard(File file) {
		KanbanBoard board = null;
		try {
			InputStream f = new FileInputStream(file);
			InputStream buffer = new BufferedInputStream(f);
			ObjectInput input = new ObjectInputStream (buffer);
			board = (KanbanBoard)input.readObject();
		} catch (Exception ex) {
			System.out.println(ex);
			//TODO: Add feedback to the user on file load failed
		}
		return board;
	}

	@Override
	public void saveBoard(File file, KanbanBoard board) {
		try{
			FileOutputStream fout = new FileOutputStream(file);
	        ObjectOutputStream oos = new ObjectOutputStream(fout);
	        oos.writeObject(board);
		}catch(Exception ex){
			System.out.println(ex);
			//TODO: Add feedback to the user on file save failed
		}
	}

}
