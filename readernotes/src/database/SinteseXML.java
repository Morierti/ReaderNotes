package readernotes.src.database;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.IOException;
import readernotes.src.core.Sintese;


public class SinteseXML extends AbstractXMLObjectBuilder {
    private Sintese _sintese;

	public SinteseXML(Sintese sinteseObject) {
        this.setSinteseObject(sinteseObject);
	}

	public void setSinteseObject(Sintese sinteseObject) {
        _sintese = sinteseObject;
	}

	public Sintese getSinteseObject() {
		return _sintese;
	}

	public Element createTitleElement() {
		Element titleElement = new Element("Title");
        titleElement.setText(_sintese.getTitle());
        return titleElement;
	}

	public Element createBookTitleElement() {
		Element bookTitleElement = new Element("Book_Title");
        bookTitleElement.setText(_sintese.getBookTitle());
        return bookTitleElement;
	}

	public Element createContentElement() {
		Element contentElement = new Element("Content");
        contentElement.setText(_sintese.getContent());
        return contentElement;
	}
	
	public void buildXMLObject() {
        Element sinteseElement = new Element("Sintese");
        
        sinteseElement.addContent(this.createTitleElement());
        sinteseElement.addContent(this.createBookTitleElement());
        sinteseElement.addContent(this.createContentElement());
        
        setXMLObject(sinteseElement);
	}
}
