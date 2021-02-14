package game.object;

import Constants.Constants;
import game.object.Actor;

import javax.swing.*;
import java.awt.*;

/**
 * Konstruktor niezniszczalnej sciany
 */
public class Wall extends Actor
{
    /**
     * Grafika sciany
     */
    private Image image;

    /**
     * Konstruktor sciany
     * @param x polozenie x
     * @param y polozenie y
     */
    public Wall(int x, int y) {
        super(x, y);

        initWall();
    }

    /**
     * Metoda ustawiajaca grafike sciany
     */
    protected void initWall() {

        ImageIcon iicon = new ImageIcon(Constants.wallGraphic);
        image = iicon.getImage();
        setImage(image);
    }
}
