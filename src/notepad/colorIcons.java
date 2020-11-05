package notepad;

import javax.swing.*;
import java.awt.*;

public class colorIcons implements Icon {
    Color col;

    public colorIcons(Color col) {
        this.col = col;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(col);
        g.fillOval(17, 4, 10, 10);
        g.setColor(Color.black);
        g.drawOval(17, 4, 10, 10);
    }

    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}
