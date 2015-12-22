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

////// TESTE
import readernotes.src.exceptions.EmptyTitleException;

public class IOManager {
    private static final String BOOK_DB_FILEPATH = "/home/rodrigo/bookDB.txt";
    private static final String SINTESE_DB_FILEPATH = "/home/rodrigo/sinteseDB.txt";
    private static final int BOOK_TITLE = 0;
    private static final int BOOK_AUTHOR = 1;
    private static final int BOOK_SINOPSE = 2;
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

    public static void writeBookToDatabase(Book book)
    throws
    IOException,
    FileNotFoundException {
        String title = book.getTitle();
        String author = book.getAuthor();
        String sinopse = book.getSinopse();

        File bookDatabase = new File(BOOK_DB_FILEPATH);
        FileWriter output = new FileWriter(bookDatabase, APPEND);
        BufferedWriter writer = new BufferedWriter(output);

        writer.write(title + "|" + author + "|" + sinopse + "\n");

        writer.close();
        output.close();
    }

    public static List<String> readBookFromDatabase()
    throws
    IOException,
    FileNotFoundException {
      File bookDatabase = new File(BOOK_DB_FILEPATH);
      FileReader input = new FileReader(bookDatabase);
      BufferedReader reader = new BufferedReader(input);

      String book = reader.readLine();

      if (book != null) {
        String[] parsedBookInfo = book.split("\\|");
        ArrayList<String> newBookInfo = new ArrayList<String>();

        newBookInfo.add(parsedBookInfo[BOOK_TITLE]);
        newBookInfo.add(parsedBookInfo[BOOK_AUTHOR]);
        newBookInfo.add(parsedBookInfo[BOOK_SINOPSE]);

        return newBookInfo;
      } else {
        return null;
      }
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

    //FUNCAO DE TESTE

    public static void main(String[] args)
    throws
    IOException,
    FileNotFoundException,
    EmptyTitleException {
      Book book1 = new Book("Iliria de Homero", "Homero", "dkajshflkdsjfhsldk");
      Book book2 = new Book("", "", "");
      Book book3 = new Book("", "", "");
      Book book4 = new Book("", "", "");
      Book book5 = new Book("", "", "");

      writeBookToDatabase(book1);

      List<String> bookInfo = readBookFromDatabase();

      System.out.println(bookInfo.get(BOOK_TITLE) + " " + bookInfo.get(BOOK_AUTHOR)
                         + " " + bookInfo.get(BOOK_SINOPSE));
    }

}
