package readernotes.src.ui;

import javax.swing.JFrame;
import readernotes.src.core.Library;
import readernotes.src.core.Book;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class BookForm extends JFrame {
	private static BookForm _instance;
	private Book _book;
	private boolean _uiGenerated = false;
	private JPanel _panel;
	private JTextArea _titleArea;
	private JTextArea _authorArea;
	private JTextArea _sinopseArea;
	private String _newTitle;
	private String _newAuthor;
	private String _newSinopse;

	public static boolean isEmpty() {
		return _instance == null;
	}

	public static BookForm getInstance() {
		if (_instance.isEmpty()) {
			new BookForm();
		}
		return _instance;
	}

	private BookForm() {
		_instance = this;
	}

	private void setNewTitle(String newTitle) {
		_newTitle = newTitle;
	}

	private String getNewTitle() {
		return _newTitle;
	}

	private void setNewAuthor(String newAuthor) {
		_newAuthor = newAuthor;
	}

	private String getNewAuthor() {
		return _newAuthor;
	}

	private void setNewSinopse(String newSinopse) {
		_newSinopse = newSinopse;
	}

	private String getNewSinopse() {
		return _newSinopse;
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

	private void setBook(String bookTitle) {
		try {
			Library library = Library.getInstance();
			_book = library.getBook(bookTitle);
		} catch (InexistentBookException exception) {
			System.err.print(exception.getMessage());
		}
	}

	private Book getBook() {
		return _book;
	}

	private JPanel getJPanel() {
		if (_panel == null) {
			_panel = new JPanel();
		}
		return _panel;
	}
	
	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return label;
	}

	private JTextArea createTitleArea() {
		JTextArea titleArea = new JTextArea();
		titleArea.setText(this.getBook().getTitle());
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);
		titleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		titleArea.setBounds(90,15,300,20);
		titleArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Title Selected");
			}

			@Override
			public void focusLost(FocusEvent event) {
				setNewTitle(titleArea.getText().trim());
				System.out.println("Title Unselected: " + getNewTitle());
			}
		});
		return titleArea;	
	}

	private JTextArea createAuthorArea() {
		JTextArea authorArea = new JTextArea();
		authorArea.setText(this.getBook().getAuthor());
		authorArea.setLineWrap(true);
		authorArea.setWrapStyleWord(true);
		authorArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		authorArea.setBounds(90,55,300,20);
		authorArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Author Selected");
			}

			@Override
			public void focusLost(FocusEvent event) {
				setNewAuthor(authorArea.getText().trim());
				System.out.println("Author Unselected: " + getNewAuthor());
			}
		});
		return authorArea;	
	}

	private JTextArea createSinopseArea() {
		JTextArea sinopseArea = new JTextArea();
		sinopseArea.setText(this.getBook().getSinopse());
		sinopseArea.setLineWrap(true);
		sinopseArea.setWrapStyleWord(true);
		sinopseArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		sinopseArea.setBounds(90,90,300,65);
		sinopseArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Sinopse Selected");
			}

			@Override
			public void focusLost(FocusEvent event) {
				setNewSinopse(sinopseArea.getText().trim());
				System.out.println("Sinopse Unselected: " + getNewSinopse());
			}
		});
		return sinopseArea;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,170,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				BookForm form = BookForm.getInstance();
				try {
					Book book = form.getBook();
					if (form.getNewTitle() != null) {
						book.setTitle(form.getNewTitle());
					}
					if (form.getNewAuthor() != null) {
						book.setAuthor(form.getNewAuthor());
					}
					if (form.getNewSinopse() != null) {
						book.setSinopse(form.getNewSinopse());
					}
				} catch (EmptyTitleException
							| EmptyAuthorException exception) {
					System.err.print(exception.getMessage());
				}
				form.dispose();
			}
		});
		return saveButton;
	}

	private void createLayout() {
		Container pane = this.getContentPane();
		JLabel titleLabel = this.createLabel("Title:");
		JLabel authorLabel = this.createLabel("Author:");
		JLabel sinopseLabel = this.createLabel("Sinopse:");
		JButton saveButton = this.createSaveButton();

		this.setTitleArea(this.createTitleArea());
		this.setAuthorArea(this.createAuthorArea());
		this.setSinopseArea(this.createSinopseArea());

		titleLabel.setBounds(10,10,70,30);
		authorLabel.setBounds(10,50,70,30);
		sinopseLabel.setBounds(10,90,70,30);

		pane.add(titleLabel);
		pane.add(authorLabel);
		pane.add(sinopseLabel);
		pane.add(saveButton);
		pane.add(this.getTitleArea());
		pane.add(this.getAuthorArea());
		pane.add(this.getSinopseArea());
	}

	private void initUI() {
		JPanel panel = this.getJPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("Book");
		this.setSize(400,210);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUIGenerated(true);
	}

	private void updateUI() {
		JTextArea titleArea = this.getTitleArea();
		JTextArea authorArea = this.getAuthorArea();
		JTextArea sinopseArea = this.getSinopseArea();
		titleArea.setText(this.getBook().getTitle());
		authorArea.setText(this.getBook().getAuthor());
		sinopseArea.setText(this.getBook().getSinopse());
	}

	public void run(String bookTitle) {
		this.setBook(bookTitle);
		if (isUIGenerated()) {
			this.updateUI();
		} else {
			this.initUI();
		}
		this.setVisible(true);
	}

}
