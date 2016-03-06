package readernotes.src.database;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import readernotes.src.core.Book;

public class BookXML {
	private Book _bookObject;
	private Document _xmlDocument;

	public BookXML(Book bookObject) {
		setBookObject(bookObject);
	}

	public void setBookObject(Book bookObject) {
		_bookObject = bookObject;
	}

	public Book getBookObject() {
		return _bookObject;
	}

	public Element createTitleElement() {
	}

	public Element createAuthorElement() {
	}

	public Element createSinopseElement() {
	}

	public void buildDocument() {
	}

	public Document getDocument() {
		return _xmlDocument;
	}
}
