package readernotes.src.database;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.IOException;
import readernotes.src.core.ReadingFile;


public class ReadingFileXML extends AbstractXMLObjectBuilder {
    private ReadingFile _readingFile;

	public ReadingFileXML(ReadingFile readingFileObject) {
        this.setReadingFileObject(readingFileObject);
	}

	public void setReadingFileObject(ReadingFile readingFileObject) {
        _readingFile = readingFileObject;
	}

	public ReadingFile getReadingFileObject() {
		return _readingFile;
	}

	public Element createTitleElement() {
		Element titleElement = new Element("Title");
        titleElement.setText(_readingFile.getTitle());
        return titleElement;
	}

	public Element createBookTitleElement() {
		Element bookTitleElement = new Element("Book_Title");
        bookTitleElement.setText(_readingFile.getBookTitle());
        return bookTitleElement;
	}

	public Element createContentElement() {
		Element contentElement = new Element("Content");
        contentElement.setText(_readingFile.getContent());
        return contentElement;
	}

	public void buildXMLObject() {
        Element readingFileElement = new Element("ReadingFile");

        readingFileElement.addContent(this.createTitleElement());
        readingFileElement.addContent(this.createBookTitleElement());
        readingFileElement.addContent(this.createContentElement());

        setXMLObject(readingFileElement);
	}
}
