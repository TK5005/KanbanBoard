package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class NewCategoryDialog extends JDialog {
	
	public NewCategoryDialog(JFrame frame, String title, Boolean modal){
		super(frame, title, modal);
		createUI();
		this.setResizable(false);
	}
	
	private void createUI(){
		JLabel label = new JLabel("New Category Box");
		this.add(label, BorderLayout.NORTH);
	}
}