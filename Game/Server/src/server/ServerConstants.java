package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Klasa odpowiedzialna za wczytywanie plikow konfiguracyjnych
 */
public class ServerConstants {

    /**
     * Metoda wczytujaca z pliku wymiary okien dla poszczegolnych paneli
     * @return String - odpowiedz zawierajaca rozmiary okien oddzielone myslnikami
     */
    public static String loadWindowsConfig() throws IOException {
        InputStream propertiesFile = new FileInputStream("ServerResources\\WindowsSizes.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for(int i=0; i<10 ;i++){
            if (i!=9) response+=(properties.getProperty("value" + (i)) + "-");
            else response+=(properties.getProperty("value" + (i)));
        }
        propertiesFile.close();
        return response;
    }


    /**
     * Metoda wczytujaca z pliku nazwy guzikow oraz domyslne teksty etykiet
     * @return String - odpowiedz zawierajaca dane konfiguracyjne oddzielone myslnikami
     */
    public static String loadLabelANDButtonConfig() throws IOException
    {
        InputStream propertiesFile = new FileInputStream("ServerResources\\Label&ButtonConfig.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for (int i=0; i<9; i++)
        {
            if (i!=8) response+=(properties.getProperty("value" + (i)) + "-");
            else response += (properties.getProperty("value" + (i)));
        }
        propertiesFile.close();
        return response;
    }


    /**
     * Metoda wczytujaca z pliku dane rozgrywki  takiej jak predkosc czy liczba punktow
     * @return String - odpowiedz zawierajaca dane rozgrywki oddzielone myslnikami
     */
    public static String loadGameParameters() throws IOException
    {
        InputStream propertiesFile = new FileInputStream("ServerResources\\GameParameters.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for(int i=1; i<7 ;i++){
            if (i!=6) response+=(properties.getProperty("value" + (i)) + "-");
            else response+=(properties.getProperty("value" + (i)));
        }
        propertiesFile.close();
        return response;
    }


    /**
     * Metoda wczytujaca z pliku tekst instrukcji
     * @return String - tekst instrukcji
     */
    public static String loadInstruction() throws IOException
    {
        InputStream propertiesFile = new FileInputStream("ServerResources\\Instruction.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for (int i=0; i<14; i++)
        {
            if (i!=8) response+=(properties.getProperty("line" + (i)) + "-");
            else response += (properties.getProperty("line" + (i)));
        }

        propertiesFile.close();
        return response;
    }


    /**
     * Metoda wczytujaca z pliku wyglad mapy
     * @param levelIndex index levelu
     * @return szablon terenu mapy
     */
    public static String loadLevel(int levelIndex) throws IOException {

        InputStream propertiesFile = new FileInputStream("ServerResources\\Maps.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for (int i=1; i<16; i++)
        {
           if(i!=15) response += (properties.getProperty("map" + levelIndex + (i)) + "-");
           else response += (properties.getProperty("map" + levelIndex + (i)));
        }

        propertiesFile.close();
        return response;
    }
}
