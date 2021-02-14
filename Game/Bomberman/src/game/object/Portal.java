package game.object;

import Constants.Constants;
import game.object.Actor;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa Portalu
 */
public class Portal extends Actor
{

    Player player = Player.getInstance();

    /**
     * Konstruktor klasy Portal
     * @param x polozenie x
     * @param y polozenie y
     */
    public Portal(int x, int y) {
        super(x, y);

        initArea();
    }

    /**
     *Metoda ustawiajaca grafike gracza
     */
    private void initArea()
    {

        ImageIcon iicon = new ImageIcon(Constants.portalGraphic);
        Image image = iicon.getImage();
        setImage(image);
    }


    /**
     * Czy level jest zaliczony
     * @return czy zaliczony level
     */
    public boolean isLevelComplete()
    {
        if (player.x() == x() && player.y() == y())
        {
            return true;
        }
        return false;
    }
}
