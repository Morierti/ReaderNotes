package readernotes.src.core;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import readernotes.src.exceptions.BookNotFoundException;
import readernotes.src.exceptions.SinteseNotFoundException;

public class IOManager {
    private static final String BOOK_DB_FILEPATH = "/home/rodrigo/bookDB.txt";
    private static final String SINTESE_DB_FILEPATH = "/home/rodrigo/sinteseDB.txt";
    private static IOManager _instance;
    private Map<String, List<String>> _books;
    private Map<String, List<String>> _sinteses;

    public static IOManager getInstance() {
        if (_instance.isEmpty()) {
            new IOManager();
        }
        return _instance;
    }

    private IOManager() {
        init();
    }

    public void init() {
        _instance = this;
        _books = new HashMap<String, List<String>>();
        _sinteses = new HashMap<String, List<String>>();
    }

    public void writeBookToFile(String title, String author, String sinopse)
    throws
    IOException,
    FileNotFoundException {
        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileWriter output = new FileWriter(bookDatabase);
        BufferedWriter writer = new BufferedWriter(output);
        writer.write(title + "|" + author + "|" + sinopse);
        writer.close();
        output.close();
    }

    public void writeSinteseToFile(String title, String bookTitle, String content)
    throws
    IOException,
    FileNotFoundException {
        File sinteseDatabase = new File(SINTESE_DB_FILEPATH);
        FileWriter output = new FileWriter(sinteseDatabase);
        BufferedWriter writer = new BufferedWriter(output);
        writer.write(title + "|" + bookTitle + "|" + content);
        writer.close();
        output.close();
    }

    public void readBooksFromFile()
    throws
    IOException,
    FileNotFoundException    {
        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileReader input = new FileReader(bookDatabase);
        BufferedReader reader = new BufferedReader(input);
    }

    public void readSintesesFromFile()
    throws
    IOException,
    FileNotFoundException {

    }

    public List<String> getBookParsedInfo(String bookTitle)
    throws
    BookNotFoundException {
        if (_books.containsKey(bookTitle)) {
            return _books.get(bookTitle);
        }
        throw new BookNotFoundException(bookTitle);
    }

    public List<String> getSinteseParsedInfo(String sinteseTitle)
    throws
    SinteseNotFoundException {
        if (_sinteses.containsKey(sinteseTitle)) {
            return _sinteses.get(sinteseTitle);
        }
        throw new SinteseNotFoundException(sinteseTitle);
    }

    public static boolean isEmpty() {
        return _instance == null;
    }
}