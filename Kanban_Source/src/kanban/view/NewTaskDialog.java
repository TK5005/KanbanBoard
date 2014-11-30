package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewTaskDialog extends JDialog {
	
	public NewTaskDialog(JFrame frame, String title, Boolean modal){
		super(frame, title, modal);
		createUI();
		this.setResizable(false);
	}
	
	private void createUI(){
		JLabel label = new JLabel("New Task Box");
		this.add(label, BorderLayout.NORTH);
	}
}
