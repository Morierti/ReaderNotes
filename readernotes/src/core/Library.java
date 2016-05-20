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
    private Map<String, Book> _bookDB;
    private Map<String, ReadingFile> _readingFileDB;

    public static Library getInstance() {
        if (_instance.isEmpty()) {
            new Library();
        }
        return _instance;
    }

    public static boolean isEmpty() {
        return _instance == null;
    }

    private Library() {
        init();
    }

    private void init() {
        this.setInstance(this);
        this.loadBookDatabase();
        this.loadReadingFileDatabase();
    }

    private void setInstance(Library instance) {
        _instance = instance;
    }

    public void loadBookDatabase() {
        try {
            this.setBookDB(IOManager.getInstance().buildBookDatabase());
        } catch (EmptyTitleException
                | EmptyAuthorException
                | JDOMException
                | IOException exception) {
            this.setBookDB(new HashMap<String, Book>());
        }
    }

    public void loadReadingFileDatabase() {
        try {
            this.setReadingFileDB(IOManager.getInstance().buildReadingFileDatabase());
        } catch (EmptyTitleException
                | JDOMException
                | IOException exception) {
            this.setReadingFileDB(new HashMap<String, ReadingFile>());
        }
    }

    public void addBook(Book newBook)
    throws
    DoubleEntryException {
        Map<String, Book> bookDatabase = this.getBookDB();
        if (bookDatabase.containsKey(newBook.getTitle())) {
            throw new DoubleEntryException(newBook.getTitle());
        } else {
            bookDatabase.put(newBook.getTitle(), newBook);
        }
    }

    public void removeBook(String bookTitle) {
        Map<String, Book> bookDatabase = this.getBookDB();
        bookDatabase.remove(bookTitle);
    }

    public Book getBook(String bookTitle)
    throws
    InexistentBookException {
        Map<String,Book> bookDatabase = this.getBookDB();
        if (bookDatabase.containsKey(bookTitle)) {
            return bookDatabase.get(bookTitle);
        } else {
            throw new InexistentBookException(bookTitle);
        }
    }

    public void addReadingFile(ReadingFile newReadingFile)
    throws
    DoubleEntryException {
        Map<String, ReadingFile> readingFileDatabase = this.getReadingFileDB();
        if (readingFileDatabase.containsKey(newReadingFile.getTitle())) {
            throw new DoubleEntryException(newReadingFile.getTitle());
        } else {
            readingFileDatabase.put(newReadingFile.getTitle(), newReadingFile);
        }
    }

    public void removeReadingFile(String title) {
        Map<String, ReadingFile> readingFileDatabase = this.getReadingFileDB();
        readingFileDatabase.remove(title);
    }

    public ReadingFile getReadingFile(String readingFileTitle)
    throws
    InexistentReadingFileException {
        Map<String, ReadingFile> readingFileDatabase = this.getReadingFileDB();
        if (!readingFileDatabase.containsKey(readingFileTitle)) {
            throw new InexistentReadingFileException(readingFileTitle);
        } else {
            return readingFileDatabase.get(readingFileTitle);
        }
    }

	public void storeBookDatabase() {
		try {
			IOManager.getInstance().writeBookDatabaseDocument();
		} catch (InexistentBookException exception) {
			System.err.print(exception.getMessage());
		}
	}

	public void storeReadingFileDatabase() {
		try {
			IOManager.getInstance().writeReadingFileDatabaseDocument();
		} catch (InexistentReadingFileException exception) {
			System.err.print(exception.getMessage());
		}
	}

    private void setBookDB(Map<String, Book> bookDB) {
        _bookDB = bookDB;
    }

    public Map<String, Book> getBookDB() {
        return _bookDB;
    }

    private void setReadingFileDB(Map<String, ReadingFile> readingFileDB) {
        _readingFileDB = readingFileDB;
    }

    public Map<String, ReadingFile> getReadingFileDB() {
        return _readingFileDB;
    }

}
