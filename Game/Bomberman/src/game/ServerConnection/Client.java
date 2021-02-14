package game.ServerConnection;

import Constants.Constants;

import java.io.*;
import java.net.Socket;

/**
 * Klasa odpowiedzialna za komunikacje z serwerem. Implementuje metody nawiazujące polaczenie z serwerem i wysylajace
 * do niego zadania
 */
public class Client {
    static Socket socket;
    /** informuje czy serwer jest osiagalny, true - nieaktwyny, false - aktywny */
    public static boolean isOffline = true;
    /**
     * Wysyla do serwera zadanie o wyslanie danych konfiguracyjnych wygladu okien, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @return linia tekstu bedaca odpowiedzia od serwera, zawiera informacje o rozmiarach okien
     */
    public static String getWindowsConfig() throws IOException {
        String respond = connect("getWindowsConfig");
        socket.close();
        return respond;
    }

    /**
     * Wysyla do serwera zadanie o wyslanie danych konfiguracyjnych wygladu etykiet i guzikow, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @return linia tekstu bedaca odpowiedzia od serwera, zawiera informacje o napisach na etykietach i guzikach
     */
    public static String getLabelsConfig() throws IOException
    {
        String respond= connect("getLabelsConfig");
        socket.close();
        return respond;
    }

    /**
     * Wysyla do serwera zadanie o wyslanie danych konfiguracyjnych rozgrywki, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @return linia tekstu bedaca odpowiedzia od serwera, zawiera informacje o danych takich jak: predkosc, liczba zyc, punktow
     */
    public static String getGameParameters() throws IOException
    {
        String respond = connect("getGameParameters");
        socket.close();
        return respond;
    }

    /**
     * Pozyskuje liste najlepszych wynikow z serwera, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @return linia tekstu zawierajaca kolejne nazwy graczy wraz z wynikami
     */
   public static String getRanking() throws IOException {
        String respond = connect("getRanking");
        socket.close();
        return respond;
    }

    /**
     * Wysyla do serwera zadanie o wyslanie danych konfiguracyjnych poziomu o zadanym indeksie, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @param index numer poziomu ktory chcemmy uzyskac
     * @return linia tekstu będąca odpowiedzia od serwera zawierajaca wyglad mapy
     */
    public static String getLevel(int index) throws IOException {
        String respond = connect("getLevel" + "-" + index);
        socket.close();
        return respond;
    }

    /**
     * Wysyla do serwera zadanie o wyslanie tekstu instrukcji, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @return linia tekstu bedaca odpowiedzia od serwera, zawiera tekst instrukcji
     */
    public static String getInstruction() throws IOException{
        String respond = connect("getInstruction");
        socket.close();
        return respond;
    }

    /**
     * Sprawdza czy program moze połaczyc sie z serwerem
     * @return boolean, jesli true - nie mozna ustanowic polaczenia, jesli false - udalo się ustanowic
     * polaczenie
     */
   public static boolean checkIfOffline(){
        try {
            isOffline = false;
            socket = new Socket(Constants.ipAddress, Constants.port);
        } catch (IOException e) {
            System.out.println("server offline");
            isOffline = true;
        }
        return isOffline;
    }

    /**
     * Ustanawia polaczenie z serwerem. Tworzy obiekt klasy socket i wysyla zadanie do serwera
     * @param command Tresc zadania
     * @return linia tekstu bddaca odpowiedzia serwera
     */
    private static String connect(String command) throws IOException {
        try {
            socket = new Socket(Constants.ipAddress, Constants.port);
        } catch (Exception e){
            System.out.println("Serwer offline");
        }
        socket = new Socket(Constants.ipAddress, Constants.port);
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(command);
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.readLine();
    }


    /**
     * Zapisuje wyniku na serwerze, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @param request nick gracza wraz z wynikiem odzielone znakiem "-"
     */
    public static void saveScore(String request) throws IOException {
        connect("saveScore" + "-" + request);
        socket.close();
    }
}

