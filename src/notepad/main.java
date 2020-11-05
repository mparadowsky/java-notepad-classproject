package notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class main {
    public static void main(String[] args) {

        JFrame jf = new JFrame("New Document");
        JTextArea textarea = new JTextArea();

        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));

        JLabel statusFg = new JLabel(" Fg");
        JLabel statusBg = new JLabel(" Bg");
        JLabel statusSize = new JLabel(" Size: --");
        JLabel fileStatus = new JLabel("New");

        statusBar.add(statusFg, Component.LEFT_ALIGNMENT);
        statusBar.add(statusBg, Component.LEFT_ALIGNMENT);
        statusBar.add(statusSize, Component.LEFT_ALIGNMENT);
        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(fileStatus, Component.RIGHT_ALIGNMENT);

        JScrollPane scrollBarVert = new JScrollPane(textarea);
        JScrollPane scrollBarHor = new JScrollPane(textarea);
        scrollBarVert.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBarHor.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JMenuBar mb = new JMenuBar();

        jf.add(mb);
        jf.setJMenuBar(mb);

        fileMenu fileMenu = new fileMenu(jf, textarea, fileStatus);
        editMenu editMenu = new editMenu(jf, textarea);
        optionsMenu optionsMenu = new optionsMenu(jf, textarea, statusFg, statusBg, statusSize);

        //TRACKING CHANGES IN THE DOCUMENT

        textarea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fileStatus.setText("Modified");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fileStatus.setText("Modified");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fileStatus.setText("Modified");
            }
        });

        mb.add(fileMenu.getFile());
        mb.add(editMenu.getEdit());
        mb.add(optionsMenu.getOptions());

        jf.add(scrollBarVert);
        jf.add(scrollBarHor);
        jf.add(statusBar, BorderLayout.SOUTH);

        jf.setLocation(50, 50);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setMinimumSize(new Dimension(250, 250));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
