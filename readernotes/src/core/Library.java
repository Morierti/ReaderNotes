package readernotes.src.core;

import java.util.Map;
import java.util.HashMap;
import org.jdom2.JDOMException;
import java.io.IOException;
import readernotes.src.database.IOManager;
import readernotes.src.database.ReadingFileXML;
import readernotes.src.database.BookXML;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentReadingFileException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class Library {
    private static Library _instance;
    private IOManager _ioManager;
    private Map<String, Book> _bookDB;
    private Map<String, ReadingFile> _readingFileDB;

    public static boolean isEmpty() {
        return _instance == null;
    }

    public static Library getInstance() {
        if (_instance.isEmpty()) {
            new Library();
        }
        return _instance;
    }

    private Library() {
        init();
    }

    private void init() {
        _instance = this;
        _ioManager = IOManager.getInstance();
        this.loadBookDatabase();
        this.loadReadingFileDatabase();
    }

    public void addBook(Book newBook)
    throws
    DoubleEntryException {
        if (_bookDB.containsKey(newBook.getTitle())) {
            throw new DoubleEntryException(newBook.getTitle());
        } else {
            _bookDB.put(newBook.getTitle(), newBook);
        }
    }

    public void removeBook(String bookTitle) {
        _bookDB.remove(bookTitle);
    }

    public Book getBook(String bookTitle)
    throws
    InexistentBookException {
        if (_bookDB.containsKey(bookTitle)) {
            return _bookDB.get(bookTitle);
        } else {
            throw new InexistentBookException(bookTitle);
        }
    }

    public Map<String, Book> getBookDB() {
        return _bookDB;
    }

    public void addReadingFile(ReadingFile newReadingFile)
    throws
    DoubleEntryException {
        if (_readingFileDB.containsKey(newReadingFile.getTitle())) {
            throw new DoubleEntryException(newReadingFile.getTitle());
        } else {
            _readingFileDB.put(newReadingFile.getTitle(), newReadingFile);
        }
    }

    public void removeReadingFile(String title) {
        _readingFileDB.remove(title);
    }

    public ReadingFile getReadingFile(String readingFileTitle)
    throws
    InexistentReadingFileException {
        if (!_readingFileDB.containsKey(readingFileTitle)) {
            throw new InexistentReadingFileException(readingFileTitle);
        } else {
            return _readingFileDB.get(readingFileTitle);
        }
    }

    public Map<String, ReadingFile> getReadingFileDB() {
        return _readingFileDB;
    }

	public void storeBookDatabase() {
		try {
			_ioManager.writeBookDatabaseDocument();
		} catch (InexistentBookException exception) {
			//Resolve this.
		}
	}

	public void storeReadingFileDatabase() {
		try {
			_ioManager.writeReadingFileDatabaseDocument();
		} catch (InexistentReadingFileException exception) {
			//Resolve this.
		}
	}

    public void loadBookDatabase() {
        try {
            _bookDB = _ioManager.buildBookDatabase();
        } catch (EmptyTitleException
                | EmptyAuthorException
                | JDOMException
                | IOException exception) {
            _bookDB = new HashMap<String,Book>();
        }
    }

    public void loadReadingFileDatabase() {
        try {
            _readingFileDB = _ioManager.buildReadingFileDatabase();
            if (_readingFileDB == null) {
                System.out.println("LIBRARY: It's null");
            }
        } catch (EmptyTitleException
                | JDOMException
                | IOException exception) {
            _readingFileDB = new HashMap<String,ReadingFile>();
        }
    }

}
