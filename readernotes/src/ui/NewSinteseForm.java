package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import readernotes.src.core.Sintese;
import readernotes.src.core.Library;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.DoubleEntryException;

public class NewSinteseForm extends JFrame {
	private static NewSinteseForm _instance;
	private String _sinteseTitle;
	private String _sinteseBookTitle;
	private String _sinteseContent;

	public static NewSinteseForm getInstance() {
		if (_instance.isEmpty()) {
			new NewSinteseForm();
		}
		return _instance;
	}

	private NewSinteseForm() {
		_instance = this;
	}

	public void run() {
		initUI();
		this.setVisible(true);
	}

	public void initUI() {
		JPanel panel = new JPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("New Sintese");
		this.setSize(400,610);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void createLayout() {
		Container pane = this.getContentPane();
		JButton saveButton = this.createSaveButton();
		JTextArea titleArea = this.createTitleArea();
		JTextArea bookTitleArea = this.createBookTitleArea();
		JTextArea contentArea = this.createContentArea();
		JLabel titleLabel = this.createNewLabel("Title");
		JLabel bookTitleLabel = this.createNewLabel("Book Title");
		JLabel contentLabel = this.createNewLabel("Content:");

		titleLabel.setBounds(10,10,70,30);
		bookTitleLabel.setBounds(10,50,100,30);
		contentLabel.setBounds(10,90,70,30);

		pane.add(titleLabel);
		pane.add(bookTitleLabel);
		pane.add(contentLabel);
		pane.add(titleArea);
		pane.add(bookTitleArea);
		pane.add(contentArea);
		pane.add(saveButton);
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
				NewSinteseForm form = NewSinteseForm.getInstance();				
				System.out.println("Title Unselected");
				form.setSinteseTitle(titleArea.getText().trim());
				System.out.println(form.getSinteseTitle());
			}
		});
		return titleArea;
	}

	private JTextArea createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		bookTitleArea.setLineWrap(true);
		bookTitleArea.setWrapStyleWord(true);
		bookTitleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		bookTitleArea.setBounds(90,55,300,20);
		bookTitleArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Book Title Selected");
			}

			@Override
			public void focusLost(FocusEvent event) {
				NewSinteseForm form = NewSinteseForm.getInstance();
				System.out.println("Book Title Unselected");
				form.setSinteseBookTitle(bookTitleArea.getText().trim());
				System.out.println(form.getSinteseBookTitle());
			}
		});
		return bookTitleArea;
	}

	private JTextArea createContentArea() {
		JTextArea contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		contentArea.setBounds(10,120,380,420);
		contentArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				System.out.println("Content Selected");
			}
		
			@Override
			public void focusLost(FocusEvent event) {
				NewSinteseForm form = NewSinteseForm.getInstance();
				System.out.println("Content Unselected");
				form.setSinteseContent(contentArea.getText().trim());
				System.out.println(form.getSinteseContent());
			}
		});
		return contentArea;
	}

	private JLabel createNewLabel(String labelValue) {
		JLabel label = new JLabel(labelValue);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return label;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,570,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					NewSinteseForm form = NewSinteseForm.getInstance();
					Library library = Library.getInstance();
					Sintese newSintese = new Sintese(form.getSinteseTitle(),
													 form.getSinteseBookTitle(),
													 form.getSinteseContent());
					library.addSintese(newSintese);
					form.dispose();
				} catch (EmptyTitleException
						 | DoubleEntryException exception) {
					System.err.print(exception.getMessage());
				}
			}
		});
		return saveButton;
	}

	public void setSinteseTitle(String title) {
		_sinteseTitle = title;
	}

	public String getSinteseTitle() {
		return _sinteseTitle;
	}

	public void setSinteseBookTitle(String bookTitle) {
		_sinteseBookTitle = bookTitle;
	}

	public String getSinteseBookTitle() {
		return _sinteseBookTitle;
	}

	public void setSinteseContent(String content) {
		_sinteseContent = content;
	}

	public String getSinteseContent() {
		return _sinteseContent;
	}

	public static boolean isEmpty() {
		return _instance == null;
	}
}
