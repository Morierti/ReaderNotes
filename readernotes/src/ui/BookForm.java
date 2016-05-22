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
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookForm
extends JFrame {
	private Book _book;
	private JScrollPane _titleArea;
	private JScrollPane _authorArea;
	private JScrollPane _sinopseArea;

	public BookForm(String bookTitle) {
		this.setBook(bookTitle);
		this.initUI();
		this.setVisible(true);
	}

	private void setTitleArea(JScrollPane titleArea) {
		_titleArea = titleArea;
	}

	private JScrollPane getTitleArea() {
		return _titleArea;
	}

	private void setAuthorArea(JScrollPane authorArea) {
		_authorArea = authorArea;
	}

	private JScrollPane getAuthorArea() {
		return _authorArea;
	}

	private void setSinopseArea(JScrollPane sinopseArea) {
		_sinopseArea = sinopseArea;
	}

	private JScrollPane getSinopseArea() {
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

	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		return label;
	}

	private JScrollPane createTitleArea() {
		JTextArea titleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(titleArea);
		scrollPane.setBounds(90,15,300,20);

		titleArea.setText(this.getBook().getTitle());
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createAuthorArea() {
		JTextArea authorArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(authorArea);
		scrollPane.setBounds(90,55,300,20);

		authorArea.setText(this.getBook().getAuthor());
		authorArea.setLineWrap(true);
		authorArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createSinopseArea() {
		JTextArea sinopseArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(sinopseArea);
		scrollPane.setBounds(90,90,300,65);

		sinopseArea.setText(this.getBook().getSinopse());
		sinopseArea.setLineWrap(true);
		sinopseArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,170,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			//Some calls are for the BookForm Class.
			JViewport titleViewport = getTitleArea().getViewport();
			JViewport authorViewport = getAuthorArea().getViewport();
			JViewport sinopseViewport = getSinopseArea().getViewport();
			JTextArea titleArea = (JTextArea) titleViewport.getView();
			JTextArea authorArea = (JTextArea) authorViewport.getView();
			JTextArea sinopseArea = (JTextArea) sinopseViewport.getView();

				try {
					Book book = getBook();
					if (titleArea.getText() != null) {
						book.setTitle(titleArea.getText().trim());
					}
					if (authorArea.getText() != null) {
						book.setAuthor(authorArea.getText().trim());
					}
					if (sinopseArea.getText() != null) {
						book.setSinopse(sinopseArea.getText().trim());
					}
				} catch (EmptyTitleException
						| EmptyAuthorException exception) {
					System.err.print(exception.getMessage());
				}
				dispose();
			}
		});
		return saveButton;
	}

	private void createLayout(JPanel panel) {
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

		panel.add(titleLabel);
		panel.add(authorLabel);
		panel.add(sinopseLabel);
		panel.add(saveButton);
		panel.add(this.getTitleArea());
		panel.add(this.getAuthorArea());
		panel.add(this.getSinopseArea());
	}

	private void initUI() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.createLayout(panel);
		this.add(panel);
		this.setTitle("Book");
		this.setSize(400,220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
