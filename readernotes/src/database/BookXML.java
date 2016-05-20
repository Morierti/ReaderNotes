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

import java.io.IOException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import readernotes.src.core.Book;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

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

		this.setXMLObject(bookElement);
	}

	public static Book buildBookObject(Element bookElement)
	throws
	EmptyTitleException,
	EmptyAuthorException {
		Book newBook = new Book(bookElement.getChild("Title").getText(),
								bookElement.getChild("Author").getText(),
								bookElement.getChild("Sinopse").getText());
		return newBook;
	}

}
