package readernotes.src.database;

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
import readernotes.src.core.Sintese;
import readernotes.src.core.Book;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentSinteseException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class IOManager {
    private static IOManager _instance;
    private Map<String, Element> _bookDBObjects;
    private Map<String, Element> _sinteseDBObjects;
    private Library _library;
    private Document _xmlBookDB;
    private Document _xmlSinteseDB;
    private String _systemUsername;
    
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
    
    public void init() {
        _instance = this;
        _bookDBObjects = new HashMap<String, Element>();
        _sinteseDBObjects = new HashMap<String, Element>();
        _library = Library.getInstance();
        _systemUsername = System.getProperty("user.name");
    }
    
    public Map<String, Book> buildBookDatabase()
    throws
    EmptyTitleException,
    EmptyAuthorException {
        HashMap<String, Book> bookDB = new HashMap<String, Book>();
        if (!_bookDBObjects.isEmpty()) {
            Set<String> bookKeySet = _bookDBObjects.keySet();
            for (String bookKey : bookKeySet) {
                Element bookElement = _bookDBObjects.get(bookKey);
                Book newBook = new Book(bookElement.getChild("Title").getText(),
                                        bookElement.getChild("Author").getText(),
                                        bookElement.getChild("Sinopse").getText());
                bookDB.put(bookKey, newBook);
            }
        }
        return bookDB;
    }
    
    public Map<String, Sintese> buildSinteseDatabase()
    throws
    EmptyTitleException {
        Map<String, Sintese> sinteseDB = new HashMap<String, Sintese>();
        if (!_sinteseDBObjects.isEmpty()) {
            Set<String> sinteseKeySet = _sinteseDBObjects.keySet();
            for (String sinteseKey : sinteseKeySet) {
                Element sinteseElement = _sinteseDBObjects.get(sinteseKey);
                Sintese newSintese = new Sintese(sinteseElement.getChild("Title").getText(),
                                                 sinteseElement.getChild("Book_Title").getText(),
                                                 sinteseElement.getChild("Content").getText());
                sinteseDB.put(sinteseKey, newSintese);
            }
        }
        return sinteseDB;
    }
    
    public Map<String, Element> buildBookDBObjects()
    throws
    InexistentBookException {
        Set<String> keyBookDB = _library.getBookDB().keySet();
        for (String iterBookKey : keyBookDB) {
            BookXML iterBookXML = new BookXML(_library.getBook(iterBookKey));
            iterBookXML.buildXMLObject();
            _bookDBObjects.put(iterBookKey, iterBookXML.getXMLObject());
        }
        return _bookDBObjects;
    } 
    
    public Map<String, Element> buildSinteseDBObjects()
    throws
    InexistentSinteseException {
        Set<String> keySinteseDB = _library.getSinteseDB().keySet();
        for (String iterSinteseKey : keySinteseDB) {
            SinteseXML iterSinteseXML = new SinteseXML(_library.getSintese(iterSinteseKey));
            iterSinteseXML.buildXMLObject();
            _sinteseDBObjects.put(iterSinteseKey, iterSinteseXML.getXMLObject());
        }
        return _sinteseDBObjects;
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
        String filepath = "/home/";
        filepath = filepath.concat(_systemUsername);
        filepath = filepath.concat("/.readernotes/");
        filepath = filepath.concat(filename);
        return filepath;
    }
    
    public Document buildBookDatabaseDocument() {
        Document xmlDocument = new Document(new Element("Books"));
        Set<String> bookDBKeys = _bookDBObjects.keySet();
        for (String bookKey : bookDBKeys) {
            xmlDocument.getRootElement().addContent(_bookDBObjects.get(bookKey));
        }
        this.printDocumentToFile(this.buildFilePath("bookDB"), xmlDocument);
        _xmlBookDB = xmlDocument;
        return xmlDocument;
    }
    
    public Document buildSinteseDatabaseDocument() {
        Document xmlDocument = new Document(new Element("Sinteses"));
        Set<String> sinteseDBKeys = _sinteseDBObjects.keySet();
        for (String sinteseKey : sinteseDBKeys) {
            xmlDocument.getRootElement().addContent(_sinteseDBObjects.get(sinteseKey));
        }
        this.printDocumentToFile(this.buildFilePath("sinteseDB"), xmlDocument);
        _xmlSinteseDB = xmlDocument;
        return xmlDocument;
    }
    
    public Document buildXMLDocument(String filepath)
    throws 
    JDOMException,
    IOException {
        SAXBuilder saxbuilder = new SAXBuilder();
        Document xmlDocument = saxbuilder.build(new File(filepath));
        return xmlDocument;
    }
    
    public Map<String, Element> readBookDatabaseDocument()
    throws 
    JDOMException,
    IOException {
        Document bookDBDocument = buildXMLDocument(this.buildFilePath("bookDB"));
        List<Element> books = bookDBDocument.getRootElement().getChildren("Book");
        for (Element iterator : books) {
            _bookDBObjects.put(iterator.getChildText("Title"), iterator);
        }
        return _bookDBObjects;
    }
    
    public Map<String, Element> readSinteseDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Document sinteseDocument = this.buildXMLDocument(this.buildFilePath("sinteseDB"));
        List<Element> sinteses = sinteseDocument.getRootElement().getChildren("Sintese");
        for (Element iterator : sinteses) {
            _sinteseDBObjects.put(iterator.getChildText("Title"), iterator);
        }
        return _sinteseDBObjects;
    }
    
}
