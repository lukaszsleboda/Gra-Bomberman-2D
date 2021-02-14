package game.object;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Explosion extends Actor {

    /**
     * Referencja na obiekt gracza
     */
    Player player = Player.getInstance();


    /**
     * Konstruktor klasy explosion
     *
     * @param x polozenie w osi x
     * @param y polozenie w osi y
     * @param type eksplozja(lewa, prawa, ...)
     */
    public Explosion(int x, int y, String type) {
        super(x, y);
        initExplosion(type);
    }


    /**
     * Metoda ustawiajaca grafike eksplozji
     * @param type typ eksplozji
     */
    private void initExplosion(String type) {
        switch (type) {
            case "left":
                ImageIcon icon1 = new ImageIcon(Constants.explosionLGraphic);
                Image image = icon1.getImage();
                setImage(image);
                break;

            case "right":
                ImageIcon icon2 = new ImageIcon(Constants.explosionRGraphic);
                Image image2 = icon2.getImage();
                setImage(image2);
                break;

            case "down":
                ImageIcon icon3 = new ImageIcon(Constants.explosionDGraphic);
                Image image3 = icon3.getImage();
                setImage(image3);
                break;

            case "up":
                ImageIcon icon4 = new ImageIcon(Constants.explosionUGraphic);
                Image image4 = icon4.getImage();
                setImage(image4);
                break;

            case "mid":
                ImageIcon icon5 = new ImageIcon(Constants.explosionCGraphic);
                Image image5 = icon5.getImage();
                setImage(image5);
                break;

            default:
                break;
        }

    }


    /**
     * Metoda niszczaca sciany w zasiegu bomby i dodajaca punkty gracza
     *
     * @param dstwalls lista zniszczalnych obiektow klasy siana
     */
    public void destroyWall(ArrayList<DestructibleWall> dstwalls, ArrayList<Wall> walls, ArrayList<PointsPack> pointsPacks) {
        for (int i = 0; i < dstwalls.size(); i++) {

            if ((dstwalls.get(i).x() == x()) && (dstwalls.get(i).y() == y())) {
                player.points += dstwalls.get(i).getWallPoints();
                if (dstwalls.get(i).isHiddenSpike())
                {
                    Spike spike = new Spike(dstwalls.get(i).x(),dstwalls.get(i).y());
                    walls.add(spike);
                }
                if (dstwalls.get(i).isHiddenPointPack())
                {
                    PointsPack pointsPack = new PointsPack(dstwalls.get(i).x(),dstwalls.get(i).y());
                    pointsPacks.add(pointsPack);
                }
                dstwalls.remove(i);

            }
        }
    }


    /**
     * Metoda zabijajaca gracza
     * @param clippers lista clipperow
     */
    public void killClipper(ArrayList<Clipper> clippers) {
        for (int i = 0; i < clippers.size(); i++) {
            for (int k = 0; k < 30; k++) {
                if ((clippers.get(i).x() == x() + k) && (clippers.get(i).y() == y())) {
                    player.setPoints(player.getPoints()+clippers.get(i).getClipperPoints());
                    clippers.remove(i);
                } else if ((clippers.get(i).x() == x() - k) && (clippers.get(i).y() == y())) {
                    player.setPoints(player.getPoints()+clippers.get(i).getClipperPoints());
                    clippers.remove(i);
                }
            }
        }
    }


    /**
     * Czy gracz zostal zabity
     * @param player gracz
     * @return czy gracz zostal zabity
     */
    public boolean isPlayerKill(Player player) {
        for (int k = -25; k < 25; k++) {
            if (x() == player.x() + k && y() == player.y())
            {

                if (player.getLifes() == 1)
                {
                    player.setLifes(0);
                    return true;
                }
                else
                {
                    reduceLifes(player);
                }

            }
            else if (x() == player.x() - k && y() == player.y())
            {

                if (player.getLifes() == 1)
                {
                    player.setLifes(0);
                    return true;
                }
                else
                {
                    reduceLifes(player);
                }

            }
        }
        return false;
    }

    /**
     * Metoda redukujaca zycia i ustawiajaca pozytcje poczatkowa
     * Wywolywana jest po utracie zycia
     * @param player
     */
    private void reduceLifes(Player player)
    {
        player.setLifes(player.getLifes()-1);
        player.setStepCounter(10);
        player.setX(60);
        player.setY(60);
    }

}

