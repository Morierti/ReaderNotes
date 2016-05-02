package readernotes.src.ui;

import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import readernotes.src.core.Book;
import readernotes.src.core.Library;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

public class NewBookForm extends JFrame {
	private JTextArea _titleArea;
	private JTextArea _authorArea;
	private JTextArea _sinopseArea;

	public NewBookForm() {
		this.initUI();
		this.setVisible(true);
	}

	private void setTitleArea(JTextArea titleArea) {
		_titleArea = titleArea;
	}

	private JTextArea getTitleArea() {
		return _titleArea;
	}

	private void setAuthorArea(JTextArea authorArea) {
		_authorArea = authorArea;
	}

	private JTextArea getAuthorArea() {
		return _authorArea;
	}

	private void setSinopseArea(JTextArea sinopseArea) {
		_sinopseArea = sinopseArea;
	}

	private JTextArea getSinopseArea() {
		return _sinopseArea;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {				
				try { 
					Library library = Library.getInstance();
					Book newBook = new Book(getTitleArea().getText().trim(),
											getAuthorArea().getText().trim(),
											getSinopseArea().getText().trim());
					library.addBook(newBook);
				} catch (DoubleEntryException 
						| EmptyTitleException 
						| EmptyAuthorException exception) {
					System.err.print(exception.getMessage());
				}
				dispose();
			}
		});
		saveButton.setBounds(320,170,70,30);
		return saveButton;
	}

	private JTextArea createTitleArea() {
		JTextArea titleArea = new JTextArea();
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);
		titleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		titleArea.setBounds(90,15,300,20);
		return titleArea;
	}

	private JTextArea createAuthorArea() {
		JTextArea authorArea = new JTextArea();
		authorArea.setLineWrap(true);
		authorArea.setWrapStyleWord(true);
		authorArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		authorArea.setBounds(90,55,300,20);
		return authorArea;
	}

	private JTextArea createSinopseArea() {
		JTextArea sinopseArea = new JTextArea();
		sinopseArea.setLineWrap(true);
		sinopseArea.setWrapStyleWord(true);
		sinopseArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		sinopseArea.setBounds(90,90,300,65);
		return sinopseArea;
	}

	private JLabel createNewLabel(String name) {
		JLabel newLabel = new JLabel(name);
		newLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return newLabel;
	}

	private void createLayout() {
		Container pane = this.getContentPane();
		JButton saveButton = this.createSaveButton();
		JLabel sinopseLabel = this.createNewLabel("Sinopse");
		JLabel authorLabel = this.createNewLabel("Author");
		JLabel titleLabel = this.createNewLabel("Title");

		this.setTitleArea(this.createTitleArea());
		this.setAuthorArea(this.createAuthorArea());
		this.setSinopseArea(this.createSinopseArea());
		
		sinopseLabel.setBounds(10,90,70,30);
		authorLabel.setBounds(10,50,70,30);
		titleLabel.setBounds(10,10,70,30);

		pane.add(titleLabel);
		pane.add(authorLabel);
		pane.add(sinopseLabel);
		pane.add(saveButton);
		pane.add(this.getTitleArea());
		pane.add(this.getAuthorArea());
		pane.add(this.getSinopseArea());
		
	}

	private void initUI() {
		JPanel panel = new JPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("New Book");
		this.setSize(400,210);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}		
