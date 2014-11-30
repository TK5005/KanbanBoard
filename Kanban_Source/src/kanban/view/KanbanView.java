package kanban.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import kanban.controller.IKanbanController;
import kanban.controller.IKanbanModelObserver;
import kanban.controller.KanbanController;
import kanban.model.KanbanModel;
import kanban.vo.Category;
import kanban.vo.Task;
import javax.swing.BoxLayout;

/**
 * This the the main KanabanView class. It can contain other custom views and windows.
 * 
 */
public class KanbanView extends JFrame implements IKanbanModelObserver, ActionListener {
	
	private int DEFAULT_WIDTH = 1024;
	private int DEFAULT_HEIGHT = 768;
	
	/**
	 * Controller for view. Will act as the ActionListener for interactive components.
	 * Will also be used by the view to register with the model for updates.
	 */
	private IKanbanController controller;
	private JMenuItem exitMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem newCategoryItem;
	private JMenuItem newTaskItem;
	private JMenuItem newBoardItem;
	private JMenuItem openMenuItem;
	private JList<Task> taskList;
	private JPanel categoryPanel;
	private JScrollPane categoryScrollPane;
	
	/**
	 * Constructor
	 * @param controller The controller acting as the views ActionListener
	 */
	public KanbanView(KanbanController controller){
		this.controller = controller;
		controller.registerModelObserver(this);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("Kanban Board");
		createUI();
	}
	
	/**
	 * Create the Kanban UI
	 */
	private void createUI(){
		this.setJMenuBar(createMenuBar());
		categoryPanel = new JPanel();
		categoryScrollPane = new JScrollPane(categoryPanel);
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.X_AXIS));
		getContentPane().add(categoryScrollPane, BorderLayout.CENTER);
	}
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createHelpMenu());
		return menuBar;
	}
	
	private JMenu createHelpMenu(){
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About Kanban Board");
		helpMenu.add(aboutMenuItem);
		return helpMenu;
	}
	
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		JMenu newMenu = new JMenu("New");
		newCategoryItem = new JMenuItem("Category");
		newCategoryItem.addActionListener(this);
		newTaskItem = new JMenuItem("Task");
		newTaskItem.addActionListener(this);
		newBoardItem = new JMenuItem("Kanban Board");
		newBoardItem.addActionListener(this); 
		newMenu.add(newCategoryItem);
		newMenu.add(newTaskItem);
		newMenu.add(newBoardItem);
		fileMenu.add(newMenu);
		openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		openMenuItem.addActionListener(this);
		fileMenu.add(openMenuItem);
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.addActionListener(this);
		fileMenu.add(exitMenuItem);
		return fileMenu;
	}
	
	private JMenu createEditMenu(){
		JMenu editMenu = new JMenu("Edit");
		JMenuItem categoryItem = new JMenuItem("Categories");
		editMenu.add(categoryItem);
		return editMenu;
	}
	
	@Override
	public void modelUpdated() {
		KanbanModel model = KanbanModel.getInstance();
		drawCategories(model.getKanbanBoard().getCategoryArray());
	}
	
	private void drawCategories(Category[] categories){
		KanbanModel model = KanbanModel.getInstance();
		categoryPanel.removeAll();
		for(Category category : categories){
			int taskNum = model.categoryTaskNumber(category);
			if( taskNum > 0){
				System.out.println(category.getName() + " has " + taskNum + " Tasks");
				CategoryView catView = new CategoryView(category);
				catView.revalidate();
				categoryPanel.add(catView);
			}
		}
		categoryPanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(exitMenuItem)){
			controller.exitApplication();
		}
		if(arg0.getSource().equals(saveMenuItem)){
			controller.saveKanbanBoard();
		}
		if(arg0.getSource().equals(newBoardItem)){
			controller.createNewKanbanBoard();
		}
		if(arg0.getSource().equals(newCategoryItem)){
			openNewCategoryWindow();
		}
		if(arg0.getSource().equals(newTaskItem)){
			openNewTaskWindow();
		}
		if(arg0.getSource().equals(openMenuItem)){
			controller.openKanbanBoard();
		}
	}
	
	public void openNewTaskWindow(){
		NewTaskDialog newTaskDialog = new NewTaskDialog(this, "New Task", true);
		newTaskDialog.setSize(500, 400);
		newTaskDialog.setVisible(true);
	}
	
	private void openNewCategoryWindow(){
		NewCategoryDialog newCatDialog = new NewCategoryDialog(this, "New Category", true);
		newCatDialog.setSize(500, 400);
		newCatDialog.setVisible(true);		
	}
}
