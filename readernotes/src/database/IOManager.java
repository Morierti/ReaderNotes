package readernotes.src.database;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.io.IOException;
import java.io.FileWriter;
import readernotes.src.core.Library;
import readernotes.src.core.Sintese;
import readernotes.src.core.Book;

public class IOManager {
    private Map<String, Element> _bookDBObjects;
    private Map<String, Element> _sinteseDBObjects;
    private Library _library;
    private Document _xmlBookDB;
    private Document _xmlSinteseDB;
    private String _systemUsername;
    
    public IOManager() {
        init();
    }
    
    public void init() {
        _bookDBObjects = new HashMap<String, Element>();
        _sinteseDBObjects = new HashMap<String, Element>();
        _library = Library.getInstance();
        _systemUsername = System.getProperty("user.name");
    }
    
    public Map<String, Element> buildBookDBObjects() {
        Map<String, Book> bookDB = _library.getBookDB();
        return null;
    }
    
    public Map<String, Element> buildSinteseDBObjects() {
        Map<String, Sintese> sinteseDB = _library.getSinteseDB();
        return null;
    }
    
    public Map<String, Element> buildBookDatabase() {
        Set<String> keyBookDB = _library.getBookDB().keySet();
        for (String iterBookKey : keyBookDB) {
            Book iterBook = _library.getBook(iterBookKey);
            BookXML iterBookXML = new BookXML(iterBook);
            iterBookXML.buildXMLObject();
            _bookDBObjects.put(iterBookKey, iterBookXML.getXMLObject());
        }
        return _bookDBObjects;
    } 
    
    public Map<String, Element> buildSinteseDatabase() {
        Set<String> keySinteseDB = _library.getSinteseDB().keySet();
        for (String iterSinteseKey : keySinteseDB) {
            Sintese iterSintese = _library.getSintese(iterSinteseKey);
            SinteseXML iterSinteseXML = new SinteseXML(iterSintese);
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
        Element booksElement = new Element("Books");
        Document xmlDocument = new Document(booksElement);
        Set<String> bookDBKeys = _bookDBObjects.keySet();
        for (String bookKey : bookDBKeys) {
            Element iterElement = _bookDBObjects.get(bookKey);
            xmlDocument.getRootElement().addContent(iterElement);
        }
        String filePath = buildFilePath("bookDB");
        printDocumentToFile(filePath, xmlDocument);
        _xmlBookDB = xmlDocument;
        return xmlDocument;
    }
    
    public Document buildSinteseDatabaseDocument() {
        Element sintesesElement = new Element("Sinteses");
        Document xmlDocument = new Document(sintesesElement);
        Set<String> sinteseDBKeys = _sinteseDBObjects.keySet();
        for (String sinteseKey : sinteseDBKeys) {
            Element iterElement = _sinteseDBObjects.get(sinteseKey);
            xmlDocument.getRootElement().addContent(iterElement);
        }
        String filePath = buildFilePath("sinteseDB");
        printDocumentToFile(filePath, xmlDocument);
        _xmlSinteseDB = xmlDocument;
        return xmlDocument;
    }
    
    public void readBookDatabaseDocument() {
        
    }
    
    public void readSinteseDatabaseDocument() {
        
    }
    
    public Map<String, Book> getBookDatabase() {
        Map<String, Book> bookDatabase = new HashMap<String, Book>();
        return bookDatabase;
    }
    
    public Map<String, Sintese> getSinteseDatabase() {
        Map<String, Sintese> sinteseDatabase = new HashMap<String, Sintese>();
        return sinteseDatabase;
    }
    
}
