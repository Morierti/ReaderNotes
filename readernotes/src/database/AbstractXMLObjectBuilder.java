package readernotes.src.database;

import java.io.IOException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;

public abstract class AbstractXMLObjectBuilder {
	private Element _xmlObject;

	public void setXMLObject(Element xmlObject) {
		_xmlObject = xmlObject;
	}

	public Element getXMLObject() {
		return _xmlObject;
	}
    
    public Document getXMLObjectDocument() {
        return new Document(getXMLObject());
    }
        

	public abstract void buildXMLObject();

	public void printXMLObject()
	throws
	IOException {
		if (getXMLObject() == null) {
			//throw new NullDocumentException();
			//NOT IMPLEMENTED
		} else {
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(getXMLObjectDocument(), System.out);
		}
	}
}
