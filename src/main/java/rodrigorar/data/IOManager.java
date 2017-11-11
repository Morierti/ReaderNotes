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

package rodrigorar.data;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.input.SAXBuilder;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.List;
import java.io.*;

import rodrigorar.core.Library;
import rodrigorar.core.ReadingFile;
import rodrigorar.core.Book;
import rodrigorar.exceptions.InexistentBookException;
import rodrigorar.exceptions.InexistentReadingFileException;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.EmptyAuthorException;

public class IOManager {
    private static IOManager _instance;
    public static final String BOOK_DB = "bookDB";
    public static final String READINGFILE_DB = "readingFileDB";

    public static IOManager getInstance() {
        if (IOManager.isEmpty()) {
            new IOManager();
        }
        return _instance;
    }

    public static boolean isEmpty() {
        return _instance == null;
    }

    private void setInstance(IOManager value) {
        _instance = value;
    }

    private IOManager() {
        this.init();
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

    public Document buildDocument(String filepath)
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
        TreeMap<String, Book> bookDB = new TreeMap<String, Book>();
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
        Map<String, ReadingFile> readingFileDB = new TreeMap<String, ReadingFile>();
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
        Map<String,Element> bookXMLMap = new TreeMap<String,Element>();

        for (String key : keySet) {
            BookXML keyBookXML = new BookXML(library.getBook(key));
            keyBookXML.buildXMLObject();
            bookXMLMap.put(key, keyBookXML.getXMLObject());
        }

        return bookXMLMap;
    }

    public Map<String, Element> buildReadingFileXMLObjects()
    throws
    InexistentReadingFileException {
        Library library = Library.getInstance();
        Set<String> keySet = library.getReadingFileDB().keySet();
        Map<String,Element> readingFileXMLMap = new TreeMap<String,Element>();

        for (String key : keySet) {
            ReadingFileXML keyReadingFileXML = new ReadingFileXML(library.getReadingFile(key));
            keyReadingFileXML.buildXMLObject();
            readingFileXMLMap.put(key, keyReadingFileXML.getXMLObject());
        }

        return readingFileXMLMap;
    }

    public void writeBookDatabaseDocument()
    throws
    InexistentBookException {
        Document document = new Document(new Element("Books"));
        Element rootElement = document.getRootElement();
        Map<String,Element> bookXMLMap = this.buildBookXMLObjects();
        Set<String> keySet = bookXMLMap.keySet();

        for (String key : keySet) {
            Element bookElement = bookXMLMap.get(key);
            bookElement.detach();
            rootElement.addContent(bookElement);
        }

        this.printDocument(this.buildFilePath(IOManager.BOOK_DB), document);
    }

    public void writeReadingFileDatabaseDocument()
    throws
    InexistentReadingFileException {
        Document document = new Document(new Element("ReadingFiles"));
        Element rootElement = document.getRootElement();
        Map<String,Element> readingFileXMLMap = this.buildReadingFileXMLObjects();
        Set<String> keySet = readingFileXMLMap.keySet();

        for (String key : keySet) {
            Element readingFileElement = readingFileXMLMap.get(key);
            readingFileElement.detach();
            rootElement.addContent(readingFileElement);
        }
        this.printDocument(this.buildFilePath(IOManager.READINGFILE_DB), document);
    }

    public Map<String, Element> readBookDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> bookXMLObjects = new TreeMap<String,Element>();
        Document document = buildDocument(this.buildFilePath(IOManager.BOOK_DB));
        List<Element> books = document.getRootElement().getChildren("Book");

        for (Element iter : books) {
            bookXMLObjects.put(iter.getChildText("Title"), iter);
        }
        return bookXMLObjects;
    }

    public Map<String, Element> readReadingFileDatabaseDocument()
    throws
    JDOMException,
    IOException {
        Map<String,Element> readingFileXMLObjects = new TreeMap<String,Element>();
        Document document = this.buildDocument(this.buildFilePath(IOManager.READINGFILE_DB));
        List<Element> readingFiles = document.getRootElement().getChildren("ReadingFile");

        for (Element iter : readingFiles) {
            readingFileXMLObjects.put(iter.getChildText("Title"), iter);
        }
        return readingFileXMLObjects;
    }

}
