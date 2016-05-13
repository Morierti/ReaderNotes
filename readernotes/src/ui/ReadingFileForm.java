package readernotes.src.ui;

import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Library;
import readernotes.src.exceptions.InexistentReadingFileException;
import readernotes.src.exceptions.EmptyTitleException;

public class ReadingFileForm extends JFrame {
	private ReadingFile _readingFile;
	private JTextArea _titleArea;
	private JTextArea _bookTitleArea;
	private JTextArea _contentArea;

	public ReadingFileForm(String title) {
		this.setReadingFile(title);
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

	private void setReadingFile(String title) {
		try {
			Library library = Library.getInstance();
			_readingFile = library.getReadingFile(title);
		} catch (InexistentReadingFileException exception) {
			System.err.print(exception.getMessage());
		}
	}

	private ReadingFile getReadingFile() {
		return _readingFile;
	}

	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return label;
	}

	private JTextArea createTitleArea() {
		JTextArea titleArea = new JTextArea();
		titleArea.setText(this.getReadingFile().getTitle());
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);
		titleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		titleArea.setBounds(90,15,300,20);
		return titleArea;
	}

	private JTextArea createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		bookTitleArea.setText(this.getReadingFile().getBookTitle());
		bookTitleArea.setLineWrap(true);
		bookTitleArea.setWrapStyleWord(true);
		bookTitleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		bookTitleArea.setBounds(90,55,300,20);
		return bookTitleArea;
	}

	private JTextArea createContentArea() {
		JTextArea contentArea = new JTextArea();
		contentArea.setText(this.getReadingFile().getContent());
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		contentArea.setBounds(10,120,380,320);
		return contentArea;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,450,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Some calls are for ReadingFileFrom class.
				try {
					ReadingFile readingFile = getReadingFile();
					if (getTitleArea().getText() != null) {
						readingFile.setTitle(getTitleArea().getText().trim());
					}
					if (getBookTitleArea().getText() != null) {
						readingFile.setBookTitle(getBookTitleArea().getText().trim());
					}
					if (getContentArea().getText() != null) {
						readingFile.setContent(getContentArea().getText().trim());
					}
				} catch (EmptyTitleException exception) {
					System.err.print(exception.getMessage());
				}
				dispose();
			}
		});
		return saveButton;
	}

	private void createLayout() {
		Container pane = this.getContentPane();
		JButton saveButton = this.createSaveButton();
		JLabel titleLabel = this.createLabel("Title");
		JLabel bookTitleLabel = this.createLabel("Book Title");
		JLabel contentLabel = this.createLabel("Content");

		titleLabel.setBounds(10,10,70,30);
		bookTitleLabel.setBounds(10,50,100,30);
		contentLabel.setBounds(10,90,70,30);

		this.setTitleArea(this.createTitleArea());
		this.setBookTitleArea(this.createBookTitleArea());
		this.setContentArea(this.createContentArea());

		pane.add(titleLabel);
		pane.add(bookTitleLabel);
		pane.add(contentLabel);
		pane.add(saveButton);
		pane.add(this.getTitleArea());
		pane.add(this.getBookTitleArea());
		pane.add(this.getContentArea());
	}

	private void initUI() {
		JPanel panel = new JPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("Reading File");
		this.setSize(400,510);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
