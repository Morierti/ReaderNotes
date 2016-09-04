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

package readernotes.src.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import readernotes.src.core.Library;
import readernotes.src.core.Book;
import readernotes.src.core.ReadingFile;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentReadingFileException;

public class SearchResultWindow extends JFrame {
    public static final String BOOK = "Book";
    public static final String READING_FILE = "Reading File";
    private String _itemID;
    private String _itemType;
    private JList _objectList;

    public SearchResultWindow(String itemID, String itemType) {
        this.setItemID(itemID);
        this.setItemType(itemType);
        this.initUI();
        this.setVisible(true);
    }

    public DefaultListModel loadFoundItems() {
        try {
            if (this.READING_FILE.equals(this.getItemType())) {
                return this.loadFoundReadingFiles(this.getItemID());
            }
            return this.loadFoundBooks(this.getItemID());
        }
        catch (InexistentBookException | InexistentReadingFileException exception) {
            System.err.print(exception.getMessage());
        }

        //Try to remove this.
        return new DefaultListModel();
    }

    public DefaultListModel loadFoundBooks(String itemID)
    throws
    InexistentBookException {
        Library library = Library.getInstance();
        Map<String, Book> foundBooks = library.getAllMatchingBooks(itemID);
        Set<String> bookTitles = foundBooks.keySet();
        DefaultListModel listModel = new DefaultListModel();

        for (String title : bookTitles) {
            listModel.addElement(title);
        }

        return listModel;
    }

    public DefaultListModel loadFoundReadingFiles(String itemID)
    throws
    InexistentReadingFileException {
        Library library = Library.getInstance();
        Map<String, ReadingFile> foundReadingFiles = library.getAllMatchingReadingFiles(itemID);
        Set<String> titles = foundReadingFiles.keySet();
        DefaultListModel listModel = new DefaultListModel();

        for (String title : titles) {
            listModel.addElement(title);
        }

        return listModel;

    }

    private void setItemID(String itemID) {
        _itemID = itemID;
    }

    private String getItemID() {
        return _itemID;
    }

    private void setItemType(String itemType) {
        _itemType = itemType;
    }

    private String getItemType() {
        return _itemType;
    }

    private JScrollPane createNewList() {
        final JList list = new JList(this.loadFoundItems());

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(300,400));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    final int index = list.locationToIndex(event.getPoint());
					final ListModel listModel = list.getModel();
					if (getItemType() == BOOK) {
                        new BookForm((String) listModel.getElementAt(index));
                    } else {
                        new ReadingFileForm((String) listModel.getElementAt(index));
                    }
                }
            }
        });
        return scrollPane;
    }

    public void createLayout() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(this.createNewList());
        this.add(panel);
    }

    public void initUI() {
        this.createLayout();
        this.setTitle("Search Results");
        this.setSize(400,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
