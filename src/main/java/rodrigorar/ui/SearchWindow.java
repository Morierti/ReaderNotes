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

package rodrigorar.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JViewport;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchWindow
extends JFrame
implements ItemListener {
    public static final String BOOK = "Book";
    public static final String READING_FILE = "Reading File";
    private JScrollPane _itemIDArea;
    private String _searchParameter;
    private JButton _searchButton;

    public SearchWindow() {
        this.initUI();
        this.setSearchParameter("Book");
        this.setVisible(true);
    }

    private void setItemIDArea(JScrollPane itemIDArea) {
        _itemIDArea = itemIDArea;
    }

    private JScrollPane getItemIDArea() {
        return _itemIDArea;
    }

    private void setSearchParameter(String value) {
        _searchParameter = value;
    }

    private String getSearchParameter() {
        return _searchParameter;
    }

    private void setSearchButton(JButton value) {
        _searchButton = value;
    }

    private JButton getSearchButton() {
        return _searchButton;
    }

    private JLabel createNewLabel(String value) {
        JLabel label = new JLabel(value);

        label.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));

        return label;
    }

    private JScrollPane createItemIDArea() {
        JTextArea itemIDArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(itemIDArea);

        itemIDArea.setLineWrap(true);
        itemIDArea.setWrapStyleWord(true);
        itemIDArea.setPreferredSize(new Dimension(110,20));

        return scrollPane;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        setSearchParameter((String) event.getItem());
        System.out.println("Search Parameter: " + this.getSearchParameter());
    }

    private JComboBox<String> createJComboBox() {
        String[] selectionTypes = {BOOK, READING_FILE};
        JComboBox<String> selectionList = new JComboBox<String>(selectionTypes);

        selectionList.addItemListener(this);

        return selectionList;
    }

    private JButton createSearchButton() {
        JButton button = new JButton("Search");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JViewport viewport = getItemIDArea().getViewport();
                JTextArea itemIDArea = (JTextArea) viewport.getView();

                new SearchResultWindow(itemIDArea.getText().trim(), getSearchParameter());
            }
        });

        this.setSearchButton(button);
        return button;
    }

    private void createLayout(JPanel panel) {
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JLabel titleLabel = this.createNewLabel("Item ID");

        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));

        this.setItemIDArea(this.createItemIDArea());

        upperPanel.add(titleLabel);
        upperPanel.add(Box.createRigidArea(new Dimension(10,0)));
        upperPanel.add(this.getItemIDArea());
        upperPanel.add(Box.createRigidArea(new Dimension(5,0)));

        lowerPanel.add(this.createJComboBox());
        lowerPanel.add(Box.createHorizontalGlue());
        lowerPanel.add(this.createSearchButton());

        panel.add(upperPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(lowerPanel);
    }

    private void initUI() {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        this.createLayout(panel);
        this.add(panel);
        this.setTitle("Search");
        this.setSize(400,110);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
