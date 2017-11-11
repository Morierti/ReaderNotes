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

package rodrigorar.ui.listeners;

import java.awt.event.ActionEvent;

import rodrigorar.ui.ReadingFileForm;
import rodrigorar.integration.dto.ReadingFileDTO;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.core.ReadingFile;

public class ReadingFileFormListener
extends FormListener {
    private ReadingFileForm _readingFileForm;
    private ReadingFileDTO _readingFileDTO;

    public ReadingFileFormListener(ReadingFileForm form) {
        this.setReadingFileForm(form);
    }

    public void setReadingFileForm(ReadingFileForm form) {
        _readingFileForm = form;
    }

    public ReadingFileForm getReadingFileForm() {
        return _readingFileForm;
    }

    public void setReadingFileDTO(ReadingFileDTO readingFileDTO) {
        _readingFileDTO = readingFileDTO;
    }

    public ReadingFileDTO getReadingFileDTO() {
        return _readingFileDTO;
    }

    @Override
    public void setup() {
        ReadingFileForm readingFileForm = this.getReadingFileForm();
        this.setReadingFileDTO(
            new ReadingFileDTO(
                this.parseScrollPane(readingFileForm.getTitleArea()),
                this.parseScrollPane(readingFileForm.getBookTitleArea()),
                this.parseScrollPane(readingFileForm.getSubjectArea()),
                this.parseScrollPane(readingFileForm.getContentArea())
            )
        );
    }

    @Override
    public void execute(ActionEvent event) {
        ReadingFileDTO readingFileDTO = this.getReadingFileDTO();
        ReadingFileForm readingFileForm = this.getReadingFileForm();
        ReadingFile readingFile = readingFileForm.getReadingFile();

        try {
            if (readingFileDTO.getTitle() != null) {
                readingFile.setTitle(readingFileDTO.getTitle());
            }
            if (readingFileDTO.getBookTitle() != null) {
                readingFile.setBookTitle(readingFileDTO.getBookTitle());
            }
            if (readingFileDTO.getSubject() != null) {
                readingFile.setSubject(readingFileDTO.getSubject());
            }
            if (readingFileDTO.getContent() != null) {
                readingFile.setContent(readingFileDTO.getContent());
            }
        } catch (EmptyTitleException exception) {
            System.err.print(exception.getMessage());
        }

        readingFileForm.dispose();
    }


}
