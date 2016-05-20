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

    private void setInstance(IOManager instance) {
        _instance = instance;
    }

    private IOManager() {
        init();
    }

    private void init() {
        this.setInstance(this);
    }

    public void printDocument(String file, Document xmlDocument) {
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
            Set<String> keySet = bookXMLObjects.keySet();
            for (String key : keySet) {
                Element bookElement = bookXMLObjects.get(key);
                bookDB.put(key, BookXML.buildBookObject(bookElement));
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
        if (!readingFileXMLObjects.isEmpty()) {
            Set<String> keySet = readingFileXMLObjects.keySet();
            for (String key : keySet) {
                Element readingFileElement = readingFileXMLObjects.get(key);
                readingFileDB.put(key, ReadingFileXML.buildReadingFile(readingFileElement));
            }
        }
        return readingFileDB;
    }

    public Map<String, Element> buildBookXMLObjects()
    throws
    InexistentBookException {
        Library library = Library.getInstance();
        Set<String> keySet = library.getBookDB().keySet();
        Map<String,Element> bookXMLObjects = new HashMap<String,Element>();
        for (String key : keySet) {
            BookXML keyBookXML = new BookXML(library.getBook(key));
            keyBookXML.buildXMLObject();
            bookXMLObjects.put(key, keyBookXML.getXMLObject());
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
            ReadingFileXML keyReadingFileXML = new ReadingFileXML(library.getReadingFile(key));
            keyReadingFileXML.buildXMLObject();
            readingFileXMLObjects.put(key, keyReadingFileXML.getXMLObject());
        }
        return readingFileXMLObjects;
    }

    public void writeBookDatabaseDocument()
    throws
    InexistentBookException {
        Document xmlDocument = new Document(new Element("Books"));
        Element rootElement = xmlDocument.getRootElement();
        Map<String,Element> bookXMLObjects = this.buildBookXMLObjects();
        Set<String> keySet = bookXMLObjects.keySet();
        for (String key : keySet) {
            Element keyBookElement = bookXMLObjects.get(key);
            keyBookElement.detach();
            rootElement.addContent(keyBookElement);
        }
        this.printDocument(this.buildFilePath("bookDB"), xmlDocument);
    }

    public void writeReadingFileDatabaseDocument()
    throws
    InexistentReadingFileException {
        Document xmlDocument = new Document(new Element("ReadingFiles"));
        Element rootElement = xmlDocument.getRootElement();
        Map<String,Element> readingFileXMLObjects = this.buildReadingFileXMLObjects();
        Set<String> keySet = readingFileXMLObjects.keySet();
        for (String key : keySet) {
            Element keyReadingFileElement = readingFileXMLObjects.get(key);
            keyReadingFileElement.detach();
            rootElement.addContent(keyReadingFileElement);
        }
        this.printDocument(this.buildFilePath("readingFileDB"), xmlDocument);
    }

    public Map<String, Element> readBookDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> bookXMLObjects = new HashMap<String,Element>();
        Document document = buildXMLDocument(this.buildFilePath("bookDB"));
        List<Element> books = document.getRootElement().getChildren("Book");
        for (Element iterElement : books) {
            bookXMLObjects.put(iterElement.getChildText("Title"), iterElement);
        }
        return bookXMLObjects;
    }

    public Map<String, Element> readReadingFileDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> readingFileXMLObjects = new HashMap<String,Element>();
        Document document = this.buildXMLDocument(this.buildFilePath("readingFileDB"));
        List<Element> readingFiles = document.getRootElement().getChildren("ReadingFile");
        for (Element iterElement : readingFiles) {
            readingFileXMLObjects.put(iterElement.getChildText("Title"), iterElement);
        }
        return readingFileXMLObjects;
    }

}
