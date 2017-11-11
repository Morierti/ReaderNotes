/**
Copyright (C) 2016  Rodrigo Ramos Rosa

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
**/

package rodrigorar.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import rodrigorar.core.Library;
import rodrigorar.core.Book;
import rodrigorar.exceptions.InexistentBookException;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.EmptyAuthorException;
import rodrigorar.ui.listeners.BookFormListener;

public class BookForm
extends JFrame {
	private Book _book;
	private JScrollPane _titleArea;
	private JScrollPane _authorArea;
	private JScrollPane _isbnArea;
	private JScrollPane _subjectArea;
	private JScrollPane _sinopseArea;

	public BookForm(String bookTitle) {
		this.setBook(bookTitle);
		this.initUI();
		this.setVisible(true);
	}

	private void setTitleArea(JScrollPane titleArea) {
		_titleArea = titleArea;
	}

	public JScrollPane getTitleArea() {
		return _titleArea;
	}

	private void setAuthorArea(JScrollPane authorArea) {
		_authorArea = authorArea;
	}

	public JScrollPane getAuthorArea() {
		return _authorArea;
	}

	private void setISBNArea(JScrollPane isbnArea) {
		_isbnArea = isbnArea;
	}

	public JScrollPane getISBNArea() {
		return _isbnArea;
	}

	private void setSubjectArea(JScrollPane subjectArea) {
		_subjectArea = subjectArea;
	}

	public JScrollPane getSubjectArea() {
		return _subjectArea;
	}

	private void setSinopseArea(JScrollPane sinopseArea) {
		_sinopseArea = sinopseArea;
	}

	public JScrollPane getSinopseArea() {
		return _sinopseArea;
	}

	private void setBook(String bookTitle) {
		try {
			Library library = Library.getInstance();
			_book = library.getBook(bookTitle);
		} catch (InexistentBookException exception) {
			System.err.print(exception.getMessage());
			JOptionPane.showMessageDialog(this, exception.getMessage());
		}
	}

	public Book getBook() {
		return _book;
	}

	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);

		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));

		return label;
	}

	private JScrollPane createTitleArea() {
		JTextArea titleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(titleArea);

		titleArea.setText(this.getBook().getTitle());
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);

		scrollPane.setPreferredSize(new Dimension(300,20));
		scrollPane.setMaximumSize(new Dimension(2000,20));

		return scrollPane;
	}

	private JScrollPane createAuthorArea() {
		JTextArea authorArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(authorArea);

		authorArea.setText(this.getBook().getAuthor());
		authorArea.setLineWrap(true);
		authorArea.setWrapStyleWord(true);

		scrollPane.setPreferredSize(new Dimension(300,20));
		scrollPane.setMaximumSize(new Dimension(2000,20));

		return scrollPane;
	}

	private JScrollPane createISBNArea() {
		JTextArea isbnArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(isbnArea);

		isbnArea.setText(this.getBook().getISBN());
		isbnArea.setLineWrap(true);
		isbnArea.setWrapStyleWord(true);

		scrollPane.setPreferredSize(new Dimension(300,20));
		scrollPane.setMaximumSize(new Dimension(2000,20));

		return scrollPane;
	}

	private JScrollPane createSubjectArea() {
		JTextArea subjectArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(subjectArea);

		subjectArea.setText(this.getBook().getSubject());
		subjectArea.setLineWrap(true);
		subjectArea.setWrapStyleWord(true);

		scrollPane.setPreferredSize(new Dimension(300,20));
		scrollPane.setMaximumSize(new Dimension(2000,20));

		return scrollPane;
	}

	private JScrollPane createSinopseArea() {
		JTextArea sinopseArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(sinopseArea);

		sinopseArea.setText(this.getBook().getSinopse());
		sinopseArea.setLineWrap(true);
		sinopseArea.setWrapStyleWord(true);

		scrollPane.setPreferredSize(new Dimension(300,65));

		return scrollPane;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");

		saveButton.addActionListener(new BookFormListener(this));

		return saveButton;
	}

	private JPanel createTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = this.createLabel("Title");

		this.setTitleArea(this.createTitleArea());

		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createRigidArea(new Dimension(35,0)));
		titlePanel.add(this.getTitleArea());

		return titlePanel;

	}

	private JPanel createAuthorPanel() {
		JPanel authorPanel = new JPanel();
		JLabel authorLabel = this.createLabel("Author");

		this.setAuthorArea(this.createAuthorArea());

		authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.X_AXIS));
		authorPanel.add(authorLabel);
		authorPanel.add(Box.createRigidArea(new Dimension(19,0)));
		authorPanel.add(this.getAuthorArea());

		return authorPanel;
	}

	private JPanel createISBNPanel() {
		JPanel isbnPanel = new JPanel();
		JLabel isbnLabel = this.createLabel("ISBN");

		this.setISBNArea(this.createISBNArea());

		isbnPanel.setLayout(new BoxLayout(isbnPanel, BoxLayout.X_AXIS));
		isbnPanel.add(isbnLabel);
		isbnPanel.add(Box.createRigidArea(new Dimension(36,0)));
		isbnPanel.add(this.getISBNArea());

		return isbnPanel;
	}

	private JPanel createSubjectPanel() {
		JPanel subjectPanel = new JPanel();
		JLabel subjectLabel = this.createLabel("Subject");

		this.setSubjectArea(this.createSubjectArea());

		subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.X_AXIS));
		subjectPanel.add(subjectLabel);
		subjectPanel.add(Box.createRigidArea(new Dimension(16,0)));
		subjectPanel.add(this.getSubjectArea());

		return subjectPanel;
	}

	private JPanel createSinopsePanel() {
		JPanel sinopsePanel = new JPanel();
		JLabel sinopseLabel = this.createLabel("Sinopse");

		this.setSinopseArea(this.createSinopseArea());

		sinopsePanel.setLayout(new BoxLayout(sinopsePanel, BoxLayout.X_AXIS));
		sinopsePanel.add(sinopseLabel);
		System.out.println("Added Label");
		sinopsePanel.add(Box.createRigidArea(new Dimension(10,0)));
		sinopsePanel.add(this.getSinopseArea());

		return sinopsePanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(this.createSaveButton());

		return buttonPanel;
	}

	private JPanel createLayout() {
		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(this.createTitlePanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(this.createAuthorPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(this.createISBNPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(this.createSubjectPanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(this.createSinopsePanel());
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(this.createButtonPanel());

		return panel;
	}

	private void initUI() {
		this.add(this.createLayout());
		this.setTitle("Book");
		this.setSize(400,320);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
