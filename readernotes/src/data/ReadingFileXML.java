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

package readernotes.src.data;

// Lib Imports
import org.jdom2.Element;
import java.io.IOException;

// Application Imports
import readernotes.src.core.ReadingFile;
import readernotes.src.exceptions.EmptyTitleException;


public class ReadingFileXML
extends EntityXML {
	public static final String READING_FILE = "ReadingFile";
	public static final String TITLE = "Title";
	public static final String BOOK_TITLE = "Book_Title";
	public static final String SUBJECT = "Subject";
	public static final String CONTENT = "Content";
    private ReadingFile _readingFile;

	public ReadingFileXML(ReadingFile readingFile) {
        this.setReadingFile(readingFile);
	}

	public void setReadingFile(ReadingFile value) {
        _readingFile = value;
	}

	public ReadingFile getReadingFile() {
		return _readingFile;
	}

	public Element createTitleElement() {
		Element title = new Element(ReadingFileXML.TITLE);

        title.setText(this.getReadingFile().getTitle());

        return title;
	}

	public Element createBookTitleElement() {
		Element bookTitle = new Element(ReadingFileXML.READING_FILE);

        bookTitle.setText(this.getReadingFile().getBookTitle());

        return bookTitle;
	}

    public Element createSubjectElement() {
        Element subject = new Element(ReadingFileXML.SUBJECT);

        subject.setText(this.getReadingFile().getSubject());

        return subject;
    }

	public Element createContentElement() {
		Element content = new Element(ReadingFileXML.CONTENT);

        content.setText(this.getReadingFile().getContent());

        return content;
	}

	public void buildXMLObject() {
        Element readingFileElement = new Element(ReadingFileXML.READING_FILE);

        readingFileElement.addContent(this.createTitleElement());
        readingFileElement.addContent(this.createBookTitleElement());
        readingFileElement.addContent(this.createSubjectElement());
        readingFileElement.addContent(this.createContentElement());

        this.setXMLObject(readingFileElement);
	}

    public static ReadingFile buildReadingFile(Element readingFileElement)
    throws
    EmptyTitleException {
        ReadingFile readingFile = new ReadingFile(
            readingFileElement.getChild(ReadingFileXML.TITLE).getText(),
            readingFileElement.getChild(ReadingFileXML.BOOK_TITLE).getText(),
            readingFileElement.getChild(ReadingFileXML.SUBJECT).getText(),
            readingFileElement.getChild(ReadingFileXML.CONTENT).getText()
        );

        return readingFile;
    }
}
