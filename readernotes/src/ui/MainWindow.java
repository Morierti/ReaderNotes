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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;
import java.util.Map;
import java.util.Set;
import readernotes.src.core.Library;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Book;

//TEST
import java.util.ArrayList;

public class MainWindow
extends JFrame {
	private static MainWindow _instance;

	public static MainWindow getInstance() {
		if (_instance == null) {
			new MainWindow();
		}
		return _instance;
	}

    private MainWindow() {
		_instance = this;
        initUI();
    }

    private void initUI() {
        this.createMenuBar();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,25,35));
		this.createNewBookList(panel);
		this.createNewReadingFileList(panel);
		this.add(panel);
        this.setTitle("Reader Notes");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        this.createFileMenu(menubar);
        this.createOperationsMenu(menubar);
        this.createHelpMenu(menubar);
        this.setJMenuBar(menubar);
    }

    private void createNewReadingFileList(JPanel panel) {
		Library library = Library.getInstance();
		Map<String, ReadingFile> readingFileDatabase = library.getReadingFileDB();
		Set<String> readingFileDatabaseKeys = readingFileDatabase.keySet();
		JList list = new JList(readingFileDatabaseKeys.toArray());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = list.locationToIndex(event.getPoint());
					ListModel listModel = list.getModel();
					new ReadingFileForm((String) listModel.getElementAt(index));
				}
			}
		});
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(400,500));
		pane.getViewport().add(list);
		panel.add(pane);

    }

    private void createNewBookList(JPanel panel) {
		Library library = Library.getInstance();
		Map<String, Book> bookDatabase = library.getBookDB();
		Set<String> bookDatabaseKeys = bookDatabase.keySet();
		JList list = new JList(bookDatabaseKeys.toArray());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = list.locationToIndex(event.getPoint());
					ListModel listModel = list.getModel();
					new BookForm((String) listModel.getElementAt(index));
				}
			}
		});
		JScrollPane pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(400,500));
		pane.getViewport().add(list);
		panel.add(pane);
    }

    private void createFileMenu(JMenuBar menubar) {
    	JMenu fileMenu = new JMenu("File");
    	this.createFileMenuItems(fileMenu);
    	menubar.add(fileMenu);
    }

    private void createOperationsMenu(JMenuBar menubar) {
    	JMenu operationsMenu = new JMenu("Operations");
    	this.createOperationsMenuItems(operationsMenu);
    	menubar.add(operationsMenu);
    }

    private void createHelpMenu(JMenuBar menubar) {
    	JMenu helpMenu = new JMenu("Help");
    	this.createHelpMenuItems(helpMenu);
    	menubar.add(helpMenu);
    }

    private void createFileMenuItems(JMenu fileMenu) {
	   	JMenuItem exit = new JMenuItem("Exit");
        JMenuItem save = new JMenuItem("Save");
        //ToolTips

        exit.setToolTipText("Exit Application");

     	//Event Handlers

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Library library = Library.getInstance();
				library.storeBookDatabase();
				library.storeReadingFileDatabase();
			}
		});

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				Library library = Library.getInstance();
				library.storeBookDatabase();
				library.storeReadingFileDatabase();
                System.exit(0);
            }
        });

        //Incorporation

		fileMenu.add(save);
        fileMenu.add(exit);
    }

    private void createHelpMenuItems(JMenu helpMenu) {
    	JMenuItem about = new JMenuItem("About");

    	//ToolTips
    	about.setToolTipText("Information about the application.");

    	//Event Handlers

    	about.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			new AboutWindow();
    		}
    	});

    	//Incorporation
    	helpMenu.add(about);
    }

    private void createOperationsMenuItems(JMenu operationsMenu) {
    	JMenuItem insertBook = new JMenuItem("New Book");
		JMenuItem insertReadingFile = new JMenuItem("New Reading File");
		JMenuItem removeBook = new JMenuItem("Remove Book");
		JMenuItem removeReadingFile = new JMenuItem("Remove Reading File");
		JMenuItem search = new JMenuItem("Search");

    	//ToolTips

    	//Event Handlers

		insertBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new NewBookForm();
			}
		});

		insertReadingFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new NewReadingFileForm();
			}
		});

		removeBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new RemoveBookWindow();
			}
		});

		removeReadingFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new RemoveReadingFileWindow();
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new SearchWindow();
			}
		});

    	//Incorporation

		operationsMenu.add(insertBook);
		operationsMenu.add(insertReadingFile);
		operationsMenu.add(removeBook);
		operationsMenu.add(removeReadingFile);
		operationsMenu.add(search);
    }

}
