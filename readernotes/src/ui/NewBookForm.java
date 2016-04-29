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
	private String _title;
	private String _author;
	private String _sinopse;
	private static NewBookForm _instance;

	public static NewBookForm getInstance() {
		if (_instance.isEmpty()) {
			new NewBookForm();
		}
		return _instance;
	}

	private NewBookForm() {
		_instance = this;
	}
	
	public void run() {
		initUI();
		this.setVisible(true);
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

	private void createLayout() {
		Container pane = this.getContentPane();
		JButton saveButton = this.createSaveButton();
		JTextArea sinopseArea = this.createSinopseArea();
		JTextArea authorArea = this.createAuthorArea();
		JTextArea titleArea = this.createTitleArea();
		JLabel sinopseLabel = this.createNewLabel("Sinopse");
		JLabel authorLabel = this.createNewLabel("Author");
		JLabel titleLabel = this.createNewLabel("Title");
		
		//Arranjar forma de tirar isto daqui.
		sinopseLabel.setBounds(10,90,70,30);
		authorLabel.setBounds(10,50,70,30);
		titleLabel.setBounds(10,10,70,30);

		pane.add(titleLabel);
		pane.add(titleArea);
		pane.add(authorLabel);
		pane.add(authorArea);
		pane.add(sinopseLabel);
		pane.add(sinopseArea);
		pane.add(saveButton);
		
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				NewBookForm newBookForm = NewBookForm.getInstance();				
				try { 
					Book newBook = new Book(newBookForm.getTitle(),
											newBookForm.getAuthor(),
											newBookForm.getSinopse());
					Library library = Library.getInstance();
					library.addBook(newBook);
				} catch (DoubleEntryException 
						| EmptyTitleException 
						| EmptyAuthorException exception) {
					System.err.print(exception.getMessage());
				}
				newBookForm.dispose();
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
		titleArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Title Selected");
			}
			
			@Override
			public void focusLost(FocusEvent event) {
				NewBookForm newBookForm = NewBookForm.getInstance();				
				newBookForm.setTitle(titleArea.getText().trim());
				System.out.println(newBookForm.getTitle());
			}
		});
		return titleArea;
	}

	private JTextArea createAuthorArea() {
		JTextArea authorArea = new JTextArea();
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
				NewBookForm newBookForm = NewBookForm.getInstance();
				newBookForm.setAuthor(authorArea.getText().trim());
				System.out.println(newBookForm.getAuthor());
			}
		});
		return authorArea;
	}

	private JTextArea createSinopseArea() {
		JTextArea sinopseArea = new JTextArea();
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
				NewBookForm newBookForm = NewBookForm.getInstance();
				newBookForm.setSinopse(sinopseArea.getText().trim());
				System.out.println(newBookForm.getSinopse());
			}
		});
		return sinopseArea;
	}

	private JLabel createNewLabel(String name) {
		JLabel newLabel = new JLabel(name);
		newLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return newLabel;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getSinopse() {
		return _sinopse;
	}

	public void setSinopse(String sinopse) {
		_sinopse = sinopse;
	}

	public static boolean isEmpty() {
		return _instance == null;
	}
}		
