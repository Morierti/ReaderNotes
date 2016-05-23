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
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Library;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.DoubleEntryException;

public class NewReadingFileForm extends JFrame {
	private static NewReadingFileForm _instance;
	private JScrollPane _titleArea;
	private JScrollPane _bookTitleArea;
	private JScrollPane _contentArea;

	public NewReadingFileForm() {
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

	private JScrollPane createTitleArea() {
		JTextArea titleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(titleArea);
		scrollPane.setBounds(90,15,300,20);

		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createBookTitleArea() {
		JTextArea bookTitleArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(bookTitleArea);
		scrollPane.setBounds(90,55,300,20);

		bookTitleArea.setLineWrap(true);
		bookTitleArea.setWrapStyleWord(true);

		return scrollPane;
	}

	private JScrollPane createContentArea() {
		JTextArea contentArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setBounds(10,120,380,320);

		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);

		return scrollPane;
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
				//Some are for NewReadingFileForm class.
				try {
					Library library = Library.getInstance();

					JViewport titleViewport = getTitleArea().getViewport();
					JViewport bookTitleViewport = getBookTitleArea().getViewport();
					JViewport contentViewport = getContentArea().getViewport();

					JTextArea title = (JTextArea) titleViewport.getView();
					JTextArea bookTitle = (JTextArea) bookTitleViewport.getView();
					JTextArea content = (JTextArea) contentViewport.getView();

					library.addReadingFile(new ReadingFile(title.getText().trim(),
												 			bookTitle.getText().trim(),
												 			content.getText().trim()));
					//Update List on main window
					MainWindow.getInstance().updateReadingFileList();
					dispose();
				} catch (EmptyTitleException
						 | DoubleEntryException exception) {
					System.err.print(exception.getMessage());
				}
			}
		});
		return saveButton;
	}


	private void createLayout(JPanel panel) {
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

		panel.add(titleLabel);
		panel.add(bookTitleLabel);
		panel.add(contentLabel);
		panel.add(saveButton);
		panel.add(this.getTitleArea());
		panel.add(this.getBookTitleArea());
		panel.add(this.getContentArea());
	}

	public void initUI() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.createLayout(panel);
		this.add(panel);
		this.setTitle("New Reading File");
		this.setSize(400,510);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
