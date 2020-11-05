package notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editMenu extends menu {
    private JMenu editMenu;

    public editMenu(JFrame jf, JTextArea textArea) {
        super(jf, textArea);

        editMenu = createMenu("Edit");
        editMenu.setMnemonic('E');

        //SUBMENU

        JMenu address = createMenu("Addresses");
        JMenuItem workAddress = createSubMenu("Work Address", "shift P", address);
        JMenuItem homeAddress = createSubMenu("Home Address", "shift D", address);
        JMenuItem schoolAddress = createSubMenu("School Address", "shift S", address);
        editMenu.add(address);

        //ACTIONLISTENERS

        workAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("\n Work Address \n Warsaw \n"); //dodaje w miejscu kursora tekst
            }
        });
        homeAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("\n Home Address \n Warsaw \n");
            }
        });
        schoolAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("\n School Address \n Warsaw \n");
            }
        });
    }
    public JMenu getEdit() {
        return editMenu;
    }

}
