package game.object;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa zniszczalnej sciany
 */
public class DestructibleWall extends Wall
{
    /**
     * Punkty przyznawane za zniszczenie sciany
     */
    public int wallPoints = Constants.wallPoints;

    /**
     * Flaga mowiaca czy w scianie ukryte sa kolce
     */
    private boolean hiddenSpike;

    /**
     * Metoda informujaca czy w scianie ukryta jest paczka z punktami
     */
    private boolean hiddenPointPack;

    /**
     * Konstruktor zniszczalnej sciany
     * @param x polozenie x
     * @param y polozenie y
     */
    public DestructibleWall(int x, int y, boolean isSpikeInside, boolean isPointPackInside) {
        super(x, y);
        hiddenSpike = isSpikeInside;
        hiddenPointPack = isPointPackInside;
        initDestructibleWall();
    }

    /**
     * Metoda ustawiajaca grafike sciany
     */
    private void initDestructibleWall() {

        ImageIcon iicon = new ImageIcon(Constants.dstWallGraphic);
        Image image = iicon.getImage();
        setImage(image);
    }

    /**
     * Geter
     * @return czy ukryte sa kolce
     */
    public boolean isHiddenSpike() {
        return hiddenSpike;
    }

    /**
     * Geter
     * @return liczba punktow sciany
     */
    public int getWallPoints() {
        return wallPoints;
    }

    /**
     * Geter
     * @return czy ukryte sa punkty
     */
    public boolean isHiddenPointPack() {
        return hiddenPointPack;
    }


}
