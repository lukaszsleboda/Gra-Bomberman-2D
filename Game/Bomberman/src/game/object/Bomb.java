package game.object;

import Constants.Constants;
import javax.swing.*;
import java.awt.*;


/**
 * Definicja klasy Bomb
 */

public class Bomb extends Actor {

    /**
     * Konstruktor bomby
     *
     * @param x polozenie w x
     * @param y polozenie w y
     */
    public Bomb(int x, int y)
    {
        super(x, y);
        initBomb();
    }


    /**
     * Metoda nadająca bombie wyjściową grafikę
     */
    private void initBomb()
    {
        ImageIcon icon1 = new ImageIcon(Constants.bombGraphic);
        Image image = icon1.getImage();
        setImage(image);
    }

    /**
     * metoda symulująca tykanie bomby obrazowana zmianą jej grafiki
     *
     * @param i odpowioada za przypisanie tyknięciu odpowiedniej dla niego grafiki
     */
    public void bombTicking(int i) {
        ImageIcon icon2 = new ImageIcon(Constants.bombGraphic);
        Image image1 = icon2.getImage();

        ImageIcon icon3 = new ImageIcon(Constants.explosionCGraphic);
        Image image2 = icon3.getImage();


        if (i == 0)
        {
            setImage(image1);
        }
        else if (i == 1)
        {
            setImage(image1);
        }
        else if (i ==2)
        {
            setImage(image2);
        }
    }


}



