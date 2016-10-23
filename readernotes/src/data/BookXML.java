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

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import readernotes.src.core.Book;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class BookXML
extends AbstractXMLObjectBuilder {
	public static final String BOOK = "Book";
	public static final String TITLE = "Title";
	public static final String AUTHOR = "Author";
	public static final String SUBJECT = "Subject";
	public static final String ISBN = "ISBN";
	public static final String SINOPSE = "Sinopse";
	private Book _book;

	public BookXML(Book book) {
		this.setBook(book);
	}

	public void setBook(Book value) {
		_book = value;
	}

	public Book getBook() {
		return _book;
	}

	public Element createTitleElement() {
		Element titleElement = new Element(BookXML.TITLE);

		titleElement.setText(this.getBook().getTitle());

		return titleElement;
	}

	public Element createAuthorElement() {
		Element authorElement = new Element(BookXML.AUTHOR);

		authorElement.setText(this.getBook().getAuthor());

		return authorElement;
	}

	public Element createSubjectElement() {
		Element subjectElement = new Element(BookXML.SUBJECT);

		subjectElement.setText(this.getBook().getSubject());

		return subjectElement;
	}

	public Element createISBNElement() {
		Element isbnElement = new Element(BookXML.SUBJECT);

		isbnElement.setText(this.getBook().getISBN());

		return isbnElement;
	}

	public Element createSinopseElement() {
		Element sinopseElement = new Element(BookXML.SINOPSE);

		sinopseElement.setText(this.getBook().getSinopse());

		return sinopseElement;
	}

	public void buildXMLObject() {
		Element bookElement = new Element(BookXML.BOOK);

        bookElement.addContent(this.createTitleElement());
		bookElement.addContent(this.createAuthorElement());
		bookElement.addContent(this.createISBNElement());
		bookElement.addContent(this.createSubjectElement());
		bookElement.addContent(this.createSinopseElement());

		this.setXMLObject(bookElement);
	}

	public static Book buildBookObject(Element bookElement)
	throws
	EmptyTitleException,
	EmptyAuthorException {
		Book book = new Book(
			bookElement.getChild(BookXML.TITLE).getText(),
			bookElement.getChild(BookXML.AUTHOR).getText(),
			bookElement.getChild(BookXML.ISBN).getText(),
			bookElement.getChild(BookXML.SUBJECT).getText(),
			bookElement.getChild(BookXML.SINOPSE).getText()
		);
		return book;
	}

}
