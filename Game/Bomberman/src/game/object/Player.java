package game.object;

import Constants.Constants;
import game.object.Actor;

import javax.swing.*;
import java.awt.*;



public class Player extends Actor
{
    private static volatile Player instance;

    /**
     * Imie gracza
     */
    public String name;
    /**
     * Punkty gracza
     */
    public Integer points;
    /**
     * Zycia gracza
     */
    public Integer lifes = Constants.numberOfLifes;
    /**
     * Zmienna okreslajaca aktualny kierunek ruchu gracza
     */
    public String direct;

    /**
     * Licznik krokow gracza
     */
    private int stepCounter = 0;

    /**
     * Konstruktor Gracza
     * @param x polozenie w x
     * @param y polozenie w y
     */
    public Player(int x, int y) {
        super(x, y);
        initPlayer();
        if (instance != null) {
            throw new RuntimeException("Uzyj getInstance() ");
        }

    }

    /**
     * Metoda umozliwiajaca pobranie referencji do gracza
     * @return referencje do gracza
     */
    public static Player getInstance()
    {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) instance = new Player(60, 60);
            }
        }

        return instance;
    }


    /**
     * Metoda ustawiajaca grafike gracza
     */
    private void initPlayer() {

        ImageIcon iicon = new ImageIcon(Constants.playerDGraphic);
        Image image = iicon.getImage();
        setImage(image);

        name = "noname";
        points = 0;
        direct = "wait";
    }

    /**
     * Metoda poruszajaca graczem
     * @param x o ile przesunac w x
     * @param y o ile przesunac w y
     */
    public void move(int x, int y) {

        int dx = x() + x;
        int dy = y() + y;

        setX(dx);
        setY(dy);
    }

    /**
     * Seter punktow
     * @param points liczba punktow
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * Seter liczby zyc
     * @param lifes liczba zyc
     */
    public void setLifes(Integer lifes) {
        this.lifes = lifes;
    }

    /**
     * Geter kierunku
     * @return kierunek ruchu
     */
    public String getDirect() {
        return direct;
    }

    /**
     * Seter kierunku
     * @param direct kierunek ruchu
     */
    public void setDirect(String direct) {
        this.direct = direct;
    }

    /**
     * Metoda zwracajaca imie gracza
     * @return imie gracza
     */
    public String getName()
    {
        return name;
    }

    /**
     * Metoda ustawiajaca imie gracza
     * @param newname imie
     */
    public void setName(String newname)
    {
        name = newname;
    }

    /**
     * Metoda zwracajaca punkty gracza
     * @return punkty gracza
     */
    public Integer getPoints()
    {
        return points;
    }

    /**
     * Geter zyc
     * @return liczba zyc
     */
    public Integer getLifes() {return  lifes;}


    /**
     * Geter liczby krokow
     * @return
     */
    public int getStepCounter() {
        return stepCounter;
    }

    /**
     * Seter liczby krokow
     * @param stepCounter
     */
    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }
}

