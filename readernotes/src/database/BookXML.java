package readernotes.src.database;

import java.io.IOException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
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
		Element titleElement = new Element("Title");
		titleElement.setText(_bookObject.getTitle());
		return titleElement;
	}

	public Element createAuthorElement() {
		Element authorElement = new Element("Author");
		titleElement.setText(_bookObject.getAuthor());
		return authorElement;
	}

	public Element createSinopseElement() {
		Element sinopseElement = new Element("Sinopse");
		sinopseElement.setText(_bookObject.getSinopse());
		return sinopseElement;
	}

	public void buidDocument()
	throws
	IOException {
		Element bookElement = new Element("Book");
		Document bookDocument = new Document(bookElement);
		Element title = createTitleElement();
		Element author = createAuthorElement();
		Element sinopse = createSinopseElement();
		bookDocument.getRootElement().addContent(title);
		bookDocument.getRootElement().addContent(author);
		bookDocument.getRootElement().addContent(sinopse);

		//TEST
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(bookDocument, System.out);
	}

	public Document getDocument() {
		return _xmlDocument;
	}

	public static void main(String[] args) {
		try {	
			Book newBook = new Book("Book Title", "Author Title");
			newBook.setSinopse("Book Sinopse or something");
			BookXML book = new BookXML(newBook);
			book.buildDocument();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
