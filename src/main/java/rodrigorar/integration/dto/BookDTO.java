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

package rodrigorar.integration.dto;

public class BookDTO {
    private String _title;
    private String _author;
    private String _isbn;
    private String _subject;
    private String _sinopse;

    public BookDTO(String title, String author, String isbn, String subject,
                   String sinopse) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setISBN(isbn);
        this.setSubject(subject);
        this.setSinopse(sinopse);
    }


    private void setTitle(String title) {
        _title = title;
    }

    public String getTitle() {
        return _title;
    }

    private void setAuthor(String author) {
        _author = author;
    }

    public String getAuthor() {
        return _author;
    }

    private void setISBN(String isbn) {
        _isbn = isbn;
    }

    public String getISBN() {
        return _isbn;
    }

    private void setSubject(String subject) {
        _subject = subject;
    }

    public String getSubject() {
        return _subject;
    }

    private void setSinopse(String sinopse) {
        _sinopse = sinopse;
    }

    public String getSinopse() {
        return _sinopse;
    }
}
