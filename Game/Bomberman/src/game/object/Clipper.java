package game.object;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa clippera (creepera, wrogo do gracza nastawionego NPC)
 */
public class Clipper extends Actor {
    /**
     * Predkosc clippera
     */
    private int speed = Constants.clipperSpeed;
    /**
     * kierunek clippera
     */
    public int Direction = Constants.initialClipperDirection;

    /**
     * Liczba punktow za zabicie clippera
     */
    public int clipperPoints = Constants.clipperPoints;

    /**
     * Konstruktor clippera
     *
     * @param x polozenie w x
     * @param y polozenie w y
     */
    public Clipper(int x, int y)
    {
        super(x, y);
        initClipper();
    }

    /**
     * Metoda ustawiajaca grafike clippera
     */
    private void initClipper()
    {
        ImageIcon iicon = new ImageIcon(Constants.clipperGraphic);
        Image image = iicon.getImage();
        setImage(image);

    }

    /**
     * Metoda poruszajaca clipperem
     *
     * @param x o ile przesunac
     * @param y o ile przesunac
     */
    public void move(int x, int y)
    {
        int dx = x() + x * speed;
        int dy = y() + y * speed;
        setX(dx);
        setY(dy);
    }

    /**
     * Metoda sprawdzajaca kolizje gracza z wrogiem
     * @param player obiekt gracza
     * @return czy wystapila kolizja
     */
    public boolean isPlayerKill( Player player) {
        for (int k = -25; k < 25; k++)
        {
            if (x() == player.x() + k && y() == player.y()) {
                if (player.getLifes() == 1)
                {
                    player.setLifes(0);
                    return true;
                }
                else
                {
                    reduceLifes(player);
                }
            } else if (x() == player.x() && y() == player.y() + k) {
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
     * Metoda redukujaca liczbe zyc i ustawiajaca gracza w poczatkowej pozycji
     * Wywolywana jest gdy gracz traci zycie
     * @param player referencja na gracza
     */
    private void reduceLifes(Player player)
    {
        player.setLifes(player.getLifes()-1);
        player.setStepCounter(10);
        player.setX(60);
        player.setY(60);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }

    public int getClipperPoints() {
        return clipperPoints;
    }

    public void setClipperPoints(int clipperPoints) {
        this.clipperPoints = clipperPoints;
    }
}




