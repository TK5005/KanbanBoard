package kanban.view;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import kanban.model.KanbanModel;
import kanban.vo.Category;

public class EditCategoryDialog extends JDialog {
	
	private JTextField categoryTitleField;
	private JTextArea categoryDescriptionField;
	private JComboBox<Category> comboBox;
	
	private Category currentCategory;
	
	public EditCategoryDialog(JFrame frame, String title, Boolean modal) {
		super(frame, title, modal);
		createUI();
		currentCategory = (Category)comboBox.getSelectedItem();
		updateDisplay();
	}
	
	private void updateDisplay(){
		categoryTitleField.setText(currentCategory.getName());
		categoryDescriptionField.setText(currentCategory.getDescription());
	}
	
	private void saveButtonClicked(){
		KanbanModel.getInstance().updateCategory(currentCategory, categoryTitleField.getText(), categoryDescriptionField.getText());
	}
	
	private void closeButtonClicked(){
		this.dispose();
	}
	
	private void categoryChanged(){
		currentCategory = (Category)comboBox.getSelectedItem();
		updateDisplay();
	}
	
	private void createUI(){
		JPanel categorySelectionPanel = new JPanel();
		getContentPane().add(categorySelectionPanel, BorderLayout.NORTH);
		categorySelectionPanel.setLayout(new BoxLayout(categorySelectionPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		categorySelectionPanel.add(horizontalGlue_2);
		
		JLabel lblNewLabel = new JLabel("Category:");
		categorySelectionPanel.add(lblNewLabel);
		
		comboBox = new JComboBox<Category>(KanbanModel.getInstance().getCategories());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				categoryChanged();
			}
		});
		comboBox.setMaximumRowCount(10);
		categorySelectionPanel.add(comboBox);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		categorySelectionPanel.add(horizontalGlue_3);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue_1);
		
		JButton saveCatButton = new JButton("Save");
		saveCatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});
		buttonPanel.add(saveCatButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeButtonClicked();
			}
		});
		buttonPanel.add(closeButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		buttonPanel.add(horizontalGlue);
		
		JPanel categoryContentPanel = new JPanel();
		getContentPane().add(categoryContentPanel, BorderLayout.CENTER);
		categoryContentPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 11, 64, 14);
		categoryContentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 43, 68, 14);
		categoryContentPanel.add(lblNewLabel_2);
		
		categoryTitleField = new JTextField();
		categoryTitleField.setMaximumSize(new Dimension(2147483647, 20));
		categoryTitleField.setBounds(83, 11, 341, 20);
		categoryContentPanel.add(categoryTitleField);
		categoryTitleField.setColumns(10);
		
		categoryDescriptionField = new JTextArea();
		categoryDescriptionField.setBounds(83, 38, 341, 169);
		categoryContentPanel.add(categoryDescriptionField);
	}
}
