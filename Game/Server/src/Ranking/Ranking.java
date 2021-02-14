package Ranking;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;
/**
 * Klasa obslugujaca liste najlepszych graczy
 */
public class Ranking {
    /**
     * Lista zawierajaca imie i punkty graczy
     */
    static ArrayList<String> ranking;
    /**
     * Metoda wczytujaca liste graczy i wynikow z pliku, przypisuje je do listy oraz sortuje
     */
    public static void loadRanking() throws IOException {
        InputStream propertiesFile = new FileInputStream("ServerResources\\Ranking.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        ranking = new ArrayList<>();
        for(int i = 1 ; i < 6 ; i++)
        {
            ranking.add(properties.getProperty("nick" + (i)) + "-" + properties.getProperty("score" + (i)));
        }
        propertiesFile.close();
        ranking.sort(new MyComparator());
    }
    /**
     * Metoda wywolywana po otrzymaniu komunikatu z prosba o wyslanie rankingu.
     * Tworzy linie tekstu zawierajaca zestaw nick plus punkty
     * @return String zawierajacy nicki i punkty graczy
     */
    public static String getRanking() throws IOException {
        InputStream propertiesFile = new FileInputStream("ServerResources\\Ranking.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for(int i = 0 ; i < 5 ; i++)
        {
            if(i<4) response+= ranking.get(i) + ", ";
            if(i==4) response+= ranking.get(i);
        }
        return response;
    }
    /**
     * Metoda wywolywana po otrzymaniu komunikatu z prosba o zapisanie wyniku w pliku
     * Wynik najpierw jest zapisywany, nastpnie lista jest sortowana a na koniec usuwany ostatni rekord
     * @param Nick nick gracza
     * @param score wynik gracza
     */
    public static void saveScore(String Nick, String score) throws IOException {
        ranking.add(Nick + "-" + score);
        ranking.sort(new MyComparator());
        ranking.remove(ranking.size()-1);
        SaveInFile();
    }
    /**
     * Implementacja interfejsu  klasy Comparator
     */
    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2){
            Integer a = Integer.parseInt(o1.split("-")[1]);
            Integer b = Integer.parseInt(o2.split("-")[1]);
            return -a.compareTo(b);
        }
    }
    /**
     * Metoda zapisujaca liste najlepszych wynikow do pliku Ranking.txt
     */
    static void SaveInFile() throws IOException {
        InputStream propertiesFile = new FileInputStream("ServerResources\\Ranking.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        for(int i=0;i<5;i++) {
            properties.setProperty("nick" + (i+1), ranking.get(i).split("-")[0]);
            properties.setProperty("score" + (i+1), ranking.get(i).split("-")[1]);
        }
        properties.store(new FileOutputStream("ServerResources\\Ranking.txt"), null);
        propertiesFile.close();
    }
}
