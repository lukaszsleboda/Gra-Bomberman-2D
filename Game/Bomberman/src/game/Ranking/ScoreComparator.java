package game.Ranking;


import java.util.Comparator;

/**
 * Klasa porównywacza wyników w liście najlepszych graczy
 */
public class ScoreComparator implements Comparator<Score>
{
    /**
     * funkcja porównująca
     * @param score1 to wynik pierwszy
     * @param score2 to wynik drugi
     * @return zwraca rezultat porównania, 1. większy, drugi większy lub oba są równe
     */
    public int compare(Score score1, Score score2)
    {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2)
        {
            return -1;
        }

        else if (sc1 < sc2)
        {
            return +1;
        }

        else
        {
            return 0;
        }
    }
}