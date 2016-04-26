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

		Book newBook1 = new Book("A Mensagem", "José Saramago");
		Book newBook2 = new Book("Moby Dick", "Some Author");
		Book newBook3 = new Book("A Iliada", "Homero");
		Book newBook4 = new Book("Biblia", "Unknown");
		Book newBook5 = new Book("Os Pensadores", "Daniel J.Boorstin");
		Sintese newSintese1 = new Sintese("Sintese 1", "A Mensagem");
		Sintese newSintese2 = new Sintese("Sintese 2", "Moby Dick");
		Sintese newSintese3 = new Sintese("Sintese 3", "A Iliada");
		Sintese newSintese4 = new Sintese("Sintese 4", "Biblia");
		Sintese newSintese5 = new Sintese("Sintese 5", "Os Pensadores");

		_library.addBook(newBook1);
		_library.addBook(newBook2);
		_library.addBook(newBook3);
		_library.addBook(newBook4);
		_library.addBook(newBook5);
		_library.addSintese(newSintese1);
		_library.addSintese(newSintese2);
		_library.addSintese(newSintese3);
		_library.addSintese(newSintese4);
		_library.addSintese(newSintese5);
		
	    EventQueue.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() {
	            MainWindow mainWindow = new MainWindow();
	            mainWindow.setVisible(true);
	        }
	    });
	}
}
