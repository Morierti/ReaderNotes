package readernotes.src.database;

import java.io.IOException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;

public abstract class AbstractXMLObjectBuilder {
	private Document _xmlDocument;

	public Document getXMLObject() {
		return _xmlDocument;
	}

	public abstract void buildDocument();

	public void printDocument()
	throws
	IOException {
		if (_xmlDocument == null) {
			//throw new NullDocumentException();
			//NOT IMPLEMENTED
		} else {
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(_xmlDocument, System.out);
		}
	}
}
