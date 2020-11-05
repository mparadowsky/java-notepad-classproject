package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class optionsMenu extends menu {
    private JMenu options, foregroundSubMenu, backgroundSubMenu, fontSizeSubMenu;
    private JLabel statusLabFG, statusLabBG, statusLabSize;
    private String[] colors = {"Red", "Green", "Blue", "White", "Black", "Yellow"};

    private ButtonGroup btnFGColor = new ButtonGroup();
    private ButtonGroup btnBGColor = new ButtonGroup();
    private ButtonGroup btnFontSize = new ButtonGroup();

    public optionsMenu(JFrame frame, JTextArea textArea, JLabel statusLabFG, JLabel statusLabBG, JLabel statusLabSize) {
        super(frame, textArea);
        options = createMenu("Options");
        options.setMnemonic('O');

        this.statusLabBG = statusLabBG;
        this.statusLabFG = statusLabFG;
        this.statusLabSize = statusLabSize;

        //SUBMENU

        foregroundSubMenu = createMenu("Foreground");
        options.add(foregroundSubMenu);
        backgroundSubMenu = createMenu("Background");
        options.add(backgroundSubMenu);
        fontSizeSubMenu = createMenu("Font size");
        options.add(fontSizeSubMenu);

        for (int i = 0; i < colors.length; i++) {
            foregroundSubMenu(colors[i]);
            backgroundSubMenu(colors[i]);
        }

        for (int i = 8; i < 25; i += 4) {
            fontSizeSubMenu(i + " px", i);
        }


    }

    public JMenu getOptions() {
        return options;
    }

    //FONT SIZE CHANGING MENU

    void fontSizeSubMenu(String name, int size) {
        JMenuItem radioBtn = new JRadioButtonMenuItem(name);
        radioBtn.setFont(new Font("serif", Font.PLAIN, size));

        statusLabSize.setText(" Size: " + getTextArea().getFont().getSize() + " px");
        btnFontSize.add(radioBtn);

        radioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextArea().setFont(new Font("serif", Font.PLAIN, size));
                statusLabSize.setText(" Size: " + size + " px");
            }
        });
        fontSizeSubMenu.add(radioBtn);
    }

    //FONT COLOR CHANGING MENU

    void foregroundSubMenu(String color) {
        statusLabFG.setIconTextGap(30);
        statusLabFG.setIcon(new colorIcons(Color.black));

        Color col = getColor(color);
        colorIcons icon = new colorIcons(col);

        JMenuItem radioBtn = new JRadioButtonMenuItem("<html><p style='margin-left:10'>" + color + "</p></html>", icon);
        radioBtn.setPreferredSize(new Dimension(120, radioBtn.getPreferredSize().height));
        btnFGColor.add(radioBtn);
        foregroundSubMenu.add(radioBtn);

        radioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextArea().setForeground(col);
                statusLabFG.setIcon(icon);
            }
        });
    }

    //BACKGROUND COLOR CHANGING MENU

    void backgroundSubMenu(String color) {
        statusLabBG.setIconTextGap(30);
        statusLabBG.setIcon(new colorIcons(Color.WHITE));

        Color col = getColor(color);
        colorIcons icon = new colorIcons(col);
        JMenuItem radioBtn = new JRadioButtonMenuItem("<html><p style='margin-left:10'>" + color + "</p></html>", icon);
        radioBtn.setPreferredSize(new Dimension(120, radioBtn.getPreferredSize().height));
        btnBGColor.add(radioBtn);
        backgroundSubMenu.add(radioBtn);

        radioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTextArea().setBackground(col);
                statusLabBG.setIcon(icon);
            }
        });
    }

    Color getColor(String color) {
        Field field;
        Color col;
        try {
            field = Color.class.getField(color.toLowerCase());
            col = (Color) field.get(null);
        } catch (Exception e) {
            col = Color.black;
        }
        return col;
    }
}

