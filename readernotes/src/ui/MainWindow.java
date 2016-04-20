package readernotes.src.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

    public MainWindow() {
        initUI();
    }
    
    private void initUI() {
        this.createMenuBar();
        this.setTitle("Reader Notes");
        this.setSize(800, 700);
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
        
        //ToolTips
        
        exitMenuItem.setToolTipText("Exit Application");
     	
     	//Event Handlers
     	   
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        //Incorporation
        
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
    	
    	//ToolTips
    	
    	//Event Handlers
    	
    	//Incorporation
    }

}
