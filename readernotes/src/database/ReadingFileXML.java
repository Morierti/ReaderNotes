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

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.IOException;
import readernotes.src.core.ReadingFile;
import readernotes.src.exceptions.EmptyTitleException;


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

        this.setXMLObject(readingFileElement);
	}

    public static ReadingFile buildReadingFile(Element readingFileElement)
    throws
    EmptyTitleException {
        ReadingFile newReadingFile = new ReadingFile(readingFileElement.getChild("Title").getText(),
                                                        readingFileElement.getChild("Book_Title").getText(),
                                                        readingFileElement.getChild("Content").getText());
        return newReadingFile;
    }
}
