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

import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.BorderFactory;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Library;
import readernotes.src.exceptions.InexistentReadingFileException;
import readernotes.src.exceptions.EmptyTitleException;

public class ReadingFileForm extends JFrame {
	private ReadingFile _readingFile;
	private JScrollPane _titleArea;
	private JScrollPane _bookTitleArea;
	private JScrollPane _contentArea;

	public ReadingFileForm(String title) {
		this.setReadingFile(title);
		this.initUI();
		this.setVisible(true);
	}

	private void setTitleArea(JScrollPane titleArea) {
		_titleArea = titleArea;
	}

	private JScrollPane getTitleArea() {
		return _titleArea;
	}

	private void setBookTitleArea(JScrollPane bookTitleArea) {
		_bookTitleArea = bookTitleArea;
	}

	private JScrollPane getBookTitleArea() {
		return _bookTitleArea;
	}

	private void setContentArea(JScrollPane contentArea) {
		_contentArea = contentArea;
	}

	private JScrollPane getContentArea() {
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

	private JScrollPane createTitleArea() {
		JTextArea titleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(titleArea);
		scrollPane.setBounds(90,15,300,20);

		titleArea.setText(this.getReadingFile().getTitle());
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(bookTitleArea);
		scrollPane.setBounds(90,55,300,20);

		bookTitleArea.setText(this.getReadingFile().getBookTitle());
		bookTitleArea.setLineWrap(true);
		bookTitleArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createContentArea() {
		JTextArea contentArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setBounds(10,120,380,320);

		contentArea.setText(this.getReadingFile().getContent());
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(320,450,70,30);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Some calls are for ReadingFileFrom class.
				JViewport titleViewport = getTitleArea().getViewport();
				JViewport bookTitleViewport = getBookTitleArea().getViewport();
				JViewport contentViewport = getContentArea().getViewport();

				JTextArea title = (JTextArea) titleViewport.getView();
				JTextArea bookTitle = (JTextArea) bookTitleViewport.getView();
				JTextArea content = (JTextArea) contentViewport.getView();

				try {
					ReadingFile readingFile = getReadingFile();
					if (title.getText() != null) {
						readingFile.setTitle(title.getText().trim());
					}
					if (bookTitle.getText() != null) {
						readingFile.setBookTitle(bookTitle.getText().trim());
					}
					if (content.getText() != null) {
						readingFile.setContent(content.getText().trim());
					}
				} catch (EmptyTitleException exception) {
					System.err.print(exception.getMessage());
				}
				dispose();
			}
		});
		return saveButton;
	}

	private void createLayout(JPanel panel) {
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

		panel.add(titleLabel);
		panel.add(bookTitleLabel);
		panel.add(contentLabel);
		panel.add(saveButton);
		panel.add(this.getTitleArea());
		panel.add(this.getBookTitleArea());
		panel.add(this.getContentArea());
	}

	private void initUI() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.createLayout(panel);
		this.add(panel);
		this.setTitle("Reading File");
		this.setSize(400,510);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
