package readernotes.src.database;

/**
 * Pass the construction of book and sintese objectos to the respective
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
import readernotes.src.core.Sintese;
import readernotes.src.core.Book;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentSinteseException;
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
        loadDatabases();
    }

    private void loadDatabases() {
    	try {
    		this.readBookDatabaseDocument();
    		this.readSinteseDatabaseDocument();
    	} catch (JDOMException | IOException exception) {
    		//REVER ISTO - Pensar se as excepções devem ser apanhadas aqui
    		// ou se devem ser passadas para cima.
		}
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
        Document xmlDocument = saxbuilder.build(new File(filepath));
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

    public Map<String, Sintese> buildSinteseDatabase()
    throws
    EmptyTitleException,
    JDOMException,
    IOException {
        Map<String, Sintese> sinteseDB = new HashMap<String, Sintese>();
        Map<String, Element> sinteseXMLObjects = this.readSinteseDatabaseDocument();
        if (!sinteseXMLObjects.isEmpty()) {
            Set<String> sinteseKeySet = sinteseXMLObjects.keySet();
            for (String sinteseKey : sinteseKeySet) {
                Element sinteseElement = sinteseXMLObjects.get(sinteseKey);
                Sintese newSintese = new Sintese(sinteseElement.getChild("Title").getText(),
                                                 sinteseElement.getChild("Book_Title").getText(),
                                                 sinteseElement.getChild("Content").getText());
                sinteseDB.put(sinteseKey, newSintese);
            }
        }
        return sinteseDB;
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

    public Map<String, Element> buildSinteseXMLObjects()
    throws
    InexistentSinteseException {
        Library library = Library.getInstance();
        Set<String> keySinteseDB = library.getSinteseDB().keySet();
        Map<String,Element> sinteseXMLObjects = new HashMap<String,Element>();
        for (String iterSinteseKey : keySinteseDB) {
            SinteseXML iterSinteseXML = new SinteseXML(library.getSintese(iterSinteseKey));
            iterSinteseXML.buildXMLObject();
            sinteseXMLObjects.put(iterSinteseKey, iterSinteseXML.getXMLObject());
        }
        return sinteseXMLObjects;
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

    public Document writeSinteseDatabaseDocument()
    throws
    InexistentSinteseException {
        Document xmlDocument = new Document(new Element("Sinteses"));
        Element rootElement = xmlDocument.getRootElement();
        Map<String,Element> sinteseXMLObjects = this.buildSinteseXMLObjects();
        Set<String> sinteseDBKeys = sinteseXMLObjects.keySet();
        for (String sinteseKey : sinteseDBKeys) {
            Element sinteseKeyElement = sinteseXMLObjects.get(sinteseKey);
            sinteseKeyElement.detach();
            rootElement.addContent(sinteseKeyElement);
        }
        this.printDocumentToFile(this.buildFilePath("sinteseDB"), xmlDocument);
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

    public Map<String, Element> readSinteseDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> sinteseXMLObjects = new HashMap<String,Element>();
        Document sinteseDocument = this.buildXMLDocument(this.buildFilePath("sinteseDB"));
        List<Element> sinteses = sinteseDocument.getRootElement().getChildren("Sintese");
        for (Element iterator : sinteses) {
            sinteseXMLObjects.put(iterator.getChildText("Title"), iterator);
        }
        return sinteseXMLObjects;
    }

}
