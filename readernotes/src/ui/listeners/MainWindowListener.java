package readernotes.src.ui.listeners;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import readernotes.src.ui.SaveMessageWindow;

public class MainWindowListener implements WindowListener {

    public void windowActivated(WindowEvent event) {
        System.out.println("windowActivated method called");
    }

    public void windowClosed(WindowEvent event) {
        System.out.println("windowClosed method called");
    }

    public void windowClosing(WindowEvent event) {
        new SaveMessageWindow();
    }

    public void windowDeactivated(WindowEvent event) {
        System.out.println("windowDeactivated method called");
    }

    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified method called");
    }

    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified method called");
    }

    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened method called");
    }
}
