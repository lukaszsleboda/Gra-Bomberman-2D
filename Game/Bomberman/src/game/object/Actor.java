package game.object;

import java.awt.*;

/**
 * Definicja klasy Actor
 */
public class Actor
{
    /**
     * Zmienna pomocna przy sprawdzaniu kolizji
     */
    private final int SPACE = 30;

    /**
     * polozenie w osi x na mapie
     */
    private int x;
    /**
     * Polozenie w osi y na mapie
     */
    private int y;
    /**
     * Grafika obiektu
     */
    private Image image;

    /**
     * Konstruktor klasy Aktor
     * @param x polozenie w osi x
     * @param y polozenie w osi y
     */
    public Actor(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Metoda zwracajaca grafike
     * @return grafika obiektu
     */
    public Image getImage() {
        return image;
    }

    /**
     * Metoda ustawiajaca grafike obiektu
     * @param img grafika obiektu
     */
    public void setImage(Image img) {
        image = img;
    }

    /**
     * Mrtoda zwracajaca polozenie
     * @return polozenie w osi x
     */
    public int x() {

        return x;
    }
    /**
     * Mrtoda zwracajaca polozenie
     * @return polozenie w osi y
     */
    public int y() {

        return y;
    }

    /**
     * Metoda ustawiajaca polozenie gracza w x
     * @param x polozenie w x
     */
    public void setX(int x) {

        this.x = x;
    }
    /**
     * Metoda ustawiajaca polozenie w y
     * @param y polozenie w y
     */
    public void setY(int y) {

        this.y = y;
    }

    /**
     * Metoda sprawdzjaca kolizje z lewej strony
     * @param actor obiekt ktorego kolizja bedzie sprawdzana
     * @return czy obiekt sie zdeza
     */
    public boolean isLeftCollision(Actor actor) {

            return x() - SPACE == actor.x() && y() == actor.y();

        }
    /**
     * Metoda sprawdzjaca kolizje z prawej strony
     * @param actor obiekt ktorego kolizja bedzie sprawdzana
     * @return czy obiekt sie zdeza
     */
    public boolean isRightCollision(Actor actor) {

        return x() + SPACE == actor.x() && y() == actor.y();
    }
    /**
     * Metoda sprawdzjaca kolizje z gory
     * @param actor obiekt ktorego kolizja bedzie sprawdzana
     * @return czy obiekt sie zdeza
     */
    public boolean isTopCollision(Actor actor) {

        return y() - SPACE == actor.y() && x() == actor.x();
    }
    /**
     * Metoda sprawdzjaca kolizje od dolu
     * @param actor obiekt ktorego kolizja bedzie sprawdzana
     * @return czy obiekt sie zdeza
     */
    public boolean isBottomCollision(Actor actor) {

        return y() + SPACE == actor.y() && x() == actor.x();
    }

}
