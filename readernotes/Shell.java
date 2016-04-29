package readernotes;

import readernotes.src.ui.MainWindow;
import readernotes.src.core.Book;
import readernotes.src.core.Sintese;
import readernotes.src.core.Library;
import readernotes.src.database.BookXML;
import readernotes.src.database.SinteseXML;
import readernotes.src.database.IOManager;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentSinteseException;
import java.awt.EventQueue;

public class Shell {
	private static Library _library;
	private IOManager _ioManager;
	private Shell _instance;
	
	public Shell() {
		init();
	}
	
	private void init() {
		_ioManager.getInstance();
		_library = Library.getInstance();
		_instance = this;
	}
	
	public static void main(String[] args)
	throws
	Exception {
		Shell shell = new Shell();
		
	    EventQueue.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() {
	            MainWindow mainWindow = MainWindow.getInstance();
	            mainWindow.setVisible(true);
	        }
	    });
	}
}
