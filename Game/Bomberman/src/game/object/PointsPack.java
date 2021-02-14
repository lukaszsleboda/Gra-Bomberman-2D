package game.object;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PointsPack extends Actor
    {

        /**
         * Referencja do gracza
         */
        Player player = Player.getInstance();

        /**
         * Punkty paczki
         */
        public int PPpoints;

        /**
         * Konstruktor klasy PointsPack
         * @param x polozenie x
         * @param y polozenie y
         */
    public PointsPack(int x, int y) {
        super(x, y);
        PPpoints = Constants.packPoints;
        initPointsPack();
    }

        /**
         *Metoda ustawiajaca grafike gracza
         */
        private void initPointsPack()
        {

            ImageIcon iicon = new ImageIcon(Constants.pointsPackGraphic);
            Image image = iicon.getImage();
            setImage(image);
        }


        /**
         * Czy paczka z punktami zostaje zebrana
         * @return czy zebrana
         */
        public boolean isPackPick()
        {
            if (player.x() == x() && player.y() == y())
            {
                player.setPoints(player.getPoints() + PPpoints);
                return true;
            }
            return false;
        }


    }

