package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import readernotes.src.core.Library;

public class RemoveSinteseWindow extends JFrame {
    private JTextArea _titleArea;

    public RemoveSinteseWindow() {
        this.initUI();
        this.setVisible(true);
    }

    private void setTitleArea(JTextArea titleArea) {
        _titleArea = titleArea;
    }

    private JTextArea getTitleArea() {
        return _titleArea;
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

    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(290,70,100,30);
        removeButton.addActionListener(new ActionListener() {
            //Some calls belong to RemoveBookWindow class.
            @Override
            public void actionPerformed(ActionEvent evetn) {
                //Code
                System.out.println("Remove Button Pressed.");
                System.out.println("Title: " + getTitleArea().getText().trim());
                Library library = Library.getInstance();
                library.removeSintese(getTitleArea().getText().trim());
                dispose();
            }
        });
        return removeButton;
    }

    private JButton createSeeSinteseButton() {
        JButton seeBookButton = new JButton("See Book");
        seeBookButton.setBounds(180,70,100,30);
        seeBookButton.addActionListener(new ActionListener() {
            //Some calls belong to RemoveBookWindow class.
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("See Sintese Button Pressed.");
                new SinteseForm(getTitleArea().getText().trim());
            }
        });
        return seeBookButton;
    }

    private void createLayout() {
        Container pane = this.getContentPane();
        JLabel titleLabel = this.createNewLabel("Title");

        this.setTitleArea(this.createTitleArea());

        titleLabel.setBounds(10,10,70,30);

        pane.add(titleLabel);
        pane.add(this.getTitleArea());
        pane.add(this.createSeeSinteseButton());
        pane.add(this.createRemoveButton());
    }

    private void initUI() {
        JPanel panel = new JPanel();
        this.createLayout();
        this.add(panel);
        this.setTitle("Remove Sintese");
        this.setSize(400,110);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
