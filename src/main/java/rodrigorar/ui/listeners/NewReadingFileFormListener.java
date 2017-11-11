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

// Lib imports
import java.awt.event.ActionEvent;

// Application Imports
import rodrigorar.core.ReadingFile;
import rodrigorar.core.Library;
import rodrigorar.ui.NewReadingFileForm;
import rodrigorar.ui.MainWindow;
import rodrigorar.integration.dto.ReadingFileDTO;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.DoubleEntryException;

public class NewReadingFileFormListener
extends FormListener {
    private NewReadingFileForm _readingFileForm;
    private ReadingFileDTO _readingFileDTO;

    public NewReadingFileFormListener(NewReadingFileForm readingFileForm) {
        this.setReadingFileForm(readingFileForm);
    }

    private void setReadingFileForm(NewReadingFileForm readingFileForm) {
        _readingFileForm = readingFileForm;
    }

    public NewReadingFileForm getReadingFileForm() {
        return _readingFileForm;
    }

    private void setReadingFileDTO(ReadingFileDTO readingFileDTO) {
        _readingFileDTO = readingFileDTO;
    }

    public ReadingFileDTO getReadingFileDTO() {
        return _readingFileDTO;
    }

    @Override
    public void setup() {
        NewReadingFileForm readingFileForm = this.getReadingFileForm();
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
        try {
            Library library = Library.getInstance();
            ReadingFileDTO readingFileDTO = this.getReadingFileDTO();

            library.addReadingFile(
                        new ReadingFile(
                                readingFileDTO.getTitle(),
                                readingFileDTO.getBookTitle(),
                                readingFileDTO.getSubject(),
                                readingFileDTO.getContent()
                            )
                    );

             MainWindow.getInstance().updateReadingFileList();
             getReadingFileForm().dispose();

        } catch (EmptyTitleException | DoubleEntryException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
