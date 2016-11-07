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

package readernotes.src.ui.dto;

public class ReadingFileDTO {
    private String _title;
    private String _bookTitle;
    private String _subject;
    private String _content;
    
    public ReadingFileDTO(String title, String bookTitle, String subject,
                          String content) {
        this.setTitle(title);
        this.setBookTitle(bookTitle);
        this.setSubject(subject);
        this.setContent(content);                     
    }
    
    public void setTitle(String title) {
        _title = title;
    }
    
    public String getTitle() {
        return _title;
    }
    
    public void setBookTitle(String bookTitle) {
        _bookTitle = bookTitle;
    }
    
    public String getBookTitle() {
        return _bookTitle;
    }
    
    public void setSubject(String subject) {
        _subject = subject;
    }
    
    public String getSubject() {
        return _subject;
    }
    
    public void setContent(String content) {
        _content = content;
    }
    
    public String getContent() {
        return _content;
    }
}
