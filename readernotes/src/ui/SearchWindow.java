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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchWindow
extends JFrame
implements ItemListener {
    public static final String BOOK = "Book";
    public static final String READING_FILE = "Reading File";
    private JTextArea _titleArea;
    private String _searchParameter;
    private JButton _seeObjectButton;

    public SearchWindow() {
        this.initUI();
        this.setSearchParameter("Book");
        this.setVisible(true);
    }

    private void setTitleArea(JTextArea titleArea) {
        _titleArea = titleArea;
    }

    private JTextArea getTitleArea() {
        return _titleArea;
    }

    private void setSearchParameter(String value) {
        _searchParameter = value;
    }

    private String getSearchParameter() {
        return _searchParameter;
    }

    private void setSeeObjectButton(JButton value) {
        _seeObjectButton = value;
    }

    private JButton getSeeObjectButton() {
        return _seeObjectButton;
    }

    private JLabel createNewLabel(String value) {
        JLabel label = new JLabel(value);
        label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
        return label;
    }

    private JTextArea createTitleArea() {
        JTextArea titleArea = new JTextArea();
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        titleArea.setBounds(60,15,320,20);
        return titleArea;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if(event.getStateChange() == ItemEvent.SELECTED) {
            String selectedValue = event.getItem().toString();
            System.out.println(selectedValue);
            this.setSearchParameter(selectedValue);
            this.getSeeObjectButton().setText("See " + selectedValue);
        }
    }

    private JComboBox<String> createJComboBox() {
        String[] selectionTypes = {BOOK, READING_FILE};
        JComboBox<String> selectionList = new JComboBox<String>(selectionTypes);
        selectionList.addItemListener(this);
        selectionList.setBounds(30,60,150,30);
        return selectionList;
    }

    private JButton createSeeObjectButton() {
        JButton button = new JButton("See Book");
        button.setBounds(230,60,150,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (getSearchParameter() == BOOK) {
                    new BookForm(getTitleArea().getText().trim());
                } else {
                    new ReadingFileForm(getTitleArea().getText().trim());
                }
            }
        });

        this.setSeeObjectButton(button);
        return button;
    }

    private void createLayout() {
        Container pane = this.getContentPane();
        JLabel titleLabel = this.createNewLabel("Title");

        this.setTitleArea(this.createTitleArea());

        titleLabel.setBounds(10,10,70,30);

        pane.add(titleLabel);
        pane.add(this.getTitleArea());
        pane.add(this.createJComboBox());
        pane.add(this.createSeeObjectButton());
    }

    private void initUI() {
        JPanel panel = new JPanel();
        this.createLayout();
        this.add(panel);
        this.setTitle("Search");
        this.setSize(400,110);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
