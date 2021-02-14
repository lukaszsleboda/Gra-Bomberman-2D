package game.Additions;

import game.object.*;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za czity w grze
 */
public class Cheat {

    /**
     * Metoda sttyczna ustawiajaca zycia na 999
     */
    public static void unlimitedLifes()
    {
        Player player = Player.getInstance();
        player.setLifes(999);
    }

    /**
     * Metoda statyczna zaytrzymujaca clippery w miejscu
     * @param c lista clipperow
     */
    public static void StopClipers(ArrayList<Clipper> c)
    {
        for (int i = 0; i < c.size(); i++)
        {
            c.get(i).setDirection(0);
        }
    }

    /**
     * Metoda zabijajaca clipery
     * @param c lista clipperow
     */
    public static void killClipers(ArrayList<Clipper> c)
    {
        for (int i = 0; i < c.size(); i++)
        {
            c.remove(i);
        }
    }

    /**
     * Metoda statyczna pokaujaca lokalizacje paczek z punktami
     * @param d  lista zniszczalnych scian
     * @param p lista paczek z punktami
     */
    public static void showPointsPacks(ArrayList<DestructibleWall> d, ArrayList<PointsPack> p)
    {
        for (int i=0; i<d.size(); i++)
        {
            if (d.get(i).isHiddenPointPack())
            {
                PointsPack pointsPack = new PointsPack(d.get(i).x(),d.get(i).y());
                p.add(pointsPack);
                d.remove(i);
            }
        }
    }

    /**
     * Metoda dodajaca 50 punktow graczowi
     */
    public static void addPoints()
    {
        Player player = Player.getInstance();
        player.setPoints(player.getPoints()+50);
    }

    /**
     * Metoda pokazujaca portal do nastepnego levelu
     * @param portal referencja na portal
     * @param d lista zniszczalnych scian
     */
    public static void showPortal(Portal portal, ArrayList<DestructibleWall> d)
    {
        for (int i = 0; i < d.size(); i++)
        {
           if (d.get(i).x() == portal.x() && d.get(i).y() == portal.y())
           {
               d.remove(i);
           }
        }
    }


}
