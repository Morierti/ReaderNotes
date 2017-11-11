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

package rodrigorar.core;

// Lib imports
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import org.jdom2.JDOMException;
import java.io.IOException;

// Application Imports
import rodrigorar.data.IOManager;
import rodrigorar.exceptions.DoubleEntryException;
import rodrigorar.exceptions.InexistentEntityException;
import rodrigorar.exceptions.InexistentBookException;
import rodrigorar.exceptions.InexistentReadingFileException;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.EmptyAuthorException;

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

    private void setInstance(Library value) {
        _instance = value;
    }

    public void loadBookDatabase() {
        try {
            this.setBookDB(IOManager.getInstance().buildBookDatabase());
        } catch (EmptyTitleException
                | EmptyAuthorException
                | JDOMException
                | IOException exception) {

            this.setBookDB(new TreeMap<String, Book>());
        }
    }

    public void loadReadingFileDatabase() {
        try {
            this.setReadingFileDB(IOManager.getInstance().buildReadingFileDatabase());
        } catch (EmptyTitleException
                | JDOMException
                | IOException exception) {

            this.setReadingFileDB(new TreeMap<String, ReadingFile>());
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

    public Book getBook(String bookID)
    throws
    InexistentBookException {
        Map<String,Book> bookDatabase = this.getBookDB();
        System.out.println(bookID);
        if (bookDatabase.containsKey(bookID)) {
            return bookDatabase.get(bookID);
        } else {
            throw new InexistentBookException();
        }
    }

    public Map<String, Book> getAllMatchingBooks(String bookID) {
        Map<String, Book> matchingBooks = new TreeMap<String, Book>();
        Map<String, Book> bookDatabase = this.getBookDB();
        Set<String> bookTitles = bookDatabase.keySet();
        Book iterator = null;

        for (String title : bookTitles) {
            iterator = bookDatabase.get(title);
            if (iterator.matchesEntity(bookID)) {
                matchingBooks.put(title, iterator);
            }
        }

        return matchingBooks;
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

    public ReadingFile getReadingFile(String readingFileID)
    throws
    InexistentReadingFileException {
        Map<String, ReadingFile> readingFileDatabase = this.getReadingFileDB();

        if (readingFileDatabase.containsKey(readingFileID)) {
            return readingFileDatabase.get(readingFileID);
        } else {
            throw new InexistentReadingFileException();
        }
    }

    public Map<String, ReadingFile> getAllMatchingReadingFiles(String rfID) {
        Map<String, ReadingFile> matchingReadingFiles = new TreeMap<String, ReadingFile>();
        Map<String, ReadingFile> readingFileDatabase = this.getReadingFileDB();
        Set<String> titles = readingFileDatabase.keySet();
        ReadingFile iterator = null;

        for (String title : titles) {
            iterator = readingFileDatabase.get(title);
            if (iterator.matchesEntity(rfID)) {
                matchingReadingFiles.put(title, iterator);
            }
        }

        return matchingReadingFiles;
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
