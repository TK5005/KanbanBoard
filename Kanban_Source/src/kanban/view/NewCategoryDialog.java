package kanban.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kanban.model.KanbanModel;
import kanban.vo.Category;

public class NewCategoryDialog extends JDialog {
	private JTextField categoryTitleField;
	private JTextArea categoryDescriptionField;
	
	public NewCategoryDialog(JFrame frame, String title, Boolean modal){
		super(frame, title, modal);
		createUI();
		this.setResizable(false);
	}
	
	private void createUI(){
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue);
		
		JButton createButton = new JButton("Create Category");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategoryClicked();
			}
		});
		buttonPanel.add(createButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelNewCategory();
			}
		});
		buttonPanel.add(cancelButton);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue_1);
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel catTitle = new JLabel("Title:");
		catTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		catTitle.setBounds(20, 11, 46, 14);
		contentPanel.add(catTitle);
		
		categoryTitleField = new JTextField();
		categoryTitleField.setBounds(83, 11, 351, 20);
		contentPanel.add(categoryTitleField);
		categoryTitleField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Description:");
		lblNewLabel.setBounds(10, 47, 57, 14);
		contentPanel.add(lblNewLabel);
		
		categoryDescriptionField = new JTextArea();
		categoryDescriptionField.setBounds(83, 41, 351, 125);
		contentPanel.add(categoryDescriptionField);
	}
	
	private void addCategoryClicked(){
		String categoryTitle = categoryTitleField.getText();
		String categoryDescription = categoryDescriptionField.getText();
		Category category = new Category();
		category.setName(categoryTitle);
		category.setDescription(categoryDescription);
		System.out.println(category.getName() + ": " + category.getDescription());
		KanbanModel model = KanbanModel.getInstance();
		model.addCategory(category);
		this.dispose();
	}
	
	private void cancelNewCategory(){
		this.dispose();
	}
}