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
    private static final boolean APPEND = true;
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

    public void writeBookToFile(String title)
    throws
    IOException,
    FileNotFoundException {
        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileWriter output = new FileWriter(bookDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);
        
        writer.write(title + "\n");
        
        writer.close();
        output.close();
    }

    public void writeBookToFile(String title, String author)
    throws
    IOException,
    FileNotFoundException {
        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileWriter output = new FileWriter(bookDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);

        writer.write(title + "|" + author + "\n");
        
        writer.close();
        output.close();
    }

    public void writeBookToFile(String title, String author, String sinopse)
    throws
    IOException,
    FileNotFoundException {
        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileWriter output = new FileWriter(bookDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);
        
        writer.write(title + "|" + author + "|" + sinopse + "\n");
        
        writer.close();
        output.close();
    }

    public void writeSinteseToFile(String title, String bookTitle)
    throws
    IOException,
    FileNotFoundException {
        File sinteseDatabase = new File(SINTESE_DB_FILEPATH);
        FileWriter output = FileWriter(sinteseDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);

        writer.write(title + "|" + bookTitle + "\n\n");

        writer.close();
        output.close();
    }

    public void writeSinteseToFile(String title, String bookTitle, String content)
    throws
    IOException,
    FileNotFoundException {
        File sinteseDatabase = new File(SINTESE_DB_FILEPATH);
        FileWriter output = new FileWriter(sinteseDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);
        
        writer.write(title + "|" + bookTitle + "\n");
        writer.write(content + "\n\n");
        
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

    public Map<String, List<String>> getAllParsedBooks() {
        return _books;
    }

    public Map<String, List<String>> getAllParsedSinteses() {
        return _sinteses;
    }

    public static boolean isEmpty() {
        return _instance == null;
    }


    //MANUAL TEST FOR THE CLASS
    public static void main(String[] args)
    throws
    BookNotFoundException,
    SinteseNotFoundException,
    IOException {
        IOManager io = IOManager.getInstance();

        io.writeBookToFile("Iliria de Homero");
        io.writeBookToFile("Memorial do Convento", "José Saramago");
        io.writeBookToFile("A Brave New World", "Haldous Huxley",
                           "A book about all the good things there are to come");
    }
}