package readernotes.src.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import readernotes.src.core.Book;

public class NewBookForm extends JFrame {
	private Book _newBook;
	private String _title;
	private String _author;
	private String _sinopse;
	
	public void run() {
		initUI();
		this.setVisible(true);
	}

	private void initUI() {
		JPanel panel = new JPanel();
		this.createLayout(panel);
		this.add(panel);
		this.setTitle("New Book");
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void createLayout(JPanel panel) {
		Container pane = getContentPane();
		JButton saveButton = this.createSaveButton();
		JTextArea sinopseArea = this.createTextArea();
		JLabel sinopseLabel = this.createNewLabel("Sinopse");
		JLabel authorLabel = this.createNewLabel("Author");
		JLabel titleLabel = this.createNewLabel("Title");
		pane.add(saveButton);
		
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
			}
		});
		saveButton.setBounds(300,150,70,30);
		return saveButton;
	}

	private JTextArea createTextArea() {
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return textArea;
	}

	private JLabel createNewLabel(String name) {
		JLabel newLabel = new JLabel(name);
		newLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return newLabel;
	}
}		
