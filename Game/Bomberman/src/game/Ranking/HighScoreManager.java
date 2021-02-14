package game.Ranking;

import java.util.*;
import java.io.*;

/**
 * klasa kierownika wyników
 */
public class HighScoreManager
{
    /**
     * Deklaracja tablicy najlepszych wyników
     */
    private ArrayList<Score> scores;

    /**
     * Plik zawierający listę
     */
    private static final String HIGHSCORE_FILE = "scores.txt";

    /**
     * Przygotowanie do operacji na pliku
     */
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * Konstruktor kierownika wyników
     */
    public HighScoreManager()
    {
        /**
         * Inicjalizacja listy najlepszych wyników
         */
        scores = new ArrayList<Score>();
    }

    /**
     * Metoda pobierajaca wyniki
     * @return punkty gracza
     */
    public ArrayList<Score> getScores()
    {
        loadScoreFile();
        sort();
        return scores;
    }

    /**
     * Metoda sortująca wyniki za pomocą utworzonego porównywacza
     */
    private void sort()
    {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    /**
     * Metoda dodająca nowo uzyskany rezultat
     * @param name imie gracza
     * @param score punkty gracza
     */
    public void addScore(String name, int score)
    {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }

    /**
     * Metoda pobierająca dane z pliku
     */
    public void loadScoreFile()
    {
        try
        {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        }

        catch (FileNotFoundException e)
        {
            System.out.println("FNF Error: " + e.getMessage());
        }

        catch (IOException e)
        {
            System.out.println("IO Error: " + e.getMessage());
        }

        catch (ClassNotFoundException e)
        {
            System.out.println("CNF Error: " + e.getMessage());
        }

        finally
        {
            try
            {
                if (outputStream != null)
                {
                    outputStream.flush();
                    outputStream.close();
                }
            }

            catch (IOException e)
            {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }

    /**
     * Metoda aktualizująca listę nalepszych rezultatów
     */
    public void updateScoreFile()
    {
        try
        {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        }

        catch (FileNotFoundException e)
        {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        }

        catch (IOException e)
        {
            System.out.println("[Update] IO Error: " + e.getMessage());
        }

        finally
        {
            try
            {
                if (outputStream != null)
                {
                    outputStream.flush();
                    outputStream.close();
                }
            }

            catch (IOException e)
            {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    /**
     * Metoda zwracająca najlepsze wyniki
     * @return wynik
     */
    public String getHighscoreString()
    {
        String highscoreString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;

        int x = scores.size();

        if (x > max)
        {
            x = max;
        }

        while (i < x)
        {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getNaam() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}