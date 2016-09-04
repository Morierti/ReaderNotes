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
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import java.util.Map;
import java.util.Set;
import readernotes.src.core.Library;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Book;
import readernotes.src.ui.listeners.MainWindowListener;

//TEST
import java.util.ArrayList;

public class MainWindow
extends JFrame {
	private static MainWindow _instance;
	private JList _bookList;
	private JList _readingFileList;

	public static MainWindow getInstance() {
		if (_instance == null) {
			new MainWindow();
		}
		return _instance;
	}

	private void setInstance(MainWindow value) {
		_instance = value;
	}

    private MainWindow() {
		this.setInstance(this);
        this.initUI();
		this.setVisible(true);
    }

	private void setBookList(JList bookList) {
		_bookList = bookList;
	}

	private JList getBookList() {
		return _bookList;
	}

	private void setReadingFileList(JList readingFileList) {
		_readingFileList = readingFileList;
	}

	private JList getReadingFileList() {
		return _readingFileList;
	}

    private void initUI() {
        this.createMenuBar();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		this.createNewBookList(panel);
		this.createNewReadingFileList(panel);
		this.add(panel);
        this.setTitle("Reader Notes");
        this.setSize(900,500);
		this.pack();
        this.setLocationRelativeTo(null);
		this.addWindowListener(new MainWindowListener());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        this.createFileMenu(menubar);
        this.createOperationsMenu(menubar);
        this.createHelpMenu(menubar);
        this.setJMenuBar(menubar);
    }

	private DefaultListModel buildBookListModel() {
		DefaultListModel listModel = new DefaultListModel();
		Map<String, Book> bookDatabase = Library.getInstance().getBookDB();
		Set<String> keySet = bookDatabase.keySet();

		for (String key : keySet) {
			listModel.addElement(key);
		}

		return listModel;
	}

	private DefaultListModel buildReadingFileListModel() {
		DefaultListModel listModel = new DefaultListModel();
		Map<String, ReadingFile> readingFileDatabase = Library.getInstance().getReadingFileDB();
		Set<String> keySet = readingFileDatabase.keySet();

		for (String key : keySet) {
			listModel.addElement(key);
		}

		return listModel;
	}

    private void createNewReadingFileList(JPanel panel) {
		final JList list = new JList(this.buildReadingFileListModel());

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(400,400));

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = list.locationToIndex(event.getPoint());
					ListModel listModel = list.getModel();
					new ReadingFileForm((String) listModel.getElementAt(index));
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		this.setReadingFileList(list);
		panel.add(scrollPane);

    }

    private void createNewBookList(JPanel panel) {
		final JList list = new JList(this.buildBookListModel());

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(400,400));

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					final int index = list.locationToIndex(event.getPoint());
					final ListModel listModel = list.getModel();
					new BookForm((String) listModel.getElementAt(index));
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		this.setBookList(list);
		panel.add(scrollPane);
    }

	public void updateBookList() {
		DefaultListModel listModel = this.buildBookListModel();
		JList bookList = this.getBookList();

		bookList.setModel(listModel);
		bookList.setSelectedIndex(0);

		System.out.println("Updated Book List");
	}

	public void updateReadingFileList() {
		DefaultListModel listModel = this.buildReadingFileListModel();
		JList readingFileList = this.getReadingFileList();

		readingFileList.setModel(listModel);
		readingFileList.setSelectedIndex(0);
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
				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				new SaveMessageWindow();
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
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});

		removeBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new RemoveBookWindow();
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});

		removeReadingFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new RemoveReadingFileWindow();
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new SearchWindow();
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
