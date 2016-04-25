package readernotes.src.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//TEST
import java.util.ArrayList;

public class MainWindow extends JFrame {

    public MainWindow() {
        initUI();
    }
    
    private void initUI() {
        this.createMenuBar();
	JPanel panel = new JPanel();
	this.createNewBookList(panel);
	this.createNewSinteseList(panel);
	this.add(panel);
        this.setTitle("Reader Notes");
        this.setSize(500, 500);
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

    private void createNewSinteseList(JPanel panel) {

    }

    private void createNewBookList(JPanel panel) {
	ArrayList<String> testList = new ArrayList<String>();
	testList.add("1");
	testList.add("2");
	testList.add("3");
	testList.add("4");
	testList.add("5");
	JList list = new JList(testList.toArray());
	list.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			//Code
		}
	});
	JScrollPane pane = new JScrollPane();
	pane.setPreferredSize(new Dimension(200,400));
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
   	JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem save = new JMenuItem("Save");
        //ToolTips
        
        exitMenuItem.setToolTipText("Exit Application");
     	
     	//Event Handlers
     	   
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

	save.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//Code
		}
	});
        
        //Incorporation
        
	fileMenu.add(save);
        fileMenu.add(exitMenuItem);
    }
    
    private void createHelpMenuItems(JMenu helpMenu) {
    	JMenuItem aboutItem = new JMenuItem("About");
    	
    	//ToolTips
    	aboutItem.setToolTipText("Information about the application.");
    	
    	//Event Handlers
    	
    	aboutItem.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent event) {
    			//Code
    		}
    	});
    	
    	//Incorporation
    	helpMenu.add(aboutItem);
    }
    
    private void createOperationsMenuItems(JMenu operationsMenu) {
    	JMenuItem insertBook = new JMenuItem("New Book");
	JMenuItem insertSintese = new JMenuItem("New Sintese");
	JMenuItem removeBook = new JMenuItem("Remove Book");
	JMenuItem removeSintese = new JMenuItem("Remove Sintese");
	JMenuItem search = new JMenuItem("Search");
	
    	//ToolTips
    	
    	//Event Handlers
	
	insertBook.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//Code
		}
	});

	insertSintese.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//Code
		}
	});

	removeBook.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//Code
		}
	});

	removeSintese.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//code
		}
	});

	search.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			//code
		}
	});
    	
    	//Incorporation

	operationsMenu.add(insertBook);
	operationsMenu.add(insertSintese);
	operationsMenu.add(removeBook);
	operationsMenu.add(removeSintese);
	operationsMenu.add(search);
    }

}
