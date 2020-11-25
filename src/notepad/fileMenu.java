package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class fileMenu extends menu{

    private JMenu file;
    private JLabel status;
    private boolean isSaved = false;
    private String path;
    private JSeparator redSeparator = new JSeparator();

    //constructor

    public fileMenu(JFrame frame,JTextArea textArea, JLabel status) {
        super(frame,textArea);
        this.status = status;
        file = createMenu("File");
        file.setMnemonic('F');

       //SUBMENU

        JMenuItem open = createSubMenu("Open", "O", file);
        JMenuItem save = createSubMenu("Save","S", file);
        JMenuItem saveAs = createSubMenu("Save As","A", file);
        file.add(redSeparator);
        redSeparator.setBackground(Color.red);
        JMenuItem exit = createSubMenu("Exit", "E", file);

        //ACTIONLISTENERS

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser(path);

                int result = fileChooser.showOpenDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        FileReader reader = new FileReader(selectedFile.getAbsoluteFile());
                        textArea.read(reader, null);
                        frame.setTitle(fileChooser.getSelectedFile().getName());

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                fileChooser.setVisible(true);
            }
        });

        save.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                if (!isSaved) {
                    saveAs();
                }
                else {
                    FileWriter writer;
                    try {
                        writer = new FileWriter(path);
                        textArea.write(writer);
                        status.setText("Saved");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            }
         }
        });
        saveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
        exit.addActionListener(new ActionListener() {// EXIT
           @Override
           public void  actionPerformed(ActionEvent e) {
               int closeConfirm = JOptionPane.YES_NO_OPTION;
               JOptionPane.showConfirmDialog(null, "Are you sure?", "", closeConfirm);
                if(closeConfirm == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
           }
       });
    }
    public JMenuItem getFile() {
        return file;
    }

    void saveAs() {
        JFileChooser fileChooser = new JFileChooser(path);
       int result = fileChooser.showSaveDialog(getFrame());
        if(result == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                getTextArea().write(writer);
                path = fileChooser.getSelectedFile().getAbsolutePath();
                getFrame().setTitle(fileChooser.getSelectedFile().getName());
                status.setText("Saved");
                isSaved = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        fileChooser.setVisible(true);
    }
}
