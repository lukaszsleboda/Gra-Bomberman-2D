package game.panel;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa logo gry
 */
public class Logo extends JPanel {

    /**
     * Logo gry
     * @param g grafika
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon pauseG = new ImageIcon(Constants.logoGraphic);
        Image pasueI = pauseG.getImage();
        g.drawImage(pasueI,0,0, this);
        g.setFont(new Font("Jokerman", Font.BOLD, 24));
        g.drawString("Sleboda&Gorecki", 10,30);

    }

}
