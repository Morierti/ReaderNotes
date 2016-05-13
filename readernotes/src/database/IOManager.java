package readernotes.src.database;

/**
 * Pass the construction of book and readingFile objects to the respective
 * XML classes instead of being built here.
 **/

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.input.SAXBuilder;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.io.*;
import readernotes.src.core.Library;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Book;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentReadingFileException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class IOManager {
    private static IOManager _instance;

    public static boolean isEmpty() {
        return _instance == null;
    }

    public static IOManager getInstance() {
        if (IOManager.isEmpty()) {
            new IOManager();
        }
        return _instance;
    }

    private IOManager() {
        init();
    }

    private void init() {
        _instance = this;
    }

    public void printDocumentToFile(String file, Document xmlDocument) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(xmlDocument, new FileWriter(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String buildFilePath(String filename) {
        String systemUsername = System.getProperty("user.name");
        String filepath = "/home/";
        filepath = filepath.concat(systemUsername);
        filepath = filepath.concat("/.readernotes/");
        filepath = filepath.concat(filename);
        return filepath;
    }

    public Document buildXMLDocument(String filepath)
    throws
    JDOMException,
    IOException {
        SAXBuilder saxbuilder = new SAXBuilder();
        System.out.println("Built SAX Builder");
        Document xmlDocument = saxbuilder.build(new File(filepath));
        System.out.println("Build xml Document");
        return xmlDocument;
    }

    public Map<String, Book> buildBookDatabase()
    throws
    EmptyTitleException,
    EmptyAuthorException,
    JDOMException,
    IOException {
        HashMap<String, Book> bookDB = new HashMap<String, Book>();
        Map<String, Element> bookXMLObjects = this.readBookDatabaseDocument();
        if (!bookXMLObjects.isEmpty()) {
            Set<String> bookKeySet = bookXMLObjects.keySet();
            for (String bookKey : bookKeySet) {
                Element bookElement = bookXMLObjects.get(bookKey);
                Book newBook = new Book(bookElement.getChild("Title").getText(),
                                        bookElement.getChild("Author").getText(),
                                        bookElement.getChild("Sinopse").getText());
                bookDB.put(bookKey, newBook);
            }
        }
        return bookDB;
    }

    public Map<String, ReadingFile> buildReadingFileDatabase()
    throws
    EmptyTitleException,
    JDOMException,
    IOException {
        Map<String, ReadingFile> readingFileDB = new HashMap<String, ReadingFile>();
        Map<String, Element> readingFileXMLObjects = this.readReadingFileDatabaseDocument();
        if (readingFileXMLObjects == null) {
            System.out.println("IOMANAGER -> It's null");
        }
        if (!readingFileXMLObjects.isEmpty()) {
            Set<String> keySet = readingFileXMLObjects.keySet();
            for (String key : keySet) {
                Element readingFileElement = readingFileXMLObjects.get(key);
                ReadingFile newReadingFile = new ReadingFile(readingFileElement.getChild("Title").getText(),
                                                                readingFileElement.getChild("Book_Title").getText(),
                                                                readingFileElement.getChild("Content").getText());
                readingFileDB.put(key, newReadingFile);
            }
        }
        return readingFileDB;
    }

    public Map<String, Element> buildBookXMLObjects()
    throws
    InexistentBookException {
        Library library = Library.getInstance();
        Set<String> keyBookDB = library.getBookDB().keySet();
        Map<String,Element> bookXMLObjects = new HashMap<String,Element>();
        for (String iterBookKey : keyBookDB) {
            BookXML iterBookXML = new BookXML(library.getBook(iterBookKey));
            iterBookXML.buildXMLObject();
            bookXMLObjects.put(iterBookKey, iterBookXML.getXMLObject());
        }
        return bookXMLObjects;
    }

    public Map<String, Element> buildReadingFileXMLObjects()
    throws
    InexistentReadingFileException {
        Library library = Library.getInstance();
        Set<String> keySet = library.getReadingFileDB().keySet();
        Map<String,Element> readingFileXMLObjects = new HashMap<String,Element>();
        for (String key : keySet) {
            ReadingFileXML iterReadingFileXML = new ReadingFileXML(library.getReadingFile(key));
            iterReadingFileXML.buildXMLObject();
            readingFileXMLObjects.put(key, iterReadingFileXML.getXMLObject());
        }
        return readingFileXMLObjects;
    }

    public Document writeBookDatabaseDocument()
    throws
    InexistentBookException {
        Document xmlDocument = new Document(new Element("Books"));
        Element rootElement = xmlDocument.getRootElement();
        Map<String,Element> bookXMLObjects = this.buildBookXMLObjects();
        Set<String> bookDBKeys = bookXMLObjects.keySet();
        for (String bookKey : bookDBKeys) {
            Element bookKeyElement = bookXMLObjects.get(bookKey);
            bookKeyElement.detach();
            rootElement.addContent(bookKeyElement);
        }
        this.printDocumentToFile(this.buildFilePath("bookDB"), xmlDocument);
        return xmlDocument;
    }

    public Document writeReadingFileDatabaseDocument()
    throws
    InexistentReadingFileException {
        Document xmlDocument = new Document(new Element("ReadingFiles"));
        Element rootElement = xmlDocument.getRootElement();
        Map<String,Element> readingFileXMLObjects = this.buildReadingFileXMLObjects();
        Set<String> readingFileDBKeys = readingFileXMLObjects.keySet();
        for (String readingFileKey : readingFileDBKeys) {
            Element readingFileKeyElement = readingFileXMLObjects.get(readingFileKey);
            readingFileKeyElement.detach();
            rootElement.addContent(readingFileKeyElement);
        }
        this.printDocumentToFile(this.buildFilePath("readingFileDB"), xmlDocument);
        return xmlDocument;
    }

    public Map<String, Element> readBookDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> bookXMLObjects = new HashMap<String,Element>();
        Document bookDBDocument = buildXMLDocument(this.buildFilePath("bookDB"));
        List<Element> books = bookDBDocument.getRootElement().getChildren("Book");
        for (Element iterator : books) {
            bookXMLObjects.put(iterator.getChildText("Title"), iterator);
        }
        return bookXMLObjects;
    }

    public Map<String, Element> readReadingFileDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> readingFileXMLObjects = new HashMap<String,Element>();
        System.out.println("Built HashMap");
        Document readingFileDocument = this.buildXMLDocument(this.buildFilePath("readingFileDB"));
        System.out.println("Built Document");
        List<Element> readingFiles = readingFileDocument.getRootElement().getChildren("ReadingFile");
        for (Element iterator : readingFiles) {
            readingFileXMLObjects.put(iterator.getChildText("Title"), iterator);
        }
        System.out.println("Returning");
        return readingFileXMLObjects;
    }

}
