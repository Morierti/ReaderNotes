package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import readernotes.src.core.Sintese;
import readernotes.src.core.Library;
import readernotes.src.exceptions.InexistentSinteseException;

public class SinteseForm extends JFrame {
	private static SinteseForm _instance;
	private Sintese _sintese;
	private JTextArea _titleArea;
	private JTextArea _bookTitleArea;
	private JTextArea _contentArea;
	private String _newTitle;
	private String _newBookTitle;
	private String _newContent;
	private JPanel _panel;
	private boolean _uiGenerated;

	public static boolean isEmpty() {
		return _instance == null;
	}

	public static SinteseForm getInstance() {
		if (_instance.isEmpty()) {
			new SinteseForm();
		}
		return _instance;
	}

	private SinteseForm() {
		_instance = this;
	}

	private void setUIGenerated(boolean value) {
		_uiGenerated = value;
	}

	private boolean isUIGenerated() {
		return _uiGenerated;
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

	private void setNewTitle(String title) {
		_newTitle = title;
	}

	private String getNewTitle() {
		return _newTitle;
	}

	private void setNewBookTitle(String bookTitle) {
		_newBookTitle = bookTitle;
	}

	private String getNewBookTitle() {
		return _newBookTitle;
	}

	private void setNewContent(String content) {
		_newContent = content;
	}

	private String getNewContent() {
		return _newContent;
	}

	private void setSintese(String title) {
		try {
			Library library = Library.getInstance();
			_sintese = library.getSintese(title);
		} catch (InexistentSinteseException exception) {
			System.err.print(exception.getMessage());
		}
	}

	private Sintese getSintese() {
		return _sintese;
	}

	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);
		return label;
	}

	private JTextArea createTitleArea() {
		JTextArea titleArea = new JTextArea();
		return titleArea;
	}

	private JTextArea createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		return bookTitleArea;
	}

	private JTextArea createContentArea() {
		JTextArea contentArea = new JTextArea();
		return contentArea;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		return saveButton;
	}

	private void createLayout() {
		this.setTitleArea(this.createTitleArea());
		this.setBookTitleArea(this.createBookTitleArea());
		this.setContentArea(this.createContentArea());
	}

	private void initUI() {
		//Code
	}

	private void updateUI() {
		//Code
	}

	public void run() {
		//Code
	}

}
