
package game.Ranking;

import java.io.Serializable;

/**
 * Klasa w danych gracza
 */

public class Score  implements Serializable
{
    private int score;
    private String naam;

    /**
     * funkcja zwracająca wynik uzyskany w podejściu
     * @return wynik w podejsciu
     */
    public int getScore()
    {
        return score;
    }

    /**
     * funkcja zwracająca nazwę gracza
     * @return nazwa gracza
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Konstruktor danych gracza
     * @param naam to nazwa gracza
     * @param score to wynik
     */
    public Score(String naam, int score)
    {
        this.score = score;
        this.naam = naam;
    }
}

