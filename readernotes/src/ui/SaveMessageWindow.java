package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Dimension;
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

        return label;
    }

    private JPanel createLowerPanel() {
        JPanel lowerPanel = new JPanel();

        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));

        lowerPanel.add(Box.createHorizontalGlue());
        lowerPanel.add(this.createYesButton());
        lowerPanel.add(Box.createRigidArea(new Dimension(10,0)));
        lowerPanel.add(this.createNoButton());

        return lowerPanel;
    }

    private void createLayout(JPanel panel) {
        JLabel infoLabel = this.createInformationLabel();

        panel.add(infoLabel);
        panel.add(this.createLowerPanel());
    }

    private void initUI() {
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new GridLayout(2,0));

        this.createLayout(panel);
        this.add(panel);
        this.setTitle("Save");
        this.setSize(350,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
