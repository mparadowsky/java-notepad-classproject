package notepad;

import javax.swing.*;
import java.awt.*;

public class menu {
    private JFrame frame;
    private JTextArea textArea;

    public menu(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
        textArea.setFont(new Font("serif", Font.PLAIN, 12));
    }


    public JMenuItem createSubMenu(String text, String keyStroke, JMenu menu) {
        JMenuItem subMenu = new JMenuItem(text);
        menu.add(subMenu);
        subMenu.setAccelerator(KeyStroke.getKeyStroke("control " + keyStroke));
        return subMenu;
    }

    public JMenu createMenu(String name) {
        JMenu menu = new JMenu(name);
        return menu;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
