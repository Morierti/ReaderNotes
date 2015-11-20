package readernotes.src.core;

import java.util.List;
import java.util.ArrayList;

public class IOManager {
    private static IOManager _instance;
    private List<String> _books;
    private List<String> _sinteses;

    public void getInstance() {
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
        _books = new ArrayList<String>;
        _sintese = new ArrayList<String>;
    }

    public void writeBookToFile(String title, String author, String sinopse) {

    }

    public void writeSinteseToFile(String title, String bookTitle, String contnet) {

    }

    public void readBooksFromFile() {

    }

    public void readSintesesFromFile() {

    }

    public List<String> getBookParsedInfo(String bookTitle) {
        return null;
    }

    public List<String> getSinteseParsedInfo(String sinteseTitle) {
        return null;
    }

    public static boolean isEmpty() {
        return _instance == null;
    }
}