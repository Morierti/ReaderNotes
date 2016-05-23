package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import readernotes.src.core.Library;

public class SaveMessageWindow extends JFrame {

    public SaveMessageWindow() {
        this.initUI();
        this.setVisible(true);
    }

    private JButton createYesButton() {
        JButton button = new JButton("Yes");
        button.setBounds(230,60,70,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Library.getInstance().storeBookDatabase();
                Library.getInstance().storeReadingFileDatabase();
                System.exit(0);
            }
        });

        return button;
    }

    private JButton createNoButton() {
        JButton button = new JButton("No");
        button.setBounds(310,60,70,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        return button;
    }

    private JLabel createInformationLabel() {
        JLabel label = new JLabel("Do you want to save the modifications?");
        label.setBounds(60,10,300,30);
        return label;
    }

    private void createLayout(JPanel panel) {
        JLabel infoLabel = this.createInformationLabel();
        JButton noButton = this.createNoButton();
        JButton yesButton = this.createYesButton();

        panel.add(infoLabel);
        panel.add(noButton);
        panel.add(yesButton);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.createLayout(panel);
        this.add(panel);
        this.setTitle("Save");
        this.setSize(400,120);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
