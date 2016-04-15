package readernotes.src.database;

import java.io.IOException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import readernotes.src.core.Book;

public class BookXML extends AbstractXMLObjectBuilder {
	private Book _bookObject;

	public BookXML(Book bookObject) {
		this.setBookObject(bookObject);
	}

	public void setBookObject(Book bookObject) {
		_bookObject = bookObject;
	}

	public Book getBookObject() {
		return _bookObject;
	}

	public Element createTitleElement() {
		Element titleElement = new Element("Title");
		titleElement.setText(_bookObject.getTitle());
		return titleElement;
	}

	public Element createAuthorElement() {
		Element authorElement = new Element("Author");
		authorElement.setText(_bookObject.getAuthor());
		return authorElement;
	}

	public Element createSinopseElement() {
		Element sinopseElement = new Element("Sinopse");
		sinopseElement.setText(_bookObject.getSinopse());
		return sinopseElement;
	}

	public void buildXMLObject() {
		Element bookElement = new Element("Book");
		
        bookElement.addContent(this.createTitleElement());
		bookElement.addContent(this.createAuthorElement());
		bookElement.addContent(this.createSinopseElement());

		setXMLObject(bookElement);
	}

}
