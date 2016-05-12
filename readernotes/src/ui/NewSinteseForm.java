package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import readernotes.src.core.Sintese;
import readernotes.src.core.Library;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.DoubleEntryException;

public class NewSinteseForm extends JFrame {
	private static NewSinteseForm _instance;
	private JTextArea _titleArea;
	private JTextArea _bookTitleArea;
	private JTextArea _contentArea;

	public NewSinteseForm() {
		this.initUI();
		this.setVisible(true);
	}

	private void setTitleArea(JTextArea titleArea) {
		_titleArea = titleArea;
	}

	private JTextArea getTitleArea() {
		return _titleArea;
	}

	private void setBookTitleArea(JTextArea bookTitleArea) {
		_bookTitleArea = bookTitleArea;
	}

	private JTextArea getBookTitleArea() {
		return _bookTitleArea;
	}

	private void setContentArea(JTextArea contentArea) {
		_contentArea = contentArea;
	}

	private JTextArea getContentArea() {
		return _contentArea;
	}

	private JTextArea createTitleArea() {
		JTextArea titleArea = new JTextArea();
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);
		titleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		titleArea.setBounds(90,15,300,20);
		return titleArea;
	}

	private JTextArea createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		bookTitleArea.setLineWrap(true);
		bookTitleArea.setWrapStyleWord(true);
		bookTitleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		bookTitleArea.setBounds(90,55,300,20);
		return bookTitleArea;
	}

	private JTextArea createContentArea() {
		JTextArea contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		contentArea.setBounds(10,120,380,320);
		return contentArea;
	}

	private JLabel createNewLabel(String labelValue) {
		JLabel label = new JLabel(labelValue);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return label;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,450,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Some are for NewSinteseForm class.
				try {
					Library library = Library.getInstance();
					library.addSintese(new Sintese(getTitleArea().getText().trim(),
												 getBookTitleArea().getText().trim(),
												 getContentArea().getText().trim()));
					dispose();
				} catch (EmptyTitleException
						 | DoubleEntryException exception) {
					System.err.print(exception.getMessage());
				}
			}
		});
		return saveButton;
	}


	private void createLayout() {
		Container pane = this.getContentPane();
		JButton saveButton = this.createSaveButton();
		JLabel titleLabel = this.createNewLabel("Title");
		JLabel bookTitleLabel = this.createNewLabel("Book Title");
		JLabel contentLabel = this.createNewLabel("Content:");

		this.setTitleArea(this.createTitleArea());
		this.setBookTitleArea(this.createBookTitleArea());
		this.setContentArea(this.createContentArea());

		titleLabel.setBounds(10,10,70,30);
		bookTitleLabel.setBounds(10,50,100,30);
		contentLabel.setBounds(10,90,70,30);

		pane.add(titleLabel);
		pane.add(bookTitleLabel);
		pane.add(contentLabel);
		pane.add(saveButton);
		pane.add(this.getTitleArea());
		pane.add(this.getBookTitleArea());
		pane.add(this.getContentArea());
	}

	public void initUI() {
		JPanel panel = new JPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("New Sintese");
		this.setSize(400,510);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
