package game.object;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa reprezentujaca kolce na planszy
 */
public class Spike extends Wall {
    /**
     * Konstruktor klasy Aktor
     *
     * @param x polozenie w osi x
     * @param y polozenie w osi y
     */
    public Spike(int x, int y) {
        super(x, y);

    }

    /**
     * Nadpisanie metody ustawiajacej grafike obiektu
     */
    @Override
    protected void initWall()
    {
        ImageIcon iicon = new ImageIcon(Constants.spikeGraphic);
        Image image = iicon.getImage();
        setImage(image);
    }


}
